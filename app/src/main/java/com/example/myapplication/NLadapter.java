package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NLadapter extends BaseAdapter {
    private Qlkho context;
    private int layout;
    private ArrayList<ingredients> nllist;

    public NLadapter(Qlkho context, int layout, ArrayList<ingredients> nllist) {
        this.context = context;
        this.layout = layout;
        this.nllist = nllist;
    }

    private class ViewHolder {
        TextView txttennl, txtgianl, txtdonvi, txtsoluong;
        ImageView imgedit, imgdelete;
    }

    @Override
    public int getCount() {
        return nllist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txttennl = (TextView) view.findViewById(R.id.tenNL);
            holder.txtgianl = (TextView) view.findViewById(R.id.giaNL);
            holder.txtdonvi = (TextView) view.findViewById(R.id.donvi);
            holder.txtsoluong = (TextView) view.findViewById(R.id.soluong);
            holder.imgedit = (ImageView) view.findViewById(R.id.edit);
            holder.imgdelete = (ImageView) view.findViewById(R.id.delete);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ingredients nguyenlieu = nllist.get(position);
        holder.txttennl.setText(nguyenlieu.getTenNL());
        holder.txtgianl.setText(nguyenlieu.getGiaNL() + "");
        holder.txtdonvi.setText(nguyenlieu.getDonvi());
        holder.txtsoluong.setText(nguyenlieu.getSoluong() + "");
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, updateNL.class);
                intent.putExtra("dataNL", nguyenlieu);
                context.startActivity(intent);
            }
        });
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacnhanxoa(nguyenlieu.getTenNL(),nguyenlieu.getMaNL());
            }
        });
        return view;
    }

    private void xacnhanxoa(String tenNL,int id) {
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(context);
        dialogxoa.setTitle("Thống báo").setMessage("Bạn có muốn xoá "+ tenNL + " không?").setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.xoaNL(id);
            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogxoa.show();
    }

}
