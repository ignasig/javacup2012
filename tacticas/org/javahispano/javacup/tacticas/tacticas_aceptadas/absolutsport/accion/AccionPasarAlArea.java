package org.javahispano.javacup.tacticas.tacticas_aceptadas.absolutsport.accion;

import org.javahispano.javacup.model.command.Command;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.absolutsport.tactica.utilidades.Jugador;

/**
 * Clase que implementa el interfaz Action y que representa la acci�n de pase al area del jugador.
 * 
 * @author Christian Onwuzor Mart�n (chr -> airchris01@yahoo.es)
 */
public class AccionPasarAlArea implements Accion {

	private Command comando;
	private Jugador jugadorDestino;
	
	
	public AccionPasarAlArea(Command comando, Jugador jugadorDestino) {

		this.comando = comando;
		this.jugadorDestino = jugadorDestino;
	}



	public Command comando() {
		return comando;
	}

	public Jugador jugadorDestino() {
		return jugadorDestino;
	}
}
