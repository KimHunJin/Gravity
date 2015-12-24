package com.dxmnd.gravitymembermanagementapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dxmnd.gravitymembermanagementapplication.R;
import com.dxmnd.gravitymembermanagementapplication.sqlite.SQLite;

public class MemberModActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtModMemberName, edtModStudentNumber, edtModEmail, edtModHP, edtModIntroduce, edtToolSearch;
    RadioButton rbtModMemberMale, rbtModMemberFemale;
    RadioGroup rdgModMemberGender;
    CheckBox cbModDeveloper, cbModPlanner, cbModDesigner;
    Button btnModMemberSave, btnModMemberCancel;
    String name = "", email = "", number = "", hp = "", gender = "", position = "", introduce = "";
    SQLite sqlite;
    int key;
    ImageView imgToolBack, imgToolSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_mod);

        init();
        Intent it = getIntent();
        key = it.getExtras().getInt("number");
        sql(key);
        setEditText();

        checkGender();
        checkPosition();

        imgToolBack = (ImageView) findViewById(R.id.img_tool_back);
        imgToolBack.setImageResource(R.drawable.ic_arrow_left_grey600_24dp);
        imgToolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edtToolSearch = (EditText)findViewById(R.id.edt_tool_search);
        edtToolSearch.setText("Member Modify");
        edtToolSearch.setEnabled(false);
        edtToolSearch.setGravity(Gravity.CENTER);

        imgToolSearch = (ImageView)findViewById(R.id.img_tool_search);
        imgToolSearch.setVisibility(View.INVISIBLE);

    }

    private void init() {
        edtModMemberName = (EditText) findViewById(R.id.edtModMemberName);
        edtModEmail = (EditText) findViewById(R.id.edtModEmail);
        edtModStudentNumber = (EditText) findViewById(R.id.edtModStudentNumber);
        edtModHP = (EditText) findViewById(R.id.edtModHP);
        edtModIntroduce = (EditText) findViewById(R.id.edtModIntroduce);
        rdgModMemberGender = (RadioGroup) findViewById(R.id.rdgModMemberGender);
        rbtModMemberMale = (RadioButton) findViewById(R.id.rbtModMemberMale);
        rbtModMemberFemale = (RadioButton) findViewById(R.id.rbtModMemberFemale);
        cbModDeveloper = (CheckBox) findViewById(R.id.cbModDeveloper);
        cbModPlanner = (CheckBox) findViewById(R.id.cbModPlanner);
        cbModDesigner = (CheckBox) findViewById(R.id.cbModDesigner);
        btnModMemberSave = (Button) findViewById(R.id.btnModMemberSave);
        btnModMemberCancel = (Button) findViewById(R.id.btnModMemberCancel);

        sqlite = new SQLite(getApplicationContext(), "member.db", null, 1);

        btnModMemberSave.setOnClickListener(this);
        btnModMemberCancel.setOnClickListener(this);

    }

    private void sql(int key) {

        Cursor cursor = sqlite.select("select * from `member` where `number` = '" + key + "';");
        while (cursor.moveToNext()) {
            name = cursor.getString(1);
            gender = cursor.getString(2);
            number = cursor.getString(3);
            hp = cursor.getString(4);
            email = cursor.getString(5);
            position = cursor.getString(6);
            introduce = cursor.getString(7);
        }
    }

    private void setEditText() {
        edtModMemberName.setText(name.toString().trim());
        edtModStudentNumber.setText(number.toString().trim());
        edtModHP.setText(hp.toString().trim());
        edtModEmail.setText(email.toString().trim());
        edtModIntroduce.setText(introduce.toString().trim());
    }

    private void checkGender() {
        if (gender.equals("남")) {
            rbtModMemberMale.setChecked(true);
        } else if (gender.equals("여")) {
            rbtModMemberFemale.setChecked(true);
        }
    }

    private void checkPosition() {
        String[] str = position.split(" ");
        for (int i = 0; i < str.length; i++) {
            Log.e("test",str[i].toString());
            if (str[i].equals(cbModDesigner.getText())) {
                cbModDesigner.setChecked(true);
            } else if (str[i].equals(cbModDeveloper.getText())) {
                cbModDeveloper.setChecked(true);
            } else if (str[i].equals(cbModPlanner.getText())) {
                cbModPlanner.setChecked(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnModMemberSave: {
                save();
                if (name.equals("") || number.equals("")) {
                    Toast.makeText(getApplicationContext(), "이름과 학번을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    sqlite.update("update `member` set  `gender` = '" + gender + "',  `hp`= '" + hp + "', `email` = '" + email + "', `position` = '" + position + "' , `introduce` = '" + introduce + "' where `number` = '" + key + "';");
                    Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }
            case R.id.btnModMemberCancel: {
                finish();
                break;
            }
        }
    }

    private void save() {

        position="";
        RadioButton selectRbtGender = (RadioButton) findViewById(rdgModMemberGender.getCheckedRadioButtonId());
        name = edtModMemberName.getText().toString().trim();
        hp = edtModHP.getText().toString().trim();
        number = edtModStudentNumber.getText().toString().trim();
        gender = selectRbtGender.getText().toString().trim();
        email = edtModEmail.getText().toString().trim();
        position = getPosition().toString().trim();
        introduce = edtModIntroduce.getText().toString().trim();
    }

    private String getPosition() {
        if (cbModDeveloper.isChecked()) {
            position += " " + cbModDeveloper.getText().toString();
        }
        if (cbModDesigner.isChecked()) {
            position += " " + cbModDesigner.getText().toString();
        }
        if (cbModPlanner.isChecked()) {
            position += " " + cbModPlanner.getText().toString();
        }
        return position;
    }
}
