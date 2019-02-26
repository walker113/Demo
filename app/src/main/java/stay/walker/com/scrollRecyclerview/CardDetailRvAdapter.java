package stay.walker.com.scrollRecyclerview;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import stay.walker.com.retrofitdemo.R;

public class CardDetailRvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<String> data;
    public CardDetailRvAdapter(@Nullable List<String> data) {
        super(R.layout.ent_item_visitor, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.visitor_tv_name,"张三"+item);
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return data.get(position % data.size());
    }
}
