package com.example.timeforyourself;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class Morning extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private MediaPlayer player = new MediaPlayer();
    private TextView mTextViewCountDown;
    private CountDownTimer timer;
    private ImageButton TimerBtn;
    private ImageButton PlayPause;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning);
        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.timer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        player = MediaPlayer.create(this, R.raw.morn);
        player.start();
        player.setLooping(true);
        PlayPause = this.findViewById(R.id.btnstart);
        TimerBtn = findViewById(R.id.timer);
        mTextViewCountDown = findViewById(R.id.textView);
        mTextViewCountDown.setVisibility(View.INVISIBLE);

        TimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setSelection(0); // onItemSelected chose case 0
                spinner.performClick();
                stopCountdown();
            }
        });

        PlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {

                if (player.isPlaying()) {
                    PlayPause.setImageResource(R.drawable.play); // in java code :srcCompat="@drawable/"
                    player.pause();
                } else {
                    player.start();
                    PlayPause.setImageResource(R.drawable.pause);

                }
                PlayPause.setBackgroundResource(R.drawable.spinner_style);
            }
        }); // One button can make two actions with switch images
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
        player.release();
    } // stop music when you pressed the back button

        private void stopCountdown() {
            if (timer != null) {
                timer.cancel();
            }
    }
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (position) {

                    case 0:
                        mTextViewCountDown.setVisibility(View.INVISIBLE);
                        PlayPause.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(3000, 100) {
                            @SuppressLint("DefaultLocale")
                            public void onTick(long millisUntilFinished) {
                                mTextViewCountDown.setText(String.format("%02d:%02d",
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {
                                PlayPause.setVisibility(View.VISIBLE);
                                mTextViewCountDown.setVisibility(View.INVISIBLE);
                                PlayPause.setImageResource(R.drawable.play);
                                player.pause();
                            }
                        }.start();
                        break;
                    case 2:
                            PlayPause.setVisibility(View.INVISIBLE);
                            mTextViewCountDown.setVisibility(View.VISIBLE);
                            timer = new CountDownTimer(300000, 100) {
                                @SuppressLint("DefaultLocale")
                                public void onTick(long millisUntilFinished) {
                                    mTextViewCountDown.setText(String.format("%02d:%02d",
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                }

                                public void onFinish() {
                                    PlayPause.setVisibility(View.VISIBLE);
                                    mTextViewCountDown.setVisibility(View.INVISIBLE);
                                    PlayPause.setImageResource(R.drawable.play);
                                    player.pause();
                                }
                            }.start();

                        break;
                    case 3:
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(600000, 100) {
                            @SuppressLint("DefaultLocale")
                            public void onTick(long millisUntilFinished) {
                                mTextViewCountDown.setText(String.format("%02d:%02d",
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {
                                mTextViewCountDown.setVisibility(View.INVISIBLE);
                                PlayPause.setImageResource(R.drawable.play);
                                player.pause();

                            }
                        }.start();
                        break;
                }
            }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
