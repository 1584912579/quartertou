package com.example.asus.quartertou.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.LoginBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.MainActivity;
import com.example.asus.quartertou.ui.base.BaseActivity;
import com.example.asus.quartertou.ui.login.contract.LoginContract;
import com.example.asus.quartertou.ui.login.presenter.LoginPresenter;
import com.example.asus.quartertou.ui.register.Register_an_accountActivity;

import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View,View.OnClickListener {

    private ImageView backtrack_one;
    /**
     * 注册账号
     */
    private TextView register_an_account;
    /**
     * 18310830365
     */
    private EditText mobile;
    /**
     * 123456
     */
    private EditText password;
    /**
     * 登录
     */
    private Button Login;
    /**
     * 忘记密码
     */
    private TextView Forget_password;
    /**
     * 游客登录
     */
    private TextView Tourist;
    private String s;
    private String s1;
    private String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        backtrack_one = (ImageView) findViewById(R.id.backtrack_one);
        backtrack_one.setOnClickListener(this);
        register_an_account = (TextView) findViewById(R.id.register_an_account);
        register_an_account.setOnClickListener(this);
        mobile = (EditText) findViewById(R.id.mobile);
        password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.login);

        Login.setOnClickListener(this);
        Forget_password = (TextView) findViewById(R.id.forget_password);
        Forget_password.setOnClickListener(this);
        Tourist = (TextView) findViewById(R.id.tourist);
        Tourist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            //返回上一级
                case R.id.backtrack_one:
                   LoginActivity.this.finish();
                break;
            //注册账号
                case R.id.register_an_account:
                    Intent intent1=new Intent(LoginActivity.this,Register_an_accountActivity.class);
                    startActivity(intent1);
                break;
            case R.id.login:
                s = mobile.getText().toString();
                s1 = password.getText().toString();
                if (s.length()!=11){
                    Toast.makeText(getApplicationContext(),"电话长度必须为十一位",Toast.LENGTH_SHORT).show();
                } else if (!Pattern.matches(REGEX_MOBILE, s)){
                    Toast.makeText(getApplicationContext(),"电话号码不合法 ",Toast.LENGTH_SHORT).show();

                }else if(s1.length()<6){
                    Toast.makeText(getApplicationContext(),"密码长度不能少于6位",Toast.LENGTH_SHORT).show();
                }else if(s1.length()>20) {
                    Toast.makeText(getApplicationContext(),"密码长度不能大于20位",Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.login(s, s1);
                }

                break;
                //忘记密码
            case R.id.forget_password:
//                sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
//                sharedPreferences.edit().clear().commit();

                break;
                //游客登录
            case R.id.tourist:
                Intent intent5=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent5);
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        if ("0".equals(loginBean.getCode())) {
            Toast.makeText(LoginActivity.this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
            //保存用户信息到SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("uid", loginBean.getData().getUid() + "");
            //  Log.i("uuuu",userBean.getData().getUid()+"");
            editor.putString("name", loginBean.getData().getUsername() + "");
            editor.putString("iconUrl", loginBean.getData().getIcon() + "");
            editor.putString("token", loginBean.getData().getToken() + "");
            // Log.i("uuuu",userBean.getData().getToken()+"");
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(LoginActivity.this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            //15133044161
        }

    }


}
