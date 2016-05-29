# pm25
关于pm_data的数据预处理  

*** 
## 运行环境：
* java: 1.7
* [MongoDB](https://docs.mongodb.com/manual/installation/): 3.2.6
* [driver](http://mongodb.github.io/mongo-java-driver/3.2/driver/): mongo-java-driver-3.2.2

## 文件结构
1. InsertPmHour.java, 主程序类，从数据文件中读取数据存储到MongoDB中；
2. MyDate.java，工具类，提供有关时间的一些方法，比如计算星期几等；
3. Test.java,用于写程序过程中测试一些类与方法。
