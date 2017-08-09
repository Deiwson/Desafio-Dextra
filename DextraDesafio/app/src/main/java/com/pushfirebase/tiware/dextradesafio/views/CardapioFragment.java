package com.pushfirebase.tiware.dextradesafio.views;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pushfirebase.tiware.dextradesafio.R;
import com.pushfirebase.tiware.dextradesafio.adapters.CardapioAdapter;
import com.pushfirebase.tiware.dextradesafio.models.Lanche;
import com.pushfirebase.tiware.dextradesafio.presenters.CardapioPresenter;
import com.pushfirebase.tiware.dextradesafio.util.BtnClickListener;

import java.util.List;


public class CardapioFragment extends Fragment implements CardapioPresenter.View{

    private OnFragmentInteractionListener mListener;

    private CardapioPresenter cardapioPresenter;

    private CardapioAdapter adapter;

    private Context context;

    public CardapioFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = inflater.getContext();

        View view = inflater.inflate(R.layout.fragment_cardapio, container, false);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.context);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        RecyclerView recyclerView = view.findViewById(R.id.cardapioRV);
        adapter = new CardapioAdapter(context, new BtnClickListener() {
            @Override
            public void onBtnClick(int position) {

                cardapioPresenter.addIng(position);

            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        cardapioPresenter = new CardapioPresenter(this,context);
        cardapioPresenter.initData();


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void addDatas(List<Lanche> datas) {

        for (Lanche lanche:datas) {

            adapter.add(lanche);
            adapter.update();
        }

    }

    @Override
    public void addIng() {

        adapter.update();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
