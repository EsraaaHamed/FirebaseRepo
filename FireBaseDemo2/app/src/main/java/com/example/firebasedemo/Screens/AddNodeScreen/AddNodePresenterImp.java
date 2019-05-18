package com.example.firebasedemo.Screens.AddNodeScreen;

import android.content.Intent;

import com.example.firebasedemo.Model.NoteBean;
import com.example.firebasedemo.Screens.NotesListScreen.ReadingWritingToRealDb;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNodePresenterImp implements AddNodeContarct.AddNodePresenter {
    AddNodeContarct.AddNodeView addNodeView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public AddNodePresenterImp(AddNodeContarct.AddNodeView addNodeView) {
        this.addNodeView=addNodeView;
        firebaseDatabase= FirebaseDatabase.getInstance();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid=user.getUid();
        databaseReference = firebaseDatabase.getReference("Notes").child(userid);
    }


    @Override
    public void writeNewNote(String title, String body) {
        NoteBean note = new NoteBean(title, body);
        databaseReference.child(databaseReference.push().getKey()).setValue(note);

    }
}
