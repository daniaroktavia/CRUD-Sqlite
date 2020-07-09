package com.example.crudsqlite.Fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.crudsqlite.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class HomeFragment extends Fragment {
    private Fragment fragment;
    private Button insert_btn, showdata_btn, prima_btn;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        insert_btn = view.findViewById(R.id.btn_insert);
        showdata_btn = view.findViewById(R.id.btn_show_data);
        prima_btn = view.findViewById(R.id.btn_prima);

        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new InsertFragment();
                Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });

        showdata_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ShowDataFragment();
                Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });

        prima_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new PrimaFragment();
                Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }


}

