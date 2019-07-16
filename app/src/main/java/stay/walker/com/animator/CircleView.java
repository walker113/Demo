package stay.walker.com.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    private Paint mPaint;
    private float radius = 100;
    {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public float getRadius() {
        return radius;
    }
}
