package com.kcaco.greendaodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kcaco.greendaodemo.entity.User;
import com.kcaco.greendaodemo.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAdd, mDelete, mUpdate, mFind;
    private TextView mContext;
    private User mUser;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        mUserDao = MyApplication.getInstances().getDaoSession().getUserDao();
    }

    private void initEvent() {
        mAdd.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mFind.setOnClickListener(this);
    }

    private  void initView() {
        mContext = (TextView) findViewById(R.id.textView);
        mAdd = (Button) findViewById(R.id.button);
        mDelete = (Button) findViewById(R.id.button1);
        mUpdate = (Button) findViewById(R.id.button2);
        mFind = (Button) findViewById(R.id.button3);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                addData();
                break;
            case R.id.button1:
                deleteDataById(3);
                break;
            case R.id.button2:
                updateData();
                break;
            case R.id.button3:
                findData();
                break;
        }
    }

    /**
     * 添加数据
     */
    private void addData() {
        mUser = new User((long)3, "duxiaoshuai");
        mUserDao.insert(mUser);
        mContext.setText(mUser.getName());
    }

    /**
     * 删除数据
     */
    private void deleteDataById(long id) {
        mUserDao.deleteByKey(id);
    }

    /**
     * 更新数据
     */
    private void updateData() {
        mUser = new User((long)2,"xioahuangdi");
        mUserDao.update(mUser);
    }

    /**
     * 查询数据
     */
    private void findData() {
        List<User> users = mUserDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName()+",";
        }
        mContext.setText("查询全部数据"+userName);
    }

}