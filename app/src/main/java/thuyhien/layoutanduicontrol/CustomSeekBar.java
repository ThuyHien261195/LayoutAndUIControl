package thuyhien.layoutanduicontrol;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;

import static android.R.attr.max;

/**
 * Created by thuyhien on 9/11/17.
 */

public class CustomSeekBar extends AppCompatSeekBar {
    private int stepValue = 1;

    public CustomSeekBar(Context context) {
        super(context);
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CustomSeekBar, 0, 0);
        try {
            int realMax = getRealMax();
            stepValue = typedArray.getInteger(R.styleable.CustomSeekBar_step_value, 1);
            setMax(realMax);
        } finally {
            typedArray.recycle();
        }
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getStepValue() {
        return stepValue;
    }

    public void setStepValue(int stepValue) {
        int realMax = getRealMax();
        this.stepValue = stepValue;
        setMax(realMax);
    }

    public int getRealMax() {
        return super.getMax() * stepValue;
    }

    @Override
    public synchronized int getProgress() {
        return super.getProgress() * stepValue;
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress / stepValue);
    }

    @Override
    public synchronized void setMax(int max) {
        if (stepValue == 0) {
            stepValue = 1;
        }
        super.setMax(max / stepValue);
    }
}
