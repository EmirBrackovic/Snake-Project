package gui;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Dio koda koji sluzi za grafiku hrane / jabuke u igri.
 * @author Emir Brackovic
 *
 */
public class Jabuka {
   /**
    * Koordinate po x i y osi i velicina jabuke.
    */
    private int x, y, sirina, visina;
   
    /**
     * Koordinate i velicina
     * @param x
     * Vrijednost po x osi
     * @param y
     * Vrijednost po y osi
     * @param velicina
     * Velicina jabuke/ sirina i visina
     */
    public Jabuka(int x, int y, int velicina) {
        this.x = x;
        this.y = y;
        sirina = velicina;
        visina = velicina;
    }
    public void otkucaj() {
       
    }
    /**
     * Funkcija koja crta jabuku.
     * Postavlja boju na crveno i boji na datim koordinatama.
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x * sirina , y * visina, sirina, visina);
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