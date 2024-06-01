package com.finallabtres.animalogistics.UI.refugio.editar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finallabtres.animalogistics.R;

public class EditarRefugioFragment extends Fragment {

    private EditarRefugioViewModel mViewModel;

    public static EditarRefugioFragment newInstance() {
        return new EditarRefugioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_refugio, container, false);

aca estoy

    }


}