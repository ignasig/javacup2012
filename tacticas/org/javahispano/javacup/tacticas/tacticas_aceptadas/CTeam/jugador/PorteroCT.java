package org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.jugador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.tactica.ICTeam2011;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.tactica.PosicionCT;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.command.CommandHitBall;
import org.javahispano.javacup.model.command.CommandMoveTo;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.util.Position;

public class PorteroCT extends AbstractJugador {

	public PorteroCT(Equipo equipo, int indice, PlayerDetail detalle, ICTeam2011 tactica) {
		super(equipo, indice, detalle, tactica);
	}

	@Override
	public Collection<? extends Command> jugar() {
		List<Command> comandos = new ArrayList<Command>();

		if (isControlBalon()) {
			int rc = partido.ballPosition().nearestIndex(partido.rivalPlayers());
			Position rival = partido.rivalPlayers()[rc];
			double dr = partido.ballPosition().distance(rival);
			double angulo = dr < 5 ? 60 : 45;
			comandos.add(new CommandHitBall(getIndice(), Constants.centroCampoJuego, 1.0, angulo));
		}

		List<IJugadorCT> recuperacion = tactica.jugadoresRecuperacion();
		if (recuperacion.contains(this)) {
			int it = Math.max(0, tactica.iteracionesRecuperacion());
			double[] b = partido.getTrajectory(it);
			Position posBalon = new Position(b[0], b[1]);
			comandos.add(new CommandMoveTo(getIndice(), posBalon));
			return comandos;
		}

		PosicionCT balon = new PosicionCT(partido.ballPosition());
		PosicionCT posteDer = new PosicionCT(Constants.posteDerArcoInf);
		PosicionCT posteIzq = new PosicionCT(Constants.posteIzqArcoInf);
		PosicionCT act = getActual();

		double dist1 = act.distanciaALinea(balon, posteDer, true);
		double dist2 = act.distanciaALinea(balon, posteIzq, true);
		if (dist1 < dist2) {
			dist1 = dist1 * Math.abs(Math.cos(act.angulo(balon) - Math.PI / 2.0));
		} else {
			dist2 = dist2 * Math.abs(Math.cos(act.angulo(balon) + Math.PI / 2.0));
		}
		double dif = dist1 - dist2;
		if (Math.abs(dif) <= 0.125) {
			comandos.add(new CommandMoveTo(getIndice(), act.getPos()));
		} else {
			double x = act.getX() + dif / 2.0;
			x = Math.max(x, Constants.posteIzqArcoInf.getX() + 0.25);
			x = Math.min(x, Constants.posteDerArcoInf.getX() - 0.25);

			comandos.add(new CommandMoveTo(getIndice(), new Position(x, Constants.centroArcoInf.getY() + 2)));
		}

		return comandos;
	}

}
