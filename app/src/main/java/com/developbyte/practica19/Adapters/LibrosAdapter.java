package com.developbyte.practica19.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.practica19.Lista.ILista;
import com.developbyte.practica19.Model.LibroModel;
import com.developbyte.practica19.R;

import java.util.List;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.ViewHolder> {

    private Context context;
    private ILista.IListaRepresentationDelegate representationDelegate;
    private List<LibroModel> modelList;

    public LibrosAdapter(Context context, ILista.IListaRepresentationDelegate representationDelegate) {
        this.context = context;
        this.representationDelegate = representationDelegate;
    }

    public void setModelList(List<LibroModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LibrosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_model_list, parent, false);
        return new LibrosAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrosAdapter.ViewHolder holder, int position) {

        holder.imgPortada.setImageBitmap(BitmapFactory.decodeFile(modelList.get(position).getImage()));
        holder.txtTitulo.setText(modelList.get(position).getTitulo());
        holder.txtEditorial.setText(modelList.get(position).getEditorial());
        holder.txtAutor.setText(modelList.get(position).getAutor());
        holder.txtAnio.setText(String.valueOf(modelList.get(position).getAnio()));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateAlert(position).show();
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                representationDelegate.editLibro(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private AppCompatImageView imgPortada;
        private AppCompatTextView txtTitulo, txtEditorial, txtAutor, txtAnio;
        private AppCompatButton btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPortada = itemView.findViewById(R.id.imgPortada);

            txtTitulo = itemView.findViewById(R.id.txtTitle);
            txtEditorial = itemView.findViewById(R.id.txtEditorial);
            txtAutor = itemView.findViewById(R.id.txtAutor);
            txtAnio = itemView.findViewById(R.id.txtAnio);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public AlertDialog generateAlert(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Estas seguro de eliminar el libro '" + modelList.get(position).getTitulo() + "'?")
                .setTitle("Eliminar!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        representationDelegate.deleteLibro(position);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
