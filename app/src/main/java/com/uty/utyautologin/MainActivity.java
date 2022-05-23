package com.uty.utyautologin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String urlLogin = "https://sia.uty.ac.id/";

    private String NIM = "5191011036";
    private String Password = "asdqwe123@@A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ambilData ambil = new ambilData();
        ambil.execute();
    }

    private class ambilData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {

                Connection.Response loginForm = Jsoup.connect(urlLogin)
                        .method(Connection.Method.GET)
                        .execute();
                Document document = loginForm.parse();

                String xPathSelector = "/html/body/section/div/div/div/div[1]/div/div[2]/div/div[2]/form/div/p";
                Elements captcha = document.selectXpath(xPathSelector);
                String soal = captcha.text();
                String cari_angka = soal.replaceAll("\\D+","");
                System.out.println("Ini hasil: ");
                System.out.println(cari_angka);

                System.out.println(hitung(cari_angka));

            }
            catch (IOException e){
                e.printStackTrace();
            }
//            org.jsoup.select.Elements nama = document.selectXpath("/html/body/div[2]/nav/div[2]/div/ul/div/center/p");
//            String nama_ = nama.text();
//            System.out.println(nama_);
            return null;
        };
    }

    protected int hitung(String daftar_nilai){
        int z = 0;
        int panjang = daftar_nilai.length();
        int[] daftar = new int[panjang];
        int jumlah;

        for (int i = 0; i < panjang; i++) {
            daftar[i] = Integer.parseInt(daftar_nilai.charAt(i));
        }

        for (int i = 0; i < panjang; i++) {
            jumlah = jumlah + daftar[i];
        }
        return jumlah;
    }

}