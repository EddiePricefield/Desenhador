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
public class Triangulo extends Forma{ //Ao invés de fazer uma lista para cada coordenada, criamos um objeto que contém as 4 coordenadas e colocamos esses objetos na lista
    
    private int[] xPoints;
    private int[] yPoints;
    
    @Override
    public void desenhar( Graphics g ){
        
       //Pegando os pontos de um retângulo com base nos pontos X e Y do mouse
       int x1d = x1 < x2 ? x1 : x2;
       int y1d = y1 < y2 ? y1 : y2;
       
       int x2d = x1 > x2 ? x1 : x2;
       int y2d = y1 > y2 ? y1 : y2;
       
       //Construindo um triângulo isósceles com base no retângulo construído pelo mouse
       
       xPoints = new int[]{x1d, ( x2d + x1d ) / 2, x2d};
       yPoints = new int[]{y2d, y1d, y2d} ;      
       
       Graphics2D g2 = ( Graphics2D ) g.create();
       
       //da pra usar um IF caso ele seja uma cor de preenchimento nula, pra nao usar o preenchimento só o contorno
       
       g2.setStroke(new BasicStroke(tamanhoContorno));
       g2.setColor( preenchimento );
       g2.fillPolygon(xPoints, yPoints, 3);
       g2.setColor( contorno );
       g2.drawPolygon(xPoints, yPoints, 3);
       
       g2.dispose();
        
    }
    
}
