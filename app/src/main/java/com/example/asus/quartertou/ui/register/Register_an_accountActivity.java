package com.example.asus.quartertou.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.MainActivity;
import com.example.asus.quartertou.ui.base.BaseActivity;
import com.example.asus.quartertou.ui.login.LoginActivity;
import com.example.asus.quartertou.ui.register.contract.RegisterContract;
import com.example.asus.quartertou.ui.register.presenter.RegisterPresenter;

import java.util.regex.Pattern;

public class Register_an_accountActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View,View.OnClickListener {

    private String zheng="^((13[0-9])|(15[^4,//D])|(18[0,5-9]))//d{8}$";
    private ImageView backtrack_two;
    /**
     * 已有账号?
     */
    private TextView existing_account;
    /**
     * 请输入手机号
     */
    private EditText mobile_register;
    /**
     * 输入大小写和数字,密码不能超过8位
     */
    private EditText password_register;
    /**
     * 注册
     */
    private Button register;
    /**
     * 游客登录
     */
    private TextView tourist_register;
    private String mobile;
    private String password;
    private String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        backtrack_two = findViewById(R.id.backtrack_two);
        backtrack_two.setOnClickListener(this);
        existing_account = (TextView) findViewById(R.id.existing_account);
        existing_account.setOnClickListener(this);
        mobile_register = (EditText) findViewById(R.id.mobile_register);
        password_register = (EditText) findViewById(R.id.password_register);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        tourist_register = (TextView) findViewById(R.id.tourist_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回上一级
            case R.id.backtrack_two:
                Register_an_accountActivity.this.finish();

                break;
                //注册
            case R.id.register:
                mobile = mobile_register.getText().toString();
                password = password_register.getText().toString();
                if (mobile.length()!=11){
                    Toast.makeText(getApplicationContext(),"电话长度必须为十一位",Toast.LENGTH_SHORT).show();
                } else if (!Pattern.matches(REGEX_MOBILE, mobile)){
                    Toast.makeText(getApplicationContext(),"电话号码不合法 ",Toast.LENGTH_SHORT).show();
                }else if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"密码长度不能少于6位",Toast.LENGTH_SHORT).show();
                }else if(password.length()>20) {
                    Toast.makeText(getApplicationContext(),"密码长度不能大于20位",Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.register(mobile, password);
                }
                break;
                //已有账号
            case R.id.existing_account:
                Intent intent2 = new Intent(Register_an_accountActivity.this, LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
                //游客登录
            case R.id.tourist_register:
                Intent intent1 = new Intent(Register_an_accountActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_register_an_account;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void registerSuccess(BaseBean baseBean) {
        if (baseBean.getCode()=="0"){
            Toast.makeText(Register_an_accountActivity.this,baseBean.getMsg(),Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(Register_an_accountActivity.this, LoginActivity.class);
            startActivity(intent1);
        }else {
            Toast.makeText(Register_an_accountActivity.this,baseBean.getMsg(),Toast.LENGTH_LONG).show();
        }



    }
}
