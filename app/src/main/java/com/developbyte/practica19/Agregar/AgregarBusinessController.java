package com.developbyte.practica19.Agregar;

import android.util.Log;

import com.developbyte.practica19.Abstract.AbstractBusinessController;
import com.developbyte.practica19.Model.LibroModel;

public class AgregarBusinessController extends AbstractBusinessController
                        implements IAgregar.IAgregarTransactionHandler, //MasterBussinesController a BussinesController 
                        IAgregar.IAgregarRepresentationDelegate, //ViewController a Businnes 
                        IAgregar.IAgregarInformationDelegate{ //Service a BusinessComtroller

    private IAgregar.IAgregarRepresentationHandler representationHandler;
    private IAgregar.IAgregarInformationHandler informationHandler;
    private IAgregar.IAgregarTransactionDelegate transactionDelegate;

    public void setRepresentationHandler(IAgregar.IAgregarRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(IAgregar.IAgregarInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionDelegate(IAgregar.IAgregarTransactionDelegate transactionDelegate){
        this.transactionDelegate = transactionDelegate;
    }

    @Override
    public void startAgregar(LibroModel model) {
        representationHandler.showAgregar(model);
    }


    @Override
    public void agregarLibro(LibroModel libroModel) {
        transactionDelegate.agregarLibro(libroModel);
    }

    @Override
    public void editarLibro(LibroModel libroModel) {
        transactionDelegate.editarLibro(libroModel);
    }
}