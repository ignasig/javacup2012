/*
 *    Chupones.java, una t�ctica con la que competir en javaCup 2011.
 *    Copyright (C) 2009  Jes�s Garc�a (jge_coco@terra.es)
 *    
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *    
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *    
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.javahispano.javacup.tacticas.tacticas_aceptadas.Chupones;
//package javaCup2011_Chupones;

import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.TacticDetail;
import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.Tactic;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.command.CommandMoveTo;
import org.javahispano.javacup.model.command.CommandHitBall;
import org.javahispano.javacup.render.EstiloUniforme;
import java.awt.Color;
import org.javahispano.javacup.model.*;
import java.util.List;
import java.util.ArrayList;

public class Chupones implements Tactic {

	Position alineacion1[] = new Position[]{
		new Position(0.2595419847328244,-50.41044776119403),
		new Position(-11.16030534351145,-35.78358208955224),
		new Position(12.717557251908397,-35.26119402985075),
		new Position(28.290076335877863,-28.470149253731343),
		new Position(-28.290076335877863,-28.470149253731343),
		new Position(14.793893129770993,-18.544776119402986),
		new Position(-17.389312977099234,-19.58955223880597),
		new Position(-23.618320610687025,-0.7835820895522387),
		new Position(0.0,0.0),
		new Position(-6.4885496183206115,-6.529850746268657),
		new Position(22.580152671755727,-1.3059701492537314)
	};

	Position alineacion2[] = new Position[]{
		new Position(0.2595419847328244,-50.41044776119403),
		new Position(-11.16030534351145,-35.78358208955224),
		new Position(12.717557251908397,-35.26119402985075),
		new Position(28.290076335877863,-28.470149253731343),
		new Position(-28.290076335877863,-28.470149253731343),
		new Position(14.793893129770993,-18.544776119402986),
		new Position(-17.389312977099234,-19.58955223880597),
		new Position(-23.618320610687025,-0.7835820895522387),
		new Position(6.4885496183206115,-6.529850746268657),
		new Position(-6.4885496183206115,-6.529850746268657),
		new Position(22.580152671755727,-1.3059701492537314)
	};

	class TacticaDetalleImpl implements TacticDetail {
		
		public String getTacticName() {
			return "Chupones";
		}

		public String getCountry() {
			return "Espa�a";
		}

		public String getCoach() {
			return "jge_coco";
		}

		public Color getShirtColor() {
			return new Color(255, 255, 0);
		}

		public Color getShortsColor() {
			return new Color(204, 102, 0);
		}

		public Color getShirtLineColor() {
			return new Color(255, 255, 255);
		}

		public Color getSocksColor() {
			return new Color(255, 255, 0);
		}

		public Color getGoalKeeper() {
			return new Color(204, 204, 204        );
		}

		public EstiloUniforme getStyle() {
			return EstiloUniforme.SIN_ESTILO;
		}

		public Color getShirtColor2() {
			return new Color(255, 255, 255);
		}

		public Color getShortsColor2() {
			return new Color(255, 0, 102);
		}

		public Color getShirtLineColor2() {
			return new Color(255, 255, 255);
		}

		public Color getSocksColor2() {
			return new Color(255, 204, 0);
		}

		public Color getGoalKeeper2() {
			return new Color(155, 53, 201        );
		}

		public EstiloUniforme getStyle2() {
			return EstiloUniforme.SIN_ESTILO;
		}

		class JugadorImpl implements PlayerDetail {

			String nombre;
			int numero;
			Color piel, pelo;
			double velocidad, remate, presicion;
			boolean portero;
			Position posicion;

			public JugadorImpl(String nombre, int numero, Color piel, Color pelo,
					double velocidad, double remate, double presicion, boolean portero) {
				this.nombre = nombre;
				this.numero = numero;
				this.piel = piel;
				this.pelo = pelo;
				this.velocidad = velocidad;
				this.remate = remate;
				this.presicion = presicion;
				this.portero = portero;
			}

			public String getPlayerName() {
				return nombre;
			}

			public Color getSkinColor() {
				return piel;
			}

			public Color getHairColor() {
				return pelo;
			}

			public int getNumber() {
				return numero;
			}

			public boolean isGoalKeeper() {
				return portero;
			}

			public double getSpeed() {
				return velocidad;
			}

			public double getPower() {
				return remate;
			}

			public double getPrecision() {
				return presicion;
			}
			
		}

		public PlayerDetail[] getPlayers() {
			return new PlayerDetail[]{
				new JugadorImpl("Singuli", 1, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, true),
				new JugadorImpl("Bini", 2, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false),
				new JugadorImpl("Terni", 3, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false),
				new JugadorImpl("Quaterni", 4, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false),
				new JugadorImpl("Quini", 5, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false),
				new JugadorImpl("Seni", 6, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false),
				new JugadorImpl("Septeni", 7, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false),
				new JugadorImpl("Octoni", 8, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false),
				new JugadorImpl("Noveni", 9, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false),
				new JugadorImpl("Deni", 10, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false),
				new JugadorImpl("Undeni", 11, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,1.0d, false)
			};
		}
	}

	TacticDetail detalle = new TacticaDetalleImpl();

	public TacticDetail getDetail() {
		return detalle;
	}
	
	public Position[] getStartPositions(GameSituations sp) {
		return alineacion1;
	}
	
	public Position[] getNoStartPositions(GameSituations sp) {
		return alineacion2;
	}

	//
	// Atributos
	//
	// En casos de arrays con alguna dimensi�n de tama�o 2,
	// el �ndice 0 corresponde a datos asociados a jugadores,
	// y el �ndice 1 corresponde a datos asociados a rivalPlayers,
	//

	private static final int N = 11;  // n�mero de jugadores en cada equipo
	private static final int itersSegundo = 20;  // iteraciones por segundo

	/** �ndices de portero y portero rival. */
	private int[] porteros = new int[2];

	/**
	 * Matriz de distancias al cuadrado
	 * entre dos conjuntos de posiciones (de jugadores y de rivalPlayers)
	 * dist2[j1][j2] es la distancia al cuadrado entre jugador j1 y rival j2
	 */
	private double[][] dist2 = new double[N][N];

	/**
	 * Mejores emparejamientos entre jugadores y rivalPlayers.
	 * mejorPareja[0][j2] es el mejor jugador dado rival j2
	 * mejorPareja[1][j1] es el mejor rival dado jugador j1
	 */
	private int[][] mejorPareja = new int[2][N];

	/** Coordenada polar. */
	class Pol {
		double A;  // �ngulo (alfa)
		double R;  // distancia (rho)
	}

	/**
	 * Posiciones relativas polares entre jugadores, rivalPlayers, y bal�n.
	 * posRelJugs[0][j][j1] es la polar entre jugador j y jugador j1
	 * posRelJugs[1][j][j2] es la polar entre jugador j y rival j2
	 * posRelBalon[j] es la polar entre jugador j y bal�n
	 */
	private double ampDisparo[] = new double[N];
	private double dirDisparo[] = new double[N];
	
	private Position balon0 = new Position();
	private double alturaBalon0 = 0;
	private Position balon1;
	private double alturaBalon1;
	
	// array de posiciones para el ComandoIrA,
	// que permite filtrado de movimientos peque�os en cada iteraci�n
	private Position[] aComandoIrA = new Position[N];
	
	private ArrayList<Command> comandos = new ArrayList<Command>();

	//
	// M�todos de t�ctica
	//

	/**
	 * Determina los porteros propio y rival (actualiza array porteros[]).
	 */
	private void determinarPorteros(GameSituations sp) {
		for (int j = 0; j < N; ++j) {
			if (sp.myPlayersDetail()[j].isGoalKeeper()) {
				porteros[0] = j;
				break;
			}
		}
		for (int j = 0; j < N; ++j) {
			if (sp.rivalPlayersDetail()[j].isGoalKeeper()) {
				porteros[1] = j;
				break;
			}
		}
	}

	/**
	 * Calcula matriz dist2, de distancias al cuadrado
	 * entre jugadores (aj1) y rivalPlayers (aj2).
	 * @param aj1   Array de posiciones de jugadores.
	 * @param aj2   Array de posiciones de rivalPlayers.
	 */
	private void calcularDist2(Position[] aj1, Position[] aj2) {
		for (int j1 = 0; j1 < N; ++j1) {
			double x = aj1[j1].getX();
			double y = aj1[j1].getY();
			for (int j2 = 0; j2 < N; ++j2) {
				double dx = aj2[j2].getX() - x;
				double dy = aj2[j2].getY() - y;
				dist2[j1][j2] = dx*dx + dy*dy;
			}
		}
	}

	/**
	 * Calcula los mejores emparejamientos entre jugadores y rivalPlayers,
	 * es decir, el conjunto de mapeos (jugador, rival) que garantiza
	 * el menor tiempo para desplazarse los jugadores hasta sus rivalPlayers,
	 * dadas sus posiciones, en conjunto.
	 * El resultado est� indexado por jugador propio.
	 * @param aj1   Array de posiciones de jugadores.
	 * @param aj2   Array de posiciones de rivalPlayers (o cercanas a ellos).
	 * @param p1    �ndice del portero propio.
	 * @param p2    �ndice del portero rival.
	 * @return      Array de ints con la pareja de cada jugador.
	 */
	private int[] calcularParejas(Position[] aj1, Position[] aj2, int p1, int p2) {

		calcularDist2(aj1, aj2);

		int j1, j2;  // �ndices de jugador y rival
		double suma = 0;  // suma de cuadrados de distancias

		// los porteros se emparejan entre ellos (dummy, ya que no se usa este emparejamiento)
		mejorPareja[0][p2] = p1;
		mejorPareja[1][p1] = p2;
		// enparejamiento inicial de jugadores no-porteros (vale cualquiera biyectivo)
		for (j1 = 0, j2 = 0; j1 < N; ++j1, ++j2) {
			if (j1 == p1)
				++j1;
			if (j2 == p2)
				++j2;
			mejorPareja[0][j2] = j1;
			mejorPareja[1][j1] = j2;
			suma += dist2[j1][j1];
		}

		// busca el emparejamieto con menor suma de cuadrados

		boolean cambio;

		do {
			cambio = false;
			for (j2 = 0; j2 < N; ++j2) {
				if (j2 == p2)
					continue;

				for (j1 = 0; j1 < N; ++j1) {

					if (j1 == p1)
						continue;

					if (j1 != mejorPareja[0][j2]) {

						int j1x = mejorPareja[0][j2];
						int j2x = mejorPareja[1][j1];

						double dif = 
							(dist2[j1][j2] - dist2[j1x][j2]) + 
							(dist2[j1x][j2x] - dist2[j1][j2x]);

						if (dif < 0) {
							suma += dif;

							// cambio de pareja
							mejorPareja[0][j2] = j1;
							mejorPareja[0][j2x] = j1x;
							mejorPareja[1][j1] = j2;
							mejorPareja[1][j1x] = j2x;

							cambio = true;
						}
					}
				}
			}
		} while (cambio);
		
		return mejorPareja[1];
	}

	/**
	 * Calcula la mejor posici�n destino de los jugadores
	 * dada la posici�n del bal�n y la de los rivalPlayers.
	 * Considera la mejor posici�n como la de cada rival pero
	 * algo desplazada en direcci�n al bal�n.
	 * El resultado est� indexado por jugador rival.
	 * @param ballPosition Posici�n del bal�n.
	 * @param aj2   Array de posiciones de rivalPlayers.
	 * @param desp  Desplazamiento m�ximo.
	 * @return      Array de posiciones destino pr�ximas a los rivalPlayers.
	 */
	private Position[] calcularPosProxRiv(Position balon, Position[] aj2, double desp) {

		Position[] pos = new Position[N];

		for (int j2 = 0; j2 < N; ++j2) {
			pos[j2] = aj2[j2].moveAngle(aj2[j2].angle(balon), desp, aj2[j2].distance(balon));
		}

		return pos;
	}

	/**
	 * Calcula las matrices ampDisparo y dirDisparo, 
	 * de amplitudes horizontales y direcciones de disparo  
	 * hacia la porter�a rival. Ignora posiciones en el campo propio.
	 * ampDisparo es el �ngulo m�ximo entre postes no controlado por el portero,
	 * (ignorando otros rivalPlayers) y dirDisparo es la direcci�n central en ese �ngulo.
	 * @param sp    Situaci�n del partido.
	 */
	private void calcularDisparos(GameSituations sp) {
		for (int j1 = 0; j1 < N; ++j1) {
			Position pj = sp.myPlayers()[j1];
			if (0 < pj.getY()) {
				// s�lo jugadores en campo rival
				double alfaIzq = pj.angle(Constants.posteIzqArcoSup);
				double alfaDer = pj.angle(Constants.posteDerArcoSup);
				double alfaPtro = pj.angle(sp.rivalPlayers()[porteros[1]]);
				double distPtro = pj.distance(sp.rivalPlayers()[porteros[1]]);
				if (distPtro < 0.1)
					distPtro = 0.1;  // acota Constants.DISTANCIA_CONTROL_BALON_PORTERO / distPtro
				double alfaPtroLat = Math.asin(Constants.DISTANCIA_CONTROL_BALON_PORTERO / distPtro);
				double alfaPtroIzq = alfaPtro + alfaPtroLat;
				double alfaPtroDer = alfaPtro - alfaPtroLat;
				double alfaMed = (alfaIzq + alfaDer) / 2;
				if (alfaPtro > alfaMed) {
					if (alfaPtroDer > alfaDer)
						alfaIzq = alfaPtroDer;
					else
						alfaIzq = alfaDer;
				} else {
					if (alfaIzq > alfaPtroIzq)
						alfaDer = alfaPtroIzq;
					else
						alfaDer = alfaIzq;
				}
				ampDisparo[j1] = alfaIzq - alfaDer;
				if (0 < ampDisparo[j1])
					dirDisparo[j1] = (alfaIzq + alfaDer) / 2;
				else
					dirDisparo[j1] = pj.angle(Constants.centroArcoSup);
			} else {
				ampDisparo[j1] = 0;
				dirDisparo[j1] = pj.angle(Constants.centroArcoSup);
			}
		}
	}

	/**
	 * Calcula mejor posici�n para el portero.
	 * Considera la posici�n del bal�n como origen del peligro,
	 * y decide la posici�n del portero minimizando 
	 * el/los �ngulos de porter�a no cubiertos por �l.
	 * @param sp    Situaci�n del partido.
	 * @return      Nueva posici�n para el portero.
	 */
	private Position posPortero(GameSituations sp) {
		Position resp;
		Position b1 = new Position(sp.ballPosition());
		
		if (b1.getY() <= -Constants.LARGO_CAMPO_JUEGO/2) {
			// bal�n en l�nea de banda de mi porter�a o detr�s
			if (b1.getX() < 0)
				resp = new Position(Constants.posteIzqArcoInf);
			else
				resp = new Position(Constants.posteDerArcoInf);
		} else {
			// bal�n delante de la l�nea de banda de mi porter�a
			double alfaPosteIzq = b1.angle(Constants.posteIzqArcoInf);
			double alfaPosteDer = b1.angle(Constants.posteDerArcoInf);
			double alfaPosteMediatriz = (alfaPosteIzq + alfaPosteDer) / 2;
			
			Position b2 = Position.Intersection(
					b1, b1.moveAngle(alfaPosteMediatriz, 1),
					Constants.posteIzqArcoInf, Constants.posteDerArcoInf);

			Position b3 = b2.moveAngle(-alfaPosteMediatriz, Constants.DISTANCIA_CONTROL_BALON_PORTERO);
			
			int[] recups = sp.getRecoveryBall();
			if (recups.length > 1) {
				// obtiene coordenadas ballPosition en instante recuperaci�n
				double[] posRecup = sp.getTrajectory(recups[0]);
				for (int i = 1; i < recups.length; i++) {
					if (i == porteros[0])
					// ordena al portero que recupere
					b3 = new Position(posRecup[0], posRecup[1]);
					break;
				}
			}

			resp =  b3;
		}
		
		return resp;
	}

	private void actualizarDatosEntradaIteracion(GameSituations sp) {
		balon1 = sp.ballPosition();
		alturaBalon1 = sp.ballAltitude();
	}
	
	private void actualizarDatosSalidaIteracion(GameSituations sp) {
		balon0 = balon1;
		alturaBalon0 = alturaBalon1;
	}

	/**
	 * Devuelve el n�mero aproximado de iteraciones hasta que el bal�n 
	 * llegue a una altura, en la parte descendente de la par�bola.
	 * @param p0    Altura z anterior del bal�n
	 * @param p1    Altura z actual del bal�n
	 * @param pfin  Altura final del bal�n
	 * @return      Tiempo hasta que ocurra (n�mero de iteraciones, contando desde ahora)
	 */
	private double t_z(double p0, double p1, double pfin) {
		// despeja t en la f�rmula p(t) = 1/2*a*t^2 + v1*t + p1 = pfin
		double a = -Constants.G;  // aceleraci�n eje z
		double v1 = (p1 - p0) * (1 + a/2);  // estimaci�n velocidad inicial eje z (en bz1)
		double discr = v1*v1 - 2*a*(p1 - pfin);
		double t = 0;
		
		if (0 < discr)
			t = (-v1 - Math.sqrt(discr)) / a;
		
		return t;
	}
	
	/**
	 * Devuelve el punto aproximado de rebote del bal�n en el campo.
	 * @param p0    Posici�n xy anterior del bal�n
	 * @param p1    Posici�n xy actual del bal�n
	 * @param t     Tiempo hasta que ocurra (n�mero de iteraciones, contando desde ahora)
	 * @return
	 */
	private Position posRebote(Position p0, Position p1, double t) {
		// calcula p(t) = 1/2*a*t^2 + v1*t + p1, descompuesta por cada componente
		double a = Constants.FACTOR_DISMINUCION_VEL_BALON_AIRE - 1;  // aceleraci�n ejes x e y
		double vx1 = (p1.getX() - p0.getX()) * (1 + a/2);  // estim. velocidad inicial eje x (en p1)
		double vy1 = (p1.getY() - p0.getY()) * (1 + a/2);  // estim. velocidad inicial eje y (en p1)
		double x = a*t*t/2 + vx1*t + p1.getX();
		double y = a*t*t/2 + vy1*t + p1.getY();
		
		Position imp = new Position(x, y);
		return imp;
	}
	
	/**
	 * Devuelve el n�mero aproximado de iteraciones hasta que el bal�n raso se para.
	 * @param p0    Altura z anterior del bal�n
	 * @param p1    Altura z actual del bal�n
	 * @return      Tiempo hasta que ocurra (n�mero de iteraciones, contando desde ahora)
	 */
	private double t_xy(Position p0, Position p1) {
		// despeja t en la f�rmula v(t) = a*t + v1 = 0
		double a = Constants.FACTOR_DISMINUCION_VEL_BALON_SUELO - 1;  // aceleraci�n plano xy
		double v1 = p0.distance(p1) * (1 + a/2);  // estim. velocidad inicial plano xy (en p1)
		double t = -v1 / a;
		
		return t;
	}
	
	/**
	 * Devuelve la situaci�n aproximada del bal�n en un tiro raso.
	 * @param p0    Posici�n xy anterior del bal�n
	 * @param p1    Posici�n xy actual del bal�n
	 * @param t     Tiempo (n�mero de iteraciones, contando desde ahora)
	 * @return      Posici�n aproximada donde se detendr� el bal�n
	 */
	private Position posRasa(Position p0, Position p1, double t) {
		// calcula p(t) = 1/2*a*t^2 + v1*t + p1, descompuesta por cada componente
		double a = Constants.FACTOR_DISMINUCION_VEL_BALON_SUELO - 1;  // aceleraci�n ejes x e y
		double vx1 = (p1.getX() - p0.getX()) * (1 + a/2);  // estim. velocidad inicial eje x (en p1)
		double vy1 = (p1.getY() - p0.getY()) * (1 + a/2);  // estim. velocidad inicial eje y (en p1)
		double x = a*t*t/2 + vx1*t + p1.getX();
		double y = a*t*t/2 + vy1*t + p1.getY();
		
		Position imp = new Position(x, y);
		return imp;
	}

	/**
	 * Muestra una matriz de mejores posiciones de pase,
	 * para depurar m�todo mejoresPosicionesPase.
	 * @param msimplex      Matrix de simplex.
	 */
	private void printMejoresPosicionesPase(double[][] msimplex) {
		int simplexFil = N + 4 + 1;  // rivalPlayers (N) + bandas (4) + funci�n (1)
		int simplexCol =  3 + simplexFil + 1;  // posicion_bondad (3) + filas (simplexFil) + term_indep (1)
		for (int f = 0; f < simplexFil; ++f) {
			for (int c = 0; c < simplexCol; ++c) {
				//System.out.print(msimplex[f][c] + (c < simplexCol-1 ? "\t" : "\n"));
			}
		}
		//System.out.println("--------");
	}

	/**
	 * Devuelve un array con las mejores posiciones de pase a cada jugador.
	 * Usa el m�todo del simplex, considerando, para cada jugador, el politopo
	 * que lo contiene y est� limitado con caras con pendiente 1 que pasan por
	 * las mediatrices del jugador y cada uno de sus rivalPlayers. Hay 4 caras
	 * adicionales, que pasan por las bandas.
	 * WARNING: No ha sido suficientemente testeado.
	 * @param sp    Situaci�n del partido
	 * @return      Array con N elementos 3D: mejor posici�n (x,y) y su calidad (z) 
	 */
	private double[][] mejoresPosicionesPase(Position[] aj1, Position[] aj2) {
		double[][] ampp = new double[N][3];
		int simplexFil = N + 4 + 1;  // rivalPlayers (N) + bandas (4) + funci�n (1)
		int simplexCol =  3 + simplexFil + 1;  // posicion_bondad (3) + filas (simplexFil) + term_indep (1)
		double[][] msimplex = new double[simplexFil][simplexCol];
		
		// calcula la mejor posici�n de pase a cada jugador
		for (int j1 = 0; j1 < N; ++j1) {
			
			// puesta a cero
			for (int f = 0; f < simplexFil; ++f)
				for (int c = 0; c < simplexCol; ++c)
					msimplex[f][c] = 0;
			
			// caras del politopo asociadas a los rivalPlayers
			for (int j2 = 0; j2 < N; ++j2) {
				// punto medio entre jugador y rival, 
				// con correcci�n sistema referencia (cambio de variable para que x,y>=0)
				Position med = new Position(
						(aj1[j1].getX() + aj2[j2].getX()) / 2 + Constants.ANCHO_CAMPO_JUEGO/2,
						(aj1[j1].getY() + aj2[j2].getY()) / 2 + Constants.LARGO_CAMPO_JUEGO/2);
				// vector unitario del jugador al rival
				Position rel = new Position(
						aj2[j2].getX() - aj1[j1].getX(),
						aj2[j2].getY() - aj1[j1].getY());
				double lng = Math.sqrt(rel.getX()*rel.getX() + rel.getY()*rel.getY());
				if (0 < lng)
					rel = new Position(rel.getX()/lng, rel.getY()/lng);  // convierte rel en unitario
				else
					rel = new Position(0, -1);  // si coinciden, considero que mi jugador est� algo m�s arriba

				msimplex[j2][0] = rel.getX();  // x
				msimplex[j2][1] = rel.getY();  // y
				msimplex[j2][2] = 1;  // z
				msimplex[j2][3+j2] = 1;  // variable de holgura
				msimplex[j2][3+simplexFil] = (med.getX()*rel.getX() + med.getY()*rel.getY());  // term_indep
			}
			
			// caras del politopo asociadas a las bandas
			// banda inferior
			msimplex[N+0][0] = 0;  // x
			msimplex[N+0][1] = -1;  // y
			msimplex[N+0][2] = 1*10;  // z (sesgo hacia delante)
			msimplex[N+0][3+N+0] = 1;  // variable de holgura
			// banda superior
			msimplex[N+1][0] = 0;  // x
			msimplex[N+1][1] = 1;  // y
			msimplex[N+1][2] = 1;  // z
			msimplex[N+1][3+N+1] = 1;  // variable de holgura
			msimplex[N+1][3+simplexFil] = Constants.LARGO_CAMPO_JUEGO;  // term_indep
			// banda izquierda
			msimplex[N+2][0] = -1;  // x
			msimplex[N+2][1] = 0;  // y
			msimplex[N+2][2] = 1;  // z
			msimplex[N+2][3+N+2] = 1;  // variable de holgura
			// banda derecha
			msimplex[N+3][0] = 1;  // x
			msimplex[N+3][1] = 0;  // y
			msimplex[N+3][2] = 1;  // z
			msimplex[N+3][3+N+3] = 1;  // variable de holgura
			msimplex[N+3][3+simplexFil] = Constants.ANCHO_CAMPO_JUEGO;  // term_indep
			
			// funci�n: z
			msimplex[simplexFil-1][2] = -1;  // z
			msimplex[simplexFil-1][3+N+4] = 1;  // variable de holgura
			
			// Ahora la matriz ya contiene el problema en forma est�ndar
			
			boolean hecho = false;
			do {
				//printMejoresPosicionesPase(msimplex);
				// selecci�n de la columna pivote
				hecho = true;
				int cp = 0;
				for (int c = 0; c < simplexCol-1; ++c) {
					if (msimplex[simplexFil-1][c] < 0)
						hecho = false;
					if (msimplex[simplexFil-1][c] < msimplex[simplexFil-1][cp])
						cp = c;
				}
				if (!hecho) {
					// selecci�n del pivote
					int fp = -1;
					boolean pivote = false;
					double r = -1;
					for (int f = 0; f < simplexFil-1; ++f) {
						if (0 < msimplex[f][cp]) {  // ERROR
							double r2 = msimplex[f][simplexCol-1] / msimplex[f][cp];
							if (!pivote || r2 < r) {
								fp = f;
								r = r2;
								pivote = true;
							}
						}
					}
					
					// borrado de la columna pivote
					for (int f = 0; f < simplexFil; ++f) {
						if (f != fp && msimplex[f][cp] != 0) {
							for (int c = 0; c < simplexCol; ++c) {
								if (c != cp) {
									msimplex[f][c] = 
										msimplex[f][c] * msimplex[fp][cp] -
										msimplex[fp][c] * msimplex[f][cp];
								}
							}
							msimplex[f][cp] = 0;
						}
					}
				}
			} while (!hecho);
			
			// Obtenci�n del punto �ptimo
			for (int c = 0; c < 3; ++c) {
				for (int f = 0; f < N + 4; ++f) {
					if (msimplex[f][c] != 0) {
						ampp[j1][c] = msimplex[f][simplexCol-1] / msimplex[f][c];
						break;
					}
				}
			}
			
			// correcci�n sistema referencia (deshace cambio de variable)
			ampp[j1][0] -= Constants.ANCHO_CAMPO_JUEGO/2;
			ampp[j1][1] -= Constants.LARGO_CAMPO_JUEGO/2;
			/*
			System.out.println("Jugador " + j1 + ":");
			System.out.println("  x: " + ampp[j1][0]);
			System.out.println("  y: " + ampp[j1][1]);
			System.out.println("  z: " + ampp[j1][2]);
			*/
		}
		
		return ampp;
	}
	
	public List<Command> execute(GameSituations sp) {
	actualizarDatosEntradaIteracion(sp);

	int iter = sp.iteration();

	if (iter == 0)
		determinarPorteros(sp);

	comandos.clear();

	// realiza emparejamientos (un jugador por rival, excepto porteros)
	//   para decidir la posici�n de cada jugador
		if (iter % (Constants.ITERACIONES_GOLPEAR_BALON * 10) == 0
				|| sp.isStarts() || sp.isRivalStarts())
			calcularDist2(sp.myPlayers(), sp.rivalPlayers());
		Position[] posProxRivales = calcularPosProxRiv(sp.ballPosition(), sp.rivalPlayers(), 5);
		int[] parejas = calcularParejas(sp.myPlayers(), posProxRivales, porteros[0], porteros[1]);
		for (int j = 0; j < N; ++j) {
			if (j == porteros[0])
				continue;
			aComandoIrA[j] = new Position(posProxRivales[parejas[j]]);
		}
		
		// subo los 3 m�s cercanos a la porter�a rival 
		//   en caso de que est�n m�s abajo que el bal�n
		int[] jcpr = Constants.centroArcoSup.nearestIndexes(sp.myPlayers());
		for (int j = 0; j < 3; ++j) {
			if (sp.myPlayers()[jcpr[j]].getY() < sp.ballPosition().getY()) {
				aComandoIrA[jcpr[j]] = Position.Intersection(
						sp.myPlayers()[jcpr[j]],
						new Position(0, Constants.LARGO_CAMPO_JUEGO/2+Constants.LARGO_AREA_CHICA), // <- valor "y" arbitrario aceptable
						new Position(-Constants.ANCHO_CAMPO_JUEGO, sp.ballPosition().getY()),
						new Position(Constants.ANCHO_CAMPO_JUEGO, sp.ballPosition().getY()));
			}
		}
		
		// muevo portero
		aComandoIrA[porteros[0]] = posPortero(sp);

		// muevo jugadores (posiblemente tambi�n portero)
		//   en funci�n del tipo de tiro (raso/par�b�lico)
		if (alturaBalon0 == 0 && alturaBalon1 == 0) {  // bal�n raso
			double t = t_xy(balon0, balon1);
			// acerco jugador(es) a 4 puntos de la trayectoria prevista del ba�n
			for (int n = 1; n < 4; ++n) {
				if (n == porteros[0])
					continue;
				Position pos = posRasa(balon0, balon1, t/n);
				int[] jc = pos.nearestIndexes(sp.myPlayers());
				aComandoIrA[jc[0]] = sp.ballPosition();
			}
		} else {  // bal�n describiendo par�bola
			// muevo al punto del primer rebote del bal�n a quien est� m�s cerca
			double t = t_z(alturaBalon0, alturaBalon1, 0);
			Position rebote = posRebote(balon0, balon1, t);
			int[] jc = rebote.nearestIndexes(sp.myPlayers());
			if (jc[0] != porteros[0])
				aComandoIrA[jc[0]] = rebote;
		}
		
		calcularDisparos(sp);
		int[] aRem = sp.canKick();
		if (0 < aRem.length) {
			int jm = 1;  // jugador con mayor amplitud de disparo
			for (int j = 1; j < N; ++j) {
				if (ampDisparo[jm] < ampDisparo[j])
					jm = j;
			}
			for (int j : aRem) {
				if (j == jm && sp.ballPosition().distance(Constants.centroArcoSup) < Constants.LARGO_CAMPO_JUEGO/4) {
					comandos.add(new CommandHitBall(j, dirDisparo[j] * 180 / Math.PI, 1.0, 5.0));
				} else {
					double avert = (j == porteros[0] || sp.isStarts() ? 40.0 : 5.0);
					comandos.add(new CommandHitBall(j, sp.myPlayers()[jm], 1.0, avert));
				}
			}
		}
		
		int[] recups = sp.getRecoveryBall();
		if (recups.length > 1) {
			// obtiene coordenadas ballPosition en instante recuperaci�n
			double[] posRecup = sp.getTrajectory(recups[0]);
			for (int i = 1; i < recups.length; i++) {
				if (i != porteros[0])
				// ordena al jugador recuperador m�s cercano (no portero) que recupere
				comandos.add(new CommandMoveTo(recups[i], new Position(posRecup[0], posRecup[1])));
			}
		}

		// filtrado de movimientos peque�os
		for (int j = 0; j < N; ++j) {
			if (sp.myPlayersDetail()[j].getSpeed()/itersSegundo
					<= sp.myPlayers()[j].distance(aComandoIrA[j]))
				comandos.add(new CommandMoveTo(j, aComandoIrA[j]));
		}

		actualizarDatosSalidaIteracion(sp);
		
		return comandos;
	}
}

