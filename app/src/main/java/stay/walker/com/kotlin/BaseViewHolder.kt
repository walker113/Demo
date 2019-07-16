package stay.walker.com.kotlin

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes


abstract class BaseViewHolder : RecyclerView.ViewHolder {

    constructor(itemView: View) : super(itemView)


    @SuppressLint("UseSparseArrays")
    private val valueHashMap = HashMap<Int, View>()

    protected fun <T : View> getView(@IdRes id: Int): T {
        var view = valueHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            valueHashMap[id] = view
        }

        return view as T;

    }

    protected fun setText(id: Int, text: String?) {
        getView<TextView>(id).text = text
    }



}
