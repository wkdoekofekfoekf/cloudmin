package com.han.food.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.han.food.R;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

public class LoginActivity extends AppCompatActivity {

    /**
     * client 정보를 넣어준다.
     */
    private static String OAUTH_CLIENT_ID = "oBE1aXgQYiVgEfXMEhZO";
    private static String OAUTH_CLIENT_SECRET = "0FLfaX264b";
    private static String OAUTH_CLIENT_NAME = "Food App";
    private static OAuthLogin mOAuthLoginInstance;
    private static Context mContext;
    private OAuthLoginButton mOAuthLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext  = this;
        setContentView(R.layout.activity_login2);
        initData();
        this.setTitle("OAuthLoginSample Ver." + OAuthLogin.getVersion());
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
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            } else {

            }
        }

    };
}