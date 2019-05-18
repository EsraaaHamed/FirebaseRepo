package com.example.firebasedemo.Model;

import java.io.Serializable;

public class NoteBean implements Serializable {
    String noteTitle;
    String body;
    public NoteBean()
    {

    }
    public NoteBean(String noteTitle, String body) {
        this.noteTitle = noteTitle;
        this.body = body;
    }

    @Override
    public String toString() {
       return noteTitle;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
