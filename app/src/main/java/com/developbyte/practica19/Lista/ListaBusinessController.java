package com.developbyte.practica19.Lista;

import android.content.Intent;
import android.util.Log;

import com.developbyte.practica19.Abstract.AbstractBusinessController;
import com.developbyte.practica19.Adapters.LibrosAdapter;
import com.developbyte.practica19.Model.LibroModel;

import java.util.ArrayList;
import java.util.List;

public class ListaBusinessController extends AbstractBusinessController
                        implements ILista.IListaTransactionHandler, //MasterBussinesController a BussinesController 
                        ILista.IListaRepresentationDelegate, //ViewController a Businnes 
                        ILista.IListaInformationDelegate{ //Service a BusinessComtroller

    private ILista.IListaRepresentationHandler representationHandler;
    private ILista.IListaInformationHandler informationHandler;
    private ILista.IListaTransactionDelegate transactionDelegate;

    private List<LibroModel> modelList;
    private List<LibroModel> modelListFiltro;

    public void setRepresentationHandler(ILista.IListaRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(ILista.IListaInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionDelegate(ILista.IListaTransactionDelegate transactionDelegate){
        this.transactionDelegate = transactionDelegate;
    }

    @Override
    public void startLista() {
        representationHandler.showLista();
    }

    @Override
    public void agregarLibro(LibroModel libroModel) {
        if(modelList == null){
            modelList = new ArrayList<>();
            llenarLista();
        }
        libroModel.setId(modelList.size() + 1);
        modelList.add(libroModel);
        representationHandler.actualizarLista(modelList);
    }

    @Override
    public void editarLibro(LibroModel libroModel) {
        modelList.set(libroModel.getId() - 1, libroModel);
        representationHandler.actualizarLista(modelList);
    }

    @Override
    public void showAgregar() {
        transactionDelegate.initAgregar(null);
    }

    @Override
    public void deleteLibro(int position) {
        modelList.remove(position);
        representationHandler.actualizarLista(modelList);
    }

    @Override
    public void editLibro(int position) {
        transactionDelegate.initAgregar(modelList.get(position));
    }

    /* titulo, autor, editorial, año */
    @Override
    public void filtrarLibros(String filtro) {
        if(modelListFiltro == null){
            modelListFiltro = new ArrayList<>();
        }else if(modelListFiltro.size() > 0){
            modelListFiltro.clear();
        }

        for(int i  = 0; i < modelList.size(); i++){
            if(
                modelList.get(i).getTitulo().toLowerCase().contains(filtro) ||
                modelList.get(i).getAutor().toLowerCase().contains(filtro) ||
                modelList.get(i).getEditorial().toLowerCase().contains(filtro)
            ){
                modelListFiltro.add(modelList.get(i));
            }else if(isNumber(filtro)){
                if(modelList.get(i).getAnio() == Integer.parseInt(filtro)){
                    modelListFiltro.add(modelList.get(i));
                }
            }
        }

        representationHandler.actualizarLista(modelListFiltro);
    }

    private boolean isNumber(String number){
        try{
            Integer.parseInt(number);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void llenarLista(){
        if(modelList == null){
            modelList = new ArrayList<>();
        }
        String [] titulos = { "Programacion Android", "Programacion web", "Programacion spring", "Git", "Programacion IOS" };
        String [] autores = { "Andres Garcia", "Juan Perez", "Jose Hernandez", "Laura Martines", "Lisa Codie" };
        String [] editoriales = {"Planeta", "Gandi", "Planeta", "Gandi", "Gandi"};
        int [] anios = {2018, 2019, 2020, 2021, 2022};

        for(int i = 0; i < titulos.length; i++){
            modelList.add(new LibroModel(
                    i+1,
                    titulos[i],
                    autores[i],
                    editoriales[i],
                    "",
                    anios[i]
            ));
        }
    }


}