# TuyaKeyGrab
Grabs the localKey from eFamilyCloud app install

app-debug.apk is the compiled app

install and login to eFamilyCloud (preferably in an emulator)

run the app to show the localKey for your device. Just grabs the first instance of localKey in the file from:

Environment.getExternalStorageDirectory() /Android/data/com.efamily.cloud/cache/1.abj

