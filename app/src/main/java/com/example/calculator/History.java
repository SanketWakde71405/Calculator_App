package com.example.calculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class History extends AppCompatActivity implements ItemClickListener {

    RecyclerView historyRecycler;
    RelativeLayout mainLayout2;
    RelativeLayout topView,buttonView,recyclerViewContainer;
    ArrayList<String> Data;
    HistoryAdapter upAdapter;
    ImageView go_back;
    ImageView clearAllButton;
    ImageView addCardImage,backCardImage;
    String inputText;
    String angleMode;

    MaterialCardView addCard,multiplyCard,divideCard,subtractCard,equalCard,backCard;

    TextView multiplyCardText,divideCardText,subtractCardText,equalCardText;
    TextView nullContainer;
    TextView titleText;
    TextView resultText;
    TextInputEditText inputEditText;
    boolean Mode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyRecycler=findViewById(R.id.recycler_list);
        go_back=findViewById(R.id.close_activity);
        inputEditText=findViewById(R.id.historyText);
        resultText=findViewById(R.id.result_text);
        mainLayout2=findViewById(R.id.mainLayout2);
        titleText=findViewById(R.id.title_layout);
        clearAllButton=findViewById(R.id.clearAll);
        topView=findViewById(R.id.top_view);
        buttonView=findViewById(R.id.button_view);
        recyclerViewContainer=findViewById(R.id.recyclerViewContainer);
        nullContainer=findViewById(R.id.null_container);

        addCard=findViewById(R.id.add_card);
        multiplyCard=findViewById(R.id.multiply_card);
        subtractCard=findViewById(R.id.negative_card);
        divideCard=findViewById(R.id.divide_card);
        equalCard=findViewById(R.id.equal_card);
        backCard=findViewById(R.id.back_space_card);
        addCardImage=findViewById(R.id.add_card_image);
        multiplyCardText=findViewById(R.id.multiply_card_text);
        subtractCardText=findViewById(R.id.negative_card_text);
        divideCardText=findViewById(R.id.divide_card_text);
        equalCardText=findViewById(R.id.equal_card_text);
        backCardImage=findViewById(R.id.back_space_card_text);

        historyRecycler.setHasFixedSize(true);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        Data= new ArrayList<>();
        Data= (ArrayList<String>) getIntent().getSerializableExtra("Operations");
        Mode=getIntent().getBooleanExtra("Mode",true);
        angleMode=getIntent().getStringExtra("Angle mode");

        upAdapter= new HistoryAdapter(this,Data,this);
        historyRecycler.setAdapter(upAdapter);
        upAdapter.setThemeMode(Mode);


        if (Data.isEmpty()){
            topView.setVisibility(View.INVISIBLE);
            buttonView.setVisibility(View.INVISIBLE);
            recyclerViewContainer.setVisibility(View.INVISIBLE);
            nullContainer.setVisibility(View.VISIBLE);
        }else{
            topView.setVisibility(View.VISIBLE);
            buttonView.setVisibility(View.VISIBLE);
            recyclerViewContainer.setVisibility(View.VISIBLE);
            nullContainer.setVisibility(View.INVISIBLE);
        }


        if (Mode){
            go_back.setImageResource(R.drawable.baseline_close_24);
            mainLayout2.setBackgroundColor(Color.parseColor("#F9F9F9"));
            titleText.setTextColor(Color.parseColor("#000000"));
            clearAllButton.setImageResource(R.drawable.erase);
            inputEditText.setTextColor(Color.parseColor("#000000"));
            resultText.setTextColor(Color.parseColor("#000000"));
            nullContainer.setTextColor(Color.parseColor("#000000"));
            addCard.setCardBackgroundColor(Color.parseColor("#CBDDE7"));
            multiplyCard.setCardBackgroundColor(Color.parseColor("#CBDDE7"));
            subtractCard.setCardBackgroundColor(Color.parseColor("#CBDDE7"));
            divideCard.setCardBackgroundColor(Color.parseColor("#CBDDE7"));
            equalCard.setCardBackgroundColor(Color.parseColor("#050505"));
            backCard.setCardBackgroundColor(Color.parseColor("#CBDDE7"));
            backCardImage.setImageResource(R.drawable.backspace);
            addCardImage.setImageResource(R.drawable.baseline_add_24);
            multiplyCardText.setTextColor(Color.parseColor("#373737"));
            divideCardText.setTextColor(Color.parseColor("#373737"));
            subtractCardText.setTextColor(Color.parseColor("#373737"));
            equalCardText.setTextColor(Color.parseColor("#FBFBFB"));
        }else{
            go_back.setImageResource(R.drawable.baseline_close_24_dark);
            mainLayout2.setBackgroundColor(Color.parseColor("#373737"));
            titleText.setTextColor(Color.parseColor("#FFFFFF"));
            clearAllButton.setImageResource(R.drawable.erase_dark);
            inputEditText.setTextColor(Color.parseColor("#FFFFFF"));
            resultText.setTextColor(Color.parseColor("#FFFFFF"));
            nullContainer.setTextColor(Color.parseColor("#FFFFFF"));
            addCard.setCardBackgroundColor(Color.parseColor("#356885"));
            multiplyCard.setCardBackgroundColor(Color.parseColor("#356885"));
            divideCard.setCardBackgroundColor(Color.parseColor("#356885"));
            subtractCard.setCardBackgroundColor(Color.parseColor("#356885"));
            equalCard.setCardBackgroundColor(Color.parseColor("#FEFEFE"));
            backCard.setCardBackgroundColor(Color.parseColor("#356885"));
            backCardImage.setImageResource(R.drawable.backspace_dark);
            addCardImage.setImageResource(R.drawable.baseline_add_24_dark);
            multiplyCardText.setTextColor(Color.parseColor("#FBFBFB"));
            subtractCardText.setTextColor(Color.parseColor("#FBFBFB"));
            divideCardText.setTextColor(Color.parseColor("#FBFBFB"));
            equalCardText.setTextColor(Color.parseColor("#373737"));
        }

        addCard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                inputEditText.setText(inputEditText.getText().toString()+"+");
            }
        });

        subtractCard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                inputEditText.setText(inputEditText.getText().toString()+"-");
            }
        });

        multiplyCard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                inputEditText.setText(inputEditText.getText().toString()+"*");
            }
        });

        divideCard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                inputEditText.setText(inputEditText.getText().toString()+"÷");
            }
        });

        backCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText=inputEditText.getText().toString();
                if (inputText.length() >1 ) {
                    inputText = inputText.substring(0, inputText.length() - 1);
                    inputEditText.setText(inputText);
                }
                else if (inputText.length() <=1 ) {
                    inputEditText.setText("");
                }
            }
        });

        equalCard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                BODMASCalculator.setAngleMode(angleMode);
                String Result= BODMASCalculator.evaluateExpression(inputEditText.getText().toString());
                if (Result.equals("Error")){
                    resultText.setText(Result);
                }else if (Double.parseDouble(Result)%1==0){
                    int myInt;
                    myInt=(int) Double.parseDouble(Result);
                    resultText.setText(Integer.toString(myInt));
                }
                else{
                    String formattedString = convertDoubleToString(Double.parseDouble(Result));
                    resultText.setText(formattedString);
                }


            }
        });

        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upAdapter.historyList.clear();
                topView.setVisibility(View.INVISIBLE);
                buttonView.setVisibility(View.INVISIBLE);
                recyclerViewContainer.setVisibility(View.INVISIBLE);
                nullContainer.setVisibility(View.VISIBLE);

            }
        });
        go_back.setOnClickListener(view -> finish());


    }

    private String convertDoubleToString(double parseDouble) {
        DecimalFormat decimalFormat = new DecimalFormat("#.######"); // Format with up to 6 decimal places
        return decimalFormat.format(parseDouble);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemClick(String text) {
        inputEditText.setText(inputEditText.getText().toString() + text);
    }
}