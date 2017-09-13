package com.lhj.sample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;
import com.greendao.gen.UserDao;
import com.lhj.sample.bean.User;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_Name)
    EditText mEtName;
    @BindView(R.id.et_age)
    EditText mEtAge;
    @BindView(R.id.btnAdd)
    Button mBtnAdd;
    @BindView(R.id.btnDelete)
    Button mBtnDelete;
    @BindView(R.id.btnQuery)
    Button mBtnQuery;
    @BindView(R.id.tvQuery)
    TextView mTvQuery;
    @BindView(R.id.btupdate)
    Button btupdate;

    private UserDao mUserDao;
    private DaoSession daoSession;
    private SQLiteDatabase db;
    private Database database;

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
        btupdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd: {
                String name = mEtName.getText().toString();
                String age = mEtAge.getText().toString();

                User user = new User();
                user.setId(1111);
                user.setName(name);
                user.setAge(Integer.parseInt(age));

                mUserDao.insert(user);
            }
            break;
            case R.id.btnDelete:

                mUserDao.deleteAll();

                break;
            case R.id.btnQuery:

                List<User> list = mUserDao.queryBuilder().list(); //查询所有数据

                Log.e("TAG", "总数量：" + list.size());

                for (User u : list) {
                    Log.e("TAG", "id: " + u.getId());
                    Log.e("TAG", u.getName() + " : " + u.getAge());
                }

                break;
            case R.id.btupdate:  // 更新
            {
//                User user = new User();
//                user.setId(1111);
//                user.setName("草泥马");
//              //  user.setAge(100);

//                mUserDao.refresh(); // 通过 refresh来更新数据库
//

                String sql = "update "+ mUserDao.getTablename() + " set NAME = " + "\"草泥马\"";

                Log.e("TAG", "语句："+sql);

                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
                db.endTransaction();

            }
            break;
        }
    }

    /*初始化数据库相关*/
    private void initDbHelp() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "recluse.db", null);

        db = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(db);

        daoSession = daoMaster.newSession();

        mUserDao = daoSession.getUserDao();

        this.database = mUserDao.getDatabase();
    }
}
