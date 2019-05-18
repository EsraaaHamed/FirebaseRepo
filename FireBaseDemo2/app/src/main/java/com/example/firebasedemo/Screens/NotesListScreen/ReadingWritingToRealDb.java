package com.example.firebasedemo.Screens.NotesListScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.firebasedemo.Screens.AddNodeScreen.LoginPage;
import com.example.firebasedemo.Model.NoteBean;
import com.example.firebasedemo.Screens.NoteDetailesScreen.NoteDetailes;
import com.example.firebasedemo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadingWritingToRealDb extends AppCompatActivity implements ReadingWritingToRealDbContract.ReadingWritingView {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    ReadingWritingToRealDbContract.ReadingWritingPresenter presenter;
    ArrayAdapter<NoteBean> notesAdapter;
    ListView notesListView;
    Button addNewNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_writing_to_real_db);
        firebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String userid=user.getUid();
        databaseReference = firebaseDatabase.getReference("Notes").child(userid);
        notesListView=(ListView)findViewById(R.id.notesListView);
        addNewNote=(Button)findViewById(R.id.addNewNote);
        notesAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        presenter= new ReadingWritingToRealPressenterImp(this,this);
        presenter.readingDBToAdapte();

        /////////////////////////////////////////////////////////////


        addNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noteInt = new Intent(ReadingWritingToRealDb.this, LoginPage.class);
                startActivity(noteInt);
            }
        });
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent noteList = new Intent(ReadingWritingToRealDb.this, NoteDetailes.class);
                noteList.putExtra("NoteBean",(NoteBean)parent.getItemAtPosition(position));
                startActivity(noteList);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void addToAdpater(NoteBean noteBean) {
        notesAdapter.add(noteBean);
    }

    @Override
    public void notifyAndSetAdapapter() {
        notesAdapter.notifyDataSetChanged();
        notesListView.setAdapter(notesAdapter);
    }

    @Override
    public void clearAdapter() {
        notesAdapter.clear();
    }
}
