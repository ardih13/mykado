package com.test.mykado.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.test.mykado.R;
import com.test.mykado.data.GenderModel;

import java.util.List;

public class GenderAdapter extends BaseAdapter implements SpinnerAdapter {

    private List<GenderModel> listGender;
    Context context;

    public GenderAdapter(Context context, List<GenderModel> gender) {
        this.context = context;
        this.listGender = gender;
    }

    @Override
    public int getCount() {
        return listGender.size();
    }

    @Override
    public Object getItem(int position) {
        return listGender.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private View getCustomView(int position, View convertView, ViewGroup parent) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner, parent, false);
        TextView gender = layout.findViewById(R.id.tvGender);

        GenderModel type = listGender.get(position);
        gender.setText(type.getName());

        return layout;
    }

    // It gets a View that displays in the drop down popup the data at the specified position
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // It gets a View that displays the data at the specified position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
