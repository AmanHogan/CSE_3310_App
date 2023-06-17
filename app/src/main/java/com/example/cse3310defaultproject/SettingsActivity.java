package com.example.cse3310defaultproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.User;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {

    DataRepository db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.db = DataRepository.getInstance();
        User currentUser = db.getCurrentUser();

        // Get Text ID of text boxes
        TextView emailText = (TextView)findViewById(R.id.disp_email);
        TextView cardNumber = (TextView)findViewById(R.id.disp_card);
        TextView addressText = (TextView)findViewById(R.id.disp_address);
        TextView idText = (TextView)findViewById(R.id.disp_id);
        TextView topName = (TextView)findViewById(R.id.settings_name);
        TextView topEmail = (TextView)findViewById(R.id.settings_email);

        // Display the User's name on top of Activity
        topName.setText(currentUser.getName());

        // If User does not have an email, display that there is no email on file
        if(currentUser.getEmail() == null || currentUser.getEmail().isEmpty())
        {
            emailText.setText("Current Email: Not on File");
        }

        // If user has email on file, display email
        else
        {
            emailText.setText("Email address: "+ currentUser.getEmail());
            topEmail.setText(currentUser.getEmail());
        }

        // If there is no card number on file, display that there is no card on file
        if(currentUser.getCardNumber() == null || currentUser.getCardNumber().isEmpty())
        {
            cardNumber.setText("Card Number: Not on file");
        }

        // If there is a card number on file, display the card number
        else
        {
            cardNumber.setText("Card Number: ****" + currentUser.getCardNumber().substring(4));
        }

        // If there is no address on file, display that there is no address
        if(currentUser.getAddress() == null || currentUser.getAddress().isEmpty())
        {
            addressText.setText("Home Address: Not on File");
        }

        // If there is an address, display this address
        else
        {
            addressText.setText("Home Address: " + currentUser.getAddress());
        }

        if(currentUser.getUserId() == null || currentUser.getUserId().isEmpty())
        {
            idText.setText("ID: Error in displaying ID");
        }

        else
        {
            idText.setText("ID: " + currentUser.getUserId());
        }

    }
}