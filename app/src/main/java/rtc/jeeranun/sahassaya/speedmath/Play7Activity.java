package rtc.jeeranun.sahassaya.speedmath;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Play7Activity extends AppCompatActivity implements View.OnClickListener{
    //Explicit  master
    //sahassaya
    private TextView questionTextView, ch1TextView, ch2TextView,
            ch3TextView, ch4TextView, scoreTextView,timeTextView;
    private Random random;
    private int firstAnInt, secondAnInt, answerAnInt,
            trueChoiceAnInt, scoreAnInt = 0;
    private int timeAnInt = 30; // นี่คือเวลาลูป
    private ImageView rabbit1ImageView,rabbit2ImageView,rabbit3ImageView,rabbit4ImageView,rabbit5ImageView;
    private ImageView[] imageViews;
    private ImageView heart1ImageView,heart2ImageView, heart3ImageView;
    private  int heardAnInt = 0;
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play7);
        //Setup
        random = new Random();

        // Bind Widger
        questionTextView = (TextView) findViewById(R.id.textView49);
        ch1TextView = (TextView) findViewById(R.id.textView45);
        ch2TextView = (TextView) findViewById(R.id.textView46);
        ch3TextView = (TextView) findViewById(R.id.textView47);
        ch4TextView = (TextView) findViewById(R.id.textView48);
        scoreTextView = (TextView) findViewById(R.id.textView50);
        timeTextView = (TextView) findViewById(R.id.textView51);
        heart1ImageView = (ImageView) findViewById(R.id.imageView50);
        heart2ImageView = (ImageView) findViewById(R.id.imageView51);
        heart3ImageView = (ImageView) findViewById(R.id.imageView52);
        rabbit1ImageView = (ImageView) findViewById(R.id.imageView53);
        rabbit2ImageView = (ImageView) findViewById(R.id.imageView54);
        rabbit3ImageView = (ImageView) findViewById(R.id.imageView55);
        rabbit4ImageView = (ImageView) findViewById(R.id.imageView56);
        rabbit5ImageView = (ImageView) findViewById(R.id.imageView57);


        imageViews = new ImageView[]{rabbit1ImageView,rabbit2ImageView,rabbit3ImageView,
                rabbit4ImageView,rabbit5ImageView};


        //Click Controller
        ch1TextView.setOnClickListener(this);
        ch2TextView.setOnClickListener(this);
        ch3TextView.setOnClickListener(this);
        ch4TextView.setOnClickListener(this);

        //Play Controller
        playController();

        countTime();

    }   // Main Method

    private void countTime() {
        if (timeAnInt == 0) {
            timeAnInt = 30;
            deleteHeard();
        } // if

        timeAnInt -= 1;
        timeTextView.setText(Integer.toString(timeAnInt) + "วินาที");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                countTime();
            }
        },1000);

    }      // countTime

    private void playController() {

        firstAnInt = random.nextInt(100);
        secondAnInt = random.nextInt(100);
        answerAnInt = firstAnInt + secondAnInt;
        trueChoiceAnInt = random.nextInt(4) + 1;
        Log.d("4janV1", "ข้อที่ถูก ==> " + trueChoiceAnInt);
        timeAnInt = 30;
        //Change Question
        questionTextView.setText(Integer.toString(firstAnInt) + " + " +
                Integer.toString(secondAnInt) + " = ?");

        //Change Choice
        TextView[] textViews = new TextView[]{ch1TextView, ch2TextView, ch3TextView, ch4TextView};
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText(Integer.toString(random.nextInt(100)));
        }

        switch (trueChoiceAnInt) {
            case 1:
                ch1TextView.setText(Integer.toString(answerAnInt));
                break;
            case 2:
                ch2TextView.setText(Integer.toString(answerAnInt));
                break;
            case 3:
                ch3TextView.setText(Integer.toString(answerAnInt));
                break;
            case 4:
                ch4TextView.setText(Integer.toString(answerAnInt));
                break;
            default:
                ch1TextView.setText(Integer.toString(answerAnInt));
                break;
        }   // switch




    }// playController

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.textView45:
                checkAnswer(Integer.parseInt(ch1TextView.getText().toString()));
                break;
            case R.id.textView46:
                checkAnswer(Integer.parseInt(ch2TextView.getText().toString()));
                break;
            case R.id.textView47:
                checkAnswer(Integer.parseInt(ch3TextView.getText().toString()));
                break;
            case R.id.textView48:
                checkAnswer(Integer.parseInt(ch4TextView.getText().toString()));
                break;
        }   // switch

        playController();

    }   // onClick



    private void checkAnswer(int intChoice) {
        Log.d("3decV1", "You Choose Answer ==> " + intChoice);


        if (intChoice == answerAnInt) {
            scoreAnInt += 1;
        } else {
            deleteHeard();
        }

        //Change Score
        Log.d("3decV1", "Score ==> " + scoreTextView);
        scoreTextView.setText("Score = " + Integer.toString(scoreAnInt));


        for (int i=0;i<imageViews.length;i++){
            imageViews [i].setVisibility(View.INVISIBLE);

        } //for
        if (scoreAnInt <4) {
            imageViews[0].setVisibility(View.VISIBLE);
        } else if (scoreAnInt <8) {
            imageViews[1].setVisibility(View.VISIBLE);
        } else if (scoreAnInt <12) {
            imageViews[2].setVisibility(View.VISIBLE);
        } else if (scoreAnInt <16) {
            imageViews[3].setVisibility(View.VISIBLE);
        } else if (scoreAnInt <20) {
            imageViews[4].setVisibility(View.VISIBLE);

            MyAlert myAlert = new MyAlert(Play7Activity.this, "ผ่านด่านที่ 7", "ยินดีด้วยผ่านด่านที่ 7 แล้ว", Play8Activity.class);

            myAlert.myDialog();

        }

    }  //checkanser



    private void deleteHeard() {


        ImageView[] imageViews = new ImageView[]
                {heart3ImageView,heart2ImageView,heart1ImageView};


        if (heardAnInt < imageViews.length) {
            imageViews[heardAnInt].setVisibility(View.INVISIBLE);
            heardAnInt += 1;
        } else {
            Intent intent = new Intent(Play7Activity.this, ShowScore.class);
            intent.putExtra("Score", scoreAnInt);
            startActivity(intent);
            finish();
        }
    }

}   // Main Class