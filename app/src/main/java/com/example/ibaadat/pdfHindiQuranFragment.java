package com.example.ibaadat;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ibaadat.databinding.FragmentPdfHindiQuranBinding;

import org.jetbrains.annotations.NotNull;

public class pdfHindiQuranFragment extends Fragment {

    private FragmentPdfHindiQuranBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pdf_hindi_quran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = FragmentPdfHindiQuranBinding.bind(view);
        bind.pdf.fromAsset("pdf/hindi_quran.pdf").show();
    }
}