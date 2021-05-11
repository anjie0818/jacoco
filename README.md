# jacoco
## 调用方式
* ant/maven/cli/java api
## 原理
https://mp.weixin.qq.com/s?src=11&timestamp=1620728491&ver=3062&signature=5XXh2Vin-WnnVVsUR5zEWSSGKN069LtPMrxSzRmo9CGSaVzHrFe6nde8kn9ceZKKuULqiNpax-4hUiMtMLoCDB8R1AGr1hLhcnAlfNadKDZZlVnwC3*11z*IOe8G32ix&new=1
## 官方说明
https://www.jacoco.org/jacoco/trunk/doc/cli.html
## 精华贴
https://testerhome.com/topics/20632
## jacocoagent
### 打桩 
java -javaagent:jacocoagent.jar=includes=*,output=tcpserver,port=9100,address=127.0.0.1 -jar target/spring-boot-helloworld-0.0.1-SNAPSHOT.jar 
## jacococli
### execinfo
```
java -jar jacococli.jar execinfo jacoco1.exec |grep com.neo
                  HITS（命中数）/PROBES（探针）
79f2a5ea4e1e9c77    2 of   2   com/neo/Application
1bb9dcef454bb3cc    2 of   3   com/neo/controller/HelloWorldController
8edb1f12ee37c2e8   10 of  22   com/neo/Application$$EnhancerBySpringCGLIB$$5dd799d2
1bb9dcef454bb3cc    2 of   3   com/neo/controller/HelloWorldController
```
### classinfo   
java -jar jacococli.jar classinfo target/classes/com/neo/controller/HelloWorldController.class --verbose	

### dump exec文件
 java -jar jacococli.jar dump --address 127.0.0.1 --port 9100 --destfile ./jacoco1.exec --reset --quiet
 --reset 转储后在测试目标上重置执行数据	

# merge exec文件
 java -jar jacococli.jar merge ./jacoco*.exec --destfile merge.exec
# 生成报告  
* clone 获取源码
* mvn package 获取class文件
 Java -jar jacococli.jar report ./merge.exec --classfiles target/classes --sourcefiles src/main/java --encoding utf-8 --html jacoReport
 
 
 ### FAQ 
 * 覆盖率为0
    * 原因一：一个类多个统计结果（类名相同，类id不同，merage是安装id进行操作的） 
        * https://blog.csdn.net/qq744746842/article/details/115058826
        * https://blog.csdn.net/qq744746842/article/details/111713216
    * 原因二：https://testerhome.com/topics/20632
* 负载均衡统计覆盖率
    * 直接统计到一个exec文件就可以
### 探索
* “代码变更覆盖率”在后端测试中的实践
    * https://blog.csdn.net/weixin_34255055/article/details/86016254
* 变更代码覆盖率平台
    * https://github.com/hzlifeng1/DiffTestPlatform