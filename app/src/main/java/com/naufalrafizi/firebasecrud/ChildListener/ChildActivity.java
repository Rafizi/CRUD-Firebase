package com.naufalrafizi.firebasecrud.ChildListener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.naufalrafizi.firebasecrud.Mahasiswa;
import com.naufalrafizi.firebasecrud.R;

import java.util.ArrayList;
import java.util.List;

public class ChildActivity extends AppCompatActivity {

    EditText namaEditText,siapaEditText;
    Button addButton;
    public static TextView tvEmptyView;
    RecyclerView recyclerView;
    ChildAdapter myAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        creatingLayout();
    }

    private void creatingLayout() {
        tvEmptyView = (TextView) findViewById(R.id.tvEmptyView);
        namaEditText = (EditText) findViewById(R.id.nameEditText);
        siapaEditText=(EditText) findViewById(R.id.siapaEditText);
        addButton = (Button) findViewById(R.id.addButton);
        recyclerView=(RecyclerView) findViewById(R.id.recylerview_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter= new ChildAdapter(this);
        updateAdapter();
        recyclerView.setAdapter(myAdapter);
    }

    public void btnaddOnclick (View v){
        String nama = namaEditText.getText().toString();
        String siapa = siapaEditText.getText().toString();

        Mahasiswa mahasiswa = new Mahasiswa(nama,siapa);

        if (TextUtils.isEmpty(nama)){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();

            return;
        }
        if (TextUtils.isEmpty(siapa)){
            Toast.makeText(this, "Please enter siapa", Toast.LENGTH_SHORT).show();

            return;
        }
        updatedatauser(mahasiswa);
    }

    private void updatedatauser(Mahasiswa mahasiswa) {
        databaseReference.child("mahasiswa").push().setValue(mahasiswa);

        namaEditText.setText(null);
        siapaEditText.setText(null);

        updateAdapter();
    }

    private void updateAdapter() {
        final List<Mahasiswa>mahasiswaList = new ArrayList<>();

        databaseReference.child("mahasiswa").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Mahasiswa siswa = new Mahasiswa();
//                siswa.setNama(dataSnapshot.getKey());
//                siswa.setSiapa(dataSnapshot.getValue().toString());

//                adapter.refill(siswa);

                mahasiswaList.add(dataSnapshot.getValue(Mahasiswa.class));
                displayuser(mahasiswaList);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void displayuser(List<Mahasiswa>ls) {
        tvEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        namaEditText.setText(null);
        siapaEditText.setText(null);
        myAdapter.setData(ls);
        myAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
