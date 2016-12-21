package dsa.eetac.upc.edu.pruebaexamen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Alberto on 05/12/2016.
 */

public class ElmeuArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public ElmeuArrayAdapter(Context context, String[] values) {
        super(context, R.layout.activity_main, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_main, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);
        /*imageView.setImageResource(R.drawable.muntanya_logo);
        String s = values[position];
if (s.equals("Montseny")) {
        imageView.setImageResource(R.drawable.monseny_turo_home_150p);
        } else if (s.equals("Mont Perdut")) {
        imageView.setImageResource(R.drawable.montperdut_150p);
       } else if (s.equals("Dôme de Neige")) {
       imageView.setImageResource(R.drawable.dome_de_neige_150p);
        } else if (s.equals("Pica d'Estats")) {
        imageView.setImageResource(R.drawable.pica_destats_150p);
        } else if (s.equals("Pedraforca")) {
        imageView.setImageResource(R.drawable.pedradorca_150p);
        } else {   //Montardo
        imageView.setImageResource(R.drawable.montardo_150p);
        }*/
        return rowView;
    }

}
