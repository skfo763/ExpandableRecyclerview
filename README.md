# ExpandableRecyclerview
### Expandable Recyclerview that can collapse and expand the height of itself


# Settings
#### 1. gradle
##### add this code into project build.gradle
~~~groovy
allprojects {
    repositories {
   		...
   		maven { url 'https://jitpack.io' }
   	}
}
~~~

##### add this code into module(app) build.gradle
~~~groovy
dependencies {
    implementation 'com.github.skfo763:ExpandableRecyclerview:1.0.2'
}
~~~

#### 2. maven
~~~groovy
<repositories>
	<repository>
	    <id>jitpack.io</id>
        <url>https://jitpack.io</url>
	</repository>
</repositories>
~~~
##### and add dependency
~~~groovy
<dependency>	   
	<groupId>com.github.skfo763</groupId>
    <artifactId>ExpandableRecyclerview</artifactId>
	<version>1.0.2</version>
</dependency>
~~~


# How to use
#### activity_main.xml
~~~xml
<com.skfo763.expandable_recyclerview.ExpandableRecyclerView
	android:id="@+id/expandable_recyclerview"        
    android:layout_width="match_parent"
    android:layout_height="wrap_content" 
    app:minLine="1"
    app:maxLine="5"
    app:isExpanded="false">
</com.skfo763.expandable_recyclerview.ExpandableRecyclerView>
 ~~~
 
 
#### MainActivity.kt
~~~kotlin
expandable_recyclerview.animationDuration = 300L
expandable_recyclerview.animationInterpolator = DecelerateInterpolator()

expandable_recyclerview.onAnimationStart = {
    // Customize your event
}
...
expand_button.setOnClickListener {
    expandable_recyclerview.expandView()
}

collapse_button.setOnClickListener {
    expandable_recyclerview.collapseView()
}
~~~

#### attributes
| name | description | default value | available on xml |
|:---------|:-------------------------|:-------|:----------|
| minLine | minimum line for recyclerview | 1 | true |
| maxLine | maximum line for recyclerview | depends on data | true |
| isExpanded | default state of recyclerview (true = expanded, false = collapsed) | false | true |
| animationDuration | duration of expand/collapse animation | 350 milliseconds | false |
| animationInterpolator | interpolator of animation | DecelerateInterpolator() | false |
| onAnimationStart | action that can be executed at start of expand/collapse animation | empty | false | 
| onAnimationStart | action that can be executed at start of expand/collapse animation | empty | false | 
| onAnimationEnd | action that can be executed at end of expand/collapse animation | empty | false | 
| onAnimationRepeat | action that can be executed when repeating expand/collapse animation | empty | false | 
| onAnimationCancel | action that can be executed when expand/collapse animation is canceled | empty | false | 

# Contribution
### All kind of contribution is welcomed. Please send me a pull request


# license
~~~
Copyright 2019 Changyeon-Seo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~
