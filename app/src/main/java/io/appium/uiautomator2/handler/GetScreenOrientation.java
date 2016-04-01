package io.appium.uiautomator2.handler;

import org.json.JSONException;

import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.ScreenOrientation;
import io.appium.uiautomator2.util.Device;

public class GetScreenOrientation extends RequestHandler {

    public GetScreenOrientation(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse handle(IHttpRequest request) throws JSONException {
        ScreenOrientation orientation;
        int rotation = Device.getUiDevice().getDisplayRotation();
        if (rotation == 0 || rotation == 3) {
            orientation = ScreenOrientation.LANDSCAPE;
        } else {
            orientation = ScreenOrientation.PORTRAIT;
        }
        return new AppiumResponse(getSessionId(request), orientation);
    }
}
