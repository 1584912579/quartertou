package com.example.asus.quartertou.ui.UserInfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.quartertou.R;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.ui.UserInfo.contract.UpdateContract;
import com.example.asus.quartertou.ui.UserInfo.presenter.UpdatePresenter;
import com.example.asus.quartertou.ui.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity<UpdatePresenter> implements UpdateContract.View {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.bt)
    Button mBt;
    @BindView(R.id.ll)
    LinearLayout ll;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private String imgPath;
    private File imgFile;
    private Bitmap photo;
    private PopupWindow pop;
    private SharedPreferences sharedPreferences;
    private String uid;
    private String name;
    private String iconUrl;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        imgPath = getExternalCacheDir() + File.separator + "header.jpg";
        imgFile = new File(imgPath);

        //获取
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "");
        token = sharedPreferences.getString("token", "");
        name = sharedPreferences.getString("name", "");
        iconUrl = sharedPreferences.getString("iconUrl", "");
        mTv.setText(name);
        Glide.with(this).load(iconUrl).into(mIv);

    }
    @Override
    public int getContentLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }
    @OnClick({R.id.iv, R.id.tv, R.id.bt})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv:
                showPopupWindow();
                pop.showAtLocation(ll, Gravity.BOTTOM, 0, 0);
                break;

            case R.id.bt:
                sharedPreferences.edit().clear().commit();
                UserInfoActivity.this.finish();
                break;
        }
    }
    /****
     * 头像提示框
     */
    public void showPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_popupwindows, null);
        pop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        //点击PopupWindow外部可以取消
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable());
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);

        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //拍照

                if (pop != null && pop.isShowing()) {
                    pop.dismiss();
                }

                //调用相机，需要隐式意图
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile));
                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
            }
        });
        //去相册
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// 图库选择

                if (pop != null && pop.isShowing()) {
                    pop.dismiss();
                }
                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(pickIntent, PHOTO_REQUEST_GALLERY);

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pop != null && pop.isShowing()) {
                    pop.dismiss();
                }
            }
        });
    }
    //    回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_REQUEST_TAKEPHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    //截图
                    crop(Uri.fromFile(imgFile));
                }
                break;
            case PHOTO_REQUEST_CUT:
                //截图图片成功
                Bundle bundle = data.getExtras();

                if (bundle != null) {

                    try {
                        photo = bundle.getParcelable("data");
                        if (imgFile.exists()) {
                            imgFile.delete();
                        }
                        //创建出新的文件
                        imgFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(imgFile);
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //上传头像
                   mPresenter.updateHeader(uid, token, imgPath);

                }

                break;
            case PHOTO_REQUEST_GALLERY:
                crop(data.getData());
                break;

        }
    }
    //截取图片
    private void crop(Uri uri) {
        Intent intent = new Intent();

        //指定裁剪的动作
        intent.setAction("com.android.camera.action.CROP");
        //设置裁剪的数据(uri路径)....裁剪的类型(image/*)
        intent.setDataAndType(uri, "image/*");
        //执行裁剪的指令
        intent.putExtra("crop", "true");
        //指定裁剪框的宽高比
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //指定输出的时候宽度和高度
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        //设置取消人脸识别
        intent.putExtra("noFaceDetection", true);
        //设置返回数据
        intent.putExtra("return-data", true);
        //
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }



    @Override
    public void updateSuccess(String code) {
        if ("0".equals(code) && photo != null) {
            Toast.makeText(UserInfoActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
            //去设置头像
            mIv.setImageBitmap(photo);

        }
    }
}
