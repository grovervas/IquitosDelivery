package com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.Establecimiento;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Clases.VolleySingleton;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Interfaces.ItemClickListener;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.R;

import java.util.ArrayList;

/**
 * Created by grva on 26/02/2018.
 */

public class EstablecimientoAdapter extends RecyclerView.Adapter<EstablecimientoAdapter.EstablecimientoViewHolder> {

    ArrayList<Establecimiento> listaEstablecimientos;
    Context context;

    public EstablecimientoAdapter(ArrayList<Establecimiento> listaEstablecimientos, Context context){
        this.listaEstablecimientos = listaEstablecimientos;
        this.context = context;
    }

    @Override
    public EstablecimientoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_establecimientos,null,false);
        return new EstablecimientoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EstablecimientoViewHolder holder, int position) {
        //holder.id = listaCategorias.get(position).getId_categoria();
        holder.idDescEstablecimiento.setText(listaEstablecimientos.get(position).getNombre());
        if(listaEstablecimientos.get(position).getImagen()!=null){
            cargarImagenWebService(listaEstablecimientos.get(position).getImagen(),holder);
        }else{
            holder.idImgEstablecimiento.setImageResource(R.mipmap.ic_launcher);
        }
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Toast.makeText(context,listaCategorias.get(position).getId_categoria(),Toast.LENGTH_SHORT);
                Log.i("DeliveryIQT","valor:" + listaEstablecimientos.get(position).getId_establecimiento());
            }
        });
    }


    private void cargarImagenWebService(String rutaImagen, final EstablecimientoViewHolder holder) {

        //String ip = context.getString(R.string.ip);

        String urlImagen= rutaImagen;
        urlImagen=urlImagen.replace(" ","%20");

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.idImgEstablecimiento.setImageBitmap(response);
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
        return listaEstablecimientos.size();
    }

    public class EstablecimientoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        //int id;
        TextView idDescEstablecimiento;
        ImageView idImgEstablecimiento;

        private ItemClickListener itemClickListener;

        public EstablecimientoViewHolder(View itemView){
            super(itemView);
            //this.id = 0;
            idDescEstablecimiento = (TextView)itemView.findViewById(R.id.idDescEstablecimiento);
            idImgEstablecimiento = (ImageView)itemView.findViewById(R.id.idImgEstablecimiento);

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