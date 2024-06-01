package com.example.yourjourney.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourjourney.DatabaseHelper;
import com.example.yourjourney.R;
import com.example.yourjourney.model.ModelUser;

public class RegisterActivity extends AppCompatActivity {
    Button btn_reg;
    EditText reg_username, regpass, reg_name, reg_email;
    TextView loginLink;
    Toolbar tbReg;
    ModelUser modelUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tbReg = findViewById(R.id.toolbar_reg);
        tbReg.setTitle("SIGNUP");
        setSupportActionBar(tbReg);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_reg = findViewById(R.id.btn_signup);
        reg_name = findViewById(R.id.reg_name);
        reg_email = findViewById(R.id.reg_email);
        reg_username = findViewById(R.id.reg_username);
        regpass = findViewById(R.id.reg_pass);
        loginLink = findViewById(R.id.loginLink);

        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        btn_reg.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(RegisterActivity.this);
            String name = reg_name.getText().toString();
            String email = reg_email.getText().toString();
            String username = reg_username.getText().toString();
            String pass = regpass.getText().toString();

            if (name.isEmpty()) {
                reg_name.setError("Please fill this field");
            }else if (email.isEmpty()) {
                reg_email.setError("Please fill this field");
            }else if (username.isEmpty()) {
                reg_username.setError("Please fill this field");
            }else if (pass.isEmpty()) {
                regpass.setError("Please fill this field");
            }else if (db.chehckUser(username)) {
                Toast.makeText(this, "Username Already Exists", Toast.LENGTH_SHORT).show();
            } else {
                modelUser = new ModelUser(name, email, username, pass);
                db.register(modelUser);
                db.close();

                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Data Registered Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}