package com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Adapters.CategoriaAdapter;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.Categorias;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.ServiciosRest;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.LoginActivity;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.MainActivity;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaCategoriaFragment extends Fragment implements Response.Listener<JSONArray>, Response.ErrorListener{

    private RecyclerView recyclerCategoria;
    private ServiciosRest serviciosRest = new ServiciosRest();

    ArrayList<Categorias> listaCategorias;
    ProgressDialog dialog;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;


    public ListaCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_categoria, container, false);
        listaCategorias = new ArrayList<>();
        recyclerCategoria = (RecyclerView)view.findViewById(R.id.recycler_categoria);
        recyclerCategoria.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCategoria.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());
        cargarServicioWeb();
        return view;
    }

    private void cargarServicioWeb() {

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Procesando...");
        dialog.show();

        String url = serviciosRest.getUrlCategorias();
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null, this,this);
        //jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        //request.add(jsonObjectRequest);
        request.add(jsonArrayRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getContext(),"No se puede conectar",Toast.LENGTH_SHORT).show();
        dialog.hide();
        Log.d("LOG_VOLLEY", error.toString());

    }

    @Override
    public void onResponse(JSONArray response) {

        Categorias categorias=null;
        JSONArray json = response;
        try{
            for(int i=0;i<json.length();i++){
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                Log.i("Imagen:",jsonObject.optString("imagen"));
                categorias = new Categorias(jsonObject.optInt("id"),jsonObject.optString("descripcion"),jsonObject.optString("imagen"));
                listaCategorias.add(categorias);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        dialog.hide();
        CategoriaAdapter adapter = new CategoriaAdapter(listaCategorias, getContext());
        recyclerCategoria.setAdapter(adapter);
    }
}
