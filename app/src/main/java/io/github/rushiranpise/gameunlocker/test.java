package io.github.rushiranpise.test;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.util.HashSet;
import java.util.Set;
import android.util.Log;

public class PropertyHook implements IXposedHookLoadPackage {

    private static final String PROP_USB_FFS_READY = "sys.usb.ffs.ready";
    private static final String PROP_USB_STATE = "sys.usb.state";
    private static final String PROP_USB_CONFIG = "sys.usb.config";
    private static final String PROP_USB_REBOOT_FUNNC = "persist.sys.usb.reboot.funnc";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod("com.byxiaorun.detector", lpparam.classLoader, "getValueFromProp", String.class, new XC_MethodHook() {

            private static final Set<String> PROPERTIES_TO_HOOK = new HashSet<>(Arrays.asList(
                    PROP_USB_FFS_READY, PROP_USB_STATE, PROP_USB_CONFIG, PROP_USB_REBOOT_FUNNC));

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                String propertyName = (String) param.args[0];
                if (PROPERTIES_TO_HOOK.contains(propertyName)) {
                    param.setResult("1");
                }
            }
        });
    }
}
