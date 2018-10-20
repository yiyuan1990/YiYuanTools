package com.sjr.yiyuantools.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.api.Constant;
import com.sjr.yiyuantools.base.BaseActivity;
import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.common.MD5Util;
import com.sjr.yiyuantools.common.SPUtil;
import com.sjr.yiyuantools.common.assist.Check;
import com.sjr.yiyuantools.contract.LoginContract;
import com.sjr.yiyuantools.entity.Login;
import com.sjr.yiyuantools.presenter.LoginPresenter;
import com.sjr.yiyuantools.utils.ToastUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class LoginAct extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.etPw)
    EditText etPw;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnWeChat)
    ImageView btnWeChat;
    String userName, userPw, md5Pw;
    @BindView(R.id.free_login)
    TextView freeLogin;

    @Override
    public int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        etUserName.setHintTextColor(getResources().getColor(R.color.white));
        etPw.setHintTextColor(getResources().getColor(R.color.white));
        String userName = SPUtil.getString(this, "userName", "");
        String userPw = SPUtil.getString(this, "userPw", "");
        etUserName.setText(userName);
        etPw.setText(userPw);

    }

    @Override
    public void result(BaseResponse<Login> data) {
        Login data1 = data.getData();
        int err_code = data1.getErr_code();
        if (err_code == 0) {
            keepUserData(data1);//保存登录用户资料
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            ToastUtil.showShortToast(data1.getErr_msg());
            btnLogin.setBackground(getResources().getDrawable(R.drawable.circular_green_bg));
            btnLogin.setClickable(true);
        }

    }

    @Override
    public void err(int err) {
        if (err == 999) {
            ToastUtil.showShortToast("登录失败，请稍等重试");
            btnLogin.setBackground(getResources().getDrawable(R.drawable.circular_green_bg));
            btnLogin.setClickable(true);
        }
    }

    private void keepUserData(Login data) {
        String token = data.getToken();
        String uuid = data.getUuid();
        SPUtil.putString(this, "token", token);
        SPUtil.putString(this, "uuid", uuid);
        SPUtil.putString(this, "loginState", "1");
        SPUtil.putString(this, "userName", userName);
        SPUtil.putString(this, "userPw", userPw);
        SPUtil.putString(this, "md5Pw", md5Pw);

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(ActivityEvent.STOP);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnLogin, R.id.btnWeChat, R.id.free_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
//                http://hn2.api.okayapi.com/?s=App.User.Login&username=yiyuan&password=25d55ad283aa400af464c76d713c07ad
                btnLogin.setBackground(getResources().getDrawable(R.drawable.circula_gray_bg));
                btnLogin.setClickable(false);
                userName = etUserName.getText().toString().trim();
                userPw = etPw.getText().toString().trim();
                if (Check.isEmpty(userName) || Check.isEmpty(userPw)) {
                    btnLogin.setBackground(getResources().getDrawable(R.drawable.circular_green_bg));
                    btnLogin.setClickable(true);
                    ToastUtil.showShortToast("用户名或者密码不能为空");
                    btnLogin.setBackground(getResources().getDrawable(R.drawable.circular_green_bg));
                    btnLogin.setClickable(true);
                } else {
                    md5Pw = MD5Util.getMD5Str(userPw);
                    if (Check.isEmpty(md5Pw)) {
                        ToastUtil.showShortToast("密码不符合规范，请重新输入！");
                        btnLogin.setBackground(getResources().getDrawable(R.drawable.circular_green_bg));
                        btnLogin.setClickable(true);
                    } else {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("app_key", Constant.APP_KEY);
                        params.put("username", userName);
                        params.put("password", md5Pw);
                        getPresenter().login(params, false, false);
                    }
                }


                break;
            case R.id.btnWeChat:

                break;
            case R.id.free_login:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
