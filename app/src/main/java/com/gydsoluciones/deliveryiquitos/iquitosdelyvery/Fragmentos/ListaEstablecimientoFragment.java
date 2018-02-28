package com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.android.volley.toolbox.Volley;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Adapters.CategoriaAdapter;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Adapters.EstablecimientoAdapter;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.Categorias;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.Establecimiento;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.ServiciosRest;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListaEstablecimientoFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    private RecyclerView recyclerEstablecimiento;
    private ServiciosRest serviciosRest = new ServiciosRest();

    ArrayList<Establecimiento> listaEstablecimientos;
    ProgressDialog dialog;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    int codigoCategoria;


    public ListaEstablecimientoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_establecimiento, container, false);

        codigoCategoria = getArguments().getInt("categoria");

        listaEstablecimientos = new ArrayList<>();
        recyclerEstablecimiento = (RecyclerView)view.findViewById(R.id.recycler_establecimiento);
        recyclerEstablecimiento.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerEstablecimiento.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());
        cargarServicioWeb();

        return view;
    }

    private void cargarServicioWeb() {

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Procesando...");
        dialog.show();

        String url = serviciosRest.getUrlEstablecimiento() + codigoCategoria;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getContext(),"No se puede conectar",Toast.LENGTH_SHORT).show();
        dialog.hide();
        Log.d("LOG_VOLLEY", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {

        Establecimiento establecimiento = null;
        try{
            JSONArray json = response.getJSONArray("establecimiento");
            Log.i("Delivery",json.toString());
            for(int i=0;i<json.length();i++){
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                Log.i("Imagen:",jsonObject.optString("imagen"));
                establecimiento = new Establecimiento(jsonObject.optInt("id"),jsonObject.optString("descripcion"),jsonObject.optString("imagen"));
                listaEstablecimientos.add(establecimiento);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        dialog.hide();
        EstablecimientoAdapter adapter = new EstablecimientoAdapter(listaEstablecimientos, getContext());
        recyclerEstablecimiento.setAdapter(adapter);
    }

}
