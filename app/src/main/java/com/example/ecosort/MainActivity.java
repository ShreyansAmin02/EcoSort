package com.example.ecosort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecosort.Utils.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    TextView signUpPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewIds();

        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Perform login authentication logic here
            if (isValidCredentials(email, password)) {
                // Successful login, navigate to next activity
                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                SharedPrefManager.setLoginState(true);
                if (email.equals("ad@ad.com")){
                    SharedPrefManager.setAdmin(true);
                }else {
                    SharedPrefManager.setAdmin(false);
                }
                Intent intent = new Intent(MainActivity.this, BinListActivity.class);
                startActivity(intent);

            } else {
                // Invalid credentials, show error message
                Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                emailEditText.setError("Invalid email");
                passwordEditText.setError("Invalid password");            }
        });
        signUpPage.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void setViewIds() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpPage = findViewById(R.id.signUp);

    }

    private boolean isValidCredentials(String email, String password) {
        // Perform validation logic here
        // Return true if credentials are valid, false otherwise
        return (email.equals("ex@ex.com") && password.equals("ex123")) || (email.equals("ad@ad.com") && password.equals("ad123"));
    }
}