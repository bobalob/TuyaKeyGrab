# TuyaKeyGrab

### Grabs localKey from eFamilyCloud app cache file 

**Only works with v 1.0.7 or earlier of the eFamilyCloud app - UPDATE: Unfortunately this no longer works, the webserver denies login for the older version of the app**

I've tried messing with the HTTP conversation from the older version of the app and faking a newer version but this still disallows login.

* Install and login to eFamilyCloud (preferably in an emulator)
[Play Store Link](https://play.google.com/store/apps/details?id=com.efamily.cloud&hl=en)

* Run the app to show the localKey for your device. 
* App gathers all localKey messages from the following file:

$ExternalStorageDirectory/Android/data/com.efamily.cloud/cache/1.abj

### Releases

app-release.apk is the compiled app in [Releases Tab](https://github.com/bobalob/TuyaKeyGrab/releases)


Original idea from [Exilit](https://github.com/exilit)
