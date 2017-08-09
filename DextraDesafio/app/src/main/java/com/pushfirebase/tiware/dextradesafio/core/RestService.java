package com.pushfirebase.tiware.dextradesafio.core;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pushfirebase.tiware.dextradesafio.R;
import com.pushfirebase.tiware.dextradesafio.enuns.ServerUrlEnun;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestService extends AsyncTask<Object, String, Void> {


    private String urlBase;

    protected String messageProgress;

    protected Context context;

    private ProgressDialog progress;

    protected boolean showProgress;

    protected RestServicePreProcessListener preProcess;

    private Handler searchDelay;

    private Object[] params;


    public RestService(Context context, String service){

        this.context = context;

        messageProgress = context.getText(R.string.loading).toString();

        searchDelay = new Handler();

        urlBase = ServerUrlEnun.API_IP.getCodigo();
    }


    /**
     *
     * params[0] = callback
     * params[1] = servico
     * params[2] = parametro
     * params[3] = metodo
     * params[4] = classe de retorno
     *
     * @param params
     * @return
     */
    @Override
    protected Void doInBackground(final Object... params) {

        this.params = params;

        execute();

        return null;
    }




    private String execute(){

        String result = "";

        JsonContainer container = new JsonContainer();
        container.setSuccess(Boolean.FALSE);

        try {

            if(preProcess != null){
                preProcess.process();
                preProcess = null;
            }

            final RestServiceListener callback = (RestServiceListener) params[0];

            if(params[3] != null && params[3].equals(ServiceMethodEnum.POST)){

                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(params[2]);
            }

            String url = (String) params[1];
            result = executeRequest(url);

            Gson gson = new Gson();
            Object obj =  gson.fromJson(result, (Class<?>) params[4]);

            if(params.length > 4){

                if(params[4] != String.class && params[4] != Boolean.class){

                    int y = 0;
                }
            }

            if(showProgress){

                progress.dismiss();
            }

            searchDelay.removeCallbacks(search);

            if(callback != null){

                container.setSuccess(Boolean.TRUE);

                callback.processCallback(container,obj);
            }

        }
        catch (Exception e){

            e.printStackTrace();
        }

        return result;
    }


    private String   executeRequest(String urlString){

        HttpURLConnection urlConnection = null;
        String result = "";


        try {

            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in);

        } catch (Exception e){

            e.printStackTrace();

        }finally {

            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return  result;
    }

    private String readStream(InputStream in) throws IOException {

        BufferedReader r = new BufferedReader(new InputStreamReader(in));

        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line).append('\n');
        }

        return  total.toString();

    }



    @Override
    protected void onPreExecute() {

        progress = new ProgressDialog(context);

        searchDelay.postDelayed(search, 1000);
    }


    protected void onPostExecute(Void aVoid) {


    }

    private Runnable search = new Runnable(){

        @Override
        public void run(){

            if(showProgress){

                try {
                    progress.setCanceledOnTouchOutside(false);

                    progress.setMessage(messageProgress);

                    progress.show();
                }
                catch (Exception e){
                    //nada
                }
            }
        }
    };

    public String getUrlBase(){

        return urlBase;
    }



    private Runnable tryAgain = new Runnable(){

        @Override
        public void run(){

            execute();
        }
    };

}
