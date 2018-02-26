package com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.Categorias;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.VolleySingleton;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.R;

import java.util.ArrayList;

/**
 * Created by grva on 21/02/2018.
 */

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    ArrayList<Categorias> listaCategorias;
    Context context;

    public CategoriaAdapter(ArrayList<Categorias> listaCategorias, Context context){
        this.listaCategorias = listaCategorias;
        this.context = context;
    }

    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_categorias,null,false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, int position) {
        holder.tvCategoria.setText(listaCategorias.get(position).getNombre());
        if(listaCategorias.get(position).getImagenUrl()!=null){
            //holder.imgCategoria.setImageBitmap(listaCategorias.get(position).getImagenUrl());
            cargarImagenWebService(listaCategorias.get(position).getImagenUrl(),holder);
        }else{
            holder.imgCategoria.setImageResource(R.mipmap.ic_launcher);
        }
        //imageLoader.DisplayImage(listaCategorias.get(position).getImagenUrl(),holder.imgCategoria);
    }

    private void cargarImagenWebService(String rutaImagen, final CategoriaViewHolder holder) {

        //String ip = context.getString(R.string.ip);

        String urlImagen= rutaImagen;
        urlImagen=urlImagen.replace(" ","%20");

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imgCategoria.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error al cargar la imagen",Toast.LENGTH_SHORT).show();
            }
        });
        //request.add(imageRequest);
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(imageRequest);
    }



    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder{
        TextView tvCategoria;
        ImageView imgCategoria;


        public CategoriaViewHolder(View itemView){
            super(itemView);
            tvCategoria = (TextView)itemView.findViewById(R.id.tvCategoria);
            imgCategoria = (ImageView)itemView.findViewById(R.id.imgCategoria);
        }
    }
}
