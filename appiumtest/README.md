## appniumtest 项目

### 一.工程结构

### 二.项目介绍

### 三.搭建流程

### 四.项目问题

### 五.临时
一、项目结构
包依赖以及代码管理采用：maven
用例组织：testNG
测试报告：ExtentReports
a)	项目结构如下：
UniqloAppUITest
-- src
    -- main
          --java
              -- com.uniqloAPPUI
--api        解释：使用httpclient对api请求、响应的工具封装
              	-- business   解释：对优衣库业务的api请求封装
			  	-- constant   解释：包含常用的常量以及枚举，例如语言、商品的类型
			  	-- exception  解释：封装的异常
			  	-- handler    解释：android和ios处理器，方便driver统一处理
			  	-- internal   解释：读取testng的xml文件参数，以及启动ios/android的driver，向用例提供统一的出口，用例内部不用关心driver类型。
              	-- listener    解释：testing的监听器，用于报告输出、以及自定义测试开始、结束、成功或者失败做的事情
			  	-- ui        解释：用例的基础类，封装了常用的方法(等待、查找元素等api)，方便调用而不用每个api都去引用driver对象，不涉及到业务逻辑
			  	-- utils      解释：常用等待类、读取csv文件、滑动等api封装
          --resources  解释：存放log4j2的配置文件
    -- test
		--java
			--common.test  解释：存放通用封装的自测用例
			--uniqloAPPUI
				--cases    解释：用例存放目录
				--data     解释：测试数据存放目录
				--elements  解释：页面定位元素存放目录
				AppiumBaseTest.java  解释：所有用例的基类，包含业务逻辑
		--resources     解释：存放项目的配置信息
二、原理
a)	商品获取
流程：
 
 
b)	用例编写
1.	所有用例继承AppiumBaseTest这个基类
2.	用例采用testNG方式编写，用例中直接可以使用driver对象进行api调用
3.	页面的元素存放到elements文件中，并使用类型变量public static方式，统一返回By，用例中直接使用driver.findElements/driver.findElement方式
4.	用例中存在多case数据共享，项目采用redis方式存放的。
c)	用例执行
 
d)	报告输出
Testng配置文件中注册了ExtentTestNGITestListener监听器，当每个用例执行结束之后，该监听器会自动记录用例的信息，并从redis中获取base64截图信息
三、工程代码存放位置
http://uniqlofrsvn.saas.hand-china.com/svn/FRPF/automation/UniqloAppUITest

四、Jenkins账户、工程执行步骤
地址：http://192.168.11.168:8080/
账户：admin/Atcadmin2018
工程执行步骤：
1）确认本地环境已搭建好，测试手机已登录并开启微信账号
2）工程所在位置如下，Uniqlo-Android-APPUI-AutoTest为安卓工程，Uniqlo-IOS-APPUI-AutoTest为IOS工程，两个工程的执行方法一致，下面以安卓工程为例进行说明
 
3）点击Uniqlo-Android-APPUI-AutoTest进入安卓工程，点击配置，进入配置页面，检查节点机是否可用
 
4）再次进入安卓工程，点击Build with Parameters
 
4）xmlFileName填写svn工程目录下面的任一testng.xml文件名即可（根据自己的需要），然后点击开始构建，即可启动自动化测试任务
 
 （任选其一，也可以创建自己的testng.xml文件）
5）可以在Jenkins的控制台输出实时查看测试的进展情况
 


五、工程维护注意事项
测试报告中出现fail的用例，请按如下步骤排查问题：
1）首先根据测试报告、截图和日志分析问题的原因，如网络延迟、断言不匹配等等
2）如果是网络延迟导致元素定位不到、页面显示不出来，则需要重新执行测试
3）如果是不是由于网络延迟造成的元素定位不到，则可能是xpath路径发生改变，需要修改测试脚本；
4）如果是断言不匹配，请确认是否是bug；如果不是bug，可能是测试脚本的问题
5）测试脚本的问题请检查测试用例，
   测试用例存放路径：UniqloAppUITest\src\test\java\com\uniqloAPPUI\cases


六、问题Troubleshooting相关联系人
古林让、赵家启

