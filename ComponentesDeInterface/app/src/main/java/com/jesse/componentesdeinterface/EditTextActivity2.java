package com.jesse.componentesdeinterface;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jesse.componentesdeinterface.databinding.ActivityEditText2Binding;
import com.jesse.componentesdeinterface.databinding.ActivityMainBinding;

public class EditTextActivity2 extends AppCompatActivity {
        private ActivityEditText2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditText2Binding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = binding.buttonLogin;
        EditText text = binding.editTextPassword;

        button.setOnClickListener(v -> {
            Toast.makeText(this, "Senha - " + text.getText(), Toast.LENGTH_SHORT).show();
        });
    }}