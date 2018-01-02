package pyxis.uzuki.live.predicatelayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pyxis.uzuki.live.predicatelayout.PredicateLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] keywordSplits = new String[]{"#Keyword1", "#Keyword2", "#Keyword3", "#Keyword4",
                "#Keyword1", "#Keyword2", "#Keyword3", "#Keyword4",
                "#Keyword1", "#Keyword2", "#Keyword3", "#Keyword4"};

        PredicateLayout layout = findViewById(R.id.predicateLayout);
        layout.addItem(keywordSplits);
        layout.notifyDataSetChanged();
    }
}
