package cn.cnic;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.Date;

/**
 * Created by hadoop on 16-5-28.
 *
 * 将2015-1020～2016-04每小时的pm25数据存入数据库中
 */


public class InsertPmHour {
    public static void main(String[] args){
        String filepath = "/home/hadoop/pm25/201510201604/";
        File file = new File(filepath);
        File[] tempList = file.listFiles();
        String encoding = "utf-8";
        String zone = " +0800";


        MongoClient mgc = new MongoClient("localhost");

        MongoDatabase database = mgc.getDatabase("test"); //修改数据库
        MongoCollection<Document> col = database.getCollection("pm_data"); //修改collection
        Document doc = new Document();
        int count = 0;
        int null_cnt = 0;

        //System.out.println(tempList.length); 217个文件
        for(int i=0;i<tempList.length;i++){
            if (tempList[i].isFile() && tempList[i].exists()) { // 判断文件是否存在
                System.out.println("file "+i+": "+tempList[i]);
                try {
                    InputStreamReader read = null;
                    read = new InputStreamReader(new FileInputStream(tempList[i]), encoding); // 考虑到编码格式
                    BufferedReader br = new BufferedReader(read);
                    String line = "";
                    int num = 0;

                    while((line=br.readLine())!=null){
                        String[]data  = line.split(",");
                        num++;
                        // skip the first line including the data attr
                        if(num==1){
                            continue;
                        }else{

                            //若前面几项空气指标都为空，那么该条数据删除
                            if(data[8].equals("") && data[8].equals(data[13]) && data[13].equals(data[11])
                                    &&data[11].equals(data[15]) && data[15].equals(data[19]) && data[19].equals(data[21])
                                    && data[21].equals(data[9])){
                                null_cnt++;
                                //System.out.println("all is null");
                                continue;
                            }

                            // 为防止前面某个数据项为空，影响后面数据的插入，所以分开
                            try {
                                doc.put("aqi",Float.parseFloat(data[8]));
                            }catch (Exception e){
                                //System.out.println("aqi: "+data[8]+","+"co "+data[13]+","+"no2 "+data[11]+","+"o3 "+data[15]+","+
                                //    "pm10 "+data[19]+","+"pm25 "+data[21]+","+"so2 "+data[9]);
                                //e.printStackTrace();
                                doc.put("aqi",-1);
                            }
                            try {
                                doc.put("co",Float.parseFloat(data[13]));
                            }catch (Exception e){
                                doc.put("co",-1);
                            }
                            try {
                                doc.put("no2",Float.parseFloat(data[11]));
                            }catch (Exception e){
                                doc.put("no2",-1);
                            }
                            try {
                                doc.put("o3",Float.parseFloat(data[15]));
                            }catch (Exception e){
                                doc.put("o3",-1);
                            }
                            try {
                                doc.put("pm10",Float.parseFloat(data[19]));
                            }catch (Exception e){
                                doc.put("pm10",-1);
                            }
                            try {
                                doc.put("pm25",Float.parseFloat(data[21]));
                            }catch (Exception e){
                                doc.put("pm25",-1);
                            }
                            try {
                                doc.put("so2",Float.parseFloat(data[9]));
                            }catch (Exception e){
                                doc.put("so2",-1);
                            }



                            doc.put("code",data[4]);
                            data[23] = data[23]+zone;
                            Date date = MyDate.strToDate(data[23]);
                            doc.put("time",date);
                            doc.put("dayofweekbj",MyDate.dayofWeek(date));
                            doc.put("hourofdayjb",MyDate.hourofDay(date));
                            doc.put("dayofmonthbj",MyDate.dayofMonth(date));
                            doc.put("dayofyearbj",MyDate.dayofYear(date));
                            doc.put("weekofyearbj",MyDate.weekofYear(date));
                            doc.put("weekofmonthbj",MyDate.weekofMonth(date));

                            col.insertOne(doc);

                            doc.clear();
                            count++;

                        }



                    }
                    System.out.println("all null: "+null_cnt);
                    System.out.println("count: "+count);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{
                System.out.println("文件不存在！");
            }
        }
        System.out.println("total all null: "+null_cnt);
        System.out.println("total count: "+count);
    }



}
