## DesiredCapabilities 对象 setCapability 参数

**Server capabilities**

Capability           Appium 、Selendroid

platformName         平台的名称：iOS, Android, or FirefoxOS

platformVersion      移动设备的系统版本号，7.1, 4.4

deviceName           IOS：instruments -s devices，Android:随便写

app                  安装文件路径：/abs/path/to/my.apk or http://myapp.com/app.ipa

browserName          测试的web浏览器，如果是测app则忽略

newCommandTimeout    等待新命令的最长时间，超时后退出。默认是60s

autoLaunch           是否自动安装和启动,默认true

language             模拟器的语言设置

locale               模拟器的地区设置

udid                 设备号

orientation          模拟器的横竖屏设置

autoWebview          Move directly into Webview context

noReset              在当前session前不重置app状态

fullReset            ios删除文件夹，Android删除app数据。

 

**android only**

appActivity                 要启动的Activity

appPackage                  要启动的包

appWaitActivity             等待的Activity

appWaitPackage              等待的包 

deviceReadyTimeout          等待设备就绪的时间

androidCoverage             

enablePerformanceLogging    (Chrome and webview only)default=false

androidDeviceReadyTimeout

androidDeviceSocket

avd

avdLaunchTimeout

avdReadyTimeout

avdArgs

useKeystore

keystorePath

keystorePassword

keyAlias

keyPassword

chromedriverExecutable

autoWebviewTimeout

intentAction

intentCategory

intentFlags

optionalIntentArguments

unicodeKeyboard

resetKeyboard

noSign              Skip checking and signing,work only with UiAutomator and not with selendroid

ignoreUnimportantViews

 

**ios  only**

calendarFormat

bundleId

udid

launchTimeout

locationServicesEnabled

locationServicesAuthorized

autoAcceptAlerts

nativeInstrumentsLib

nativeWebTap

safariAllowPopups

safariIgnoreFraudWarning

safariOpenLinksInBackground

keepKeyChains

localizableStringsDir

processArguments

interKeyDelay

showIOSLog
