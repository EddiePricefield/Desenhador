/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meupaint;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Eddie
 */
public class Poligono extends Forma { //Ao invés de fazer uma lista para cada coordenada, criamos um objeto que contém as 4 coordenadas e colocamos esses objetos na lista

    private int[] xPoints;
    private int[] yPoints;
    private int lados;
    
    public Poligono(int lados){
        this.lados = lados;
    }

    @Override
    public void desenhar(Graphics g) {

        //Construindo um polígono regular
        xPoints = new int[lados];
        yPoints = new int[lados];
        
        for (int i = 0; i < lados; i++){
            double angulo = ((2 * Math.PI) / lados) * i; //Temos que 360° = 2pi rad
            xPoints[i] = (int)(x1 + (x2 - x1)*Math.cos(angulo) - (y2 - y1)*Math.sin(angulo));
            yPoints[i] = (int)(y1 + (x2 - x1)*Math.sin(angulo) + (y2 - y1)*Math.cos(angulo));
            
        }

        Graphics2D g2 = (Graphics2D) g.create();

        //da pra usar um IF caso ele seja uma cor de preenchimento nula, pra nao usar o preenchimento só o contorno
        g2.setStroke(new BasicStroke(tamanhoContorno));
        g2.setColor(preenchimento);
        g2.fillPolygon(xPoints, yPoints, lados);
        g2.setColor(contorno);
        g2.drawPolygon(xPoints, yPoints, lados);

        g2.dispose();

    }

}
