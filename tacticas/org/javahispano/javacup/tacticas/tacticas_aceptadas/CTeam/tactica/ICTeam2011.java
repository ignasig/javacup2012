package org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.tactica;

import java.util.List;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.jugador.IJugadorCT;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.jugador.IJugadorCT.Equipo;
import org.javahispano.javacup.model.util.Position;

public interface ICTeam2011 {
	public List<IJugadorCT> getJugadores(Equipo equipo);

	public int iteracionesRecuperacion();

	public int iteracionesRecuperacionRival();

	public List<IJugadorCT> jugadoresRecuperacion();
	
	public List<IJugadorCT> previoJugadoresRecuperacion();

	public List<IJugadorCT> rivalesRecuperacion();

	public Equipo posesionBalon();

	public Position[] alineacionActual();

	public boolean posicionUsada(PosicionCT p);

	public void usada(PosicionCT p);

	public List<IJugadorCT> recuperando();

	public void ultimoPase(PosicionCT ultimo);

	public PosicionCT getUltimoPase();
	
	public boolean cambioPosesion();
}
