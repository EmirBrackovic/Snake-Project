package konzola;

import javax.swing.JFrame;

import logika.Slika;



public class Okvir {
   
	/**
	 * Podešavanja okvira / konzole
     * Dodavanje slike (igre) u konzolu i podesavanje glavnih postavki okvira. Neke od stvarih koje sam postavio su:
     * Konzola ne moze mijenjati svoju velicinu,
     * Naslov igre,
     * Centriranje konzole na ekranu,
     * Da je vidljiva i slicno...
     */
    public Okvir() {
       
        JFrame Okvir = new JFrame();
        Slika slika = new Slika();
       
        Okvir.add(slika);
        Okvir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Okvir.setTitle("*Snake Emir Brackovic*");
        Okvir.setResizable(false);
        Okvir.pack();
        Okvir.setLocationRelativeTo(null);
        Okvir.setVisible(true);    
       
    }
    
}