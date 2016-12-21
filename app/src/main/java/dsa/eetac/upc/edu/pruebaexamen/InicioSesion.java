package dsa.eetac.upc.edu.pruebaexamen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
    }
    public static class RestClient {
        private static final String BASE_URL = "http://10.192.91.239:8080";
        private static AsyncHttpClient client = new AsyncHttpClient();


        public static void get(String url, AsyncHttpResponseHandler responseHandler) {
            client.get(getAbsoluteUrl(url), responseHandler);

        }

        public static void post(Context context, String url, StringEntity entity, String c, AsyncHttpResponseHandler responseHandler) {

            client.post(context, getAbsoluteUrl(url), entity,c, responseHandler);
        }

        private static String getAbsoluteUrl(String relativeUrl) {
            return BASE_URL + relativeUrl;
        }
    }

    public void inicio_sesion (View v) throws JSONException, UnsupportedEncodingException {

        final EditText user =(EditText) findViewById(R.id.user);
        final EditText pass =(EditText) findViewById(R.id.pass);
        String p=pass.getText().toString();
        if (user.getText().toString().equals("")||pass.toString().equals("")){
            Toast.makeText(getApplicationContext(),"Algun campo está en blanco",Toast.LENGTH_SHORT).show();
        }
        else {
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("nom", user.getText().toString());
            jsonParams.put("contra", pass.getText().toString());
            StringEntity entity = new StringEntity(jsonParams.toString());
            RestClient.post(getApplicationContext(), "/myapp/json/login", entity, "application/json",
                    new TextHttpResponseHandler() {

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            if (responseString.equals("200")) {
                                Intent a = new Intent(getApplicationContext(), Inici.class);
                                Bundle extras = new Bundle();
                                extras.putString("usuari", user.getText().toString());
                                a.putExtras(extras);
                                startActivity(a);
                            }
                            if (responseString.equals("500")) {
                                Toast.makeText(getApplicationContext(), "usuario inexistente", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }

    public void registro (View v) throws JSONException, UnsupportedEncodingException {
        final TextView user =(TextView) findViewById(R.id.user);
        final TextView pass =(TextView) findViewById(R.id.pass);
        if (user.getText().toString().equals("")||pass.toString().equals("")){
            Toast.makeText(getApplicationContext(),"Algun campo está en blanco",Toast.LENGTH_SHORT).show();
        }
        else {
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("nom", user.getText().toString());
            jsonParams.put("contra", pass.getText().toString());
            StringEntity entity = new StringEntity(jsonParams.toString());
            RestClient.post(getApplicationContext(), "/myapp/json/register", entity, "application/json",
                    new TextHttpResponseHandler() {

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            if (responseString.equals("200")) {
                                Intent a = new Intent(getApplicationContext(), Inici.class);
                                Bundle extras = new Bundle();
                                extras.putString("usuari", user.getText().toString());
                                a.putExtras(extras);
                                startActivity(a);
                            }
                            if (responseString.equals("500")) {
                                Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }
}
