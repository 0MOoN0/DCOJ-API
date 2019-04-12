# DOCJ
Drop Code Online Judge，在线学习评测系统  
# 使用提供的批处理文件启动应用
本系统仍在开发中，系统内容迭代速度较快，现提供批处理文件使本系统快速启动，以便测试
## 必须的环境
数据库：MySQL，需要root用户的密码为root，否则请修改`dcoj-web/src/main/resources/application-leon.yml`配置文件里面对应的数据库用户和密码  
JDK1.8  
Maven：  
1.  需要配置环境变量，以便使用`mvn`命令对项目进行管理  
2.  第一次启动该应用时需要从远程仓库上下载大量依赖，请配置好maven本地仓库，镜像等，远程仓库镜像推荐使用国内镜像  
## 应用更新
应用更新使用`git pull`命令
## 应用启动
第一次运行需要对文件进行打包，请使用`mvn_compile_start.bat`批处理文件对应用进行打包的启动  
其他情况可以直接使用`mvn_start.bat`启动

