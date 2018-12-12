package com.miquel.egea.wherevent;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;


public class NewQuedadaActivity extends AppCompatActivity {
    EditText tituloedit;
    EditText ubicacionedit;
    TextView fechaedit;
    TextView horaedit;
    EditText descripcionedit;
    Integer tipoevento;
    private DatePickerDialog.OnDateSetListener fechaeditListener;
    private TimePickerDialog.OnTimeSetListener horaeditListener;
    private static int iconos[] = { R.drawable.bbq, R.drawable.bolos, R.drawable.camping, R.drawable.cena, R.drawable.cine, R.drawable.copa,
            R.drawable.estudio,R.drawable.globos, R.drawable.gym, R.drawable.pastel, R.drawable.playa, R.drawable.regalo,
            R.drawable.viaje};
    private boolean textovacio;
    private String titulo_edit;
    private String ubicacion_edit;
    private String fecha_edit;
    private String hora_edit;
    private String descripcion_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quedada);
        tituloedit = this.findViewById(R.id.tituloedit);
        ubicacionedit = this.findViewById(R.id.ubicacionedit);
        fechaedit = this.findViewById(R.id.fechaedit);
        horaedit = this.findViewById(R.id.horaedit);
        descripcionedit = this.findViewById(R.id.descripcionedit);

        fechaedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NewQuedadaActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_Alert,
                        fechaeditListener,
                        year, month, day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        fechaeditListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                fechaedit.setText(date);
            }
        };

        horaedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hora = cal.get(Calendar.HOUR);
                int minutos = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        NewQuedadaActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_Alert,
                        horaeditListener,
                        hora, minutos, true);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        horaeditListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker datePicker, int hora, int minutos) {
                String time = hora + ":" + minutos + " h.";
                horaedit.setText(time);
            }
        };

        RecyclerView mylist = findViewById(R.id.recyclerView2);
        //creamos el layout HORIZONTAL
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mylist.setLayoutManager(layoutManager);
        mylist.setAdapter(new MyAdapter());
    }

    @Override
    protected void onStart() {
        textovacio=false;
        titulo_edit="";
        fecha_edit="";
        hora_edit="";
        descripcion_edit="";
        ubicacion_edit="";
        tipoevento=-1;

        super.onStart();
    }

    //recycler view iconos
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.image_view = itemView.findViewById(R.id.image_view);
            //añadimos listener a los iconos para saber cual clicamos
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickIconSelect(getAdapterPosition());
                }
            });
        }
    }

    private void onClickIconSelect(int position) {
        Toast.makeText(this, "Icono seleccionado", Toast.LENGTH_SHORT).show();
        tipoevento=position;
        //nos quedamos con la posicion del icono en la lista
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder>{
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater()
                    .inflate(R.layout.icono_view,parent,false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.image_view.setImageResource(iconos[position]);
        }

        @Override
        public int getItemCount() {
            return iconos.length;
        }
    }

    //fin recycler view iconos

    public void onClickCrear(View view) {
        //cogemos los campos introducidos por el usuario
        titulo_edit = tituloedit.getText().toString();
        ubicacion_edit = ubicacionedit.getText().toString();
        fecha_edit = fechaedit.getText().toString();
        hora_edit = horaedit.getText().toString();
        descripcion_edit = descripcionedit.getText().toString();

        //comprobamos si el usuario ha escrito en todos los campos
        ComprobarDatosVacios();

        //si hay campos vacios creamos un dialogo para que pueda revisar
        if(textovacio) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("No has rellenado todos los campos, deseas crear el evento igualmente?")
                    .setTitle("Campos incompletos!")
                    .setIcon(R.drawable.cross)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EnviarDatos();
                        }})
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(NewQuedadaActivity.this, "Evento no creado", Toast.LENGTH_SHORT).show();
                    }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        //si no los hay enviamos los datos de vuelta
        else{
            EnviarDatos();
        }


    }
    public void EnviarDatos(){
        Intent data = new Intent();
        data.putExtra("titulo",titulo_edit);
        data.putExtra("ubicacion",ubicacion_edit);
        data.putExtra("fecha",fecha_edit);
        data.putExtra("hora",hora_edit);
        data.putExtra("descripción",descripcion_edit);
        data.putExtra("tipoevento",tipoevento);
        setResult(RESULT_OK,data);
        finish();
    }
    public void ComprobarDatosVacios(){
        if(titulo_edit.equals("")){ textovacio = true; }
        if(ubicacion_edit.equals("")){ textovacio = true; }
        if(fecha_edit.equals("")){ textovacio = true; }
        if(hora_edit.equals("")){ textovacio = true; }
        if(descripcion_edit.equals("")){ textovacio = true; }
        if(tipoevento==-1){ textovacio = true; }
    }


}
