package com.pushfirebase.tiware.dextradesafio.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pushfirebase.tiware.dextradesafio.R;
import com.pushfirebase.tiware.dextradesafio.models.Ingrediente;
import com.pushfirebase.tiware.dextradesafio.models.Lanche;
import com.pushfirebase.tiware.dextradesafio.util.BtnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korea on 05/08/17.
 */

public class CardapioAdapter  extends RecyclerView.Adapter<CardapioAdapter.ViewHolder> {

 private List<Lanche> items;
    private Context context;
    private BtnClickListener mClickListener = null;

    public CardapioAdapter(Context context,BtnClickListener listener) {

        this.items = new ArrayList<>();
        this.context = context;
        this.mClickListener = listener;
    }

    public void add(Lanche l){

        items.add(l);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, final int position) {
        Lanche item = items.get(position);
        holder.lancheNome.setText(item.getName());
        holder.lanchePreco.setText(item.getPreco()+"");

        holder.lancheIngredientesAdd.setId(item.getId());
        holder.lancheIngredientesAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mClickListener != null)
                    mClickListener.onBtnClick(position);
            }
        });

        StringBuilder builder = new StringBuilder();
        for (Ingrediente i: item.getListIngs()) {

            builder.append(i.getName()+";");

        }

        holder.lancheIngredientes.setText(builder.toString());

        Glide.with(context)
                .load(Uri.parse(item.getImage()))
                .into(holder.lancheImage);
        holder.itemView.setTag(item);
     }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void update(){
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lancheNome;
        public TextView lanchePreco;
        public TextView lancheIngredientes;
        public ImageView lancheImage;
        public ImageButton lancheIngredientesAdd;
        public ViewHolder(View itemView) {
            super(itemView);
            lancheNome = (TextView) itemView.findViewById(R.id.lancheNome);
            lanchePreco = (TextView) itemView.findViewById(R.id.lanchePreco);
            lancheIngredientes = (TextView) itemView.findViewById(R.id.lancheIngredientes);
            lancheImage = (ImageView) itemView.findViewById(R.id.lancheImage);
            lancheIngredientesAdd = (ImageButton) itemView.findViewById(R.id.lancheIngredientesAdd);

        }

    }

}
