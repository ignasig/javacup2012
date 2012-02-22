package org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.tactica;

import static org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.jugador.IJugadorCT.Equipo.PROPIO;

import java.util.ArrayList;
import java.util.List;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.jugador.IJugadorCT;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.jugador.IJugadorCT.Equipo;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.model.Tactic;

public class CTeam2011 extends BaseCTeam2011 implements Tactic {

	public List<Command> execute(GameSituations sp) {
		List<Command> comandos = new ArrayList<Command>();

		inicializar(sp);
		try {
			ultRecuperando.clear();
			ultRecuperando.addAll(recuperando);
			
			usadas.clear();
			recuperando.clear();

			inicializarIteracion();

			calcularAlineacion();
			int idx = 0;
			for (IJugadorCT jug : getJugadores(PROPIO)) {
				Position p = alineacionActual()[idx++];
				jug.setSiguiente(new PosicionCT(p));
			}

			if (posesionBalon().equals(Equipo.PROPIO)) {
				defensa = null;
				tacticaAtaque();
			} else {
				ataque = null;
				tacticaDefensa();
			}

			for (IJugadorCT jug : getJugadores(PROPIO)) {
				comandos.addAll(jug.jugar());
			}

			finalizarIteracion();
		} catch (Exception ex) {
			safeInvocation(comandos);
			ex.printStackTrace();
		}

		return comandos;
	}

	private void safeInvocation(List<Command> comandos) {
		try {
			calcularAlineacion();
			int idx = 0;
			for (IJugadorCT jug : getJugadores(PROPIO)) {
				Position p = alineacionActual()[idx++];
				jug.setSiguiente(new PosicionCT(p));
			}

			for (IJugadorCT jug : getJugadores(PROPIO)) {
				comandos.addAll(jug.jugar());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	IEstrategiaCT ataque = null;

	IEstrategiaCT defensa = null;

	private void tacticaDefensa() {
		defensa = new EstrategiaDefensaCT(sp, this);
		defensa.ejecutarIteracion();
	}

	private void tacticaAtaque() {
		ataque = new EstrategiaAtaqueCT(sp, this, voronoiTodos, voronoiRivales, voronoiJugadores);
		ataque.ejecutarIteracion();
	}

	private void calcularAlineacion() {
		double y = sp.ballPosition().getY();
		alineacion = 0;
		for (int i = alineaciones.length - 1; i > 0; i--) {
			boolean t = false;
			for (int j = 1; j <= 4; j++) {
				if (alineaciones[i][j].getY() > y) {
					t = true;
					break;
				}
			}
			if (!t) {
				alineacion = i;
				break;
			}
		}
	}

}