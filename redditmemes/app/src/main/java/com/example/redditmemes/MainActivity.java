package com.example.redditmemes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.redditmemes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);
        
        binding.next.setOnDragListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMeme();
            }
        });
        
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareMeme();
            }
        });
        
    }

    private void getMeme() {
    }

    private void shareMeme() {
        
    }
}