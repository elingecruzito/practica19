package com.developbyte.practica19.Lista;

import com.developbyte.practica19.Model.LibroModel;

import java.util.List;

public interface ILista {
    //Comunica de MasterBussinesController a BussinesController
    interface IListaTransactionHandler{
        void startLista();
        void agregarLibro(LibroModel libroModel);
        void editarLibro(LibroModel libroModel);
    }
    
    //Comunica de BussinesController a MasterBussinesController
    interface IListaTransactionDelegate{
        void initAgregar(LibroModel libroModel);
        
    }

    //Comunica de BusinessController a ViewController
    interface IListaRepresentationHandler{
        boolean showLista();

        void actualizarLista(List<LibroModel> modelList);
    }

    //Comunica de Service a BusinessComtroller
    interface IListaInformationDelegate{
    }

    //Comunica de BusinessController a Service
    interface IListaInformationHandler{
    }

    //Comunica de ViewController a Businnes
    interface IListaRepresentationDelegate{
        void showAgregar();

        void deleteLibro(int position);
        void editLibro(int position);
        void filtrarLibros(String filtro);
    }
}