import com.mongodb.client.MongoDatabase;
import dao.CategoriaDAO;
import model.Categoria;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.List;

public class TestCategorias {

    public static void main(String[] args) {
        MongoDatabase database = MongoDB.getDB();

        CategoriaDAO categoriaDAO = new CategoriaDAO(database);

        //Guardamos dos categorias
        categoriaDAO.addCategory(new Categoria("Categoria1"));
        categoriaDAO.addCategory(new Categoria("Categoria2"));

        //Obtenemos una categoria guardada
        System.out.println("----------------Una categoria Guardada--------------------");
        Categoria cat = categoriaDAO.getCategoria("Categoria1");
        System.out.println("Id: " + cat.getIdCategory());
        System.out.println("Nombre: " + cat.getNombre());

        //obtenemos una lista de todas las categorias guardadas
        System.out.println("\n-------------Listado de todas las categorias--------------------");

        List<Categoria> listCats = categoriaDAO.getAll();
        listCats.forEach(categoria -> {
            System.out.println("---");
            System.out.println("ID: " + categoria.getIdCategory());
            System.out.println("Nombre: " + categoria.getNombre());
        });

        //Eliminamos la categoria 1
        System.out.println("\n----Se elimina la categoria 1-------------------");
        int result = categoriaDAO.deleteCategoria("Categoria1");
        System.out.println("Categorias eliminadas: " + result);

        //Editamos la categoria 2
        System.out.println("\n----Se actualiza la categoria 2-------------------");
        Categoria update = new Categoria("model.Categoria Actualizada");

        result = categoriaDAO.updateCategoria("Categoria2", update);

        System.out.println("Categorias actualizadas: " + result);


        //obtenemos una lista de todas las categorias guardadas
        System.out.println("\n-------Nuevamente Listado de todas las categorias--------------------");

        listCats = categoriaDAO.getAll();
        listCats.forEach(categoria -> {
            System.out.println("---");
            System.out.println("ID: " + categoria.getIdCategory());
            System.out.println("Nombre: " + categoria.getNombre());
        });

        System.out.println("\n-------Se muestran las categorias en JSON--------------------");
        listCats = categoriaDAO.getAll();
        listCats.forEach(categoria -> {

            JsonWriterSettings settings = JsonWriterSettings.builder()
                    .indent(true)
                    .outputMode(JsonMode.EXTENDED)
                    .build();

            Document doc = categoria.toDocument();
            String json = doc.toJson(settings);
            System.out.println(json);
        });

    }
}
