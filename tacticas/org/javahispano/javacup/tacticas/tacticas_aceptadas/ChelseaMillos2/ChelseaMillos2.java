/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.javahispano.javacup.tacticas.tacticas_aceptadas.ChelseaMillos2;
import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.util.Constants;
import org.javahispano.javacup.model.TacticDetail;
import org.javahispano.javacup.model.PlayerDetail;
import org.javahispano.javacup.model.Tactic;
import org.javahispano.javacup.model.command.Command;
import org.javahispano.javacup.model.command.CommandMoveTo;
import org.javahispano.javacup.model.command.CommandHitBall;
import org.javahispano.javacup.model.trajectory.AbstractTrajectory;
import org.javahispano.javacup.model.trajectory.FloorTrajectory;
import org.javahispano.javacup.render.EstiloUniforme;
import java.awt.Color;
import java.util.ArrayList;
import org.javahispano.javacup.model.*;
import java.util.List;
import java.util.Random;
/**
 *
 * @author david
 */
public class ChelseaMillos2 implements Tactic {
     MyPosition align1[] = new MyPosition[]{
        new MyPosition(0,-50.41044776119403),
        new MyPosition(-19.46564885496183,-31.6044776119403),
        new MyPosition(0.2595419847328244,-31.082089552238806),
        new MyPosition(19.984732824427482,-31.6044776119403),
        new MyPosition(7.526717557251908,-11.753731343283583),
        new MyPosition(-8.564885496183205,-11.753731343283583),
        new MyPosition(-24.65648854961832,-2.3507462686567164),
        new MyPosition(23.099236641221374,-2.873134328358209),
        new MyPosition(-14.274809160305344,30.559701492537314),
        new MyPosition(-0.7786259541984732,8.097014925373134),
        new MyPosition(12.717557251908397,29.51492537313433)
    };
      double minX[] = new double[]{
        0,
        -Constants.ANCHO_CAMPO_JUEGO/2,
        -Constants.ANCHO_CAMPO_JUEGO/4,
        -Constants.ANCHO_CAMPO_JUEGO/6,
        0,
        -Constants.ANCHO_CAMPO_JUEGO/2,
        -Constants.ANCHO_CAMPO_JUEGO/4,
        0,
        -Constants.ANCHO_CAMPO_JUEGO/2,
        -Constants.ANCHO_CAMPO_JUEGO/4,
        0
    };

    double maxX[] = new double[]{
        0,
        0,
        Constants.ANCHO_CAMPO_JUEGO/6,
        Constants.ANCHO_CAMPO_JUEGO/4,
        Constants.ANCHO_CAMPO_JUEGO/2,
        0,
        Constants.ANCHO_CAMPO_JUEGO/4,
        Constants.ANCHO_CAMPO_JUEGO/2,
        0,
        Constants.ANCHO_CAMPO_JUEGO/4,
        Constants.ANCHO_CAMPO_JUEGO/2
    };
    Position alineacion1[] = new Position[]{
        new Position(0.2595419847328244, -50.41044776119403),
        new Position(-19.46564885496183, -31.6044776119403),
        new Position(0.2595419847328244, -31.082089552238806),
        new Position(19.984732824427482, -31.6044776119403),
        new Position(7.526717557251908, -11.753731343283583),
        new Position(-8.564885496183205, -11.753731343283583),
        new Position(-24.65648854961832, -2.3507462686567164),
        new Position(23.099236641221374, -2.873134328358209),
        new Position(-14.274809160305344, 30.559701492537314),
        new Position(-0.7786259541984732, 8.097014925373134),
        new Position(12.717557251908397, 29.51492537313433)
    };
    Position alineacion2[] = new Position[]{
        new Position(0.2595419847328244, -50.41044776119403),
        new Position(-11.16030534351145, -31.082089552238806),
        new Position(11.16030534351145, -31.6044776119403),
        new Position(27.251908396946565, -27.94776119402985),
        new Position(-29.84732824427481, -26.902985074626866),
        new Position(8.564885496183205, -7.574626865671642),
        new Position(-10.641221374045802, -7.052238805970149),
        new Position(27.251908396946565, 4.440298507462686),
        new Position(-29.32824427480916, 3.3955223880597014),
        new Position(-0.2595419847328244, 19.067164179104477),
        new Position(-0.2595419847328244, 35.78358208955224)
    };
    Position alineacion3[] = new Position[]{
        new Position(0.2595419847328244, -50.41044776119403),
        new Position(-11.16030534351145, -31.082089552238806),
        new Position(11.16030534351145, -31.6044776119403),
        new Position(26.732824427480914, -20.111940298507463),
        new Position(-29.32824427480916, -21.67910447761194),
        new Position(0.2595419847328244, -0.26119402985074625),
        new Position(-18.946564885496183, -0.26119402985074625),
        new Position(18.946564885496183, -0.26119402985074625),
        new Position(-19.46564885496183, 35.78358208955224),
        new Position(-0.2595419847328244, 19.067164179104477),
        new Position(18.946564885496183, 35.26119402985075)
    };
    Position alineacion4[] = new Position[]{
        new Position(0.2595419847328244, -50.41044776119403),
        new Position(-11.16030534351145, -31.082089552238806),
        new Position(11.16030534351145, -31.6044776119403),
        new Position(28.290076335877863, -28.470149253731343),
        new Position(-28.290076335877863, -28.470149253731343),
        new Position(11.16030534351145, -1.3059701492537314),
        new Position(-10.641221374045802, -0.7835820895522387),
        new Position(-27.251908396946565, 31.6044776119403),
        new Position(-10.641221374045802, 30.559701492537314),
        new Position(9.603053435114505, 28.992537313432837),
        new Position(25.69465648854962, 28.992537313432837)
    };
    Position alineacion5[] = new Position[]{
        new Position(0.2595419847328244, -50.41044776119403),
        new Position(-11.16030534351145, -35.78358208955224),
        new Position(12.717557251908397, -35.26119402985075),
        new Position(28.290076335877863, -28.470149253731343),
        new Position(-28.290076335877863, -28.470149253731343),
        new Position(14.793893129770993, -18.544776119402986),
        new Position(-17.389312977099234, -19.58955223880597),
        new Position(-23.618320610687025, -0.7835820895522387),
        new Position(5.969465648854961, -5.485074626865671),
        new Position(0.2595419847328244, -0.26119402985074625),
        new Position(22.580152671755727, -1.3059701492537314)
    };
    Position alineacion6[] = new Position[]{
        new Position(0.2595419847328244, -50.41044776119403),
        new Position(-11.16030534351145, -35.78358208955224),
        new Position(12.717557251908397, -35.26119402985075),
        new Position(28.290076335877863, -28.470149253731343),
        new Position(-28.290076335877863, -28.470149253731343),
        new Position(14.793893129770993, -18.544776119402986),
        new Position(-17.389312977099234, -19.58955223880597),
        new Position(-23.618320610687025, -0.7835820895522387),
        new Position(6.4885496183206115, -6.529850746268657),
        new Position(-6.4885496183206115, -6.529850746268657),
        new Position(22.580152671755727, -1.3059701492537314)
    };

    class TacticaDetalleImpl implements TacticDetail {

        @Override
       public String getTacticName() {
            return "chelsea-millos";
        }

       
        public String getCountry() {
            return "Colombia";
        }

        public String getCoach() {
            return "Bruteiker";
        }

        public Color getShirtColor() {
            return new Color(51, 51, 255);
        }

        public Color getShortsColor() {
            return new Color(51, 51, 255);
        }

        public Color getShirtLineColor() {
            return new Color(51, 51, 255);
        }

        public Color getSocksColor() {
            return new Color(51, 51, 255);
        }

        public Color getGoalKeeper() {
            return new Color(145, 247, 47        );
        }

        public EstiloUniforme getStyle() {
            return EstiloUniforme.FRANJA_HORIZONTAL;
        }

        public Color getShirtColor2() {
            return new Color(255, 255, 153);
        }

        public Color getShortsColor2() {
            return new Color(255, 255, 153);
        }

        public Color getShirtLineColor2() {
            return new Color(255, 255, 153);
        }

        public Color getSocksColor2() {
            return new Color(255, 255, 153);
        }

        public Color getGoalKeeper2() {
            return new Color(102, 255, 102        );
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

            @Override
            public String getPlayerName() {
                return nombre;
            }

            @Override
            public Color getSkinColor() {
                return piel;
            }

            @Override
            public Color getHairColor() {
                return pelo;
            }

            @Override
            public int getNumber() {
                return numero;
            }

            @Override
            public boolean isGoalKeeper() {
                return portero;
            }

            @Override
            public double getSpeed() {
                return velocidad;
            }

            @Override
            public double getPower() {
                return remate;
            }

            @Override
            public double getPrecision() {
                return presicion;
            }
        }

        @Override
//        public PlayerDetail[] getPlayers() {
//            return new PlayerDetail[]{
//                        new JugadorImpl("Christofer", 1, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 1.0d, 1.0d, true),
//                        new JugadorImpl("Ruben", 2, new Color(0, 0, 0), new Color(0, 0, 0), 1.0d, 0.53d, 0.78d, false),
//                        new JugadorImpl("Gabriel", 3, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.53d, 0.78d, false),
//                        new JugadorImpl("Gari", 4, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.53d, 0.78d, false),
//                        new JugadorImpl("Miguel", 5, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.53d, 0.78d, false),
//                        new JugadorImpl("Hugo", 6, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.53d, 0.78d, false),
//                        new JugadorImpl("Mark", 7, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.53d, 0.78d, false),
//                        new JugadorImpl("Mauricio", 8, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.53d, 0.78d, false),
//                        new JugadorImpl("Humberto", 9, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 1.0d, 1.0d, false),
//                        new JugadorImpl("Mago", 10, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 0.5d, 0.81d, false),
//                        new JugadorImpl("Alexis", 11, new Color(255, 200, 150), new Color(50, 0, 0), 1.0d, 1.0d, 1.0d, false)
//                    };
//        }
        public PlayerDetail[] getPlayers() {
            return new PlayerDetail[]{
                new JugadorImpl("cech", 1, new Color(255,200,150), new Color(50,0,0),1d,1d,1d, true),
                new JugadorImpl("ferreira", 2, new Color(255,200,150), new Color(50,0,0),1d,0.625d,0.5d, false),
                new JugadorImpl("therry", 3, new Color(255,200,150), new Color(50,0,0),1d,0.625d,0.5d, false),
                new JugadorImpl("ivanovich", 4, new Color(255,200,150), new Color(50,0,0),1d,0.625d,0.5d, false),
                new JugadorImpl("ashly cole", 5, new Color(255,200,150), new Color(50,0,0),1d,0.625d,0.5d, false),
                new JugadorImpl("michel", 6, new Color(255,200,150), new Color(50,0,0),1d,0.625d,0.5d, false),
                new JugadorImpl("malouda", 7, new Color(255,200,150), new Color(50,0,0),1d,0.625d,0.5d, false),
                new JugadorImpl("alex", 8, new Color(255,200,150), new Color(50,0,0),1d,0.625d,0.5d, false),
                new JugadorImpl("lampard", 9, new Color(255,200,150), new Color(50,0,0),1d,1d,1d, false),
                new JugadorImpl("anelka", 10, new Color(255,200,150), new Color(50,0,0),1d,1d,1d, false),
                new JugadorImpl("drogba", 11, new Color(255,200,150), new Color(50,0,0),1d,1d,1d, false)
            };
        }
    }
    TacticDetail detalle = new TacticaDetalleImpl();

    @Override
    public TacticDetail getDetail() {
        return detalle;
    }

    @Override
    public Position[] getStartPositions(GameSituations sp) {
        return alineacion5;
    }

    @Override
    public Position[] getNoStartPositions(GameSituations sp) {
        return alineacion6;
    }
    //Lista de comandos
   // LinkedList<Comando> comandos = new LinkedList<Comando>();

    List<Command> commands; // list of output commands
    List<Integer> availablePlayers; // Players who can be used for the strategy
    boolean attacking;
    MyPosition[] enemies;
    MyPosition[] myPlayers;
    List<Integer> dangerousEnemies;
    int[] ableToShot;
    PlayerDetail[] myPlayersDetails;
    PlayerDetail[] enemiesDetails;
    int[] myPlayersCantShoot;
    int[] enemiesCantShoot;
    MyPosition ballwithZ, theNextBall;
    double currentDx, currentDy, currentDz;
    MyPosition[] minDestinations, maxDestinations;
    boolean[] defense, middle, attack;

    public ChelseaMillos2() {
        commands = new ArrayList<Command>();
        availablePlayers = new ArrayList<Integer>();
        dangerousEnemies = new ArrayList<Integer>();
        ballwithZ = new MyPosition(new Position());
        theNextBall = new MyPosition(new Position());
        minDestinations = new MyPosition[align1.length];
        maxDestinations = new MyPosition[align1.length];
        myPlayers = new MyPosition[align1.length];
        enemies = new MyPosition[align1.length];
        defense = new boolean[align1.length];
        middle = new boolean[align1.length];
        attack = new boolean[align1.length];
        for (int i = 0; i < align1.length; ++i){
            defense[i] = i >= 1 && i <= 4;
            middle[i] = i >= 5 && i <= 7;
            attack[i] = i >= 8;
        }
        minDestinations[0] = align1[0];
        maxDestinations[0] = align1[0];
    }


    public void initRound(GameSituations sp) {

        // Clean the list of output commands
        commands.clear();
        // Players who can be used for the strategy
        availablePlayers.clear();
        for (int i = 0; i < 11; ++i){
            availablePlayers.add(i);
        }
        attacking = false;
        Position[] temp = sp.rivalPlayers();
        for (int i = 0; i < temp.length; ++i){
            enemies[i] = new MyPosition(temp[i]);
        }

        temp = sp.myPlayers();
        for (int i = 0; i < temp.length; ++i){
            myPlayers[i] = new MyPosition(temp[i]);
        }

        dangerousEnemies.clear();
        MyPosition myGoal = new MyPosition(0, -Constants.LARGO_CAMPO_JUEGO/2);
        while (enemies.length != dangerousEnemies.size()) {
            double minDist = 1000;
            int minJ = 0;
            for (int j = enemies.length - 1; j >= 0 ; --j){
                if (dangerousEnemies.contains(new Integer(j))) {
                    continue;
                }
                double dist = myGoal.distance(enemies[j]);
                if (dist < minDist) {
                    minDist = dist;
                    minJ = j;
                }
            }
            dangerousEnemies.add(new Integer(minJ));
        }
        ableToShot = sp.canKick();
        myPlayersDetails = sp.myPlayersDetail();
        enemiesDetails = sp.rivalPlayersDetail();
        myPlayersCantShoot = sp.iterationsToKick();
        enemiesCantShoot = sp.rivalIterationsToKick();

        MyPosition oldBallwithZ = ballwithZ;
        ballwithZ = new MyPosition(sp.ballPosition());
        ballwithZ.z = sp.ballAltitude();

        //System.out.println(ballwithZ);

        for (int i = 1; i < minDestinations.length; ++i){
            if (attack[i]) {
                minDestinations[i] = new MyPosition(minX[i], Math.min(Constants.LARGO_CAMPO_JUEGO/2 - 10, (Math.max(0, ballwithZ.y))));
                maxDestinations[i] = new MyPosition(maxX[i], (Math.max(0,Math.min(Constants.LARGO_CAMPO_JUEGO/2 - 10, ballwithZ.y + 30))));
            } else if (middle[i]) {
                minDestinations[i] = new MyPosition(minX[i], Math.min(Constants.LARGO_CAMPO_JUEGO/2 - 10, (Math.max(-Constants.LARGO_CAMPO_JUEGO/2 + 10, ballwithZ.y - 20))));
                maxDestinations[i] = new MyPosition(maxX[i], (Math.max(-Constants.LARGO_CAMPO_JUEGO/2 + 10,Math.min(Constants.LARGO_CAMPO_JUEGO/2 - 10, ballwithZ.y + 20))));
            }
            else {
                minDestinations[i] = new MyPosition(align1[i]);
                maxDestinations[i] = new MyPosition(align1[i]);
            }
            /*
            if (ballwithZ.getY() > 0 && !myPlayersDetails[i].isGoalKeeper()){
                destinations[i] = align1[i].getDiffPosition(0, ballwithZ.getY());
                if (isOut(destinations[i])) {
                    destinations[i] = align1[i];
                }
            } else {
                destinations[i] = align1[i];
            }
            */
        }

        /*
        System.out.println("X : " + ballwithZ.getX() + "/" + theNextBall.getX());
        System.out.println("Y : " + ballwithZ.getY() + "/" + theNextBall.getY());
        System.out.println("Z : " + ballwithZ.z + "/" + theNextBall.z);
        */

        /* Calculate Dz, Dx, Dy */
        currentDz = ballwithZ.z - oldBallwithZ.z;
        if (currentDz != 0) {currentDz -= Constants.G; }
        if (ballwithZ.z == 0 && currentDz < 0) {
            currentDz = (-currentDz - Constants.G) * Constants.FACTOR_DISMINUCION_ALTURA_BALON_REBOTE;
            currentDz = roundAtMultiple(currentDz, Constants.G);
        }

        currentDx = ballwithZ.getX() - oldBallwithZ.getX();
        currentDy = ballwithZ.getY() - oldBallwithZ.getY();

        boolean onGround = (ballwithZ.z == 0 && currentDz >= 0 && currentDz < Constants.G * 3);
        if (onGround) {
            currentDx *= Constants.FACTOR_DISMINUCION_VEL_BALON_SUELO;
            currentDy *= Constants.FACTOR_DISMINUCION_VEL_BALON_SUELO;
        } else {
            currentDx *= Constants.FACTOR_DISMINUCION_VEL_BALON_AIRE;
            currentDy *= Constants.FACTOR_DISMINUCION_VEL_BALON_AIRE;
        }
    }

    public class MyPosition {
        double x, y, z;

        public MyPosition() {
            x = y = z = 0;
        }
        public MyPosition(MyPosition p) {
            x = p.x;
            y = p.y;
            z = p.z;
        }
        public MyPosition(Position p) {
            x = p.getX();
            y = p.getY();
            z = 0;
        }
        public MyPosition(double x, double y) {
            this.x = x;
            this.y = y;
            this.z = 0;
        }
        public MyPosition(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }

        public double distanceSQ(double px, double py) {
            return (x - px) * (x - px) + (y - py) * (y - py);
        }
        public double distance() {
            return this.distance(new MyPosition());
        }
        public double distance(double px, double py) {
            return Math.sqrt((x - px) * (x - px) + (y - py) * (y - py));
        }
        public double distance(MyPosition p) {
            return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
        }
        public double distance(Position p) {
            return this.distance(new MyPosition(p));
        }
        public Position getPosicion() {
            return new Position(x,y);
        }
        public MyPosition getDiffPosition(double dx, double dy) {
            return new MyPosition(this.x + dx, this.y + dy);
        }

        private void moveDiff(double dx, double dy) {
            this.x += dx;
            this.y += dy;
        }

        public String toString() {
            return "(" + this.x + ", " + this.y + ", " + this.z + ")";
        }
    }

    public int determineGoal(MyPosition lastBall, MyPosition ball) {
        double posY = 0;
        if (ball.y < 0) {
            posY = -Constants.LARGO_CAMPO_JUEGO / 2;
        } else {
            posY = Constants.LARGO_CAMPO_JUEGO / 2;
        }
        double newDy = lastBall.y - ball.y;
        double posZ;
        double posX;

        if (newDy != 0) {
            posZ = ((lastBall.z - ball.z) / newDy) * (posY - ball.y) + ball.z;
        } else {
            posZ = ball.z;
        }
        if (Math.abs(ball.getY()) >= Constants.LARGO_CAMPO_JUEGO / 2) {
            if (posZ <= Constants.ALTO_ARCO) {

                if (newDy != 0) {
                    posX = ((lastBall.x - ball.x) / newDy) * (posY - ball.y) + ball.x;
                } else {
                    posX = ball.x;
                }

                if (Math.abs(posX) < Constants.LARGO_ARCO / 2 - Constants.RADIO_BALON) {
                    if (ball.getY() < 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    public class DecisionScore {
        boolean forUs;
        int turn;
        int receiver;
        MyPosition finalBall;
        int isGoal;

        int shooter;
        double strength;
        double angle;
        double angleZ;

        MyPosition[] evol;

        private DecisionScore() {}

        public DecisionScore(DecisionScore ds) {
            this.turn = ds.turn;
            this.forUs = ds.forUs;
            this.receiver = ds.receiver;
            this.finalBall = ds.finalBall;
            this.isGoal = ds.isGoal;
            this.shooter = ds.shooter;
            this.strength = ds.strength;
            this.angle = ds.angle;
            this.angleZ = ds.angleZ;
            this.evol = ds.evol.clone();
        }
        public DecisionScore(MyPosition[] ball) {
            this(ball, false);
        }

        public DecisionScore(MyPosition[] ball, boolean onlyMyPlayers) {

            evol = ball.clone();

            Mainloop : for (int k = 1; k < ball.length; ++k){
                isGoal = determineGoal(ball[k-1], ball[k]);
                if (isGoal != 0 || isOut(ball[k])) {
                    turn = k;
                    forUs = false;
                    break Mainloop;
                }

                if (k > 0) {
                    if (!onlyMyPlayers){
                        for (int j = 0; j < enemies.length; ++j){
                            if (isReachable(ball[k], enemiesDetails[j], enemies[j], enemiesCantShoot[j], k)){
                                turn = k;
                                receiver = j;
                                forUs = false;
                                break Mainloop;
                            }
                        }
                    }

                    for (int i = 0; i < myPlayers.length; ++i){
                        if (isReachable(ball[k], myPlayersDetails[i], myPlayers[i], myPlayersCantShoot[i], k)){
                            turn = k;
                            receiver = i;
                            forUs = true;
                            break Mainloop;
                        }
                    }
                }
            }

            finalBall = ball[turn];

        }

        public boolean isReachable(MyPosition ball, PlayerDetail jd, MyPosition currentPos, int cantShoot, int turn){
            if (cantShoot <= turn){
                double maxHeight = Constants.ALTURA_CONTROL_BALON;
                double maxDist = Constants.DISTANCIA_CONTROL_BALON;
                if (jd.isGoalKeeper() && Math.abs(currentPos.getX()) <= Constants.LARGO_AREA_GRANDE / 2 && Math.abs(currentPos.getY()) > Constants.ANCHO_AREA_GRANDE){
                    maxDist = Constants.DISTANCIA_CONTROL_BALON_PORTERO;
                    maxHeight = Constants.ALTO_ARCO;
                }
                if (ball.z > maxHeight) {
                    return false;
                }

                double dist = ball.distance(currentPos);
                return dist - maxDist <= turn * Constants.getVelocidad(jd.getSpeed());
            } else {
                return false;
            }
        }

        public boolean isBetter(DecisionScore ds) {
            if (this.isGoal != ds.isGoal) {
                return this.isGoal > ds.isGoal;
            }

            if (this.forUs != ds.forUs) {
                return this.forUs;
            }

            if (attack[this.receiver] != attack[ds.receiver]) {
                return attack[this.receiver];
            }

            if (isOut(this.finalBall) != isOut(ds.finalBall)){
                return !isOut(this.finalBall);
            }

            double thisDist = this.finalBall.distance(Constants.centroArcoSup);
            double dsDist = ds.finalBall.distance(Constants.centroArcoSup);
            if (thisDist != dsDist) {
                return thisDist < dsDist;
            }

            if (this.strength != ds.strength) {
                return this.strength > ds.strength;
            }

            return false;
        }
    }

    public class AverageDecisionScore extends DecisionScore {

        int count;
        double countForUs;
        double countIsGoal;
        
        public AverageDecisionScore (DecisionScore ds, ArrayList<DecisionScore> dsList, double errorAngle) {
            super(ds);
            finalBall = new MyPosition();
            count = 0;
            countIsGoal= 0;
            countForUs = 0;
            double errorAngleDeg = errorAngle * 180 / Math.PI;
            //System.out.println(errorAngleDeg + " =+= " + errorAngle);
            for (DecisionScore ds2 : dsList) {
                if (Math.abs(ds2.angle - ds.angle) <= errorAngleDeg) {

                    finalBall = new MyPosition((finalBall.getX()*count + ds2.finalBall.getX()) / (count + 1),
                            (finalBall.getY()*count + ds2.finalBall.getY()) / (count + 1), ds.finalBall.z);
                    if (ds2.forUs) {
                        ++countForUs;
                    }
                    if (ds2.isGoal != 0) {
                        countIsGoal += ds2.isGoal;
                    }
                    ++count;
                }
            }
        }

        public boolean isBetter(AverageDecisionScore avgDs) {
            if (countIsGoal/count != avgDs.countIsGoal/avgDs.count) {
                return countIsGoal/count > avgDs.countIsGoal/avgDs.count;
            }

            if (countForUs/count != avgDs.countForUs/avgDs.count) {
                return countForUs/count > avgDs.countForUs/avgDs.count;
            }

            return super.isBetter(avgDs);
        }
    }

    public AverageDecisionScore determineWhereToShot(int player, MyPosition ball) {
        AverageDecisionScore dsMin = null;
        double errorAngle = Constants.getErrorAngular(myPlayersDetails[player].getPrecision());

        int oldCannotShot = myPlayersCantShoot[player];
        double dz, oldDz = -1;
        myPlayersCantShoot[player] = Constants.ITERACIONES_GOLPEAR_BALON + 1;
        for (double strength = 0.1d; strength < 1.01; strength += 0.15d) {
            double speed = strength * Constants.getVelocidadRemate(myPlayersDetails[player].getPower());
            for (int angleZ = 0; angleZ <= 61; ++angleZ) {
                double radAngleZ = Math.max(0, Math.min(angleZ, Constants.ANGULO_VERTICAL_MAX)) * (Math.PI / 180d);
                double tempSpeed = speed * Math.cos(radAngleZ);
                dz = roundAtMultiple(tempSpeed * Math.sin(radAngleZ), Constants.G);
                if (dz == oldDz) {
                    continue;
                }
                oldDz = dz;
                //System.out.println("dz = " + dz);

                ArrayList<DecisionScore> dsList = new ArrayList<DecisionScore>();
                double anglestep = 3;
                for (double angle = 0; angle < 360; angle+=anglestep) {
                    MyPosition[] evolution = predictShot(player, ball, strength, angle, angleZ);
                    DecisionScore ds = new DecisionScore(evolution);
                    ds.strength = strength;
                    ds.angle = angle;
                    ds.angleZ = angleZ;
                    dsList.add(ds);
                    if (angle > 20 && angle < 160) {
                        anglestep = 2;
                    } else {
                        anglestep = 5;
                    }
                }
                for (DecisionScore ds : dsList) {
                    AverageDecisionScore avgDs = new AverageDecisionScore(ds, dsList, errorAngle);
                    if (dsMin == null || avgDs.isBetter(dsMin)) {
                        dsMin = avgDs;
                    }
                }
            }
        }

        myPlayersCantShoot[player] = oldCannotShot;


        dsMin.shooter = player;
        return dsMin;
    }

    private double roundAtMultiple(double valor, double divisor) {
        return Math.round(valor / divisor) * divisor;
    }

    public static final int MAX_PREVISION = 100;
    
      

    public MyPosition[] predictShot(int player, MyPosition ball, double iStrength, double iAngle, double iAngleZ){
        double angle = iAngle * (Math.PI / 180d);
        double speed = iStrength * Constants.getVelocidadRemate(myPlayersDetails[player].getPower());
        double angleZ = Math.max(0, Math.min(iAngleZ, Constants.ANGULO_VERTICAL_MAX)) * (Math.PI / 180d);

        speed = speed * Math.cos(angleZ);
        double ballDx = 0,  ballDy = 0,  ballDz = 0;
        ballDz = roundAtMultiple(speed * Math.sin(angleZ), Constants.G);
        ballDx = Math.cos(angle) * speed;
        ballDy = Math.sin(angle) * speed;

        MyPosition newBall = new MyPosition(ball);
        newBall.z = 0;
        return predictBall(newBall, ballDx, ballDy, ballDz, MAX_PREVISION);
    }

    public boolean isOut(Position b){
        double MITAD_ANCHO = Constants.ANCHO_CAMPO_JUEGO / 2;
        double MITAD_LARGO = Constants.LARGO_CAMPO_JUEGO / 2;
        return !(b.getX() >= -MITAD_ANCHO && b.getX() <= MITAD_ANCHO &&
                b.getY() >= -MITAD_LARGO && b.getY() <= MITAD_LARGO);
    }

    public boolean isOut(MyPosition b){
        double MITAD_ANCHO = Constants.ANCHO_CAMPO_JUEGO / 2;
        double MITAD_LARGO = Constants.LARGO_CAMPO_JUEGO / 2;
        return !(b.getX() >= -MITAD_ANCHO && b.getX() <= MITAD_ANCHO &&
                b.getY() >= -MITAD_LARGO && b.getY() <= MITAD_LARGO);
    }
    private AbstractTrajectory trayectoria = new FloorTrajectory(0, 0);
     private AbstractTrajectory _trayectoria = new FloorTrajectory(0, 0);
    double iteracionReal = 0;
    double iteracion = 0;
    private double _angTrayectoria = 0, _x0Trayectoria = 0, _y0Trayectoria = 0, _t0Trayectoria;
    private double angTrayectoria = 0, x0Trayectoria = 0, y0Trayectoria = 0, t0Trayectoria;
    private double balonDx0 = 0, balonDy0 = 0, balonDz0 = 0;
    private Position balon0;
    private double balonDx = 0, balonDy = 0, balonDz = 0;//velocidad del ballPosition
    private double balonDx2 = 0, balonDy2 = 0;//velocidad del ballPosition cuando sale del campo
    private boolean rebote = false;//true cuando rebota el ballPosition en el pasto
    public MyPosition[] predictBall(MyPosition ball, double iBallDx, double iBallDy, double iBallDz, int previsions){
        MyPosition[] res = new MyPosition[previsions];
        res[0] = ball;
        double ballDx = iBallDx,  ballDy = iBallDy,  ballDz = iBallDz;

        for (int step = 1; step < previsions; ++step) {
            MyPosition nextBall = new MyPosition(res[step-1]);
            boolean onGround = (nextBall.z == 0 && ballDz >= 0 && ballDz < Constants.G * 3);
            if (isOut(nextBall)){
                res[step] = res[step-1];
                continue;
            }
            if (onGround) {
                nextBall.moveDiff(ballDx, ballDy);
                ballDx = ballDx * Constants.FACTOR_DISMINUCION_VEL_BALON_SUELO;
                ballDy = ballDy * Constants.FACTOR_DISMINUCION_VEL_BALON_SUELO;
            } else {
                nextBall.z = roundAtMultiple(nextBall.z + ballDz, Constants.G);
                nextBall.moveDiff(ballDx, ballDy);
                ballDz = roundAtMultiple(ballDz - Constants.G, Constants.G);
                ballDx = ballDx * Constants.FACTOR_DISMINUCION_VEL_BALON_AIRE;
                ballDy = ballDy * Constants.FACTOR_DISMINUCION_VEL_BALON_AIRE;
                if (nextBall.z == 0) {
                    ballDz = (-ballDz - Constants.G) * Constants.FACTOR_DISMINUCION_ALTURA_BALON_REBOTE;
                    ballDz = roundAtMultiple(ballDz, Constants.G);
                }
            }
            res[step] = nextBall;
            /*////////////////////////////////////////////////////////////////////////
            iteration=iteracionReal++;
            double timeReal = (iteracionReal - _t0Trayectoria + 1) / 60d;
            double time = (iteration - t0Trayectoria + 1) / 60d;
            double time0 = (iteration - t0Trayectoria) / 60d;
            double trX = trayectoria.getX(time) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
            double _trX = _trayectoria.getX(timeReal) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
            double _trY = _trayectoria.getY(timeReal) * Constants.AMPLIFICA_VEL_TRAYECTORIA;

            nextBall = new Position(x0Trayectoria + trX * Math.cos(angTrayectoria),
                    y0Trayectoria + trX * Math.sin(angTrayectoria));
//            Position balonVisible = new Position(_x0Trayectoria + _trX * Math.cos(_angTrayectoria),
//                    _y0Trayectoria + _trX * Math.sin(_angTrayectoria)).getPosicionInvertida();
            double ballAltitude = _trY * 2;
            if (time0 > 0) {
                double trX0 = trayectoria.getX(time0) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
                double trY0 = trayectoria.getY(time0) * Constants.AMPLIFICA_VEL_TRAYECTORIA;
                Posicion b0 = new Posicion(x0Trayectoria + trX0 * Math.cos(angTrayectoria),
                        y0Trayectoria + trX0 * Math.sin(angTrayectoria));
                double alturaBalon0 = trY0 * 2;
                balonDx0 = balonDx;
                balonDy0 = balonDy;
                balonDz0 = balonDz;
                balonDx = ball.getX() - b0.getX();
                balonDy = ball.getY() - b0.getY();
                balonDz = ballAltitude - alturaBalon0;
                rebote = trayectoria.rebote1(time0, time);
            } else {
                rebote = false;
            }
            res[step] = nextBall;
            ////////////////////////////////////////////////////////////////////////*/
        }
        return res;
    }

    int nbturns = 0;

    @Override
    public List<Command> execute(GameSituations sp) {
        // Initialize the round
        initRound(sp);
        ++nbturns;
        double anguloZ =0;;
        //Instancia un generador aleatorio
        Random r = new Random();

        if (ableToShot.length > 0) {
            AverageDecisionScore ds =  determineWhereToShot(ableToShot[0], ballwithZ);
            if (ds == null) {
                //System.out.println("ds null !!");
            } else {
                //System.out.println(myPlayersDetails[ds.shooter].getNumber() + " === " + ds.count);
               if ( ds.shooter > 8  ){
                   anguloZ = 11;
                   commands.add(new CommandHitBall(ds.shooter, ds.angle, ds.strength, anguloZ));
                   //System.out.println(" dispara jugador > 8  fuerza "+ds.strength +" "+sp.myPlayers()[ds.shooter].getX()+" "+sp.myPlayers()[ds.shooter].getY()+" anguloZ "+ds.angleZ+" anguloZ_Disparado "+anguloZ);
               } else {
                   if( ds.strength == 1 ){
                       anguloZ = 11;
                   } else {
                       anguloZ = ds.angleZ;
                   }
                   commands.add(new CommandHitBall(ds.shooter, ds.angle, ds.strength, anguloZ));
                  // System.out.println(" dispara jugador <= 8  fuerza "+ds.strength +" "+sp.myPlayers()[ds.shooter].getX()+" "+sp.myPlayers()[ds.shooter].getY()+" anguloZ "+ds.angleZ+" anguloZ_Disparado "+anguloZ);
               }
              //   commands.add(new CommandHitBall(ds.shooter, Constants.centroArcoInf, ds.strength, ds.angleZ));
//                if (r.nextBoolean()) {
//                    //Ordena que debe rematar al centro del arco
//                    commands.add(new CommandHitBall(i, Constants.centroArcoSup, 1, 12 + r.nextInt(6)));
//                } else if (r.nextBoolean()) {
//                    //Ordena que debe rematar al poste derecho
//                    commands.add(new CommandHitBall(i, Constants.posteDerArcoSup, 1, 12 + r.nextInt(6)));
//                } else {
//                    //Ordena que debe rematar al poste izquierdo
//                    commands.add(new CommandHitBall(i, Constants.posteIzqArcoSup, 1, 12 + r.nextInt(6)));
//                }
                availablePlayers.remove(new Integer(ds.shooter));

                if (ds.forUs) {
                    commands.add(new CommandMoveTo(ds.receiver, ds.finalBall.getPosicion()));
                    availablePlayers.remove(new Integer(ds.receiver));
                    //System.out.println(myPlayersDetails[ds.shooter].getNumber() + "->" + myPlayersDetails[ds.receiver].getNumber() + " in " + ds.turn + " turns to (" + ds.finalBall.getX() + "," + ds.finalBall.getY() + ") : proba = " + (ds.countForUs/ds.count));
                } else if (ds.isGoal != 0) {
                    /*System.out.println(myPlayersDetails[ds.shooter].getNumber() + " shoots to score ! : proba = " + (ds.countIsGoal/ds.count));
                    for (int i = 0; i < ds.evol.length ; ++ i){
                     System.out.println("Evol " + i + ": " + ds.evol[i]);
                    }*/
                } else {
                    //System.out.println(myPlayersDetails[ds.shooter].getNumber() + "-> ??? in " + ds.turn + " turns to (" + ds.finalBall.getX() + "," + ds.finalBall.getY() + ") : probaForUs = " + (ds.countForUs/ds.count) + " probaToScore = " + (ds.countIsGoal/ds.count));
                }
            }
        }

        {
            MyPosition[] evolution = predictBall(ballwithZ, currentDx, currentDy, currentDz, MAX_PREVISION);
            DecisionScore ds = new DecisionScore(evolution);
            theNextBall = evolution[1];
            attacking = ds.forUs || ballwithZ.getY() > 0;
        }

        { // The player who will be the nearest to the ball have to go there
            MyPosition[] evolution = predictBall(ballwithZ, currentDx, currentDy, currentDz, MAX_PREVISION);
            DecisionScore ds = new DecisionScore(evolution, true);
            if (ds.forUs){
                commands.add(new CommandMoveTo(ds.receiver, ds.finalBall.getPosicion()));
                availablePlayers.remove(new Integer(ds.receiver));
            } else if (ds.isGoal != 0) {
                commands.add(new CommandMoveTo(0, ds.finalBall.getPosicion()));
                availablePlayers.remove(new Integer(0));
            }
        }

        for (int j : dangerousEnemies) {
            int iMin = -1;
            double distMin = 0;
            for (int i : availablePlayers) {
                if (i == 0 || (attacking && !defense[i])) continue;
                double dist = enemies[j].distance(myPlayers[i]);
                if (dist < distMin || iMin < 0) {
                    iMin = i;
                    distMin = dist;
                }
            }
            if (iMin < 0) break;

            commands.add(new CommandMoveTo(iMin, enemies[j].getPosicion().movePosition(0, -1)));
            availablePlayers.remove(new Integer(iMin));
        }


        for (int i : availablePlayers) {
            int jMin = -1;
            double distMin = 0;
            for (int j = 0; j < enemies.length; ++j) {
                double dist = enemies[j].distance(myPlayers[i]);
                if (dist < distMin || jMin < 0) {
                    jMin = j;
                    distMin = dist;
                }
            }
            Position diff = new Position(myPlayers[i].getX() - enemies[jMin].getX(), myPlayers[i].getY() - enemies[jMin].getY());
            double dist = diff.distance();
            if (dist == 0) {
                diff = new Position(0, 1);
            } else {
                diff = new Position(diff.getX() / dist, diff.getY() / dist);
            }

            dist = Constants.getVelocidad(myPlayersDetails[i].getSpeed());
            MyPosition destination = myPlayers[i].getDiffPosition(diff.getX()*dist, diff.getY()*dist);

            MyPosition tempDest = new MyPosition((minDestinations[i].x + maxDestinations[i].x)/2, (minDestinations[i].y + maxDestinations[i].y)/2);
            dist = destination.distance(tempDest);
            double maxDist = 0;
            for (double x = minDestinations[i].x; x <= maxDestinations[i].x; ++x) {
                for (double y = minDestinations[i].y; y <= maxDestinations[i].y; ++y) {
                    dist = destination.distanceSQ(x,y);
                    if (dist > maxDist) {
                        maxDist = dist;
                        tempDest.x = x;
                        tempDest.y = y;
                    }
                }
            }
            //System.out.println("Destination set for player " + myPlayersDetails[i].getNumber());
            commands.add(new CommandMoveTo(i, tempDest.getPosicion()));

        }

        return commands;
    }
}
