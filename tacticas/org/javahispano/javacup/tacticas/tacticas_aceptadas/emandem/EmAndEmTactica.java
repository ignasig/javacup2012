package org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem;

import java.util.List;

import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.model.Tactic;
import org.javahispano.javacup.model.TacticDetail;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.alineaciones.Alineaciones;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.configuracion.TacticaDetalleImpl;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.controladores.CPU;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.controladores.ControladorAlineaciones;


public class EmAndEmTactica implements Tactic {
	private Alineaciones alineaciones = new Alineaciones();
	private TacticDetail detalle=new TacticaDetalleImpl();
	private CPU cpu = new CPU();
	
	public TacticDetail getDetail() {
		return detalle;
	}

	public Position[] getStartPositions(final GameSituations situacionPartido) {
		ControladorAlineaciones.setProximaIteracionCambioAlineacion(0);
		return this.alineaciones.getAlineacion5();
	}

	public Position[] getNoStartPositions(final GameSituations situacionPartido) {
		ControladorAlineaciones.setProximaIteracionCambioAlineacion(0);
		return this.alineaciones.getAlineacion6();
	}

	//Lista de comandos a ejecutar.
	@Override
	public List<Command> execute(final GameSituations situacionPartido) {
		return this.cpu.getComandosEjecutar(situacionPartido);
	}
}