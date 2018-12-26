一个简单的HTTP Server.
---
### 概述
这是一个能够访问当前目录的简易http服务器，支持get、post、put、delete四种请求方式。

### 使用说明
1. 当前项目目录下建立webapp作为该服务器的root目录；

### 改进
1. 实现但线程http server，可以解析常见http方法；
2. 通过线程池来提高并发量；
3. 通过NIO模型来实现异步非阻塞通信；

