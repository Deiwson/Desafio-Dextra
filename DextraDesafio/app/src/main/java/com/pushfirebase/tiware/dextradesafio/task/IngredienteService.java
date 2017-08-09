package com.pushfirebase.tiware.dextradesafio.task;

import android.content.Context;

import com.pushfirebase.tiware.dextradesafio.models.Ingrediente;
import com.pushfirebase.tiware.dextradesafio.core.RestService;
import com.pushfirebase.tiware.dextradesafio.core.RestServiceListener;
import com.pushfirebase.tiware.dextradesafio.core.ServiceMethodEnum;



public class IngredienteService extends RestService {


    public IngredienteService(Context context){

        super(context, "ingrediente");
    }


    public void list(RestServiceListener callback){

        try {

            showProgress = false;

            StringBuilder urlServico = new StringBuilder();

            urlServico.append(getUrlBase());

            urlServico.append("ingrediente");

            execute(callback, urlServico.toString(), null, ServiceMethodEnum.GET, Ingrediente[].class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void loadIngrediente(String idIngrediente, String language, RestServiceListener callback){

        try {

            showProgress = false;

            StringBuilder urlServico = new StringBuilder();

            urlServico.append("loadIngrediente/");

            urlServico.append(idIngrediente);

            urlServico.append("/");

            urlServico.append(language);

            execute(callback, urlServico.toString(), null, ServiceMethodEnum.GET, Ingrediente.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
