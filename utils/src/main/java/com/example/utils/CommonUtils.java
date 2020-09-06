package com.example.utils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.hb.dialog.dialog.ConfirmDialog;
import com.kongzue.dialog.v2.DialogSettings;
import com.kongzue.dialog.v2.MessageDialog;
import com.kongzue.dialog.v2.Notification;
import com.kongzue.dialog.v2.TipDialog;
import com.lcw.library.imagepicker.ImagePicker;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.view.animation.Animation.INFINITE;
import static com.kongzue.dialog.v2.DialogSettings.STYLE_MATERIAL;
import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;
import static com.kongzue.dialog.v2.Notification.TYPE_NORMAL;


/**
 * 大神之路，代码封装，大大减少你的开发时间
 * 通用工具类封装
 */
public class CommonUtils {

    private static long exitTime = 0;//起始时间为0
    private static List<String> mTitleList = new ArrayList<>();//banner加载图片的标题
    private static List<Integer> mImgList = new ArrayList<>();//banner加载的图片

    /**
     * 向后台带参数的Post请求
     * @关键字 key
     * @传递的参数 text
     * @请求地址 url
     */
    public static void PostHttpValue(final String key, final String text, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行Http向后端 post请求
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put(key, text);
                client.post(url,params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onFailure(Throwable error, String content) {
                        super.onFailure(error, content);
                    }

                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                    }
                });
            }
        }).start();
    }

    /**
     * 向后台带参数的Post请求
     * @关键字 key
     * @传递的参数 text
     * @请求地址 url
     */
    public static void PostHttpValue(final String key, final String text, final String key2, final String text2, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行Http向后端 post请求
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put(key, text);
                params.put(key2, text2);
                client.post(url,params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onFailure(Throwable error, String content) {
                        super.onFailure(error, content);
                    }

                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                    }
                });
            }
        }).start();
    }

    /**
     * 向后台无参数的Post请求
     * @请求地址 url
     */
    public static void PostHttpNoValue(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行Http向后端 post请求
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                client.post(url,params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onFailure(Throwable error, String content) {
                        super.onFailure(error, content);
                    }

                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                    }
                });
            }
        }).start();
    }

    /**
     * 存储信息
     * @存储名 Mode
     * @关键字 key
     * @需要存储的值 text
     */
    public static void SaveInfo(Context context,String Mode,String key,String text){
        SharedPreferences preferences = context.getSharedPreferences(Mode, Context.MODE_PRIVATE);
        preferences.edit().putString(key, text).apply();
    }

    /**
     * 获取存储信息
     * @存储名 Mode
     * @关键字 key
     * @return
     */
    public static String SharedInfo(Context context,String Mode, String key){
        SharedPreferences preferences = context.getSharedPreferences(Mode, Context.MODE_PRIVATE);
        String text = preferences.getString(key,null);
        return text;
    }

    /**
     * 清除保存的存储信息
     * @存储名 Mode
     */
    public static void ClearInfo(Context context,String Mode){
        SharedPreferences preferences = context.getSharedPreferences(Mode, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }

    /**
     * 对话框
     * @上下文 context
     * @需要弹出的语句 text
     */
    public static void DialogShow(Context context,String text){
        DialogSettings.style = STYLE_MATERIAL;  //对话框为IOS风格
        DialogSettings.tip_theme = THEME_LIGHT;  //设置提示框主题为亮色主题
        DialogSettings.use_blur = true;
        TipDialog.show(context, text, TipDialog.SHOW_TIME_LONG, TipDialog.TYPE_WARNING);
    }

    /**
     *确认后跳转对话框
     * @上下文 context
     * @消息 message
     * @要跳转到的class页面 cls
     */
    public static void SelectDialogShow(int iconResId,final Context context, String message, final Class<?> cls){
        ConfirmDialog confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(iconResId).setMsg(message);
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
                //点击确定跳转到指定的页面
                IntentToPage(context,cls);//挑转到页面
            }

            @Override
            public void cancel() {
            }
        });
        confirmDialog.show();
    }

    /**
     * 同时出现两个对话框
     * @上下文 context
     * @消息 message
     * @点击确定消息 messageshow
     */
    public static void SelectDialogMessageShow(int iconResId,final Context context, String message, final String messageshow){
        ConfirmDialog confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(iconResId).setMsg(message);
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
                //点击确定弹出消息
                DialogShow(context,messageshow);
            }

            @Override
            public void cancel() {
            }
        });
        confirmDialog.show();
    }

    /**
     *跳转页面
     * @上下文 context
     * @要跳转到的class页面 cls
     */
    public static void IntentToPage(Context context,Class<?> cls){
        context.startActivity(new Intent(context, cls));//挑转到指定class页面
    }

    /**
     * 全屏
     * @param activity
     */
    public static void Immerse(Activity activity){
        //沉浸式
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    /**
     * 3秒后销毁页面
     * @上下文 context
     * @要跳转到的class cls
     */
    public static void Delay(final Activity activity){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               activity.finish();
            }
        },3000);
    }

    /**
     * 3秒后跳转
     * @上下文 context
     * @要跳转到的class cls
     */
    public static void Delay3s(final Context context, final Activity activity, final Class<?> cls){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentToPage(context,cls);
                activity.finish();
            }
        },3000);
    }

    /**
     *单词发音
     * @上下文 context
     * @单词 text
     */
    public static void WordsVoice(Context context,String text){
        String voiceurl =  "http://media.shanbay.com/audio/us/";//发音地址
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, Uri.parse( voiceurl+text+".mp3"));
        mediaPlayer.start();//开始播放
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();//释放
            }
        });
    }

    /**
     * 仿微信消息通知
     * @param context
     */
    public static void FanWeixin(Context context,int iconResId,String text){
        Notification.show(context, 1, context.getResources().getDrawable(iconResId), context.getString(R.string.app_name), text, Notification.SHOW_TIME_LONG,TYPE_NORMAL );
    }

    /**
     * 关键文字颜色变化
     * @全文 text
     * @关键字 keyword
     * @return
     */
    public static SpannableString MatcherSearchText(int color, String text, String keyword) {
        SpannableString spannableString = new SpannableString(text);
        //条件 keyword
        Pattern pattern = Pattern.compile(keyword);
        //匹配
        Matcher matcher = pattern.matcher(new SpannableString(text.toLowerCase()));
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            //ForegroundColorSpan 需要new 不然也只能是部分变色
            spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //返回变色处理的结果
        return spannableString;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String CurrentTimeShow(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        return time;
    }

    /**
     * 图片上传post请求
     * @param imagePath
     * @param url
     * @return
     */
    public static String ImagedoPost(String imagePath,String url) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String result = "error";
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("image", imagePath,
        RequestBody.create(MediaType.parse("image/png"), new File(imagePath)));
        RequestBody requestBody = builder.build();
        Request.Builder reqBuilder = new Request.Builder();
        Request request = reqBuilder.url(url).post(requestBody).build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
//            Log.d(TAG, "响应码 " + response.code());
            if (response.isSuccessful()) {
                String resultValue = response.body().string();
//                Log.d(TAG, "响应体 " + resultValue);
                return resultValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 选择相册中的图片
     * @param activity
     * @param requestCode
     */
    public static void SelectImage(Activity activity,int requestCode,int id){
        ImagePicker.getInstance()
                .setTitle("选择图片")//设置标题
                .showCamera(true)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(id)//设置最大选择图片数目(默认为1，单选)
//                        .setImagePaths(mImageList)//保存上一次选择图片的状态，如果不需要可以忽略
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(activity, requestCode);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
    }

    /**
     * 选择相册中的视频
     * @param activity
     * @param requestCode
     */
    public static void SelectVideo(Activity activity,int requestCode,int id){
        ImagePicker.getInstance()
                .setTitle("选择视频")//设置标题
                .showCamera(true)//设置是否显示拍照按钮
                .showImage(false)//设置是否展示图片
                .showVideo(true)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(id)//设置最大选择图片数目(默认为1，单选)
//                        .setImagePaths(mImageList)//保存上一次选择图片的状态，如果不需要可以忽略
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(activity, requestCode);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
    }

    /**
     * 解决ScrollView中ListView只显示一行的问题
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * OKHttp异步Get请求
     * @请求地址 url
     * @回调 callback
     */
    public static void OkGet(String url,Callback callback){
        // 异步GET请求
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 带参数的OkHttpPost请求
     * @参数关键字 key
     * @参数值 value
     * @请求地址 url
     * @回调函数 callback
     */
    public static void OKPostValue(String key,String value,String url,Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add(key, value)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 带2个参数的OkHttpPost请求
     * @参数关键字 key
     * @参数值 value
     * @请求地址 url
     * @回调函数 callback
     */
    public static void OKPostValue(String key,String value,String key2,String value2,String url,Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add(key, value)
                .add(key2, value2)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 不带参数的OkHttpPost请求
     * @请求地址 url
     * @回调 callback
     */
    public static void OKPostNovalue(String url,Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 保持不息屏
     * @param activity
     */
    public static void KeepScreenOn(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 去除状态栏
     * @param activity
     */
    public static void HideStatusBar(Activity activity) {
        ImmersionBar.with(activity).hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init();
    }

    /**
     * 全屏
     * @param activity
     */
    public static void SetFullScreen(Activity activity) {
        ImmersionBar.with(activity).init();
    }

    /**
     * 再按一次退出提醒
     * @上下文 context
     */
    public static void QuitShow(Activity activity ){
        if ((System.currentTimeMillis() - exitTime) > 2000) {//判断此次按键于上一次按键的时间差是否>2s
            Toast.makeText(activity, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();//纪录这次按键的时间，下次有用
            return;//时间差大于2s，退出返回事件
        }
        activity.finish();//时间差小于2s，销毁
    }

    /**
     * Banner轮播图
     * @banner控件 banner
     * @加载的图片 image
     */
    public static void BannerSet(Banner banner,int image1,int image2,int image3 ,int image4) {
        mImgList.add(image1);
        mImgList.add(image2);
        mImgList.add(image3);
        mImgList.add(image4);

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //  banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE); // 显示圆形指示器和标题（水平显示
        //设置图片加载器
        banner.setImageLoader(new MyLoader());
        //设置图片集合
        banner.setImages(mImgList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(mTitleList);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**
     * /横幅banner图片加载器
     */
    public static class MyLoader extends ImageLoader {
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
    
    /**
     * okhttp3上传图片
     * @param path  图片路径
     * @param url  图片上传请求接口
     * @return
     */
    public static Call UploadFile(String path, String url) {

        // 获取要上传的文件
        File mFile = new File(path);
        OkHttpClient client = new OkHttpClient.Builder().build();
        // 设置文件以及文件上传类型封装
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile);
        // 文件上传的请求体封装
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", mFile.getName(), requestBody)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();

        Call call = client.newCall(request);

        return call;
    }


    /**
     * 选择图片
     */
    public static void SelectImage(Activity activity, int code) {
        ImagePicker.getInstance()
                .setTitle("选择图片")//设置标题
                .showCamera(true)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
//                        .setImagePaths(mImageList)//保存上一次选择图片的状态，如果不需要可以忽略
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(activity, code);
    }

    ;

    /**
     * 选择视频
     */
    public static void SelectVideo(Activity activity, int code) {
        ImagePicker.getInstance()
                .setTitle("选择视频")//设置标题
                .showCamera(false)//设置是否显示拍照按钮
                .showImage(false)//设置是否展示图片
                .showVideo(true)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
//                        .setImagePaths(mImageList)//保存上一次选择图片的状态，如果不需要可以忽略
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(activity, code);
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String CurrentTimeWenzi() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        return time;
    }

    /**
     * 操作成功后2秒返回首页
     */
    public static void PublishSuccess(final Activity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.finish();
            }
        }, 2000);
    }

    /**
     * 1秒后跳转
     */
    public static void Delay1s(final Context context, final Activity activity, final Class<?> cls) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentToPage(context, cls);
                activity.finish();
            }
        }, 1000);
    }

    /**
     * 隐藏状态栏和导航栏、标题栏
     */
    public static void HideBarAll(Activity activity) {
        ImmersionBar.with(activity).hideBar(BarHide.FLAG_HIDE_BAR);
    }

    /**
     * 3秒后销毁页面
     */
    public static void Delay3s(final Activity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.finish();
            }
        }, 3000);
    }

    /**
     * 手势判断
     */
    public static void GestureDetectorAction(View view, final GestureDetector gestureDetector) {
        //布局监听
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true; // 注：返回true才能完整接收触摸事件
            }
        });
    }

    /**
     * 再按一次退出提醒
     */
    public static void QuitShowToHome(Activity activity) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {//判断此次按键于上一次按键的时间差是否>2s
            Toast.makeText(activity, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();//纪录这次按键的时间，下次有用
            return;//时间差大于2s，退出返回事件
        }
        activity.finishAffinity();//时间差小于2s，销毁
    }

    /**
     * OKhttp封装
     */
    public static void OKPost(HashMap<String, String> param, String url, Callback callback) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();
        //封装请求的参数
        if (!param.isEmpty()) {
            //遍历集合
            for (Map.Entry<String, String> entry : param.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody form = formBody.build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.post(form)
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }


    /**
     * 有跳转的确定对话框
     */
    public static void ConfirmShow(final Context context, final Activity activity, final Class<?> clss, String text) {
        new XPopup.Builder(context).asConfirm("", text,
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        activity.startActivity(new Intent(context, clss));
                        activity.finishAffinity();
                    }
                })
                .show();
    }

    /**
     * 无跳转的确定对话框
     */
    public static void NoneConfirmShow(Context context, String text, final Activity activity) {
        new XPopup.Builder(context).asConfirm("", text,
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        activity.finish();
                    }
                })
                .show();
    }

    /**
     * 默认的确定对话框
     */
    public static void DeafultConfirmShow(final Context context, String text, Activity activity, final String text2) {
        new XPopup.Builder(context).asConfirm("", text,
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        DialogShow(context, text2);
                    }
                })
                .show();
    }

    /**
     * 循环旋转动画
     */
    public static void setRotateAnim(CircleImageView circleImageView) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(INFINITE);
        rotateAnimation.setDuration(20000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        circleImageView.startAnimation(rotateAnimation);
    }

    /**
     * 倒计时
     */
    public static void initCountDownTimer(final TextView tv_time, final Activity activity, final Context context, final Class<?> cls) {
        CountDownTimer timer = new CountDownTimer(4000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                tv_time.setText(l / 1000 + "S");
            }

            @Override
            public void onFinish() {
                IntentToPage(context, cls);//倒计时结束后跳转
                activity.finish();
            }
        }.start();
    }

    /**
     * 时间提示
     */
    public static void initCalendarTime(final Context context, final String text) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String h;
                String m;
                h = String.valueOf(hourOfDay);
                m = String.valueOf(minute);
                if (hourOfDay < 10) {
                    h = "0" + hourOfDay;
                }
                if (minute < 10) {
                    m = "0" + hourOfDay;
                }
                DialogShow(context, h + ":" + m + text);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    /**
     * long类型转字符时间
     */
    public static String longToDate(long l){
        Date date = new Date(l);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        return sd.format(date);
    }

    /**
     * 设置导航栏颜色
     */
    public static void setSystemBarColor(Activity activity,int color) {
        ImmersionBar.with(activity).navigationBarColor(color);
    }

    /**
     * 底部tab切换颜色变化4
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public static void setCurPoint4(Activity activity, ImageView ivone, ImageView ivtwo, ImageView ivthree, ImageView ivfour,
                                   int one, int two, int three, int four){
        ivone.setImageDrawable(activity.getResources().getDrawable(one));
        ivtwo.setImageDrawable(activity.getResources().getDrawable(two));
        ivthree.setImageDrawable(activity.getResources().getDrawable(three));
        ivfour.setImageDrawable(activity.getResources().getDrawable(four));
    }

    /**
     * 底部tab切换颜色变化3
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public static void setCurPoint3(Activity activity, ImageView ivone, ImageView ivtwo, ImageView ivthree,
                                    int one, int two, int three){
        ivone.setImageDrawable(activity.getResources().getDrawable(one));
        ivtwo.setImageDrawable(activity.getResources().getDrawable(two));
        ivthree.setImageDrawable(activity.getResources().getDrawable(three));
    }

    /**
     * 底部tab切换颜色变化3
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public static void setTextCurPoint3(Activity activity, ImageView ivone, ImageView ivtwo, ImageView ivthree,
                                    int one, int two, int three){
        ivone.setImageDrawable(activity.getResources().getDrawable(one));
        ivtwo.setImageDrawable(activity.getResources().getDrawable(two));
        ivthree.setImageDrawable(activity.getResources().getDrawable(three));
    }

}
