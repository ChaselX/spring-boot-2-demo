文章地址：https://www.jianshu.com/p/e8818819a68f

#### 使用Maven构建项目

可以用IDEA集成好的Spring Initializr来创建一个SpringBoot项目。

![IDEA创建SpringBoot项目](https://upload-images.jianshu.io/upload_images/7882361-aecad41f87158ef5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Group和Artifact根据你的项目随意命名](https://upload-images.jianshu.io/upload_images/7882361-1723924af7e009cb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

勾选上自己需要的依赖（不选也没关系，在Maven中手动加即可）

![](https://upload-images.jianshu.io/upload_images/7882361-caa8b13ab9e5d515.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### Spring Boot应用启动器

Spring Boot提供了很多应用启动器，分别用来支持不同的功能，因为Spring Boot的自动化配置特性，我们不需考虑项目依赖版本问题，使用Spring Boot的应用启动器，它能自动帮我们将相关的依赖全部导入到项目中。

这里介绍几个常见的应用启动器：

* spring-boot-starter: Spring Boot的核心启动器，包含了自动配置、日志和YAML
* spring-boot-starter-aop: 支持AOP面向切面编程的功能，包括spring-aop和AspecJ
* spring-boot-starter-cache: 支持Spring的Cache抽象
* spring-boot-starter-artermis: 通过Apache Artemis支持JMS（Java Message Service）的API
* spring-boot-starter-data-jpa: 支持JPA
* spring-boot-starter-data-solr: 支持Apache Solr搜索平台，包括spring-data-solr
* spring-boot-starter-freemarker: 支持FreeMarker模板引擎
* spring-boot-starter-jdbc: 支持JDBC数据库
* spring-boot-starter-Redis: 支持Redis键值储存数据库，包括spring-redis
* spring-boot-starter-security: 支持spring-security
* spring-boot-starter-thymeleaf: 支持Thymeleaf模板引擎，包括与Spring的集成
* spring-boot-starter-web: 支持全栈式web开发，包括tomcat和Spring-WebMVC
* spring-boot-starter-log4j: 支持Log4J日志框架
* spring-boot-starter-logging: 引入Spring Boot默认的日志框架Logback

也可以在Spring官网 https://start.spring.io/ 构建项目，勾选上自己需要的依赖即可（之后在Maven里再加也可以）。

项目的创建成功以后的Maven如下所示：

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.example</groupId>
	<artifactId>demo</artifactId>
	<version>0.0.1-SNAPSHOT</version> 发布文章
	<packaging>jar</packaging>

	<name>demo</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.0.5.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.2</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
```

#### 配置系统基本参数

要访问mysql数据库，还需要配置一下系统变量。
默认的系统变量配置文件是项目当前文件夹的`/src/main/resources`下的`application.properties`

![](https://upload-images.jianshu.io/upload_images/7882361-c9f43efdb43307d3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

但是我更喜欢yml的风格，删掉这个文件，在相同的位置创建一个`application.yml`文件

```
# 指定端口号
server:
  port: 8080
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password:
mybatis:
  mapper-locations: classpath*:mapper/*.xml  #注意：一定要对应mapper映射xml文件的所在路径
  type-aliases-package: com.example.demo.model.entity  # 注意：对应实体类的路径
```
#### 编写控制层处理HTTP请求

在`/src/main/java/com/example/demo/controller`下创建一个`HelloController.java`

```
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChaselX
 * @date 2018/10/6 18:56
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping
    public String sayHello() {
        return "Hello SpringBoot！";
    }
}
```

运行`DemoApplication.java`，浏览器请求`localhost:8080`可以看到如下效果

![localhost:8080](https://upload-images.jianshu.io/upload_images/7882361-fb9495696b10d8ee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 通过Mybatis操作数据库

根据之前的配置

```
mybatis:
  mapper-locations: classpath*:mapper/*.xml  # 注意：一定要对应mapper映射xml文件的所在路径
  type-aliases-package: com.example.demo.model.entity # 对应实体类的路径
```

在`/src/main/java/com/example/demo/model/entity`下创建实体类`SysUser.java`

```
package com.example.demo.model.entity;

/**
 * @author ChaselX
 * @date 2018/10/7 17:42
 */
public class SysUser {

    private static final long serialVersionUID = 215517484123587L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 账号是否可用
     */
    private boolean enabled;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
```

在`/src/main/java/com/example/demo/mapper`下创建`UserMapper.java`

```
package com.example.demo.mapper;

import com.example.demo.model.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ChaselX
 * @date 2018/10/7 17:53
 */
public interface UserMapper {
    @Insert("INSERT INTO user(username, password, name, mobile) VALUES(#{username}, #{password}, #{name}, #{mobile})")
//    返回插入记录的主键id
//    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int add(SysUser user);

    @Select("SELECT * from user")
    List<SysUser> getAll();
}
```

在`DemoApplication.java`上加一个`@MapperScan("com.example.demo.mapper")`注解，这个注解的作用是自动扫描com.example.demo.mapper包下的Mapper，实现并注入到Bean中。

```
package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

```

编写对应的后端控制层与服务层业务逻辑代码，文件位置参考代码中的`package`

```
package com.example.demo.service;

import com.example.demo.model.entity.SysUser;

import java.util.List;

/**
 * @author ChaselX
 * @date 2018/10/8 8:59
 */
public interface UserService {
    List<User> getAll();

    boolean addUser(SysUser user);
}
```

```
package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChaselX
 * @date 2018/10/8 8:59
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<SysUser> getAll() {
        return userMapper.getAll();
    }

    @Override
    public boolean addUser(SysUser user) {
        return userMapper.add(user) > 0;
    }
}
```

```
package com.example.demo.controller;

import com.example.demo.model.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author ChaselX
 * @date 2018/10/8 9:08
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody SysUser user) {
        if (userService.addUser(user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("创建用户失败！");
    }
}
```

现在，可以运行项目利用`postman`对上面的功能接口进行测试了

![](https://upload-images.jianshu.io/upload_images/7882361-58173e1544e3fbb9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### redis配置与使用（非必须）

这里不讲redis的安装和启动，只讲项目如何使用redis

在`pom.xml`中加入`spring-boot-starter-data-redis`依赖

```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-redis</artifactId>
	</dependency>
```

对系统配置文件`application.yml`做如下配置

```
spring: 
  redis:
      host: 127.0.0.1
      port: 6379
      timeout: 2000ms
      database: 0
      password:
      lettuce:
        pool:
          max-active:  100 # 连接池最大连接数（使用负值表示没有限制）
          max-idle: 100 # 连接池中的最大空闲连接
          min-idle: 50 # 连接池中的最小空闲连接
          max-wait: 6000ms
```

由于使用的是SpringBoot 2.0推荐的lettuce连接池。SpringBoot 2.0需要手动构建`LettuceConnectionFactory Bean`，

```
package com.example.demo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author ChaselX
 * @date 2018/9/4 10:02
 */
@Configuration
public class RedisConfig {
    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort()));
    }
}
```

##### Redis哨兵主从模式

对系统配置文件`application.yml`做如下配置

```
  spring: 
    redis:
      timeout: 2000ms
      database: 1
      lettuce:
        pool:
          max-active:  100 # 连接池最大连接数（使用负值表示没有限制）
          max-idle: 100 # 连接池中的最大空闲连接
          min-idle: 50 # 连接池中的最小空闲连接
          max-wait: 6000ms
      sentinel:
        master: mymaster
        nodes: 10.1.58.117:27379,10.1.58.137:27379
      password: 
```

构建哨兵模式的连接工厂，修改`LettuceConnectionFactory`

```
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration(redisProperties.getSentinel().getMaster(), new HashSet<>(redisProperties.getSentinel().getNodes()));
        redisSentinelConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        return new LettuceConnectionFactory(redisSentinelConfiguration);
    }
```

##### Redis集群

对系统配置文件`application.yml`做如下配置

```
spring: 
  redis:
    cluster:
      nodes: 
        - 192.168.1.111:7001
        - 192.168.1.112:7001
        - 192.168.1.110:7002
        - 192.168.1.110:7001
        - 192.168.1.111:7002
        - 192.168.1.112:7001
      password:
      lettuce:
        pool:
          max-active:  100 # 连接池最大连接数（使用负值表示没有限制）
          max-idle: 100 # 连接池中的最大空闲连接
          min-idle: 50 # 连接池中的最小空闲连接
          max-wait: 6000ms
      timeout: 2000ms
```

构建集群模式的连接工厂，修改`LettuceConnectionFactory`

```
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration) {
        return new LettuceConnectionFactory(redisClusterConfiguration);
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        configuration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        return configuration;
    }
```

##### 使用Redis

改造之前的`HelloController`对redis功能做简单的测试

```
package com.example.demo.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author ChaselX
 * @date 2018/10/6 18:56
 */
@RestController
@RequestMapping("/")
@MapperScan("com.example.demo.mapper")
public class HelloController {
    @Autowired
    private RedisTemplate<String, String> stringStringRedisTemplate;

    @GetMapping
    public String sayHello() {
        stringStringRedisTemplate.opsForValue().set("Say hello", "Hello SpringBoot From Redis!", 5, TimeUnit.SECONDS);
        return stringStringRedisTemplate.opsForValue().get("Say hello");
    }
}
```

运行项目

![](https://upload-images.jianshu.io/upload_images/7882361-3eeac62feccca00e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



#### 数据库创建用户及授权（非必须）

对于正式生产环境，你登录到数据库的往往不会是root用户，而是通过仅具有特定数据库权限的用户登录数据库。可以通过下面的SQL语句创建数据库用户。

```
insert into mysql.user(Host,User,Password) values("%","admin",password("admin123"));

GRANT ALL ON db_name.* TO admin@% identified by "admin123";

flush privileges;
```

#### 数据库连接池druid配置（非必须）

Maven中加入druid依赖

```
	<dependency>
		<groupId>com.alibaba</groupId>
		<artifactId>druid-spring-boot-starter</artifactId>
		<version>1.1.10</version>
	</dependency>
```

在`application.yml`中加上如下配置

```
spring:
  datasource:
    druid:
      # 初始化大小，最小，最大
      initialSize: 5
      minIdle: 5
      maxActive: 20
      # 配置获取连接等待超时的时间
      maxWait: 60000
      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
      filters: stat,wall,slf4j
      # 打开PSCache，并且指定每个连接上PSCache的大小
      poolPreparedStatements: true
      maxOpenPreparedStatements: 20
      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      # yml方式配置servlet与filter
      stat-view-servlet:
        enabled: true
        # /druid登录账号
        login-username: admin 
        # /druid登录密码
        login-password: admin 
        reset-enable: false
      web-stat-filter:
        enabled: true
        exclusions: /druid/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico
        url-pattern: /*
```

配置好`druid`后访问 `{url}/druid`，由于配置了登录账号和密码，需要身份认证

![](https://upload-images.jianshu.io/upload_images/7882361-dcac5752ea7f2ec8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

认证成功后便可通过监控页面查看各项监控数据

![](https://upload-images.jianshu.io/upload_images/7882361-6519036e2af46782.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 基于Spring Security登录机制的登录验证与认证

传统的登录是通过cookie-session方式实现登录认证，而在前后端分离的情况下，实现用户的认证与鉴权的更好的方式是使用JWT(Java Web Token)

##### 引入Spring Security

在`pom.xml`中加入`Spring Security`依赖

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
```

在项目中加入`WebSecurityConfig`配置文件

```
package com.example.demo.common.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ChaselX
 * @date 2018/11/28 16:18
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // http请求安全配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated() // 所有请求都需要权限验证
                .and()
                .logout().permitAll()
                .and()
                .formLogin();

    }

//    // 忽略web静态资源，若需要
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
//    }
}
```

修改一下之前的`HelloController`

```
package com.example.demo.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author ChaselX
 * @date 2018/10/6 18:56
 */
@RestController
@RequestMapping("/")
@MapperScan("com.example.demo.mapper")
public class HelloController {
    @Autowired
    private RedisTemplate<String, String> stringStringRedisTemplate;

    @GetMapping
    public String mainPage() {
        return "Hello SpringBoot From \"/\"";
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        stringStringRedisTemplate.opsForValue().set("demo:SayHello", "Hello SpringBoot From Redis!", 5, TimeUnit.SECONDS);
        return stringStringRedisTemplate.opsForValue().get("demo:SayHello");
    }
}
```

运行项目测试

![首页正常展示](https://upload-images.jianshu.io/upload_images/7882361-f2e065b80bfe5bbd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

访问`localhost:8080/sayHello`会跳转到`http://localhost:8080/login`登录页

![](https://upload-images.jianshu.io/upload_images/7882361-3f40603a7bb47a6c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Spring Security的安全策略已经生效，但是具体的登录功能还没有实现

##### 基于Spring Security的登录功能实现

要实现基于Spring Security的登录功能，首先需要定义一个继承了Spring Security的`UserDetailsService`接口的接口，修改一下之前的UserService

```
package com.example.demo.service;

import com.example.demo.model.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author ChaselX
 * @date 2018/10/8 8:59
 */
public interface UserService extends UserDetailsService {
    List<SysUser> getAll();

    boolean addUser(SysUser user);
}
```

再修改一下接口的实现类`UserServiceImpl`实现`UserDetailsService`接口的`loadUserByUsername()`方法

```
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
```

因为要返回UserDetails对象，具体方法实现先放在一边。先看看如何使用这个方法进行用户认证。在`SpringSecurityConfig`加入以下代码

```
    @Autowired
    private UserDetailsService userServiceImpl; // 属性名为userServiceImpl对应实现类的名称

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
    }
```

###### 密码自定义加密验证

用户的密码在数据库中通常是以密文的形式存储的，为此需要实现一个密码的自定义验证，指定Spring Security使用什么加密规则对密码进行验证，创建一个bean`bCryptPasswordEncoder`

```
package com.example.demo.common.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ChaselX
 * @date 2018/11/28 19:21
 */
@Configuration
public class BaseConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

修改`WebSecurityConfig`的代码指定`passwordEncoder`

```
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }
```

由于使用了密码加密验证，需要修改一下添加用户那里的逻辑，在插入数据库之前先对密码做BCrypt加密

```
package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChaselX
 * @date 2018/10/8 8:59
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public List<SysUser> getAll() {
        return userMapper.getAll();
    }

    @Override
    public boolean addUser(SysUser sysUser) {
        sysUser.setPassword(encoder.encode(sysUser.getPassword()));
        return userMapper.add(sysUser) > 0;
    }
}
```


这样便实现了密码的自定义验证

###### 基于RBAC的用户、角色、权限

虽然实现了加密验证，但是却没有定义权限验证相关的用户、角色、权限

要使用Spring Security安全框架，由于`UserDetailsService.loadUserByUsername()`返回的是一个`UserDetails`类型的对象。`UserDetails`接口中最重要的是`getAuthorities()`方法，用户所具有的所有权限都定义在里面。因此需要做些处理，从数据库获取系统用户，并根据相关的角色权限来构造`UserDetails`的`authorities`属性，为此首先需要定义好系统的用户、角色、权限实体表与它们之间的关系表。用户实体类已经定义好，还有角色、权限、用户角色以及角色权限未定义。

```
package com.example.demo.model.entity;

import java.util.Date;

/**
 * 角色实体类
 *
 * @author ChaselX
 * @date 2018/12/1 16:05
 */
public class Role {
    /**
     * 角色的authority前缀
     */
    public static final String PREFIX = "ROLE_";

    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色代号
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    private Long operator;

    private Date operateTime;

    // 省略get/set方法代码
}
```

```
package com.example.demo.model.entity;

import java.util.Date;

/**
 * 权限实体类
 * 
 * @author ChaselX
 * @date 2018/12/1 16:00
 */
public class Permission {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operateTime;

    // 省略get/set方法代码
}
```

```

package com.example.demo.model.entity;

import java.util.Date;

/**
 * 用户-角色关系表
 *
 * @author ChaselX
 * @date 2018/12/1 16:17
 */
public class UserRole {
    private Long id;

    private Long userId;

    private Long roleId;

    private Long operator;

    private Date operateTime;

    // 省略get/set方法代码
}
```

```
package com.example.demo.model.entity;

import java.util.Date;

/**
 * 角色-权限关系表
 *
 * @author ChaselX
 * @date 2018/12/1 16:25
 */
public class RolePermission {
    private Long id;

    private Long roleId;

    private Long permissionId;

    private Long operator;

    private Date operateTime;

    // 省略get/set方法代码
}
```

数据库相关建表这边就不赘述了，按照基本的主键id自增，参数驼峰命名法转下划线命名法即可，若有需要日后再补充。

为了减少数据库的访问次数，一次性将用户相关的信息（角色、权限）查询出来，封装一个`SysUserVO`类

```
package com.example.demo.model.vo;

import com.example.demo.model.entity.Permission;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.SysUser;

import java.util.List;

/**
 * @author ChaselX
 * @date 2018/12/1 16:51
 */
public class SysUserVO extends SysUser {
    private List<Role> roles;

    private List<Permission> permissions;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
```

在`UserMapper`添加一个查询用户详细信息的方法`getDetailsByUsername`

```
package com.example.demo.mapper;

import com.example.demo.model.entity.SysUser;
import com.example.demo.model.vo.SysUserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ChaselX
 * @date 2018/10/7 17:53
 */
public interface UserMapper {
    @Insert("INSERT INTO user(username, password, name, mobile) VALUES(#{username}, #{password}, #{name}, #{mobile})")
//    返回插入记录的主键id
//    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int add(SysUser sysUser);

    @Select("SELECT * from user")
    List<SysUser> getAll();

    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
            @Result(property = "roles", column = "user_id", many = @Many(select = "com.example.demo.mapper.RoleMapper.getRolesByUserId")),
            @Result(property = "permissions", column = "user_id", many = @Many(select = "com.example.demo.mapper.PermissionMapper.getPermissionsByUserId"))
    })
    SysUserVO getDetailsByUsername(String username);
}
```

注意，由于`SysUserVO`的两个字段roles与permissions是集合类型的，所以用到了`@Results`、`@Result`与`@Many`注解，更全面的说明可参考[mybatis官方文档（需翻墙）](https://www.cnblogs.com/HawkFalcon/p/7988024.html)。

`@Many`注解的`select`属性表明select引用的来源分别为`com.example.demo.mapper.RoleMapper`下的`getRolesByUserId`方法与`com.example.demo.mapper.PermissionMapper`下的`getPermissionsByUserId`方法

```
package com.example.demo.mapper;

import com.example.demo.model.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ChaselX
 * @date 2018/12/4 14:36
 */
public interface RoleMapper {
    @Select("select r.id, r.code, r.name, r.remark from role r where r.id in (select ur.role_id from user_role ur where ur.user_id = #{userId})")
    List<Role> getRolesByUserId(Long userId);
}
```

```
package com.example.demo.mapper;

import com.example.demo.model.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ChaselX
 * @date 2018/12/4 14:58
 */
public interface PermissionMapper {
    @Select("SELECT p.id, p.code, p.name FROM permission p WHERE p.id IN (" +
            "SELECT rp.permission_id FROM role_permission rp WHERE rp.role_id in(" +
            "SELECT ur.role_id FROM user_role ur WHERE ur.user_id = #{userId}))")
    List<Permission> getPermissionsByUserId(Long userId);
}
```

这些都完成了以后就可以动手实现前面放置在一边的`UserServiceImpl`的`loadUserByUsername`方法了

```
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserVO userVO = userMapper.getDetailsByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : userVO.getRoles()
        ) {
            authorities.add(new SimpleGrantedAuthority(Role.PREFIX + role.getCode()));
        }
        for (Permission permission : userVO.getPermissions()
        ) {
            authorities.add(new SimpleGrantedAuthority(permission.getCode()));
        }
        return new User(userVO.getUsername(), userVO.getPassword(), authorities);
    }
```

现在登录功能已经实现，可以运行项目进行登录功能测试了（tips：在登录之前需要先在用户表中加入使用BCrypt加密的用户记录），项目启动后访问http://localhost:8080/login会跳转到登录界面

![](https://upload-images.jianshu.io/upload_images/7882361-191ba755e47e1324.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

输入用户名和密码，登录成功后会返回系统首页

![](https://upload-images.jianshu.io/upload_images/7882361-601a1618385d9262.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


##### aes加密传输登录密码

在非https的情况下，若无特殊处理，用户的登录密码会以明文的方式传输给后端。因此需要对用户密码进行加密传输，保证请求报文即使被截取，也不会泄露用户的密码。前后端加解密流程如下（[图片引用](https://segmentfault.com/a/1190000014544933)）：

![](https://upload-images.jianshu.io/upload_images/7882361-b9c2645162094d0e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###### 调用接口获取动态加密秘钥

在客户端向后端post登录信息之前，先调用接口获取动态加密秘钥，前端生成随机秘钥，后端会把缓存放进redis里，为了安全性考虑，缓存的有效期设置为5s



客户端收到动态加密秘钥后，通过秘钥对密码做AES加密，将登陆信息通过POST请求发送给后端

##### jwt动态刷新

#### 数据库分页查询

往项目的pom.xml里加入

```
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>latest version</version>
</dependency>
```