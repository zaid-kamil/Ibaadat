package com.example.ibaadat;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.jetbrains.annotations.NotNull;

public class NamazLoader extends AsyncTaskLoader<String> {

    private final String city;

    public NamazLoader(@NonNull @NotNull Context context, String city) {
        super(context);
        this.city = city;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getNamazInfo(city);
    }
}
