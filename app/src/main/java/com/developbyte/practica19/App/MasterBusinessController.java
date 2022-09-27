package com.developbyte.practica19.App;

import com.developbyte.practica19.Agregar.IAgregar;
import com.developbyte.practica19.Lista.ILista;
import com.developbyte.practica19.Model.LibroModel;

public class MasterBusinessController implements ILista.IListaTransactionDelegate,
        IAgregar.IAgregarTransactionDelegate{

    private ILista.IListaTransactionHandler listaTransactionHandler;
    private IAgregar.IAgregarTransactionHandler agregarTransactionHandler;


    public void setListaTransactionHandler(ILista.IListaTransactionHandler listaTransactionHandler) {
        this.listaTransactionHandler = listaTransactionHandler;
    }
    public void setAgregarTransactionHandler(IAgregar.IAgregarTransactionHandler agregarTransactionHandler) {
        this.agregarTransactionHandler = agregarTransactionHandler;
    }


    public void listaInit(){
        listaTransactionHandler.startLista();
    }
    @Override
    public void initAgregar(LibroModel libroModel) {
        agregarTransactionHandler.startAgregar(libroModel);
    }


    @Override
    public void agregarLibro(LibroModel libroModel) {
        listaTransactionHandler.agregarLibro(libroModel);
    }

    @Override
    public void editarLibro(LibroModel libroModel) {
        listaTransactionHandler.editarLibro(libroModel);
    }
}