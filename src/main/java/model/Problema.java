package model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Problema {

    private ObjectId idProblema;
    private String titulo;
    private String descripcion;
    private ObjectId IdCategory;

    public Problema(String titulo, String descripcion, ObjectId IdCategory) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.IdCategory = IdCategory;
    }

    public Problema(ObjectId idProblema, String titulo, String descripcion, ObjectId idCategory) {
        this.idProblema = idProblema;
        this.titulo = titulo;
        this.descripcion = descripcion;
        IdCategory = idCategory;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ObjectId getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(ObjectId idCategory) {
        this.IdCategory = idCategory;
    }

    public ObjectId getIdProblema() {
        return idProblema;
    }

    public Document toDocument(){
        Document doc = new Document();
        doc.put("titulo", titulo);
        doc.put("descripcion", descripcion);
        doc.put("IdCategory", IdCategory);
        return doc;
    }

    public static Problema fromDocument(Document document){
        String titulo = document.getString("titulo");
        String descripcion = document.getString("descripcion");
        ObjectId catId = document.getObjectId("IdCategory");
        ObjectId probId = document.getObjectId("_id");

        Problema p = new Problema(probId, titulo, descripcion, catId);
        return p;
    }
}
