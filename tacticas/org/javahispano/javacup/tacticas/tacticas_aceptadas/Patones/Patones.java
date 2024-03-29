package org.javahispano.javacup.tacticas.tacticas_aceptadas.Patones;

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
import java.util.LinkedList;
import org.javahispano.javacup.model.*;
import java.util.List;

public class Patones implements Tactic {

    Position alineacion1[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-8.797202797202797,-36.58371040723982),
        new Position(1.4265734265734267,-35.8710407239819),
        new Position(10.6993006993007,-37.05882352941177),
        new Position(0.23776223776223776,-22.805429864253394),
        new Position(18.78321678321678,-23.28054298642534),
        new Position(-19.25874125874126,-22.56787330316742),
        new Position(-10.6993006993007,-10.214932126696834),
        new Position(8.797202797202797,-2.8506787330316743),
        new Position(0.7132867132867133,-1.4253393665158371),
        new Position(12.125874125874127,-9.97737556561086)
    };

    Position alineacion2[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-8.797202797202797,-36.58371040723982),
        new Position(1.4265734265734267,-35.8710407239819),
        new Position(10.6993006993007,-37.05882352941177),
        new Position(0.23776223776223776,-22.805429864253394),
        new Position(18.78321678321678,-23.28054298642534),
        new Position(-19.25874125874126,-22.56787330316742),
        new Position(-10.6993006993007,-10.214932126696834),
        new Position(7.132867132867133,-6.651583710407239),
        new Position(-0.23776223776223776,-12.828054298642533),
        new Position(15.454545454545453,-11.165158371040723)
    };

    Position alineacion3[]=new Position[]{
        new Position(0.2595419847328244,-50.41044776119403),
        new Position(-8.797202797202797,-36.58371040723982),
        new Position(1.4265734265734267,-35.8710407239819),
        new Position(10.461538461538462,-37.05882352941177),
        new Position(0.23776223776223776,-15.441176470588236),
        new Position(17.832167832167833,-6.414027149321266),
        new Position(-19.25874125874126,-4.751131221719457),
        new Position(-13.552447552447552,9.502262443438914),
        new Position(7.132867132867133,22.56787330316742),
        new Position(-5.944055944055944,20.90497737556561),
        new Position(12.125874125874127,10.927601809954751)
    };

    class TacticaDetalleImpl implements TacticDetail {

        public String getTacticName() {
            return "patonea";
        }

        public String getCountry() {
            return "Colombia";
        }

        public String getCoach() {
            return "Ferney Vallejo";
        }

        public Color getShirtColor() {
            return new Color(229, 227, 250);
        }

        public Color getShortsColor() {
            return new Color(64, 10, 9);
        }

        public Color getShirtLineColor() {
            return new Color(0, 69, 239);
        }

        public Color getSocksColor() {
            return new Color(158, 39, 194);
        }

        public Color getGoalKeeper() {
            return new Color(188, 159, 59        );
        }

        public EstiloUniforme getStyle() {
            return EstiloUniforme.FRANJA_HORIZONTAL;
        }

        public Color getShirtColor2() {
            return new Color(55, 211, 135);
        }

        public Color getShortsColor2() {
            return new Color(52, 143, 232);
        }

        public Color getShirtLineColor2() {
            return new Color(66, 165, 96);
        }

        public Color getSocksColor2() {
            return new Color(72, 101, 144);
        }

        public Color getGoalKeeper2() {
            return new Color(207, 199, 50        );
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
                new JugadorImpl("Jugador", 1, new Color(255,200,150), new Color(50,0,0),1.0d,0.88d,0.95d, true),
                new JugadorImpl("Jugador", 2, new Color(255,200,150), new Color(50,0,0),0.74d,0.72d,0.81d, false),
                new JugadorImpl("Jugador", 3, new Color(255,200,150), new Color(50,0,0),0.74d,0.74d,0.81d, false),
                new JugadorImpl("Jugador", 4, new Color(255,200,150), new Color(50,0,0),0.78d,0.67d,0.74d, false),
                new JugadorImpl("Jugador", 5, new Color(255,200,150), new Color(50,0,0),0.76d,0.76d,0.94d, false),
                new JugadorImpl("Jugador", 6, new Color(255,200,150), new Color(50,0,0),0.69d,0.76d,0.9d, false),
                new JugadorImpl("Jugador", 7, new Color(255,200,150), new Color(50,0,0),0.63d,0.76d,0.96d, false),
                new JugadorImpl("Jugador", 8, new Color(255,200,150), new Color(50,0,0),0.71d,0.96d,0.85d, false),
                new JugadorImpl("Jugador", 9, new Color(255,200,150), new Color(50,0,0),0.75d,0.74d,0.77d, false),
                new JugadorImpl("Jugador", 10, new Color(255,200,150), new Color(50,0,0),0.98d,1.0d,1.0d, false),
                new JugadorImpl("Jugador", 11, new Color(255,200,150), new Color(50,0,0),1.0d,1.0d,1.0d, false)
            };
        }
    }

    TacticDetail detalle=new TacticaDetalleImpl();
    public TacticDetail getDetail() {
        return detalle;
    }

    public Position[] getStartPositions(GameSituations sp) {
    return alineacion1;
    }

    public Position[] getNoStartPositions(GameSituations sp) {
    return alineacion2;
    }

    LinkedList<Command> comandos = new LinkedList<Command>();
    int capo = 7;
    public List<Command> execute(GameSituations sp) {
        comandos.clear();
        Position[] jugadores = sp.myPlayers();
        for (int i = 0; i < jugadores.length; i++) {
            comandos.add(new CommandMoveTo(i, alineacion3[i]));
        }

        Position porIr = Constants.centroArcoInf.moveAngle(Constants.centroArcoInf.angle(sp.ballPosition()), 5);
        comandos.add(new CommandMoveTo(0, porIr));


        
        if (!sp.isRivalStarts()) {
            int[] recuperadores = sp.getRecoveryBall();
            if (recuperadores.length > 1) {
                double[] posRecuperacion = sp.getTrajectory(recuperadores[0]);
                for (int i = 1; i < recuperadores.length; i++) {
                    comandos.add(new CommandMoveTo(recuperadores[i],
                            new Position(posRecuperacion[0], posRecuperacion[1])));
                }
            }
        }
        int[] jug = sp.canKick();
        for(int i=0;i<jug.length;i++){
            comandos.add(new CommandHitBall(jug[i], Constants.centroArcoSup, 1.0, 15));
            Position mipos = sp.myPlayers()[jug[i]];
            int[] coates = mipos.nearestIndexes(sp.myPlayers());
            for(int j=0;j<coates.length;j++){
                if(sp.myPlayers()[coates[j]].getY() > mipos.getY()){
                    if(libre(jug[i],coates[j],sp)){
                        comandos.add(new CommandHitBall(jug[i], sp.myPlayers()[coates[j]], 0.9, false));
                        break;
                    }
                }
            }
        }


        return comandos;

    }


    public boolean libre(int mi,int micoate,GameSituations sp){
        Position pco = sp.myPlayers()[micoate];
        Position pmi = sp.myPlayers()[mi];
        for(int i=0;i<sp.rivalPlayers().length;i++){
            Position posurival = sp.rivalPlayers()[i];
            if(posurival.distance(pco) < 10)return false;
            if(pmi.distance(posurival) < pmi.distance(pco)
            && (Math.abs(pmi.angle(pco) - pmi.angle(posurival))) < Math.toRadians(25))return false;
        }
        return true;
    }

    public boolean libre2(int mi,Position pco,GameSituations sp){
        Position pmi = sp.myPlayers()[mi];
        for(int i=0;i<sp.rivalPlayers().length;i++){
            Position posurival = sp.rivalPlayers()[i];
            if(posurival.distance(pco) < 10)return false;
            if(pmi.distance(posurival) < pmi.distance(pco)
            && (Math.abs(pmi.angle(pco) - pmi.angle(posurival))) < Math.toRadians(25))return false;
        }
        return true;
    }
}