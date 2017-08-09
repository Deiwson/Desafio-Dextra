package com.pushfirebase.tiware.dextradesafio.core;

/**
 * Created by diegobellimondego on 19/04/15.
 */
public interface RestServiceListener<O> {

    void processCallback(JsonContainer resultado, O dados);
}
