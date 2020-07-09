package com.example.crudsqlite.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.crudsqlite.Database.PegawaiHelper;
import com.example.crudsqlite.Model.Pegawai;
import com.example.crudsqlite.R;
import com.google.gson.Gson;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class InsertFragment extends Fragment {

    private EditText idText, nameText, birthText, genText;
    private Button submitBtn;
    private PegawaiHelper pegawaiHelper;

    public InsertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        idText = view.findViewById(R.id.et_id);
        nameText = view.findViewById(R.id.et_name);
        birthText = view.findViewById(R.id.et_birthdate);
        genText = view.findViewById(R.id.et_gender);
        submitBtn = view.findViewById(R.id.btn_submit);
        pegawaiHelper = PegawaiHelper.getInstance(getContext());

        btnSubmitClick();
    }

    private void btnSubmitClick() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pegawai peg = new Pegawai();
                peg.setId(idText.getText().toString());
                peg.setName(nameText.getText().toString());
                peg.setBirthDate(birthText.getText().toString());
                peg.setGender(genText.getText().toString());

                long result = pegawaiHelper.insertPegawai(peg);
                if (result > 0) {
                    Objects.requireNonNull(getActivity()).setResult(100);
                    Toast.makeText(getContext(),"Berhasil disimpan.", Toast.LENGTH_SHORT).show();
                    Log.e("hasil", new Gson().toJson(pegawaiHelper.getAllPegawai()));
                    idText.setText("");nameText.setText(""); birthText.setText("");genText.setText("");
                } else {
                    Toast.makeText(getContext(),"Gagal disimpan.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
