package pyxis.uzuki.live.predicatelayout.preset;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
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


    @NotNull
    @Override
    public TextView generateNewText(@NotNull Context context, @NotNull String text, @Nullable Integer backgroundRes,
                                    int size, int gravity, int color) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        textView.setText(String.format(" %s ", text));
        textView.setGravity(gravity);
        textView.setTextColor(color);

        if (backgroundRes != null) {
            textView.setBackground(ContextCompat.getDrawable(context, backgroundRes));
        }
        return textView;
    }
}
