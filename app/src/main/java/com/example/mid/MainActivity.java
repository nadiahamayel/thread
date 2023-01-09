package com.example.mid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // hold a reference to the EditText, TextView, and Button
    EditText edtTerms;
    TextView txtResult;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the views
        edtTerms = findViewById(R.id.edtTerms);
        txtResult = findViewById(R.id.txtResult);
        btnStart = findViewById(R.id.btnStart);

        // set an onClickListener for the button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the number of terms from the EditText
                int numTerms = Integer.parseInt(edtTerms.getText().toString());

                // create an object of the SeriesCalculator inner class and wrap it in a Thread object
                Thread thread = new Thread(new SeriesCalculator(numTerms));
                // start the thread
                thread.start();
            }
        });
    }

    // inner class that implements the Runnable interface
    private class SeriesCalculator implements Runnable {
        private int numTerms;

        public SeriesCalculator(int numTerms) {
            this.numTerms = numTerms;
        }

        @Override
        public void run() {
            // calculate the series
            double result = 0;
            for (int i = 1; i <= numTerms; i++) {
                result += 1.0 / i;
            }
            // display the result in the TextView
            final double finalResult = result;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtResult.setText(String.valueOf(finalResult));
                }
            });
        }
    }
}
