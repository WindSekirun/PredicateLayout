package pyxis.uzuki.live.predicatelayout.impl

import android.content.Context
import android.widget.TextView

/**
 * PredicateLayout
 * Class: PredicateTextTransformer
 * Created by Pyxis on 2018-01-02.
 *
 *
 * Description:
 */

interface PredicateTextTransformer {
    fun generateNewText(context: Context, text: String, backgroundRes: Int?, size: Int, gravity: Int, color: Int): TextView
}
