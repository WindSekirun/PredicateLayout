package pyxis.uzuki.live.predicatelayoutdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import pyxis.uzuki.live.predicatelayout.PredicateLayout;
import pyxis.uzuki.live.predicatelayout.impl.PredicateTextTransformer;
import pyxis.uzuki.live.pyxinjector.annotation.BindView;
import pyxis.uzuki.live.pyxinjector.base.InjectActivity;
import pyxis.uzuki.live.richutilskt.utils.RichUtils;

public class MainActivity extends InjectActivity {
    private @BindView EditText editKeyword;
    private @BindView PredicateLayout predicateLayout;
    private ArrayList<String> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editKeyword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addKeyword(editKeyword.getText().toString());
                return true;
            }
            return false;
        });

    }

    private void addKeyword(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }

        if (text.length() > 20) {
            return;
        }

        itemList.add(String.format("#%s", text));
        updatePredicateLayout();
        editKeyword.setText("");
    }

    private void updatePredicateLayout() {
        predicateLayout.clear();
        predicateLayout.setTextTransformer((context, text, backgroundRes, size, gravity, color) -> {
            int dip9 = RichUtils.dip2px(context, 9);
            int dip6 = RichUtils.dip2px(context, 6);

            TextView textView = new TextView(MainActivity.this);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            textView.setText(String.format(" %s ", text));
            textView.setGravity(gravity);
            textView.setPadding(dip9, dip6, dip9, dip6);
            textView.setTextColor(color);

            if (backgroundRes != null) {
                textView.setBackground(ContextCompat.getDrawable(context, backgroundRes));
            }
            return textView;
        });
        predicateLayout.setItems(itemList);
        predicateLayout.notifyDataSetChanged();
    }
}
