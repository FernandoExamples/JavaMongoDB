import dao.CategoriaDAO;
import dao.ProblemaDAO;
import model.Categoria;
import model.Problema;

import java.util.List;

public class TestProblemas {

    public static  void main(String [] args){

        CategoriaDAO categoriaDAO = new CategoriaDAO(MongoDB.getDB());
        ProblemaDAO problemaDAO = new ProblemaDAO(MongoDB.getDB());

        //Agregamos dos categorias
        categoriaDAO.addCategory(new Categoria("Categoria1"));
        categoriaDAO.addCategory(new Categoria("Categoria2"));

        //agremos dos problemas a cada categoria
        problemaDAO.addProblem(new Problema(
              "Problema 1",
              "Este es el problema numero 1",
              categoriaDAO.getCategoria("Categoria1").getIdCategory()
        ));

        problemaDAO.addProblem(new Problema(
                "Problema 2",
                "Este es el problema numero 2",
                categoriaDAO.getCategoria("Categoria1").getIdCategory()
        ));

        problemaDAO.addProblem(new Problema(
                "Problema 3",
                "Este es el problema numero 3",
                categoriaDAO.getCategoria("Categoria2").getIdCategory()
        ));

        problemaDAO.addProblem(new Problema(
                "Problema 4",
                "Este es el problema numero 4",
                categoriaDAO.getCategoria("Categoria2").getIdCategory()
        ));

        //Obtenemos los problemas de la categoria 1
        System.out.println("\n-------------------Problemas de la categoria 1-----------------------");
        List<Problema> problemas = problemaDAO.getProblemsFromCategory(categoriaDAO.getCategoria("Categoria1").getIdCategory());
        for(Problema p : problemas){
            System.out.println("---");
            System.out.println("ID: " + p.getIdProblema());
            System.out.println("Titulo: " + p.getTitulo());
            System.out.println("Descripcion: " + p.getDescripcion());
            System.out.println("Categoria " + p.getIdCategory());
        }

        //Obtenemos los problemas de la categoria 2
        System.out.println("\n-------------------Problemas de la categoria 2-----------------------");
        problemas = problemaDAO.getProblemsFromCategory(categoriaDAO.getCategoria("Categoria2").getIdCategory());
        for(Problema p : problemas){
            System.out.println("---");
            System.out.println("ID: " + p.getIdProblema());
            System.out.println("Titulo: " + p.getTitulo());
            System.out.println("Descripcion: " + p.getDescripcion());
            System.out.println("Categoria " + p.getIdCategory());
        }

        //Obtenemos una lista de todos los problemas
        System.out.println("\n-------------------Problemas de todas las categorias-----------------------");
        problemas = problemaDAO.getAll();
        for(Problema p : problemas){
            System.out.println("---");
            System.out.println("ID: " + p.getIdProblema());
            System.out.println("Titulo: " + p.getTitulo());
            System.out.println("Descripcion: " + p.getDescripcion());
            System.out.println("Categoria " + p.getIdCategory());
        }

        //Eliminamos el problema numero 1
        System.out.println("\n------------------Eliminamos el problema 1-----------------------");

        int result = problemaDAO.deleteProblema(
          problemaDAO.getAll().get(0).getIdProblema()
        );
        System.out.println("Problemas eliminados: " + result);

        //Modificamos el problema numero 3. Cambiandolo de categoria a la Categoria1 y sus demas atributos
        System.out.println("\n------------------Modificamos el problema 3-----------------------");

        Problema problema3 = problemaDAO.getProblemsFromCategory(categoriaDAO.getCategoria("Categoria2").getIdCategory()).get(0);
        problema3.setTitulo("Titulo actualizado del problema 3");
        problema3.setDescripcion("Descripcion actualizada");
        problema3.setIdCategory(categoriaDAO.getCategoria("Categoria1").getIdCategory());

        result = problemaDAO.updateProblema(
            problema3.getIdProblema(), problema3
        );
        System.out.println("Problemas modificados: " + result);


        //Obtenemos una lista nuevamente de todos los problemas
        System.out.println("\n-------------------Problemas de todas las categorias actualizados-------------------");
        problemas = problemaDAO.getAll();
        for(Problema p : problemas){
            System.out.println("---");
            System.out.println("ID: " + p.getIdProblema());
            System.out.println("Titulo: " + p.getTitulo());
            System.out.println("Descripcion: " + p.getDescripcion());
            System.out.println("Categoria " + p.getIdCategory());
        }

        //Obtenemos los problemas de la categoria 1
        System.out.println("\n-------------------Problemas de la categoria 1-----------------------");
        problemas = problemaDAO.getProblemsFromCategory(categoriaDAO.getCategoria("Categoria1").getIdCategory());
        for(Problema p : problemas){
            System.out.println("---");
            System.out.println("ID: " + p.getIdProblema());
            System.out.println("Titulo: " + p.getTitulo());
            System.out.println("Descripcion: " + p.getDescripcion());
            System.out.println("Categoria " + p.getIdCategory());
        }

        //Obtenemos los problemas de la categoria 2
        System.out.println("\n-------------------Problemas de la categoria 2-----------------------");
        problemas = problemaDAO.getProblemsFromCategory(categoriaDAO.getCategoria("Categoria2").getIdCategory());
        for(Problema p : problemas){
            System.out.println("---");
            System.out.println("ID: " + p.getIdProblema());
            System.out.println("Titulo: " + p.getTitulo());
            System.out.println("Descripcion: " + p.getDescripcion());
            System.out.println("Categoria " + p.getIdCategory());
        }
    }
}
