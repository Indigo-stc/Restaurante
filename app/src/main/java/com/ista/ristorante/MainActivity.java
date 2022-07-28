package com.ista.ristorante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ista.ristorante.models.DBHelper;
import com.ista.ristorante.models.Plato;

public class MainActivity extends AppCompatActivity {

    Button btn_Crear, btn_Elim, btn_Modif;
    EditText et_nombre, et_costo, et_pvp, et_ingred, et_tipo;
    ListView lista;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(MainActivity.this);
        //Initialize variables
        btn_Crear = findViewById(R.id.btn_Crear);
        btn_Elim = findViewById(R.id.btn_Eliminar);
        btn_Modif = findViewById(R.id.btn_modificar);
        lista = findViewById(R.id.lista);
        et_costo = findViewById(R.id.et_costo);
        et_ingred = findViewById(R.id.et_ingred);
        et_nombre = findViewById(R.id.et_nombre);
        et_pvp = findViewById(R.id.et_pvp);
        et_tipo = findViewById(R.id.spinner);
        //Listeners
        btn_Crear.setOnClickListener(l -> insertar());

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plato p = (Plato) parent.getItemAtPosition(position);
                et_nombre.setText(p.getNombre());
                et_tipo.setText(p.getTipo());
                et_ingred.setText(p.getIngredientes());
                et_costo.setText(p.getCosto());
                et_pvp.setText(p.getPvp());
                btn_Modif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        p.setNombre(et_nombre.getText().toString());
                        p.setIngredientes(et_ingred.getText().toString());
                        p.setTipo(et_tipo.getText().toString());
                        p.setPvp(et_pvp.getText().toString());
                        p.setCosto(et_costo.getText().toString());
                        modificar(p);
                        showAll();
                    }
                });
                btn_Elim.setOnClickListener(l -> eliminar(p));
            }
        });
        showAll();
    }

    public void insertar() {
        try {
            Plato plato = new Plato(et_nombre.getText().toString(),
                    et_tipo.getText().toString(),
                    et_ingred.getText().toString(),
                    et_costo.getText().toString(),
                    et_pvp.getText().toString());

            boolean result = dbHelper.insert(plato);
            if (result) {
                Toast.makeText(MainActivity.this, "Exito", Toast.LENGTH_SHORT).show();
                showAll();
                limpiar();
            } else {
                Toast.makeText(MainActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(MainActivity.this, "Datos erroenos", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar(Plato p) {
        if (!dbHelper.delete(p)) {
            Toast.makeText(MainActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
            showAll();
            limpiar();
        } else {
            Toast.makeText(MainActivity.this, "Fallido", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificar(Plato p) {
        if (dbHelper.update(p) > 0) {
            Toast.makeText(MainActivity.this, "Actualizado", Toast.LENGTH_SHORT).show();
            showAll();
            limpiar();
        } else {
            Toast.makeText(MainActivity.this, "Fallido", Toast.LENGTH_SHORT).show();
        }
    }

    public void showAll() {
        ArrayAdapter everyone = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dbHelper.listAll());
        lista.setAdapter(everyone);
    }

    public void limpiar() {
        et_nombre.setText("");
        et_tipo.setText("");
        et_ingred.setText("");
        et_costo.setText("");
        et_pvp.setText("");
    }

}