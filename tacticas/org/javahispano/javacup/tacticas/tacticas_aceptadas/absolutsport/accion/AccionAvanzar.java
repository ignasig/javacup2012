package org.javahispano.javacup.tacticas.tacticas_aceptadas.absolutsport.accion;

import org.javahispano.javacup.model.command.Command;


/**
 * Clase que implementa el interfaz Action y que representa la acci�n de avance del jugador.
 * 
 * @author Christian Onwuzor Mart�n (chr -> airchris01@yahoo.es)
 */
public class AccionAvanzar implements Accion {

	private Command comando;
	private Command comandoIrA;

	public AccionAvanzar(Command comando, Command comandoIrA) {
		
		this.comando = comando;
		this.comandoIrA = comandoIrA;
	}

	
	public Command comando() {
		return comando;
	}


	public Command comandoIrA() {
		return comandoIrA;
	}
}
