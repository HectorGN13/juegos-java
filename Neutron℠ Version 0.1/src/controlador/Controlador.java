package controlador;

import modelo.Modelo;

import java.util.ArrayList;

public class Controlador {
    private Modelo m;

    public Controlador(Modelo m) {
        this.m = m;
    }

    public Modelo getM() {
        return m;
    }

    public void peticion(ArrayList<Integer> position) {
        m.jugar(position.get(0), position.get(1));
    }
}
