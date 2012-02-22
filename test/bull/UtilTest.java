package bull;

import org.javahispano.javacup.model.util.Position;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bull.Util;

public class UtilTest {
	
	@DataProvider(name="datosDistancia")
	Object[][] datosObtenerTurno(){
		
		
		
		return new Object[][]{
				{new Position (20,20), new Position (20,40), new Double (20)},				
				
				
				
		};
	}

  @Test (dataProvider="datosDistancia")
  public void calculaDistancia(Position pos1,Position pos2, Double res) {
    assert res.equals(Util.calculaDistancia(pos1, pos2));
  }

  @Test
  public void ordenaPositiones() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void restaAbs() {
    throw new RuntimeException("Test not implemented");
  }
}
