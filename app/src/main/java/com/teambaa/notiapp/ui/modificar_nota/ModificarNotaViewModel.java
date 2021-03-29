package com.teambaa.notiapp.ui.modificar_nota;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModificarNotaViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public ModificarNotaViewModel()
    {

    }

    public LiveData<String> getText()
    {
        return mText;
    }
}