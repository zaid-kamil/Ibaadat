package com.example.ibaadat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.ibaadat.databinding.FragmentNamazSchedulerBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

import static android.content.Context.ALARM_SERVICE;


public class NamazSchedulerFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    private com.example.ibaadat.databinding.FragmentNamazSchedulerBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_namaz_scheduler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = FragmentNamazSchedulerBinding.bind(view);
        bind.btnSchedule.setOnClickListener(view1 -> {
            String city = bind.editCity.getText().toString();
            InputMethodManager inputManager = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
            // Check the status of the network connection.
            ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connMgr != null) {
                networkInfo = connMgr.getActiveNetworkInfo();
            }
            if (networkInfo != null && networkInfo.isConnected() && city.length() != 0) {

                Bundle queryBundle = new Bundle();
                queryBundle.putString("city", city);
                getLoaderManager().restartLoader(0, queryBundle, this);
            } else {
                if (city.length() == 0) {

                    bind.editCity.setError(getString(R.string.no_search_term));
                } else {

                    bind.editCity.setError(getString(R.string.no_network));
                }
            }
        });
    }

    @NonNull
    @NotNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {
        String queryString = "";

        if (args != null) {
            queryString = args.getString("city");
        }

        return new NamazLoader(getActivity(), queryString);
    }

    @Override
    public void onLoadFinished(@NonNull @NotNull Loader<String> loader, String data) {
        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(data);
            // Get the JSONArray of book items.
            JSONArray itemsArray = jsonObject.getJSONArray("data");

            // Initialize iterator and results fields.
            int i = 0;
            String fazr = null;
            String dhuhr = null;
            String asr = null;
            String maghrib = null;
            String isha = null;

            // Look for results in the items array, exiting when both the
            // title and author are found or when all items have been checked.
            while (i < itemsArray.length() && (fazr == null && dhuhr == null && asr == null && maghrib == null && isha == null)) {
                // Get the current item information.
                JSONObject timings = itemsArray.getJSONObject(i);
                JSONObject namazTime = timings.getJSONObject("timings");
                String date = timings.getJSONObject("date").getString("readable");

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    fazr = namazTime.getString("Fajr");
                    dhuhr = namazTime.getString("Dhuhr");
                    asr = namazTime.getString("Asr");
                    maghrib = namazTime.getString("Maghrib");
                    isha = namazTime.getString("Isha");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }

            // If all are found, display the result.
            if (fazr != null && dhuhr != null && asr != null && maghrib != null && isha != null) {
                Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                bind.fajrTime.setText(fazr);
                bind.zoharTime.setText(dhuhr);
                bind.asrTime.setText(asr);
                bind.magribTime.setText(maghrib);
                bind.ishaTime.setText(isha);
                setupAlarm(fazr, dhuhr, asr, maghrib, isha);
            } else {
                // If none are found, update the UI to show failed results.
                bind.editCity.setError(getString(R.string.no_results));

            }

        } catch (Exception e) {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            bind.editCity.setError(getString(R.string.no_results));

            e.printStackTrace();
        }
    }

    private void setupAlarm(String fazr, String zohar, String asr, String maghrib, String isha) {
        Intent intent = new Intent(getActivity(), NamanBroadcastReciever.class);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 234, intent, 0);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 235, intent, 0);
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 236, intent, 0);
        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 237, intent, 0);
        PendingIntent pendingIntent5 = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 238, intent, 0);
        Timestamp fazrTime = getNamazTimeStamp(fazr);
        Timestamp asrTime = getNamazTimeStamp(asr);
        Timestamp maghribTime = getNamazTimeStamp(maghrib);
        Timestamp ishaTime = getNamazTimeStamp(isha);
        Timestamp zoharTime = getNamazTimeStamp(zohar);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + fazrTime.getTime(), pendingIntent1);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zoharTime.getTime(), pendingIntent2);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + asrTime.getTime(), pendingIntent3);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + maghribTime.getTime(), pendingIntent4);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + ishaTime.getTime(), pendingIntent5);
        Toast.makeText(getActivity(), "Alarm set for namaz schedule", Toast.LENGTH_LONG).show();
        Log.d("time", String.valueOf(System.currentTimeMillis() + fazrTime.getTime()));
    }

    @NotNull
    private Timestamp getNamazTimeStamp(String namaz) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        String namazDate = year + "-" + month + "-" + day + " " + namaz.split(" ")[0] + ":" + 00;
        return Timestamp.valueOf(namazDate);

    }

    @Override
    public void onLoaderReset(@NonNull @NotNull Loader<String> loader) {

    }

}