package com.example.danny.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.danny.afinal.R.id.currentView;
import static com.example.danny.afinal.R.id.maxView;
import static com.example.danny.afinal.R.id.minView;
import static com.example.danny.afinal.R.id.progressBar;
import static com.example.danny.afinal.R.id.weather;

public class WeatherForecast extends Activity {
    final static String ns = null;
    final static String urlString = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";
    private SharedPreferences settings;

    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    ProgressBar prog;
    TextView cur;
    TextView minv;
    TextView maxv;
    ImageView weath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        prog = (ProgressBar) findViewById(progressBar);

        cur = (TextView) findViewById(currentView);
        minv = (TextView) findViewById(minView);
        maxv = (TextView) findViewById(maxView);
        weath = (ImageView) findViewById(weather);



        System.out.println("1 the first");
        //ForescastQuery fore = new ForescastQuery();












            }
    @Override
    protected void onResume() {
        super.onResume();
        ForescastQuery fore = new ForescastQuery();
        fore.execute();
    }
    class ForescastQuery extends AsyncTask<String,Integer, String[]> {
        String min;
        String max;
        String current;
        String bitUrl;
        Bitmap picture;
        String iconName;
        String picName;
        String[] stuff= new String[3];


        protected void onProgressUpdate(Integer... progress) {
            prog.setProgress(progress[0]);
        }



        @Override
        protected void onPostExecute(String[] stuff){

            cur.setText("Current= " +stuff[2]);
            System.out.println(current+"blahblah");

            minv.setText("Min= "+stuff[0]);

            maxv.setText("Max= "+stuff[1]);
            weath.setImageBitmap(picture);
            prog.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String[] doInBackground(String... blah) {
            try {
                System.out.println("2 the second");

                downloadUrl(urlString);
                return stuff;
            } catch (IOException e) {
                System.out.println("2 the second fail");
                return null;

            }






        }








        private StringBuilder downloadUrl(String urlString) throws IOException {


            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");

            conn.setDoInput(true);
            // Starts the query
            conn.connect();


            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            pull(conn.getInputStream());
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null)
                total.append(line);
            return total;
        }



        public void pull(InputStream i)

        {
            System.out.println("4 something else");
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                System.out.println("3 it got here");

                xpp.setInput(i, "UTF_8");
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        System.out.println("Start document");
                    }  else if (eventType== XmlPullParser.START_TAG && xpp.getName().equals("temperature")){
                        current = xpp.getAttributeValue(null, "value");
                        min = xpp.getAttributeValue(null, "min");
                        max = xpp.getAttributeValue(null, "max");
                        stuff[0]=min;
                        publishProgress(25);
                        stuff[1]=max;
                        publishProgress(50);
                        stuff[2]=current;
                        publishProgress(75);



                        System.out.println(current+min+"/n"+max+"thevalues");
                    } else if (eventType== XmlPullParser.START_TAG && xpp.getName().equals("weather")) {
                        iconName = xpp.getAttributeValue(null, "icon");
                        picName = iconName + ".png";
                        System.out.println(fileExistance(picName) + "boogah");
                        if (!fileExistance(picName)) {

                            bitUrl = ("http://openweathermap.org/img/w/" + iconName + ".png");
                            picture = getImage(bitUrl);
                            FileOutputStream outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                            picture.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                            outputStream.flush();
                            outputStream.close();
                            Log.i(picName, "downloaded");
                        } else {
                            FileInputStream fis = null;
                            try {
                                fis = openFileInput(picName);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            picture = BitmapFactory.decodeStream(fis);
                            Log.i(picName, "found locally");

                        }
                        publishProgress(100);

                    } else if (eventType == XmlPullParser.START_TAG) {
                        System.out.println("Start tag " + xpp.getName());
                    } else if (eventType == XmlPullParser.END_TAG) {
                        System.out.println("End tag " + xpp.getName());
                    } else if (eventType == XmlPullParser.TEXT) {
                        System.out.println("Text " + xpp.getText());
                    }
                    eventType = xpp.next();
                }
                System.out.println("End document");

            } catch (XmlPullParserException e) {
                System.out.println("parse fail");

            } catch (IOException e) {
                System.out.println("IO fail");

            }


        }

    }


    public static Bitmap getImage(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else
                return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    public static Bitmap getImage(String urlString) {
        try {
            URL url = new URL(urlString);
            return getImage(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public boolean fileExistance(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();   }


    public void refreshDisplay(){

    }
}

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
