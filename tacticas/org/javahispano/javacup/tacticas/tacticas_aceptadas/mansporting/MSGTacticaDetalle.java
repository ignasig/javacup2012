/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting;

import java.awt.Color;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.javahispano.javacup.render.EstiloUniforme;
import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.TacticDetail;

/**
 *
 * @author MaN
 */
public class MSGTacticaDetalle implements TacticDetail {

    private static final String NOMBRE = "Mansporting de Gijón";
    private static final String PAIS = "España";
    private static final String ENTRENADOR = "MaN";
    private static final Color COLOR_CAMISETA = new Color(217, 227, 228);
    private static final Color COLOR_PANTALON = new Color(28, 75, 155);
    private static final Color COLOR_FRANJA = new Color(120, 0, 0);
    private static final Color COLOR_CALCETOS = new Color(28, 75, 155);
    private static final Color COLOR_PORTERO = new Color(88, 181, 111);
    private static final EstiloUniforme ESTILO_UNIFORME = EstiloUniforme.LINEAS_VERTICALES;
    private static final Color COLOR_CAMISETA2 = new Color(200, 0, 0);
    private static final Color COLOR_PANTALON2 = new Color(200, 0, 0);
    private static final Color COLOR_FRANJA2 = new Color(200, 0, 0);
    private static final Color COLOR_CALCETOS2 = new Color(200, 0, 0);
    private static final Color COLOR_PORTERO2 = new Color(255, 143, 83);
    private static final EstiloUniforme ESTILO_UNIFORME2 = EstiloUniforme.SIN_ESTILO;
    private final List<MSGJugadorDetalle> jugadoresDetalle;

    public MSGTacticaDetalle(Collection<? extends MSGJugadorDetalle> jugadoresDetalle) {
        this.jugadoresDetalle = new LinkedList<MSGJugadorDetalle>();
        this.jugadoresDetalle.addAll(jugadoresDetalle);
    }

    public String getTacticName() {
        return NOMBRE;
    }

    public String getCountry() {
        return PAIS;
    }

    public String getCoach() {
        return ENTRENADOR;
    }

    public Color getShirtColor() {
        return COLOR_CAMISETA;
    }

    public Color getShortsColor() {
        return COLOR_PANTALON;
    }

    public Color getShirtLineColor() {
        return COLOR_FRANJA;
    }

    public Color getSocksColor() {
        return COLOR_CALCETOS;
    }

    public Color getGoalKeeper() {
        return COLOR_PORTERO;
    }

    public EstiloUniforme getStyle() {
        return ESTILO_UNIFORME;
    }

    public Color getShirtColor2() {
        return COLOR_CAMISETA2;
    }

    public Color getShortsColor2() {
        return COLOR_PANTALON2;
    }

    public Color getShirtLineColor2() {
        return COLOR_FRANJA2;
    }

    public Color getSocksColor2() {
        return COLOR_CALCETOS2;
    }

    public Color getGoalKeeper2() {
        return COLOR_PORTERO2;
    }

    public EstiloUniforme getStyle2() {
        return ESTILO_UNIFORME2;
    }

    public PlayerDetail[] getPlayers() {
        return MSGUtils.convertJugadores(jugadoresDetalle);
    }
}
