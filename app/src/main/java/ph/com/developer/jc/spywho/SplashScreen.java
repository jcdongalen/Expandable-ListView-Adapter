package ph.com.developer.jc.spywho;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {

    Button btnStart, btnStop, btnView;
    String path;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnView = (Button) findViewById(R.id.btnView);

        editText = (EditText) findViewById(R.id.editText);

        path = Environment.getDataDirectory().getAbsolutePath();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new movetoRight().execute(btnStop.getX());
//                String x = String.valueOf(btnStop.getX());
//                Log.wtf("Position", x);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnStop.setX(btnStop.getX() + 100);
//                MyProgressDialog progressDialog = new MyProgressDialog(SplashScreen.this);
//                progressDialog.setProgMax(100);
//                progressDialog.setProgVal(0);
//                progressDialog.show();

//                for(int i = 0; i < 100; i++){
//                    try {
//                        progressDialog.setProgVal(i+1);
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

                ShowCustomProgress task = new ShowCustomProgress();
                task.execute();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private class ShowCustomProgress extends AsyncTask<String, String, String>{

        MyProgressDialog progressDialog = new MyProgressDialog(SplashScreen.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgMax(100);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            for(int i = 0; i < 100; i++){
                try {
                    Thread.sleep(10);
                    publishProgress(String.valueOf(i+1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgVal(Integer.parseInt(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }
    }

    private int goToRight() {
        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        int maxX = mdispSize.x;
        int maxY = mdispSize.y;
//        while (btnStop.getX() <= maxX);{
//            btnStop.setX(btnStop.getX() + 10);
//        }
        return maxX;
    }

    private int getbtnStopPosition() {
        return Integer.parseInt(String.valueOf(btnStop.getX()));
    }

    private class movetoRight extends AsyncTask<Float, Integer, Integer> {

        ProgressDialog progressDialog = new ProgressDialog(SplashScreen.this);

        int x = 0;
        int MaxX = 800;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.setTitle("Downloading");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgressPercentFormat(null);
            progressDialog.setMax(MaxX);
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(Float... params) {
            for (int i = 0; i < 10; i++) {
                if (i < MaxX) {
                    try {
                        Thread.sleep(1000);
                        publishProgress(10);
                        x =+ 10;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
                new Thread(new Runnable() {
                    @Override
                    public synchronized void run() {
                        progressDialog.setSecondaryProgress(x);
                        btnStop.setX(btnStop.getX() + 10);
                    }
                }).start();
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
    }
}
