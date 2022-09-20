package edu.uncc.inclass05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import edu.uncc.inclass05.models.DataServices;

public class AppAdapater extends ArrayAdapter<DataServices.App> {

    public AppAdapater(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_layout, parent, false);
        }

        DataServices.App app = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewArtist = convertView.findViewById(R.id.textViewArtist);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);

        textViewName.setText(app.name);
        textViewArtist.setText(app.artistName);
        textViewDate.setText(app.releaseDate);

        return convertView;
    }
}
