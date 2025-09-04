/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meupaint;

import java.awt.Graphics;
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
    private Stack<List<Forma>> undoTudo;
    private Stack<Stack<Forma>> redoTudo;
    
    private int estadoStack;

    public PainelDesenho() {
        formas = new ArrayList<>();
        undoForma = new Stack<>();
        redoForma = new Stack<>();
        undoTudo = new Stack<>();
        redoTudo = new Stack<>();
    }
    
    interface Comando {
        void undo();
        void redo();
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

    }

    public void setFormaTemp(Forma formaTemp) { //Criar a forma
        this.formaTemp = formaTemp;
    }

    public void addFormaTemp(Forma forma) { //Adicionar a forma no ArrayList de formas (pra ele nao sumir)
        formas.add(forma);
        undoForma.push(forma);
        redoForma.clear();
        redoTudo.clear();
    }

    public void limparListas() { //Limpar todas as formas da tela, ou seja, limpar o array
        
        if (!formas.isEmpty()){
            undoTudo.push(new ArrayList<>(formas));
            formas.clear();
            redoTudo.clear();
            redoForma.clear();
            repaint();
        }
        
    }
    
    //O Push envia o que você quer para o topo do Stack
    //O Pop remove o valor do topo do Stack
    
    public void desfazer() {
        
        if (formas.isEmpty() && !undoTudo.isEmpty()){
            redoTudo.push((Stack<Forma>) redoForma.clone());
            redoForma.clear();
            formas = new ArrayList<>(undoTudo.pop());
            estadoStack = 1;
            repaint();
            
        } else if (!undoForma.isEmpty()) {
            Forma a = undoForma.pop();
            redoForma.push(a);
            formas.remove(a);
            estadoStack = 2;
            repaint();
        }
    }

    public void refazer() {
        
        if (redoForma.isEmpty() && !redoTudo.isEmpty()){
            undoTudo.push(new ArrayList<>(formas));
            formas.clear();
            redoForma = (redoTudo.pop());
            estadoStack = 3;
            repaint();
            
        }else if (!redoForma.isEmpty()) {
            Forma a = redoForma.pop();
            undoForma.push(a);
            formas.add(a);
            estadoStack = 4;
            repaint();
        }
    }

    public Forma getLastForma() {
        return formas.get(formas.size() - 1);
    }
    
    public Forma getFormaRefeita() {
        return undoForma.peek();
    }
    
    public Forma getFormaDesfeita() {
        return redoForma.peek();
    }
    
    public int retornarEstado(){
        
        return estadoStack;
        
    }
    
}
