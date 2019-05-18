package com.example.firebasedemo.Screens.NoteDetailesScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebasedemo.Model.NoteBean;
import com.example.firebasedemo.R;
import com.example.firebasedemo.Screens.NotesListScreen.ReadingWritingToRealDb;

public class NoteDetailes extends AppCompatActivity {
    EditText noteTitle;
    EditText body;
    Button btnClose;
    Intent intent;
    String noteTitleStr;
    String bodystr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detailes);
        noteTitle= (EditText)findViewById(R.id.noteTitleTwo);
        body=(EditText) findViewById(R.id.bodyTwo);
        btnClose=(Button)findViewById(R.id.closeBtn);
        intent=getIntent();
        NoteBean noteBean=(NoteBean)intent.getSerializableExtra("NoteBean");
        noteTitleStr=noteBean.getNoteTitle();
        noteTitle.setText(noteBean.getNoteTitle());
        body.setText(noteBean.getBody());


    }

    public void close(View view) {

        Intent noteList = new Intent(NoteDetailes.this, ReadingWritingToRealDb.class);

        startActivity(noteList);

    }
}
