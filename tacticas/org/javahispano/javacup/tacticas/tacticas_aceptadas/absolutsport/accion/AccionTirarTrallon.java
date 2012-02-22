package org.javahispano.javacup.tacticas.tacticas_aceptadas.absolutsport.accion;

import org.javahispano.javacup.model.command.Command;

/**
 * Clase que implementa el interfaz Action y que representa la acci�n de tiro a trallon del jugador.
 * 
 * @author Christian Onwuzor Mart�n (chr -> airchris01@yahoo.es)
 */
public class AccionTirarTrallon implements Accion {

	private Command comando;

	public AccionTirarTrallon(Command comando) {
		this.comando = comando;
	}
	
	public Command comando() {
		return comando;
	}
}
