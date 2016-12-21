package dsa.eetac.upc.edu.pruebaexamen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class Insertar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
    }

    public void post (View v) throws JSONException, UnsupportedEncodingException {
        final EditText des =(EditText) findViewById(R.id.desc);
        final EditText nom =(EditText) findViewById(R.id.nombre);
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("nombre", nom.getText().toString());
        jsonParams.put("descripcion",des.getText().toString());
        StringEntity entity = new StringEntity(jsonParams.toString());
        Bundle extra=getIntent().getExtras();
        String usuari=extra.getString("usuari");
        InicioSesion.RestClient.post(getApplicationContext(), "/myapp/json/etakemon/"+usuari, entity, "application/json",
                new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String si) {
                    if (si.equals("200")){
                        Toast.makeText(getApplicationContext(),"Añadido correctamente",Toast.LENGTH_SHORT).show();
                    }
                    }

                    @Override
                    public void onFailure(int statusCOde, Header[] headers, String s, Throwable i){

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    }
                });
    }
}