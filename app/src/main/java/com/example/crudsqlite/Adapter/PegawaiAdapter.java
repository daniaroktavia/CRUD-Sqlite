package com.example.crudsqlite.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudsqlite.Database.PegawaiHelper;
import com.example.crudsqlite.Model.Pegawai;
import com.example.crudsqlite.R;

import java.util.ArrayList;

public class PegawaiAdapter extends RecyclerView.Adapter<PegawaiAdapter.ListViewHolder> {
    private ArrayList<Pegawai> listPegawai;
    private PegawaiHelper pegawaiHelper;
    private OnUpdateClick onUpdateClick;

    public PegawaiAdapter(ArrayList<Pegawai> listPegawai, PegawaiHelper pegawaiHelper){
        this.listPegawai = listPegawai;
        this.pegawaiHelper = pegawaiHelper;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pegawai, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        holder.idText.setText(listPegawai.get(position).getId());
        holder.nameText.setText(listPegawai.get(position).getName());
        holder.birthText.setText(listPegawai.get(position).getBirthDate());
        holder.genderText.setText(listPegawai.get(position).getGender());

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegawaiHelper.deletePegawai(listPegawai.get(position).getId());
                listPegawai.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,listPegawai.size());
                Toast.makeText(v.getContext(),"Berhasil dihapus.", Toast.LENGTH_SHORT).show();
            }
        });

        holder.updBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClick.onItemClicked(listPegawai.get(position));
            }
        });
    }

    public interface OnUpdateClick {
        void onItemClicked(Pegawai pegawai);
    }

    public void setOnUpdateClick(OnUpdateClick onUpdateClick) {
        this.onUpdateClick = onUpdateClick;
    }

    @Override
    public int getItemCount() {
        return listPegawai.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView idText, nameText, birthText, genderText;
        Button updBtn, delBtn;

        public ListViewHolder(@NonNull View v) {
            super(v);
            idText = v.findViewById(R.id.txtId);
            nameText = v.findViewById(R.id.txtName);
            birthText = v.findViewById(R.id.txtBirth);
            genderText = v.findViewById(R.id.txtGender);
            updBtn = v.findViewById(R.id.btn_ubah);
            delBtn = v.findViewById(R.id.btn_delete);
        }
    }
}
