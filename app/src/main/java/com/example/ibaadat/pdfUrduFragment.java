package com.example.ibaadat;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ibaadat.databinding.FragmentPdfUrduQuranBinding;

import org.jetbrains.annotations.NotNull;


public class pdfUrduFragment extends Fragment {

    private com.example.ibaadat.databinding.FragmentPdfUrduQuranBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pdf_urdu_quran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = FragmentPdfUrduQuranBinding.bind(view);
        bind.pdf.fromAsset("pdf/urdu_quran.pdf").show();
    }
}