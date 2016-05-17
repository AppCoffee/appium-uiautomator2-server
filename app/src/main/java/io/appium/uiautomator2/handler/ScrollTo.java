package io.appium.uiautomator2.handler;

import android.support.test.uiautomator.UiObjectNotFoundException;

import com.jayway.jsonpath.JsonPath;

import org.json.JSONException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

public class ScrollTo extends BaseRequestHandler {

    public ScrollTo(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse handle(IHttpRequest request) {
        try {
            String json = getPayload(request).toString();
            String selector = "$.params.selector", uiSelectorString, scrollToString = "";
            uiSelectorString = JsonPath.compile(selector).read(json);
            Device.getUiDevice().waitForIdle();
            // Extracting (\"Radio Group\") text from the String
            Matcher m = Pattern.compile("\\(\"([^)]+)\"\\)").matcher(uiSelectorString);
            while (m.find())
                scrollToString = m.group(1);

            AndroidElement.scrollTo(scrollToString);
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (UiObjectNotFoundException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
        return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Scrolled to specfied text");
    }
}
