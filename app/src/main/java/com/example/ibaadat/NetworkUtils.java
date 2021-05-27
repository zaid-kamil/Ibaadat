package com.example.ibaadat;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String NAMAZ_BASE_URL =  "https://api.aladhan.com/v1/calendarByCity?";
    private static final String CITY_PARAM = "city";
    private static final String COUNTRY_PARAM = "country";
    private static final String STATE_PARAM = "state";
    private static final String METHOD_PARAM = "method";
    static String getNamazInfo(String city){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String namazJSONString = null;
        try {
            Uri builtURI = Uri.parse(NAMAZ_BASE_URL).buildUpon()
                    .appendQueryParameter(CITY_PARAM, city)
                    .appendQueryParameter(COUNTRY_PARAM, "India")
                    .appendQueryParameter(METHOD_PARAM, "2")
                    .build();
            URL requestURL = new URL(builtURI.toString());
            Log.d("url",requestURL.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            namazJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return namazJSONString;
    }
}
