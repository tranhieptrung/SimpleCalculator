package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    private SoundPool soundPool;
    private int zero,one,two,three,four,five,six,seven,eight,night,ac,mod,mul,div,minus,plus,dot,equal;
    private TextView display;
    private String input, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.display);
        toggleButton = (ToggleButton) findViewById(R.id.toggleBtn);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        zero = soundPool.load(this, R.raw.calculadora0, 1);
        one = soundPool.load(this, R.raw.calculadora1, 1);
        two = soundPool.load(this, R.raw.calculadora2, 1);
        three = soundPool.load(this, R.raw.calculadora3, 1);
        four = soundPool.load(this, R.raw.calculadora4, 1);
        five = soundPool.load(this, R.raw.calculadora5, 1);
        six = soundPool.load(this, R.raw.calculadora6, 1);
        seven = soundPool.load(this, R.raw.calculadora7, 1);
        eight = soundPool.load(this, R.raw.calculadora8, 1);
        night = soundPool.load(this, R.raw.calculadora9, 1);

        ac = soundPool.load(this, R.raw.calculadora10, 1);
        minus = soundPool.load(this, R.raw.calculadora17, 1);
        mod = soundPool.load(this, R.raw.calculadora11, 1);
        mul = soundPool.load(this, R.raw.calculadora12, 1);
        div = soundPool.load(this, R.raw.calculadora13, 1);
        plus = soundPool.load(this, R.raw.calculadora14, 1);
        dot = soundPool.load(this, R.raw.calculadora15, 1);
        equal = soundPool.load(this, R.raw.calculadora16, 1);


    }

    public void ButtonClick(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();

                switch (view.getId()) {
                    case R.id.btn0:
                        soundPool.play(zero, 1 ,1,0,0,1);
                        break;
                    case R.id.btn1:
                        soundPool.play(one, 1 ,1,0,0,1);
                        break;
                    case R.id.btn2:
                        soundPool.play(two, 1 ,1,0,0,1);
                        break;
                    case R.id.btn3:
                        soundPool.play(three, 1 ,1,0,0,1);
                        break;
                    case R.id.btn4:
                        soundPool.play(four, 1 ,1,0,0,1);
                        break;
                    case R.id.btn5:
                        soundPool.play(five, 1 ,1,0,0,1);
                        break;
                    case R.id.btn6:
                        soundPool.play(six, 1 ,1,0,0,1);
                        break;
                    case R.id.btn7:
                        soundPool.play(seven, 1 ,1,0,0,1);
                        break;
                    case R.id.btn8:
                        soundPool.play(eight, 1 ,1,0,0,1);
                        break;
                    case R.id.btn9:
                        soundPool.play(night, 1 ,1,0,0,1);
                        break;
                    case R.id.btnDiv:
                        soundPool.play(div, 1 ,1,0,0,1);
                        break;
                    case R.id.btnMul:
                        soundPool.play(mul, 1 ,1,0,0,1);
                        break;
                    case R.id.btnMinus:
                        soundPool.play(minus, 1 ,1,0,0,1);
                        break;
                    case R.id.btnMod:
                        soundPool.play(mod, 1 ,1,0,0,1);
                        break;
                    case R.id.btnPlus:
                        soundPool.play(plus, 1 ,1,0,0,1);
                        break;
                    case R.id.btnEqual:
                        soundPool.play(equal, 1 ,1,0,0,1);
                        break;
                    case R.id.btnDot:
                        soundPool.play(dot, 1 ,1,0,0,1);
                        break;
                }

        switch (data) {
            case "AC":
                input = "";
                break;
            case "=":
                Solve();
                answer = input;
                break;
            case "x":
                Solve();
                input += "*";
                break;
            case "%":
                if (input.length() > 0) {
                    String[] number = input.split("\\%");
                    double per = Double.parseDouble(number[0]) / 100;
                    input = per + "";
                }
                break;
            default:
                if (input == null) {
                    input = "";
                }
                if (data.equals("+") || data.equals("-") || data.equals("/")) {
                    Solve();
                }
                input += data;
        }
        display.setText(input);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

    public void Solve() {
        if (input.split("\\*").length == 2) {
            String[] number = input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                input = mul + "";
            } catch (Exception e) {

            }
        }
        if (input.split("\\/").length == 2) {
            String[] number = input.split("\\/");
            try {
                double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                input = div + "";
            } catch (Exception e) {

            }
        }
        if (input.split("\\+").length == 2) {
            String[] number = input.split("\\+");
            try {
                double sum = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                input = sum + "";
            } catch (Exception e) {

            }
        }
        if (input.split("\\-").length == 2) {
            String[] number = input.split("\\-");
            try {
                double sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                input = sub + "";
            } catch (Exception e) {

            }
        }
        String[] n = input.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                input = n[0];
            }
        }
    }
}
