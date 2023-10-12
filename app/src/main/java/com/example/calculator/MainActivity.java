package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView sinInverse;
    TextView cosInverse;
    TextView tanInverse;
    TextView stack;
    TextView result;
    TextView equalSign;
    TextView bracketAdder,bracketCloser,allClearButton;
    TextView percentCalculator,equalText;
    TextView subtractButton;
    TextView degText;
    TextView oneText,fourText,sevenText,pointText;
    TextView twoText,fiveText,eightText,zeroText;
    TextView threeText,sixText,nineText;
    TextView sineText,expoText,powerXText,powerYText;
    TextView cosineText,logText,rootText,divideByOneText;
    TextView tanText,factorialText,modText;

    ToggleButton toggleButton;
    RelativeLayout Calcudora;
    RelativeLayout mainLayout;
    ImageView Slider;

    MaterialCardView oneButton,fourButton,sevenButton,pointButton;
    MaterialCardView twoButton,fiveButton,eightButton,zeroButton;
    MaterialCardView threeButton,sixButton,nineButton,backButton;
    MaterialCardView sineButton,piButton,expoButton,powerButton,inverseSine;
    MaterialCardView cosineButton,logButton,rootButton,divideByOneButton,inverseCosine;
    MaterialCardView tanButton,degConverter,factorialButton,modButton,inverseTan;
    MaterialCardView equalButton;
    MaterialCardView FirstCard,SecondCard;

    String stackText;
    ArrayList<String> history;
    ImageView additionButton;
    ImageView divideButton;
    ImageView multiplyButton;
    ImageView backSpace;
    ImageView piImage;
    ImageView historyViewer;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calcudora=findViewById(R.id.calcudora);
        sinInverse=findViewById(R.id.sin_inverse);
        cosInverse=findViewById(R.id.cos_inverse);
        tanInverse=findViewById(R.id.tan_inverse);
        bracketAdder=findViewById(R.id.bracket_adder);
        bracketCloser=findViewById(R.id.bracket_closer);
        allClearButton=findViewById(R.id.all_clear_button);
        stack=findViewById(R.id.stack);
        result=findViewById(R.id.result);
        equalSign=findViewById(R.id.equal_sign);
        equalButton=findViewById(R.id.equal_button);
        toggleButton = findViewById(R.id.toggleButton);
        Slider=findViewById(R.id.slider);
        mainLayout=findViewById(R.id.mainLayout);
        FirstCard=findViewById(R.id.firstCard);
        SecondCard=findViewById(R.id.secondCard);

        oneButton=findViewById(R.id.one_button);
        fourButton=findViewById(R.id.four_button);
        sevenButton=findViewById(R.id.seven_button);
        pointButton=findViewById(R.id.decimal_button);
        oneText=findViewById(R.id.one_text);
        fourText=findViewById(R.id.four_text);
        sevenText=findViewById(R.id.seven_text);
        pointText=findViewById(R.id.decimal_text);


        twoButton=findViewById(R.id.two_button);
        fiveButton=findViewById(R.id.five_button);
        eightButton=findViewById(R.id.eight_tbutton);
        zeroButton=findViewById(R.id.zeo_button);
        twoText=findViewById(R.id.two_text);
        fiveText=findViewById(R.id.five_text);
        eightText=findViewById(R.id.eight_text);
        zeroText=findViewById(R.id.zeo_text);

        threeButton=findViewById(R.id.three_button);
        sixButton=findViewById(R.id.six_button);
        nineButton=findViewById(R.id.nine_button);
        backButton=findViewById(R.id.back_space_button);
        threeText=findViewById(R.id.three_text);
        sixText=findViewById(R.id.six_text);
        nineText=findViewById(R.id.nine_text);
        backSpace=findViewById(R.id.back_space);

        additionButton=findViewById(R.id.add_button);
        subtractButton=findViewById(R.id.subtract_button);
        percentCalculator=findViewById(R.id.percent_modulo);
        divideButton=findViewById(R.id.divide_button);
        multiplyButton=findViewById(R.id.multiply_button);
        equalText=findViewById(R.id.equal_text);

        sineButton=findViewById(R.id.sine_button);
        piButton=findViewById(R.id.pi_button);
        expoButton=findViewById(R.id.exponent_button);
        powerButton=findViewById(R.id.power_button);
        inverseSine=findViewById(R.id.sin_inverse_button);
        sineText=findViewById(R.id.sine_text);
        piImage=findViewById(R.id.pi_image);
        expoText=findViewById(R.id.e_text);
        powerXText=findViewById(R.id.x_text);
        powerYText=findViewById(R.id.power_y_text);



        cosineButton=findViewById(R.id.cosine_button);
        logButton=findViewById(R.id.log_button);
        rootButton=findViewById(R.id.sqrt_button);
        divideByOneButton=findViewById(R.id.divide_by_one_button);
        inverseCosine=findViewById(R.id.cos_inverse_button);
        cosineText=findViewById(R.id.cosine_text);
        logText=findViewById(R.id.log_text);
        rootText=findViewById(R.id.sqrt_text);
        divideByOneText=findViewById(R.id.divide_by_one_text);


        tanButton=findViewById(R.id.tan_button);
        degConverter=findViewById(R.id.deg_converter);
        factorialButton=findViewById(R.id.factorial_button);
        modButton=findViewById(R.id.mod_button);
        inverseTan=findViewById(R.id.tan_inverse_button);
        degText=findViewById(R.id.deg_text);
        tanText=findViewById(R.id.tan_text);
        factorialText=findViewById(R.id.factorial_text);
        modText=findViewById(R.id.abs_text);

        sinInverse.setText("asin");
        cosInverse.setText("acos");
        tanInverse.setText("atan");

        historyViewer=findViewById(R.id.history_viewer);
        history= new ArrayList<>();

        bracketAdder.setOnClickListener(view -> stack.setText(stack.getText().toString()+"("));
        bracketCloser.setOnClickListener(view -> stack.setText(stack.getText().toString()+")"));

        allClearButton.setOnClickListener(view -> {
            stack.setText("");
            result.setText("0");
            equalSign.setVisibility(View.GONE);
        });

        oneButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"1"));

        fourButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"4"));

        sevenButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"7"));

        pointButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"."));

        twoButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"2"));

        fiveButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"5"));

        eightButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"8"));

        zeroButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"0"));

        threeButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"3"));

        sixButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"6"));

        nineButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"9"));


        backButton.setOnClickListener(view -> {
            stackText=stack.getText().toString();
            if (stackText.length() >1 ) {
                stackText = stackText.substring(0, stackText.length() - 1);
                stack.setText(stackText);
            }
            else if (stackText.length() <=1 ) {
                stack.setText("");
            }
        });

        additionButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"+"));

        percentCalculator.setOnClickListener(view -> stack.setText(stack.getText().toString()+"%"));

        subtractButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"-"));

        divideButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"÷"));

        multiplyButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"*"));


        sineButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"sin("));

        piButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"π"));

        expoButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"e"));

        powerButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"^"));

        inverseSine.setOnClickListener(view -> stack.setText(stack.getText().toString()+"asin("));

        cosineButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"cos("));

        logButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"ln("));

        rootButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"√"));

        divideByOneButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"1÷"));

        inverseCosine.setOnClickListener(view -> stack.setText(stack.getText().toString()+"acos("));

        tanButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"tan("));

        degConverter.setOnClickListener(view -> {
            if (degText.getText().toString().equals("deg")){
                degText.setText("rad");
            }else{
                degText.setText("deg");
            }
        });

        factorialButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"!"));

        modButton.setOnClickListener(view -> stack.setText(stack.getText().toString()+"abs("));

        inverseTan.setOnClickListener(view -> stack.setText(stack.getText().toString()+"atan("));

        equalButton.setOnClickListener(view -> {
           BODMASCalculator.setAngleMode(degText.getText().toString());
           String Result= BODMASCalculator.evaluateExpression(stack.getText().toString());

           equalSign.setVisibility(View.VISIBLE);
           if (Result.equals("Error")){
               result.setText(Result);
           }else if (Double.parseDouble(Result)%1==0){
               int myInt;
               myInt=(int) Double.parseDouble(Result);
               result.setText(Integer.toString(myInt));
           }
           else{
               String formattedString = convertDoubleToString(Double.parseDouble(Result));
               result.setText(formattedString);
           }

           history.add(stack.getText().toString()+"="+result.getText().toString());

        });


        historyViewer.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,History.class);
            intent.putExtra("Operations",history);
            intent.putExtra("Mode",toggleButton.isChecked());
            intent.putExtra("Angle mode",degText.getText().toString());


            startActivity(intent);
        });


        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Calcudora.setBackgroundResource(R.drawable.rectangle_273);
                Slider.setImageResource(R.drawable.rectangle_274);
                mainLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                historyViewer.setImageResource(R.drawable.history);
                stack.setTextColor(Color.parseColor("#373737"));
                result.setTextColor(Color.parseColor("#373737"));
                equalSign.setTextColor(Color.parseColor("#373737"));
                sinInverse.setTextColor(Color.parseColor("#373737"));
                cosInverse.setTextColor(Color.parseColor("#373737"));
                tanInverse.setTextColor(Color.parseColor("#373737"));
                inverseSine.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                inverseCosine.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                inverseTan.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                FirstCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                oneButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                fourButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                sevenButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                pointButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                twoButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                fiveButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                eightButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                zeroButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                threeButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                sixButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                nineButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                backButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                SecondCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                equalButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                sineButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cosineButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                tanButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                piButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                expoButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                powerButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                logButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                rootButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                divideByOneButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                degConverter.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                factorialButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                modButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                allClearButton.setTextColor(Color.parseColor("#373737"));
                bracketAdder.setTextColor(Color.parseColor("#373737"));
                bracketCloser.setTextColor(Color.parseColor("#373737"));
                percentCalculator.setTextColor(Color.parseColor("#373737"));
                subtractButton.setTextColor(Color.parseColor("#373737"));
                additionButton.setImageResource(R.drawable.baseline_add_24);
                divideButton.setImageResource(R.drawable.division);
                multiplyButton.setImageResource(R.drawable.asterisk);
                equalText.setTextColor(Color.parseColor("#FBFBFB"));
                oneText.setTextColor(Color.parseColor("#373737"));
                fourText.setTextColor(Color.parseColor("#373737"));
                sevenText.setTextColor(Color.parseColor("#373737"));
                pointText.setTextColor(Color.parseColor("#373737"));
                twoText.setTextColor(Color.parseColor("#373737"));
                fiveText.setTextColor(Color.parseColor("#373737"));
                eightText.setTextColor(Color.parseColor("#373737"));
                zeroText.setTextColor(Color.parseColor("#373737"));
                threeText.setTextColor(Color.parseColor("#373737"));
                sixText.setTextColor(Color.parseColor("#373737"));
                nineText.setTextColor(Color.parseColor("#373737"));
                backSpace.setImageResource(R.drawable.backspace);
                sineText.setTextColor(Color.parseColor("#373737"));
                expoText.setTextColor(Color.parseColor("#373737"));
                powerXText.setTextColor(Color.parseColor("#373737"));
                powerYText.setTextColor(Color.parseColor("#373737"));
                degText.setTextColor(Color.parseColor("#373737"));
                piImage.setImageResource(R.drawable.pi);
                cosineText.setTextColor(Color.parseColor("#373737"));
                logText.setTextColor(Color.parseColor("#373737"));
                rootText.setTextColor(Color.parseColor("#373737"));
                divideByOneText.setTextColor(Color.parseColor("#373737"));
                tanText.setTextColor(Color.parseColor("#373737"));
                factorialText.setTextColor(Color.parseColor("#373737"));
                modText.setTextColor(Color.parseColor("#373737"));

            } else {
                Calcudora.setBackgroundResource(R.drawable.rectangle_275);
                Slider.setImageResource(R.drawable.rectangle_276);
                mainLayout.setBackgroundColor(Color.parseColor("#373737"));
                historyViewer.setImageResource(R.drawable.history_dark);
                stack.setTextColor(Color.parseColor("#FBFBFB"));
                result.setTextColor(Color.parseColor("#FBFBFB"));
                equalSign.setTextColor(Color.parseColor("#FBFBFB"));
                sinInverse.setTextColor(Color.parseColor("#FBFBFB"));
                cosInverse.setTextColor(Color.parseColor("#FBFBFB"));
                tanInverse.setTextColor(Color.parseColor("#FBFBFB"));
                inverseSine.setCardBackgroundColor(Color.parseColor("#0E2437"));
                inverseCosine.setCardBackgroundColor(Color.parseColor("#0E2437"));
                inverseTan.setCardBackgroundColor(Color.parseColor("#0E2437"));
                FirstCard.setCardBackgroundColor(Color.parseColor("#0E2437"));
                oneButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                fourButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                sevenButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                pointButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                twoButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                fiveButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                eightButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                zeroButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                threeButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                sixButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                nineButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                backButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                SecondCard.setCardBackgroundColor(Color.parseColor("#0E2437"));
                equalButton.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                sineButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                cosineButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                tanButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                piButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                logButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                degConverter.setCardBackgroundColor(Color.parseColor("#0E2437"));
                powerButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                rootButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                factorialButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                expoButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                divideByOneButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                modButton.setCardBackgroundColor(Color.parseColor("#0E2437"));
                allClearButton.setTextColor(Color.parseColor("#FBFBFB"));
                bracketAdder.setTextColor(Color.parseColor("#FBFBFB"));
                bracketCloser.setTextColor(Color.parseColor("#FBFBFB"));
                percentCalculator.setTextColor(Color.parseColor("#FBFBFB"));
                additionButton.setImageResource(R.drawable.baseline_add_24_dark);
                divideButton.setImageResource(R.drawable.division_dark);
                subtractButton.setTextColor(Color.parseColor("#FBFBFB"));
                multiplyButton.setImageResource(R.drawable.asterisk_dark);
                equalText.setTextColor(Color.parseColor("#373737"));
                oneText.setTextColor(Color.parseColor("#FBFBFB"));
                fourText.setTextColor(Color.parseColor("#FBFBFB"));
                sevenText.setTextColor(Color.parseColor("#FBFBFB"));
                pointText.setTextColor(Color.parseColor("#FBFBFB"));
                twoText.setTextColor(Color.parseColor("#FBFBFB"));
                fiveText.setTextColor(Color.parseColor("#FBFBFB"));
                eightText.setTextColor(Color.parseColor("#FBFBFB"));
                zeroText.setTextColor(Color.parseColor("#FBFBFB"));
                threeText.setTextColor(Color.parseColor("#FBFBFB"));
                sixText.setTextColor(Color.parseColor("#FBFBFB"));
                nineText.setTextColor(Color.parseColor("#FBFBFB"));
                backSpace.setImageResource(R.drawable.backspace_dark);
                sineText.setTextColor(Color.parseColor("#FBFBFB"));
                expoText.setTextColor(Color.parseColor("#FBFBFB"));
                powerXText.setTextColor(Color.parseColor("#FBFBFB"));
                powerYText.setTextColor(Color.parseColor("#FBFBFB"));
                degText.setTextColor(Color.parseColor("#FBFBFB"));
                piImage.setImageResource(R.drawable.pi_dark);
                cosineText.setTextColor(Color.parseColor("#FBFBFB"));
                logText.setTextColor(Color.parseColor("#FBFBFB"));
                rootText.setTextColor(Color.parseColor("#FBFBFB"));
                divideByOneText.setTextColor(Color.parseColor("#FBFBFB"));
                tanText.setTextColor(Color.parseColor("#FBFBFB"));
                factorialText.setTextColor(Color.parseColor("#FBFBFB"));
                modText.setTextColor(Color.parseColor("#FBFBFB"));

            }
        });
    }

    private String convertDoubleToString(double parseDouble) {
        DecimalFormat decimalFormat = new DecimalFormat("#.######"); // Format with up to 6 decimal places
        return decimalFormat.format(parseDouble);
    }


}