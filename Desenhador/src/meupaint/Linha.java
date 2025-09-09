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
public class Linha extends Forma { //Ao invés de fazer uma lista para cada coordenada, criamos um objeto que contém as 4 coordenadas e colocamos esses objetos na lista

    @Override
    public void desenhar(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        //Cria um contexto gráfico novo

        g2.setStroke(new BasicStroke(tamanhoContorno, BasicStroke.CAP_ROUND, BasicStroke.CAP_BUTT));
        g2.setColor(contorno);
        g2.drawLine(x1, y1, x2, y2);

        g2.dispose(); //Joga ele fora

    }

}
