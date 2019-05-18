package com.example.firebasedemo.Screens.NotesListScreen;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.firebasedemo.Model.NoteBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadingWritingToRealPressenterImp implements ReadingWritingToRealDbContract.ReadingWritingPresenter
{
        private FirebaseDatabase firebaseDatabase;
        private DatabaseReference databaseReference;
        ReadingWritingToRealDbContract.ReadingWritingView readingWritingView;
        Activity activity;

    public ReadingWritingToRealPressenterImp(ReadingWritingToRealDbContract.ReadingWritingView readingWritingView) {
        this.readingWritingView=readingWritingView;
        firebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String userid=user.getUid();
        databaseReference = firebaseDatabase.getReference("Notes").child(userid);
    }

    public ReadingWritingToRealPressenterImp(ReadingWritingToRealDbContract.ReadingWritingView readingWritingView, Activity activity) {
        this.readingWritingView=readingWritingView;
        this.activity=activity;
        firebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String userid=user.getUid();
        databaseReference = firebaseDatabase.getReference("Notes").child(userid);

    }


    @Override
    public void readingDBToAdapte() {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readingWritingView.clearAdapter();

                NoteBean reterivedNote;

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    String body=snapshot.child("body").getValue().toString();
                    String title=snapshot.child("noteTitle").getValue().toString();
                    reterivedNote= new NoteBean(title,body);
                    readingWritingView.addToAdpater(reterivedNote);

                }
                readingWritingView.notifyAndSetAdapapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
