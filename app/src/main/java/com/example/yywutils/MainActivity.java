package com.example.yywutils;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.show)
    TextView show;
    private Context context;
    private long exitTime = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView = (TextView) findViewById(R.id.show);
//        SaveInfo(context,"Utils","myutils","我是工具类");
//        SharedInfo(context,"Utils","myutils");
//        CommonUtils.ClearInfo(context,"Utils");
//        System.out.println("输出" + SharedInfo(context,"Utils","myutils"));
//        NWeixin(context,R.drawable.tubiao,"我是工具类");
//        SelectDialogMessageShow(R.drawable.tubiao,context,"确定选择工具类吗？","选择成功");
//        DialogShow(context,"我是工具类");
//        SelectDialogShow(R.drawable.tubiao,context,"确定跳转吗？",MainActivity2.class);
//        SetFullScreen(MainActivity.this);
//        KeepScreenOn(MainActivity.this);
//        HideStatusBar(MainActivity.this);
//        CurrentTimeShow();
//        System.out.println(CurrentTimeShow());
//        String text = "我是工具类我是工具类我是工具类我是工具类我是工具类我是工具类" +
//                      "我是工具类我是工具类我是工具类我是工具类我是工具类我是工具类";
//        textView.setText(MatcherSearchText(Color.parseColor("#1296db"),text,"我"));
//        BannerSet(banner,R.drawable.tubiao,R.drawable.tubiao,R.drawable.tubiao,R.drawable.tubiao);


//        PerMission(MainActivity.this, new Action() {
//            @Override
//            public void onAction(List<String> permissions) {
//                //权限申请成功的动作
//            }
//        });
    }


//    @Override
//    public void onBackPressed() {
//        QuitShow(MainActivity.this);//再按一次退出
//    }

}
