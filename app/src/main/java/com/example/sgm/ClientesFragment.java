package com.example.sgm;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ClientesFragment extends Fragment {
    public interface OnClienteSalvoListener { void onClienteSalvo(String nome, String telefone); }
    private OnClienteSalvoListener listener;

    @Override public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        if (context instanceof OnClienteSalvoListener) listener = (OnClienteSalvoListener) context;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes, container, false);
        EditText nome = view.findViewById(R.id.edtNome);
        EditText telefone = view.findViewById(R.id.edtTelefone);
        TextView lista = view.findViewById(R.id.txtClientes);

        if (getActivity() instanceof MainActivity) {
            String dados = ((MainActivity) getActivity()).getClientes();
            lista.setText(dados.isEmpty() ? "Nenhum cliente cadastrado." : dados.replace("\n", "\n\n"));
        }

        view.findViewById(R.id.btnSalvarCliente).setOnClickListener(v -> {
            String n = nome.getText().toString().trim();
            String t = telefone.getText().toString().trim();
            if (TextUtils.isEmpty(n) || TextUtils.isEmpty(t)) {
                Toast.makeText(requireContext(), "Preencha nome e telefone.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (listener != null) listener.onClienteSalvo(n, t);
            if (getActivity() instanceof MainActivity) {
                String dados = ((MainActivity) getActivity()).getClientes();
                lista.setText(dados.replace("\n", "\n\n"));
            }
            nome.setText("");
            telefone.setText("");
        });
        return view;
    }

    @Override public void onDetach() { listener = null; super.onDetach(); }
}
