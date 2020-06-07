package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int attemptsRemaining = 5;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("Attempts Remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });

    }

    public void validate (String username, String password) {

        if (username.equals("abcd") && password.equals("1234"))
        {
            Intent success = new Intent(MainActivity.this, SuccessfulLoginActivity.class); //move from one activity to another
            startActivity(success); //normal screen
        }
        else
        {
            attemptsRemaining--;

            Info.setText("Attempts Remaining: " + attemptsRemaining);

            if (attemptsRemaining == 0)
            {
                Login.setEnabled(false);
                Intent fail = new Intent(MainActivity.this, FailedLoginActivity.class);
                startActivity(fail);
            }
        }


    }

}
