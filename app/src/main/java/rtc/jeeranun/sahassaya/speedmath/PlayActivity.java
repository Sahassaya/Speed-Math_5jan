package rtc.jeeranun.sahassaya.speedmath;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {


    //Explicit  master
    //sahassaya
    private TextView questionTextView, ch1TextView, ch2TextView,
            ch3TextView, ch4TextView, scoreTextView,timeTextView;
    private Random random;
    private int firstAnInt, secondAnInt, answerAnInt,
            trueChoiceAnInt, scoreAnInt = 0;
    private int timeAnInt = 30; // นี่คือเวลาลูป
    private ImageView rabbit1ImageView,rabbit2ImageView,rabbit3ImageView,rabbit4ImageView,rabbit5ImageView,rabbit6ImageView, rabbit7ImageView;
    private ImageView[] imageViews;
    private ImageView heart1ImageView,heart2ImageView, heart3ImageView;
    private  int heardAnInt = 0;
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        //Setup
        random = new Random();

        // Bind Widger
        questionTextView = (TextView) findViewById(R.id.textView7);
        ch1TextView = (TextView) findViewById(R.id.textView3);
        ch2TextView = (TextView) findViewById(R.id.textView4);
        ch3TextView = (TextView) findViewById(R.id.textView5);
        ch4TextView = (TextView) findViewById(R.id.textView6);
        scoreTextView = (TextView) findViewById(R.id.textView8);
        timeTextView = (TextView) findViewById(R.id.textView9);
        heart1ImageView = (ImageView) findViewById(R.id.imageView);
        heart2ImageView = (ImageView) findViewById(R.id.imageView3);
        heart3ImageView = (ImageView) findViewById(R.id.imageView4);
        rabbit1ImageView = (ImageView) findViewById(R.id.imageView5);
        rabbit2ImageView = (ImageView) findViewById(R.id.imageView6);
        rabbit3ImageView = (ImageView) findViewById(R.id.imageView7);
        rabbit4ImageView = (ImageView) findViewById(R.id.imageView8);
        rabbit5ImageView = (ImageView) findViewById(R.id.imageView9);
        rabbit6ImageView = (ImageView) findViewById(R.id.imageView10);
        rabbit7ImageView = (ImageView) findViewById(R.id.imageView11);

        imageViews = new ImageView[]{rabbit1ImageView,rabbit2ImageView,rabbit3ImageView,
                rabbit4ImageView,rabbit5ImageView,rabbit6ImageView,rabbit7ImageView};


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
                if (aBoolean) {
                    countTime();
                }
            }
        },1000);

    }      // countTime

    private void playController() {

        firstAnInt = random.nextInt(11);
        secondAnInt = random.nextInt(10);
        answerAnInt = firstAnInt + secondAnInt; // นี่คือคำตอบที่ถูกต้อง
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

        //สร้าง ช้อย ใกล้เคียง
        textViews[0].setText(Integer.toString(answerAnInt + 10));
        textViews[1].setText(Integer.toString(answerAnInt - 10));
        textViews[2].setText(Integer.toString(answerAnInt - 2));
        textViews[3].setText(Integer.toString(answerAnInt + 2));



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
            case R.id.textView3:
                checkAnswer(Integer.parseInt(ch1TextView.getText().toString()));
                break;
            case R.id.textView4:
                checkAnswer(Integer.parseInt(ch2TextView.getText().toString()));
                break;
            case R.id.textView5:
                checkAnswer(Integer.parseInt(ch3TextView.getText().toString()));
                break;
            case R.id.textView6:
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
        if (scoreAnInt <3) {
            imageViews[0].setVisibility(View.VISIBLE);
        } else if (scoreAnInt < 6) {
            imageViews[1].setVisibility(View.VISIBLE);
        } else if (scoreAnInt <9) {
            imageViews[2].setVisibility(View.VISIBLE);
        } else if (scoreAnInt <12) {
            imageViews[3].setVisibility(View.VISIBLE);
        } else if (scoreAnInt <15) {
            imageViews[4].setVisibility(View.VISIBLE);
        } else if (scoreAnInt < 18) {
            imageViews[5].setVisibility(View.VISIBLE);
        } else {
            imageViews[6].setVisibility(View.VISIBLE);

            //เฉพาะกิด

            MyAlert myAlert = new MyAlert(PlayActivity.this,
                    "ผ่านด่านที่ 1", "ยินดีด้วยผ่านด่านที่ 1 แล้ว", Play2Activity.class);
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
            Intent intent = new Intent(PlayActivity.this, ShowScore.class);
            intent.putExtra("Score", scoreAnInt);
            startActivity(intent);
            finish();
        }
    }

}   // Main Class
