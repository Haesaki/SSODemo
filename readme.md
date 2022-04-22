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
1. Mapper 的Bean无法创建
只是用mybatis的话 需要手动在mapper class的上面加上@Mapper标注他是mapper class, 但是我使用了tk去管理mybatis 这样就省去了简单的数据库操作需要写麻烦的xml配置文件的问题.
```java
WARN  AnnotationConfigServletWebServerApplicationContext:591 - Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'SSOController': Unsatisfied dependency expressed through field 'userService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'userServiceImpl': Unsatisfied dependency expressed through field 'usersMapper'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.example.ssodemo.mapper.UsersMapper' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
```
我是使用了 tk.mybatis.spring 去托管mybatis，没有设置扫描mapper扫描目录，就会出现无法创建Bean的情况， 加上就可以解决这个问题
```java
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.ssodemo.mapper"})
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class SsoDemoApplication {}
```

2. dataSource 创建问题
    这是由于 spring application.yml引入其他配置文件的方式好像过期了 原来是
```yaml
spring:
  profiles:
    active: dev # Deprecated configuration property 'spring.profiles' 
  # 让你用这种方式去替代掉上面的这种配置 但是会出错 麻了
  config:
    activate:
      on-profile: 
```

3. classloader 出错???
```text
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'myMapper' defined in file 
[C:\SSODemo\target\classes\com\example\ssodemo\mapper\MyMapper.class]: Invocation of init method failed; 
nested exception is tk.mybatis.mapper.MapperException: 
tk.mybatis.mapper.MapperException: 
java.lang.ClassCastException: 
class sun.reflect.generics.reflectiveObjects.TypeVariableImpl cannot be cast to class java.lang.Class 
(sun.reflect.generics.reflectiveObjects.TypeVariableImpl and java.lang.Class are in module java.base of loader 'bootstrap')
```
[同时要注意 @MapperScan里的basePackage不能包含通用mapper（我的是BaseDao）的路径，只包含其他的mapper的路径，不然会报错](https://blog.csdn.net/joy_tom/article/details/110938464)