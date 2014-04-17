#!/system/bin/sh
wm size 1920x1200
wm density 240
# mounting system as rw
mount -o remount,rw /system
export DENSITY=`cat /system/build.prop|grep ro.sf.lcd_density`
sed -i s/$DENSITY/ro.sf.lcd_density=240/g /system/build.prop
mount -o remount,ro /system
