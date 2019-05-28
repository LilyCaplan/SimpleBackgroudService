package backgroundservice.turntotech.org.simplebackgroudservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText mEdText;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdText = (EditText) findViewById(R.id.edtext);
        mContext = this;

    }


    /*
        1.
        startNewService method is called by clicking the 'Start Service' button.
        It is defined in activity_main.xml onClick event.
    */
    public void startNewService(View view) {

        String timeInSeconds = mEdText.getText().toString();

        if (timeInSeconds == null){
            mTimeLeftInMillis = 0;
        } else {
            mTimeLeftInMillis = 1000 * Long.parseLong(timeInSeconds);
        }

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                Log.d("TIMER",  Long.toString(mTimeLeftInMillis));
            }

            @Override
            public void onFinish() {
                startService(new Intent( mContext, MyService.class));
            }
        }.start();





    }

    /*
        2.
        stopNewService method is called by clicking the 'Stop Service' button.
        It is defined in activity_main.xml onClick event.
    */
    public void stopNewService(View view) {
        mCountDownTimer.cancel();
        stopService(new Intent(this, MyService.class));
    }





}
