package com.dxmnd.gravitymembermanagementapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.dxmnd.gravitymembermanagementapplication.R;
import com.dxmnd.gravitymembermanagementapplication.adapter.MemberListAdapter;
import com.dxmnd.gravitymembermanagementapplication.item.MemberListItem;
import com.dxmnd.gravitymembermanagementapplication.sqlite.SQLite;

import java.util.ArrayList;

public class MemberModListActivity extends AppCompatActivity {

    ListView memberListView;
    ArrayList<MemberListItem> memberItem = new ArrayList<>();
    SQLite mSQLite;
    EditText edtToolSearch;
    ImageView imgToolBack, imgToolSearch;
    MemberListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_mod_list);
        memberListView = (ListView) findViewById(R.id.modListView);

        mSQLite = new SQLite(getApplicationContext(), "member.db", null, 1);

        select("select `number`, `name`, `gender` from `member` order by `name` asc");


        adapter = new MemberListAdapter(this, R.layout.member_list_item, memberItem);
        memberListView.setAdapter(adapter);

        memberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int number = memberItem.get(position).getmNumber();
                Intent it = new Intent(getApplication(), MemberModActivity.class);
                it.putExtra("number", number);
                startActivity(it);
            }
        });

        imgToolBack = (ImageView) findViewById(R.id.img_tool_back);
        imgToolBack.setImageResource(R.drawable.ic_arrow_left_grey600_24dp);
        imgToolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edtToolSearch = (EditText) findViewById(R.id.edt_tool_search);

        imgToolSearch = (ImageView) findViewById(R.id.img_tool_search);
        imgToolSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchQuery = edtToolSearch.getText().toString();
                select("select `number`, `name`, `gender` from `member` where `name` like '%" + searchQuery + "%' or gender like '%" + searchQuery + "%' order by `name` asc;");
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * sqlite에서 정보를 검색하여 리스트에 저장하는 메서드
     */
    private void select(String query) {
        Cursor cursor = mSQLite.select(query);
        int mNumber;
        String strName = "";
        String strGender = "";
        memberItem.clear();
        while (cursor.moveToNext()) {
            mNumber = cursor.getInt(0);
            strName = cursor.getString(1);
            strGender = cursor.getString(2);
            MemberListItem memberListItem = new MemberListItem(mNumber, strName, strGender);
            memberItem.add(memberListItem);
        }
    }
}
