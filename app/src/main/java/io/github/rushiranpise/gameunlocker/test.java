package io.github.rushiranpise.test;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.security.KeyStore;
import java.util.Arrays;


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

@SuppressLint("DiscouragedPrivateApi")
@SuppressWarnings("ConstantConditions")
public class test implements IXposedHookLoadPackage {


    @Override
public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {


    // Hook the getValueFromProp() method.
    XposedHelpers.findAndHookMethod("com.byxiaorun.detector", lpparam.classLoader, "getValueFromProp", String.class, new XC_MethodHook() {

        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

            // Check if the property name is sys.usb.ffs.ready, sys.usb.state, sys.usb.config, or persist.sys.usb.reboot.funnc.
            if (param.args[0].equals("sys.usb.ffs.ready") || param.args[0].equals("sys.usb.state") || param.args[0].equals("sys.usb.config") || param.args[0].equals("persist.sys.usb.reboot.funnc")) {

                // Replace the return value with a string that contains the value "1".
                param.setResult("1");
            }
        }
    });
}
}
