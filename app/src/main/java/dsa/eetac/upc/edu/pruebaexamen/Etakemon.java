package dsa.eetac.upc.edu.pruebaexamen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Etakemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etakemon);
        Bundle extra=getIntent().getExtras();
        String nombre=extra.getString("nombre");
        String desc=extra.getString("descripcion");
        TextView text1=(TextView) findViewById(R.id.song);
        TextView text2=(TextView) findViewById(R.id.singer);
        text1.setText(nombre);
        text2.setText(desc);
    }
}
