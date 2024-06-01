package com.example.yourjourney.fragment;

import static android.app.ProgressDialog.show;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourjourney.DatabaseHelper;
import com.example.yourjourney.R;
import com.example.yourjourney.activties.EditProfileActivity;
import com.example.yourjourney.activties.LoginActivity;
import com.example.yourjourney.activties.MainActivity;
import com.example.yourjourney.activties.RegisterActivity;
import com.example.yourjourney.activties.WelcomeActivity;
import com.example.yourjourney.model.ModelHotel;
import com.example.yourjourney.model.ModelUser;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    TextView btn_logout, btn_edit, btn_delete, profile_name, profile_email,profile_username;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_logout = view.findViewById(R.id.btn_logout);
        btn_edit = view.findViewById(R.id.btn_edit);
        btn_delete =  view.findViewById(R.id.btn_delete);
        profile_name = view.findViewById(R.id.profile_name);
        profile_email = view.findViewById(R.id.profile_email);
        profile_username = view.findViewById(R.id.profile_username);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int userId = preferences.getInt("userId", -1);

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        ModelUser user = dbHelper.getProfile(userId);
        if (user != null) {
            profile_username.setText(user.getUsername());
            profile_name.setText(user.getName());
            profile_email.setText(user.getEmail());
        } else {
            Toast.makeText(getActivity(), "Data pengguna tidak ditemukan", Toast.LENGTH_SHORT).show();
        }


        btn_logout.setOnClickListener((View v) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(getActivity(), WelcomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        btn_edit.setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        btn_delete.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();
            new AlertDialog.Builder(getActivity())
                    .setTitle("Delete Account")
                    .setMessage("Apakah Anda yakin ingin menghapus akun ini?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        DatabaseHelper db = new DatabaseHelper(getActivity());
                        db.deleteProfile(userId);
                        db.close();
                        Toast.makeText(getActivity(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

    }

}
