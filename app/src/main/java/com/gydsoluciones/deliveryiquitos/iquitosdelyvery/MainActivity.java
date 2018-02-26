package com.gydsoluciones.deliveryiquitos.iquitosdelyvery;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Fragmentos.ListaCategoriaFragment;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Fragmentos.ListaInicioFragment;
import com.gydsoluciones.deliveryiquitos.iquitosdelyvery.Fragmentos.ListaOfertasFragment;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (BottomNavigationView)findViewById(R.id.navigation);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_inicio:
                        fragment = new ListaInicioFragment();
                        Log.i("Delivery","Inicio");
                        break;
                    case R.id.navigation_categorias:
                        fragment = new ListaCategoriaFragment();
                        Log.i("Delivery","Categorias");
                        break;
                    case R.id.navigation_ofertas:
                        fragment = new ListaOfertasFragment();
                        Log.i("Delivery","Ofertas");
                        break;
                }
                fragmentTransaction.replace(R.id.fragment_principal, fragment);
                fragmentTransaction.commit();
                return true;
            }
        });

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new ListaInicioFragment();
        fragmentTransaction.add(R.id.fragment_principal, fragment);
        fragmentTransaction.commit();

    }


    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    fragment = new ListaInicioFragment();
                    Log.i("Delivery","Inicio");
                    break;
                case R.id.navigation_categorias:
                    fragment = new ListaCategoriaFragment();
                    Log.i("Delivery","Categorias");
                    break;
                case R.id.navigation_ofertas:
                    fragment = new ListaOfertasFragment();
                    Log.i("Delivery","Ofertas");
                    break;
            }
            fragmentTransaction.replace(R.id.fragment_principal, fragment);
            fragmentTransaction.commit();
            return true;
        }
    };*/

}
