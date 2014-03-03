NovaView
========

This is a simple android library you can use to load a novasoftware schedule. 

Current screenshot
==================
![Screenshot](http://johnthedaniel.github.io/NovaView/screenshot.png)

Usage
=====
**This has only been tested in Android Studio** 

In order to use this app, you have to import it as a module and add it as a dependency to your app's main module. This method is still a little bit wobbly, due to Android Studio's youthness. 

The stuff we are going to do:

1. Download the project
2. Copy it into your project
3. Make gradle familiar with it, i.e making it a module
4. Add it as a dependency
5. Useage

####Step 1.
Download the library from the releases tab here at github. You will get a .zip file. Extract it to a desired location. 

####Step 2.
Find the directory named "NovaViewLib" and copy it to your clipboard. Now, navigate to your Android project. In the root of the broject, i.e the directory where you find a **settings.gradle** will you need to create a new folder named "Libraries". Once you have created you directory, paste you NovaViewLib into "Libraries". 

Ya'll done? Sweet! Gimme high five!

####Step 3. 
Open your **settings.gradle** and make your imported library a module. 
If your app is called "MyApp", your **settings.gladle** should look like this:

```Groovy
include ':MyApp', ':Libraries:NovaViewLib'
```

Now do a gradle clean/build, and sync gradle with android files. 

####Step 4.
Open the project structure and click on "MyApp" and navigate into dependencies. Now, at the bottom, there should be a plus sign, click it and choose "add dependency from module" and add NovaViewLib.

#Usage
In your activity layout file, add the view named ```<com.johndaniel.novaviewlib.NovaView />``` and set the height of it. In NovaViewSample I have placed the view in a fragment. Here is the example code: 
```XML
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:john="http://schemas.android.com/apk/res-auto"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:paddingLeft="@dimen/activity_horizontal_margin"
                android:paddingRight="@dimen/activity_horizontal_margin"
                android:paddingTop="@dimen/activity_vertical_margin"
                android:paddingBottom="@dimen/activity_vertical_margin"                
                tools:context="com.johndaniel.novaviewsamlpe.MainActivity$PlaceholderFragment">

    <com.johndaniel.novaviewlib.NovaView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:id="@+id/novaview"
            john:schoolId="52550" >
    </com.johndaniel.novaviewlib.NovaView>
</RelativeLayout>
```
You can, directly in the XML, tell NovaView the schoolId you are going to use. To do this, add a line of xml inside your ViewGroup container, in my case the `RelativeLayout`, below the `xmls:android="http://schemas.android..."` part, which contains:
```XML
xmlns:john="http://schemas.android.com/apk/res-auto"
``` 
**Note: the** `res-auto` **part is something that is possibly exclusive to Android Studio. Honestly, I don't really know. I don't remember any time I have written something like that in Eclipse, but this worked well for me in Android Studio**

And now, in your `<com.johndaniel.novaviewlib.NovaView />` view, add the line:
```
john:schoolId="the id of your school"
```
To get your school id in NovaSoftware's schedule application: open your schedule from your school's website --> right click on the schedule --> open image in a new tab --> search for the attribute `schoolId=` in the url. 


Sweet! You are almost done. Now you have to set up the last working bits of your Novaview, and that is week, day and mode. 

In your activity's onCreate method, write something like this: 
```Java
import...

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Find the view
        NovaView novaView = (NovaView) rootView.findViewById(R.id.novaview);

        //Set some stuff. Pretty straight forvard
        novaView.setPeriod("Vt");  //Optional if you set a week.
        novaView.setDay(NovaView.DAY_MONDAY);
        novaView.setMode(NovaView.SHOW_DATE);
        novaView.setId("n2c");  //The id of your class or id-number. In blackeberg we have a class called N2c.
        novaView.setWeekNo(7);
    }
``` 

If you want to change day, do `novaView.setDay(NovaView.DAY_...)` and replace the dots with desired day. 
**Note: If you want to change the day while the application is running, you'll have to reload the schedule. So after you set a new day, call the method** `novaView.loadSchoolUrl()`**.**
You can also view the full week. To do that, call `novaView.setDay(NovaView.DAY_FULL_WEEK)`.

More to come! Gotta study... k bye.
