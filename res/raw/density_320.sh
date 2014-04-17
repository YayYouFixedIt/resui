#!/system/bin/sh
wm size 2560x1600
wm density 320
mount -o remount,rw /system
export DENSITY=`cat /system/build.prop|grep ro.sf.lcd_density`
sed -i s/$DENSITY/ro.sf.lcd_density=320/g /system/build.prop
mount -o remount,ro /system
