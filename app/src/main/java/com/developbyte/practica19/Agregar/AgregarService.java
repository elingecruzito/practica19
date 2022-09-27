package com.developbyte.practica19.Agregar;

import com.developbyte.practica19.Abstract.AbstractService;
import java.util.HashMap;
import java.util.Map;

public class AgregarService extends AbstractService implements IAgregar.IAgregarInformationHandler {

    private IAgregar.IAgregarInformationDelegate iAgregarInformationDelegate;
    private Map<String, String> parameters = new HashMap<>();

    public void setInformationDelegate(IAgregar.IAgregarInformationDelegate iAgregarInformationDelegate) {
        this.iAgregarInformationDelegate = iAgregarInformationDelegate;
    }
}