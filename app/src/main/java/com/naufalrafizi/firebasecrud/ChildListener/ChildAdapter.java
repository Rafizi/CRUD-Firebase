package com.naufalrafizi.firebasecrud.ChildListener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naufalrafizi.firebasecrud.Mahasiswa;
import com.naufalrafizi.firebasecrud.MainActivity;
import com.naufalrafizi.firebasecrud.R;
import com.naufalrafizi.firebasecrud.RecyleviewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/22/2017.
 */

public class ChildAdapter  extends RecyclerView.Adapter<ChildAdapter.ViewHolder>  {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<Mahasiswa> mahasiswaList= new ArrayList<>();

    public ChildAdapter(Context context) {

    }

    public void setData(List<Mahasiswa>mahasiswa){
        mahasiswaList.clear();
        mahasiswaList.addAll(mahasiswa);
        notifyDataSetChanged();
    }
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChildAdapter.ViewHolder holder, final int position) {

        final Mahasiswa mahasiswa = mahasiswaList.get(position);

        holder.tvnama.setText(mahasiswa.getNama());
        holder.tvsiapa.setText(mahasiswa.getSiapa());
        holder.deleteButton.setText("delete");
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("mahasiswa").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Mahasiswa mahasiswaTemp = snapshot.getValue(Mahasiswa.class);
                            if (mahasiswa.getNama().equals(mahasiswaTemp.getNama())){
                                databaseReference.child("mahasiswa").child(snapshot.getKey().toString()).removeValue();
                                mahasiswaList.remove(position);
                                notifyDataSetChanged();
                                if (mahasiswaList.size()==0){
                                    ChildActivity.tvEmptyView.setVisibility(View.VISIBLE);
                                }
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnama,tvsiapa;
        Button deleteButton;
        public ViewHolder(View itemView) {
            super(itemView);
            tvnama = (TextView)itemView.findViewById(R.id.tvNama);
            tvsiapa = (TextView)itemView.findViewById(R.id.tvSiapa);
            deleteButton = (Button)itemView.findViewById(R.id.deleteButton);
        }
    }
}
