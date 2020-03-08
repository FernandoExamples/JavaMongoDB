package model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Categoria {

    private String nombre;
    private ObjectId idCategory;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria(ObjectId idCategory, String nombre) {
        this.nombre = nombre;
        this.idCategory = idCategory;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ObjectId getIdCategory() {
        return idCategory;
    }

    public Document toDocument(){
        Document document = new Document();
        document.append("nombre", nombre);
        return document;
    }

    public static Categoria fromDocument(Document document){
        String name = document.getString("nombre");
        ObjectId id = document.getObjectId("_id");
        Categoria cat = new Categoria(id, name);
        return cat;
    }
}
