package com.example.ibaadat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ibaadat.databinding.FragmentFirstBinding;

import org.jetbrains.annotations.NotNull;

public class FirstFragment extends Fragment {

    int result;
    private FragmentFirstBinding bind;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = FragmentFirstBinding.bind(view);
        bind.hijri.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_hijriCalendar));
        bind.etazbi.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_tazbiFragment));
        bind.surahDua.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_surahDuaFragment));
        bind.namazSchedular.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_namazSchedulerFragment));
        bind.qibla.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_qiblaLocator2));


    }


}