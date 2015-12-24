package com.dxmnd.gravitymembermanagementapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dxmnd.gravitymembermanagementapplication.R;
import com.dxmnd.gravitymembermanagementapplication.item.MemberListItem;

import java.util.ArrayList;

/**
 * Created by HunJin on 2015-12-22.
 */
public class MemberListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<MemberListItem> item;
    private int layout;

    private boolean[] isCheckedConfrim;

    public MemberListAdapter(Context context, int layout, ArrayList<MemberListItem> item) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.item = item;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position).getmName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder.txtMemberName = (TextView) convertView.findViewById(R.id.txtItemMemberName);
            viewHolder.txtMemberGender = (TextView) convertView.findViewById(R.id.txtItemMemberGender);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MemberListItem memberListItem = item.get(position);
        viewHolder.txtMemberName.setText(memberListItem.getmName());
        viewHolder.txtMemberGender.setText(memberListItem.getmGender());

        return convertView;
    }


    class ViewHolder {
        private TextView txtMemberName;
        private TextView txtMemberGender;
    }
}
