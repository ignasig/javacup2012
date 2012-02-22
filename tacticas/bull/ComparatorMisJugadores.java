package bull;

import java.util.Comparator;

public class ComparatorMisJugadores implements Comparator<MisJugadores>{
	@Override
	public int compare(MisJugadores arg0, MisJugadores arg1) {
		return  Double.compare(arg0.distance , arg1.distance);
	}
}