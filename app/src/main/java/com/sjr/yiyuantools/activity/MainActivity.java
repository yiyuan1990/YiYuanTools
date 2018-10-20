package com.sjr.yiyuantools.activity;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.adapter.AdMainViewPager;
import com.sjr.yiyuantools.base.BaseActivity;
import com.sjr.yiyuantools.base.BasePresenter;
import com.sjr.yiyuantools.base.BaseView;
import com.sjr.yiyuantools.common.BottomNavigationViewHelper;
import com.sjr.yiyuantools.common.statusbar.BSUtils;
import com.sjr.yiyuantools.entity.UpdateResult;
import com.sjr.yiyuantools.fragment.GiveFragment;
import com.sjr.yiyuantools.fragment.HomeFragment;
import com.sjr.yiyuantools.fragment.MyFragment;
import com.sjr.yiyuantools.fragment.NearFragment;
import com.sjr.yiyuantools.fragment.ShopFragment;
import com.sjr.yiyuantools.update.UpdateHttpUtil;
import com.sjr.yiyuantools.utils.ToastUtil;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.listener.ExceptionHandler;
import com.vector.update_app.listener.IUpdateDialogFragmentListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    private MenuItem menuItem;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void init() {
        BSUtils.setStatusBar(this, R.color.light_red_a, false);


        checkAndUpdateApp();
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        //控制中间那个tab的显示
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigation.getChildAt(0);
        //这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
        //加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(this).inflate(R.layout.tab_main, menuView, false);
        //添加到Tab上
        itemView.addView(badge);
        final ImageView ivMid = badge.findViewById(R.id.ivMid);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_home:
                        viewpager.setCurrentItem(0);
                        BSUtils.setStatusBar(MainActivity.this, R.color.light_red_a, false);
                        ivMid.setPadding(20, 20, 20, 20);
                        break;
                    case R.id.item_near:
                        viewpager.setCurrentItem(1);
                        BSUtils.setStatusBar(MainActivity.this, R.color.tran, true);
                        ivMid.setPadding(20, 20, 20, 20);
                        break;
                    case R.id.item_release:
                        ivMid.setPadding(10, 10, 10, 10);
                        viewpager.setCurrentItem(2);
                        BSUtils.setStatusBar(MainActivity.this, R.color.tran, true);
                        break;
                    case R.id.item_shop:
                        viewpager.setCurrentItem(3);
                        BSUtils.setStatusBar(MainActivity.this, R.color.tran, true);
                        ivMid.setPadding(20, 20, 20, 20);
                        break;
                    case R.id.item_my:
                        viewpager.setCurrentItem(4);
                        BSUtils.setStatusBar(MainActivity.this, R.color.tran, false);
                        ivMid.setPadding(20, 20, 20, 20);
                        break;
                }
                return false;
            }
        });

        viewpager.setOffscreenPageLimit(4);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //禁止ViewPager滑动
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        setupViewPager(viewpager);

    }

    private void setupViewPager(ViewPager viewpager) {
        AdMainViewPager adapter = new AdMainViewPager(getSupportFragmentManager());
        HomeFragment homeFragment = new HomeFragment();
        NearFragment nearFragment = new NearFragment();
        GiveFragment giveFragment = new GiveFragment();
        ShopFragment shopFragment = new ShopFragment();//商铺
        MyFragment myFragment = new MyFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(nearFragment);
        adapter.addFragment(giveFragment);//发布
        adapter.addFragment(shopFragment);//商铺
        adapter.addFragment(myFragment);
        viewpager.setAdapter(adapter);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            exit();
        }
        return true;
    }

    private long time = 0;//退出方法

    private void exit() {//如果在两秒大于2秒
        if (System.currentTimeMillis() - time > 2000) {//获得当前的时间
            time = System.currentTimeMillis();
            ToastUtil.showShortToast("再点击一次退出应用程序");
        } else {//点击在两秒以内
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ActsManager.getAppManager().AppExit(YiYuanApp.getContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    /**
     * 检查app版本  更新版本
     */
    private String mUpdateUrl = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json.txt";
    private String mUpdateUrl1 = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json1.txt";
    private String mApkFileUrl = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/apk/sample-debug.apk";
    private String upUrl = "http://hn2.api.okayapi.com/?s=App.Table.Get&model_name=app_update&check_code=OKAYAPI&id=0";

    private void checkAndUpdateApp() {

        //默认更新方式
//        new UpdateAppManager.Builder()
//                .setActivity(this)
//                .setUpdateUrl(mUpdateUrl)
//                .handleException(new ExceptionHandler() {
//                    @Override
//                    public void onException(Exception e) {
//                        e.printStackTrace();
//                    }
//                })
//                .setHttpManager(new UpdateHttpUtil())
//                .build()
//                .update();

        //自定义接口协议
        Map<String, String> map = new HashMap<String, String>();
//        map.put("appKey", "ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f");
//        map.put("appVersion", AppUpdateUtils.getVersionName(this));
//        map.put("key1", "value2");
//        map.put("key2", "value3");
        map.put("s", "App.Table.Get");
        map.put("app_key", "F11F22AB37B152404A1898CF4FB26FF1");
        map.put("model_name", "app_update");
        map.put("check_code", "OKAYAPI");
        map.put("id", "1");
        new UpdateAppManager.Builder()
                .setActivity(this)
                .setHttpManager(new UpdateHttpUtil())
                .setUpdateUrl(upUrl)
                .setPost(true)
                .setParams(map)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                .setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
                .setTopPic(R.mipmap.app_update_top)
                .setThemeColor(0xffffac5d)
                .setUpdateDialogFragmentListener(new IUpdateDialogFragmentListener() {
                    @Override
                    public void onUpdateNotifyDialogCancel(UpdateAppBean updateApp) {

                    }
                })
                .build()
                .checkNewApp(new UpdateCallback() {
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        Gson mGson = new Gson();
                        UpdateResult UpdateResult = mGson.fromJson(json, UpdateResult.class);
                        UpdateResult.DataBeanX.DataBean dataBean = UpdateResult.getData().getData();


                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        updateAppBean
                                //（必须）是否更新Yes,No
                                .setUpdate(dataBean.getIsUpdate())
                                //（必须）新版本号，
                                .setNewVersion(dataBean.getNewVersion())
                                //（必须）下载地址
                                .setApkFileUrl(dataBean.getApkFileUrl())
                                //测试下载路径是重定向路径
//                                    .setApkFileUrl("http://openbox.mobilem.360.cn/index/d/sid/3282847")
//                                    .setUpdateDefDialogTitle(String.format("AppUpdate 是否升级到%s版本？", newVersion))
                                //（必须）更新内容
                                .setUpdateLog(dataBean.getUpdateLog())
                                //测试内容过度
//                                    .setUpdateLog("测试")
//                                    .setUpdateLog("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16")
//                                    .setUpdateLog("今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说\r\n")
                                //大小，不设置不显示大小，可以不设置
                                .setTargetSize(dataBean.getApkSize())
                                //是否强制更新，可以不设置
                                .setConstraint(false)
                                //设置md5，可以不设置
                                .setNewMd5("");


//                        try {
//                            JSONObject jsonObject = new JSONObject(json);
//                            final String newVersion = jsonObject.optString("newVersion");
//                            updateAppBean
//                                    //（必须）是否更新Yes,No
//                                    .setUpdate(jsonObject.optString("isUpdate"))
//                                    //（必须）新版本号，
//                                    .setNewVersion(newVersion)
//                                    //（必须）下载地址
//                                    .setApkFileUrl(jsonObject.optString("apkFileUrl"))
//                                    //测试下载路径是重定向路径
////                                    .setApkFileUrl("http://openbox.mobilem.360.cn/index/d/sid/3282847")
////                                    .setUpdateDefDialogTitle(String.format("AppUpdate 是否升级到%s版本？", newVersion))
//                                    //（必须）更新内容
//                                    .setUpdateLog(jsonObject.optString("updateLog"))
//                                    //测试内容过度
////                                    .setUpdateLog("测试")
////                                    .setUpdateLog("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16")
////                                    .setUpdateLog("今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说\r\n")
//                                    //大小，不设置不显示大小，可以不设置
//                                    .setTargetSize(jsonObject.optString("apkSize"))
//                                    //是否强制更新，可以不设置
//                                    .setConstraint(true)
//                                    //设置md5，可以不设置
//                                    .setNewMd5(jsonObject.optString("new_md5"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                    }

                    @Override
                    protected void onAfter() {
//                        sad.dismiss();
//                        sad = null;
                    }

                    @Override
                    protected void noNewApp(String error) {

                    }

                    @Override
                    protected void onBefore() {

                    }
                });

    }

}
