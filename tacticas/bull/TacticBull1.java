package bull;

import java.awt.Color;
import org.javahispano.javacup.model.*;
import org.javahispano.javacup.model.util.*;
import org.javahispano.javacup.render.EstiloUniforme;
import org.javahispano.javacup.model.command.*;
import org.javahispano.javacup.model.engine.GameSituations;

import java.util.ArrayList;
import java.util.List;

public class TacticBull1 implements Tactic {

    Position alineacion1[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-19.46564885496183,-31.6044776119403),
        new Position(-0.951048951048951,-30.6447963800905),
        new Position(19.984732824427482,-31.6044776119403),
        new Position(-0.4755244755244755,-18.29185520361991),
        new Position(-0.23776223776223776,5.463800904977376),
        new Position(-19.734265734265733,-6.889140271493213),
        new Position(17.832167832167833,-7.601809954751132),
        new Position(0.0,28.744343891402718),
        new Position(-29.006993006993007,14.490950226244346),
        new Position(28.76923076923077,15.441176470588236)
    };

    Position alineacion2[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-11.16030534351145,-31.082089552238806),
        new Position(11.16030534351145,-31.6044776119403),
        new Position(27.251908396946565,-27.94776119402985),
        new Position(-29.84732824427481,-26.902985074626866),
        new Position(8.564885496183205,-7.574626865671642),
        new Position(-10.641221374045802,-7.052238805970149),
        new Position(27.251908396946565,4.440298507462686),
        new Position(-29.32824427480916,3.3955223880597014),
        new Position(-0.2595419847328244,19.067164179104477),
        new Position(-0.2595419847328244,35.78358208955224)
    };

    Position alineacion3[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-11.16030534351145,-31.082089552238806),
        new Position(11.16030534351145,-31.6044776119403),
        new Position(26.732824427480914,-20.111940298507463),
        new Position(-29.32824427480916,-21.67910447761194),
        new Position(0.2595419847328244,-0.26119402985074625),
        new Position(-18.946564885496183,-0.26119402985074625),
        new Position(18.946564885496183,-0.26119402985074625),
        new Position(-19.46564885496183,35.78358208955224),
        new Position(-0.2595419847328244,19.067164179104477),
        new Position(18.946564885496183,35.26119402985075)
    };

    Position alineacion4[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-11.16030534351145,-31.082089552238806),
        new Position(11.16030534351145,-31.6044776119403),
        new Position(28.290076335877863,-28.470149253731343),
        new Position(-28.290076335877863,-28.470149253731343),
        new Position(11.16030534351145,-1.3059701492537314),
        new Position(-10.641221374045802,-0.7835820895522387),
        new Position(-27.251908396946565,31.6044776119403),
        new Position(-10.641221374045802,30.559701492537314),
        new Position(9.603053435114505,28.992537313432837),
        new Position(25.69465648854962,28.992537313432837)
    };

    Position alineacion5[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-11.16030534351145,-35.78358208955224),
        new Position(12.717557251908397,-35.26119402985075),
        new Position(28.290076335877863,-28.470149253731343),
        new Position(-28.290076335877863,-28.470149253731343),
        new Position(14.793893129770993,-18.544776119402986),
        new Position(-17.389312977099234,-19.58955223880597),
        new Position(-23.618320610687025,-0.7835820895522387),
        new Position(5.969465648854961,-5.485074626865671),
        new Position(0.2595419847328244,-0.26119402985074625),
        new Position(22.580152671755727,-1.3059701492537314)
    };

    Position alineacion6[]=new Position[]{
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

    Position alineacion7[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-19.46564885496183,-31.6044776119403),
        new Position(-0.951048951048951,-30.6447963800905),
        new Position(19.984732824427482,-31.6044776119403),
        new Position(-0.4755244755244755,-18.29185520361991),
        new Position(-0.23776223776223776,5.463800904977376),
        new Position(-19.734265734265733,-6.889140271493213),
        new Position(17.832167832167833,-7.601809954751132),
        new Position(0.0,28.744343891402718),
        new Position(-29.006993006993007,14.490950226244346),
        new Position(28.76923076923077,15.441176470588236)
    };

    class TacticDetailImpl implements TacticDetail {

        public String getTacticName() {
            return "bull team";
        }

        public String getCountry() {
            return "Andorra";
        }

        public String getCoach() {
            return "BigBull";
        }

        public Color getShirtColor() {
            return new Color(255, 255, 0);
        }

        public Color getShortsColor() {
            return new Color(0, 51, 51);
        }

        public Color getShirtLineColor() {
            return new Color(255, 51, 51);
        }

        public Color getSocksColor() {
            return new Color(255, 255, 0);
        }

        public Color getGoalKeeper() {
            return new Color(0, 0, 0        );
        }

        public EstiloUniforme getStyle() {
            return EstiloUniforme.SIN_ESTILO;
        }

        public Color getShirtColor2() {
            return new Color(0, 51, 255);
        }

        public Color getShortsColor2() {
            return new Color(51, 51, 51);
        }

        public Color getShirtLineColor2() {
            return new Color(255, 255, 0);
        }

        public Color getSocksColor2() {
            return new Color(0, 51, 255);
        }

        public Color getGoalKeeper2() {
            return new Color(51, 255, 0        );
        }

        public EstiloUniforme getStyle2() {
            return EstiloUniforme.FRANJA_DIAGONAL;
        }

        class JugadorImpl implements PlayerDetail {

            String nombre;
            int numero;
            Color piel, pelo;
            double velocidad, remate, presicion;
            boolean portero;
            Position Position;

            public JugadorImpl(String nombre, int numero, Color piel, Color pelo,
                    double velocidad, double remate, double presicion, boolean portero) {
                this.nombre=nombre;
                this.numero=numero;
                this.piel=piel;
                this.pelo=pelo;
                this.velocidad=velocidad;
                this.remate=remate;
                this.presicion=presicion;
                this.portero=portero;
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
                new JugadorImpl("dasaef", 1, new Color(255,200,150), new Color(255,255,204),1.0d,0.5d,1.0d, true),
                new JugadorImpl("Jugador", 2, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 3, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 4, new Color(255,200,150), new Color(50,0,0),1.0d,0.5d,0.5d, false),
                new JugadorImpl("Jugador", 5, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,0.5d, false),
                new JugadorImpl("Jugador", 6, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,0.5d, false),
                new JugadorImpl("Jugador", 7, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,0.5d, false),
                new JugadorImpl("Jugador", 8, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,0.5d, false),
                new JugadorImpl("Jugador", 9, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,1.0d, false),
                new JugadorImpl("Jugador", 10, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,1.0d, false),
                new JugadorImpl("Jugador", 11, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,1.0d, false)
            };
        }
    }

    TacticDetail detalle=new TacticDetailImpl();
    public TacticDetail getDetail() {
        return detalle;
    }

    public Position[] getStartPositions(GameSituations sp) {
    return alineacion5;
    }

    public Position[] getNoStartPositions(GameSituations sp) {
    return alineacion6;
    }

    public List<Command> execute(GameSituations sp) {
    	Position posBalon = sp.ballPosition();
    	Position [] posMios=sp.myPlayers();
    	Position [] posRivales= sp.rivalPlayers();
   	
    	List <MisJugadores> lJugadoresBalon =Util.ordenaPorDistancia(posBalon, posMios);
    	
    	List <MisJugadores> lRivalesBalon =Util.ordenaPorDistancia(posBalon, posRivales);
    	
    	List <Command> commands= new ArrayList <Command> ();
    	Command command= new CommandHitBall(lJugadoresBalon.get(0).getJugador(), Constants.posteIzqArcoSup, 1, Constants.ANGULO_VERTICAL);
    	commands.add(command);
    	command = new CommandMoveTo(lJugadoresBalon.get(0).getJugador(),posBalon);
    	commands.add(command);
    	int jugadorRivalBalon =lRivalesBalon.get(0).getJugador(); 
    	int jugadorCercaJugadorRivalBalon = Util.ordenaPorDistancia(posRivales[jugadorRivalBalon], posRivales).get(0).jugador;
    	
    	command = new CommandMoveTo(jugadorCercaJugadorRivalBalon,posRivales[jugadorRivalBalon]);
    	commands.add(command);
    	List <Integer> jugadoresUsados = new ArrayList<Integer>();
    	jugadoresUsados.add(new Integer (lJugadoresBalon.get(0).getJugador()));
    	jugadoresUsados.add(new Integer (0));
    	jugadoresUsados.add(new Integer (jugadorRivalBalon));
    	for (MisJugadores jugador: lJugadoresBalon ){
    		int j=0;
    		List <MisJugadores> lCerca =Util.ordenaPorDistancia(posMios[jugador.getJugador()], posRivales);
    		while (jugadoresUsados.contains(new Integer (lCerca.get(j).getJugador())) && j<lCerca.size() ){
    			j++;
    		}
    		command = new CommandMoveTo(jugador.getJugador(), posRivales [lCerca.get(j).getJugador()]);
    		commands.add( command);
    		jugadoresUsados.add(new Integer (jugador.getJugador()));
    		    	}
        return commands;
    }
}