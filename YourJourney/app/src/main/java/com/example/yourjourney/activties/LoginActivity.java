package com.example.yourjourney.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourjourney.DatabaseHelper;
import com.example.yourjourney.R;
import com.example.yourjourney.model.ModelUser;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    EditText log_username, logpass;
    TextView signuplink;
    Toolbar tbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tbLogin = findViewById(R.id.toolbar_login);
        tbLogin.setTitle("LOGIN");
        setSupportActionBar(tbLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_login = findViewById(R.id.btn_login);
        log_username = findViewById(R.id.log_username);
        logpass = findViewById(R.id.log_pass);
        signuplink = findViewById(R.id.signupLink);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean("isLoggedIn", false)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btn_login.setOnClickListener(v -> {
            DatabaseHelper db =  new DatabaseHelper(LoginActivity.this);
            String username = log_username.getText().toString();
            String pass = logpass.getText().toString();
            ModelUser user = db.getLogin(username,pass);
            if (username.isEmpty()) {
                log_username.setError("Field tidak boleh kosong");
            }else if (pass.isEmpty()) {
                logpass.setError ("Field tidak boleh kosong");
            } else if (user != null) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putInt("userId", user.getId());
                editor.apply();

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }else{
                Toast.makeText(LoginActivity.this, "Username atau Password salah.", Toast.LENGTH_SHORT).show();
            }

        });

        signuplink.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
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