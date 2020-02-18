# ExpandableRecyclerview
### Expandable Recyclerview that can collapse and expand the height of itself


### Settings
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

***

### How to use
##### activity_main.xml
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
