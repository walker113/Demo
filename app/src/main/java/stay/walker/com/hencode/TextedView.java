package stay.walker.com.hencode;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.StaticLayout;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class TextedView extends View {

    public TextedView(Context context) {
        super(context);

    }

    private Paint mPaint;
    private Paint.FontMetrics mFontMetrics;
    {
        mPaint = new Paint();
        mFontMetrics = new Paint.FontMetrics();

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "字体文件名称"));

        canvas.drawText("content", 100, 100, mPaint);
        // 文字有基线，

        mPaint.getTextBounds("abab", 0, "abab".length(), new Rect());

        mPaint.getFontMetrics(mFontMetrics);

        Matrix matrix ;


    }
}
