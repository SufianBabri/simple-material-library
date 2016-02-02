[ ![Download](https://api.bintray.com/packages/sufian88/maven/simple-material-library/images/download.svg) ](https://bintray.com/sufian88/maven/simple-material-library/_latestVersion)
# Simple Material Library
As the names says, it is a simple library which follows [Google's Material Design][1]. Lollipop specific effects (such as ripples) won't be ported-back to older versions of Android.

##Demo
![Demo of RaisedButton](https://github.com/SufianBabri/simple-material-library/blob/master/Demo.gif)

##Gradle Dependency
Add following to your `build.gradle` file:

    dependencies {
      compile 'com.github.sufianbabri:simple-material-library:0.1.6'
    }


##Usage
Now you can simply add `RaisedButton` as below in your XML:

    <com.simple.material.RaisedButton
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:raised_button="http://schemas.android.com/apk/res-auto"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            raised_button:buttonColor="#0b5087"
            raised_button:text="Raised Button" />
            
    <com.simple.material.RaisedButton
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:raised_button="http://schemas.android.com/apk/res-auto"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            raised_button:buttonColor="#0b5087"
            raised_button:smallButton="true"
            raised_button:text="More" />
            
# Attributes and their descriptions:

        <!--icon to show at left-->
        <attr name="iconStart" format="reference" />
        
        <!--icon to show at start-->
        <attr name="iconLeft" format="reference" />
        
        <!--button color-->
        <attr name="buttonColor" format="string" />
        
        <!--Button enabled or disabled-->
        <attr name="enabled" format="boolean" />
        
        <!--Small buttons will have less padding and min dimensions-->
        <attr name="smallButton" format="boolean" />
        
        <!--Useful if custom width is desired for a small button. Default is 78dp-->
        <attr name="minWidth" format="integer" />
        
        <!--Useful if custom height is desired for a small button. Default is 35dp-->
        <attr name="minHeight" format="integer" />
        
        <!--text of the button-->
        <attr name="text" format="string" />

##Change log
######0.1.6
1. now supports icon ([#7](https://github.com/SufianBabri/simple-material-library/issues/7)),
2. height increased to 35dp of small `RaisedButton` (previously it was 28dp),
3. changes to demo app.

  [1]: www.google.com/design/spec/material-design/introduction.html
