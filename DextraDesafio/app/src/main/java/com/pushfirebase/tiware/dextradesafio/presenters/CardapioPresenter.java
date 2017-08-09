package com.pushfirebase.tiware.dextradesafio.presenters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.pushfirebase.tiware.dextradesafio.R;
import com.pushfirebase.tiware.dextradesafio.models.Ingrediente;
import com.pushfirebase.tiware.dextradesafio.models.Lanche;
import com.pushfirebase.tiware.dextradesafio.util.IngredienteAdd;
import com.pushfirebase.tiware.dextradesafio.util.Preferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korea on 05/08/17.
 */

public class CardapioPresenter {

    private View view;
    private Context context;
    private  List<Lanche> datas;
    private Dialog dialog;

    public CardapioPresenter(View view,Context context ) {

        this.view = view;
        this.context = context;
    }

    public void initData() {
        datas = Preferences.getInstance(context).getLancheLIst();

        for (Lanche l:datas) {

            l.setListIngs(new ArrayList<Ingrediente>());

            for (int i:l.getIngredients()) {

                Ingrediente ing = Preferences.getInstance(context).getIngPorId(i);
                if(ing != null){

                    l.getListIngs().add(ing);
                    l.setPreco(l.getPreco()+ing.getPrice());
                }

            }

        }

        view.addDatas(datas);
    }

    public void openModal(int position){

        dialog = new Dialog(context);

        dialog.setContentView(R.layout.dialog);

        ImageView imgDialog = dialog.findViewById(R.id.imgDialog);

        Lanche lanche = datas.get(position);

        dialog.setTitle(lanche.getName());

        Glide.with(context)
                .load(Uri.parse(lanche.getImage()))
                .into(imgDialog);

        dialog.show();
    }


    public void addIng(int position){

        openModal(position);
    }

    public interface View {
        void addDatas(List<Lanche> datas);
        void addIng();
    }
}