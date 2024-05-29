package com.wzr.food.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.wzr.food.MainActivity;
import com.wzr.food.R;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.logic.model.CallBackBean;
import com.wzr.food.logic.model.LoginBean;
import com.wzr.food.logic.model.RegisterBean;
import com.wzr.food.repository.Repository;
import com.wzr.food.ui.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnKeyListener {

    private Button loginRegisterBtn;  // ActionBar完成按钮

    private TextView switchModeBtn;  // 注册/登录切换按钮

    private EditText loginAccountEdit;

    private EditText loginPasswordEdit;

    private EditText registerAccountEdit;

    private EditText registerNickNameEdit;

    private EditText registerPasswordEdit;

    private View loginLayout;

    private View registerLayout;

    private AppCompatCheckBox autoLoginCheckBox;

    private boolean registerMode = false; // 注册模式

    private boolean registerPanelInited = false; // 注册面板是否初始化

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initloginRegisterBtn();
        setupLoginPanel();
        setupRegisterPanel();
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initView() {

    }

    /**
     * 登录注册按钮
     */
    private void initloginRegisterBtn() {
        loginRegisterBtn = findViewById(R.id.btm_login_register);
        loginRegisterBtn.setOnClickListener(v -> {
            if (registerMode) {
                register();
            } else {
                login();
            }

        });
    }

    private void login() {
        String username = loginAccountEdit.getText().toString().trim();
        String password = loginPasswordEdit.getText().toString().trim();

        Repository.getLoginService().login(username, password).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                LoginBean loginBean = response.body();
                MessageDao.saveLoginBean(loginBean);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

            }
        });
    }

    private void register() {
        if (!registerMode || !registerPanelInited) {
            return;
        }
        if (!checkRegisterContentValid()) {
            return;
        }

        String username = registerAccountEdit.getText().toString().trim();
        String password = registerPasswordEdit.getText().toString().trim();
        String nickname = registerNickNameEdit.getText().toString().trim();

        Repository.getRegisterService().register(new RegisterBean(nickname, password, username)).enqueue(new Callback<CallBackBean>() {
            @Override
            public void onResponse(Call<CallBackBean> call, Response<CallBackBean> response) {
                switchMode();  // 切换回登录
                loginAccountEdit.setText(username);
                loginPasswordEdit.setText(password);
                registerAccountEdit.setText("");
                registerNickNameEdit.setText("");
                registerPasswordEdit.setText("");
            }

            @Override
            public void onFailure(Call<CallBackBean> call, Throwable t) {

            }
        });
    }

    private boolean checkRegisterContentValid() {
        if (!registerMode || !registerPanelInited) {
            return false;
        }
        // 帐号限制检查
        String account = registerAccountEdit.getText().toString().trim();
        if (account.length() <= 0 || account.length() > 20) {
            Toast.makeText(this, "R.string.register_account_tip", Toast.LENGTH_SHORT).show();
            return false;
        }
        // 昵称检查
        String nick = registerNickNameEdit.getText().toString().trim();
        if (nick.length() <= 0 || nick.length() > 10) {
            Toast.makeText(this, "R.string.register_nick_name_tip", Toast.LENGTH_SHORT).show();
            return false;
        }
        // 密码检查
        String password = registerPasswordEdit.getText().toString().trim();
        if (password.length() < 6 || password.length() > 20) {
            Toast.makeText(this, R.string.register_password_tip, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 登录面板
     */
    private void setupLoginPanel() {
        loginAccountEdit = findViewById(R.id.edit_login_account);
        loginPasswordEdit = findViewById(R.id.edit_login_password);

        loginAccountEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        loginPasswordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});

        loginAccountEdit.addTextChangedListener(textWatcher);
        loginPasswordEdit.addTextChangedListener(textWatcher);

        loginPasswordEdit.setOnKeyListener(this);

        autoLoginCheckBox = findViewById(R.id.cb_auto_login);

//        if(!autoLoginCheckBox.isChecked())
//        {
//
//        }
    }

    /**
     * 注册面板
     */
    private void setupRegisterPanel() {
        loginLayout = findViewById(R.id.login_layout);
        registerLayout = findViewById(R.id.register_layout);
        switchModeBtn = findViewById(R.id.register_login_tip);
        switchModeBtn.setOnClickListener(v -> switchMode());
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * ***************************************** 注册/登录切换 **************************************
     */
    private void switchMode() {
        registerMode = !registerMode;
        if (registerMode && !registerPanelInited) {
            registerAccountEdit = findViewById(R.id.edit_register_account);
            registerNickNameEdit = findViewById(R.id.edit_register_nickname);
            registerPasswordEdit = findViewById(R.id.edit_register_password);

            registerAccountEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
            registerNickNameEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
            registerPasswordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

            registerAccountEdit.addTextChangedListener(textWatcher);
            registerNickNameEdit.addTextChangedListener(textWatcher);
            registerPasswordEdit.addTextChangedListener(textWatcher);

            registerPanelInited = true;
        }

        loginLayout.setVisibility(registerMode ? View.GONE : View.VISIBLE);
        registerLayout.setVisibility(registerMode ? View.VISIBLE : View.GONE);

        switchModeBtn.setText(registerMode ? R.string.login_has_account : R.string.register);

        if (registerMode) {
            loginRegisterBtn.setText("注册");
        } else {
            loginRegisterBtn.setText("登录");
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }
}