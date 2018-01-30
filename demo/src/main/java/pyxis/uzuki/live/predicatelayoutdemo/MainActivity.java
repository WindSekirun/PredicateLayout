package pyxis.uzuki.live.predicatelayoutdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import kotlin.jvm.functions.Function1;
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

        predicateLayout.usingCustomView(new Function1<String, View>() {
            @Override
            public View invoke(String s) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.itemn, null);
                TextView textView = view.findViewById(R.id.txtItem);
                textView.setText(s);
                return view;
            }
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
        predicateLayout.setItems(itemList);
        predicateLayout.notifyDataSetChanged();
    }
}
