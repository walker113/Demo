package stay.walker.com.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class TempPopupWindow extends PopupWindow {
    public TempPopupWindow(Context context) {

        super(context);

        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        setOutsideTouchable(true);
        setFocusable(true);

        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }
}
