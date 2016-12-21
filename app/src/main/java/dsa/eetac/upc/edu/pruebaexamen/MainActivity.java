package dsa.eetac.upc.edu.pruebaexamen;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends ListActivity {

    HashMap<String,String> hs=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getList();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    protected void onListItemClick(ListView l, View view, int position, long id) {
        String selectedValue =(String) getListAdapter().getItem(position);
        Intent Activity2 = new Intent(getApplicationContext(), Etakemon.class);
        Bundle extras = new Bundle();
        extras.putString("nombre",selectedValue);
        extras.putString("descripcion",hs.get(selectedValue));
        Activity2.putExtras(extras);
        startActivity(Activity2);
    }


        public void getList() throws JSONException, UnsupportedEncodingException {
            final TextView res =(TextView) findViewById(R.id.label);
            Bundle extras=getIntent().getExtras();
            String usuari=extras.getString("usuari");
            InicioSesion.RestClient.get("/myapp/json/get/"+usuari, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {
                        String[] arrayEtakemon =new String[response.length()];
                            String res=response.get("nombre").toString();
                            String s=response.get("descripcion").toString();
                            hs.put(res,s);
                            arrayEtakemon[0]=res;
                        setListAdapter(new ElmeuArrayAdapter(getApplicationContext(), arrayEtakemon
                        ));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCOde, Header[] headers, String s, Throwable i){

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    // Pull out the first event on the public timeline
                    try {
                        String[] arrayEtakemon =new String[response.length()];
                        for (int i=0;i<response.length();i++) {
                            String res=response.getJSONObject(i).get("nombre").toString();
                            String s=response.getJSONObject(i).get("descripcion").toString();
                            hs.put(res,s);
                            arrayEtakemon[i]=res;
                        }
                        setListAdapter(new ElmeuArrayAdapter(getApplicationContext(), arrayEtakemon
                        ));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
}
