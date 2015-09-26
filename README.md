# simple-material-library
A simple Material library which follows Google's guidelines. Lollipop specific effects (such as ripples) won't be ported-back to older versions of Android.

# Gradle Dependency
Add following to your `build.gradle` file:

    repositories {
        maven {
            url 'https://dl.bintray.com/sufian88/maven/'
        }
    }
    dependencies {
      compile 'com.github.sufianbabri:simple-material-library:0.1.1'
    }


#Usage
Now you can simply add `RaisedButton` as below in your XML:

    <com.simple.material.RaisedButton
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:raised_button="http://schemas.android.com/apk/res-auto"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            raised_button:cardCornerRadius="4dp"
            raised_button:buttonColor="#0b5087"
            raised_button:text="Raised Button" />
