package com.example.a10;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    public static int x;
    public static int y;

    private int orientation = Configuration.ORIENTATION_PORTRAIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setContentView(new AnimatedView(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x -= (int) event.values[0] * 10;
            y += (int) event.values[1] * 10;
            if (getResources().getConfiguration().orientation != orientation) {
                x = 0;
                y = 0;
            }
        }
    }

    public class AnimatedView extends AppCompatImageView {

        ImageView background;
        ImageView spaceship;
        static final int spaceshipHeightWidth = 500;

        public AnimatedView(Context context) {
            super(context);
            background = new ImageView(context);
            background.setImageResource(R.drawable.wallp);

            spaceship = new ImageView(context);
            spaceship.setImageResource(R.drawable.spaceship);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            background.getDrawable().setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            background.getDrawable().draw(canvas);
            spaceship.getDrawable().setBounds(x, y, x + spaceshipHeightWidth, y + spaceshipHeightWidth);

            // saiu pela esquerda
            if (spaceship.getDrawable().getBounds().left < 0) {
                x = 0;
                spaceship.getDrawable().getBounds().left = x;
                spaceship.getDrawable().getBounds().right = spaceshipHeightWidth;
            }
            // saiu por cima
            if (spaceship.getDrawable().getBounds().top < 0) {
                y = 0;
                spaceship.getDrawable().getBounds().top = y;
                spaceship.getDrawable().getBounds().bottom = spaceshipHeightWidth;
            }
            // saiu por baixo
            if (spaceship.getDrawable().getBounds().bottom > canvas.getHeight()) {
                y = canvas.getHeight() - spaceshipHeightWidth;
                spaceship.getDrawable().getBounds().top = y;
                spaceship.getDrawable().getBounds().bottom = canvas.getHeight();
            }
            // saiu pela direita
            if (spaceship.getDrawable().getBounds().left + spaceshipHeightWidth > canvas.getWidth()) {
                x = canvas.getWidth() - spaceshipHeightWidth;
                spaceship.getDrawable().getBounds().left = x;
                spaceship.getDrawable().getBounds().right = canvas.getWidth();
            }

            spaceship.getDrawable().draw(canvas);
            invalidate();
        }
    }

}