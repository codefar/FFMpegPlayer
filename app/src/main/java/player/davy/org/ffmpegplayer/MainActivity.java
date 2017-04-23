package player.davy.org.ffmpegplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv.setText(VideoPlayer.avcodecInfo());

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        surfaceHolder = surfaceView.getHolder();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            surfaceHolder.addCallback(this);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                surfaceHolder.addCallback(this);
                if (surfaceHolder.getSurface().isValid()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            VideoPlayer.play(surfaceHolder.getSurface());
                        }
                    }).start();
                }
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    VideoPlayer.play(surfaceHolder.getSurface());
                }
            }).start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void copyBigDataToSD() {

        final File file = new File("/scdard/11.mp4");
        if (file.exists()) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream myInput;
                    OutputStream myOutput = new FileOutputStream(file);
                    myInput = MainActivity.this.getAssets().open("11.mp4");
                    byte[] buffer = new byte[1024];
                    int length = myInput.read(buffer);
                    while (length > 0) {
                        myOutput.write(buffer, 0, length);
                        length = myInput.read(buffer);
                    }
                    myOutput.flush();
                    myInput.close();
                    myOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
