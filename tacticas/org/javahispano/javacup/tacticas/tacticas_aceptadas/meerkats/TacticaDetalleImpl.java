package org.javahispano.javacup.tacticas.tacticas_aceptadas.meerkats;

import java.awt.Color;
import org.javahispano.javacup.render.EstiloUniforme;
import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.TacticDetail;

/**
 * Implementación de TacticDetail.
 */
public class TacticaDetalleImpl implements TacticDetail {

    @Override
    public String getTacticName() {
	return "Meerkats FC";
    }

    @Override
    public String getCountry() {
	return "Colombia";
    }

    @Override
    public String getCoach() {
	return "Christian Ortega";
    }

    @Override
    public Color getShirtColor() {
	return new Color(255, 0, 0);
    }

    @Override
    public Color getShortsColor() {
	return new Color(0, 0, 0);
    }

    @Override
    public Color getShirtLineColor() {
	return new Color(0, 0, 0);
    }

    @Override
    public Color getSocksColor() {
	return new Color(0, 0, 0);
    }

    @Override
    public Color getGoalKeeper() {
	return new Color(255, 153, 0);
    }

    @Override
    public EstiloUniforme getStyle() {
	return EstiloUniforme.SIN_ESTILO;
    }

    @Override
    public Color getShirtColor2() {
	return new Color(255, 255, 255);
    }

    @Override
    public Color getShortsColor2() {
	return new Color(255, 255, 255);
    }

    @Override
    public Color getShirtLineColor2() {
	return new Color(255, 0, 0);
    }

    @Override
    public Color getSocksColor2() {
	return new Color(255, 0, 0);
    }

    @Override
    public Color getGoalKeeper2() {
	return new Color(0, 0, 204);
    }

    @Override
    public EstiloUniforme getStyle2() {
	return EstiloUniforme.SIN_ESTILO;
    }

    @Override
    public PlayerDetail[] getPlayers() {
	return Alineacion.getJugadores();
    }
    
}
