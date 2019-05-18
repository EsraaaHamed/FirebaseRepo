package com.example.firebasedemo.Screens.AddNodeScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebasedemo.Model.NoteBean;
import com.example.firebasedemo.R;
import com.example.firebasedemo.Screens.NotesListScreen.ReadingWritingToRealDb;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPage extends AppCompatActivity implements AddNodeContarct.AddNodeView{
    Button btnNext;
    Button btnClose;
    EditText noteTitle;
    EditText body;
    AddNodeContarct.AddNodePresenter presenter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnNext= (Button) findViewById(R.id.btnNext);
        btnClose=(Button) findViewById(R.id.Close);
        noteTitle=(EditText) findViewById(R.id.editNoteTitle);
        body=(EditText) findViewById(R.id.editBody) ;
        presenter=new AddNodePresenterImp(this);
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

               // writeNewNote(noteTitle.getText().toString(),body.getText().toString());
                presenter.writeNewNote(noteTitle.getText().toString(),body.getText().toString());
                Intent noteList = new Intent(LoginPage.this, ReadingWritingToRealDb.class);
                startActivity(noteList);

            }
        });

    }
   /* private void writeNewNote( String title, String body) {
        NoteBean note = new NoteBean(title, body);

       // String email=user.getEmail();
        databaseReference.child(databaseReference.push().getKey()).setValue(note);
        //databaseReference.child(databaseReference.push().getKey()).setValue(note);
        Intent noteList = new Intent(LoginPage.this, ReadingWritingToRealDb.class);
        startActivity(noteList);
    }*/

    public void close(View view) {
        finish();

    }

}
