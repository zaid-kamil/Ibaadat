package com.example.ibaadat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ibaadat.databinding.FragmentSurahDuaBinding;

import org.jetbrains.annotations.NotNull;


public class SurahDuaFragment extends Fragment {


    private FragmentSurahDuaBinding bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_surah_dua, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = FragmentSurahDuaBinding.bind(view);
        bind.qenglishImage.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_surahDuaFragment_to_pdfEnglishQuranFragment);
        });
        bind.urdu.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_surahDuaFragment_to_pdfUrduFragment);
        });
        bind.hinid.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_surahDuaFragment_to_pdfHindiQuranFragment);
        });
        bind.audio.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_surahDuaFragment_to_audioPlayerFragment);
        });



    }
}