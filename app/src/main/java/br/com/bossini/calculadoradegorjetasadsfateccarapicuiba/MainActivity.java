package br.com.bossini.calculadoradegorjetasadsfateccarapicuiba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat =
            NumberFormat.getPercentInstance();
    private double billAmount;
    private double percent = 0.15;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView, totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountTextView = (TextView)
                findViewById(R.id.amountTextView);
        percentTextView = (TextView)
                findViewById(R.id.percentTextView);
        tipTextView = (TextView)
                findViewById(R.id.tipTextView);
        totalTextView = (TextView)
                findViewById(R.id.totalTextView);
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));
        EditText amountEditText =
                (EditText) findViewById(R.id.amountEditText);
        SeekBar percentSeekBar =
                (SeekBar) findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
    }

    private SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    percent = progress / 100.0;
                    double tip = billAmount * percent;
                    double total = billAmount + tip;
                    tipTextView.setText(currencyFormat.format(tip));
                    totalTextView.setText(currencyFormat.format(total));
                    percentTextView.setText(percentFormat.format(percent));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
    private TextWatcher amountEditTextWatcher =
            new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    try{
                        billAmount = Double.parseDouble(charSequence.toString()) / 100;
                        double tip = billAmount * percent;
                        double total = billAmount + tip;
                        tipTextView.setText(currencyFormat.format(tip));
                        totalTextView.setText(currencyFormat.format(total));
                        amountTextView.setText(currencyFormat.format(billAmount));
                    }
                    catch (NumberFormatException e){
                        billAmount = 0;
                        tipTextView.setText(currencyFormat.format(billAmount));
                        amountTextView.setText(currencyFormat.format(billAmount));
                        totalTextView.setText(currencyFormat.format(billAmount));
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };


}
