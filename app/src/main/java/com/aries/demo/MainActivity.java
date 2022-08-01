package com.aries.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText keyWordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyWordInput = findViewById(R.id.key_word_input);

        AppCompatButton SearchBtn = findViewById(R.id.go_btn);
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keyWordInput == null) {
                    Toast.makeText(getApplicationContext(),
                            "fatal load edittext", Toast.LENGTH_SHORT).show();
                    return;
                }
                String title = keyWordInput.getText().toString();
                Intent intent = new Intent(MainActivity.this, JobsOverviewActivity.class);
                intent.putExtra(JobsOverviewActivity.EXTRA_TITLE, title);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}