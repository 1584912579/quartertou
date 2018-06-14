package com.example.asus.quartertou.ui.Creation.wjokes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.Creation.wjokes.contract.WJokesContract;
import com.example.asus.quartertou.ui.Creation.wjokes.presenter.WJokesPresenter;
import com.example.asus.quartertou.ui.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WJokesActivity extends BaseActivity<WJokesPresenter> implements WJokesContract.View {

    @BindView(R.id.qx)
    TextView mQx;
    @BindView(R.id.fb)
    TextView mFb;
    @BindView(R.id.wz)
    EditText mWz;
    @BindView(R.id.tupian)
    ImageView mTupian;
    private SharedPreferences sharedPreferences;
    private String uid;
    private String token;
    private String imgPath;
    private File imgFile;
    private Bitmap photo;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_wjokes);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "");
        token = sharedPreferences.getString("token", "");
        imgPath = getExternalCacheDir() + File.separator + "header.jpg";
        imgFile = new File(imgPath);
    }

    @OnClick({R.id.qx, R.id.fb, R.id.wz, R.id.tupian})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.qx:
                WJokesActivity.this.finish();
                break;
            case R.id.fb:
                //发表
                String content = mWz.getText().toString();
                mPresenter.getWJokesPresenter(uid,token,content,imgPath);
                break;

            case R.id.tupian:
                //图片
                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(pickIntent, 1);
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_wjokes;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }
    //    回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                uri = data.getData();
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    photo = bundle.getParcelable("data");
                    //判断该文件创建
                    if (imgFile.exists()) {
                        imgFile.delete();
                    }
                    //创建出新的文件
                    try {
                        imgFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(imgFile);

                        //把裁剪后的图片保存到本地
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    @Override
    public void getWJokesSuccess(BaseBean baseBean) {
        Toast.makeText(WJokesActivity.this,baseBean.getMsg(),Toast.LENGTH_SHORT).show();
    }
}
