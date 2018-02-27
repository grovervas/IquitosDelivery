package com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.Categorias;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.VolleySingleton;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Fragmentos.ListaCategoriaFragment;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Fragmentos.ListaEstablecimientoFragment;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Interfaces.ItemClickListener;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.MainActivity;
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
    public void onBindViewHolder(final CategoriaViewHolder holder, int position) {
        //holder.id = listaCategorias.get(position).getId_categoria();
        holder.tvCategoria.setText(listaCategorias.get(position).getNombre());
        if(listaCategorias.get(position).getImagenUrl()!=null){
            cargarImagenWebService(listaCategorias.get(position).getImagenUrl(),holder);
        }else{
            holder.imgCategoria.setImageResource(R.mipmap.ic_launcher);
        }
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Toast.makeText(context,listaCategorias.get(position).getId_categoria(),Toast.LENGTH_SHORT);
                //Log.i("DeliveryIQT","valor:" + listaCategorias.get(position).getId_categoria());
                Bundle args = new Bundle();
                args.putInt("categoria",listaCategorias.get(position).getId_categoria());
                FragmentManager fragmentManager = ((Activity)context).getFragmentManager();
                Fragment fragment = new ListaEstablecimientoFragment();
                fragment.setArguments(args);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_principal,fragment);
                transaction.commit();
            }
        });
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

    public class CategoriaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        //int id;
        TextView tvCategoria;
        ImageView imgCategoria;

        private ItemClickListener itemClickListener;

        public CategoriaViewHolder(View itemView){
            super(itemView);
            //this.id = 0;
            tvCategoria = (TextView)itemView.findViewById(R.id.tvCategoria);
            imgCategoria = (ImageView)itemView.findViewById(R.id.imgCategoria);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true);
            return true;
        }
    }
}
