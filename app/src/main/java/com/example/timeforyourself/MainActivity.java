package com.example.timeforyourself;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn);
        Button button2 = findViewById(R.id.btn2);
        Button button3 = findViewById(R.id.btn3);
        Button button4 = findViewById(R.id.btn4);
        Button button5 = findViewById(R.id.btn5);
        Button button6 = findViewById(R.id.btn6);
        Button button7 = findViewById(R.id.btn7);
        Button button8 = findViewById(R.id.btn8);
        Button button9 = findViewById(R.id.btn9);
        Button button10 = findViewById(R.id.btn10);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
}
    @Override
    public void onClick(View v){

    switch (v.getId()){
        case R.id.btn:
            Intent intent = new Intent(this, Morning.class);
            startActivity(intent);
            break;
        case R.id.btn2:
            Intent intent2 = new Intent(this, Paradise.class);
            startActivity(intent2);
            break;
        case R.id.btn3:
            Intent intent3 = new Intent(this, Forest.class);
            startActivity(intent3);
            break;
        case R.id.btn4:
            Intent intent4 = new Intent(this, Sea.class);
            startActivity(intent4);
            break;
        case R.id.btn5:
            Intent intent5= new Intent(this, Ocean.class);
            startActivity(intent5);
            break;
        case R.id.btn6:
            Intent intent6 = new Intent(this, Calm.class);
            startActivity(intent6);
            break;
        case R.id.btn7:
            Intent intent7 = new Intent(this, Happiness.class);
            startActivity(intent7);
            break;
        case R.id.btn8:
            Intent intent8 = new Intent(this, FindYourself.class);
            startActivity(intent8);
            break;
        case R.id.btn9:
            Intent intent9 = new Intent(this, FreeYourMind.class);
            startActivity(intent9);
            break;
        case R.id.btn10:
            Intent intent10 = new Intent(this, Focused.class);
            startActivity(intent10);
            break;
        }

    }
}