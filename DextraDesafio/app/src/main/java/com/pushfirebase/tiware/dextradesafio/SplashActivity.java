package com.pushfirebase.tiware.dextradesafio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pushfirebase.tiware.dextradesafio.models.Ingrediente;
import com.pushfirebase.tiware.dextradesafio.models.Lanche;
import com.pushfirebase.tiware.dextradesafio.core.JsonContainer;
import com.pushfirebase.tiware.dextradesafio.core.RestServiceListener;
import com.pushfirebase.tiware.dextradesafio.task.IngredienteService;
import com.pushfirebase.tiware.dextradesafio.task.LancheService;
import com.pushfirebase.tiware.dextradesafio.util.Preferences;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar splashProgress;

    private TextView  splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashText = (TextView) findViewById(R.id.splashText);
        splashProgress = (ProgressBar) findViewById(R.id.splashProgress);


        new IngredienteService(this).list(new RestServiceListener<Ingrediente[]>() {

            @Override
            public void processCallback(JsonContainer resultado, Ingrediente[] ings) {

                if(resultado.getSuccess()){

                    Preferences.getInstance(SplashActivity.this).setLisIng(ings);

                    runThread();

                }else{

                    callNetWorkError();

                }
            }
        });

    }

    private void runThread(){
        runOnUiThread (new Thread(new Runnable() {
            public void run() {
                callLanche();
            }
        }));
    }

    private void callLanche(){

        new LancheService(this).list(new RestServiceListener<Lanche[]>() {

            @Override
            public void processCallback(JsonContainer resultado, Lanche[] lncs) {

                if(resultado.getSuccess()){

                    Preferences.getInstance(SplashActivity.this).setListLanche(lncs);
                    openApp();

                }else{

                    callNetWorkError();

                }
            }
        });

    }

    private void callNetWorkError(){


        splashText.setText(R.string.loadingError);
        splashProgress.setIndeterminate(Boolean.FALSE);
        splashProgress.setVisibility(View.GONE);


    }


    private void openApp(){

        startActivity(new Intent(SplashActivity.this,BaseActivity.class));
        finish();
    }

}
