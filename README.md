# 打桩 
* 81/82 暴露端口对外访问 9100（权限参考apm监控）
* 81/82 上传 jacocoagent.jar
java -javaagent:jacocoagent.jar=includes=*,output=tcpserver,port=9100,address=127.0.0.1 -jar target/spring-boot-helloworld-0.0.1-SNAPSHOT.jar 
# dump exec文件
* 在jenkins中操作
 java -jar jacococli.jar dump --address 127.0.0.1 --port 9100 --destfile ./jacoco1.exec --reset
  java -jar jacococli.jar dump --address 127.0.0.1 --port 9100 --destfile ./jacoco2.exec --reset
# merge exec文件
 java -jar jacococli.jar merge ./jacoco*.exec --destfile merge.exec
 Java -jar jacococli.jar report ./jacoco.exec --classfiles target/classes --sourcefiles src/main/java --encoding utf-8 --html jacoReport
 Java -jar jacococli.jar report ./merge.exec --classfiles target/classes --sourcefiles src/main/java --encoding utf-8 --html jacoReport
# 生成报告  
* jenkins clone 获取源码
* jenkins mvn package 获取class文件
 Java -jar jacococli.jar report ./jacoco.exec --classfiles target/classes --sourcefiles src/main/java --encoding utf-8 --html jacoReport
 Java -jar jacococli.jar report ./merge.exec --classfiles target/classes --sourcefiles src/main/java --encoding utf-8 --html jacoReport
 
 ------------------------------
 
 # html在jenkins中如何显示   「」「」「」「」「」」「」「」「」「高优级别
 * jmeter-report
 * jacoco-report
 
 # jenkinsfile流水线流程
 * CD发布完成-jacoco打桩成功
 * 执行jmeter测试脚本-生成测试报告
 * 执行shell-jacoco命令获取exec文件
 * 获取devops服务源码和class文件-生成测试报告