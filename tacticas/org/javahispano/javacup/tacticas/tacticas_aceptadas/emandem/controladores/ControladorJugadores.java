/**
 * 
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.controladores;

import java.util.ArrayList;
import java.util.List;

import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.command.CommandMoveTo;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.enums.CUADRANTE_CANCHA;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.emandem.enums.JUGADOR;

public class ControladorJugadores {

	private static ControladorJugadores instance;

	static {
		instance = new ControladorJugadores();
	}

	private ControladorJugadores() {

	}

	public static ControladorJugadores getInstance() {
		return instance;
	}

	public List<Command> getComandos(final	GameSituations situacionPartido, Position[] alineacionActual) {
		List<Command> comandos = new ArrayList<Command>();

		for (JUGADOR jugador : JUGADOR.values()) {
			// Se salta al portero de la lista
			if (jugador.numero() == JUGADOR.PORTERO.numero()) {
				continue;
			}
			
			if (ControladorEstadoPartido.getInstance().getJugadoresPuedenRematar().contains(jugador.numero())) {
				comandos.add(ControladorTiros.getInstance().getTiro(jugador.numero(), situacionPartido));

			} else if(ControladorEstadoPartido.getInstance().getJugadoresPuedenRecuperar().contains(jugador.numero())) {
				Position posicionBalon = ControladorEstadoPartido.getInstance().getPosicionRecuperaBalon();

				switch (CUADRANTE_CANCHA.valueOf(ControladorEstadoPartido.getInstance().getJugadoresConCuadrante().get(jugador.numero()))) {
				case CUADRANTE_1_SUP:
					if (ControladorEstadoPartido.getInstance().isEstaBalonEnElCuadrante1Sup()) {
						comandos.add(new CommandMoveTo(jugador.numero(), new Position(posicionBalon.getX(), posicionBalon.getY())));

					} else {
						// Verifica si ya llego a su posicion inicial el jugador, si ya llego no hace nada
						if (situacionPartido.myPlayers()[jugador.numero()].getX() != alineacionActual[jugador.numero()].getX() &&
								situacionPartido.myPlayers()[jugador.numero()].getY() != alineacionActual[jugador.numero()].getY()) {
							Position posicionCorregida = this.verificaYDesmarcaJugador(jugador.numero(), new Position(alineacionActual[jugador.numero()].getX(), alineacionActual[jugador.numero()].getY()));
							comandos.add(new CommandMoveTo(jugador.numero(), posicionCorregida));
						}
					}
					break;
				
				case CUADRANTE_2_SUP:
					if (ControladorEstadoPartido.getInstance().isEstaBalonEnElCuadrante2Sup()) {
						comandos.add(new CommandMoveTo(jugador.numero(), new Position(posicionBalon.getX(), posicionBalon.getY())));

					} else {
						// Verifica si ya llego a su posicion inicial el jugador, si ya llego no hace nada
						if (situacionPartido.myPlayers()[jugador.numero()].getX() != alineacionActual[jugador.numero()].getX() &&
								situacionPartido.myPlayers()[jugador.numero()].getY() != alineacionActual[jugador.numero()].getY()) {
							Position posicionCorregida = this.verificaYDesmarcaJugador(jugador.numero(), new Position(alineacionActual[jugador.numero()].getX(), alineacionActual[jugador.numero()].getY()));
							comandos.add(new CommandMoveTo(jugador.numero(), posicionCorregida));
						}
					}
					break;
					
				case CUADRANTE_3_SUP:
					if (ControladorEstadoPartido.getInstance().isEstaBalonEnElCuadrante3Sup()) {
						comandos.add(new CommandMoveTo(jugador.numero(), new Position(posicionBalon.getX(), posicionBalon.getY())));

					} else {
						// Verifica si ya llego a su posicion inicial el jugador, si ya llego no hace nada
						if (situacionPartido.myPlayers()[jugador.numero()].getX() != alineacionActual[jugador.numero()].getX() &&
								situacionPartido.myPlayers()[jugador.numero()].getY() != alineacionActual[jugador.numero()].getY()) {
							Position posicionCorregida = this.verificaYDesmarcaJugador(jugador.numero(), new Position(alineacionActual[jugador.numero()].getX(), alineacionActual[jugador.numero()].getY()));
							comandos.add(new CommandMoveTo(jugador.numero(), posicionCorregida));
						}
					}
					break;
					
				case CUADRANTE_4_SUP:
					if (ControladorEstadoPartido.getInstance().isEstaBalonEnElCuadrante4Sup()) {
						comandos.add(new CommandMoveTo(jugador.numero(), new Position(posicionBalon.getX(), posicionBalon.getY())));

					} else {
						// Verifica si ya llego a su posicion inicial el jugador, si ya llego no hace nada
						if (situacionPartido.myPlayers()[jugador.numero()].getX() != alineacionActual[jugador.numero()].getX() &&
								situacionPartido.myPlayers()[jugador.numero()].getY() != alineacionActual[jugador.numero()].getY()) {
							Position posicionCorregida = this.verificaYDesmarcaJugador(jugador.numero(), new Position(alineacionActual[jugador.numero()].getX(), alineacionActual[jugador.numero()].getY()));
							comandos.add(new CommandMoveTo(jugador.numero(), posicionCorregida));
						}
					}
					break;
					
				case CUADRANTE_1_INF:
					if (ControladorEstadoPartido.getInstance().isEstaBalonEnElCuadrante1Inf()) {
						comandos.add(new CommandMoveTo(jugador.numero(), new Position(posicionBalon.getX(), posicionBalon.getY())));

					} else {
						// Verifica si ya llego a su posicion inicial el jugador, si ya llego no hace nada
						if (situacionPartido.myPlayers()[jugador.numero()].getX() != alineacionActual[jugador.numero()].getX() &&
								situacionPartido.myPlayers()[jugador.numero()].getY() != alineacionActual[jugador.numero()].getY()) {
							Position posicionCorregida = this.verificaYDesmarcaJugador(jugador.numero(), new Position(alineacionActual[jugador.numero()].getX(), alineacionActual[jugador.numero()].getY()));
							comandos.add(new CommandMoveTo(jugador.numero(), posicionCorregida));
						}
					}
					break;
					
				case CUADRANTE_2_INF:
					if (ControladorEstadoPartido.getInstance().isEstaBalonEnElCuadrante2Inf()) {
						comandos.add(new CommandMoveTo(jugador.numero(), new Position(posicionBalon.getX(), posicionBalon.getY())));

					} else {
						// Verifica si ya llego a su posicion inicial el jugador, si ya llego no hace nada
						if (situacionPartido.myPlayers()[jugador.numero()].getX() != alineacionActual[jugador.numero()].getX() &&
								situacionPartido.myPlayers()[jugador.numero()].getY() != alineacionActual[jugador.numero()].getY()) {
							Position posicionCorregida = this.verificaYDesmarcaJugador(jugador.numero(), new Position(alineacionActual[jugador.numero()].getX(), alineacionActual[jugador.numero()].getY()));
							comandos.add(new CommandMoveTo(jugador.numero(), posicionCorregida));
						}
					}
					break;
					
				case CUADRANTE_3_INF:
					if (ControladorEstadoPartido.getInstance().isEstaBalonEnElCuadrante3Inf()) {
						comandos.add(new CommandMoveTo(jugador.numero(), new Position(posicionBalon.getX(), posicionBalon.getY())));

					} else {
						// Verifica si ya llego a su posicion inicial el jugador, si ya llego no hace nada
						if (situacionPartido.myPlayers()[jugador.numero()].getX() != alineacionActual[jugador.numero()].getX() &&
								situacionPartido.myPlayers()[jugador.numero()].getY() != alineacionActual[jugador.numero()].getY()) {
							Position posicionCorregida = this.verificaYDesmarcaJugador(jugador.numero(), new Position(alineacionActual[jugador.numero()].getX(), alineacionActual[jugador.numero()].getY()));
							comandos.add(new CommandMoveTo(jugador.numero(), posicionCorregida));
						}
					}
					break;
					
				case CUADRANTE_4_INF:
					if (ControladorEstadoPartido.getInstance().isEstaBalonEnElCuadrante4Inf()) {
						comandos.add(new CommandMoveTo(jugador.numero(), new Position(posicionBalon.getX(), posicionBalon.getY())));

					} else {
						// Verifica si ya llego a su posicion inicial el jugador, si ya llego no hace nada
						if (situacionPartido.myPlayers()[jugador.numero()].getX() != alineacionActual[jugador.numero()].getX() &&
								situacionPartido.myPlayers()[jugador.numero()].getY() != alineacionActual[jugador.numero()].getY()) {
							Position posicionCorregida = this.verificaYDesmarcaJugador(jugador.numero(), new Position(alineacionActual[jugador.numero()].getX(), alineacionActual[jugador.numero()].getY()));
							comandos.add(new CommandMoveTo(jugador.numero(), posicionCorregida));
						}
					}
					break;
				}
			} else {
				// Verifica si ya llego a su posicion inicial el jugador, si ya llego no hace nada
				if (situacionPartido.myPlayers()[jugador.numero()].getX() != alineacionActual[jugador.numero()].getX() &&
						situacionPartido.myPlayers()[jugador.numero()].getY() != alineacionActual[jugador.numero()].getY()) {
					Position posicionCorregida = this.verificaYDesmarcaJugador(jugador.numero(), new Position(alineacionActual[jugador.numero()].getX(), alineacionActual[jugador.numero()].getY()));
					comandos.add(new CommandMoveTo(jugador.numero(), posicionCorregida));
				}
			}
		}

		return comandos;
	}

	private Position verificaYDesmarcaJugador(final int jugadorNumero, final Position posicion) {
		if (ControladorEstadoPartido.getInstance().getJugadoresMarcados().containsKey(jugadorNumero) &&
				ControladorEstadoPartido.getInstance().getJugadoresMarcados().get(jugadorNumero).size() > 1) {
			posicion.setPosition(posicion.getX() + 5, posicion.getY());
		}
		return posicion;
	}


}
