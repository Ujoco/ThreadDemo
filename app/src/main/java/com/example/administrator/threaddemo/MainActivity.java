package com.example.administrator.threaddemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private ImageView mImage;
    private MHandler mHandler = new MHandler(this);

    public ImageView getmImage() {
        return mImage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = (ImageView) findViewById(R.id.image);

        Message msg = Message.obtain();
        msg.what = 0;
        mHandler.sendMessageDelayed(msg,2000);
    }

    public static class MHandler extends Handler{
        WeakReference<MainActivity> weakReference;
        private MHandler(MainActivity activity){
            weakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {

            Message message = Message.obtain();
            MainActivity activity = weakReference.get();
            int what = msg.what;

            switch (what){
                case 0:activity.getmImage().setImageResource(R.drawable.one);
                    break;
                case 1:activity.getmImage().setImageResource(R.drawable.two);
                    break;
                case 2:activity.getmImage().setImageResource(R.drawable.three);
                    break;
            }
            message.what = (++what) % 3;
            sendMessageDelayed(message,2000);
        }
    }
}
