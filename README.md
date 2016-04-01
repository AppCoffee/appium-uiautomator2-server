### appium-uiautmator2-server

A netty server that runs on the device listening for commands and executes using UiAutomator V2.

### building project
build the android project using below commands 

`gradle clean assembleServerDebug`
`gradle assembleServerDebugAndroidTest`


### Starting server
push both src and test apks to the device and execute the instrumentation tests.

`adb shell am instrument -w io.appium.uiautomator2.server.test/android.support.test.runner.AndroidJUnitRunner`



### run unitTest
build the unitTest flavor using the below commands 

`gradle clean assembleUnitTestDebug`
`gradle aassembleUnitTestDebugAndroidTest`


unitTest flavor contains tests for handlers and can be invoked by using following command 

`gradle clean connectedUnitTestDebugAndroidTest`

the above command takes care about installing the AUT apk in to the testing device/emulator before running the tests.


you can also invoke the test using below command

`adb shell am instrument -w io.appium.uiautomator2.unittest.test/android.support.test.runner.AndroidJUnitRunner`

Note: AUT apk should be installed before executing above command.
