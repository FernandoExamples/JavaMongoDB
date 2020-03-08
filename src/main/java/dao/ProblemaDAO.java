package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import model.Problema;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

public class ProblemaDAO {

    private MongoDatabase db;
    private final String collectionName = "problemas";

    public ProblemaDAO(MongoDatabase db) {
        this.db = db;
    }

    /**
     * Metodo que inserta un problema en la coleccion problemas
     * @param problema
     */
    public void addProblem(Problema problema){
        db.getCollection(collectionName).insertOne(problema.toDocument());
    }

    /**
     * Problema que obtiene todos los documentos de una categoria
     * @param categoryId
     */
    public List<Problema> getProblemsFromCategory(ObjectId categoryId){
        List<Problema> problemas = new ArrayList<>();
        Bson filter = eq("IdCategory", categoryId);

        FindIterable<Document> result = db.getCollection(collectionName).find(filter);

        result.forEach((Consumer<Document>) document -> {
            problemas.add(Problema.fromDocument(document));
        });

        return problemas;
    }

    /**
     * Obtiene una lista de todos los problemas
     * @return
     */
    public List<Problema> getAll(){
        List<Problema> problemas = new ArrayList<>();
        FindIterable<Document> result = db.getCollection(collectionName).find();

        for(Document d : result)
            problemas.add(Problema.fromDocument(d));

        return problemas;
    }

    /**
     * Metodo que elimina una problema a partir de su ObjectID
     * @param idProblema
     * @return
     */
    public int deleteProblema(ObjectId idProblema) {
        Bson filter = eq("_id", idProblema);

        DeleteResult result = db.getCollection(collectionName).deleteOne(filter);
        return (int) result.getDeletedCount();
    }

    /**
     * Actualiza un problema a partir de su ObjectID
     * @param idProblema
     * @param problemUpdate
     * @return
     */
    public int updateProblema(ObjectId idProblema, Problema problemUpdate) {

        Bson filter = eq("_id", idProblema);

        Document docUpdate = new Document("$set", problemUpdate.toDocument());

        UpdateResult result = db.getCollection(collectionName).updateOne(filter, docUpdate);
        return (int) result.getModifiedCount();
    }
}
