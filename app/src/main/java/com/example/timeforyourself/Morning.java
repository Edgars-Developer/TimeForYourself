package com.example.timeforyourself;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

        TimerBtn.setOnClickListener(v -> {
            spinner.setSelection(0); // onItemSelected chose case 0
            spinner.performClick();
            stopCountdown();
        });

        PlayPause.setOnClickListener(v -> {

            if (player.isPlaying()) {
                PlayPause.setImageResource(R.drawable.play); // in java code :srcCompat="@drawable/"
                player.pause();
            } else {
                player.start();
                PlayPause.setImageResource(R.drawable.pause);

            }
            PlayPause.setBackgroundResource(R.drawable.spinner_style);
        }); // One button can make two actions with switch images
    }

    private void showAlert() {
        final AlertDialog.Builder myBuilder = new AlertDialog.Builder(Morning.this);
        myBuilder.setTitle("Choose your timer");
        View mView = getLayoutInflater().inflate(R.layout.spinnerdialog, null);
        myBuilder.setView(mView);
        Spinner mSpinner = mView.findViewById(R.id.spinner2);
        Spinner mSpinner1 = mView.findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Morning.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.hours));
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Morning.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.minutes));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner1.setAdapter(adapter1);
        myBuilder.setPositiveButton("Start Timer", (dialogInterface, which) -> {

                PlayPause.setVisibility(View.INVISIBLE);
                mTextViewCountDown.setVisibility(View.VISIBLE);
                long h = mSpinner.getSelectedItemPosition();
                long m = mSpinner1.getSelectedItemPosition();
                long result = h + m;


                timer = new CountDownTimer(result, 100) {
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



        });
        AlertDialog dialog = myBuilder.create();
        dialog.show();


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
                        showAlert();
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
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(600000, 100) {
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
                    case 4:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(900000, 100) {
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
                    case 5:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(1200000, 100) {
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
                    case 6:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(1800000, 100) {
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
                    case 7:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(2400000, 100) {
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
                    case 8:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(3600000, 100) {
                            @SuppressLint("DefaultLocale")
                            public void onTick(long millisUntilFinished) {
                                if (millisUntilFinished<3600000)
                                        mTextViewCountDown.setText(String.format("%02d:%02d",
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                else
                                mTextViewCountDown.setText(String.format("%02d:%02d:%02d",
                                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
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
                    case 9:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(7200000, 100) {
                            @SuppressLint("DefaultLocale")
                            public void onTick(long millisUntilFinished) {
                                if (millisUntilFinished<3600000)
                                    mTextViewCountDown.setText(String.format("%02d:%02d",
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                else
                                    mTextViewCountDown.setText(String.format("%02d:%02d:%02d",
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
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
                    case 10:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(14400000, 100) {
                            @SuppressLint("DefaultLocale")
                            public void onTick(long millisUntilFinished) {
                                if (millisUntilFinished<3600000)
                                    mTextViewCountDown.setText(String.format("%02d:%02d",
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                else
                                    mTextViewCountDown.setText(String.format("%02d:%02d:%02d",
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
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
                    case 11:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(21600000, 100) {
                            @SuppressLint("DefaultLocale")
                            public void onTick(long millisUntilFinished) {
                                if (millisUntilFinished<3600000)
                                    mTextViewCountDown.setText(String.format("%02d:%02d",
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                else
                                    mTextViewCountDown.setText(String.format("%02d:%02d:%02d",
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
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
                    case 12:
                        PlayPause.setVisibility(View.INVISIBLE);
                        mTextViewCountDown.setVisibility(View.VISIBLE);
                        timer = new CountDownTimer(28800000, 100) {
                            @SuppressLint("DefaultLocale")
                            public void onTick(long millisUntilFinished) {
                                if (millisUntilFinished<3600000)
                                    mTextViewCountDown.setText(String.format("%02d:%02d",
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                else
                                    mTextViewCountDown.setText(String.format("%02d:%02d:%02d",
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
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
                   }
            }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
