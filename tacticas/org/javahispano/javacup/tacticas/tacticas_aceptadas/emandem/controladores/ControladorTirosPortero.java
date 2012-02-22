package org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.controladores;

import org.javahispano.javacup.model.command.CommandHitBall;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;

public class ControladorTirosPortero {
	
	private static ControladorTirosPortero instance;
	
	static {
		instance = new ControladorTirosPortero();
	}
	
	private ControladorTirosPortero() {

	}

	public static ControladorTirosPortero getInstance() {
		return instance;
	}

	public CommandHitBall getTiro(final int jugador, final GameSituations situacionPartido) {
		Position destinoBola = null;
		double ejeXDestino = ControladorUtils.getInstance().generaNumeroDobleAleatorioEntre(0, 34);
		double fuerzaRemate = 1;
		boolean tiraLadoDerecho = ControladorUtils.getInstance().generaBoleanoAleatorioEntre();

		destinoBola = Constants.centroArcoSup;
		if (tiraLadoDerecho) {
			destinoBola.setPosition((ejeXDestino * -1), destinoBola.getY());
		} else {
			destinoBola.setPosition(ejeXDestino, destinoBola.getY());
		}

		return new CommandHitBall(jugador, destinoBola, fuerzaRemate, 30);
	}
}
