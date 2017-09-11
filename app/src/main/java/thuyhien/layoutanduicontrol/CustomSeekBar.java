package thuyhien.layoutanduicontrol;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;

/**
 * Created by thuyhien on 9/11/17.
 */

public class CustomSeekBar extends AppCompatSeekBar {

    int MULTIPLIER = 100;

    public CustomSeekBar(Context context) {
        super(context);
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void configSeekBar(int max, int multiplier){
        setMax(max/multiplier);
        MULTIPLIER = multiplier;
    }

    @Override
    public synchronized int getProgress() {
        return super.getProgress() * MULTIPLIER;
    }
}
