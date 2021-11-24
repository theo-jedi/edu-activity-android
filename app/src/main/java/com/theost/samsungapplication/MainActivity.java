package com.theost.samsungapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    // Обьявляем переменные
    private EditText inputLogin;
    private EditText inputPassword;
    private EditText inputEmail;

    private ProgressBar loadingBar;
    private TextView outputResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем элементы из Layout с помощью findViewById(id)
        inputLogin = findViewById(R.id.inputLogin);
        inputPassword = findViewById(R.id.inputPassword);
        inputEmail = findViewById(R.id.inputEmail);

        loadingBar = findViewById(R.id.loadingBar);
        outputResult = findViewById(R.id.outputResult);

        // Получаем значения из ресурсов
        int color = ContextCompat.getColor(this, R.color.purple_500);
        String text = getResources().getString(R.string.app_name);

        // Отслеживаем нажатие на кнопку "Registration"
        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String login = inputLogin.getText().toString();
                String password = inputPassword.getText().toString();
                String email = inputEmail.getText().toString();

                outputResult.setText("Login: " + login + ", Password: " + password + ", Email: " + email);
                outputResult.setTextColor(color);

                inputLogin.setText(text);

                outputResult.setVisibility(View.VISIBLE);
                loadingBar.setVisibility(View.VISIBLE);
            }
        });

        // Отслеживаем нажатие на кнопку "Start Second Activity"
        findViewById(R.id.startButton).setOnClickListener(view -> startSecondActivity());

        // Отслеживаем нажатие на кнопку "Choose File"
        findViewById(R.id.chooseButton).setOnClickListener(view -> startFilePicker());

        // Создаём и добавляем TextView вручную
        TextView myTv = new TextView(this);
        myTv.setText(R.string.app_name);
        ((LinearLayout) findViewById(R.id.rootLayout)).addView(myTv);
    }

    // Стартуем вторую активность
    private void startSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class); // создаём намерение
        intent.putExtra("key", "наша строка"); // кладём внутрь значение
        startActivity(intent); // стартуем
    }

    // Стартуем выбор файлов
    private void startFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); // создаём намерение
        intent.setType("*/*"); // устанавливаем mimeType (тип контента)
        pickerLauncher.launch(intent); // запускаем активность
    }

    // Создаём лаунчер для нового startActivityForResult
    ActivityResultLauncher<Intent> pickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), // Создаём контракт
            new ActivityResultCallback<ActivityResult>() { // Создаём callback
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Обрабатываем полученные результат
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        System.out.println(result.describeContents()); // Описание контента
                        System.out.println(result.getData().toString()); // Контент
                    }
                }
            }
    );

    // Старый способ получения результата из активности (Deprecated)
    // Вызывается после startActivityForResult(intent, 0)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            System.out.println(data.toString());
        }
    }

}