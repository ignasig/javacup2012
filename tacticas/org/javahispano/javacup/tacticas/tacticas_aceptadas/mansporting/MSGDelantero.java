/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

import java.awt.Color;
import java.util.List;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.command.CommandHitBall;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Punto;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Punto3D;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.TrigonometriaUtils;

/**
 *
 * @author MaN
 */
public class MSGDelantero extends MSGMedioCentro {

    /**
     * Fuerza con la que remata el delantero.
     */
    public static final double FUERZA_REMATE_DELANTERO = 1D;

    public MSGDelantero(int index, Color colorPelo, Color colorPiel, String nombre, int numero, MSGEstadisticas estadisticas) {
        super(index, colorPelo, colorPiel, nombre, numero, estadisticas);
    }

    @Override
    public List<Command> ejecuta() {
        List<Command> comando = new MSGComandoList();
        if (getPuedeRematar()) {
            if (puedeTirar()) {
                comando.add(tira());
            }
        }
        return comando.isEmpty() ? super.ejecuta() : comando;
    }

    protected CommandHitBall tira() {
        double posicionY = posicion.getY() < MSGConstants.DISTANCIA_NORMALIZACION_TIRO ? MSGConstants.DISTANCIA_NORMALIZACION_TIRO : posicion.getY();
        double posicionYDesplazada = posicionY - MSGConstants.DISTANCIA_NORMALIZACION_TIRO;
        double variacionY = posicionYDesplazada / (Constants.LARGO_CAMPO_JUEGO / 2);
        double variacionX = variacionY * MSGConstants.ANCHO_PORTERIA_REAL / 3;
        Punto destino = new Punto(context.getPorteroRival().getPosicion().getX() > 0 ? Constants.centroArcoSup.getX() - variacionX : Constants.centroArcoSup.getX() + variacionX, Constants.centroArcoSup.getY());
        CommandHitBall tira = super.tira(destino);
        if (tira != null) {
            tira.setHitPower(FUERZA_REMATE_DELANTERO);
        }
        return tira;
    }

    /**
     * Indica si puede tirar.
     * @return si puede tirar.
     */
    private boolean puedeTirar() {
        Punto3D balon = context.getBalon().getPosicion();
        Punto destino = new Punto(Constants.centroArcoSup);
        double distancia = TrigonometriaUtils.getDistancia(balon.getPuntoXY(), destino);
        double anguloHorizontal = TrigonometriaUtils.getAngulo(balon.getPuntoXY(), destino);
        double e = Constants.getErrorAngular(this.getEstadisticas().getPrecision());
        double anguloHorizontalMaximo = anguloHorizontal + (e / 2) * Math.PI;
        double anguloHorizontalMinimo = anguloHorizontal - (e / 2) * Math.PI;

        double minDX = distancia * Math.cos(anguloHorizontalMaximo);
        double maxDX = distancia * Math.cos(anguloHorizontalMinimo);

        return maxDX - minDX <= MSGConstants.ANCHO_PORTERIA_REAL;
    }
}
