## appium 中的理解

**1.appium服务相关**

- appium需下载appium安装包，可以从github上下载，保证appium本地服务开启，端口是4723
- selenium实际上也起服务了，只是看不到。selenium用的那个浏览器driver，比如chromedriver.exe，双击下会提示服务启动，并且有端口,
若是windows版，端口是9515，driver = new ChromeDriver()这样的代码实际上是暗藏着启动了本地服务了
- selenium3本来就是通过restful API，测试代码将要操作的指令通过http请求发送给webdriver对应的服务，然后webdriver服务来让浏览器
执行的
![](.tips_images/b5c25cf0.png)
- 若对于selenium的new ChromeDriver()这样一个无参的构造方法，实际上这个类的构造器中还有其他参数，里面有个可执行driver可执行文件的
路径
- 下载appium需要的服务可在github中下载，图片如下：
![](.tips_images/5c1e3cc2.png)