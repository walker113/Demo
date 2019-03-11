package stay.walker.com.scrollRecyclerview;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import stay.walker.com.retrofitdemo.R;

/**
 * @Author Lynn
 * @Date 2019/1/18
 */
public class PopupSelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PopupSelectAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public PopupSelectAdapter(@Nullable List<String> data) {
        super(data);
    }

    public PopupSelectAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.popup_tv, item);
    }
}