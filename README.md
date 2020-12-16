# NrmServer
### 1.前言
博客：https://www.nroma.cn
### 2.NroServer介绍
##### 2.1 目录结构
NrmServer --|src --|cn --|nroma--|Dispatcher.java
            |                    |LoginServlet.java
            |                    |RegisterServlet.java
            |                    |Request.java
            |                    |Response.java
            |                    |Server.java
            |                    |Servlet.java
            |                    |WebApp.java
            |                    |WebContext.java
            |                    |WebHandler.java
            |src --|cn --|nroma--|model --|Servlet.java
            |                             |ServletMapping.java
            |      |error.html
            |      |index.html
            |      |web.xml
            |Login.html
##### 2.2 部署项目
这里写图片描述 这里写图片描述

##### 2.3 启动项目
运行bin目录下的startup.bat文件 这里写图片描述

##### 2.4 访问项目
这里写图片描述

这里写图片描述

这里写图片描述
### 3、思路
##### 1.解析web.xml文件，分别将servlet,servlet-mapping放到map集合中去，然后我们根据请求的url,去找到对应的servlet-class的名字，然后根据反射，调用service方法。 
##### 2.对request和response进行封装。 
##### 3.servlet的上下文,封装servlet与请求 
##### 4.响应客户端，响应行，响应头，响应体，通过流的操作发送个客户端。

### 4.源码下载
##### github:https://github.com/moonris/NroServer
