@echo off
echo "׼��������Ŀ����ȷ�����ػ�����װMaven��Mysql"
pause
echo "���ڰ�װdcoj-judge�����زֿ�......"
cd ./dcoj-judge
call mvn clean install
echo "dcoj-judge��װ�ɹ�����������web������رմ˴���"
cd ../dcoj-web
echo "��������webӦ��......"
mvn spring-boot:run
