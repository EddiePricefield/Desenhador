/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meupaint;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Eddie
 */
public class JanelaTeste extends JFrame{
    // Por padrão, o JFrame tem um "border layout" que divide a tela em Norte, Sul, Centro, Leste e Oeste.
    
    private JButton botao;
    
    //Construtor padrão - colocar o objeto em um estado válido (nesse caso, está inicializando os métodos do JFrame).
    public JanelaTeste(){
        
        //Construção da Janela
        setTitle( "Minha Janela de Teste" );
        setDefaultCloseOperation( EXIT_ON_CLOSE ); //Quando fechamos a janela, por padrão, nós escondemos a janela apenas e não fechamos a janela. É necessário trocar o comportamento (ele da um system.exit).
        setSize( 800, 600 ); //Definir o tamanho da tela
        setLocationRelativeTo(null); //Colocar a localização relativa a ninguém (faz a tela centralizar)
        
        //Construção do Botão
        botao = new JButton( "NÃO CLICA D:" ); 
        botao.addActionListener(new ActionListener() { //Criação de uma Classe Interna Anônima
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PARA, EU FALEI PRA NÃO CLICAR");//Quando clicar no botão, vai executar esse método
            }
            
        }); //Evento de Ação é quando você clica no botão 
        
        setLayout( new FlowLayout() ); //Criando o objeto e já atribuindo como uma variável (Gerenciador de Layour funciona para fazer as coisas se adaptarem à janela)
        add( botao ); //Faz com que o botão fique visível (por padrão, ele adiciona no Centro)
    }
    
    public static void main(String[] args) {
        JanelaTeste janela = new JanelaTeste(); //O janela é a variável que referencia o objeto do tipo JanelaTeste (quase como um ponteiro).
        janela.setVisible( true );
    }
    
}
