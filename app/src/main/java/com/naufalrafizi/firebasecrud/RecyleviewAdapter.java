package com.naufalrafizi.firebasecrud;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/15/2017.
 */

public class RecyleviewAdapter extends RecyclerView.Adapter<RecyleviewAdapter.ViewHolder> {
    public ArrayList<Mahasiswa> values;
    public RecyleviewAdapter(ArrayList<Mahasiswa> values) {
        this.values = values;
    }

    @Override
    public RecyleviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mahasiswa data = values.get(position);
        holder.txtnama.setText(data.getNama());
        holder.txtsiapa.setText(data.getSiapa());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnama,txtsiapa;
        Button addButton;


        public ViewHolder(View itemView) {
            super(itemView);
            txtnama = (TextView)itemView.findViewById(R.id.txtnama);
            txtsiapa = (TextView)itemView.findViewById(R.id.txtsiapa);


        }
    }
}
