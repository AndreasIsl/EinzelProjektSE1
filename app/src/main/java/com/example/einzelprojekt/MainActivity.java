package com.example.einzelprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);
        Button btnCalc = findViewById(R.id.button2);
        TextView output = findViewById(R.id.result);
        EditText input = findViewById(R.id.editTextNumber);
        TextView plainText = findViewById(R.id.textView2);

        plainText.setTextSize(30f);
        output.setTextSize(30f);
        input.setTextSize(30f);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input.getText().toString().length() == 8) {
                    ConnectionThread cT = new ConnectionThread (input.getText().toString());
                    cT.start();
                    try {
                        cT.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    output.setText("" + cT.getResult());
                } else {
                    output.setText("Wrong Input");
                }
            }
        });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input.getText().toString().length() == 8){
                    String inputText = input.getText().toString();
                    String retVal = "";
                    String strToRev ="";

                    //impl :Quersumme	der	Matrikelnummer	bilden	und
                    //anschließend	als	Binärzahl	darstellen
                    int sum = 0;
                    for (int i = 0 ; i < inputText.length(); i++){
                        sum +=( inputText.charAt(i) - 48);
                    }

                    while (sum != 0){
                        strToRev += "" + sum % 2;
                        sum /= 2;
                    }

                    for (int i =  strToRev.length() - 1; i >= 0; i--){
                        retVal += strToRev.charAt(i);
                    }
                    output.setText(retVal);

                } else {
                    output.setText("Wrong Input");
                }
            }
        });
    }
}