package pyxis.uzuki.live.predicatelayout.preset;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import pyxis.uzuki.live.predicatelayout.impl.PredicateTextTransformer;

/**
 * PredicateLayout
 * Class: PredefindTextTransformer
 * Created by Pyxis on 2018-01-02.
 * <p>
 * Description:
 */

public class PredefindTextTransformer implements PredicateTextTransformer {
    private Context mContext;

    public PredefindTextTransformer(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public TextView generateNewText(String text, @Nullable Drawable background, int size, int gravity, int color) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        textView.setText(String.format(" %s ", text));
        textView.setGravity(gravity);
        textView.setTextColor(color);

        if (background != null) {
            textView.setBackground(background);
        }
        return textView;
    }
}
