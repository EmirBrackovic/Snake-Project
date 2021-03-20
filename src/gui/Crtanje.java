package gui;

import java.awt.Color;
import java.awt.Graphics;

import logika.Slika;
 /**
  * Dio koda koji sluzi za grafiku zmije u igri.
  * 
  */
public class Crtanje {
   
    private int x, y, sirina, visina;
    /**
     * Koordinate i velicina
     * @param x
     * Vrijednost po x osi
     * @param y
     * Vrijednost po y osi
     * @param velicina
     * Velicina zmije/ sirina i visina
     */
    public Crtanje(int x, int y, int velicina) {
        this.x = x;
        this.y = y;
        sirina = velicina;
        visina = velicina;
        
    }
    public void otkucaj() {
       
    }
    /**
     * Funkcija koja crta zmiju i tacke na njoj.
     * Boji zmiju u tamnosivu boju , a tacke u zelenu, popunjava bojom na koordinatama gdje treba.
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x * sirina, y * visina, sirina+2, visina+2);
        g.setColor(Color.GREEN);
        g.fillRect(x * sirina + 1, y * visina + 1, sirina -4, visina-4);
    }
    /**
     * Funkcija koja vraca vrijednost po x osi.
     * @return x
     */
    public int dajX() {
        return x;
    }
    /**
     * Funkcija koja postavlja vrijednost parametra x.
     * @param x
     */
    public void staviX(int x) {
        this.x = x;
    }
    /**
     * Funkcija koja vraca vrijednost po y osi.
     * @return y
     */
    public int dajY() {
        return y;
    }
    
    /**
     * funkcija koja postavlja vrijednost parametra y.
     * @param y
     */
    public void staviY(int y) {
        this.y = y;
    }
   
    
}