package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import model.Categoria;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.*;

public class CategoriaDAO {

    private MongoDatabase db;
    private final String collectionName = "categorias";

    public CategoriaDAO(MongoDatabase db) {
        this.db = db;
    }

    /**
     * Metodo que agrega una categoria. Evita que se agreguen categorias con el mismo nombre
     *
     * @param categoria
     * @return
     */
    public boolean addCategory(Categoria categoria) {

        if (getCategoria(categoria.getNombre()) == null) {
            db.getCollection(collectionName).insertOne(categoria.toDocument());
            return true;
        }

        return false;
    }

    /**
     * Metodo que obtiene una categoria a partir de su nombre
     * @param name
     * @return
     */
    public Categoria getCategoria(String name) {

        Document filter = new Document("nombre", name);

        FindIterable<Document> iterable = db.getCollection(collectionName).find(filter);

        if (iterable.cursor().hasNext()) {
            Document doc = iterable.cursor().next();
            return Categoria.fromDocument(doc);
        }

        return null;
    }

    /**
     * Metodo que obtiene una categoria a partir de su ObjectID
     * @param idCategory
     * @return
     */
    public Categoria getCategoria(ObjectId idCategory) {

        Document filter = new Document("_id", idCategory);

        FindIterable<Document> iterable = db.getCollection(collectionName).find(filter);

        if (iterable.cursor().hasNext()) {
            Document doc = iterable.cursor().next();
            return Categoria.fromDocument(doc);
        }

        return null;
    }

    /**
     * Obtiene una lista de todas las categorias
     * @return
     */
    public List<Categoria> getAll(){
        List<Categoria> categorias = new ArrayList<>();

        FindIterable<Document> results = db.getCollection(collectionName).find();

        results.forEach((Consumer<Document>) document -> categorias.add(Categoria.fromDocument(document)));

        return categorias;
    }

    /**
     * Metodo que elimina una categoria a partir de su nombre
     *
     * @param name
     * @return
     */
    public int deleteCategoria(String name) {

        Document filter = new Document("nombre", name);

        DeleteResult result = db.getCollection(collectionName).deleteOne(filter);
        return (int) result.getDeletedCount();
    }

    /**
     * Actualiza la categoria que tenga cierto nombre
     * @param name
     * @param catUpdated
     * @return
     */
    public int updateCategoria(String name, Categoria catUpdated) {

        // esta vez usamos los imports estaticos de Filters
        Bson filter = eq("nombre", name);

        Document updateObject = new Document("$set", catUpdated.toDocument());

        UpdateResult result = db.getCollection(collectionName).updateOne(filter, updateObject);
        return (int) result.getModifiedCount();
    }
}
