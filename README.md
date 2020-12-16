# NrmServer

### 1.前言

我的博客：https://www.nroma.cn

### 2.NroServer介绍

简单web服务器

##### 2.1 部署项目



```
git clone https://github.com/moonris/NrmServer.git 
```



##### 2.2 启动项目

```
Server Run
```



##### 2.3 访问项目

```
http://locathost:8080/
```

```
http://locathost:8080/g
```

```
http://locathost:8080/r
```


### 3、思路



##### 1.解析web.xml文件，分别将servlet,servlet-mapping放到map集合中去，然后我们根据请求的url,去找到对应的servlet-class的名字，然后根据反射，调用service方法。 



##### 2.对request和response进行封装。 



##### 3.servlet的上下文,封装servlet与请求 



##### 4.响应客户端，响应行，响应头，响应体，通过流的操作发送个客户端。



### 4.源码下载



##### github:https://github.com/moonris/NroServer
