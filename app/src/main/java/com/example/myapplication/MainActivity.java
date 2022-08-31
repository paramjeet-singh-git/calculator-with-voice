package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText display;
    private ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousCalculation=findViewById(R.id.previousCalculationView);
        display=findViewById(R.id.displayEditText);
        imageButton=findViewById(R.id.record);
        display.setShowSoftInputOnFocus(false);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent record=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                record.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                record.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                record.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS,"5");
               startActivityForResult(record, Integer.parseInt("1001"));
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1001){
            ArrayList<String> list=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            display.setText(list.get(0));
        }

    }

    private void updateText(String strToAdd){
        String oldStr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftStr=oldStr.substring(0,cursorPos);
        String rightStr=oldStr.substring(cursorPos);
        display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
        display.setSelection(cursorPos + strToAdd.length());

    }

    public void zeroBtnPush(View view){
        updateText(getResources().getString(R.string.zeroText));

    }
    public void oneBtnPush(View view){
        updateText(getResources().getString(R.string.oneText));

    }
    public void twoBtnPush(View view){
        updateText(getResources().getString(R.string.twoText));

    }
    public void threeBtnPush(View view){
        updateText(getResources().getString(R.string.threeText));

    }
    public void fourBtnPush(View view){
        updateText(getResources().getString(R.string.fourText));

    }
    public void fiveBtnPush(View view){
        updateText(getResources().getString(R.string.fiveText));

    }
    public void sixBtnPush(View view){
        updateText(getResources().getString(R.string.sixText));

    }
    public void sevenBtnPush(View view){
        updateText(getResources().getString(R.string.sevenText));

    }
    public void eightBtnPush(View view){
        updateText(getResources().getString(R.string.eightText));

    }
    public void nineBtnPush(View view){
        updateText(getResources().getString(R.string.nineText));

    }
    public void multiplyBtnPush(View view){
        updateText(getResources().getString(R.string.multiplyText));

    }
    public void divideBtnPush(View view){
        updateText(getResources().getString(R.string.divideText));
    }

    public void subtractBtnPush(View view){
        updateText(getResources().getString(R.string.subtractText));
    }

    public void addBtnPush(View view){
        updateText(getResources().getString(R.string.addText));
    }
    public void clearBtnPush(View view){
        display.setText("");
        previousCalculation.setText("");
    }

    public void decimalBtnPush(View view){
        updateText(getResources().getString(R.string.decimalText));
    }

    public void paropenBtnPush(View view){
        updateText(getResources().getString(R.string.parenthesesOpenText));
    }

    public void parcloseBtnPush(View view){
        updateText(getResources().getString(R.string.parenthesesCloseText));
    }

    public void equalBtnPush(View view){
        String userExp=display.getText().toString();
        previousCalculation.setText(userExp);
        userExp=userExp.replaceAll(getResources().getString(R.string.divideText),"/");
        userExp=userExp.replaceAll(getResources().getString(R.string.multiplyText),"*");

        Expression exp = new Expression(userExp);
        String result=String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());


    }
    public void backspaceBtnPush(View view){
        int cursorpos = display.getSelectionStart();
        int textLen=display.getText().length();
        if(cursorpos!=0 && textLen!=0){
            SpannableStringBuilder selection =(SpannableStringBuilder) display.getText();
            selection.replace(cursorpos-1,cursorpos,"");
            display.setText(selection);
            display.setSelection(cursorpos-1);
        }

    }
    public void trigSinBtnPush(View view){
        updateText("sin(");

    }
    public void trigCosBtnPush(View view){
        updateText("cos(");

    }

    public void trigTanBtnPush(View view){
        updateText("tan(");

    }

    public void trigArcSinBtnPush(View view){
        updateText("arcsin(");

    }

    public void trigArcCosBtnPush(View view){
        updateText("arccos(");

    }

    public void trigArcTanBtnPush(View view){
        updateText("arctan(");

    }

    public void LogBtnPush(View view){
        updateText("log10(");

    }
    public void naturalLogBtnPush(View view){
        updateText("ln(");

    }

    public void piBtnPush(View view){
        updateText("pi");

    }

    public void etextBtnPush(View view){
        updateText("exp(");

    }
    public void sqrtBtnPush(View view){
        updateText("sqrt(");


    }

    public void primeBtnPush(View view){
        updateText("ispr(");

    }

    public void xSquareBtnPush(View view){
        updateText("^(2)");


    }

    public void xPowerYBtnPush(View view){

        updateText("^(");

    }





}