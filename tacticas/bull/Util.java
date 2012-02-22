package bull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

}
