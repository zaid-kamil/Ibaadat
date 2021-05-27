package com.example.ibaadat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ibaadat.databinding.FragmentTazbiBinding;

import org.jetbrains.annotations.NotNull;

public class TazbiFragment extends Fragment {

    private FragmentTazbiBinding bind;
    int result;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tazbi,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = FragmentTazbiBinding.bind(view);

        // our compass image
        bind.btnCount.setOnClickListener(v -> plus());
        bind.btnReset.setOnClickListener(v -> clear());
        bind.btnBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
        });

    }
    public void plus()
    {
        result++;
        bind.textCounter.setText(Integer.toString(result));
    }
    public void clear(){
        result=0;
        bind.textCounter.setText(Integer.toString(result));
    }

}