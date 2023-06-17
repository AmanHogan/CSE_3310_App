package com.example.cse3310defaultproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.ServiceCategory;
import com.example.cse3310defaultproject.data.model.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class PlaceService extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText mDateFormat;
    ArrayList<String> userInformation;
    Button confirmButton;
    DataRepository db;


    int counter = 0;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.db = DataRepository.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_service);


        Spinner spinner = findViewById(R.id.place_service_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_of_service_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDateFormat = findViewById(R.id.place_service_date_service);
        confirmButton = findViewById(R.id.confirm_changes);

        mDateFormat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        PlaceService.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }


        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth)
            {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDateFormat.setText(date);
            }
        };

        populateAutoComplete();
    }

    // Automatically populates the user information into the form
    private void populateAutoComplete()
    {
        User currentUser = db.getCurrentUser();

        EditText nameOfUser = (EditText) findViewById(R.id.place_service_user_name);
        nameOfUser.setText(currentUser.getName());

        EditText phoneOfUser = (EditText) findViewById(R.id.place_service_phone);
        phoneOfUser.setText(currentUser.getPhoneNumber());

        EditText emailOfUser = findViewById(R.id.place_service_email);
        emailOfUser.setText(currentUser.getEmail());

        EditText nameOnCard = findViewById(R.id.place_service_name);
        nameOnCard.setText(currentUser.getName());

        EditText cardNumberUser = (EditText)findViewById(R.id.place_service_caard_number);
        cardNumberUser.setText(currentUser.getCardNumber());

        EditText cardExpirationDate = (EditText)findViewById(R.id.place_service_exp_date);
        cardExpirationDate.setText(currentUser.getExpDateOnCard());

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }


    public void placeServiceRequest(View view)
    {
        // Get the text ID of text boxes
        EditText nameOfUser = (EditText) findViewById(R.id.place_service_user_name);
        EditText emailOfUser = (EditText)findViewById(R.id.place_service_email);
        EditText phoneOfUser = (EditText) findViewById(R.id.place_service_phone);
        EditText bidOfUser = (EditText)findViewById(R.id.place_service_bid);
        EditText locationService = (EditText) findViewById(R.id.place_service_location);
        EditText dateOfUser = (EditText) findViewById(R.id.place_service_date_service);
        EditText cardNumberUser = (EditText)findViewById(R.id.place_service_caard_number);
        EditText cardExpirationDate = (EditText)findViewById(R.id.place_service_exp_date);
        Spinner spinner = (Spinner)findViewById(R.id.place_service_spinner);
        TextInputLayout descriptionText = (TextInputLayout) findViewById(R.id.service_review);

        // If any of the fields are empty, prompt user to enter some text
        if(nameOfUser.getText().toString().isEmpty() || emailOfUser.getText().toString().isEmpty() || phoneOfUser.getText().toString().isEmpty()||
                locationService.getText().toString().isEmpty() ||cardExpirationDate.getText().toString().isEmpty()  || cardNumberUser.getText().toString().isEmpty() ||
                descriptionText.getEditText().getText().toString().isEmpty()){
            Toast.makeText(PlaceService.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
        }

        // If they did enter the correct data, send this information to the current signed in user into the database
        else
        {
            String nameOfUserData = nameOfUser.getText().toString();
            String emailOfUserData = emailOfUser.getText().toString();
            String phoneOfUserData = phoneOfUser.getText().toString();
            String locationServiceData = locationService.getText().toString();
            String bidOfUserData = bidOfUser.getText().toString();
            String dateOfUserData = dateOfUser.getText().toString();
            String serviceType = spinner.getSelectedItem().toString();
            String expDate = cardExpirationDate.getText().toString();
            String creditCard = cardNumberUser.getText().toString();
            String descriptionTextData = descriptionText.getEditText().getText().toString();
            User currentUser = db.getCurrentUser();
            currentUser.setCardNumber(creditCard);
            currentUser.setExpDateOnCard(expDate);

            ServiceCategory cat;
            switch (serviceType.toUpperCase())
            {
                case "TUTORING":
                    cat = ServiceCategory.Tutoring;
                    break;
                case "APPLIANCES":
                    cat = ServiceCategory.Appliances;
                    break;
                case "CLEANING":
                    cat = ServiceCategory.HomeCleaning;
                    break;
                case "PLUMBING":
                    cat = ServiceCategory.Plumbing;
                    break;
                case "ELECTRICAL":
                    cat = ServiceCategory.Electrical;
                    break;
                default:
                    cat = ServiceCategory.Appliances;
                    break;
            }

            String new_sr_id = db.addServiceRequest(db.getCurrentUser().getUserId(), descriptionTextData, dateOfUserData, Double.parseDouble(bidOfUserData), cat);

            // Start new activity
            Intent intent = new Intent(PlaceService.this, HomeActivity.class);
            intent.putExtra("new_sr_id", new_sr_id);
            startActivity(intent);
        }
    }
}




