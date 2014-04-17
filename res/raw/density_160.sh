#!/system/bin/sh
wm size 1280x800
wm density 160
# mounting system as rw
mount -o remount,rw /system
export DENSITY=`cat /system/build.prop|grep ro.sf.lcd_density`
sed -i s/$DENSITY/ro.sf.lcd_density=160/g /system/build.prop
mount -o remount,ro /system
