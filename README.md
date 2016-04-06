[ ![Download](https://api.bintray.com/packages/sufian88/maven/simple-material-library/images/download.svg) ](https://bintray.com/sufian88/maven/simple-material-library/_latestVersion)
# Simple Material Library
As the names says, it is a simple library which follows [Google's Material Design][1]. Lollipop specific effects (such as ripples) won't be ported-back to older versions of Android.

## Screenshot
<img src="https://raw.githubusercontent.com/SufianBabri/simple-material-library/master/screen.png" width="320">

## Sample App

<a href='https://play.google.com/store/apps/details?id=com.sufian.simpleMaterialLibrary&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png' height="100"/></a>

##Gradle Dependency
Add following to your `build.gradle` file:

    dependencies {
      compile 'com.github.sufianbabri:simple-material-library:0.1.7'
    }


## Usage
#### `RaisedButton`

    <com.simple.material.RaisedButton
            xmlns:raised_button="http://schemas.android.com/apk/res-auto"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            raised_button:text="Raised Button" />
            
    <com.simple.material.RaisedButton
            xmlns:raised_button="http://schemas.android.com/apk/res-auto"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            raised_button:smallButton="true"
            raised_button:text="Small" />
            
#### `FlatButton`

    <com.simple.material.FlatButton
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:text="Flat Button"/>
            
# Attributes and their descriptions:

#### `RaisedButton`:

| Attribute        | Type           | Description  |
| ------------- |:-------------:|:-----|
| iconStart      | drawable | icon to show at left |
| iconLeft      | drawable | icon to show at start |
| buttonColor      | color | color of the button, if not provided, colorAccent is used |
| smallButton      | boolean | `true` if you want this to be the smaller version of the button |
| minWidth      | integer | Useful if custom width is desired for a small button. Default is 70dp |
| minHeight      | integer | Useful if custom height is desired for a small button. Default is 36dp |
| text      | String | text of the button |


#### `FlatButton`:

| Attribute        | Type           | Description  |
| ------------- |:-------------:|:-----|
| textColor      | color | the color of the text |

## Change log

###### 0.1.7
1. fixed padding issue ([#8](https://github.com/SufianBabri/simple-material-library/issues/8)),
1. added `FlatButton`,
1. colorAccent is now picked if no color is provided,
1. other improvements to library,
1. small `RaisedButton` is at least 70dp x 36dp
1. updated demo app,
1. uploaded demo app to PlayStore.

###### 0.1.6
1. now supports icon ([#7](https://github.com/SufianBabri/simple-material-library/issues/7)),
1. height increased to 35dp of small `RaisedButton` (previously it was 28dp),
1. changes to demo app.

  [1]: www.google.com/design/spec/material-design/introduction.html
