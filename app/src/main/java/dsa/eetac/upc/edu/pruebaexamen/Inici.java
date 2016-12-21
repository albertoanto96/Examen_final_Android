package dsa.eetac.upc.edu.pruebaexamen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Inici extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inici);
    }
    public void Get (View v){
        Bundle extra=getIntent().getExtras();
        String usuari=extra.getString("usuari");
        Bundle extra1=new Bundle();
        extra1.putString("usuari",usuari);
        Intent a=new Intent (getApplicationContext(),MainActivity.class);
        a.putExtras(extra1);
        startActivity(a);
    }
    public void Post (View v){
        Bundle extra=getIntent().getExtras();
        String usuari=extra.getString("usuari");
        Bundle extra1=new Bundle();
        extra1.putString("usuari",usuari);
        Intent b=new Intent (getApplicationContext(),Insertar.class);
        b.putExtras(extra1);
        startActivity(b);
    }

}
