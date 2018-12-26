一个简单的HTTP Server.
---

### version 4
#### 概述
这是一个能够访问当前目录的简易http服务器，支持get、post、put、delete四种请求方式。
### 功能点
1. 通过线程池来提高并发量；
2. 通过NIO模型来实现异步非阻塞通信；

### 使用说明
1. 当前项目目录下建立webapp作为该服务器的root目录；