package cn.cnic;
/**
 * 用于熟悉mongodb-java-driver & 一些测试
 *
 * **/
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class Test {

    public static void main(String[] args) {
	// test mongodb-java
        MongoClient mgc = null;

        mgc = new MongoClient("localhost");
        MongoDatabase database = mgc.getDatabase("airdb");
        MongoCollection<Document> col = database.getCollection("pm_data");

        /*
        // test date

        String date = "2016-05-30 14:00:00";
        //String date = "2013-12-20 14:00:00";
        String zone = " +0800";
        date = date + zone;

        Date dt = MyDate.strToDate(date);
        System.out.println("day of week: "+ MyDate.dayofWeek(dt));
        System.out.println("hour of Day: "+ MyDate.hourofDay(dt));
        System.out.println("day of Month: "+MyDate.dayofMonth(dt));
        System.out.println("day of year: "+MyDate.dayofYear(dt));
        System.out.println("day of year: "+ MyDate.weekofYear(dt));
        System.out.println("week of Month: "+MyDate.weekofMonth(dt));
        */


        //String str = "province,city,city_code,station,station_code,level,index,pollutions,aqi,so2,so2_24h,no2,no2_24h,co,co_24h,o3,o3_24h,o3_8h,o3_8h_24h,pm10,pm10_24h,pm2_5,pm2_5_24h,pubtime";
        //String[] data = str.split(",");
        /*
        for(int i=0;i<data.length;i++){
            System.out.println(i+": "+data[i]);
        }
        */
        Document doc = null;

        MongoCursor<Document> cursor = col.find().limit(5).iterator();
        try {
            while (cursor.hasNext()) {
                //System.out.println(cursor.next().toJson());
                doc = cursor.next();

               // System.out.println(doc.get("aqi"));
            }
        } finally {
            cursor.close();
        }



    }


}
