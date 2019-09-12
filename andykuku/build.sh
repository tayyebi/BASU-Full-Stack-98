#!/bin/bash

# Author: Authmane Terki (authmane512)
# E-mail: authmane512 (at) protonmail.ch
# Blog: https://medium.com/@authmane512
# Source: https://github.com/authmane512/android-project-template
# Tutorial: https://medium.com/@authmane512/how-to-do-android-development-faster-without-gradle-9046b8c1cf68
# This project is on public domain
#
# Hello! I've made this little script that allow you to init, compile and run an Android Project.
# I tried to make it as simple as possible to allow you to understand and modify it easily.
# If you think there is a very important missing feature, don't hesitate to do a pull request on Github and I will answer quickly.
# Thanks! 

set -e

APP_NAME="kuku"
PACKAGE_NAME="ir.kukuapp.mobile"

AAPT="/opt/android-sdk/build-tools/28.0.3/aapt"
DX="/opt/android-sdk/build-tools/28.0.3/dx"
ZIPALIGN="/opt/android-sdk/build-tools/28.0.3/zipalign"
APKSIGNER="/opt/android-sdk/build-tools/28.0.3/apksigner"
PLATFORM="/opt/android-sdk/platforms/android-28/android.jar"

init() {
	rm -rf .git README.md
	echo "Making ${PACKAGE_NAME}..."
	mkdir -p "$PACKAGE_DIR"
	mkdir obj
	mkdir bin
	mkdir -p res/layout
	mkdir res/values
	mkdir res/drawable
	
	sed "s/{{ PACKAGE_NAME }}/${PACKAGE_NAME}/" "template_files/HomeActivity.java" > "$PACKAGE_DIR/HomeActivity.java"
	sed "s/{{ PACKAGE_NAME }}/${PACKAGE_NAME}/" "template_files/AndroidManifest.xml" > "AndroidManifest.xml"
	sed "s/{{ APP_NAME }}/${APP_NAME}/" "template_files/strings.xml" > "res/values/strings.xml"
	cp "template_files/activity_main.xml" "res/layout/activity_main.xml"
	rm -rf template_files
}

build() {
	echo "Cleaning..."
	rm -rf obj/*
	rm -rf "$PACKAGE_DIR/R.java"

	echo "Generating R.java file..."
	$AAPT package -f -m -J src -M AndroidManifest.xml -S res -I $PLATFORM

	echo "Compiling..."
	ant compile -Dplatform=$PLATFORM

	echo "Translating in Dalvik bytecode..."
	$DX --dex --output=classes.dex obj

	echo "Making APK..."
	$AAPT package -f -m -F bin/app.unaligned.apk -M AndroidManifest.xml -S res -I $PLATFORM
	$AAPT add bin/app.unaligned.apk classes.dex

	echo "Aligning and signing APK..."
#	keytool -genkey -v -keystore debug.keystore -alias alias_name -keyalg RSA -keysize 2048 -validity 10000
	$APKSIGNER sign --ks debug.keystore --ks-pass "pass:123456" bin/app.unaligned.apk
#	$ZIPALIGN -f 4 bin/app.unaligned.apk bin/app.apk
	cp bin/app.unaligned.apk bin/app.apk
}

run() {
	echo "Launching..."
	adb install -r bin/app.apk
	adb shell am start -n "${PACKAGE_NAME}/.HomeActivity"
}

PACKAGE_DIR="src/$(echo ${PACKAGE_NAME} | sed 's/\./\//g')"

case $1 in
	init)
		init
		;;
	build)
		build
		;;
	run)
		run
		;;
	build-run)
		build
		run
		;;
	*)
		echo "error: unknown argument"
		;;
esac
