#  
名刊会项目简介  
  


#软件架构  

Build --- Gradle  
Compiler ---JAVA 1.8   
  
 后台技术：    
 
    SpringBoot2  
    HikariCP   
    Mybatis     
    swagger2  
    Scheduled    
    spring aop  
      

软件架构说明    
 
项目主要采用springBoot2 ,Rest的接口风格。 数据存储主要是leanClod。  
使用leanCloud的的SDK进行数据的存储操作。   
虽然目前项目中配置的mysql的数据库和mybatis，但并没有使用。因为leanCloud数据统计查询很不还好用。  
配置这些为以后把leancloud数据到处的本地做数据统计用。


  
  config    ------- 配置文件包，做一些初始化配置 如链接leandCloud数据存储的配置等。。。  
  common    ------- 公用文件包，放一些工具类，公用异常处理类，常量配置类等。。。  
  controller ------ 对外接口层，做一些请求类型，入参和出参的配置  
  service   ------  业务逻辑处理层，主要处理业务逻辑  
  dao       ------ 数据处理层，主要做一些数据处理业务

#安装教程  
xxxx  
xxxx 
xxxx  

#使用说明  

服务启动后，可通过访问 host:prot/swagger-ui.html(如：http://localhost:8888/swagger-ui.html )查看API 接口  
xxxx    
xxxx  

#参与贡献  

Fork 本项目
新建 Feat_xxx 分支
提交代码
新建 Pull Request
