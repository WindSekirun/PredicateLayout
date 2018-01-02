package pyxis.uzuki.live.predicatelayout.impl;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

/**
 * PredicateLayout
 * Class: PredicateTextTransformer
 * Created by Pyxis on 2018-01-02.
 * <p>
 * Description:
 */

public interface PredicateTextTransformer {
    TextView generateNewText(String text, @Nullable Drawable background, int size, int gravity, int color);
}
