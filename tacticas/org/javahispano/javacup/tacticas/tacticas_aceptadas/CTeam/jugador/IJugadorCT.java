package org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.jugador;

import java.util.Collection;
import java.util.Deque;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.tactica.PosicionCT;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.util.Disparo;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.util.Triangulation;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.engine.GameSituations;

public interface IJugadorCT {

	public boolean isControlBalon();

	public PosicionCT getSiguiente();

	public PosicionCT getActual();

	public Deque<PosicionCT> getPosiciones();

	public PlayerDetail getDetalle();

	public int getIndice();

	public Equipo getEquipo();

	public Disparo paseLibre(PosicionCT destino, int itRematar);

	public void iniciarTurno(GameSituations sp, boolean puedeRematar);

	public double distanciaEn(int iteraciones);

	public void setPuedeRecuperarBalon(boolean puede, int it);

	public void disparar(PosicionCT destino, Disparo d);

	public PosicionCT posicionVoronoi();

	public void setSiguiente(PosicionCT siguiente);

	public void setVoronoi(Triangulation propios, Triangulation rivales, Triangulation todos);

	public enum Equipo {
		PROPIO, RIVAL, NEUTRAL;
	}

	public Collection<? extends Command> jugar();

}
