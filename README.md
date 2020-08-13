@[toc]
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813181540561.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2FiY251bGw=,size_16,color_FFFFFF,t_70#pic_center)

# 写在前面

**java**

java 当前最主流的后端开发语言，目前做自动化主要是两大阵营 java 和 python，这里不讨论游戏自动化，相比而言，小厂的 python 稍微主流一些，大厂的 java 稍微主流一些，毕竟大厂几乎全部被 java 占领，而测试作为保障项目质量的一个关键角色，必须要打穿整个项目流程，因此贴近研发代码就显得尤为重要

**testng**

目前整个测试圈有两大阵营，一个是忠实的 testng 阵营，另一个是新潮流 junit5 阵营，不论哪个阵营未来都会成为测试圈的主流，我个人感觉纯粹的自动化测试或者测开应该是 testng 目前更占有又是，junit5 随着许多开发转为测试开发，会把 junit5 的技术带到测试圈进而丰富测试圈的技术栈

我个人认为 testng 比 junit5 更灵活，更加适合做自动化，但是 junit5 提供了更丰富的参数化运行，总的来说如果项目较为复杂我推荐使用更加灵活多变的 testng， 简单的项目就可以使用 junit5 来做

**appium**

写过 web 自动化脚本的人对于 appium 的上手会非常迅速，因为 appium 实际上是基于 selenium 的，脚本代码绝大部分都是一致的，只是对于 app 的 UI 测试来讲，配置稍微显得麻烦了一些，而且还要考虑到安卓和苹果两个系统，苹果对于安卓来说配置还要麻烦一些

我之前总结过一个继承关系图谱，WebDriver 是一个接口，MobileDriver 直接继承它，而 RemoteWebDriver 直接实现它

**本文的项目地址**

appuitest4j：[https://github.com/abcnull/appuitest4j](https://github.com/abcnull/appuitest4j)

另外我推荐我一个 web ui 测试项目的框架：[https://github.com/abcnull/webuitest4j](https://github.com/abcnull/webuitest4j)

# 项目结构介绍

我这里张贴一下我的项目结构图

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813181628756.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2FiY251bGw=,size_16,color_FFFFFF,t_70#pic_center)


整体框架都是放在 test 包下，采用了 pageobject 思想，我们将页面对象抽离出来，复杂的页面操作逻辑写在其中，pageobject 大致分为两个大的“阵营”，一个是 page 阵营，也就是单纯的页面对象，另一个就是 case 阵营存放测试用例脚本

- page 阵营

  - basepage 包

    这个是所有页面对象的父类，主要做页面对象共有方法的封装，这样在页面对象中大家就不用编写一些重复的复杂的页面操作方法，直接使用父类的方法即可，还包括一些页面初始化的操作

  - pageobject 包

    - data 包

      对于复杂的 pageobject，我们将页面中的数据进行分离，其中的 field 为 static final，类的命名比如 BaiduData

    - locator 包

      对于复杂 pageobject 可能会元素过多，这里我们也专门进行了分离，其中的 field 为 static final，类的命名比如 BaiduLocator

    - page 包

      存放非常纯粹的页面操作逻辑代码，可以直接使用 data 和 locator 中的数据，类的命名比如 BaiduPage

- case 阵营

  - basetest 包

    是所有 testcase 的父类，这里主要做的是测试的初始化配置操作，也就是说测试的一些工具配置放在 basetest 中，页面类的共有方法放在 BasePage 中，这样很好的分工可以减少项目的混乱程度，让整个架构变得更加清晰

  - testcase 包

    这里就可以直接写测试用例代码了

# 基本 usage

整个框架分给多个成员去完成自动化的任务，当框架优化好定型之后，编写自动化测试脚本的成员只需要关注下面几个包

- pageobject.data
- pageobject.locator
- pageobject.page
- testcase

其他包无需关注，因为成员只需要编写测试用例 testcase 和需要被测的页面类 pageobject 中的三个包

打个比方，比如要测试百度页面的搜索功能，我们可以直接在 pageobject.page 中写一个 BaiduPage 类，其中写一个 search 方法来实现完整的百度页面搜索数据的操作，其中这个数据字段可以通过 pageobject.data 中定义好的数据传进来，百度页面的搜索框的 xpath 定位也可以先写在 pageobject.locator 中，然后再 search 方法中直接调用这个 xpath 即可，最后我们在 testcase 包中写一个 BaiduTest 类，其中写一个 testSearch 方法，调用 BaiduPage 中的 search 方法，最后 assert 即可

具体更加详细和丰富的其他用法可以通过拉取博主的 github 项目来查看~

# 配置驱动项目

我们通过`.properties`文件的形式来进行配置驱动项目

这个`config.properties`存放在`src/test/resources/config`中

# 数据驱动

项目支持数据驱动的方式，我们实际中的 UI 自动化往往通过 jenkins 来持续构建，jenkins 的 job 中支持 param 传参，我们在 mvn 项目的 pom 文件中添加如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813181648652.png#pic_center)


xmlFileName，param1，param2，param3 这些名字需要与 jenkins 中传参名字一致，然后 mvn 中需要一个 surefile 插件，在这个插件的 suiteXmlFiles 标签中指定运行那个 testng.xml `<suiteXmlFile>testng.xml</suiteXmlFile>`，还可以指定传给 testng.xml 的参数（具体可以拉取项目代码查看）

# 日志系统

日志系统使用的是 slf4j+log4j 的形式来做，同时使用了 lombok 插件，我们将 log4j2.xml 的日志格式配置文件放在了`/src/main/resources`下，我们在代码打日志，先在类上打上`@Slf4j`，然后就可以直接使用`log.info`的形式了。当项目测试运行完，我们可以直接在项目下看到 logs 文件夹，可以在其中查看到我们的日志

# surefire 插件

surefire 是 mvn 的一个非常适用的测试插件，它可以结合 junit5，testng 来使用，我们可以使用它来帮助我们外部传参，可以使用它指定当我们 mvn 构建时候要执行哪个 testng.xml 文件

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813181709223.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2FiY251bGw=,size_16,color_FFFFFF,t_70#pic_center)


甚至我们可以使用 mvn 命令配合上 surefire 加上一些命令参数来运行指定的测试用例，这些可以通过访问 surefire 官网来查看

# testng 监听器

这里我目前使用了两个监听器（这些监听器都是来源于 testng 的，当我们继承或实现这些监听器，方便我们做拓展），一个是 TestListenerAdapter 类，另一个是 IAnnotationTransformer 接口

- TestListenerAdapter 类

  TestListenerAdapter 类很强大里头有测试执行过程中各个阶段的方法，比如测试开始时的方法，测试失败时，测试结束时等等，因此我们可以把这些方法重写，然后把 log 信息打印进去，这样我们就可以看见完整的 log 流程了，同时我把截图的代码存放在了被重写的测试失败的方法中

- IAnnotationTransformer 接口

  我通过实现这个接口来进行 testng.xml 测试用例运行优先级的调整，传统的 method 运行的优先级是：再一个 class 中，method 是依据方法名排序，所以基本是乱序的，与 testng.xml 中 method 的组织顺序无关，我加上这个监听器后，可以保证 testng.xml 中 class 里的 method 的书写顺序就是执行顺序，这个可以说是非常实用的，具体的源码我是参照了 stackoverflow

有了这些监听器，我们需要在 testng.xml 去声明一下，表示会使用这些监听器

```xml
<!-- 监听器 -->
<listeners>
    <!-- 测试日志监听器 -->
    <listener class-name="com.abcnull.listener.TestLogListener"/>
    <!-- 优先级监听器 -->
    <listener class-name="com.abcnull.listener.RePrioritizingListener"/>
</listeners>
```



# 责任链模式

其实对于 appium 来讲，我这里可以不加责任链模式的，由于相对的我有一个很完整的 webuitest4j 的项目，其中涉及到多个浏览器，因为不同的浏览器驱动配置可能不同，那里我使用到了责任链，所以相对的我决定 appium 这里也使用了责任链，责任链模式请阅读博主的这篇博文：[https://blog.csdn.net/abcnull/article/details/107565909](https://blog.csdn.net/abcnull/article/details/107565909)

大致思想就是把 android 和 ios 构成一条单链表，然后驱动初始化的时候来在单链表上一个结点一个结点的筛查，假如我测 android，我匹配到第一个结点为 android，那 ok，我在 android 这个结点中产生驱动，然后结束这个链表返回驱动即可，其实对于 appuitest4j 这个项目来说责任链显得不是那么有优势，因为它只有 android 和 ios 两个选择，选择余地太小，但是如果是对于 webuitest4j 这个项目来讲，责任链就很好了，因为它起到了优化 if else 的作用，可以避免我们写大量的 if else 或者 switch case 语句，针对 chrome 浏览器我们想改一些驱动初始化的配置，直接在 chrome 责任链结点中去改就行了

这里就提及一下，它不是此项目学习的关键，而仅仅算作一个小的优化

# 报告输出

传统的报告输出有两个常用的样式，一个是 extentreport 另一个是 allure 2 样式，相比而言 allure 2 要用的更多一些，本项目也是使用的 allure 2 报告样式

配置方式是结合了 surefire 插件，具体配置可以拉取代码查看细节

当我们项目运行完，会在项目直接路径下产生 allure-results 文件，我们运行`allure serve allure-results`即可解析 allure 2 报告，然后我们打开控制台返回的网址即可

allure 2 不仅仅是项目中安装依赖这么简单，也需要自己电脑中安装好 allure 工具，这样才能在自己电脑中正确解析报告，要不`allure serve allure-results`这个命令怎么来的(#^.^#)，具体的 allure 使用可以参照博主的另一篇博文[https://blog.csdn.net/abcnull/article/details/104095934](https://blog.csdn.net/abcnull/article/details/104095934)

# 其他

项目中还有其他很多文件和小的细节功能，比如说一些工具类，自定义的该项目的异常等等

项目 github 地址如下，欢迎大家 fork 和 star！！！

[https://github.com/abcnull/appuitest4j](https://github.com/abcnull/appuitest4j)

另外我有一个更为完整的 web UI 测试项目地址如下，也欢迎大家参观学习！！！

[https://github.com/abcnull/webuitest4j](https://github.com/abcnull/webuitest4j)
