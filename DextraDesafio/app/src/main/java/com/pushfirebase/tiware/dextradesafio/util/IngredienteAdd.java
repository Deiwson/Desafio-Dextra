package com.pushfirebase.tiware.dextradesafio.util;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pushfirebase.tiware.dextradesafio.models.Ingrediente;
import com.pushfirebase.tiware.dextradesafio.presenters.CardapioPresenter;

/**
 * Created by korea on 06/08/17.
 */

public class IngredienteAdd extends LinearLayout {

    private TextView textView ;
    private boolean isSelectd;
    private Ingrediente ingrediente;

    public IngredienteAdd(Ingrediente ingrediente, Context context,boolean isSelectd){
        super(context);

        this.isSelectd = isSelectd;
        textView = new TextView(context);
        textView.setText(ingrediente.getName());
        this.addView(textView);
        this.ingrediente = ingrediente;

    }

    public void removeView(LinearLayout view){

        view.removeView(this);

    }

    public boolean isSelectd() {
        return isSelectd;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }
}
