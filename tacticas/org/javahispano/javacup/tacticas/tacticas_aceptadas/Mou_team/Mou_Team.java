package org.javahispano.javacup.tacticas.tacticas_aceptadas.Mou_team;

import org.javahispano.javacup.model.engine.GameSituations;
import org.javahispano.javacup.model.util.Position;
import org.javahispano.javacup.model.TacticDetail;
import org.javahispano.javacup.model.Tactic;
import org.javahispano.javacup.model.command.Command;
import java.awt.Color;
import java.util.LinkedList;
import org.javahispano.javacup.model.*;
import java.util.List;

public class Mou_Team implements Tactic {

    public Mou_Team() {
        this.laTactica = new Tactica_Mou_Team();
        this.detalle = new TacticaDetalleImpl();
    }

    public Mou_Team(double prob,double prob2) {
        this.laTactica = new Tactica_Mou_Team(prob,prob2);
        this.detalle = new TacticaDetalleImpl();
    }
    private Tactica_Mou_Team laTactica;
    private TacticDetail detalle;

    public TacticDetail getDetail() {
        return detalle;
    }

    public Position[] getStartPositions(GameSituations sp) {
        return laTactica.getStartPositions(sp);
    }

    public Position[] getNoStartPositions(GameSituations sp) {
        return laTactica.getNoStartPositions(sp);
    }

    public List<Command> execute(GameSituations sp) {
        return laTactica.generarTactica(sp);
    }

    public int getGolesCulpaDistancia() {
        return laTactica.golCulpaMiaDistancia;
    }

    public int getGolesCulpaAltura() {
        return laTactica.golCulpaMiaAltura;

    }
}
