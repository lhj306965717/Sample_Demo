package com.lhj.sample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;
import com.greendao.gen.UserDao;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.etId)
    EditText mEtId;
    @BindView(R.id.etName)
    EditText mEtName;
    @BindView(R.id.btnAdd)
    Button mBtnAdd;
    @BindView(R.id.btnDelete)
    Button mBtnDelete;
    @BindView(R.id.btnQuery)
    Button mBtnQuery;
    @BindView(R.id.tvQuery)
    TextView mTvQuery;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDbHelp();
        initEvent();
    }

    private void initEvent() {
        mBtnAdd.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:

                break;
            case R.id.btnDelete:

                break;
            case R.id.btnQuery:

                break;
        }
    }

    /*初始化数据库相关*/
    private void initDbHelp() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "recluse-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        mUserDao = daoSession.getUserDao();
    }
}
