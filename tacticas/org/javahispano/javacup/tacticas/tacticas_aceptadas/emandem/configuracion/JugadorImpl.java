package org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.configuracion;

import java.awt.Color;

import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.util.Position;

class JugadorImpl implements PlayerDetail {

    String nombre;
    int numero;
    Color piel, pelo;
    double velocidad, remate, presicion;
    boolean portero;
    Position posicion;

    public JugadorImpl(String nombre, int numero, Color piel, Color pelo,
            double velocidad, double remate, double presicion, boolean portero) {
        this.nombre=nombre;
        this.numero=numero;
        this.piel=piel;
        this.pelo=pelo;
        this.velocidad=velocidad;
        this.remate=remate;
        this.presicion=presicion;
        this.portero=portero;
    }

    public String getPlayerName() {
        return nombre;
    }

    public Color getSkinColor() {
        return piel;
    }

    public Color getHairColor() {
        return pelo;
    }

    public int getNumber() {
        return numero;
    }

    public boolean isGoalKeeper() {
        return portero;
    }

    public double getSpeed() {
        return velocidad;
    }

    public double getPower() {
        return remate;
    }

    public double getPrecision() {
        return presicion;
    }

}