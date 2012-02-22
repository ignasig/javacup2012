package org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.controladores;

import java.util.LinkedList;
import java.util.List;

import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;

public class CPU {
	private LinkedList<Command> comandos = new LinkedList<Command>();
	
	public List<Command> getComandosEjecutar(GameSituations situacionPartido) {
		
		this.comandos.clear();
		
		ControladorEstadoPartido.getInstance().analizarYCargarInformacion(situacionPartido);
		
		Position[] alineacionActual = ControladorAlineaciones.getInstance().getAlineacionActual(situacionPartido);
		
		this.comandos.addAll(ControladorPortero.getInstance().getComandos(situacionPartido, alineacionActual));
		
		this.comandos.addAll(ControladorJugadores.getInstance().getComandos(situacionPartido, alineacionActual));
	        
	    return comandos;
	}   
}