package myadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import quotation.Quotation;

public class MyAdapter extends ArrayAdapter {
    private int m;

    @Override
    //TODO dokonczyc implmentację getView()
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = ((Activity) getContext()).getLayoutInflater();
            View newView = layoutInflater.inflate(position, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tv1=newView.findViewById(0);
            viewHolder.tv2=newView.findViewById(0);
            newView.setTag(viewHolder);
        }
        else {
            ViewHolder viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.tv1=null;getItem(position);
            viewHolder.tv2=null;
        }
        return super.getView(position, convertView, parent);
    }

    private class ViewHolder {
        TextView tv1;
        TextView tv2;
    }
    public MyAdapter(Context context, int n, List<Quotation> qList){
        super(context, n, qList);
    }

}
