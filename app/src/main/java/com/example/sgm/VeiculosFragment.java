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

public class VeiculosFragment extends Fragment {
    public interface OnVeiculoSalvoListener { void onVeiculoSalvo(String marca, String modelo, String valor); }
    private OnVeiculoSalvoListener listener;

    @Override public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        if (context instanceof OnVeiculoSalvoListener) listener = (OnVeiculoSalvoListener) context;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_veiculos, container, false);
        EditText marca = view.findViewById(R.id.edtMarca);
        EditText modelo = view.findViewById(R.id.edtModelo);
        EditText valor = view.findViewById(R.id.edtValor);
        TextView lista = view.findViewById(R.id.txtVeiculos);

        if (getActivity() instanceof MainActivity) {
            String dados = ((MainActivity) getActivity()).getVeiculos();
            lista.setText(dados.isEmpty() ? "Nenhum veículo cadastrado." : dados.replace("\n", "\n\n"));
        }

        view.findViewById(R.id.btnSalvarVeiculo).setOnClickListener(v -> {
            String m = marca.getText().toString().trim();
            String mo = modelo.getText().toString().trim();
            String va = valor.getText().toString().trim();
            if (TextUtils.isEmpty(m) || TextUtils.isEmpty(mo) || TextUtils.isEmpty(va)) {
                Toast.makeText(requireContext(), "Preencha marca, modelo e valor.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (listener != null) listener.onVeiculoSalvo(m, mo, va);
            if (getActivity() instanceof MainActivity) {
                String dados = ((MainActivity) getActivity()).getVeiculos();
                lista.setText(dados.replace("\n", "\n\n"));
            }
            marca.setText("");
            modelo.setText("");
            valor.setText("");
        });
        return view;
    }

    @Override public void onDetach() { listener = null; super.onDetach(); }
}
