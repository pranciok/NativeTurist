package com.fer.ppij.adapterlist;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fer.ppij.beans.Tour;
import com.fer.ppij.nativeturist.NativeTouristMap;
import com.fer.ppij.nativeturist.R;

/**
 * Created by user00 on 5/7/14.
 */
public class SelectTourAdapter extends ArrayAdapter<Tour> {

    private Context context;
    private List<Tour> values;

    public SelectTourAdapter(Context context,  List<Tour> values) {
        super(context, R.layout.fragment_tour, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int positionBackward=values.size()-1-position;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_tour,
                parent, false);

        TextView tourName = (TextView) rowView.findViewById(R.id.text_tour_name_fragment);
        ImageView info = (ImageView) rowView.findViewById(R.id.image_info_button);
        Button downloadButton = (Button) rowView.findViewById(R.id.button_download_fragment);

        tourName.setText(""+values.get(positionBackward).getName());
        info.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        downloadButton.setText("Select");
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//TODO
                Intent intent = new Intent(context, NativeTouristMap.class);
                intent.putExtra("idTure",values.get(positionBackward).getId());
                context.startActivity(intent);
            }
        });

        return rowView;

    }
}
