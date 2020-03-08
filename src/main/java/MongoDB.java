import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    private static String host = "localhost";
    private static int port = 27017;
    private static String databse = "codingbatdb";

    private static MongoClient mongo;

    private static void connect(){
        if (mongo == null)
            mongo = new MongoClient(host, port);
    }

    public static MongoDatabase getDB(){
        connect();
        MongoDatabase db =  mongo.getDatabase(databse);
        return db;
    }

    public static void close(){
        mongo.close();
    }

}
