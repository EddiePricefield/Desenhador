/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meupaint;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Eddie
 */
public abstract class Forma {

    //Variáveis para criação da Forma
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;

    //Variáveis para as cores da Forma
    protected Color contorno;
    protected Color preenchimento;

    protected int tamanhoContorno;

    //Construtor 
    public Forma() { //Vai ser invocado na subclasse. O compilador faz, automaticamente, um construtor com super() nelas.
        contorno = Color.BLACK;
        preenchimento = Color.WHITE;
    }

    //Método Abstrato Compartilhado (Polimorfismo)
    public abstract void desenhar(Graphics g); //O tipo de desenhar é escolhido pelo tipo de objeto e não pela referência. O nome disso é: Vinculação Dinâmica (para funcionar em C++ tem que fazer alocação dinâmica de memória, as formas precisam ser ponteiros)

    //Métodos Set
    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setContorno(Color contorno) {
        this.contorno = contorno;
    }

    public void setPreenchimento(Color preenchimento) {
        this.preenchimento = preenchimento;
    }

    public void setTamanhoContorno(int tamanhoContorno) {
        this.tamanhoContorno = tamanhoContorno;
    }

}
