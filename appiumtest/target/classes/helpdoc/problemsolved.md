## problem solved

**1.运行显示 slf4j-log4j12 和 log4j-slf4j-impl 依赖包冲突**

解决：我选择注释掉了 log4j-slf4j-impl 包，后来发现自己也没有在 resources 路径下建一个 log4j.properties 文件，后来新建即可

**2.问题如下**
```
org.openqa.selenium.WebDriverException: Connection refused: connect
Build info: version: '3.12.0', revision: '7c6e0b3', time: '2018-05-08T14:04:26.12Z'
System info: host: 'DESKTOP-3M7TENJ', ip: '192.168.137.1', os.name: 'Windows 10', os.arch: 'amd64', os.version: '10.0', java.version: '1.8.0_172'
Driver info: driver.version: AppiumDriver
```
解决：这是因为这与selenium有点不同，要下载个appium，之后还要在本地启动appium服务