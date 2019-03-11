package stay.walker.com.scrollRecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import stay.walker.com.retrofitdemo.R;

public class RecyclerViewActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener, SwitchPopupWindow.OnSwitchListner {

    @BindView(R.id.rlv)
    RecyclerView mRecycler;
    private List<String> mStrings;

    private Toolbar mToolbar;
    private TextView mTvTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        mToolbar = findViewById(R.id.detail_toolbar);
        mTvTitle = findViewById(R.id.detail_tv_title);
        mTvTitle.setOnClickListener(this);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        initView();
        mStrings = Arrays.asList("aaa", "bbb");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.switch_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PopupSelectAdapter adapter = new PopupSelectAdapter(R.layout.item_switch, mStrings);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }
    private List<String> mRvStrings;
    private void initView() {
        mRvStrings = Arrays.asList("0", "1", "2", "3", "4", "5");

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        CardDetailRvAdapter rvAdapter = new CardDetailRvAdapter(mRvStrings);
        mRecycler.setAdapter(rvAdapter);

        mRecycler.smoothScrollToPosition(mRvStrings.size());

        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                KLog.e("new state - " + newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    recyclerView.smoothScrollToPosition(mRvStrings.size());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mRecycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            public float y1,y2,x2,x1;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                KLog.e("onInterceptTouchEvent");
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = e.getX();
                    y1 = e.getY();
                }
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    x2 = e.getX();
                    y2 = e.getY();
                    if (Math.abs(x1 - x2) < 6) {
                        return false;// 距离较小，当作click事件来处理
                    }
                    if(Math.abs(x1 - x2) >60){  // 真正的onTouch事件
                        return true;
                    }
                }
                return false;

            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        rvAdapter.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtils.showShort(position);
    }

    @Override
    public void onClick(View view) {
        SwitchPopupWindow window = new SwitchPopupWindow(this, mStrings, this);
        window.showAsDropDown(mToolbar);
    }

    @Override
    public void onSwitch(int position) {

    }
}
