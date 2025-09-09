/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meupaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JPanel;

/**
 *
 * @author Eddie
 */
public class PainelDesenho extends JPanel {

    private Forma formaTemp;

    private List<Forma> formas;

    private Stack<Forma> undoForma;
    private Stack<Forma> redoForma;
    private Stack<Stack<Forma>> undoTudo;
    private Stack<Stack<Forma>> redoTudo;

    private int estadoStack;
    private boolean limparAlternativo;

    private boolean pincelAtivo = true;
    private boolean borrachaAtiva;

    private int mouseX;
    private int mouseY;

    private Color corPincel = new Color(0, 0, 0, 0);
    private int tamanhoPincel = 2;

    public PainelDesenho() {
        formas = new ArrayList<>();
        undoForma = new Stack<>();
        redoForma = new Stack<>();
        undoTudo = new Stack<>();
        redoTudo = new Stack<>();
        ativarMouseListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

        for (Forma forma : formas) { //Percorrer uma estrutura de dados (precisa ser iterada). Ou seja, vai pegar elemento por elemento do linhas, atribuir o linha e usar dentro do for
            forma.desenhar(g);
        }

        if (formaTemp != null) { //Evitar o NullPointerException porque aqui seria inicializado como null
            formaTemp.desenhar(g);
        }

        desenharReferencias(g);

    }

    public void setFormaTemp(Forma formaTemp) { //Criar a forma
        this.formaTemp = formaTemp;
    }

    public void addFormaTemp(Forma forma) { //Adicionar a forma no ArrayList de formas (pra ele nao sumir)
        formas.add(forma);
        undoForma.push(forma);
        redoForma.clear();

        if (limparAlternativo) {
            redoTudo.clear();
        }
    }

    public void setPincel(boolean valor) {
        pincelAtivo = valor;
        borrachaAtiva = false;
    }

    public void setTamanhoPincel(int tamanho) {
        tamanhoPincel = tamanho;
    }

    public void setCorPincel(Color color) {
        corPincel = color;
    }

    public void setBorracha(boolean valor) {
        borrachaAtiva = valor;
        pincelAtivo = false;
    }

    public void ativarMouseListener() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (pincelAtivo || borrachaAtiva) {
                    mouseX = e.getX();
                    mouseY = e.getY();

                    if (!corPincel.equals(new Color(0, 0, 0, 0))) {
                        repaint();
                        revalidate();
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (borrachaAtiva) {
                    mouseX = e.getX();
                    mouseY = e.getY();

                    if (!corPincel.equals(new Color(0, 0, 0, 0))) {
                        repaint();
                        revalidate();
                    }
                }
            }
        });
    }

    public void desenharReferencias(Graphics g) {

        int raio = tamanhoPincel / 2;

        if (pincelAtivo) {
            g.setColor(corPincel);
            g.fillOval(mouseX - raio, mouseY - raio, tamanhoPincel, tamanhoPincel);
        } else if (borrachaAtiva) {
            if (corPincel.equals(new Color(0, 0, 0, 0))) {
                g.setColor(corPincel);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawOval(mouseX - raio, mouseY - raio, tamanhoPincel, tamanhoPincel);
        }

    }

    public void limparListas() { //Limpar todas as formas da tela, ou seja, limpar o array

        if (limparAlternativo && !formas.isEmpty()) {
            undoTudo.push((Stack<Forma>) undoForma.clone());
            formas.clear();
            redoTudo.clear();
            redoForma.clear();
            undoForma.clear();
            repaint();
        } else {
            limparStacks();
            repaint();
        }

    }

    //O Push envia o que você quer para o topo do Stack
    //O Pop remove o valor do topo do Stack
    public void desfazer() {

        if (limparAlternativo && formas.isEmpty() && !undoTudo.isEmpty()) {
            redoTudo.push((Stack<Forma>) redoForma.clone());
            redoForma.clear();
            undoForma = (undoTudo.pop());
            formas = new ArrayList<>(undoForma);
            estadoStack = 1;
            repaint();

        } else if (!undoForma.isEmpty()) {
            Forma a = undoForma.pop();
            redoForma.push(a);
            formas.remove(a);
            estadoStack = 2;
            repaint();
        } else {
            estadoStack = 0;
        }
    }

    public void refazer() {

        if (limparAlternativo && redoForma.isEmpty() && !redoTudo.isEmpty()) {
            undoTudo.push((Stack<Forma>) undoForma.clone());
            undoForma.clear();
            formas.clear();
            redoForma = (redoTudo.pop());
            estadoStack = 3;
            repaint();

        } else if (!redoForma.isEmpty()) {
            Forma a = redoForma.pop();
            undoForma.push(a);
            formas.add(a);
            estadoStack = 4;
            repaint();
        } else {
            estadoStack = 0;
        }
    }

    public Forma getLastForma() {
        return formas.get(formas.size() - 1);
    }

    public Forma undoFormaPeek() {
        return undoForma.peek();
    }

    public Forma redoFormaPeek() {
        return redoForma.peek();
    }

    public int retornarEstado() {
        return estadoStack;
    }

    public int getStackTamanho(int stackNum) {
        return switch (stackNum) {
            case 1 ->
                undoForma.size();
            case 2 ->
                redoForma.size();
            case 3 ->
                undoTudo.size();
            case 4 ->
                redoTudo.size();
            case 5 ->
                formas.size(); //Shhhhhhh
            default ->
                0;
        };
    }

    public void limparStacks() {
        if (!formas.isEmpty()) {
            formas.clear();
            repaint();
        }
        if (!undoForma.isEmpty()) {
            undoForma.clear();
        }
        if (!redoForma.isEmpty()) {
            redoForma.clear();
        }
        if (!undoTudo.isEmpty()) {
            undoTudo.clear();
        }
        if (!redoTudo.isEmpty()) {
            redoTudo.clear();
        }
    }

    public void setLimparAlternativo(boolean limparAlternativo) {
        this.limparAlternativo = limparAlternativo;
    }

    public boolean getLimparAlternativo() {
        return limparAlternativo;
    }

}
