

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;

import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.Tactic;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.command.Command.CommandType;
import org.javahispano.javacup.model.command.CommandMoveTo;
import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.model.trajectory.AbstractTrajectory;
import org.javahispano.javacup.model.trajectory.FloorTrajectory;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.util.Position;

import bull.TacticBull1;


public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tactic tactic = new TacticBull1();
		GameSituationsTest gs = new GameSituationsTest();
		
		Position balon = new Position(-28.594d, -47.226d);
		double alturaBalon = 5;
		
		Position[] mios = new Position[]{
	        new Position(0.2595419847328244,-50.41044776119403),
	        new Position(-28.531468531468533,-29.21945701357466),
	        new Position(-7.846153846153847,-30.88235294117647),
	        new Position(9.272727272727272,-32.07013574660634),
	        new Position(27.104895104895103,-29.694570135746606),
	        new Position(0.951048951048951,-19.004524886877828),
	        new Position(-14.503496503496503,-9.264705882352942),
	        new Position(13.076923076923078,-9.264705882352942),
	        new Position(0.4755244755244755,6.651583710407239),
	        new Position(-8.55944055944056,20.192307692307693),
	        new Position(14.027972027972028,25.18099547511312)
	    };
		
		Position[] contrarios = new Position[]{
		        new Position(0.2595419847328244,-50.41044776119403),
		        new Position(-33.531468531468533,-34.21945701357466),
		        new Position(-12.846153846153847,-35.88235294117647),
		        new Position(14.272727272727272,-37.07013574660634),
		        new Position(32.104895104895103,-34.694570135746606),
		        new Position(5.951048951048951,-24.004524886877828),
		        new Position(-14.503496503496503,-9.264705882352942),
		        new Position(13.076923076923078,-9.264705882352942),
		        new Position(0.4755244755244755,6.651583710407239),
		        new Position(-8.55944055944056,20.192307692307693),
		        new Position(14.027972027972028,25.18099547511312)
		};
		
		int golesMios = 0;
		int golesContrarios = 0;
		int iteracion = 2500;
		boolean saco = false;
		boolean sacaRival = false;
		int[] iterGolpearBalonLocal = {20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		int[] iterGolpearBalonVisita = {20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		AbstractTrajectory trayectoria = new FloorTrajectory(0, 0);
		double x0 = 0;
		double y0 = 0;
		double t0 = 0;
		double a0 = 0;
		int iterReal = 1000;
		boolean invert = false;
		PlayerDetail[][] jugadores = new PlayerDetail[2][11];
		jugadores[0] = tactic.getDetail().getPlayers();
		jugadores[1] = tactic.getDetail().getPlayers();
		
		gs.setTestData(balon, alturaBalon, golesMios, golesContrarios, iteracion, mios, contrarios, saco, sacaRival, iterGolpearBalonLocal, iterGolpearBalonVisita, trayectoria, x0, y0, t0, a0, iterReal, invert);
		
		gs.setPlayersDetails(jugadores);
		List<Command> commands = tactic.execute(gs);
		
		drawPositions(commands, gs);
	}
	
	
	private static void drawPositions(List<Command> commands, GameSituations gs) {
        int ancho = (int) Constants.ANCHO_CAMPO * 10;
        int largo = (int) Constants.LARGO_CAMPO * 10;
        int anchoCampoJuego = (int) Constants.ANCHO_CAMPO_JUEGO * 10;
        int largoCampoJuego = (int) Constants.LARGO_CAMPO_JUEGO * 10;
        
		MyCanvas canvas = new MyCanvas(commands, gs, ancho, largo, anchoCampoJuego, largoCampoJuego);
        JFrame frame = new JFrame();
        frame.setSize(ancho, largo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
	}


	private static class GameSituationsTest extends GameSituations {
		
		public GameSituationsTest() {
			super();
		}
		
		public void setTestData(Position balon, double alturaBalon, int golesMios, int golesContrarios, int iteracion, Position[] mios, Position[] contrarios, boolean saco, boolean sacaRival, int[] iterGolpearBalonLocal, int[] iterGolpearBalonVisita, AbstractTrajectory trayectoria, double x0, double y0, double t0, double a0, int iterReal, boolean invert) {
		    set(balon, alturaBalon, golesMios, golesContrarios, iteracion, mios, contrarios, saco, sacaRival, iterGolpearBalonLocal, iterGolpearBalonVisita, trayectoria, x0, y0, t0, a0, iterReal, invert);
		}
		
		public void setPlayersDetails(PlayerDetail[][] jugadores) {
			set(jugadores);
		}
	}
	
	
	private static class MyCanvas extends Canvas {
		
		private List<Command> commands;
		private GameSituations gameSituation;
		private int ancho;
		private int largo;
		private int anchoCampoJuego;
		private int largoCampoJuego;
		
		public MyCanvas(List<Command> commands, GameSituations gameSituation, int ancho, int largo, int anchoCampoJuego, int largoCampoJuego) {
			this.commands = commands;
			this.gameSituation = gameSituation;
			this.ancho = ancho;
			this.largo = largo;
			this.anchoCampoJuego = anchoCampoJuego;
			this.largoCampoJuego = largoCampoJuego;
		}
		
		
		public void paint(Graphics graphics) {
			paintLines(graphics);
			paintBall(graphics);
			paintPlayers(graphics);
			paintRivals(graphics);
		}
		
		
		private void paintLines(Graphics graphics) {
			graphics.setColor(Color.BLACK);
			// Campo
			graphics.drawRect((this.ancho - this.anchoCampoJuego) / 2, (this.largo - this.largoCampoJuego) / 2, this.anchoCampoJuego, this.largoCampoJuego);
			// Medio campo
			graphics.drawLine((this.ancho - this.anchoCampoJuego) / 2, this.largo / 2, this.ancho - (this.ancho - this.anchoCampoJuego) / 2, this.largo / 2);
			// Area peque–a superior
			graphics.drawRect(scaleX(Constants.LARGO_AREA_CHICA / 2.0), scaleY((-Constants.LARGO_CAMPO_JUEGO / 2.0)), (int)Constants.LARGO_AREA_CHICA * 10, (int)Constants.ANCHO_AREA_CHICA * 10);
			//Area grande superior
			graphics.drawRect(scaleX(Constants.LARGO_AREA_GRANDE / 2.0), scaleY((-Constants.LARGO_CAMPO_JUEGO / 2.0)), (int)Constants.LARGO_AREA_GRANDE * 10, (int)Constants.ANCHO_AREA_GRANDE* 10);
			
		}
		

		private void paintBall(Graphics graphics) {
			graphics.setColor(Color.BLUE);
			graphics.fillOval(scaleX(gameSituation.ballPosition().getX()), scaleY(gameSituation.ballPosition().getY()), 10, 10);
		}
		
		
		private void paintPlayers(Graphics graphics) {
			graphics.setColor(Color.GREEN);
			for(Command command : commands) {
				System.out.print((command.getPlayerIndex() + 1) + ": " + command.getCommandType() + " ");
				
				if(command.getCommandType() == CommandType.MOVE_TO) {
					CommandMoveTo commandMoveTo = (CommandMoveTo)command;
					int playerPositionX = scaleX(this.gameSituation.myPlayers()[commandMoveTo.getPlayerIndex()].getX());
					int playerPositionY = scaleY(this.gameSituation.myPlayers()[commandMoveTo.getPlayerIndex()].getY());
					
					graphics.drawString(String.valueOf(commandMoveTo.getPlayerIndex() + 1), playerPositionX, playerPositionY);
					graphics.drawLine(playerPositionX, playerPositionY, scaleX(commandMoveTo.getMoveTo().getX()), scaleY(commandMoveTo.getMoveTo().getY()));
					System.out.println(((CommandMoveTo)command).getMoveTo().toString());
				}
			}
		}
		
		
		private void paintRivals(Graphics graphics) {
			int id = 0;
			graphics.setColor(Color.RED);
			for(Position position : this.gameSituation.rivalPlayers()) {
				int coordX = scaleX(position.getX());
				int coordY = scaleY(position.getY());
				
				graphics.drawString(String.valueOf(id++ + 1), coordX, coordY);	
			}
		}
		
		
		private int scaleX(double coordX) {
			return this.ancho - (int)(coordX * 10 + this.ancho / 2.0);
		}
		
		private int scaleY(double coordY) {
			return (int)(coordY * 10 + this.largo / 2.0);
		}
	}
}
