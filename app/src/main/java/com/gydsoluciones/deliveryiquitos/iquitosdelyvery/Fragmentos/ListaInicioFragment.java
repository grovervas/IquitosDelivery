package com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.R;

public class ListaInicioFragment extends Fragment {


   // private OnFragmentInteractionListener mListener;

    public ListaInicioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_inicio, container, false);
    }

}
