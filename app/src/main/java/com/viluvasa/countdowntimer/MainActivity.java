package com.viluvasa.countdowntimer;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 21 MARET 2018
 * CREATE BY ANAZ SATRIA AJI
 * WEBSITE : VILUVASA.COM
 * IG/TELEGRAM : @AJIANAZ
 * WANT LEARN MORE DM
 */
public class MainActivity extends AppCompatActivity {
    TextView mTextField, mDetikan;
    long HARI, JAM, MENIT, DETIK;

    //Tambahan
    private Handler handler = new Handler();
    private Runnable runnable;
    private String EVENT_DATE_TIME = "2018-06-22 08:00:00"; // MENENTUKAN TANGGAL JAM EVENT
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"; // SETTING DATE FORMAT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("MANTENESIA"); //SETTING TITLE

        countDownStart();

        mTextField = findViewById(R.id.mTextField);
        mDetikan = findViewById(R.id.mDetikan);

    }
    private void countDownStart() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    Date event_date = dateFormat.parse(EVENT_DATE_TIME);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime(); // MENGHITUNG SELISIH TANGGAL EVENT DAN SEKARANG
                        HARI = diff / (24 * 60 * 60 * 1000); //MENGHITUNG HARI DARI SELISIH
                        JAM = diff / (60 * 60 * 1000) % 24; //MENGHITUNG JAM DARI SELISIH
                        MENIT = diff / (60 * 1000) % 60; //MENGHITUNG MENIT DARI SELISIH
                        DETIK = diff / 1000 % 60; //MENGHITUNG DETIK DARI SELISIH

                        //MENAMPILKAN DATA KE TEXTVIEW
                        mTextField.setText(HARI +" Hari " +  JAM+" Jam "+ MENIT +" Menit " + DETIK + " Detik");
                    } else { //JIKA MELEBIHI TANGGAL EVENT
                        mTextField.setText("DONE"); //MENAMPILKAN SELESAI/DONE
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    protected void onStop() { //
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}
