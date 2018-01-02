## PredicateLayout 

Separated Keyword List View written in Kotlin

<img src="https://github.com/WindSekirun/PredicateLayout/blob/master/sample.png" width="202" height="360">


### Usages
*rootProject/build.gradle*
```	
allprojects {
    repositories {
	    maven { url 'https://jitpack.io' }
    }
}
```

*app/build.gradle*
```
dependencies {
 implementation 'com.github.WindSekirun:PredicateLayout:0.5.0'
}
```

### Usages

#### XML
```XML
<pyxis.uzuki.live.predicatelayout.PredicateLayout
        android:id="@+id/predicateLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center_vertical"
        app:backgroundResources="@drawable/bg_hash"
        app:gravity="center"
        app:horizontalSpacing="4dp"
        app:textColor="@android:color/white"
        app:textSize="14sp"
        app:verticalSpacing="4dp" />
```

#### Java
```Java
String[] keywordSplits = new String[]{"#Keyword1", "#Keyword2", "#Keyword3", "#Keyword4",
                "#Keyword1", "#Keyword2", "#Keyword3", "#Keyword4",
                "#Keyword1", "#Keyword2", "#Keyword3", "#Keyword4"};

PredicateLayout layout = findViewById(R.id.predicateLayout);
layout.addItem(keywordSplits);
layout.notifyDataSetChanged();
```

### License 
```
Copyright 2017 WindSekirun (DongGil, Seo)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
