package org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.jugador;

import java.util.ArrayList;
import java.util.Collection;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.tactica.ICTeam2011;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.PlayerDetail;

public class PorteroRivalCT extends AbstractJugador {

	public PorteroRivalCT(Equipo equipo, int indice, PlayerDetail detalle, ICTeam2011 tactica) {
		super(equipo, indice, detalle, tactica);
	}

	@Override
	public Collection<? extends Command> jugar() {
		return new ArrayList<Command>(0);
	}
}
