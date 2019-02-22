package stay.walker.com.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stay.walker.com.retrofitdemo.R;


public class InputDialog {

    private InputDialog() {
    }

    private AlertDialog alertDialog;
    private Context context;

    private ViewGroup bkg;
    private TextView txtDialogTitle;
    private TextView txtDialogTip;
    private EditText txtInput;
    private ImageView splitHorizontal;
    private ImageView splitVertical;

    private Button btnSelectNegative;
    private Button btnSelectPositive;

    private String title;
    private String message;

    //Fast Function
    public static InputDialog show(Context context, String title, String message) {
        InputDialog inputDialog = build(context, title, message, "确定", "取消", null);
        inputDialog.showDialog();
        return inputDialog;
    }

    public static InputDialog build(Context context, String title, String message, String okButtonCaption,
                                    String cancelButtonCaption, DialogInterface.OnClickListener onCancelButtonClickListener) {
        synchronized (InputDialog.class) {
            InputDialog inputDialog = new InputDialog();
            inputDialog.alertDialog = null;
            inputDialog.context = context;
            inputDialog.title = title;
            inputDialog.message = message;
            return inputDialog;
        }
    }


    public void showDialog() {

        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_select_ios, null);

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context, R.style.lightMode);
        alertDialog = builder.create();
        alertDialog.setView(rootView);

        Window window = alertDialog.getWindow();
        window.setWindowAnimations(R.style.iOSAnimStyle);

        bkg = (RelativeLayout) rootView.findViewById(R.id.bkg);
        txtDialogTitle = rootView.findViewById(R.id.txt_dialog_title);
        txtDialogTip = rootView.findViewById(R.id.txt_dialog_tip);
        txtInput = rootView.findViewById(R.id.txt_input);
        splitHorizontal = rootView.findViewById(R.id.split_horizontal);
        btnSelectNegative = rootView.findViewById(R.id.btn_selectNegative);
        splitVertical = rootView.findViewById(R.id.split_vertical);
        btnSelectPositive = rootView.findViewById(R.id.btn_selectPositive);
        txtInput = rootView.findViewById(R.id.txt_input);


        ImageView splitVertical = rootView.findViewById(R.id.split_vertical);
        splitVertical.setVisibility(View.VISIBLE);
        txtInput.setVisibility(View.VISIBLE);

        if (isNull(title)) {
            txtDialogTitle.setVisibility(View.GONE);
        } else {
            txtDialogTitle.setVisibility(View.VISIBLE);
            txtDialogTitle.setText(title);
        }
        if (isNull(message)) {
            txtDialogTip.setVisibility(View.GONE);
        } else {
            txtDialogTip.setVisibility(View.VISIBLE);
            txtDialogTip.setText(message);
        }

        btnSelectPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btnSelectNegative.setVisibility(View.VISIBLE);
        btnSelectNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        bkg.setBackgroundColor(context.getColor(R.color.white));

        alertDialog.show();
    }
    public static int dialog_background_color = -1;

    private boolean isNull(String s) {
        if (s == null || s.trim().isEmpty() || s.equals("null")) {
            return true;
        }
        return false;
    }


}
