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
public class MemberDeleteAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<MemberListItem> item;
    private int layout;

    private boolean[] isCheckedConfrim;

    public MemberDeleteAdapter(Context context, int layout, ArrayList<MemberListItem> item) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.item = item;
        this.layout = layout;
        this.isCheckedConfrim = new boolean[item.size()];
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
            viewHolder.cb = (CheckBox) convertView.findViewById(R.id.cbDelete);
            viewHolder.txtMemberName = (TextView) convertView.findViewById(R.id.txtItemMemberName);
            viewHolder.txtMemberGender = (TextView) convertView.findViewById(R.id.txtItemMemberGender);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MemberListItem memberListItem = item.get(position);
        viewHolder.txtMemberName.setText(memberListItem.getmName());
        viewHolder.txtMemberGender.setText(memberListItem.getmGender());

        viewHolder.cb.setClickable(false);
        viewHolder.cb.setFocusable(false);
        viewHolder.cb.setChecked(isCheckedConfrim[position]);

        return convertView;
    }

    public void setChecked(int position) {
        isCheckedConfrim[position] = !isCheckedConfrim[position];
    }

    public ArrayList<Integer> getChecked() {
        int tempSize = isCheckedConfrim.length;
        ArrayList<Integer> mArrayList = new ArrayList<>();
        for (int b = 0; b < tempSize; b++) {
            if (isCheckedConfrim[b]) {
                mArrayList.add(b);
            }
        }
        return mArrayList;
    }


    class ViewHolder {
        private CheckBox cb;
        private TextView txtMemberName;
        private TextView txtMemberGender;
    }
}
