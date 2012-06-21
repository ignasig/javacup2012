package bull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.util.Position;

public class Util {
	
	
	public static List <MisJugadores> ordenaPorDistancia (Position posicion, Position [] jugadores ){
    	List <MisJugadores> al= new ArrayList <MisJugadores>();
    	
    	for (int i=0; i<jugadores.length ; i++ ){
    		Position pos= jugadores [i];
    		al.add(new MisJugadores  (i,calculaDistancia (posicion, pos)));
    	}
    	
    	Collections.sort (al,new ComparatorMisJugadores ());
    	
    	
    	return al;
    }
    
   
    
    public static Double calculaDistancia (Position pos1,Position pos2) {
    	
    	
    	
    	Double posX = pos2.getX() - pos1.getX();
    	Double posY = pos2.getY() - pos1.getY();
    	
    	
    	Double distance = Math.sqrt(Math.pow (posX,2)+Math.pow(posY,2));
    	
    	
    	return distance;
    	
    }
    
    
    
    /**
     * Devuelve el valor absoluta de la resta
     * @param num1
     * @param num2
     * @return
     */
    public static double restaAbs (double num1, double num2){
    	double result=0;
    	if (num1>0){
    		if (num2>0){
    			if (num2>num1){
    				result=num2-num1;
    			}else{
    				result=num1-num2;
    			}
    		}else{
    			result = num2 + num1;
    		}
    	}else{
    		if (num2<0){
    			if (num2>num1){
    				result=-(num1-num2);
    			}else{
    				result=-(num2-num1);
    			}
    		}else{
    			result = -(num2 + num1);
    		}
    	}
    	return result;
    }
    
    public static int getIteracionesBalon (GameSituations gs,int jugador, boolean rival, Position pos){
    	if (rival){
    		double distancia = calculaDistancia(gs.rivalPlayers()[jugador], pos);
    		double velocidad = Constants.getVelocidad(gs.getRivalPlayerSpeed(jugador));
    		int iteraciones = (int) ((distancia-Constants.DISTANCIA_CONTROL_BALON)/velocidad);
    		return iteraciones;
    		
    	}else{
    		double distancia = calculaDistancia(gs.myPlayers()[jugador], pos);
    		double velocidad = Constants.getVelocidad(gs.getMyPlayerSpeed(jugador));
    		int iteraciones = (int) ((distancia-Constants.DISTANCIA_CONTROL_BALON)/velocidad);
    		return iteraciones;
    		
    	}
   	
    }
    
    
    public static Position getPosicionBalonEsperadajugador (GameSituations gs ){
    	Position ballPosition=gs.ballPosition();
    	double altitude = gs.ballAltitude();
    	
    	if (altitude>Constants.ALTURA_CONTROL_BALON ){
    		//Hemos de contar en que momento la altura permite que la controlemos.
    		
    	}else{
    		// Caso en el que podríamos ya ejercer control.
    		
    		
    	}
    	
    	double [] trayectoria=gs.getTrajectory(gs.iteration());
    	ballPosition.setPosition(ballPosition.getX()+trayectoria[0],ballPosition.getY()+trayectoria[1]);
    	return ballPosition;
    }
     
    
    public static Position getPosicionBalonEsperadaPortero (GameSituations gs ){
    	Position ballPosition=gs.ballPosition();
    	double altitude = gs.ballAltitude();
    	if (altitude>Constants.ALTO_ARCO){
    		//Hemos de contar en que momento la altura permite que la controlemos.
    		
    	}else{
    		// Caso en el que podríamos ya ejercer control.
    		
    		
    	}
    	
    	double [] trayectoria=gs.getTrajectory(gs.iteration());
    	ballPosition.setPosition(ballPosition.getX()+trayectoria[0],ballPosition.getY()+trayectoria[1]);
    	return ballPosition;
    }

    
}
