#!/system/bin/sh
mount -o remount,rw /system
cp -f /sdcard/tmp/build.prop /system/
mount -o remount,ro /system
