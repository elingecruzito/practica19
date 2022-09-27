package com.developbyte.practica19.Agregar;

import com.developbyte.practica19.Model.LibroModel;

public interface IAgregar {
    //Comunica de MasterBussinesController a BussinesController
    interface IAgregarTransactionHandler{
        void startAgregar(LibroModel model);
    }
    
    //Comunica de BussinesController a MasterBussinesController
    interface IAgregarTransactionDelegate{
        void agregarLibro(LibroModel libroModel);
        void editarLibro(LibroModel libroModel);
    }

    //Comunica de BusinessController a ViewController
    interface IAgregarRepresentationHandler{
        void showAgregar(LibroModel model);
    }

    //Comunica de Service a BusinessComtroller
    interface IAgregarInformationDelegate{
    }

    //Comunica de BusinessController a Service
    interface IAgregarInformationHandler{
    }

    //Comunica de ViewController a Businnes
    interface IAgregarRepresentationDelegate{
        void agregarLibro(LibroModel libroModel);
        void editarLibro(LibroModel libroModel);
    }
}