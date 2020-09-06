使用方法

1.在项目project的gradle里面添加
        
allprojects {

		repositories {

			...
			maven { url 'https://jitpack.io' }

		}

	}


2.在APP Module的gradle里面添加 
      
  dependencies {

	        implementation 'com.gitee.yywds:wzutils:1.0.0'

	}

如果依赖冲突在APP Module的gradle里面添加

 packagingOptions {

        //加上这些代码
        exclude  'lib/arm64-v8a/*'
        exclude  'lib/x86_64/*'
        exclude  'lib/armeabi-v7a/*'
        exclude  'lib/x86/*'
        
    }


3.使用演示

3.1 一行代码实现对话框

CommonUtils.DialogShow(MainActivity.this,"我是工具类");

或者直接

DialogShow(context,"我是工具类");


<img src="https://images.gitee.com/uploads/images/2020/0608/102942_b3fa525d_5307138.jpeg" width="30%" hight="30%" > <br/><br/><br/>




3.2一行代码实现仿微信消息通知，可自定义图标

 NWeixin(context,R.drawable.launcher,"大家好，欢迎使用工具类");
 
                                                                                  

<img src="https://images.gitee.com/uploads/images/2020/0608/234513_eec80c5e_5307138.jpeg" width="30%" hight="30%" >&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://images.gitee.com/uploads/images/2020/0608/103616_6cf6b8f4_5307138.jpeg" width="30%" hight="30%" ><br/><br/><br/>




3.3一行代码实现两个同时对话框，可自定义选择图标

SelectDialogMessageShow(context,"请选择","点击了确定");<br/>
SelectDialogMessageShow(R.drawable.launcher,context,"请选择","点击了确定");//自定义选择图标



<img src="https://images.gitee.com/uploads/images/2020/0608/105357_bc93c6a0_5307138.jpeg" width="30%" hight="30%" ><img src="https://images.gitee.com/uploads/images/2020/0608/105441_d5bcf92d_5307138.jpeg" width="30%" hight="30%" ><img src="https://images.gitee.com/uploads/images/2020/0608/234128_3d676c57_5307138.jpeg" width="30%" hight="30%" ><br/><br/><br/>

3.4一行代码实现SharedPreferences的存储<br/>
 SaveInfo(context,"Utils","myutils","我是工具类");<br/><br/>

3.5一行代码实现SharedPreferences的获取<br/>
 SharedInfo(context,"Utils","myutils");<br/><br/>

3.6一行代码实现清除SharedPreferences<br/>
 ClearInfo(context,"Utils");<br/><br/>

3.7一行代码实现Banner轮播图<br/>
 BannerSet(banner,R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four);<br/><br/>


<img src="https://images.gitee.com/uploads/images/2020/0608/235625_167def18_5307138.jpeg" width="30%" hight="30%" >
<img src="https://images.gitee.com/uploads/images/2020/0608/235713_7d05f8a4_5307138.jpeg" width="30%" hight="30%" >
<img src="https://images.gitee.com/uploads/images/2020/0608/235744_d69f91dd_5307138.jpeg" width="30%" hight="30%" >
<br/><br/><br/>

3.8动态权限申请<br/>
 PerMission(MainActivity.this, new Action() {<br/>
            @Override<br/>
            public void onAction(List<String> permissions) {<br/>
                //权限申请成功的执行的动作<br/><br/>
            }<br/>
        });<br/><br/>
<img src="https://images.gitee.com/uploads/images/2020/0609/000919_14f2679f_5307138.jpeg" width="30%" hight="30%" >
<img src="https://images.gitee.com/uploads/images/2020/0609/000945_c9ea8a67_5307138.jpeg" width="30%" hight="30%" >
<img src="https://images.gitee.com/uploads/images/2020/0609/001005_420ad279_5307138.jpeg" width="30%" hight="30%" >
<br/><br/><br/><br/>

3.9一行代码实现“再按一次退出”<br/><br/>
 @Override<br/>
    public void onBackPressed() {<br/>
        QuitShow(MainActivity.this);<br/>
    }<br/><br/><br/>
<img src="https://images.gitee.com/uploads/images/2020/0609/001513_a162ea89_5307138.jpeg" width="30%" hight="30%" ><br/><br/><br/><br/>

3.10一行代码实现匹配字符高亮显示<br/>
 String text = "我是机器人，我是机器人，我是机器人，我是机器人，我是机器人我是机器人";<br/>
 show.setText(MatcherSearchText(Color.parseColor("#ff0000"),text,"机器"));<br/><br/>

<img src="https://images.gitee.com/uploads/images/2020/0609/002249_349c9bfc_5307138.jpeg" width="30%" hight="30%" ><br/><br/><br/><br/>


3.11  OKHttp的Post请求使用<br/>
String url = "http://t.weather.sojson.com/api/weather/city/101030100";<br/>
        OKPostNovalue(url, new Callback() {<br/>
            @Override<br/>
            public void onFailure(@NotNull Call call, @NotNull IOException e) {<br/>
                //请求失败的动作<br/><br/>
            }<br/>
@Override<br/>
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {<br/>
              //请求成功的动作<br/>   
            }<br/>
        });<br/><br/>


3.12一行代码实现android-async-http的Post请求<br/>
 PostHttpNoValue(url);<br/><br/><br/>

3.13一行代码实现手机屏幕不息屏<br/>
 KeepScreenOn(MainActivity.this);<br/><br/><br/>

3.14一行代码实现仿微信图片选择<br/>
SelectImage(MainActivity.this,REQUEST_SELECT_IMAGES_CODE,1);<br/><br/>
<img src="https://images.gitee.com/uploads/images/2020/0609/004759_785b42dc_5307138.jpeg" width="30%" hight="30%" ><br/><br/><br/><br/>


3.15一行代码实现仿微信视频选择<br/>
SelectVideo(MainActivity.this,REQUEST_SELECT_IMAGES_CODE,1);<br/><br/>
<img src="https://images.gitee.com/uploads/images/2020/0609/004831_32ba15a6_5307138.jpeg" width="30%" hight="30%" ><br/><br/><br/><br/>

................<br/><br/><br/>

其他功能的调用在这就不一一例举了，具体调用请看我的开源代码。<br/><br/>在此特别感谢项目中使用到开源代码的各位作者的贡献！


  


