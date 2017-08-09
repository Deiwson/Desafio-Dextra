package com.pushfirebase.tiware.dextradesafio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by korea on 05/08/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lanche implements Serializable {

    private int id;

    private String name;

    private int[] ingredients;

    private String image;

    private double preco;

    private List<Ingrediente> listIngs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(int[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Ingrediente> getListIngs() {
        return listIngs;
    }

    public void setListIngs(List<Ingrediente> listIngs) {
        this.listIngs = listIngs;
    }
}