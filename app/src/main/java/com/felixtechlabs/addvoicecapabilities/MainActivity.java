package com.felixtechlabs.addvoicecapabilities;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    protected static final int REQUEST_OK = 1;
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchCompat = findViewById(R.id.switch_button);
    }

    public void onClickmic(View view) {

        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(i, REQUEST_OK);
        } catch (Exception e) {
            Toast.makeText(this, "Error initializing speech to text engine.", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OK && resultCode == RESULT_OK) {
            //  ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //   ((TextView) findViewById(R.id.text1)).setText(thingsYouSaid.get(0));
            for (String value : data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)) {
                if (value.equalsIgnoreCase("off")) {
                    // change value for switch to off
                        switchCompat.setChecked(false);
                    break;
                }  if (value.equalsIgnoreCase("on")) {
                    // change value for switch to on
                    switchCompat.setChecked(true);
                    break;
                }
                 if (value.equalsIgnoreCase("open pending request")) {

                    startActivity(new Intent (MainActivity.this,PendingRequest.class));
                    break;
                }  if (value.equalsIgnoreCase("open all request")) {
                    startActivity(new Intent (MainActivity.this,AllRequests.class));

                    break;
                }
                switchCompat.setChecked(true);
            }
        }
    }
}

