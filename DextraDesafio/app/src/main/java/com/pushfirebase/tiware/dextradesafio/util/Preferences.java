package com.pushfirebase.tiware.dextradesafio.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pushfirebase.tiware.dextradesafio.models.Ingrediente;
import com.pushfirebase.tiware.dextradesafio.models.Lanche;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by korea on 05/08/17.
 */

public class Preferences {

    private static Preferences instance;
    private static Context context;

    private String PREFENCES = "pref";
    private String INGREDIENTE = "ings";
    private String LANCHE = "lanche";

    public static Preferences getInstance(Context context) {
        if (instance == null) {
            instance = new Preferences();
        }
        Preferences.context = context;
        return instance;
    }

    public void setLisIng(Ingrediente [] list){

        Gson gson = new Gson();
        String vals = gson.toJson(list);

        SharedPreferences.Editor editor = context.getSharedPreferences(PREFENCES,0).edit();
        editor.putString(INGREDIENTE, vals);
        editor.commit();

    }

    public List<Ingrediente> getIngredienteLIst(){

        SharedPreferences mysettings= context.getSharedPreferences(PREFENCES, 0);
        String IngList  = mysettings.getString(INGREDIENTE, "");

        Type listType = new TypeToken<ArrayList<Ingrediente>>(){}.getType();

        List<Ingrediente> list = new Gson().fromJson(IngList, listType);

        return list;
    }

    public Ingrediente getIngPorId(int id){

        List<Ingrediente> list = getIngredienteLIst();

        for (Ingrediente i:list) {

            if(i.getId() == id){

                return i;

            }

        }

        return  null;
    }


    public void setListLanche(Lanche[] list){

        Gson gson = new Gson();
        String vals = gson.toJson(list);

        SharedPreferences.Editor editor = context.getSharedPreferences(PREFENCES,0).edit();
        editor.putString(LANCHE, vals);
        editor.commit();

    }

    public List<Lanche> getLancheLIst(){

        SharedPreferences mysettings= context.getSharedPreferences(PREFENCES, 0);
        String IngList  = mysettings.getString(LANCHE, "");

        Type listType = new TypeToken<ArrayList<Lanche>>(){}.getType();

        List<Lanche> list = new Gson().fromJson(IngList, listType);

        return list;
    }

}
