# SSO

不同顶级域名的单点登录的简单测试

两个顶级域名 www.tv.com & www.music.com (修改过host文件，映射向本地的ip地址)


## 相同顶级域名的单点登录

### 是什么

SingleSingOn, SSO 单点登陆可以通过基于用户会话的共享。

比如有个一级域名 sin.com 但是也有二级域名 music.sin.com, 用户只需要在一个域名登录 根据这个登录的凭证 就可以在二级域名登录。 也就是 用户在一个网站登陆后，那他产生的会话就共享给了其他网站，都是同一个用户会话

### Cookie redis 实现sso

前端通过使用cookie，可以保证在二级域名获取到。

- 顶级域名和二级域名之间的cookie值是可以共享的，可以被携带到后端
- 二级域名自己的独立cookie是不能共享的，不能被其他的二级域名获取

## 不同顶级域名的单点登录

不同域名导致的cookie是无法发送的。

### CAS

Central Authentication Serivce 用于不同顶级域名之间的单点登陆问题。

## 错误
```shell
WARN  AnnotationConfigServletWebServerApplicationContext:591 - Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'SSOController': Unsatisfied dependency expressed through field 'userService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'userServiceImpl': Unsatisfied dependency expressed through field 'usersMapper'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.example.ssodemo.mapper.UsersMapper' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
```
为啥