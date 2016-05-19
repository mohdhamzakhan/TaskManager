package com.example.admin.projectacad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.projectacad.R;
import com.example.admin.projectacad.businessLogic.To_Do;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by admin on 14-05-2016.
 */
public class taskAdapter extends ArrayAdapter {
    private ArrayList<To_Do> mTask;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public taskAdapter(Context context, int resource, ArrayList<To_Do> task)
    {
        super(context,resource);
        this.mTask=task;
        this.mContext=context;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mTask.size();
    }

    @Override
    public Object getItem(int position) {
        return mTask.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=mLayoutInflater.inflate(R.layout.item_task,parent,false);
        }
        TextView txt_Header= (TextView) convertView.findViewById(R.id.textHeader);
        TextView txt_Title= (TextView) convertView.findViewById(R.id.textTitle);
        TextView txt_Desc= (TextView) convertView.findViewById(R.id.textDescriptiion);
        TextView txt_date= (TextView) convertView.findViewById(R.id.textDate);
        TextView txt_id;
        //txt_id = (TextView) convertView.findViewById(R.id.txtID);

        To_Do to_do= (To_Do) getItem(position);

        txt_Header.setText(to_do.getDate());
        txt_Desc.setText(to_do.getDescription());
        txt_Title.setText(to_do.getTitle());
        txt_date.setText(to_do.getDate());
        //txt_id.setText(String.valueOf(to_do.getID()));

        return convertView;
    }
}
