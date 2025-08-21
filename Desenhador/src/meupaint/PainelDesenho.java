/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meupaint;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Eddie
 */
public class PainelDesenho extends JPanel {

    private Forma formaTemp;

    private List<Forma> formas;

    public PainelDesenho() {
        formas = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

        if (formaTemp != null) { //Evitar o NullPointerException porque aqui seria inicializado como null
            formaTemp.desenhar(g);
        }

        for (Forma forma : formas) { //Percorrer uma estrutura de dados (precisa ser iterada). Ou seja, vai pegar elemento por elemento do linhas, atribuir o linha e usar dentro do for
            forma.desenhar(g);
        }

    }

    public void setFormaTemp(Forma formaTemp) { //Criar a forma
        this.formaTemp = formaTemp;
    }

    public void addFormaTemp(Forma forma) { //Adicionar a forma no ArrayList de formas (pra ele nao sumir)
        formas.add(forma);
    }

    public void limparListas() { //Limpar todas as formas da tela, ou seja, limpar o array
        formas.clear();
    }

}
