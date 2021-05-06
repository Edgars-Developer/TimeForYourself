package com.example.timeforyourself;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static android.R.layout.select_dialog_singlechoice;
import static android.R.layout.simple_list_item_single_choice;


public class Ocean extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private MediaPlayer player = new MediaPlayer();
    private TextView mTextViewCountDown;
    private CountDownTimer timer;
    private ImageButton PlayPause;
    private long TIMER_DURATION; // hours for pickerHours
    private long TIMER_DURATION1; // minutes for mSpinner1
    private Spinner spinner;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, select_dialog_singlechoice, getResources().getStringArray(R.array.timer));
        adapter3.setDropDownViewResource(simple_list_item_single_choice);
        spinner.setAdapter(adapter3);
        spinner.setOnItemSelectedListener(this);
        String title = centerString(50, getString(R.string.setATimer));
        spinner.setPrompt(title);
        player = MediaPlayer.create(this, R.raw.parad);
        player.start();
        player.setLooping(true);
        PlayPause = this.findViewById(R.id.btnStart);
        ImageButton timerBtn = findViewById(R.id.timer);
        mTextViewCountDown = findViewById(R.id.textView);
        mTextViewCountDown.setVisibility(View.INVISIBLE);
        mTextViewCountDown.setTextSize(50);

        //Timer click
        timerBtn.setOnClickListener(v -> {
            stopCountdown();
            spinner.performClick(); // To open  spinner dialog
            spinner.setSelection(0); // To restart the same timer
        });
        //Click to play and pause music
        PlayPause.setOnClickListener(v -> {

            if (player.isPlaying()) {
                PlayPause.setImageResource(R.drawable.play); // in java code :srcCompat="@drawable/"
                player.pause();
            } else {
                player.start();
                PlayPause.setImageResource(R.drawable.pause);

            }
        }); // One button can make two actions with switch images
    }
    // Put spinner title in the text
    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
    // Create a timer, after 15 seconds the sound volume decrease slowly
    public void timer(long s, long y){
        mTextViewCountDown.setVisibility(View.VISIBLE);
        timer = new CountDownTimer(s, y) {
            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {

                mTextViewCountDown.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                if (millisUntilFinished<3600000){
                    mTextViewCountDown.setText(String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }
                if (millisUntilFinished < 15000) {
                    player.setVolume(0.9f, 0.9f);
                }
                if (millisUntilFinished <= 13000) {
                    player.setVolume(0.8f, 0.8f);
                }
                if (millisUntilFinished <= 11000) {
                    player.setVolume(0.7f, 0.7f);
                }
                if (millisUntilFinished <= 9000) {
                    player.setVolume(0.6f, 0.6f);
                }
                if (millisUntilFinished <= 7000) {
                    player.setVolume(0.5f, 0.5f);
                }
                if (millisUntilFinished <= 5000) {
                    player.setVolume(0.4f, 0.4f);
                }
                if (millisUntilFinished <= 3000) {
                    player.setVolume(0.3f, 0.3f);
                }
                if (millisUntilFinished <= 2000) {
                    player.setVolume(0.2f, 0.2f);
                }
                if (millisUntilFinished <= 1000) {
                    player.setVolume(0.1f, 0.1f);
                }
            }
            public void onFinish() {
                player.setVolume(1.0f, 1.0f);
                mTextViewCountDown.setVisibility(View.INVISIBLE);
                PlayPause.setImageResource(R.drawable.play);
                player.pause();
            }
        }.start();
    }
    // On click countDownTimer
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: mTextViewCountDown.setVisibility(View.INVISIBLE); break;
            case 1:  showAlert(); break;
            case 2:  timer(300000, 1000);   break;
            case 3:  timer(600000, 1000);   break;
            case 4:  timer(900000, 1000);   break;
            case 5:  timer(1200000, 1000);  break;
            case 6:  timer(1800000, 1000);  break;
            case 7:  timer(2400000, 1000);  break;
            case 8:  timer(3600000, 1000);  break;
            case 9:  timer(7200000, 1000);  break;
            case 10: timer(14400000, 1000); break;
            case 11: timer(21600000, 1000); break;
            case 12: timer(28800000, 1000); break;
            default: stopCountdown(); break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    //Click on custom timer
    @SuppressLint("SetTextI18n")
    private void showAlert() {
        final AlertDialog.Builder myBuilder = new AlertDialog.Builder(Ocean.this,R.style.ThemeOverlay_AppCompat_Dialog_Alert);
        // Modify AlertDialog title (( change tex color, size and put in center)
        TextView title = new TextView(this);
        title.setText(R.string.timerDuration);
        title.setPadding(50,50,50,50);
        title.setTextColor(Color.BLACK);
        title.setTextSize(18);
        title.setGravity(Gravity.CENTER);
        myBuilder.setCustomTitle(title);
        View mView = getLayoutInflater().inflate(R.layout.spinnerdialog, null);
        myBuilder.setView(mView);

        // It saves last pickers
        final NumberPicker pickerHours = mView.findViewById(R.id.numberpicker_main_picker);
        final NumberPicker pickerMinutes = mView.findViewById(R.id.numberpicker_main_picker1);
        pickerMinutes.setMaxValue(getResources().getStringArray(R.array.minutes).length - 1);
        pickerMinutes.setDisplayedValues(getResources().getStringArray(R.array.minutes));
        pickerHours.setMaxValue(getResources().getStringArray(R.array.hours).length - 1);
        pickerHours.setDisplayedValues(getResources().getStringArray(R.array.hours));
        pickerHours.setWrapSelectorWheel(true);
        pickerMinutes.setWrapSelectorWheel(true);

        // It saves last pickers
        AtomicReference<SharedPreferences> sharedPref = new AtomicReference<>(getSharedPreferences(getString(R.string.FileName), MODE_PRIVATE));
        int value = sharedPref.get().getInt(getString(R.string.Choise),-1);
        if(value != -1)
            pickerHours.setValue(value); // save the last selected item
        int value1 = sharedPref.get().getInt(getString(R.string.Choice1),-1);
        if(value1 != -1)
            pickerMinutes.setValue(value1); // save the last selected item

        // On Start button action
        myBuilder.setPositiveButton(R.string.startTimer, (dialogInterface, which) -> { //custom contDownTimer

            int Choice = pickerHours.getValue();
            int Choice1 = pickerMinutes.getValue();
            sharedPref.set(getSharedPreferences(getString(R.string.FileName), 0));
            SharedPreferences.Editor prefEditor = sharedPref.get().edit();
            prefEditor.putInt(getString(R.string.Choise),Choice); // show last selected item
            prefEditor.putInt(getString(R.string.Choice1),Choice1); // show last selected item
            prefEditor.apply();

            int hours = pickerHours.getValue();
            int minutes = pickerMinutes.getValue();

            switch (hours) {
                case 0: TIMER_DURATION = 0; break;
                case 1: TIMER_DURATION = 3600000;  break;
                case 2: TIMER_DURATION = 7200000;  break;
                case 3: TIMER_DURATION = 10800000; break;
                case 4: TIMER_DURATION = 14400000; break;
                case 5: TIMER_DURATION = 18000000; break;
                case 6: TIMER_DURATION = 21600000; break;
                case 7: TIMER_DURATION = 25200000; break;
                case 8: TIMER_DURATION = 28800000; break;
                case 9: TIMER_DURATION = 32400000; break;
                case 10: TIMER_DURATION = 36000000; break;
                case 11: TIMER_DURATION = 39600000; break;
                case 12: TIMER_DURATION = 43200000; break;
                case 13: TIMER_DURATION = 46800000; break;
                case 14: TIMER_DURATION = 50400000; break;
                case 15: TIMER_DURATION = 54000000; break;
                case 16: TIMER_DURATION = 57600000; break;
                case 17: TIMER_DURATION = 61200000; break;
                case 18: TIMER_DURATION = 64800000; break;
                case 19: TIMER_DURATION = 68400000; break;
                case 20: TIMER_DURATION = 72000000; break;
                case 21: TIMER_DURATION = 75600000; break;
                case 22: TIMER_DURATION = 79200000; break;
                case 23: TIMER_DURATION = 82800000; break;
                default: stopCountdown(); break;
            }
            switch (minutes) {
                case 0: TIMER_DURATION1 = 0; break;
                case 1: TIMER_DURATION1 = 60000; break;
                case 2: TIMER_DURATION1 = 120000; break;
                case 3: TIMER_DURATION1 = 180000; break;
                case 4: TIMER_DURATION1 = 240000; break;
                case 5: TIMER_DURATION1 = 300000; break;
                case 6: TIMER_DURATION1 = 360000; break;
                case 7: TIMER_DURATION1 = 420000; break;
                case 8: TIMER_DURATION1 = 480000; break;
                case 9: TIMER_DURATION1 = 540000; break;
                case 10: TIMER_DURATION1 = 600000; break;
                case 11: TIMER_DURATION1 = 660000; break;
                case 12: TIMER_DURATION1 = 720000; break;
                case 13: TIMER_DURATION1 = 780000; break;
                case 14: TIMER_DURATION1 = 840000; break;
                case 15: TIMER_DURATION1 = 900000; break;
                case 16: TIMER_DURATION1 = 960000; break;
                case 17: TIMER_DURATION1 = 1020000; break;
                case 18: TIMER_DURATION1 = 1080000; break;
                case 19: TIMER_DURATION1 = 1140000; break;
                case 20: TIMER_DURATION1 = 1200000; break;
                case 21: TIMER_DURATION1 = 1260000; break;
                case 22: TIMER_DURATION1 = 1320000; break;
                case 23: TIMER_DURATION1 = 1380000; break;
                case 24: TIMER_DURATION1 = 1440000; break;
                case 25: TIMER_DURATION1 = 1500000; break;
                case 26: TIMER_DURATION1 = 1560000; break;
                case 27: TIMER_DURATION1 = 1620000; break;
                case 28: TIMER_DURATION1 = 1680000; break;
                case 29: TIMER_DURATION1 = 1740000; break;
                case 30: TIMER_DURATION1 = 1800000; break;
                case 31: TIMER_DURATION1 = 1860000; break;
                case 32: TIMER_DURATION1 = 1920000; break;
                case 33: TIMER_DURATION1 = 1980000; break;
                case 34: TIMER_DURATION1 = 2040000; break;
                case 35: TIMER_DURATION1 = 2100000; break;
                case 36: TIMER_DURATION1 = 2160000; break;
                case 37: TIMER_DURATION1 = 2220000; break;
                case 38: TIMER_DURATION1 = 2280000; break;
                case 39: TIMER_DURATION1 = 2340000; break;
                case 40: TIMER_DURATION1 = 2400000; break;
                case 41: TIMER_DURATION1 = 2460000; break;
                case 42: TIMER_DURATION1 = 2520000; break;
                case 43: TIMER_DURATION1 = 2580000; break;
                case 44: TIMER_DURATION1 = 2640000; break;
                case 45: TIMER_DURATION1 = 2700000; break;
                case 46: TIMER_DURATION1 = 2760000; break;
                case 47: TIMER_DURATION1 = 2820000; break;
                case 48: TIMER_DURATION1 = 2880000; break;
                case 49: TIMER_DURATION1 = 2940000; break;
                case 50: TIMER_DURATION1 = 3000000; break;
                case 51: TIMER_DURATION1 = 3060000; break;
                case 52: TIMER_DURATION1 = 3120000; break;
                case 53: TIMER_DURATION1 = 3180000; break;
                case 54: TIMER_DURATION1 = 3240000; break;
                case 55: TIMER_DURATION1 = 3300000; break;
                case 56: TIMER_DURATION1 = 3360000; break;
                case 57: TIMER_DURATION1 = 3420000; break;
                case 58: TIMER_DURATION1 = 3480000; break;
                case 59: TIMER_DURATION1 = 3540000; break;
                default: stopCountdown(); break;
            }
            long result = TIMER_DURATION + TIMER_DURATION1;
            mTextViewCountDown.setVisibility(View.VISIBLE);

            timer = new CountDownTimer(result, 1000) {
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
                    mTextViewCountDown.setVisibility(View.INVISIBLE);
                    PlayPause.setImageResource(R.drawable.play);
                    player.pause();
                }
            }.start();
        });
        final AlertDialog dialog = myBuilder.create();
        dialog.show();

        //Modify AlertDialog positive button ( change tex color, size and put in center)
        final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout parent = (LinearLayout) positiveButton.getParent();
        parent.setGravity(Gravity.CENTER_HORIZONTAL);
        View leftSpacer = parent.getChildAt(1);
        leftSpacer.setVisibility(View.GONE);
        positiveButton.setBackgroundResource(R.drawable.button4_style);
        positiveButton.setTextColor(Color.BLACK);
        positiveButton.setPadding(50,50,50,50);
        positiveButton.setTextSize(15);

    }
    // stop music when you pressed the back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
        player.release();
    }
    // turn off a timer
    private void stopCountdown() {
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

}