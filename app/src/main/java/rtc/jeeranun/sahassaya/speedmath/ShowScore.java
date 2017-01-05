package rtc.jeeranun.sahassaya.speedmath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowScore extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private ImageView plImageView, exitImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        textView = (TextView) findViewById(R.id.textView10);
        plImageView = (ImageView) findViewById(R.id.imageView12);
        exitImageView = (ImageView) findViewById(R.id.imageView13);

        textView.setText("Score = " + getIntent().getIntExtra("Score", 0));

        plImageView.setOnClickListener(this);
        exitImageView.setOnClickListener(this);


    } //Main Method


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageView12:
                startActivity(new Intent(ShowScore.this,PlayActivity.class));
                break;
            case R.id.imageView13:
                finish();
                break;
        }

    }
} //Main Class
