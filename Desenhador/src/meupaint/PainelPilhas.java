/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meupaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Eddie
 */
public class PainelPilhas extends JPanel {

    private JanelaPrincipal janelaPrincipal;
    private String formaDesfazer;
    private String formaRefazer;

    Color ORANGE = new Color(201, 94, 8);
    
    public PainelPilhas(){}

    public PainelPilhas(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        formaRefazer = "";
        formaDesfazer = "";
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        //Retângulo para inserir o undoForma() e o redoForma()
        g2.drawRect(40, 30, 110, 80);
        g2.drawRect(250, 30, 110, 80);

        //Desenho das Formas dentro do Retângulo PRECISO DAR REPAINT() NA JANELA DE PILHAS QUANDO EU CHAMAR O NEGOCIO
        g2.setStroke(new BasicStroke(3));
        switch (formaDesfazer) {
            case "Pincel":
                Image pincelU = new ImageIcon(getClass().getResource("/images/pincelU.png")).getImage();
                g2.drawImage(pincelU, 40, 30, this);
                break;
            case "Borracha":
                Image borrachaU = new ImageIcon(getClass().getResource("/images/borrachaU.png")).getImage();
                g2.drawImage(borrachaU, 40, 30, this);
                break;
            case "Linha":
                g2.setColor(ORANGE);
                g2.drawLine(75, 50, 115, 90);
                break;
            case "Retangulo":
                g2.setColor(ORANGE);
                g2.drawRect(65, 45, 65, 50);
                break;
            case "Elipse":
                g2.setColor(ORANGE);
                g2.drawOval(65, 40, 60, 60);
                break;
            case "Triangulo":
                g2.setColor(ORANGE);
                int[] xPoints = {95, 70, 120};
                int[] yPoints = {45, 95, 95};
                g2.drawPolygon(xPoints, yPoints, 3);
                break;
            case "Poligono":
                g2.setColor(ORANGE);
                int[] xPointsPoly = {95, 123, 112, 78, 67};
                int[] yPointsPoly = {42, 63, 97, 97, 63};
                g2.drawPolygon(xPointsPoly, yPointsPoly, 5);
                break;
            case "LimparTudo":
                g2.setColor(ORANGE);
                g2.drawLine(70, 45, 120, 95);
                g2.drawLine(70, 95, 120, 45);
                break;
            case "RedesenharTudo":
                g2.setColor(ORANGE);
                g2.drawRect(70, 45, 20, 20);
                g2.drawOval(100, 45, 20, 20);
                int[] xTri1 = {95, 85, 105};
                int[] yTri1 = {75, 95, 95};
                g2.drawPolygon(xTri1, yTri1, 3);
                break;
            case "":
                break;

        }

        switch (formaRefazer) {
            case "Pincel":
                Image pincelR = new ImageIcon(getClass().getResource("/images/pincelR.png")).getImage();
                g2.drawImage(pincelR, 250, 30, this);
                break;
            case "Borracha":
                Image borrachaU = new ImageIcon(getClass().getResource("/images/borrachaR.png")).getImage();
                g2.drawImage(borrachaU, 250, 30, this);
                break;
            case "Linha":
                g2.setColor(Color.BLUE);
                g2.drawLine(285, 50, 325, 90);
                break;
            case "Retangulo":
                g2.setColor(Color.BLUE);
                g2.drawRect(275, 45, 65, 50);
                break;
            case "Elipse":
                g2.setColor(Color.BLUE);
                g2.drawOval(275, 40, 60, 60);
                break;
            case "Triangulo":
                g2.setColor(Color.BLUE);
                int[] xPoints = {305, 280, 330};
                int[] yPoints = {45, 95, 95};
                g2.drawPolygon(xPoints, yPoints, 3);
                break;
            case "Poligono":
                g2.setColor(Color.BLUE);
                int[] xPointsPoly = {305, 333, 322, 288, 277};
                int[] yPointsPoly = {42, 63, 97, 97, 63};
                g2.drawPolygon(xPointsPoly, yPointsPoly, 5);
                break;
            case "LimparTudo":
                g2.setColor(Color.BLUE);
                g2.drawLine(280, 45, 330, 95);
                g2.drawLine(280, 95, 330, 45);
                break;
            case "RedesenharTudo":
                g2.setColor(Color.BLUE);
                g2.drawRect(280, 45, 20, 20);
                g2.drawOval(310, 45, 20, 20);
                int[] xTri2 = {305, 295, 315};
                int[] yTri2 = {75, 95, 95};
                g2.drawPolygon(xTri2, yTri2, 3);
                break;
            case "":
                break;
        }

        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);

        g2.drawLine(10, 180, 390, 180);

        if (janelaPrincipal.tamanhoStacks(1) != 0) {
            desenharStack(g2, janelaPrincipal.tamanhoStacks(1), 100, 130, 5, 10, 3, ORANGE);
        }
        if (janelaPrincipal.tamanhoStacks(2) != 0) {
            desenharStack(g2, janelaPrincipal.tamanhoStacks(2), 95, 158, 5, 10, 3, Color.BLUE);
        }
        if (janelaPrincipal.tamanhoStacks(3) != 0) {
            desenharStack(g2, janelaPrincipal.tamanhoStacks(3), 130, 193, 5, 10, 3, ORANGE);
        }
        if (janelaPrincipal.tamanhoStacks(4) != 0) {
            desenharStack(g2, janelaPrincipal.tamanhoStacks(4), 125, 221, 5, 10, 3, Color.BLUE);
        }

    }

    private void desenharStack(Graphics2D g2, int tamanhoStack, int xIni, int yIni, int largura, int altura, int espaco, Color cor) {

        for (int i = 0; i < tamanhoStack; i++) {

            int x = xIni + i * (largura + espaco);

            g2.setColor(cor);
            g2.fillRect(x, yIni, largura, altura);

            g2.setColor(Color.BLACK);
            g2.drawRect(x, yIni, largura, altura);

        }

    }

    public void setFormaDesfazer(String formaDesfazer) {
        this.formaDesfazer = formaDesfazer;
    }

    public void setFormaRefazer(String formaRefazer) {
        this.formaRefazer = formaRefazer;
    }

    public void setJanelaPrincipal(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
    }

    public PainelPilhas getPainelPilhas() {
        return this;
    }

}
