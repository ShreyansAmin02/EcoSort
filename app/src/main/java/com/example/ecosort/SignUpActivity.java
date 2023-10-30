package com.example.ecosort;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecosort.Utils.SharedPrefManager;

public class SignUpActivity extends AppCompatActivity {
    Button signupButton;
    EditText nameEditText,emailEditText, phoneEditText, passwordEditText, confirmPassword;
    private CheckBox isAdminBox;
    private ToggleButton showPasswordToggle;
    String pattern = ".*\\d.*";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setViewIds();
        signupButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String phoneNumber = phoneEditText.getText().toString();
            String confirm = confirmPassword.getText().toString();
            // Perform login authentication logic here
            if (isValidCredentials(name, email, password, phoneNumber)) {
                // Successful signed up, navigate to next activity
                SharedPrefManager.setLoginState(true);
                Toast.makeText(SignUpActivity.this, "Signup successful",
                        Toast.LENGTH_SHORT).show();
                if (isAdminBox.isChecked()){
                    SharedPrefManager.setAdmin(true);
                }
                else {
                    SharedPrefManager.setAdmin((false));
                }
                Intent intent = new Intent(SignUpActivity.this, BinListActivity.class);
                startActivity(intent);
            } else {
                // Invalid credentials, show error message
                Toast.makeText(SignUpActivity.this, "Invalid Credentials",
                        Toast.LENGTH_SHORT).show();
                if ((name.length() < 3) || (name.matches(pattern)))
                {
                    nameEditText.setError("Invalid name");
                }
                if((password.length() < 5))
                {
                    passwordEditText.setError("Password must be at least 5 characters");
                }
                if(!(isValidEmail(email)))
                {
                    emailEditText.setError("Invalid email");
                }
                if(!(isValidPhoneNumber(phoneNumber)))
                {
                    phoneEditText.setError("Invalid phone number");
                }
                if (password != confirm)
                {
                    confirmPassword.setError("Passwords Do Not Match");
                }

                SharedPrefManager.setLoginState(false);
            }
        });
        showPasswordToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    passwordEditText.setInputType(129); // InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    confirmPassword.setInputType(129); // InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    passwordEditText.setInputType(128); // InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    confirmPassword.setInputType(128); // InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                }
                passwordEditText.setSelection(passwordEditText.getText().length()); // Maintain cursor position
                confirmPassword.setSelection(confirmPassword.getText().length()); // Maintain cursor position
            }
        });
    }
    private void setViewIds() {
        signupButton = findViewById(R.id.signupButton);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.confirmPasswordEditText);
        isAdminBox = findViewById(R.id.checkBox);
        showPasswordToggle = findViewById(R.id.showPasswordToggle);

    }
    private static boolean isValidEmail(String email) {
        CharSequence target = email;
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private static boolean isValidPhoneNumber(String phoneNumber){
        return (phoneNumber.startsWith("+61") && phoneNumber.length() == 12) || (phoneNumber.startsWith("04") && phoneNumber.length()==10);
    }

    private boolean isValidCredentials(String name, String email, String password, String phoneNumber)
    {
        // Perform validation logic here
        // Return true if credentials are valid, false otherwise
        return isValidEmail(email) && (password.length() >=5) && isValidPhoneNumber(phoneNumber) && (name.length() >=3) && (!name.matches(pattern));

    }
}
