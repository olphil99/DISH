package com.example.dish.ui.createPost;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class createPostViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public createPostViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is create post fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

