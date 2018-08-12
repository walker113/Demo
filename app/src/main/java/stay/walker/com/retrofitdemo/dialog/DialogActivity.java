package stay.walker.com.retrofitdemo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.KLog;

import stay.walker.com.retrofitdemo.R;

/**
 * @author walker
 */
public class DialogActivity extends Activity implements View.OnTouchListener{



    public void handlerMsg(Message msg) {
        switch (msg.what) {
            case 1:
                Log.e("TAG", "Hanlder message");
                dialogFragment.show(getFragmentManager(), "");
//                    AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
//                    builder.show();
                break;
            case 3:

                loadingDialog.dismiss();

                normalSingleDialog();

                break;
            default:
                break;
        }
    }

    FireMissilesDialogFragment dialogFragment;
    CommonHandler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialog);

        mWindowManager = getWindowManager();
        dialogFragment = new FireMissilesDialogFragment();
        handler = new CommonHandler(this);

    }

    LoadingDialog loadingDialog;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void delete(View view) {
//        getWindowManager().removeView(button);

//        normalDialog();
//        normalSingleDialog();
//        listDialog();
//        customDialog();
//        showProgressDialog();


        LoadingDialog.Builder builder = new LoadingDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("加载中...");
        loadingDialog = builder.create();
        loadingDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(3);
            }
        }).start();
    }

    private void normalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("已经解绑支付宝代扣，会对扣费产生影响，是否重写签约?");
        builder.setCancelable(false);
        builder.setPositiveButton("签约", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void normalSingleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("错误");
        builder.setMessage("图片上传失败，请重新上传！");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });

        builder.create().show();
    }

    private void listDialog() {
        final String items[] = {"公交", "地铁", "步行"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setCancelable(false);

        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private AlertDialog customDialog;
    private void customDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("提示");
        builder.setCancelable(false);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_ios, null);

        builder.setView(layout);
        customDialog = builder.create();
        customDialog.show();
    }




    public void showProgressDialog() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载中");
        progressDialog.show();
    }

    public void showProgressBar() {
        ProgressBar bar = new ProgressBar(this);

    }

    Button button;
    public void open(View view) {
//

//        button = new Button(this);
//        button.setText("Text");
//        mLayoutParams =
//                new WindowManager.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        1000, 0,
//                        PixelFormat.TRANSPARENT);
//        mLayoutParams.flags =
//                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
//                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
//                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
//        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
//        mLayoutParams.x = 100;
//        mLayoutParams.y = 300;
//
//        button.setOnTouchListener(this);
//        mWindowManager.addView(button, mLayoutParams);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4 * 1000);
                    Log.e("TAG", "end... ...");
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    WindowManager.LayoutParams mLayoutParams;
    WindowManager mWindowManager;



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                mLayoutParams.x = rawX;
                mLayoutParams.y = rawY;
                mWindowManager.updateViewLayout(button, mLayoutParams);
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        return false;
    }


    @Override
    protected void onStop() {
        super.onStop();
        KLog.e();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.e();
    }
}
