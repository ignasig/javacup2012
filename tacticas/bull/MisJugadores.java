package bull;

public class MisJugadores {
	
	int jugador;
	double distance;
	
	public MisJugadores (int _jugador, double _distance){
		jugador= _jugador;
		distance=_distance;
	}
	
	public int getJugador() {
		return jugador;
	}
	public void setJugador(int jugador) {
		this.jugador = jugador;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

}
