@echo off
echo "准备编译项目，请确保本地环境安装Maven和Mysql"
pause
echo "正在安装dcoj-judge到本地仓库......"
cd ./dcoj-judge
call mvn clean install
echo "dcoj-judge安装成功，即将启动web，请勿关闭此窗口"
cd ../dcoj-web
echo "正在启动web应用......"
mvn spring-boot:run
