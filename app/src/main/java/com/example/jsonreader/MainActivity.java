package com.example.jsonreader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsonreader.adaptadores.LugarAdaptador;
import com.example.jsonreader.modelos.Lugar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String url = "https://www.datos.gov.co/resource/jj37-fvz6.json";
    ArrayList<Lugar> lugares = new ArrayList<>();
    RequestQueue requestQueue;
    ListView lista;
    LugarAdaptador adaptador;
    Button filtrar, limpiar;
    EditText filtro;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filtrar = findViewById(R.id.btn_filtrar);
        limpiar = findViewById(R.id.btn_limpiar);
        filtro = findViewById(R.id.filtro_txt);
        progressBar = findViewById(R.id.progressBar);
        adaptador = new LugarAdaptador(getApplicationContext(),lugares);

        filtrar.setOnClickListener(this);
        limpiar.setOnClickListener(this);


        requestQueue = Volley.newRequestQueue(this);
        lista = findViewById(R.id.lista_items);
        RequestDatos("");

    }


    private void RequestDatos(String complemento_url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url+complemento_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(progressBar != null){
                            progressBar.setVisibility(View.GONE);
                            ParserJson(response);
                            lista.setAdapter(adaptador);
                            adaptador.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void ParserJson(JSONArray response){
        try {
            lugares.clear();
            for (int i = 0 ; i<response.length(); i++){
                Lugar lugarObjeto = new Lugar();
                JSONObject lugar = response.getJSONObject(i);
                lugarObjeto.setNombre(!lugar.isNull("nombresitio") ?  lugar.getString("nombresitio"): "");
                lugarObjeto.setTipo(!lugar.isNull("tipo") ?  "Tipo: "+lugar.getString("tipo"): "");
                lugarObjeto.setDescripcion(!lugar.isNull("descripcion") ?  "Descripción: "+lugar.getString("descripcion"): "");
                lugarObjeto.setMunicipio(!lugar.isNull("nombremunicipio") ?  "Municipio: "+lugar.getString("nombremunicipio"): "");
                lugarObjeto.setDireccion(!lugar.isNull("direccion") ?  "Dirección: "+lugar.getString("direccion"): "");
                lugarObjeto.setTelefono(!lugar.isNull("telefono") ?  "Telefono: "+lugar.getString("telefono"): "");
                lugares.add(lugarObjeto);
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_limpiar:
                filtro.setText("");
                RequestDatos("");
                break;
            case R.id.btn_filtrar:
                if(!TextUtils.isEmpty(filtro.getText())){
                    RequestDatos("?nombremunicipio="+filtro.getText());
                    return;
                }
                Toast.makeText(getApplicationContext(),"El filtro no puede ser vacío", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}