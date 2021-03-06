## PredicateLayout [![](https://jitpack.io/v/WindSekirun/PredicateLayout.svg)](https://jitpack.io/#WindSekirun/PredicateLayout)

[![Kotlin](https://img.shields.io/badge/kotlin-1.2.0-blue.svg)](http://kotlinlang.org)	[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Separated Keyword View with [Flexbox-layout](https://github.com/google/flexbox-layout) written in Kotlin


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
