package com.example.crudsqlite.Fragment;


import android.os.Build;
import android.os.Bundle;
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

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class UpdateFragment extends Fragment {

    private EditText idText, nameText, birthText, genText;
    private Button updateBtn;
    Pegawai pegawai;
    private PegawaiHelper pegawaiHelper;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idText = view.findViewById(R.id.et_id);
        nameText = view.findViewById(R.id.et_name);
        birthText = view.findViewById(R.id.et_birthdate);
        genText = view.findViewById(R.id.et_gender);
        updateBtn = view.findViewById(R.id.btn_update);
        pegawaiHelper = PegawaiHelper.getInstance(getContext());
        pegawai = new Pegawai();

        Bundle bundle = getArguments();
        if(bundle.containsKey("PEGAWAI")) {
            pegawai = Objects.requireNonNull(getArguments()).getParcelable("PEGAWAI");

            assert pegawai != null;
            idText.setText(pegawai.getId());
            nameText.setText(pegawai.getName());
            birthText.setText(pegawai.getBirthDate());
            genText.setText(pegawai.getGender());
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pegawai peg = new Pegawai();
                peg.setId(idText.getText().toString());
                peg.setName(nameText.getText().toString());
                peg.setBirthDate(birthText.getText().toString());
                peg.setGender(genText.getText().toString());

                int result = pegawaiHelper.updatePegawai(peg);

                if(result > 0){
                    Toast.makeText(getContext(),"Berhasil diubah.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(),"Gagal diubah.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
