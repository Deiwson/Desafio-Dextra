package com.pushfirebase.tiware.dextradesafio.enuns;

/**
 * Created by korea on 05/08/17.
 */

public enum ServerUrlEnun {


    API_IP("http://000.000.0.0:8080/api/");

    private String codigo;

    public String getCodigo() {
        return codigo;
    }


    ServerUrlEnun(String s) {

        this.codigo = s;
    }
}
