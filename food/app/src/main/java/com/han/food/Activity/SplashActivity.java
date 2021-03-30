package com.han.food.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.han.food.Interface.DelayCallback;
import com.han.food.R;
import com.han.food.Util.Util;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class SplashActivity extends AppCompatActivity {

    Animation Bounce_Animation,Text_Animation;
    @BindView(R.id.iv_splash_logo)
    ImageView iv_splash_logo;

    @BindView(R.id.tv_title)
    TextView tv_title;



    /**
     * client 정보를 넣어준다.
     */
    private static String OAUTH_CLIENT_ID = "oBE1aXgQYiVgEfXMEhZO";
    private static String OAUTH_CLIENT_SECRET = "0FLfaX264b";
    private static String OAUTH_CLIENT_NAME = "Food App";
    private static OAuthLogin mOAuthLoginInstance;
    private OAuthLoginButton mOAuthLoginButton;
    static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        ButterKnife.bind(this);

        Bounce_Animation = AnimationUtils.loadAnimation(mContext,R.anim.logo_anim);
        Text_Animation =  AnimationUtils.loadAnimation(mContext,R.anim.splash_text_anim);

        iv_splash_logo.setVisibility(View.VISIBLE);
        iv_splash_logo.startAnimation(Bounce_Animation);
        tv_title.setVisibility(View.VISIBLE);
        tv_title.startAnimation(Text_Animation);
        initData();
        mOAuthLoginButton.startAnimation(Text_Animation);

        //GoToMainActivity(mContext);
    }

    public void GoToMainActivity(Context mContext){
        Util.getInstance(mContext).DelayCallback(3500, new DelayCallback() {
            @Override
            public void DoSomething() {
                Intent mIntent = new Intent(mContext,LoginActivity.class);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                } else mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(mIntent);
                ((Activity) mContext).overridePendingTransition(0, 0);
            }
        });
    }



    // 네이버 로그인을 위한 인스턴스를 선언하고, 클라이어튼 네임과 시크릿코드를 이용해 초기화
    private void initData() {
        mOAuthLoginInstance = OAuthLogin.getInstance();
        mOAuthLoginInstance.showDevelopersLog(true);
        mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);
        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
    }




    @Override
    protected void onResume() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();
    }

    /**
     * startOAuthLoginActivity() 호출시 인자로 넘기거나, OAuthLoginButton 에 등록해주면 인증이 종료되는 걸 알 수 있다.
     */
    static private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                // 로그인 성공시, MainActivity로 넘어간다.
                Intent mIntent = new Intent(mContext,MainActivity.class);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                } else mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(mIntent);
                ((Activity) mContext).overridePendingTransition(0, 0);
            } else {

            }
        }

    };
}