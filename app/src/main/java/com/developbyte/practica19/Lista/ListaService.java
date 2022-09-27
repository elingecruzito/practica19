package com.developbyte.practica19.Lista;

import com.developbyte.practica19.Abstract.AbstractService;
import java.util.HashMap;
import java.util.Map;

public class ListaService extends AbstractService implements ILista.IListaInformationHandler {

    private ILista.IListaInformationDelegate iListaInformationDelegate;
    private Map<String, String> parameters = new HashMap<>();

    public void setInformationDelegate(ILista.IListaInformationDelegate iListaInformationDelegate) {
        this.iListaInformationDelegate = iListaInformationDelegate;
    }
}