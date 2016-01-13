# simple-material-library
A simple Material library which follows [Google's Material Design][1]. Lollipop specific effects (such as ripples) won't be ported-back to older versions of Android.

# Gradle Dependency
Add following to your `build.gradle` file:

    repositories {
        maven {
            url 'https://dl.bintray.com/sufian88/maven/'
        }
    }
    dependencies {
      compile 'com.github.sufianbabri:simple-material-library:0.1.5'
    }


#Usage
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
            
List of attributes and their descriptions:

        <!--button color-->
        <attr name="buttonColor" format="string|reference" />
        <!--Button enabled or disabled-->
        <attr name="enabled" format="boolean" />
        <!--Small buttons will have less padding and min dimensions-->
        <attr name="smallButton" format="boolean" />
        <!--Useful if custom width is desired for a small button. Default is 78dp-->
        <attr name="minWidth" format="integer" />
        <!--Useful if custom height is desired for a small button. Default is 28dp-->
        <attr name="minHeight" format="integer" />
        <!--text of the button-->
        <attr name="text" format="string|reference" />
        
  [1]: www.google.com/design/spec/material-design/introduction.html
