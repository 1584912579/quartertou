package com.example.asus.quartertou.ui.Creation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.asus.quartertou.R;
import com.example.asus.quartertou.ui.Creation.wjokes.WJokesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreationActivity extends AppCompatActivity {

    @BindView(R.id.qx)
    TextView mQx;
    @BindView(R.id.sp)
    ImageView mSp;
    @BindView(R.id.dz)
    ImageView mDz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.qx, R.id.sp, R.id.dz})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.qx:
                CreationActivity.this.finish();
                break;
            case R.id.sp:
                //创作视频

                break;
            case R.id.dz:
                //创作段子
                Intent intent = new Intent(CreationActivity.this, WJokesActivity.class);
                startActivity(intent);
                break;
        }
    }
}
