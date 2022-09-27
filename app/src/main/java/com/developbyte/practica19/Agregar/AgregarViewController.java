package com.developbyte.practica19.Agregar;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.FileProvider;

import com.developbyte.practica19.Abstract.AbstractViewController;
import com.developbyte.practica19.Model.LibroModel;
import com.developbyte.practica19.R;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class AgregarViewController extends AbstractViewController implements IAgregar.IAgregarRepresentationHandler {

    private IAgregar.IAgregarRepresentationDelegate representationDelegate;
    private AppCompatImageView imgLibro;
    private AppCompatEditText txtTitulo,txtAutor,txtEditorial,txtAnio, txtCatego, txtPrecio;
    private AppCompatButton btnAgregar;

    private LibroModel model;
    private boolean isNuevo;
    

    public void setRepresentationDelegate(IAgregar.IAgregarRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_agregar, container, false);


        imgLibro = view.findViewById(R.id.imgLibro);
        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtAutor = view.findViewById(R.id.txtAutor);
        txtEditorial = view.findViewById(R.id.txtEditorial);
        txtAnio = view.findViewById(R.id.txtAnio);
        txtCatego = view.findViewById(R.id.txtCatego);
        txtPrecio = view.findViewById(R.id.txtPrecio);

        btnAgregar = view.findViewById(R.id.btnAgregar);
        imgLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    abrirCamara();
                } catch (IOException e) {
                    Log.e(getClass().getName(), e.getMessage());
                }
            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setTitulo(txtTitulo.getText().toString());
                model.setAutor(txtAutor.getText().toString());
                model.setEditorial(txtEditorial.getText().toString());
                model.setAnio(Integer.parseInt(txtAnio.getText().toString()));
                model.setCategoria(txtCatego.getText().toString());
                model.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));

                if(isNuevo) {
                    representationDelegate.agregarLibro(model);
                }else{
                    representationDelegate.editarLibro(model);
                }
                onBackPressed();
            }
        });

        return view;
    }

    @Override
    public void resume() {
        txtTitulo.setText(model.getTitulo());
        txtEditorial.setText(model.getEditorial());
        txtAutor.setText(model.getAutor());
        txtAnio.setText(String.valueOf(model.getAnio()));
        if (!isNuevo){
            imgLibro.setImageBitmap(BitmapFactory.decodeFile(model.getImage()));
            btnAgregar.setText("Modificar");
        }else{
            btnAgregar.setText("Agregar");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void restoreData(Bundle savedInstanceState) {

    }


    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showAgregar(LibroModel model) {

        if(model == null){
            isNuevo = true;
            this.model = new LibroModel();
            this.model.setTitulo("Programacion Android");
            this.model.setAutor("Andres Garcia");
            this.model.setEditorial("Banregio");
            this.model.setAnio(2022);
        }else{
            isNuevo = false;
            this.model = model;
        }

        masterViewController.presetFragment(this.tag);
    }

    private File crearImagen() throws IOException {

        String nombreImagen = "foto_";
        File directorio = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);
        this.model.setImage(imagen.getAbsolutePath());
        return imagen;

    }

    private void abrirCamara() throws IOException {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;
        try{
            imagenArchivo = crearImagen();
        }catch (IOException ex){
            Log.e("Error", ex.toString());
        }
        if(imagenArchivo != null) {
            Uri fotoUri = FileProvider.getUriForFile(getContext(), "com.developbyte.practica19.Agregar", imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
        }
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 || resultCode == RESULT_OK ){
            imgLibro.setImageBitmap(BitmapFactory.decodeFile(model.getImage()));
        }
    }
}