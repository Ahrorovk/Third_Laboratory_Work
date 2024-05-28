package com.example.thirdlaboratorywork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private ImageView button;
    private boolean isButtonPressed = false;

    private CheckBox checkBox;


    private ToggleButton toggleButton;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        updateButtonImage();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isButtonPressed = !isButtonPressed;
                updateButtonImage();
            }
        });
        checkBox = findViewById(R.id.checkbox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                displayMessage(isChecked);
            }
        });

        toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                displayToggleMessage(isChecked);
            }
        });

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                String text = radioButton.getText().toString();
                displayRadioMessage(text);
            }
        });
        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText);
                editText.setText("");
            }
        });
    }

    private void displayRadioMessage(String text) {
        Toast.makeText(MainActivity.this, "Выбран: " + text, Toast.LENGTH_SHORT).show();
    }

    private void displayMessage(boolean isChecked) {
        if (isChecked) {
            Toast.makeText(MainActivity.this, "Чекбокс выбран", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Чекбокс не выбран", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateButtonImage() {
        if (isButtonPressed) {
            button.setBackgroundResource(R.drawable.smile_pressed);
        } else {
            button.setBackgroundResource(R.drawable.smile_normal);
        }
    }


    private void displayToggleMessage(boolean isChecked) {
        if (isChecked) {
            Toast.makeText(MainActivity.this, "ToggleButton включен", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "ToggleButton выключен", Toast.LENGTH_SHORT).show();
        }
    }
}