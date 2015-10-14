package course.example.awesomemap;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sadaf on 22-Sep-15.
 */


import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class delivery extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery);
    }


    public class MainActivity extends Activity implements View.OnClickListener {

        private CountDownTimer countDownTimer;
        private boolean timerStarted = false;
        private Button buttonStart;
        public TextView textView;
        private final long startTime = 100 * 1000;
        private final long interval = 1 * 1000;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            buttonStart = (Button) this.findViewById(R.id.button);
            buttonStart.setOnClickListener(this);
            textView = (TextView) this.findViewById(R.id.textView);
            countDownTimer = new CountDownTimerActivity(startTime, interval);
            textView.setText(textView.getText() + String.valueOf(startTime/1000));
        }

        @Override
        public void onClick(View v) {
            if (!timerStarted) {
                countDownTimer.start();
                timerStarted = true;
                buttonStart.setText("STOP");
            } else {
                countDownTimer.cancel();
                timerStarted = false;
                buttonStart.setText("RESTART");
            }
        }


        public class CountDownTimerActivity extends CountDownTimer {
            public CountDownTimerActivity(long startTime, long interval) {
                super(startTime, interval);
            }

            @Override
            public void onFinish() {
                textView.setText("Wait Over!!!");
            }

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("" + millisUntilFinished/1000);
            }
        }

    }

        }
