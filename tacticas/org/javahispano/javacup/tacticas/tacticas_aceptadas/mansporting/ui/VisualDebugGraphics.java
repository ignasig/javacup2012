/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javahispano.javacup.tacticas.tacticas_aceptadas.mansporting.ui;

import java.awt.Color;

/**
 *
 * @author MaN
 */
public interface VisualDebugGraphics {

    void fillRect(double x, double y, double width, double height);

    void fillOval(double x, double y, double width, double height);

    void drawRect(double x, double y, double width, double height);

    void drawOval(double x, double y, double width, double height);

    void drawLine(double x1, double y1, double x2, double y2);

    void drawArc(double x, double y, double width, double height, double startAngle, double arcAngle);

    void setColor(Color color);

    Color getColor();
}
