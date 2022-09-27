package com.developbyte.practica19.App;

import com.developbyte.practica19.Agregar.AgregarBusinessController;
import com.developbyte.practica19.Agregar.AgregarService;
import com.developbyte.practica19.Agregar.AgregarViewController;
import com.developbyte.practica19.Lista.ListaBusinessController;
import com.developbyte.practica19.Lista.ListaMasterViewController;
import com.developbyte.practica19.Lista.ListaService;
import com.developbyte.practica19.Lista.ListaViewController;

public class InjectionManager {

    private static InjectionManager instance;

    public static InjectionManager getInstance(){
        if(instance == null)
            instance = new InjectionManager();
        return instance;
    }

    public void startLista(ListaMasterViewController listaMasterViewController){
        MasterBusinessController masterBusinessController = new MasterBusinessController();

        //------------------------------------------------LISTA---------------------------------------------

        ListaBusinessController listaBusinessController = new ListaBusinessController();
        ListaViewController listaViewController = new ListaViewController();
        ListaService listaservice = new ListaService();

        listaBusinessController.setRepresentationHandler(listaViewController);
        listaBusinessController.setTransactionDelegate(masterBusinessController);
        listaBusinessController.setInformationHandler(listaservice);

        listaViewController.setTag(ListaMasterViewController.LISTA_CONTROLLER);
        listaViewController.setRepresentationDelegate(listaBusinessController);
        listaViewController.setMasterViewController(listaMasterViewController);

        listaservice.setInformationDelegate(listaBusinessController);

        listaMasterViewController.addFragment(listaViewController);
        masterBusinessController.setListaTransactionHandler(listaBusinessController);
        //------------------------------------------------AGREGAR---------------------------------------------

        AgregarBusinessController agregarBusinessController = new AgregarBusinessController();
        AgregarViewController agregarViewController = new AgregarViewController();
        AgregarService agregarservice = new AgregarService();

        agregarBusinessController.setRepresentationHandler(agregarViewController);
        agregarBusinessController.setTransactionDelegate(masterBusinessController);
        agregarBusinessController.setInformationHandler(agregarservice);

        agregarViewController.setTag(ListaMasterViewController.AGREGAR_CONTROLLER);
        agregarViewController.setRepresentationDelegate(agregarBusinessController);
        agregarViewController.setMasterViewController(listaMasterViewController);

        agregarservice.setInformationDelegate(agregarBusinessController);

        listaMasterViewController.addFragment(agregarViewController);
        masterBusinessController.setAgregarTransactionHandler(agregarBusinessController);


        masterBusinessController.listaInit();
    }
}