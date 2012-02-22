/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

import java.awt.Color;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.command.CommandHitBall;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.tray.TrayectoriaManager;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.tray.TrayectoriaPunto;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.Punto;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.trig.TrigonometriaUtils;

/**
 *
 * @author MaN
 */
public class MSGMedioCentro extends MSGAbstractJugadorPropio {

    public MSGMedioCentro(int index, Color colorPelo, Color colorPiel, String nombre, int numero, MSGEstadisticas estadisticas) {
        super(index, colorPelo, colorPiel, nombre, numero, estadisticas);
    }

    @Override
    protected Command despeja() {
        Command tiro = tira(new Punto(Constants.centroArcoSup));
        if (tiro == null) {
            double distancia = TrigonometriaUtils.getDistancia(posicion, new Punto(Constants.centroArcoSup));
            TrayectoriaPunto pase = TrayectoriaManager.getInstance().getDisparosByDistancia(distancia);
            double fuerza = pase.getTrayectoria().getVelocidad() / Constants.getVelocidadRemate(getEstadisticas().getRemate());
            tiro = new CommandHitBall(
                    index,
                    Constants.centroArcoSup,
                    fuerza,
                    pase.getTrayectoria().getAnguloVertical());

        }
        return tiro;
    }

    public double getIteracionesVentajaPase() {
        return MSGConstants.ITERACIONES_MIMIMAS_VENTAJA_MEDIO;
    }


}
