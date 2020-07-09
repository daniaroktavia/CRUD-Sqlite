package com.example.crudsqlite.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudsqlite.Adapter.PegawaiAdapter;
import com.example.crudsqlite.Database.PegawaiHelper;
import com.example.crudsqlite.Model.Pegawai;
import com.example.crudsqlite.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ShowDataFragment extends Fragment {

    private RecyclerView recyclerViewPeg;
    private PegawaiAdapter adapter;
    private PegawaiHelper pegawaiHelper;
    private Fragment fragment;

    public ShowDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewPeg = view.findViewById(R.id.recyclerview_pegawai);
        pegawaiHelper = PegawaiHelper.getInstance(getContext());
        adapter = new PegawaiAdapter(pegawaiHelper.getAllPegawai(),pegawaiHelper);
        adapter.setOnUpdateClick(new PegawaiAdapter.OnUpdateClick() {

            @Override
            public void onItemClicked(Pegawai pegawai) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("PEGAWAI",pegawai);

                fragment = new UpdateFragment();
                fragment.setArguments(bundle);
                Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewPeg.setLayoutManager(layoutManager);
        recyclerViewPeg.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPeg.setAdapter(adapter);

    }
}
