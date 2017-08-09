package com.pushfirebase.tiware.dextradesafio.task;

import android.content.Context;

import com.pushfirebase.tiware.dextradesafio.models.Lanche;
import com.pushfirebase.tiware.dextradesafio.core.RestService;
import com.pushfirebase.tiware.dextradesafio.core.RestServiceListener;
import com.pushfirebase.tiware.dextradesafio.core.ServiceMethodEnum;



public class LancheService extends RestService {


    public LancheService(Context context){

        super(context, "lanche");
    }


    public void list(RestServiceListener callback){

        try {

            showProgress = false;

            StringBuilder urlServico = new StringBuilder();

            urlServico.append(getUrlBase());

            urlServico.append("lanche");

            execute(callback, urlServico.toString(), null, ServiceMethodEnum.GET, Lanche[].class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void loadLanche(String idLanche, String language, RestServiceListener callback){

        try {

            showProgress = false;

            StringBuilder urlServico = new StringBuilder();

            urlServico.append("loadLanche/");

            urlServico.append(idLanche);

            urlServico.append("/");

            urlServico.append(language);

            execute(callback, urlServico.toString(), null, ServiceMethodEnum.GET, Lanche.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
