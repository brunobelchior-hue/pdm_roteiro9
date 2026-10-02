package com.example.sgm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InicioFragment extends Fragment {
    public interface OnInicioActionListener {
        void abrirClientes();
        void abrirVeiculos();
    }
    private OnInicioActionListener listener;

    @Override public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        if (context instanceof OnInicioActionListener) listener = (OnInicioActionListener) context;
    }
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        view.findViewById(R.id.btnInicioClientes).setOnClickListener(v -> { if (listener != null) listener.abrirClientes(); });
        view.findViewById(R.id.btnInicioVeiculos).setOnClickListener(v -> { if (listener != null) listener.abrirVeiculos(); });
        return view;
    }
    @Override public void onDetach() { listener = null; super.onDetach(); }
}
