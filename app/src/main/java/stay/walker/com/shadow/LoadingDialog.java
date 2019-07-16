package stay.walker.com.shadow;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.socks.library.KLog;

import stay.walker.com.retrofitdemo.R;


/**
 * @author 009632
 */
public class LoadingDialog extends DialogFragment {

    private TextView mTvText;
    private boolean isCancelOutside = false;
    private String mText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(Window.FEATURE_NO_TITLE, R.style.IosDialogStyle);
        setCancelable(true);
    }

    private ImageView iv_face;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shadow_dialog_aid, container);
        mTvText = view.findViewById(R.id.tipTextView);
        if (! TextUtils.isEmpty(mText)) {
            mTvText.setText(mText);
        }

        //解决办法就是先得到原来的params然后，再设置回去。
        iv_face = view.findViewById(R.id.iv_face);
//        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(((ConstraintLayout.LayoutParams) iv_face.getLayoutParams()));
//        KLog.e("h = " + params.height + ", w = " + params.width);
//        KLog.e("y = " + fl.getY() + ", x = " + fl.getX());
//        KLog.e("x = " + params.editorAbsoluteX + ", y = " + params.editorAbsoluteY);
//        params.bottomToBottom = 10;
//        params.editorAbsoluteY = 20;
//        params.editorAbsoluteX = 10;
//        params.bottomMargin = 10;
//

        params.startToStart = 564;
        params.topToTop = 702;
        // 564, y = 702.0
//        iv_face.setLayoutParams(params);


//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            DisplayMetrics dm = new DisplayMetrics();
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
//            dialog.getWindow().setAttributes(attributes);
//            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
//        }

        return view;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 设置提示信息
     *
     * @param message
     * @return
     */
    public void setMessage(String message) {
        mText = message;
        if (!isVisible()) {
            return;
        }

        KLog.e("mTvText - " + mTvText);
        if (mTvText != null && !TextUtils.isEmpty(mText)) {
            mTvText.setText(mText);
        }
    }

    /**
     * 设置是否可以取消
     *
     * @param isCancelOutside
     * @return
     */
    public LoadingDialog setCancelOutside(boolean isCancelOutside) {
        this.isCancelOutside = isCancelOutside;
        return this;
    }

    private DismissCallback mDismissCallback;

    public void setDismissCallback(DismissCallback mDismissCallback) {
        setCancelable(true);
        this.mDismissCallback = mDismissCallback;
    }

    public interface DismissCallback {
        void doDismiss();
    }

}