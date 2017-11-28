package com.naufalrafizi.firebasecrud;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recycler;
    ArrayList<Mahasiswa> list;
    DatabaseReference databaseReference;
    RecyleviewAdapter adapter;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager LM = new LinearLayoutManager(getApplicationContext());
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(LM);


        list = new ArrayList<>();



        databaseReference = FirebaseDatabase.getInstance().getReference();

        // get reference to 'users' node

        // store app title to 'app_title' node
        databaseReference.child("siswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<Mahasiswa>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                       Mahasiswa value = child.getValue(Mahasiswa.class);
                       list.add(value);

                    }
                    adapter = new RecyleviewAdapter(list);
                    recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to read app title value.", databaseError.toException());
            }
        });

    }

}
