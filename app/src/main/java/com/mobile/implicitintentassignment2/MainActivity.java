package com.mobile.implicitintentassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText mETRecipientAddress, mETUserAddress, mETMessage;
    private Button mBTNSendMailNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewsByID();

        // checking button click and getting values of edit text to send mail
        mBTNSendMailNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipientMail, userAddress, message;
                recipientMail = mETRecipientAddress.getText().toString().trim();
                userAddress = mETUserAddress.getText().toString().trim();
                message = mETMessage.getText().toString().trim();

                if(recipientMail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(recipientMail).matches()) {
                    showToast(R.string.enter_valid_email_for_recipient);
                }else if(userAddress.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(recipientMail).matches()) {
                    showToast(R.string.enter_your_valid_email);
                }else if(message.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(recipientMail).matches()) {
                    showToast(R.string.enter_message);
                }else{
                    sendMail(recipientMail, userAddress, message);
                }
            }
        });

    }

    private void sendMail(String recipientMail, String userAddress, String message) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        String[] recipients={recipientMail};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Message sending from Assignment 2 app");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.putExtra(Intent.EXTRA_CC,userAddress);
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    private void showToast(int message) {
        Toast.makeText(getApplicationContext(),  message, Toast.LENGTH_SHORT).show();
    }

    private void initializeViewsByID() {
        mETRecipientAddress = findViewById(R.id.et_enter_address_of_recipient);
        mETUserAddress = findViewById(R.id.et_your_email_address);
        mETMessage = findViewById(R.id.et_message);
        mBTNSendMailNow = findViewById(R.id.btn_send_mail_now);
    }
}