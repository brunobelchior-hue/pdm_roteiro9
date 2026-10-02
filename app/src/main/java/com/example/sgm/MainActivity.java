package com.example.sgm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity
        implements InicioFragment.OnInicioActionListener,
                   ClientesFragment.OnClienteSalvoListener,
                   VeiculosFragment.OnVeiculoSalvoListener {

    private static final String PREFS = "SGM_DADOS";
    private static final String KEY_CLIENTES = "clientes";
    private static final String KEY_VEICULOS = "veiculos";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        Button btnInicio = findViewById(R.id.btnInicio);
        Button btnClientes = findViewById(R.id.btnClientes);
        Button btnVeiculos = findViewById(R.id.btnVeiculos);
        Button btnSobre = findViewById(R.id.btnSobre);

        btnInicio.setOnClickListener(v -> abrir(new InicioFragment(), false));
        btnClientes.setOnClickListener(v -> abrir(new ClientesFragment(), false));
        btnVeiculos.setOnClickListener(v -> abrir(new VeiculosFragment(), false));
        btnSobre.setOnClickListener(v -> abrir(new SobreFragment(), false));

        if (savedInstanceState == null) {
            abrir(new InicioFragment(), false);
        }
    }

    private void abrir(Fragment fragment, boolean voltarParaInicio) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment);
        if (voltarParaInicio) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void abrirClientes() {
        abrir(new ClientesFragment(), true);
    }

    @Override
    public void abrirVeiculos() {
        abrir(new VeiculosFragment(), true);
    }

    @Override
    public void onClienteSalvo(String nome, String telefone) {
        String atual = preferences.getString(KEY_CLIENTES, "");
        String novo = "Cliente: " + nome + " | Telefone: " + telefone;
        String resultado = atual.isEmpty() ? novo : atual + "\n" + novo;
        preferences.edit().putString(KEY_CLIENTES, resultado).apply();
    }

    @Override
    public void onVeiculoSalvo(String marca, String modelo, String valor) {
        String atual = preferences.getString(KEY_VEICULOS, "");
        String novo = "Veículo: " + marca + " " + modelo + " | Valor: R$ " + valor;
        String resultado = atual.isEmpty() ? novo : atual + "\n" + novo;
        preferences.edit().putString(KEY_VEICULOS, resultado).apply();
    }

    public String getClientes() {
        return preferences.getString(KEY_CLIENTES, "");
    }

    public String getVeiculos() {
        return preferences.getString(KEY_VEICULOS, "");
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
