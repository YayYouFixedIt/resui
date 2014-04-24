#!/system/bin/sh
wm size 2560x1600
wm density 320
mount -o remount,rw /system
cp -f /sdcard/tmp/build.prop /system/
mount -o remount,ro /system
