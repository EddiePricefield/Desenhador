/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meupaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 *
 * @author Eddie
 */
public class Borracha extends Forma {

    private final ArrayList<Integer> MouseX = new ArrayList<>();
    private final ArrayList<Integer> MouseY = new ArrayList<>();
    private Color BORRACHA = new Color(214, 217, 223);

    @Override
    public void desenhar(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        //Cria um contexto gráfico novo

        g2.setStroke(new BasicStroke(tamanhoContorno, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(BORRACHA);

        Path2D.Double pincel = new Path2D.Double();

        //Ponto inicial
        pincel.moveTo(x1, y1);

        //Conexões conforme o mouse vai andando
        for (int i = 0; i < MouseX.size(); i++) {
            pincel.lineTo(MouseX.get(i), MouseY.get(i));
        }

        g2.draw(pincel);
        g2.dispose();

    }

    public void setX2(int valorX) {
        MouseX.add(valorX);
    }

    public void setY2(int valorY) {
        MouseY.add(valorY);
    }

}
