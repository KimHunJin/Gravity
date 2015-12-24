package com.dxmnd.gravitymembermanagementapplication.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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

public class MemberAddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtAddMemberName, edtAddStudentNumber, edtAddEmail, edtAddHP, edtAddIntroduce, edtToolSearch;
    RadioButton rbtAddMemberMale;
    RadioGroup rdgAddMemberGender;
    CheckBox cbAddDeveloper, cbAddPlanner, cbAddDesigner;
    Button btnAddMemberSave, btnAddMemberCancel;
    String name = "", email = "", number = "", hp = "", gender = "", position = "", introduce = "";
    ImageView imgToolBack, imgToolSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add);

        init();  // 객체 생성
        imgToolBack = (ImageView) findViewById(R.id.img_tool_back);
        imgToolBack.setImageResource(R.drawable.ic_arrow_left_grey600_24dp);
        imgToolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edtToolSearch = (EditText)findViewById(R.id.edt_tool_search);
        edtToolSearch.setText("Member Add");
        edtToolSearch.setEnabled(false);
        edtToolSearch.setGravity(Gravity.CENTER);

        imgToolSearch = (ImageView)findViewById(R.id.img_tool_search);
        imgToolSearch.setVisibility(View.INVISIBLE);
    }

    /**
     * 객체 생성(초기화) 메서드
     */
    private void init() {

        edtAddMemberName = (EditText) findViewById(R.id.edtAddMemberName);
        edtAddEmail = (EditText) findViewById(R.id.edtAddEmail);
        edtAddStudentNumber = (EditText) findViewById(R.id.edtAddStudentNumber);
        edtAddHP = (EditText) findViewById(R.id.edtAddHP);
        edtAddIntroduce = (EditText) findViewById(R.id.edtAddIntroduce);
        rdgAddMemberGender = (RadioGroup) findViewById(R.id.rdgAddMemberGender);
        rbtAddMemberMale = (RadioButton) findViewById(R.id.rbtAddMemberMale);
        cbAddDeveloper = (CheckBox) findViewById(R.id.cbAddDeveloper);
        cbAddPlanner = (CheckBox) findViewById(R.id.cbAddPlanner);
        cbAddDesigner = (CheckBox) findViewById(R.id.cbAddDesigner);
        btnAddMemberSave = (Button) findViewById(R.id.btnAddMemberSave);
        btnAddMemberCancel = (Button) findViewById(R.id.btnAddMemberCancel);
        btnAddMemberSave.setOnClickListener(this);
        btnAddMemberCancel.setOnClickListener(this);
        rbtAddMemberMale.setChecked(true);
    }

    /**
     * onClick 메서드
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddMemberSave: {
                SQLite sqlite = new SQLite(getApplicationContext(), "member.db", null, 1);
                save(); // 문자로 저장
                if (name.equals("") || number.equals("")) {
                    Toast.makeText(getApplicationContext(), "이름과 학번을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    sqlite.insert("insert into `member`  values (null, '" + name + "', '" + gender + "', '" + number + "', '" + hp + "', '" + email + "', '" + position + "', '" + introduce + "');");  // sqlite에 저장
                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }
            case R.id.btnAddMemberCancel: {
                finish();
                break;
            }
        }
    }

    /**
     * editText에 들어있는 값을 문자열로 저장하는 메서드
     */
    private void save() {

        RadioButton selectRbtGender = (RadioButton) findViewById(rdgAddMemberGender.getCheckedRadioButtonId());
        name = edtAddMemberName.getText().toString().trim();
        hp = edtAddHP.getText().toString().trim();
        number = edtAddStudentNumber.getText().toString().trim();
        gender = selectRbtGender.getText().toString().trim();
        email = edtAddEmail.getText().toString().trim();
        position = getPosition().toString().trim();
        introduce = edtAddIntroduce.getText().toString().trim();
    }

    /**
     * combobox의 내용을 문자열에 저장하는 메서드
     * <p>
     * 체크된 것만 저장
     *
     * @return
     */
    private String getPosition() {
        if (cbAddDeveloper.isChecked()) {
            position += " " + cbAddDeveloper.getText().toString();
        }
        if (cbAddDesigner.isChecked()) {
            position += " " + cbAddDesigner.getText().toString();
        }
        if (cbAddPlanner.isChecked()) {
            position += " " + cbAddPlanner.getText().toString();
        }
        return position;
    }

    /**
     * 카메라 이벤트 but 만들기 귀찮아서 안만듬
     *
     * @param v
     */
    public void createInputImage(View v) {
        Toast.makeText(getApplicationContext(), "이미지 가져오는 곳입니다만.. \n 만들기가 귀찮아요.", Toast.LENGTH_SHORT).show();
    }
}
