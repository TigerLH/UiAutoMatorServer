package com.hcb.uiautomator.core;

import android.view.accessibility.AccessibilityNodeInfo;
import static com.hcb.uiautomator.utils.ReflectionUtils.invoke;
import static com.hcb.uiautomator.utils.ReflectionUtils.method;

import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiObject;
/**
 * Static helper class for getting {@link AccessibilityNodeInfo} instances.
 *
 * Created by guysmoilov on 2/18/2016.
 */
public abstract class AccessibilityNodeInfoGetter {

    private static Configurator configurator = Configurator.getInstance();

    /**
     * Gets the {@link AccessibilityNodeInfo} associated with the given {@link UiObject}
     */
    public static AccessibilityNodeInfo fromUiObject(UiObject uiObject) {
        return (AccessibilityNodeInfo)
                invoke(method(UiObject.class, "findAccessibilityNodeInfo", long.class),
                        uiObject,
                        configurator.getWaitForSelectorTimeout());
    }
}
