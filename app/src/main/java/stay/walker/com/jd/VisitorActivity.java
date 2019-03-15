package stay.walker.com.jd;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.socks.library.KLog;

import java.util.Arrays;
import java.util.List;

import stay.walker.com.retrofitdemo.R;
import stay.walker.com.scrollRecyclerview.VisitorPopupWindow;

public class VisitorActivity extends AppCompatActivity implements View.OnClickListener, VisitorPopupWindow.OnSwitchListner, View.OnFocusChangeListener, TextWatcher {

    private RadioGroup mRadioG;
    private Toolbar mToolbar;
    private TextView mTvTime;

    private String mRsnDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.debug();
        setContentView(R.layout.visitor);
        initView();
        initData();
    }

    private void initData() {
        mRsnDesc = getString(R.string.visitor_business);
    }

    private AppCompatEditText mEdPhone;
    private AppCompatEditText mEdId;

    private void initView() {
        mToolbar = findViewById(R.id.detail_toolbar);
        findViewById(R.id.btn_commit).setOnClickListener(this);
        mTvTime = findViewById(R.id.tv_time);
        mTvTime.setOnClickListener(this);
        mRadioG = findViewById(R.id.rg);
        mRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_interview:
                        mRsnDesc = getString(R.string.visitor_interview);
                        break;
                    case R.id.rb_other:
                        mRsnDesc = getString(R.string.visitor_other);
                        break;
                    default:
                        break;
                }
            }
        });

        mEdPhone = findViewById(R.id.ed_phone);
        mEdPhone.setOnFocusChangeListener(this);
        mEdPhone.addTextChangedListener(this);

        mEdId = findViewById(R.id.ed_id);
        mEdId.setOnFocusChangeListener(this);
        mEdId.addTextChangedListener(this);
    }

    List<String> mStrings;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_commit) {
            mStrings = Arrays.asList("aaa", "bbb");
            VisitorPopupWindow window = new VisitorPopupWindow(this, mStrings, this);
            window.showAsDropDown(mToolbar);
        } else if (view.getId() == R.id.tv_time) {

        }
    }

    private void checkoutAllInputText() {

    }

    @Override
    public void onSwitch(int position) {
        KLog.e(mStrings.get(position));
        mTvTime.setText(mStrings.get(position));
    }

    private View mCurrentView;

    @Override
    public void onFocusChange(View view, boolean b) {
        KLog.e("view.getId() - " + view.getId());

        if (mCurrentView == null) {
            mCurrentView = view;
        }
        textCheck();

        mCurrentView = view;
    }

    private void textCheck() {
        if (mText == null || mText.length() <= 0) {
            return;
        }
        switch (mCurrentView.getId()) {
            case R.id.ed_phone:
                if (mText.length() != 11) {
                    ToastUtils.showShort("电话号码输入有误!");
                }
                break;
            case R.id.ed_id:
                if (!isIDNumber(mText)) {
                    ToastUtils.showShort("身份证输入有误!");
                }
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    private String mText;

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        KLog.e("charSequence - " + charSequence.toString());

        switch (mCurrentView.getId()) {
            case R.id.ed_phone:
                KLog.e("ed_phone - ");
                break;
            case R.id.ed_id:
                KLog.e("ed_id - ");
                break;
            default:
                break;
        }
        mText = charSequence.toString();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        KLog.e("editable.toString() " + editable.toString());
    }

    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        boolean matches = IDNumber.matches(regularExpression);

        if (!matches) {
            return false;
        }
        //判断第18位校验值
        if (IDNumber.length() == 18) {
            try {
                char[] charArray = IDNumber.toCharArray();
                //前十七位加权因子
                int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                //这是除以11后，可能产生的11位余数对应的验证码
                String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                int sum = 0;
                for (int i = 0; i < idCardWi.length; i++) {
                    int current = Integer.parseInt(String.valueOf(charArray[i]));
                    int count = current * idCardWi[i];
                    sum += count;
                }
                char idCardLast = charArray[17];
                int idCardMod = sum % 11;
                if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                    return true;
                } else {
                    System.out.println("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase() +
                            "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("异常:" + IDNumber);
                return false;
            }
        }
        return true;
    }


    /**
     * 获取点击事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isShouldHideKeyBord(view, ev)) {
                hideSoftInput(view.getWindowToken());
                textCheck();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定当前是否需要隐藏
     */
    protected boolean isShouldHideKeyBord(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
