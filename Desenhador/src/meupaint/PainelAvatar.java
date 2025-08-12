/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meupaint;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Eddie
 */
public class PainelAvatar extends JPanel{
    
    private boolean imagemParada;
    private ImageIcon avatarFrame;
    private ImageIcon avatarMove;
    
    public PainelAvatar(){
        
        imagemParada = true;
        avatarFrame = new ImageIcon( getClass().getResource( "/images/AvatarFrame.png" ) );
        avatarMove = new ImageIcon( getClass().getResource( "/images/AvatarMove.gif" ) );
        
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        
        if ( imagemParada ) {
            g.drawImage( avatarFrame.getImage(), 0, 0, getWidth(), getHeight(), this );
        } else {
            g.drawImage( avatarMove.getImage(), 0, 0, getWidth(), getHeight(), this );
        }
        
    }

    public void setImagemParada( boolean imagemParada ) {
        this.imagemParada = imagemParada;
    }
    
}
