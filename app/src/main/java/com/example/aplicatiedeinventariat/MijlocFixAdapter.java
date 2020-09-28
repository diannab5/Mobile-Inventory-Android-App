package com.example.aplicatiedeinventariat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MijlocFixAdapter extends ArrayAdapter<MijlocFix> {
    private int resursaID;

    public MijlocFixAdapter(@NonNull Context context, int resource, @NonNull List<MijlocFix> objects) {
        super(context, resource, objects);
        resursaID = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
    MijlocFix m=getItem(position);
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View v=inflater.inflate(resursaID,null);

        TextView numeMij=v.findViewById(R.id.numeMijloc);
        TextView categMij=v.findViewById(R.id.categorieMijloc);
        TextView cantMij=v.findViewById(R.id.cantitateMijloc);
        TextView furnizMij=v.findViewById(R.id.furnizorMijloc);
        TextView pretMij=v.findViewById(R.id.pretMijloc);
        TextView dataMij=v.findViewById(R.id.dataAdaugareMijloc);

        numeMij.setText(m.getNume());
        categMij.setText(m.getCategorie());
        cantMij.setText(""+m.getCantitate());
        furnizMij.setText(m.getFurnizor());
        pretMij.setText(""+m.getPret());
        dataMij.setText(m.getDataAdaugare());


        return v;
    }
}