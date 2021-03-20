package logika;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.Crtanje;
import gui.Jabuka;

/**
 * Dio koda koji je za logiku igre.
 * @author Emir Brackovic
 *
 */
public class Slika extends JPanel implements Runnable, KeyListener {
    /**
     * Sirina i visina prozora.
     */
    public static final int sirina = 500, visina = 500;
   
    private Thread thread;
    /**
     * Da li je kraj igre.
     */
    private boolean krajDaNe = false;
 
    private Crtanje b;
    /**
     * Lista elemenata koji su podaci za zmiju.
     */
    private ArrayList<Crtanje> zmija;
   
    private Jabuka Jabuka;
    /**
     * Lista elemenata koji sluze za jabuku.
     */
    private ArrayList<Jabuka> jabuke;
   /**
    * Random broj koji nam sluzi za lokaciju nove jabuke.
    */
    private Random r;
   
    private int x = 10, y = 10;
    
    private int	velicina = 5;
    /**
     * Pocetno kretanje zmije.
     */
    private boolean right = true, left = false, up = false, down =false;
   
    private int otkucaji = 0;
   /**
    * Glavna funkcija igre.
    */
    public Slika() {
        setFocusable(true);
       /**
        * Prihvata komande korisnika u igri.
        */
        addKeyListener(this);
        /**
         * Postavlja dimenzije prozora.
         */
        setPreferredSize(new Dimension(sirina, visina));
        /**
         * Kreira random broj.
         */
        r = new Random();
        /**
         * Kreiranje 2 liste za zmiju i za jabuku.
         */
        zmija = new ArrayList<Crtanje>();
        jabuke = new ArrayList<Jabuka>();
       
        start();
    }
    
   /**
    * Funkcija koja ispituje da li je velicina zmije 0, ako jeste onda se kreira novi element.
    * Funckija takodjer ispituje isto za jabuku, ako jeste onda se koristi random funkcija i nova jabuka smjesta na nove slucajne koordinate.
    */
    public void otkucaj() {
        if (zmija.size() == 0) {
            b = new Crtanje(x, y, 10);
            zmija.add(b);
        }
        if(jabuke.size() == 0) {
            int x = r.nextInt(49);
            int y = r.nextInt(49);
           
            Jabuka = new Jabuka(x, y, 10);
            jabuke.add(Jabuka);
        }
        /**
         * Brzina zmije.
         */
        int brzina=1500000;
        
        /**
         * Sa ovom for petljom ispitujemo da li je zmija pojela jabuku, ako jeste , povecava joj se velicina, a ta jabuka se brise. 
         */
        for(int i = 0; i < jabuke.size(); i++) {
            if(x == jabuke.get(i).dajX() &&
                    y == jabuke.get(i).dajY()) {
                velicina++;
                
                jabuke.remove(i);
                i++;
            }
        }
       /**
        * U ovoj for petlji ako dodje do kraja igre, tacnije da li je zmija dirnula sebe.
        * Pitamo korisnika da li zeli nastavak.
        * Ako je odgovor potvrdan, igra pocinje od pocetka, u suprotnom gasi se prozor igre.
        */
        for(int i = 0; i < zmija.size(); i++) {
            if(x == zmija.get(i).dajX() && y == zmija.get(i).dajY()) {
                if(i != zmija.size() - 1) {
                    int odgovor = JOptionPane.showConfirmDialog(null, "Kraj igre! Zelite li zapoceti novu igru ? ");
                    if (odgovor == 0) {
                        restart();
                    } else if(odgovor == 1) {
                        System.exit(0);
                    } else {
                        stop();
                    }
                }
            }
        }
        /**
         * Ova for petlja ispituje da li je kraj igre, tacnije da li je zmija udarila u ivice konzole.
         */
        if (x < 0 || x > 49 || y < 0 || y > 49) {
            int odgovor = JOptionPane.showConfirmDialog(null, "Kraj igre! Zelite li zapoceti novu igru ? ");
            if (odgovor == 0) {
                restart();
            } else if(odgovor == 1) {
                    System.exit(0);
            } else {
                stop();
            }
        }
       
       
        otkucaji++;
        
       /** 
        * Dio kojim kontrolisemo brzinu zmije.
        */
        if(otkucaji > brzina) {
            if(right) x++;
            if(left) x--;
            if(up) y--;
            if(down) y++;
           
            otkucaji = 0;
            
            b = new Crtanje(x, y, 10);
            zmija.add(b);
           
            if(zmija.size() > velicina) {
                zmija.remove(0);
            }
        }
    }
 
   
    /**
     * Funkcija koja boji plocu i finalno pokazuje jabuku i zmiju.
     */
	public void paint(Graphics g) {
        g.clearRect(0, 0, sirina, visina);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sirina, visina);
       
       
        for (int i = 0; i < visina / 10; i++) {
            g.drawLine(0, i * 10, sirina, i * 10);
        }
 
        for (int i = 0; i < zmija.size(); i++) {
            zmija.get(i).draw(g);
        }
        for(int i = 0; i < jabuke.size(); i++) {
            jabuke.get(i).draw(g);
        }
 
    }
	/**
	 * Funkcija koja sluzi za pocetak igre.
	 */
    public void start() {
        krajDaNe = true;
        thread = new Thread(this);
        thread.start();
    }
    /**
     * Funkcija koja sluzi za zaustavljanje igre.
     */
    public void stop() {
        krajDaNe = false;	
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void run() {
        while (krajDaNe) {
            otkucaj();
            repaint();
        }
    }
    /**
     * Funkcija koja ukoliko korisnik prihvati pocetak nove igre, resetuje sve vrijednosti na pocetne i zapocinje novu igru.
     */
    public void restart() {
        x = 10;
        y = 10;
        
        right = true;
        left = false;
        down = false;
        up = false;
        
        velicina = 5;
        
        zmija.clear();
        
        jabuke = new ArrayList<Jabuka>();
        
        b = new Crtanje(x, y, 10);
        
        zmija.add(b);
        
        Jabuka = new Jabuka(x, y, 10);
        
        jabuke.add(Jabuka);
    }
    /**
     * Funckcija koja sluzi za upravljanje zmijom.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        /**
         * Funkcija za skretanje zmije u desnu stranu pomocu desne strelice.
         */
        if(key == KeyEvent.VK_RIGHT && !left) {
            up = false;
            down = false;
            right = true;
        }
        /**
         * Funkcija za skretanje zmije u lijevu stranu pomocu lijeve strelice.
         */
        if(key == KeyEvent.VK_LEFT && !right) {
            up = false;
            down = false;
            left = true;
        }
        /**
         * Funkcija za skretanje zmije ka gore pomocu gornje strelice.
         */
        if(key == KeyEvent.VK_UP && !down) {
            left = false;
            right = false;
            up = true;
        }
        /**
         * Funkcija koja sluzi za skretanje zmije ka dolje, pomocu donje strelice.
         */
        if(key == KeyEvent.VK_DOWN && !up) {
            left = false;
            right = false;
            down = true;
        }
    }
    /**
     * Funkcije koje su obavezne, koje ispituju da li je tipka dodirnuta i da li je pustena.
     */
    @Override
    public void keyReleased(KeyEvent arg0) {   
    }
    public void keyTyped(KeyEvent arg0) {  
    }    
}