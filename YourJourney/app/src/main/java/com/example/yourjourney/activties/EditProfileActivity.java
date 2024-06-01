package com.example.yourjourney.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.yourjourney.DatabaseHelper;
import com.example.yourjourney.R;
import com.example.yourjourney.model.ModelUser;
import com.google.android.material.textfield.TextInputEditText;

public class EditProfileActivity extends AppCompatActivity {
    TextInputEditText edit_name, edit_email, edit_username, edit_password;
    Button btn_update;
    Toolbar tbEdit;
    ModelUser modelUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        tbEdit = findViewById(R.id.toolbar_edit);
        tbEdit.setTitle("EDIT PROFILE");
        setSupportActionBar(tbEdit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        btn_update = findViewById(R.id.btn_update);


        modelUser = (ModelUser) getIntent().getSerializableExtra("user");
        if (modelUser != null) {
        edit_username.setText(modelUser.getUsername());
        edit_name.setText(modelUser.getName());
        edit_email.setText(modelUser.getEmail());
        edit_password.setText(modelUser.getPassword());
        }

        btn_update.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(EditProfileActivity.this);
            String name1 = edit_name.getText().toString();
            String email1 = edit_email.getText().toString();
            String username1= edit_username.getText().toString();
            String pass1 = edit_password.getText().toString();

            if (name1.isEmpty()) {
                edit_name.setError("Please fill this field");
            } else if (email1.isEmpty()) {
                edit_email.setError("Please fill this field");
            } else if (username1.isEmpty()) {
                edit_username.setError("Please fill this field");
            } else if (pass1.isEmpty()) {
                edit_password.setError("Please fill this field");
            } else {
                boolean isUsernameChanged = !username1.equals(modelUser.getUsername());
                boolean isUsernameExists = db.chehckUser(username1);

                if (isUsernameChanged && isUsernameExists) {
                    Toast.makeText(this, "Username Already Exists", Toast.LENGTH_SHORT).show();
                } else {
                    ModelUser user = new ModelUser(name1, email1, username1, pass1);
                    db.editProfile(user, modelUser.getId());
                    db.close();

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("user", user);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
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