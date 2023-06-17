package com.example.cse3310defaultproject.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cse3310defaultproject.HomeActivity;
import com.example.cse3310defaultproject.R;
import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.ServiceCategory;
import com.example.cse3310defaultproject.data.model.User;
import com.example.cse3310defaultproject.data.model.Vendor;
import com.example.cse3310defaultproject.databinding.ActivityRegisterBinding;

import java.util.ArrayList;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    // A collection of the service cats radio buttons
    private RadioButton[] serviceCats;
    private DataRepository db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.db = DataRepository.getInstance();

        // Collection of all of our radio buttons for later
        RadioButton[] serviceCats = new RadioButton[] {
                binding.radioButtonAppliances,
                binding.radioButtonTutoring,
                binding.radioButtonElectrical,
                binding.radioButtonPlumbing,
                binding.radioButtonHomeCleaning,
                binding.radioButtonPackagingAndMoving,
        };
        this.serviceCats = serviceCats;

        // When we click the register button, call register()
        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { register(); }
        });

        binding.switchVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If the vendor switch is enabled, set all the radio buttons enabled
                // Otherwise disable them
                for (RadioButton button : serviceCats) {
                    button.setEnabled(binding.switchVendor.isChecked());
                }
            }
        });


    }

    // Checks that all the required fields are filled out, returns true if so
    // If any field is not filled out, it shows a toast and returns false
    private boolean verifyFields() {
        if (binding.editTextName.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Name is required", Toast.LENGTH_LONG).show();
            return false;
        }

        if (binding.editTextEmail.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Email is required", Toast.LENGTH_LONG).show();
            return false;
        }

        if (binding.editTextPassword.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Password is required", Toast.LENGTH_LONG).show();
            return false;
        }

        if (binding.editTextPhone.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Phone is required", Toast.LENGTH_LONG).show();
            return false;
        }

        if (binding.editTextAddress.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Address is required", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private ServiceCategory getServiceCategory() {
        for (RadioButton button : this.serviceCats) {
            if (button.isChecked()) {
                switch (button.getText().toString().toUpperCase()) {
                    case "APPLIANCES":
                        return ServiceCategory.Appliances;
                    case "TUTORING":
                        return ServiceCategory.Tutoring;
                    case "PLUMBING":
                        return ServiceCategory.Plumbing;
                    case "PACKAGING AND MOVING":
                        return ServiceCategory.Moving;
                    case "ELECTRICAL":
                        return ServiceCategory.Electrical;
                    default:
                        return ServiceCategory.HomeCleaning;
                }
            }
        }
        return ServiceCategory.HomeCleaning;
    }

    private boolean verifyNoDuplicates() {
        ArrayList<User> users = this.db.getUsers();
        // These are the things that must be unique
        String email = binding.editTextEmail.getText().toString();
        String phone = binding.editTextPhone.getText().toString();

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                Toast.makeText(RegisterActivity.this, "Email must be unique", Toast.LENGTH_LONG).show();
                return false;
            }

            if (user.getPhoneNumber().equals(phone)) {
                Toast.makeText(RegisterActivity.this, "Phone Number must be unique", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        return true;
    }

    private void register() {
        if (!verifyFields()) {
            return;
        }

        if (!verifyNoDuplicates()) {
            return;
        }

        ServiceCategory cat = getServiceCategory();


        // Register a new user no matter what
        String new_id = this.db.addUser(
                binding.editTextName.getText().toString(),
                binding.editTextEmail.getText().toString(),
                binding.editTextPassword.getText().toString(),
                binding.editTextPhone.getText().toString(),
                binding.editTextAddress.getText().toString()
        );
        User newUser = this.db.getUserByID(new_id);

        if (binding.switchVendor.isChecked()) {
            // Set the new user to be a vendor
            newUser.setVendor(true);

            // Add a vendor record
            String vendorID = this.db.addVendor(
                binding.editTextName.getText().toString(),
                binding.editTextPhone.getText().toString(),
                binding.editTextEmail.getText().toString(),
                binding.editTextAddress.getText().toString(),
                cat
            );

            // Attach that vendor record to the newly created user
            Vendor newVendor = this.db.getVendorByID(vendorID);
            newVendor.attachToUser(newUser.getUserId());

        }

        // Log them in
        this.db.setCurrentUser(newUser);

        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}