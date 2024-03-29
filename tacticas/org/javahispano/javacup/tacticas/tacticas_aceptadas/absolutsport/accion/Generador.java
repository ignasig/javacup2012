package org.javahispano.javacup.tacticas.tacticas_aceptadas.absolutsport.accion;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.javahispano.javacup.model.trajectory.AbstractTrajectory;
import org.javahispano.javacup.model.command.CommandHitBall;
import org.javahispano.javacup.model.command.CommandMoveTo;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.model.trajectory.AirTrajectory;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.absolutsport.tactica.utilidades.Entorno;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.absolutsport.tactica.utilidades.Jugador;
import org.javahispano.javacup.tacticas.tacticas_aceptadas.absolutsport.tactica.utilidades.Rival;

/**
 * Clase utilizada para generar todas las acciones que puede realizar el jugador que se encuentra en contacto con 
 * el bal�n.
 * 
 * @author Christian Onwuzor Mart�n (chr -> airchris01@yahoo.es)
 */
public class Generador {

	private int indMiJugAlcanzaAntesBalon = 0;
	private Position posMiJugAlcanzaAntesBalon = null;
	
    // M�todo que calcula la Accion avanzar de un jugador.
    // Si no puede avanzar devuelve un null.
    public AccionAvanzar avanzar(GameSituations sp, Jugador jugador, Rival[] arrayRivales) {
    	
    	// Variable en la que devolveremos el resultado.
    	AccionAvanzar accion = null;
        
    	try {
    	
	        // Dado que vamos a avanzar, solamente nos interesa calcular si el jugador que avanza llega antes al bal�n
	        // que los rivalPlayers.
	        Jugador[] arrayJugadores = new Jugador[] {jugador};
	    	
	        // Calculamos el ángulo del avance.
	        Double angulo = avanzarRango (sp, jugador, arrayJugadores, arrayRivales, 30, 0, jugador.getFuerzaGolpeoAvanzar());
	        
	        // Si podemos avanzar, instanciamos la acción adecuada.
	        if (angulo != null) {
	
	            accion = new AccionAvanzar(new CommandHitBall(
	                    							jugador.getIndice(),
	                    							angulo,
	                    							jugador.getFuerzaGolpeoAvanzar(),
	                    							false),
	                    					new CommandMoveTo(jugador.getIndice(), posMiJugAlcanzaAntesBalon));
	        }
    	}
    	catch (Exception e) {
    		System.err.println("Error en Generador.avanzar: " + e);
    		accion = null;
    	}

        return accion;
    }

    
    // Método que calcula la acción de pase del jugador a todos los jugadores que
    // pueda enviarles la pelota. Devuelve un listado de arrays en los que en la
    // primera posición se sencuentra el Jugador al que le envía la pelota y en la
    // segunda posición la acción a realizar.
    public AccionPasar pasar(GameSituations sp, Jugador jugador,
                             Jugador[] arrayJugadores, Rival[] arrayRivales) {
    	
    	AccionPasar accionPasar = null;
    	
    	try {
    	
	    	// Calculamos si nos encontramos en una acción ofensiva para cargar la alineación ofensiva.
	    	boolean accionOfensiva = sp.ballPosition().getY() > 10;
	    	String pases = accionOfensiva ? Entorno.valoracionAtaque[jugador.getIndice()] : 
	    									Entorno.valoracionNormal[jugador.getIndice()];
	    	
	    	// Variables que utilizaremos en el caso de estar en una acción ofensiva para calcular el mejor pase.
	    	Jugador jugadorAsignado = null;
	    	double anguloXYAsignado = 0;
	    	double anguloZAsignado = 0;
	    	double fuerzaAsignado = 0;
	    	boolean puertaVaciaAsignado = false;
	    	
	    	// Recorremos todos los jugadores a los que podemos enviarles la pelota por estricto orden de preferencia.
	    	StringTokenizer st = new StringTokenizer(pases, "|");
	    	while (st.hasMoreTokens() && accionPasar == null) {
	    	
	    		String destinoPase = st.nextToken();
	    		if (destinoPase != null && destinoPase.length() > 0) {
	    			
	    			Jugador jugadorDestino = arrayJugadores[Integer.parseInt(destinoPase)];
	    			
	    			if (jugadorDestino.getIndice() != jugador.getIndice() &&
	    				cumpleCondicionesPase(sp, jugador, jugadorDestino)) {
	    			
	    				// Indicamos con esta variable que vamos a buscar un pase adelantado.
	    				int paseAdelantado = 1;
	    				
	    				do {
	    				
							// Calculamos los par�metros necesarios para llamar al m�todo que calcula si el pase llegar�
							// a su destino.
			                double fuerza;
			                double anguloZ;
			                double anguloXY;
			                Position posicionPase;
			                
			                // Si es un pase adelantado buscamos pas�rsela un poco por delante del jugador.
			                if (paseAdelantado > 0) {
			                	
			                	posicionPase = new Position(sp.myPlayers()[jugadorDestino.getIndice()].getX(),
									    					Math.min((sp.myPlayers()[jugadorDestino.getIndice()].getY() + 5),
									    							 (Constants.LARGO_CAMPO_JUEGO / 2)));
			                }
			                // Si no es un pase adelantado cogemos el �ngulo del pase sobre el jugador.
			                else {
			                	posicionPase = new Position(sp.myPlayers()[jugadorDestino.getIndice()]);
			                }
			                
			                // Decrementamos la variable que cuenta si es un pase adelantado para salirnos del bucle.
			                paseAdelantado --;
			                
			                // Calculamos el �ngulo XY de la posici�n del pase.
		                	anguloXY = Math.toDegrees(sp.ballPosition().angle(posicionPase));
	
			
			                // Obtenemos la distancia del pase.
			                double distanciaPase = sp.myPlayers()[jugador.getIndice()].distance(posicionPase);
			
			                // Llegamos con un paso raso
			                if (distanciaPase < jugador.getDistanciaGolpeoBajo()) {
			                    anguloZ = 0;
			                    
			                    // Si la distancia con un pase raso es mayor que la mitad de la distancia que alcanza 
			                    // un jugador, le aplicamos la fuerza m�xima para que llegue antes siempre y cuando
			                    // no sea un pase adelantado.
			                    if (paseAdelantado < 0 &&
			                    	distanciaPase >= jugador.getDistanciaGolpeoBajo() / 2 ||
			                    	destinoDentroArea(sp, jugadorDestino)) {
			                    	
			                    	fuerza = Entorno.FUERZA_MAXIMA_GOLPEO;
			                    }
			                    // En caso contrario, le asignamos la fuerza justa.
			                    else {
			                    	fuerza = jugador.getFuerzaGolepoBajo(distanciaPase);
			                    }
			                }
			                else {
			                    anguloZ = 30;
			                    fuerza = ((Entorno.FUERZA_MAXIMA_GOLPEO * distanciaPase) / jugador.getDistanciaGolpeoAlto()) + 0.2;
			                }
			                
			                // Si el pase llega a su destino o a un compa�ero de equipo, lo realizamos.
							if (alcanzoAntesBalon(sp, jugador, 
									  			  arrayJugadores, arrayRivales,  
									  			  anguloXY, anguloZ, fuerza)) {
			
								// Si no estamos en una acci�n ofensiva devolvemos el primer pase encontrado.
								if (!accionOfensiva) {
								
									accionPasar = new AccionPasar(new CommandHitBall(
																		  				  jugador.getIndice(),
																		  				  anguloXY,
																		  				  fuerza,
																		  				  anguloZ),
																  jugadorDestino,
																  sp.myPlayers()[jugadorDestino.getIndice()]);
								}
								// Si nos encontramos en una acci�n ofensiva calculamos el mejor pase.
								else {
	
									// Decrementamos el el contdor de pase adelantado ya que en este caso volveríamos a realizar la misma
									// comprobación.
									paseAdelantado --;
									
									if (jugadorAsignado != null) {
										boolean puertaVaciaActual = puertaVacia(sp, sp.myPlayers()[jugadorDestino.getIndice()]);
		
										// El destino consultado tiene tiro a puerta vacia y el anterior no. 
										if (puertaVaciaActual && !puertaVaciaAsignado) {
											
											jugadorAsignado = jugadorDestino;
									    	anguloXYAsignado = anguloXY;
									    	anguloZAsignado = anguloZ;
									    	fuerzaAsignado = fuerza;
									    	puertaVaciaAsignado = puertaVaciaActual;
										}
										// Los 2 tienen tiro a puerta vacia o ninguno de los 2 lo tiene.
										else {
											
											// Me quedo con el que este m�s cerca del centro de la porter�a.
											double distanciaAnterior = sp.myPlayers()[jugadorAsignado.getIndice()].distance(Constants.centroArcoSup);
											double distanciaActual = sp.myPlayers()[jugadorDestino.getIndice()].distance(Constants.centroArcoSup);
											if (distanciaActual < distanciaAnterior) {
		
												jugadorAsignado = jugadorDestino;
										    	anguloXYAsignado = anguloXY;
										    	anguloZAsignado = anguloZ;
										    	fuerzaAsignado = fuerza;
										    	puertaVaciaAsignado = puertaVacia(sp, sp.myPlayers()[jugadorDestino.getIndice()]);
											}
										}
									}
									// Si no habia anteriormente un jugador asignado le asignamos los datos del actual. 
									else {
										jugadorAsignado = jugadorDestino;
								    	anguloXYAsignado = anguloXY;
								    	anguloZAsignado = anguloZ;
								    	fuerzaAsignado = fuerza;
								    	puertaVaciaAsignado = puertaVacia(sp, sp.myPlayers()[jugadorDestino.getIndice()]);
									}
								}
							}
	    				} while (accionPasar == null && paseAdelantado >= 0);
	    			}
				}
	    	}
	    	
	    	// Si nos encontramos en una accion ofensiva y hemos obtenido un destino, generamos la acci�n del pase.
	    	if (accionOfensiva && jugadorAsignado != null) {
	    		
	    		accionPasar = new AccionPasar(new CommandHitBall(
		  				  											  jugador.getIndice(),
		  				  											  anguloXYAsignado,
		  				  											  fuerzaAsignado,
		  				  											  anguloZAsignado),
		  				  					  jugadorAsignado,
		  				  					  sp.myPlayers()[jugadorAsignado.getIndice()]);
	    	}
    	}
    	catch (Exception e) {
    		System.err.println("Error en Generador.pasar: " + e);
    		accionPasar = null;
    	}
    	
        return accionPasar;
    }    
    
    
    // M�todo que calcula la acci�n de tirar.
    public AccionTirar tirar(GameSituations sp, Jugador jugador, boolean calculoParaJugadorPosesion) {

    	AccionTirar accion = null;

    	try {
    		
	        // Solamente tiramos si el tiro llega a porter�a y nos encontramos dentro
	        // de la distancia designada para tirar.
	        double distanciaPorteria = sp.myPlayers()[jugador.getIndice()].distance(Constants.centroArcoSup);
	
	        boolean tiroPuertaVacia = false;
	        boolean tiroSeguro = false;
	        
	        // Si el calculo es para el jugador que tiene la posesi�n el inicio del tiro lo cogemos desde la posici�n
	        // actual del bal�n, para aumentar la precisi�n, en caso contrario cogemos la posici�n del jugador que
	        // realiza la acci�n ya que estaremos buscando la acci�n de cualquier jugador.
	        Position inicioTiro = calculoParaJugadorPosesion ? sp.ballPosition() : sp.myPlayers()[jugador.getIndice()];
	        
	        if ( jugador.getDistanciaGolpeoAlto() > distanciaPorteria && 
	        	 distanciaPorteria < Entorno.DISTANCIA_MINIMA_TIRO) {
	        	
	        	Hashtable<String, Object> tabla = null;
	        	
	        	
	        	// Realizamos una primera pasada calculando si podemos realizar un tiro seguro.
	        	tabla = calculaPosicionTiro(sp, jugador, inicioTiro, true);
	    		if (tabla != null) {
	    			tiroSeguro = true;
	    		}
	    		
	    		
	        	// Si no hemos encontrado un tiro seguro realizamos una segunda pasada con el m�todo habitual.
	        	if (tabla == null) {
	        		tabla = calculaPosicionTiro(sp, jugador, inicioTiro, false);
	        	}
	        	
	        	
	            if (tabla != null) {
	
	            	tiroPuertaVacia = puertaVacia(sp, sp.ballPosition());
	            	accion = new AccionTirar(new CommandHitBall(jugador.getIndice(),
	                                                  				   (Position)tabla.get("posicion"),
	                                                  				   (Double)tabla.get("fuerza"), //Entorno.FUERZA_MAXIMA_GOLPEO,
	                                                  				   (Double)tabla.get("anguloZ")), //jugador.getAnguloTiro()),
	                                         forzadoTiro(sp),
	                                         (Double)tabla.get("variacion"),
	                                         tiroPuertaVacia,
	                                         tiroSeguro);
	            }
	        }
    	}
    	catch (Exception e) {
    		System.err.println("Error en Generador.tirar: " + e);
    		accion = null;
    	}
    	
        return accion;
    }
    
    
    public AccionTirarTrallon tirarATrallon(GameSituations sp, Jugador jugador) {
    	
    	AccionTirarTrallon accion = null;
    	
    	try {
    		
    		accion = new AccionTirarTrallon(new CommandHitBall(jugador.getIndice(),
			  		  												Constants.centroArcoSup,
			  		  												1,
			  		  												16));
    	}
    	catch (Exception e) {
    		System.err.println("Error en Generador.tirarATrallon: " + e);
    		accion = null;
    	}
    	
    	return accion;
    }
    
    
    // M�todo que calcula la acci�n de despeje.
    public AccionDespejar despejar(GameSituations sp, Jugador jugador, Rival[] arrayRivales) {

    	// Variable en la que devolveremos el resultado.
    	AccionDespejar despejar = null;
    	
    	try {
    		
	    	boolean realizaDespeje = false;
	
	    	double anguloVertical = 45;
	    	double fuerza = (sp.ballPosition().getY() > 0 && sp.myPlayersDetail()[jugador.getIndice()].getPower() == 1) ? 0.8 : Entorno.FUERZA_MAXIMA_GOLPEO;
	    	double amplitudAngular = 80;
	    	double incremento = (sp.myPlayers()[jugador.getIndice()].getX() < 0) ? 5 : -5;
	    	double anguloInicial = 90;
	    	double angulo = anguloInicial;	
	    	
	    	boolean salir = false;
	    	boolean otroLado = false;
	    	long contador = 0;
	    	
	
	    	// Buscamos despejar el balón en primer lugar hacia los 4 jugadores más adelantados.
	    	int[] excluir = {0, 1, 2, 3, 4, 5, 7};
	    	int[] jugadoresCercanos = sp.ballPosition().nearestIndexes(sp.myPlayers(), excluir);
	    	
	    	int i = 0;
	    	do {
	    		
	    		Position j = sp.myPlayers()[jugadoresCercanos[i]];
	    		if (j.getY() > sp.ballPosition().getY()) {
	    			
		    		if (sp.ballPosition().distance(j) > (jugador.getDistanciaGolpeoAlto() / 2)) {
		    			
		    			angulo = Math.toDegrees(sp.ballPosition().angle(j));
		    			realizaDespeje = alcanzoAlturaDespeje(sp, jugador, arrayRivales, angulo, anguloVertical, fuerza);
		    		}
	    		}
	    		
	    		i++;
	    		
	    	} while(i<jugadoresCercanos.length && !realizaDespeje);
	    	
	    	
	    	// Si no hemos podido despejar hacie los jugadores indicados, buscamos un despeje normal.
	    	if (!realizaDespeje) {
	    		
		    	angulo = anguloInicial;
		        do {
		        	realizaDespeje = alcanzoAlturaDespeje(sp, jugador, arrayRivales, angulo, anguloVertical, fuerza);
		
		    		// Si no podemos realizar el despeje para los datos obtenidos.
		    		if (!realizaDespeje) {
		    			
		    			// Si no hemos superado la amplitud angular, realizamos un nuevo incremento. 
		    			if ((Math.abs(incremento) * contador)  < amplitudAngular) {
		    				
		    				// Si tenemos que buscar el �ngulo equivalente al otro lado.
		    				if (otroLado) {
		    					angulo = anguloInicial - (incremento * contador);
		    				}
		    				// Si no tenemos que buscar el �ngulo al otro lado.
		    				else {
		    					
		    		    		// Aumemtamos el contador de la iteraci�n.
		    		    		contador ++;
		    		    		
		    					angulo = (incremento * contador) + anguloInicial;
		    				}
		    			}
		    			else {
		    				salir = true;
		    			}
		    		}
		    		
		    		// Cambiamos el valor de la variable que controla si hemos buscado el �ngulo del otro lado.
		    		otroLado = !otroLado;
		    		
		    	} while (!realizaDespeje && !salir);
	    	}
	    	
	    	
	    	// Si hemos encontrado un �ngulo de despeje, realizamos el despeje.
	    	if (realizaDespeje) {
	    		despejar = new AccionDespejar(new CommandHitBall(jugador.getIndice(),
	                    					  				  		  angulo,
	                    					  				  		  fuerza,
	                    					  				  		  anguloVertical));
	    	}
	    	// En caso contrario despejamos hacia el centro de la porter�a.
	    	else {
	    		
	    		// AQUI QUIZAS HABR�A QUE DESPEJAR HACIA UN LADO U OTRO
	    		despejar = new AccionDespejar(new CommandHitBall(jugador.getIndice(),
	      					  				  				  		  Constants.centroArcoSup,
	      					  				  				  		  fuerza,
	      					  				  				  		  anguloVertical));
	    	}
    	}
    	catch (Exception e) {
    		System.err.println("Error en Generador.despejar: " + e);
    		despejar = null;
    	}
    	
        return despejar;
    }
    
    
    // M�todo que calcula acci�n de realizar un regate.
    public AccionRegatear regatear(GameSituations sp, Jugador jugador, Rival[] arrayRivales) {

        AccionRegatear accion = null;
        
        try {
        
	        Double anguloRegate = null;
	
	        // Dado que vamos a avanzar, solamente nos interesa calcular si el jugador que avanza llega antes al bal�n
	        // que los rivalPlayers.
	        Jugador[] arrayJugadores = new Jugador[] {jugador};
	        
	        anguloRegate = avanzarRango (sp, jugador, arrayJugadores, arrayRivales, 180, 0, jugador.getFuerzaGolpeoAvanzar());
	        	
	        // Si encontramos un �ngulo para realizar el regate instanciamos la acci�n y salimos del bucle.
	        if (anguloRegate != null) {
	        	
	        	// Recuperamos el �ngulo del regate y lo formateamos por si llegara con valores negativos.
	        	//double anguloRegateFormateado = Math.toDegrees(anguloRegate);
	        	double anguloRegateFormateado = anguloRegate;
	        	if (anguloRegateFormateado < 0) {
	        		anguloRegateFormateado = anguloRegateFormateado + 360;
	        	}
	        	
	        	// Calculamos si es un regate hacia adelante.
	        	boolean regateHaciaAdelante = anguloRegateFormateado > 0 && anguloRegateFormateado < 180;
	        	
	        	// Calculamos si es un regate hacia la porteria, para ello necesitamos los �ngulos m�ximos y m�nimos. 
				double anguloMax = (sp.ballPosition().getX() < 0) ? Math.toDegrees(sp.ballPosition().angle(Constants.posteIzqArcoSup)) :
															 180;
				double anguloMin = (sp.ballPosition().getX() < 0) ? 0 :
															 Math.toDegrees(sp.ballPosition().angle(Constants.posteDerArcoSup));
				if (anguloMax < 0) {
					anguloMax = anguloMax + 360;
				}
				if (anguloMin < 0) {
					anguloMin = anguloMin + 360;
				}
				
				boolean regateHaciaPorteria = anguloRegateFormateado < anguloMax && anguloRegateFormateado > anguloMin;
	        	
	        	accion = new AccionRegatear(new CommandHitBall(
													jugador.getIndice(),
													anguloRegate,
													jugador.getFuerzaGolpeoAvanzar(),
													false),
											new CommandMoveTo(jugador.getIndice(), posMiJugAlcanzaAntesBalon),
											regateHaciaAdelante,
											regateHaciaPorteria
											);
	        }
        }
    	catch (Exception e) {
    		System.err.println("Error en Generador.regatear: " + e);
    		accion = null;
    	}
    	
        return accion;
    }
    
    
    public AccionPasarAlHueco pasarAlHueco (GameSituations sp, Jugador jugador,
    									    Jugador[] arrayJugadores, Rival[] arrayRivales,
    									    Double xRestringida) {
    	
    	AccionPasarAlHueco paseAlHueco = null;
    	
    	try {    	
    	
	    	double xMax = (xRestringida != null) ? xRestringida : (Constants.ANCHO_CAMPO_JUEGO / 2) - 1.5;
	    	double yMax = yMaxPaseAlHueco(sp, jugador);
	    	double yMin = (sp.ballPosition().getY() > 0) ? sp.ballPosition().getY()-20 : sp.ballPosition().getY();
	    	
	    	
	    	Position posInicial = new Position (0, yMax);
	    	Position posPase = new Position(posInicial);
	        	
	    	double incremento = (sp.myPlayers()[jugador.getIndice()].getX() > 0) ? 2.5 : -2.5;
	    	boolean salir = false;
	    	boolean buscaOtroLado = true;
	    	
	    	// Buscamos el pase movimiendonos sobre el ejeX.
	    	do {
	    		
	    		// Calculamos los par�metros necesarios para llamar al m�todo que calcula si el pase llegar�
				// a su destino.
	            double fuerza;
	            double anguloZ;
	            double anguloXY = Math.toDegrees(sp.myPlayers()[jugador.getIndice()].angle(posPase));
	
	            // Obtenemos la distancia del pase.
	            double distanciaPase = sp.myPlayers()[jugador.getIndice()].distance(posPase);
	            
	            // Llegamos con un paso raso
	            if (distanciaPase < jugador.getDistanciaGolpeoBajo()) {
	                anguloZ = 0;
	                fuerza = jugador.getFuerzaGolepoBajo(distanciaPase);
	            }
	            else {
	                anguloZ = 30;
	                fuerza = (Entorno.FUERZA_MAXIMA_GOLPEO * distanciaPase) / jugador.getDistanciaGolpeoAlto();
	            }    		
	    		
	            
	            boolean alcanzoBalon = false;
	            
	            // Comprobamos que no hagamos un pase muy corto para no perder ciclos parados.
	            if (distanciaPase > 2) {
	    		
			        // Si el pase llega a su destino o a un compa�ero de equipo, lo realizamos.
					alcanzoBalon = alcanzoAntesBalon(sp, jugador, 
					  			  					 arrayJugadores, arrayRivales,  
					  			  					 anguloXY, anguloZ, fuerza);
	            }
				
				if (alcanzoBalon){
				
					// Recuperamos el �ngulo del pase y lo formateamos por si fuera negativo.
					double anguloPaseFormateado = (anguloXY < 0) ? (anguloXY + 360) : anguloXY; 
					
					// Calculamos si es un pase hacia adelante.
					boolean paseHaciaAdelante = anguloPaseFormateado > 0 && anguloPaseFormateado < 180;
					
					// Calculamos si es un pase hacia el area.
					double xAreaIzquierda = -(Constants.ANCHO_CAMPO_JUEGO/2 - Constants.LARGO_AREA_GRANDE/2);
					double xAreaDerecha = (Constants.ANCHO_CAMPO_JUEGO/2 - Constants.LARGO_AREA_GRANDE/2);
					double yArea = (Constants.LARGO_CAMPO_JUEGO /2) - Constants.ANCHO_AREA_GRANDE;
					double anguloMin, anguloMax;
					if (sp.ballPosition().getX() < xAreaIzquierda) {
						anguloMin = Math.toDegrees(sp.ballPosition().angle(new Position(xAreaDerecha, yArea)));
						anguloMax = Math.toDegrees(sp.ballPosition().angle(new Position(xAreaIzquierda, Constants.LARGO_CAMPO_JUEGO /2)));
					}
					else if (sp.ballPosition().getX() < xAreaDerecha) {
						anguloMin = Math.toDegrees(sp.ballPosition().angle(new Position(xAreaDerecha, Constants.LARGO_CAMPO_JUEGO /2)));
						anguloMax = Math.toDegrees(sp.ballPosition().angle(new Position(xAreaIzquierda, Constants.LARGO_CAMPO_JUEGO /2)));
					}
					else {
						anguloMin = Math.toDegrees(sp.ballPosition().angle(new Position(xAreaDerecha, Constants.LARGO_CAMPO_JUEGO /2)));
						anguloMax = Math.toDegrees(sp.ballPosition().angle(new Position(xAreaIzquierda, yArea)));
					}
					if (anguloMin < 0) {
						anguloMin = anguloMin + 360;
					}
					if (anguloMax < 0) {
						anguloMax = anguloMax + 360;
					}
					
					boolean paseHaciaElArea = anguloPaseFormateado < anguloMax && anguloPaseFormateado > anguloMin;
					
	    			paseAlHueco = new AccionPasarAlHueco(
	    								new CommandHitBall(jugador.getIndice(),
	    														anguloXY,
	                        									fuerza,
	                        									anguloZ),
	                        			indMiJugAlcanzaAntesBalon,
	                        			posMiJugAlcanzaAntesBalon,
	                        			paseHaciaAdelante,
	                        			paseHaciaElArea);
	    		}
	    		else {
	    			
	    			if (Math.abs(posPase.getX()) < xMax) {
	    				posPase = posPase.movePosition(incremento, 0);
	    			}
	    			else {
	    				
	    				if (buscaOtroLado) {
	    					
	    					buscaOtroLado = false;
	    					incremento = -incremento;
	    					posPase = new Position (posInicial);
	    				}
	    				else {
	    					
	    					// Si tenemos margen de b�squeda en el eje Y reducimos la y un incremento e 
	    					// inicializamos todas las variables para buscar en esa posici�n.
	    					if (yMax > yMin) {
	    						
	    						buscaOtroLado = true;
	    						incremento = (sp.myPlayers()[jugador.getIndice()].getX() > 0) ? 2.5 : -2.5;
	    						
	    						yMax = yMax - 2.5;
	    						posInicial = new Position(posInicial.getX(), yMax);
	    						posPase = new Position (posInicial);
	    					}
	    					else {
	    						salir = true;
	    					}
	    				}
	    			}
	            }
	    	} while (paseAlHueco == null && !salir);
    	}
    	catch (Exception e) {
    		System.err.println("Error en Generador.pasarAlHueco: " + e);
    		paseAlHueco = null;
    	}
    	
    	return paseAlHueco;
    }
    
    
    
    public AccionPasarAlArea pasarAlArea (GameSituations sp, Jugador jugador,
    									  Jugador[] arrayJugadores, Rival[] arrayRivales) {
    	
    	AccionPasarAlArea paseAlArea = null;
    	
    	try {
    	
	    	Jugador jugadorDestino = arrayJugadores[Constants.penalSup.nearestIndex(sp.myPlayers())];
	    	
	    	// Variable en la que devolveremos el resultado.
	    	boolean realizaPase = false;
	
			// Calculamos los par�metros necesarios para llamar al m�todo que calcula si el pase llegar�
			// a su destino.
	        double fuerza;
	        double anguloZ = (sp.myPlayersDetail()[jugador.getIndice()].getPower() == 1) ? 35 : 45;
	        double anguloXY = Math.toDegrees(sp.ballPosition().angle(new Position(Constants.penalSup.getX(),
	        																Constants.penalSup.getY() + 3.8)));
	
	        // Obtenemos la distancia del pase.
	        double distanciaPase = sp.myPlayers()[jugador.getIndice()].distance(Constants.penalSup);
	
	        if (sp.myPlayersDetail()[jugador.getIndice()].getPower() < 1) {
	        	if (distanciaPase > 30) {
	        		fuerza = 1;
	        	}
	        	else if (distanciaPase > 25) {
	        		fuerza = 0.95;
	        	}
	        	else if (distanciaPase > 20) {
	        		fuerza = 0.9;	
	        	}
	        	else {
	        		fuerza = 0.85;	
	        	}
	        }
	        else {
	        	if (distanciaPase > 52) {
	        		fuerza = 1;
	        	}
	        	else if (distanciaPase > 45) {
	        		fuerza = 0.95;
	        	}
	        	else if (distanciaPase > 35) {
	        		fuerza = 0.9;	
	        	}
	        	else if (distanciaPase > 30) {
	        		fuerza = 0.85;	
	        	}
	        	else if (distanciaPase > 25) {
	        		fuerza = 0.8;
	        	}
	        	else {
	        		fuerza = 0.75;	
	        	}
	        }
	
	    	
	    	double amplitudAngular = 15;
	    	double incremento = (sp.myPlayers()[jugador.getIndice()].getX() < 0) ? 5 : -5;
	    	double angulo = anguloXY;
	    	
	    	boolean salir = false;
	    	boolean otroLado = false;
	    	long contador = 0;
	    	
	        do {
	        	realizaPase = alcanzoAlturaDespeje(sp, jugador, arrayRivales, angulo, anguloZ, fuerza);
	
	    		// Si no podemos realizar el despeje para los datos obtenidos.
	    		if (!realizaPase) {
	    			
	    			// Si no hemos superado la amplitud angular, realizamos un nuevo incremento. 
	    			if ((Math.abs(incremento) * contador)  < amplitudAngular) {
	    				
	    				// Si tenemos que buscar el �ngulo equivalente al otro lado.
	    				if (otroLado) {
	    					angulo = anguloXY - (incremento * contador);
	    				}
	    				// Si no tenemos que buscar el �ngulo al otro lado.
	    				else {
	    					
	    		    		// Aumemtamos el contador de la iteraci�n.
	    		    		contador ++;
	    		    		
	    					angulo = (incremento * contador) + anguloXY;
	    				}
	    			}
	    			else {
	    				salir = true;
	    			}
	    		}
	    		
	    		// Cambiamos el valor de la variable que controla si hemos buscado el �ngulo del otro lado.
	    		otroLado = !otroLado;
	    		
	    	} while (!realizaPase && !salir);
	    	
	    	
	    	
	    	// Si hemos encontrado un �ngulo de despeje, realizamos el despeje.
	    	if (realizaPase) {
	    		paseAlArea = new AccionPasarAlArea(new CommandHitBall(jugador.getIndice(),
	                    					  				  		  		angulo,
	                    					  				  		  		fuerza,
	                    					  				  		  		anguloZ),
	                    					  	   jugadorDestino);
	    	}
	    	// En caso contrario despejamos hacia el centro de la porter�a.
	    	else {
	    		paseAlArea = new AccionPasarAlArea(new CommandHitBall(jugador.getIndice(),
	    																   Constants.penalSup,
	    																   fuerza,
	    																   anguloZ),
	    								           jugadorDestino);
	    	}
    	}
    	catch (Exception e) {
    		System.err.println("Error en Generador.pasarAlArea: " + e);
    		paseAlArea = null;
    	}
    	
    	return paseAlArea;
    }
    
    // M�todo que calcula si un rival puede interceptar un pase.
    public boolean rivalEnTrayectoria(GameSituations sp,
    		 						  Position origen,
                                      Position destino,
                                      Double distanciaCorte) {

        boolean enTrayectoria = false;

        // Calculamos la f�rmula de la recta de la trayectoria seg�n la ecuaci�n
        // (y-y1)(x2-x1) = (y2-y1)(x-x1).
        double A = -(destino.getY() - origen.getY());
        double B = destino.getX() - origen.getX();
        double C = origen.getX() * destino.getY() - origen.getY() * destino.getX();

        // Variables que nos permitiran establecer la zona de riesgo entre el origen y el destino.
        double xIzq = (origen.getX() < destino.getX()) ? origen.getX() : destino.getX();
        double xDer = (origen.getX() > destino.getX()) ? origen.getX() : destino.getX();
        double ySup = (origen.getY() > destino.getY()) ? origen.getY() : destino.getY();
        double yInf = (origen.getY() < destino.getY()) ? origen.getY() : destino.getY();
        
        
        // Ampliamos el rango dado cuando nos encontramos una situaci�n en la que dejamos rivalPlayers que pueden
        // interceptar potencialmente el pase.
        if (xDer - xIzq < 2) {
        	xIzq = xIzq - 2;
        	xDer = xDer + 2;
        }
        if (ySup - yInf < 2) {
        	ySup = ySup + 2;
        	yInf = yInf - 2;
        }


        // Comprobamos si los rivalPlayers se encuentran en la trayectoria del pase.
        for (Position rival:sp.rivalPlayers()) {

            // Comprobamos si el rival se encuentra en la zona del pase.
            if (rival.getX() >= xIzq && rival.getX() <= xDer &&
                rival.getY() <= ySup && rival.getY() >= yInf) {
                // Obtenemos la distancia del rival a la recta de la trayectoria seg�n la ecuaci�n
                // |xP*A + yP*B + C| / (A^2 + B^2)^sqr.
                double distancia = Math.abs((rival.getX() * A) + (rival.getY() * B) + C) /
                                   Math.sqrt((A*A) + (B*B));
                if (distancia < distanciaCorte) {
                    enTrayectoria = true;
                    break;
                }
            }
        }

        return enTrayectoria;
    }
    
    
    // M�todo que devuelve true si el tiro es a puerta vacia.
    public boolean puertaVacia(GameSituations sp, Position posTiro) {
        
    	boolean vacia = true;
    	
    	// Si nos encontramos en una Y muy alta anulamos el tiro a puerta vacia
    	if (Math.abs(posTiro.getX()) > Entorno.X_ANULA_PUERTA_VACIA &&
    		posTiro.getY() > Entorno.Y_ANULA_PUERTA_VACIA) {
    		
    		vacia = false;
    	}
    	else {
    	
	    	// Recorremos todos los rivalPlayers.
	    	for (Position r:sp.rivalPlayers()) {
	    		
	    		// Comprobamos que el rival se encuentre m�s adelantado.
	    		if (r.getY() > posTiro.getY() && r.getY() <= (Constants.centroArcoSup.getY()+5)) {
	    			
	    			// Vamos a coger los puntos de intersección formados por las líneas que va a cada poste.
	    			Position interseccionPI = Position.Intersection(posTiro, Constants.posteIzqArcoSup,
							  										new Position(r.getX() - 50, r.getY()),
							  										new Position(r.getX() + 50, r.getY()));
	    			
    				Position interseccionPD = Position.Intersection(posTiro, Constants.posteDerArcoSup,
							  									    new Position(r.getX() - 50, r.getY()),
							  									    new Position(r.getX() + 50, r.getY()));
	    			
    				// Si la intersección con la línea del poste izquierdo es menor que la linea del rival 
    				// sobre la horizontal y la intersección con la línea del poste derecho es mayor que la 
    				// linea del rival sobre la horizontal significa que no hay tiro a puerta vacia.
	    			if (interseccionPI.getX() < r.getX() &&
	    				interseccionPD.getX() > r.getX()) {
	    				
	    					vacia = false;
	    					break;	    					
	    			}
	    		}
	    	}
    	}
    	
    	return vacia;
    }    
 
    public double redondeaMultiplo(double valor, double divisor) {
        return Math.round(valor / divisor) * divisor;
    }
    
    public boolean posicionDentroDelCampo (Position posicion) {
    	
    	double mx = Constants.ANCHO_CAMPO_JUEGO / 2;
    	double my = Constants.LARGO_CAMPO_JUEGO / 2;
    	
    	// Si la posici�n se encuentra dentro del campo de juego.
    	return posicion.getX() >= -mx && posicion.getX() <= mx &&
    		   posicion.getY() >= -my && posicion.getY() <= my;

    }
    
    
    
    
    //*************************************************************************************************************
    //										M�TODOS PRIVADOS DE LA CLASE
    //*************************************************************************************************************
    
    
    
    
    private boolean cumpleCondicionesPase(GameSituations sp, Jugador jugador, Jugador jugadorDestino) {
    	
    	boolean cumpleCondiciones = false;

    	// Comprobamos si el jugador que realiza el pase tiene la suficiente fuerza como para llegar al destino.
    	if (sp.myPlayers()[jugador.getIndice()].distance(sp.myPlayers()[jugadorDestino.getIndice()]) <=
    		jugador.getDistanciaGolpeoAlto()) {
    		
	    	// Si el jugador que realiza el pase es el portero comprobamos que si el jugador destino del 
			// pase esta en una zona pr�xima a la porter�a no tenga ning�n rival cerca.
			if (sp.myPlayersDetail()[jugador.getIndice()].isGoalKeeper()) {
				
				int rivalCercano = sp.myPlayers()[jugadorDestino.getIndice()].nearestIndex(sp.rivalPlayers());
				if (sp.myPlayers()[jugadorDestino.getIndice()].getY() < -10 &&
					sp.myPlayers()[jugadorDestino.getIndice()].distance(sp.rivalPlayers()[rivalCercano]) >
				    Entorno.DISTANCIA_RIVALES_PASE_PORTERO) {
					
					// Si el portero se encuentra a la derecha del poste derecho.
					if (sp.myPlayers()[jugador.getIndice()].getX() > Constants.posteDerArcoInf.getX()) {

						// El jugador destino del pase debe encontrarse a la derecha del portero.
						if (sp.myPlayers()[jugadorDestino.getIndice()].getX() >= sp.myPlayers()[jugador.getIndice()].getX()) {
							cumpleCondiciones = true;
						}
					}
					// Si el portero se encuentra a la izquierda del poste izquierdo.
					else if (sp.myPlayers()[jugador.getIndice()].getX() < Constants.posteIzqArcoInf.getX()) {
						
						// El jugador destino del pase debe encontrarse a la izquierda del portero.
						if (sp.myPlayers()[jugadorDestino.getIndice()].getX() <= sp.myPlayers()[jugador.getIndice()].getX()) {
							cumpleCondiciones = true;
						}
					}
				}
			}
	    	
			// Si el jugador no es el portero.
			else {

		    	// Comprobamos si el pase es hacia adelante y el destino tiene menos jugadores por delante y la
		    	// distancia es mayor que la m�nima exigida para realizar un pase hacia adelante.
				if ((sp.myPlayers()[jugadorDestino.getIndice()].getY() >
		    	     	 sp.myPlayers()[jugador.getIndice()].getY()) &&
		    	    
		    	     	 (rivalesPorEncimaY(sp, jugadorDestino) < rivalesPorEncimaY(sp, jugador)) &&
		    	    
		    	     	 (sp.myPlayers()[jugador.getIndice()].distance(
		    				sp.myPlayers()[jugadorDestino.getIndice()]) > Entorno.MIN_DISTANCIA_PASE_ADELANTE)
		    	    
		    			) {
		    		
		    		cumpleCondiciones = true;
		    	}
		    	
		    	// En caso contrario comprobamos que la distancia m�nima sea superior a la establecida.
		    	else if (sp.myPlayers()[jugador.getIndice()].distance(
						 sp.myPlayers()[jugadorDestino.getIndice()]) > Entorno.MIN_DISTANCIA_PASE) {
		    		
		    		cumpleCondiciones = true;
		    	}
			}
    	}
    	
    	return cumpleCondiciones;
    }

    
    private boolean destinoDentroArea (GameSituations sp, Jugador destino) {
    	
    	boolean dentroArea = false;
    	
    	if (sp.myPlayers()[destino.getIndice()].getY() >
    			((Constants.LARGO_CAMPO_JUEGO /2) - Constants.ANCHO_AREA_GRANDE) &&
    		Math.abs(sp.myPlayers()[destino.getIndice()].getX()) < Constants.LARGO_AREA_GRANDE/2) {
    		
    		dentroArea = true;
    	}
    	
    	return dentroArea;
    }
    
    private long rivalesPorEncimaY (GameSituations sp, Jugador jugador) {
    	
    	long rivales = 0;

    	double margen = 10;
    	for (Position posRival:sp.rivalPlayers()) {
    		
    		if (posRival.getY() > sp.myPlayers()[jugador.getIndice()].getY() &&
    			posRival.getX() > sp.myPlayers()[jugador.getIndice()].getX() - margen &&
    			posRival.getX() < sp.myPlayers()[jugador.getIndice()].getX() + margen) {
    			
    			rivales ++;
    		}
    	}
    	
    	return rivales;
    }
    
    
    private double yMaxPaseAlHueco (GameSituations sp, Jugador jugador) {
    	
    	double yMax = (Constants.LARGO_CAMPO_JUEGO / 2) - 1.5;
    	double y = Constants.centroArcoInf.getY();
    	
    	// Nos quedamos con la Y de nuestro jugador m�s adelantado. Buscamos de la media en adelante.
    	for (int i=5; i<sp.myPlayers().length; i++) {
    		
    		if (sp.myPlayers()[i].getY() > y) {
    			y = sp.myPlayers()[i].getY();
    		}
    	}
    	
    	// Hacemos lo propio buscando el jugador m�s retrasado de los rivalPlayers.
    	for (int i=0; i<sp.rivalPlayers().length; i++) {
    		
    		// Omitimos al portero ya que este probablemente se encontrara cerca de la l�nea de fondo.
    		if (!sp.rivalPlayersDetail()[i].isGoalKeeper() && sp.rivalPlayers()[i].getY() > y) {
    			y = sp.rivalPlayers()[i].getY();
    		}
    	}
    	
    	// Aumentamos en 20 la posici�n Y del jugador m�s adelantado para buscar la profundidad, pero 
    	// controlando que lleguemos a esa distancia siempre realizando una cuenta en perperndicular.
    	y = y+20;
    	if (y > jugador.getDistanciaGolpeoAlto()) {
    		y = jugador.getDistanciaGolpeoAlto();
    	}
    	
    	// Devolvemos el valor m�s peque�o entre la y calculada y la y m�xima, ya que no nos podemos salir 
    	// del campo. 
    	return Math.min(y, yMax);
    }

    
    private boolean alcanzoAntesBalon(GameSituations sp, Jugador jugador,
    								  Jugador[] arrayJugadores, Rival[] arrayRivales,  
    							 	  double anguloXY, double anguloZ, double fuerza) {

    	// Inicializamos las variables para aplicar al procedimiento.
    	boolean alcanzoAntesBalon = false;
   	 	double iteracion = 0; //La iteration en la que nos encontramos.
   	 	Position balon = new Position(sp.ballPosition()); //La posicion del balón.
   	 	double angConvert = Math.PI / 180d;
   	 	int jugAlcanzaBalon = -1;
   	 	Position posAlcanzaBalon = null;

   	 	// --------------------------------------------------------------------------------------------------
   	 	// Calculamos las variables del golpeo
   	 	// --------------------------------------------------------------------------------------------------
   	 	// Calculamos el ángulo XY.
   	 	double angulo = anguloXY * angConvert;
   	 	
   	 	// Calculamos el ángulo vertical.
   	 	double angVer = Math.min(anguloZ, Constants.ANGULO_VERTICAL_MAX);
   	 	angVer = Math.max(angVer, 0);
   	 	angVer = angVer * angConvert;

   	 	// Calculamos la velocidad del remate. 
   	 	double vel = fuerza * Constants.getVelocidadRemate(sp.myPlayersDetail()[jugador.getIndice()].getPower());
   	 	double v0 = vel;
        vel = vel * Math.cos(angVer);
        
        double t0Trayectoria = iteracion;
        double x0Trayectoria = balon.getX();
        double y0Trayectoria = balon.getY();
        
        AbstractTrajectory trayectoria = new AirTrajectory(Math.cos(angVer) * v0, Math.sin(angVer) * v0, 0, 0);
        double angTrayectoria = angulo;
        double alturaBalon = 0;  
        // --------------------------------------------------------------------------------------------------   	 
   	 
         
        // Una vez definidas las variables del golpeo, vamos a iterar hasta comprobar si nuestro jugador llega 
        // antes a por el balón o en cambio llega antes un rival.
        boolean salir = false;
        long ciclos = 0;
        while (!alcanzoAntesBalon && !salir &&
			   posicionDentroDelCampo(balon) &&
			   ciclos<200) {
				
        	 // Incrementamos el ciclo del c�lculo en el que nos encontramos.
        	 ciclos ++;
	       	 
	       	 double time = (iteracion - t0Trayectoria + 1) / 60d;
	       	 double trX = trayectoria.getX(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
	       	 double trY = trayectoria.getY(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;

	       	 // Obtenemos las posición del balón en la iteración calculada.
	       	 balon = new Position(x0Trayectoria + trX * Math.cos(angTrayectoria),
	       			 			  y0Trayectoria + trX * Math.sin(angTrayectoria));

	       	 // Obtenemos la altura del balón en la iteración calculada.
	       	 alturaBalon = trY * 2;

	       	 // Inrementamos los valores que nos dan el índice adecuado en la trayectoria.
	       	 iteracion++;
			
			
			// Comprobamos si en el ciclo actual mis jugadores alcanzan el bal�n.
			boolean jugadorAlcanzaBalon = false;
			for (Jugador miJugador:arrayJugadores) {

				// Tenemos que comprobar si han pasado las iteraciones que nos permiten volver a tocar el 
				// bal�n por lo que testeamos que si el jugador a comprobar es el mismo que el que realiza el 
				// golpeo hayan pasado m�s de 10 iteraciones y en caso de que el jugador sea distinto.
				if ((miJugador.getIndice() == jugador.getIndice() && ciclos > Constants.ITERACIONES_GOLPEAR_BALON) ||
					(miJugador.getIndice() != jugador.getIndice() && 
					 (sp.iterationsToKick()[miJugador.getIndice()]-ciclos <= 0))) {

					jugadorAlcanzaBalon =
						futbolistaAlcanzaElBalon (sp.myPlayers()[miJugador.getIndice()],
												  sp.myPlayersDetail()[miJugador.getIndice()].getSpeed(),
												  balon, alturaBalon, ciclos,
												  Constants.DISTANCIA_CONTROL_BALON,
												  (sp.myPlayersDetail()[miJugador.getIndice()].isGoalKeeper() ?
														  Constants.ALTO_ARCO : Constants.ALTURA_CONTROL_BALON));

					if (jugadorAlcanzaBalon) {
						jugAlcanzaBalon = miJugador.getIndice();
						posAlcanzaBalon = new Position(balon);
						break;
					}
				}
			}
			
			// Comprobamos si en el ciclo actual alg�n rival alcanza el bal�n.
			boolean rivalAlcanzaBalon = false;
			for (Rival rival:arrayRivales) {
				
				// Si en el ciclo calculado el jugador puede rematar el bal�n, calculamos si alcanza el bal�n.
				if ((sp.rivalIterationsToKick()[rival.getIndice()]-ciclos) <= 0) {

					rivalAlcanzaBalon = 
						futbolistaAlcanzaElBalon (sp.rivalPlayers()[rival.getIndice()],
												  sp.rivalPlayersDetail()[rival.getIndice()].getSpeed(),
												  balon, alturaBalon, ciclos,
												  Constants.DISTANCIA_CONTROL_BALON,
												  (sp.rivalPlayersDetail()[rival.getIndice()].isGoalKeeper() ?
														  Constants.ALTO_ARCO : Constants.ALTURA_CONTROL_BALON));
					
					if (rivalAlcanzaBalon) {
						break;
					}
				}
			}
			
			
			// Si un jugador mio alcanza antes el bal�n.
			if (jugadorAlcanzaBalon) {
				
				// Si el rival no alcanza el bal�n devolvemos true.
				if (!rivalAlcanzaBalon) {
					alcanzoAntesBalon = true;
					indMiJugAlcanzaAntesBalon = jugAlcanzaBalon;
					posMiJugAlcanzaAntesBalon = posAlcanzaBalon;
					
				}
				// En caso contrario salimos del bucle.
				else {
					alcanzoAntesBalon = false;
					salir = true;
				}
			}
			// En cambio si un jugador rival alcanza antes el bal�n, nos salimos devolviendo false.
			else if (rivalAlcanzaBalon) {
				alcanzoAntesBalon = false;
				salir = true;
			}
		}
    	
    	return alcanzoAntesBalon;
    }
    
    
    private boolean alcanzoAlturaDespeje(GameSituations sp, Jugador jugador, Rival[] arrayRivales,
    									 double anguloXY, double anguloZ, double fuerza) {

    	// Variable en la que devolvemos el resultado.
    	boolean alcanzoAlturaDespeje = false;

    	
    	// Inicializamos las variables para aplicar al procedimiento.
   	 	double iteracion = 0; //La iteration en la que nos encontramos.
   	 	Position balon = new Position(sp.ballPosition()); //La posicion del balón.
   	 	double angConvert = Math.PI / 180d;

    	
   	 	// --------------------------------------------------------------------------------------------------
   	 	// Calculamos las variables del golpeo
   	 	// --------------------------------------------------------------------------------------------------
   	 	// Calculamos el ángulo XY.
   	 	double angulo = anguloXY * angConvert;
   	 	
   	 	// Calculamos el ángulo vertical.
   	 	double angVer = Math.min(anguloZ, Constants.ANGULO_VERTICAL_MAX);
   	 	angVer = Math.max(angVer, 0);
   	 	angVer = angVer * angConvert;

   	 	// Calculamos la velocidad del remate. 
   	 	double vel = fuerza * Constants.getVelocidadRemate(sp.myPlayersDetail()[jugador.getIndice()].getPower());
   	 	double v0 = vel;
        vel = vel * Math.cos(angVer);
        
        double t0Trayectoria = iteracion;
        double x0Trayectoria = balon.getX();
        double y0Trayectoria = balon.getY();
        
        AbstractTrajectory trayectoria = new AirTrajectory(Math.cos(angVer) * v0, Math.sin(angVer) * v0, 0, 0);
        double angTrayectoria = angulo;
        double alturaBalon = 0;  
        // --------------------------------------------------------------------------------------------------   	 
    	
    	
    	// Una vez definidas las variables del golpeo, vamos a iterar hasta comprobar si el bal�n alcanza
    	// una altura a la que no llega ning�n jugador sin interceptarlo previamente.
    	boolean rivalAlcanzaBalon = false;
    	boolean salir = false;
    	long ciclos = 0;
    	
    	while (!rivalAlcanzaBalon && !salir) {

    		// Incrementamos el ciclo del c�lculo en el que nos encontramos.
    		ciclos ++;

    		double time = (iteracion - t0Trayectoria + 1) / 60d;
    		double trX = trayectoria.getX(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
    		double trY = trayectoria.getY(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;

    		// Obtenemos las posición del balón en la iteración calculada.
    		balon = new Position(x0Trayectoria + trX * Math.cos(angTrayectoria),
    						     y0Trayectoria + trX * Math.sin(angTrayectoria));

    		// Obtenemos la altura del balón en la iteración calculada.
    		alturaBalon = trY * 2;

    		// Inrementamos los valores que nos dan el índice adecuado en la trayectoria.
    		iteracion++;

 		
    		// Comprobamos si en el ciclo actual alg�n rival alcanza el bal�n.
    		for (Rival rival:arrayRivales) {

    			// Si en el ciclo calculado el jugador puede rematar el bal�n, calculamos si alcanza el bal�n.
    			if ((sp.rivalIterationsToKick()[rival.getIndice()]-ciclos) <= 0) {

    				rivalAlcanzaBalon = 
    					futbolistaAlcanzaElBalon (sp.rivalPlayers()[rival.getIndice()],
    							sp.rivalPlayersDetail()[rival.getIndice()].getSpeed(),
    							balon, alturaBalon, ciclos,
    							Constants.DISTANCIA_CONTROL_BALON,
    							(sp.rivalPlayersDetail()[rival.getIndice()].isGoalKeeper() ?
    									Constants.ALTO_ARCO : Constants.ALTURA_CONTROL_BALON));

    				if (rivalAlcanzaBalon) {
    					break;
    				}
    			}
    		}


    		if (!rivalAlcanzaBalon && alturaBalon > Constants.ALTURA_CONTROL_BALON) {
    			salir = true;
    			alcanzoAlturaDespeje = true;
    		}
    	}

    	return alcanzoAlturaDespeje;
    }    
    
    
    // Nota: Recordamos que en el avance los angulos se especifican en radianes y el golpeo en grados.  
    private Double avanzarRango (GameSituations sp, Jugador jugador,
    							 Jugador[] arrayJugadores, Rival[] arrayRivales, 
    							 double amplitudAngular, double anguloVertical, double fuerza) {
    	
    	Double anguloRango = null;
    	double incremento = 45;
    	double anguloInicial = 90;
    	
    	// Calculamos el �ngulo inicial del inicio del movimiento.
        // Si el jugador ha superado el l�mite de avance sobre el eje Y, avanzamos
        // hacia la porter�a
        if (sp.myPlayers()[jugador.getIndice()].getY() > Entorno.LIMITE_AVANCE_Y) {
        	
        	// Lado izquierdo.
        	if (sp.myPlayers()[jugador.getIndice()].getX() < (Constants.posteIzqArcoSup.getX()-1.5)) {
        		
        		// Cerca de línea de fondo.
        		if (sp.myPlayers()[jugador.getIndice()].getY() >
        			((Constants.LARGO_CAMPO_JUEGO /2) - Constants.ANCHO_AREA_CHICA)) {
        			
        			anguloInicial = 0;
        		}
        		else {
        			anguloInicial = 45;
        		}
        	}
        	// Lado derecho.
        	else if (sp.myPlayers()[jugador.getIndice()].getX() > (Constants.posteDerArcoSup.getX()+1.5)) {
        		
        		// Cerca de línea de fondo.
        		if (sp.myPlayers()[jugador.getIndice()].getY() >
        			((Constants.LARGO_CAMPO_JUEGO /2) - Constants.ANCHO_AREA_CHICA)) {
        			
        			anguloInicial = 180;
        		}
        		else {
        			anguloInicial = 135;
        		}
        	}
        }
    	
    	// Si estamos en el lado izquierdo buscamos primero avanzar hacia la porter�a (incremento angular negativo)
    	if (sp.myPlayers()[jugador.getIndice()].getX() < 0) {
    		incremento = -incremento;
    	}
    	
    	double angulo = anguloInicial;
    	
    	
    	boolean salir = false;
    	boolean alcanzoBalon = false;
    	boolean otroLado = false;
    	long contador = 0;
    	boolean cercaLinea = false;
    	
    	do {
    		
    		// Inicializamos la variable para calcularla con el angulo actual.
    		cercaLinea = false;
    		
    		// Estamos cerca de cualquier línea de banda.
    		if (Math.abs(sp.ballPosition().getX()) > ((Constants.ANCHO_CAMPO_JUEGO/2)-2.5) &&
    			angulo == 90) {
    			
   				cercaLinea = true;
    		}
    		
    		// Estamos cerca de la línea de fondo.
    		if (sp.ballPosition().getY() > ((Constants.LARGO_CAMPO_JUEGO/2)-2.5)) {
    			
    			if (sp.ballPosition().getX() > 0 && angulo == 180) {
    				cercaLinea = true;
    			}
    			else if (sp.ballPosition().getX() < 0 && angulo == 0) {
    				cercaLinea = true;
    			}
    		}
    		
    		// Comprobamos que no estemos cerca de la línea para poder calcular el avance.
    		if (!cercaLinea) {
    			alcanzoBalon = alcanzoAntesBalon(sp, jugador, 
    											 arrayJugadores, arrayRivales,  
    											 angulo, anguloVertical, fuerza);
    		}

    		// Si alcanzamos el bal�n nos quedamos con el �ngulo para devolverlo.
    		if (alcanzoBalon) {
    			anguloRango = angulo;
    		}
    		// Si no alcanzamos el bal�n comprobamos si podemos seguir variando el �ngulo del desplazamiento
    		// para seguri buscando.
    		else {
    			
    			// Si no hemos superado la amplitud angular, realizamos un nuevo incremento. 
    			if ((Math.abs(incremento) * contador)  < amplitudAngular) {
    				
    				// Si tenemos que buscar el �ngulo equivalente al otro lado.
    				if (otroLado) {
    					angulo = anguloInicial - (incremento * contador);
    				}
    				// Si no tenemos que buscar el �ngulo al otro lado.
    				else {
    					
    		    		// Aumemtamos el contador de la iteraci�n.
    		    		contador ++;
    		    		
    					angulo = (incremento * contador) + anguloInicial;
    				}
    			}
    			else {
    				salir = true;
    			}
    		}
    		
    		// Cambiamos el valor de la variable que controla si hemos buscado el �ngulo del otro lado.
    		otroLado = !otroLado;
    		
    	} while (!alcanzoBalon && !salir);

    	return anguloRango;
    }
    
    
    // M�todo que nos permite conocer si un futbolista (jugador o rival) alcanza el bal�n en la situaci�n
    // dada por los par�metros establecidos.
    private boolean futbolistaAlcanzaElBalon (Position futbolista, double velocidadFutbolista,
    										  Position balon, double alturaBalon, long iteraciones,
    										  double alcanceXY, double alcanceZ) {

    	boolean alcanzaElBalon = false;
    	
		// Si el jugador alcanza el bal�n en el eje Z.
		if (alturaBalon <= alcanceZ) {
		
			Position irA = new Position(balon.getX(), balon.getY());
			
			double dist = futbolista.distance(irA);
			double vel = Constants.getVelocidad(velocidadFutbolista) * iteraciones;
	        if (dist < vel) {
	            vel = dist;
	        }
	        futbolista = futbolista.movePosition(irA.getX() - futbolista.getX(),
	        									  irA.getY() - futbolista.getY(), vel);
	        
	        if (futbolista.distance(irA) < alcanceXY) {
	        	alcanzaElBalon = true;
	        }
		}
		
		return alcanzaElBalon;
    }
    
    
    private Hashtable<String, Object> calculaPosicionTiro(GameSituations sp, Jugador jugador,
    													  Position inicioTiro, boolean opcionTiroSeguro) {
    	
    	// Variable en la que devolveremos el resultado.
    	Hashtable<String, Object> tabla = null;
    	double varIni = 0.0, varFin = 0.0;
    	
    	
    	// Calculamos los valores para realizar el tiro.
    	double fuerza = 1;
    	double anguloZ = 23; 
    	double[] valores = calculaValoresTiro(sp, jugador, inicioTiro);
    	if (valores != null) {
    		
        	fuerza = valores[0];
        	anguloZ = valores[1];
    	}
    	
    	ArrayList<String> listadoZona = listaZonaTiro(sp, jugador, inicioTiro, opcionTiroSeguro, fuerza, anguloZ);

    	// Tenemos informaci�n disponible en el listado con las zonas encontradas para realizar un tiro. 
    	if (listadoZona != null && listadoZona.size() > 0) {

    		double ini = 0.0, fin = 0.0;
    		for (String zona:listadoZona) {

    			String[] arr = zona.split("#");
    			ini = new Double(arr[0]);
    			fin = new Double(arr[1]);

    			// Comprobamos si nos encntramos en el caso inicial o la zona actual es m�s amplia que la 
    			// anterior para quedarnos con la m�s amplia.
    			if ( (varIni == 0.0 && varFin == 0.0) || (fin - ini) > (varFin - varIni) ) {
    				varIni = ini;
    				varFin = fin;
    			}
    		}

    		// Calculamos la variaci�n a aplicar al poste izquierdo para calcular el punto medio de la
    		// variaci�n obtenida
    		double variacion;
    		if (varFin == varIni) {
    			variacion = varFin;
    		} else {
    			// SI NO ES TIRO SEGURO LO PODEMOS AJUSTAR MÁS A UNO DE LOS PALOS
    			variacion = (varFin + varIni) / 2;
    		}

    		Position posicionXY = Constants.posteIzqArcoSup.movePosition(variacion, 0);

    		// Corregimos los valores obtenidos para que no de en el poste y no se vaya alta aplicando 
    		// finalmente los incrementos y situaciones límite.
    		Hashtable<String, Object> tablaCorreccion = corregirAnguloZVariacionTiro(sp, jugador, inicioTiro, fuerza, posicionXY, anguloZ);
    		
    		tabla = new Hashtable<String, Object>();
    		tabla.put("posicion", tablaCorreccion.get("posicionXY"));
    		tabla.put("anguloZ", tablaCorreccion.get("anguloZ"));
    		tabla.put("variacion", (varFin - varIni));
    		tabla.put("fuerza", fuerza);
    	}
    	
    	return tabla;
    }    
 
    
    private double[] calculaValoresTiro(GameSituations sp, Jugador jugador, Position posTiro) {
    	
    	// Variable en la que devolvemos el resultado.
    	double[] valores = null;
    	
       	// Inicializamos las variables para aplicar al procedimiento.
    	double iteracion = 0; //La iteration en la que nos encontramos.
    	double angConvert = Math.PI / 180d;
    	double anguloXY = posTiro.angle(Constants.centroArcoSup);
    	
    	// --------------------------------------------------------------------------------------------------
    	// Calculamos las variables del golpeo
    	// --------------------------------------------------------------------------------------------------
    	// Calculamos el ángulo XY.
    	double angulo = anguloXY;
    	
    	// Inicialización de las variables del golpeo.
    	double anguloZ = 30;
    	double fuerza = 1;
    	double decremento = -1;
    	boolean alturaCorrecta;
    	
    	do {
        	Position balon = new Position(posTiro); //La posicion del balón.
    		
	    	// Calculamos el ángulo vertical.
	    	double angVer = Math.min(anguloZ, Constants.ANGULO_VERTICAL_MAX);
	    	angVer = Math.max(angVer, 0);
	    	angVer = angVer * angConvert;
	
	    	// Calculamos la velocidad del remate. 
	    	double vel = fuerza * Constants.getVelocidadRemate(sp.myPlayersDetail()[jugador.getIndice()].getPower());
	    	double v0 = vel;
	    	vel = vel * Math.cos(angVer);

	    	double t0Trayectoria = iteracion;
	    	double x0Trayectoria = balon.getX();
	    	double y0Trayectoria = balon.getY();

	
	        AbstractTrajectory trayectoria = new AirTrajectory(Math.cos(angVer) * v0, Math.sin(angVer) * v0, 0, 0);
	        double angTrayectoria = angulo;
	        double alturaBalon = 0;  
	    	// --------------------------------------------------------------------------------------------------   	 
	    	
	        // Variable en la que almacenaremos si la altura antes de superar la línea de meta es correcta. 
	    	alturaCorrecta = true;
	
	    	// Una vez definidas las variables del golpeo, vamos a iterar hasta comprobar si nuestro jugador llega 
	    	// antes a por el balón o en cambio llega antes un rival.
	    	long ciclos = 0;
	    	while (posicionDentroDelCampo(balon) && ciclos<200) {
	
	    		// --------------------------------------------------------------------------------------------------
	    		// Calculamos la posición tras la iteración
	    		// --------------------------------------------------------------------------------------------------
	    		// Incrementamos el ciclo del c�lculo en el que nos encontramos.
	    		ciclos ++;

	    		double time = (iteracion - t0Trayectoria + 1) / 60d;
	    		double trX = trayectoria.getX(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
	    		double trY = trayectoria.getY(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;

	    		// Obtenemos las posición del balón en la iteración calculada.
	    		balon = new Position(x0Trayectoria + trX * Math.cos(angTrayectoria),
	    							 y0Trayectoria + trX * Math.sin(angTrayectoria));

	    		// Obtenemos la altura del balón en la iteración calculada.
	    		alturaBalon = trY * 2;
	    		alturaCorrecta = (alturaBalon < Constants.ALTO_ARCO);
   		
	    		// Inrementamos los valores que nos dan el índice adecuado en la trayectoria.
	    		iteracion++;
	    		// --------------------------------------------------------------------------------------------------
	    	}
	    	
	    	if (alturaCorrecta) {
	    		
	    		valores = new double[2];
	    		valores[0] = fuerza;
	    		valores[1] = anguloZ;
	    	}
	    	anguloZ = anguloZ + decremento;
	    	
    	} while (!alturaCorrecta && anguloZ > -1);
    	
    	return valores;
    }

    
    private Hashtable<String, Object> corregirAnguloZVariacionTiro(GameSituations sp, Jugador jugador, Position inicioTiro, double fuerza, Position posicionXY, double anguloZ) {

    	// Variable en la que devolveremos las correcciones.
    	Hashtable<String, Object> tablaCorreccion = new Hashtable<String, Object>();
    	
    	// Almacenamos el ánguloZ original para realizar el cálculo óptimo.
    	double anguloOriginal = anguloZ;
    	
    	// Variable en la que almacenamos el angulo corregido.
    	double anguloCorregido = anguloZ;

    	// El ángulo de la posicionXY hacia donde va dirigido el tiro.
    	double anguloXY;
    	
    	// Variable que utilizamos para validar si tenemos que corregir la posición XY porque el tiro da en el poste.
    	boolean corregirX;
    	
    	do {
    	
    		// Inicializamos la variable.
    		corregirX = false;
    		
    		// Obtenemos el ángulo en la iteración actual.
    		anguloXY = inicioTiro.angle(posicionXY);
    		
	       	// Inicializamos las variables para aplicar al procedimiento.
	    	double iteracion = 0; //La iteration en la que nos encontramos.
	    	double angConvert = Math.PI / 180d;
	    	
	    	
	        // Variables a utilizar en el calculo de tiro al poste.
	    	Position balon;
	    	double balonDx0 = 0;
	        double balonDy0 = 0;
	        double balonDz0 = 0;
	        double alturaBalon = 0;
	        
	    	// --------------------------------------------------------------------------------------------------
	    	// Calculamos las variables del golpeo
	    	// --------------------------------------------------------------------------------------------------
	    	// Calculamos el ángulo XY.
	    	double angulo = anguloXY;
	    	
	    	// Inicialización de las variables del golpeo.
	    	double decremento = -1;
	    	boolean alturaCorrecta;

	    	do {
	    		// Asignamos el valor correcto a cada vuelta de bucle.
	    		anguloCorregido = anguloZ;
	    		
	        	balon = new Position(inicioTiro); //La posicion del balón.
	    		
		    	// Calculamos el ángulo vertical.
		    	double angVer = Math.min(anguloZ, Constants.ANGULO_VERTICAL_MAX);
		    	angVer = Math.max(angVer, 0);
		    	angVer = angVer * angConvert;
		
		    	// Calculamos la velocidad del remate. 
		    	double vel = fuerza * Constants.getVelocidadRemate(sp.myPlayersDetail()[jugador.getIndice()].getPower());
		    	double v0 = vel;
		    	vel = vel * Math.cos(angVer);
		    	
		    	// Obtenemos los valores para la progresión y así determinar si el balón da en el poste.
	            double balonDz = redondeaMultiplo(vel * Math.sin(angVer), Constants.G);
	            double balonDx = Math.cos(angulo) * vel;
	            double balonDy = Math.sin(angulo) * vel;
	
		    	double t0Trayectoria = iteracion;
		    	double x0Trayectoria = balon.getX();
		    	double y0Trayectoria = balon.getY();
	
		
		        AbstractTrajectory trayectoria = new AirTrajectory(Math.cos(angVer) * v0, Math.sin(angVer) * v0, 0, 0);
		        double angTrayectoria = angulo;
		    	// --------------------------------------------------------------------------------------------------   	 
	
		        
		        // Variable en la que almacenaremos si la altura antes de superar la línea de meta es correcta. 
		    	alturaCorrecta = true;
	
		    	// Una vez definidas las variables del golpeo, vamos a iterar hasta comprobar si nuestro jugador llega 
		    	// antes a por el balón o en cambio llega antes un rival.
		    	long ciclos = 0;
		    	while (posicionDentroDelCampo(balon) && ciclos<200) {
		
		    		// --------------------------------------------------------------------------------------------------
		    		// Calculamos la posición tras la iteración
		    		// --------------------------------------------------------------------------------------------------
		    		// Incrementamos el ciclo del c�lculo en el que nos encontramos.
		    		ciclos ++;
	
		    		double time = (iteracion - t0Trayectoria + 1) / 60d;
		    		double time0 = (iteracion - t0Trayectoria) / 60d;
		    		double trX = trayectoria.getX(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
		    		double trY = trayectoria.getY(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
	
		    		// Obtenemos las posición del balón en la iteración calculada.
		    		balon = new Position(x0Trayectoria + trX * Math.cos(angTrayectoria),
		    							 y0Trayectoria + trX * Math.sin(angTrayectoria));
	
		    		// Obtenemos la altura del balón en la iteración calculada.
		    		alturaBalon = trY * 2;
		    		
		    		// Actualizamos los valores de la progresión para determinar si da en el poste.
		            if (time0 > 0) {
		                double trX0 = trayectoria.getX(time0) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
		                double trY0 = trayectoria.getY(time0) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
		                Position b0 = new Position(x0Trayectoria + trX0 * Math.cos(angTrayectoria),
		                        				   y0Trayectoria + trX0 * Math.sin(angTrayectoria));
		                double alturaBalon0 = trY0 * 2;
		                balonDx0 = balonDx;
		                balonDy0 = balonDy;
		                balonDz0 = balonDz;
		                balonDx = balon.getX() - b0.getX();
		                balonDy = balon.getY() - b0.getY();
		                balonDz = alturaBalon - alturaBalon0;
		            }	    		
		    		
		    		
		    		alturaCorrecta = (alturaBalon < Constants.ALTO_ARCO);
	   		
		    		// Inrementamos los valores que nos dan el índice adecuado en la trayectoria.
		    		iteracion++;
		    		// --------------------------------------------------------------------------------------------------
		    	}
		    	
		    	anguloZ = anguloZ + decremento;
		    	
	    	} while (!alturaCorrecta && anguloZ > -1);
	    	
	    	
	    	boolean salir = false;
	    	double posY = Constants.LARGO_CAMPO_JUEGO / 2;
	        double posX = (balonDx0 / balonDy0) * (posY - balon.getY()) + balon.getX();//proyeccion x de la trayectoria del ballPosition en la linea de meta
	        double posZ = (balonDz0 / balonDy0) * (posY - balon.getY()) + alturaBalon;//proyeccion z de la trayectoria del ballPosition en la linea de meta
	        
	        if (posZ <= Constants.ALTO_ARCO) {
	            double abs = Math.abs(posX);
				if (abs < Constants.LARGO_ARCO / 2 - Constants.RADIO_BALON) {
					if (alturaBalon - balonDz0 > Constants.ALTO_ARCO) {

						salir = true;
						
						// En este caso hay que corregir la z, por lo que usamos la varibale corregirX
						// para volver a generar el proceso y actualizamos el ánguloZ.
						corregirX = true;
						
						//Inicializamos el ánguloZ para optimizar el tiro.
						anguloOriginal = anguloZ;
					}
					salir = true;
				}
				if (!salir) {
					
					// Da en el poste
					if (abs >= Constants.LARGO_ARCO / 2 - Constants.RADIO_BALON
						&& abs <= Constants.LARGO_ARCO / 2 + Constants.RADIO_BALON) {
					
						corregirX = true;
						
						// Si llegamos a 0, detenemos la búsqueda para no entrar en un bucle infinito.
						if (posicionXY.getX() == 0) {
							corregirX = false;
						}
						else {
							// Movemos el ángulo en función del poste en el que haya dado.
							if (balon.getX() < 0) {
								posicionXY = new Position(posicionXY.getX() + 0.1, posicionXY.getY());
							} else {
								posicionXY = new Position(posicionXY.getX() -0.1, posicionXY.getY());
							}
							
							//Inicializamos el ánguloZ para optimizar el tiro.
							anguloZ = anguloOriginal;
						}
					}
				}
	        }
	        // El tiro se va alto.
	        else {
	        	
				salir = true;
				
				// En este caso hay que corregir la z, por lo que usamos la varibale corregirX
				// para volver a generar el proceso y actualizamos el ánguloZ.
				corregirX = true;
				
				//Inicializamos el ánguloZ para optimizar el tiro.
				anguloOriginal = anguloZ;
	        }
    	} while (corregirX && (anguloZ > -1));
    	
    	tablaCorreccion.put("posicionXY", posicionXY);
    	tablaCorreccion.put("anguloZ", anguloCorregido);
    	
    	return tablaCorreccion;
    }
    
    
    private ArrayList<String> listaZonaTiro (GameSituations sp, Jugador jugador,
    										 Position inicioTiro, boolean opcionTiroSeguro,
    										 double fuerza, double anguloZ) {
    	
    	ArrayList<String> lista = new ArrayList<String>();
    	
    	double incremento = 0.1;
    	double variacion = 0.0;

    	// Comenzamos a recorrer desde el poste izquierdo de la porteria hasta el poste derecho buscando los
    	// huecos libres para tirar.
    	boolean zonaTiro = false;
    	Double inicioZona = null;
    	Position posTiro = new Position(Constants.posteIzqArcoSup);
    	
    	// Variable en la que almacenaremos el resultado de realizar el c�lculo del tiro atendiendo a si es
    	// un tiro seguro o no.
    	boolean llegaAGol;
    	
    	do {
    		posTiro = posTiro.movePosition(incremento, 0);
    		variacion = variacion + incremento;
    		
    		// Calcular la trayectoria con la opci�n de tiro seguro.
    		if (opcionTiroSeguro) {
    			llegaAGol = trayectoriaDeGol(sp, jugador, inicioTiro, posTiro, fuerza, anguloZ);
    		}
    		// Calcular la trayectoria sin la opci�n de tiro seguro (es decir, si tenemos v�a libre).
    		else {
    			llegaAGol = !rivalEnTrayectoria(sp, inicioTiro, posTiro, Entorno.DISTANCIA_CORTE_TIRO) &&
    						!porteroEnTrayectoria(sp, inicioTiro, posTiro);
    		}
    		
    		// Si no hay rival en la trayectoria del tiro.
    		if (llegaAGol) {
    		
    			// Si antes no en zona de tiro, marcamos esta posicion como la de inicio de la 
    			// zona de tiro libre.
    			if (!zonaTiro) {
    				zonaTiro = true;
    				inicioZona = variacion;
    			}
    		}
    		// Hay rival en la trayectoria del equipo.
    		else {
    			
    			// Si nos encontramos en zona de tiro cerramos la zona con la variaci�n actual e indicamos que
    			// nos encontramos fuera de ella nuevamente. 
    			if (zonaTiro) {
    				zonaTiro = false;
    				lista.add(inicioZona + "#" + (variacion - incremento));
    				inicioZona = null;
    			}
    		}
    	} while (posTiro.getX() < Constants.posteDerArcoSup.getX());
    	
    	
    	// Si finalizamos el bucle y tenemos una zona de tiro abierta la cerramos. 
    	if (zonaTiro) {
    		
    		if ((variacion-incremento > inicioZona)) {
    			lista.add(inicioZona + "#" + (variacion - incremento));
    		}
    		else {
    			lista.add(inicioZona + "#" + inicioZona);
    		}
    	}
    	
    	return lista;
    }    
 
    
    // M�todo que devuelve true si nos encontramos en la zona de forzado de tiro.
    private boolean forzadoTiro(GameSituations sp) {
    	
    	boolean forzado = sp.ballPosition().distance(Constants.centroArcoSup) < Entorno.DISTANCIA_FORZAR_TIRO;
    	
    	// Si nos encontramos en la distancia de forzado y el bal�n se encuentra fuera del eje de las x de la 
    	// posici�n que delimita la porteria, comprobamos que el �ngulo de tiro se encuentre dentro del rango. 
    	if (forzado && Math.abs(sp.ballPosition().getX()) > Constants.posteDerArcoSup.getX() + 1.5) {
    		
    		double incremento = 30;
    		double anguloTiro = Math.toDegrees(sp.ballPosition().angle(Constants.centroArcoSup));
    		forzado = (anguloTiro > (0 + incremento)) && (anguloTiro < (180 - incremento));
    	}
    	
    	
    	return forzado;
    }
    
    
    private boolean trayectoriaDeGol (GameSituations sp, Jugador jugador,
    								  Position inicioTiro, Position destinoTiro,
    								  double fuerza, double anguloZ) {
    	
   		//NOTA: En este caso el anguloXY debe llegar informado en grados.
    	double anguloXY = Math.toDegrees(inicioTiro.angle(destinoTiro));
    	
    	// Inicializamos las variables para aplicar al procedimiento.
    	double iteracion = 0; //La iteration en la que nos encontramos.
    	Position balon = new Position(inicioTiro); //La posicion de inicio del tiro.
    	double angConvert = Math.PI / 180d;

    	// --------------------------------------------------------------------------------------------------
    	// Calculamos las variables del golpeo
    	// --------------------------------------------------------------------------------------------------
    	// Calculamos el ángulo XY.
    	double angulo = anguloXY * angConvert;

    	// Calculamos el ángulo vertical.
    	double angVer = Math.min(anguloZ, Constants.ANGULO_VERTICAL_MAX);
    	angVer = Math.max(angVer, 0);
    	angVer = angVer * angConvert;

    	// Calculamos la velocidad del remate. 
    	double vel = fuerza * Constants.getVelocidadRemate(sp.myPlayersDetail()[jugador.getIndice()].getPower());
    	double v0 = vel;
    	vel = vel * Math.cos(angVer);

    	double t0Trayectoria = iteracion;
    	double x0Trayectoria = balon.getX();
    	double y0Trayectoria = balon.getY();

    	AbstractTrajectory trayectoria = new AirTrajectory(Math.cos(angVer) * v0, Math.sin(angVer) * v0, 0, 0);
    	double angTrayectoria = angulo;
    	double alturaBalon = 0;  
    	// --------------------------------------------------------------------------------------------------    	


    	boolean salir = false;
    	long ciclos = 0;
    	boolean rivalAlcanzaBalon = false;
    	
    	// Una vez definidas las variables del golpeo, vamos a iterar hasta comprobar si algun rival 
    	// alcanza el bal�n.
    	while (!rivalAlcanzaBalon && !salir &&
    		   posicionDentroDelCampo(balon) &&
			   ciclos<200) {
				
         	// --------------------------------------------------------------------------------------------------
         	// Calculamos la posición tras la iteración
         	// --------------------------------------------------------------------------------------------------        	 
			// Incrementamos el ciclo del c�lculo en el que nos encontramos.
			ciclos ++;
			
			double time = (iteracion - t0Trayectoria + 1) / 60d;
			double trX = trayectoria.getX(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
			double trY = trayectoria.getY(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;

			// Obtenemos las posición del balón en la iteración calculada.
			balon = new Position(x0Trayectoria + trX * Math.cos(angTrayectoria),
								 y0Trayectoria + trX * Math.sin(angTrayectoria));

			// Obtenemos la altura del balón en la iteración calculada.
			alturaBalon = trY * 2;

			// Inrementamos los valores que nos dan el índice adecuado en la trayectoria.
			iteracion++;
	        // --------------------------------------------------------------------------------------------------	       	 
	       	 
			// Comprobamos si en el ciclo actual alg�n rival alcanza el bal�n.
			Position[] arrayRivales = sp.rivalPlayers();
			//for (Rival rival:arrayRivales) {
			int contador = 0;
			while (contador < arrayRivales.length && !rivalAlcanzaBalon) {
				
				// Si en el ciclo calculado el jugador puede rematar el bal�n, calculamos si alcanza el bal�n.
				if ((sp.rivalIterationsToKick()[contador]-ciclos) <= 0) {
					
					rivalAlcanzaBalon = 
						futbolistaAlcanzaElBalon (sp.rivalPlayers()[contador],
												  sp.rivalPlayersDetail()[contador].getSpeed(),
												  balon, alturaBalon, ciclos,
												  (sp.rivalPlayersDetail()[contador].isGoalKeeper() ?
														  Constants.DISTANCIA_CONTROL_BALON_PORTERO : Constants.DISTANCIA_CONTROL_BALON),
												  (sp.rivalPlayersDetail()[contador].isGoalKeeper() ?
														  Constants.ALTO_ARCO : Constants.ALTURA_CONTROL_BALON));
				}

				// Incrementamos el contador para buscar el siguiente rival.
				contador ++;
			}
		}

         return !rivalAlcanzaBalon;
    }
    
    
    private boolean porteroEnTrayectoria (GameSituations sp,
    									  Position origen,
    									  Position destino) {

    	// Calculamos la f�rmula de la recta de la trayectoria seg�n la ecuaci�n
    	// (y-y1)(x2-x1) = (y2-y1)(x-x1).
    	double A = -(destino.getY() - origen.getY());
    	double B = destino.getX() - origen.getX();
    	double C = -origen.getY() * (destino.getX() - origen.getX()) +
    	origen.getX() * (destino.getY() - origen.getY());

    	// Obtenemos el rival m�s cercano al gol (portero)
    	Position rival = sp.rivalPlayers()[destino.nearestIndex(sp.rivalPlayers())];
    	// Obtenemos la distancia del rival a la recta de la trayectoria seg�n la ecuaci�n
    	// |xP*A + yP*B + C| / (A^2 + B^2)^sqr.
    	double distancia = Math.abs((rival.getX() * A) + (rival.getY() * B) + C) /
    	Math.sqrt((A*A) + (B*B));

    	return distancia < Entorno.DISTANCIA_CORTE_TIRO;
    }
    
}