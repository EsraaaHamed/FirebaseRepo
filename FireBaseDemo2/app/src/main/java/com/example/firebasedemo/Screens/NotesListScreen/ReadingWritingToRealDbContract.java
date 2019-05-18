package com.example.firebasedemo.Screens.NotesListScreen;

import com.example.firebasedemo.Model.NoteBean;

public interface ReadingWritingToRealDbContract {
    interface ReadingWritingView
    {
        void clearAdapter();
        void addToAdpater(NoteBean noteBean);
        void notifyAndSetAdapapter();
    }
    interface ReadingWritingPresenter
    {
        void readingDBToAdapte();

    }
}
