package stay.walker.com.scrollRecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.List;

import stay.walker.com.retrofitdemo.R;

public class SwitchPopupWindow extends PopupWindow {

    private final Context mContext;
    private final List<String> mStrings;
    private final OnSwitchListner mListener;

    /**
     * @param context  上下文（请传Activity）
     * @param strings  所展示的字符串集合
     * @param listener 点击事件回调
     */
    public SwitchPopupWindow(Context context, List<String> strings, OnSwitchListner listener) {
        super(context);
        mContext = context;
        mStrings = strings;
        mListener = listener;
        init();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.popup_switch, null);
        setContentView(view);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        setFocusable(true);
//        setAnimationStyle(R.style.SwitchPopupWindowAnimationStyle);
        View blackView = view.findViewById(R.id.switch_view);
        blackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.switch_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        PopupSelectAdapter adapter = new PopupSelectAdapter(R.layout.item_switch, mStrings);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dismiss();
                mListener.onSwitch(position);
            }
        });
    }




    public interface OnSwitchListner {

        void onSwitch(int position);

    }
}
