package com.teambaa.notiapp.ui.eliminar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EliminarViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public EliminarViewModel()
    {
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}