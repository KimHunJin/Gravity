package com.dxmnd.gravitymembermanagementapplication.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.dxmnd.gravitymembermanagementapplication.R;
import com.dxmnd.gravitymembermanagementapplication.adapter.MemberDeleteAdapter;
import com.dxmnd.gravitymembermanagementapplication.adapter.MemberListAdapter;
import com.dxmnd.gravitymembermanagementapplication.item.MemberListItem;
import com.dxmnd.gravitymembermanagementapplication.sqlite.SQLite;

import java.util.ArrayList;

public class MemberDeleteActivity extends AppCompatActivity implements View.OnClickListener {

    ListView memberDeleteListView;
    ArrayList<MemberListItem> memberItem = new ArrayList<>();
    SQLite mSQLite;
    MemberDeleteAdapter adapter;
    Button btnDelete, btnCancel;
    private android.support.v7.app.AlertDialog mDialog;
    ImageView imgToolBack, imgToolSearch;
    EditText edtToolSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_delete);

        init();

        select("select `number`, `name`, `gender` from `member` order by `name` asc");

        adapter = new MemberDeleteAdapter(this, R.layout.member_delete_item, memberItem);
        memberDeleteListView.setAdapter(adapter);

        memberDeleteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setChecked(position);
                adapter.notifyDataSetChanged();
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

    private void init() {
        memberDeleteListView = (ListView) findViewById(R.id.memberDeleteListView);
        btnDelete = (Button) findViewById(R.id.btnDeleteMemberDelete);
        btnCancel = (Button) findViewById(R.id.btnDeleteMemberCancel);
        mSQLite = new SQLite(getApplicationContext(), "member.db", null, 1);

        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDeleteMemberDelete: {

                mDialog = CreateDialog();
                mDialog.show();
                break;
            }
            case R.id.btnDeleteMemberCancel: {
                finish();
                break;
            }
        }
    }

    private android.support.v7.app.AlertDialog CreateDialog() {
        final android.support.v7.app.AlertDialog.Builder mAB = new android.support.v7.app.AlertDialog.Builder(this);
        mAB.setTitle("삭제 확인");
        mAB.setCancelable(true);
        mAB.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < adapter.getChecked().size(); i++) {
                    int number = memberItem.get(adapter.getChecked().get(i)).getmNumber();
                    mSQLite.delete("delete from `member` where `number` = '" + number + "';");
                }
                Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        })
                .setPositiveButton("취소하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setDismiss(mDialog);
                    }
                });
        return mAB.create();
    }

    private void setDismiss(Dialog dia) {
        if (dia != null && dia.isShowing())
            dia.dismiss();
    }
}
