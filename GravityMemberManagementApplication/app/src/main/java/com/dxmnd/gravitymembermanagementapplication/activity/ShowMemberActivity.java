package com.dxmnd.gravitymembermanagementapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dxmnd.gravitymembermanagementapplication.R;
import com.dxmnd.gravitymembermanagementapplication.sqlite.SQLite;

public class ShowMemberActivity extends AppCompatActivity {

    EditText edtDetailMemberName, edtDetailStudentNumber, edtDetailEmail, edtDetailHP, edtDetailIntroduce, edtDetailGender, edtDetailPosition, edtToolSearch;
    SQLite sqlite;
    String name = "", email = "", studentNumber = "", hp = "", gender = "", position = "", introduce = "";
    Button btnCancel;
    ImageView imgToolBack, imgToolSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_member);
        Intent it = getIntent();
        int number = it.getExtras().getInt("number");

        init();
        sql(number);
        setEditText();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        edtToolSearch = (EditText)findViewById(R.id.edt_tool_search);
        edtToolSearch.setText("Member Detail Information");
        edtToolSearch.setEnabled(false);
        edtToolSearch.setGravity(Gravity.CENTER);

        imgToolSearch = (ImageView)findViewById(R.id.img_tool_search);
        imgToolSearch.setVisibility(View.INVISIBLE);
    }

    private void init() {
        edtDetailMemberName = (EditText) findViewById(R.id.edtDetailMemberName);
        edtDetailEmail = (EditText) findViewById(R.id.edtDetailEmail);
        edtDetailStudentNumber = (EditText) findViewById(R.id.edtDetailStudentNumber);
        edtDetailHP = (EditText) findViewById(R.id.edtDetailHP);
        edtDetailIntroduce = (EditText) findViewById(R.id.edtDetailIntroduce);
        edtDetailGender = (EditText) findViewById(R.id.edtDetailMemberGender);
        edtDetailPosition = (EditText) findViewById(R.id.edtDetailPosition);

        btnCancel = (Button) findViewById(R.id.btnDetailMemberCancel);
    }

    private void sql(int number) {
        sqlite = new SQLite(getApplicationContext(), "member.db", null, 1);
        Cursor cursor = sqlite.select("select * from `member` where `number` = '" + number + "';");
        while (cursor.moveToNext()) {
            name = cursor.getString(1);
            gender = cursor.getString(2);
            studentNumber = cursor.getString(3);
            hp = cursor.getString(4);
            email = cursor.getString(5);
            position = cursor.getString(6);
            introduce = cursor.getString(7);
        }
    }

    private void setEditText() {
        edtDetailMemberName.setText(name.toString().trim());
        edtDetailGender.setText(gender.toString().trim());
        edtDetailStudentNumber.setText(studentNumber.toString().trim());
        edtDetailHP.setText(hp.toString().trim());
        edtDetailEmail.setText(email.toString().trim());
        edtDetailPosition.setText(position.toString().trim());
        edtDetailIntroduce.setText(introduce.toString().trim());
    }

}
