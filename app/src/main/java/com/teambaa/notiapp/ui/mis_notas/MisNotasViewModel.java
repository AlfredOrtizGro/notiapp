package com.teambaa.notiapp.ui.mis_notas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MisNotasViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public MisNotasViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("MIS NOTAS");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}