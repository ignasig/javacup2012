package org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.controladores;

import java.util.ArrayList;
import java.util.List;

import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.alineaciones.Alineaciones;

public class ControladorAlineaciones {

	private static ControladorAlineaciones instance;
	private static Alineaciones detalleAlineaciones;
	private static List<Position[]> alineaciones;
	
	private static int alineacionActual;
	private final static int ITERACIONES_CAMBIO = 300;
	private static int proximaIteracionCambioAlineacion = 0;

	static {
		instance = new ControladorAlineaciones();
		alineaciones = new ArrayList<Position[]>();
		detalleAlineaciones = new Alineaciones();
		
		alineaciones.add(detalleAlineaciones.getAlineacion1());
		alineaciones.add(detalleAlineaciones.getAlineacion2());
		alineaciones.add(detalleAlineaciones.getAlineacion3());
		alineaciones.add(detalleAlineaciones.getAlineacion4());
	}
	
	private ControladorAlineaciones() {

	}

	public static ControladorAlineaciones getInstance() {
		return instance;
	}
	
	public Position[] getAlineacionActual(final GameSituations situacionPartido) {
		if (situacionPartido.iteration() >= proximaIteracionCambioAlineacion) {
			alineacionActual = ControladorUtils.getInstance().generaNumeroEnteroAleatorioEntre(0, alineaciones.size() -1);
			proximaIteracionCambioAlineacion += ITERACIONES_CAMBIO;
		}
		return alineaciones.get(alineacionActual);
	}

	public static void setProximaIteracionCambioAlineacion( final 
			int proximaIteracionCambioAlineacion) {
		ControladorAlineaciones.proximaIteracionCambioAlineacion = proximaIteracionCambioAlineacion;
	}
	
}
