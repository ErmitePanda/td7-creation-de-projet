package fr.umontpellier.iut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Couple {

    private JeuPuzzle jeuPuzzle;
    private Couple predecesseur;

    public Couple(JeuPuzzle jeuPuzzle, Couple predecesseur) {
        this.jeuPuzzle = jeuPuzzle;
        this.predecesseur = predecesseur;
    }

    /**
     * Vérifie si les fils du taquin sont déjà vus et met à jour la frontière
     * et l'ensemble des configurations déjà vues.
     */
    public void mettreAJour(LinkedList<Couple> frontiere, HashSet<JeuPuzzle> dejaVus) {
        ArrayList<JeuPuzzle> filsTaquin = jeuPuzzle.genererFils();
        Couple c;
        for (int i = 0; i < filsTaquin.size(); i++) {
            if (!dejaVus.contains(filsTaquin.get(i))) {
                c = new Couple(filsTaquin.get(i), this);
                frontiere.add(c);
                dejaVus.add(filsTaquin.get(i));
            }
        }
        frontiere.remove(this);
    }

    /**
     * @return la liste des taquins intermédiaire à partir du taquin initial
     * et jusqu'au taquin courant
     */
    public ArrayList<JeuPuzzle> getListeDeMouvements() {
        if (jeuPuzzle == null) return new ArrayList<JeuPuzzle>();
        ArrayList<JeuPuzzle> listeMouvement = new ArrayList<>();
        Couple c = this;
        while (c != null) {
            listeMouvement.add(c.jeuPuzzle);
            c = c.predecesseur;
        }

        Collections.reverse(listeMouvement);

        return listeMouvement;
    }

    public JeuPuzzle getJeuPuzzle() {
        return jeuPuzzle;
    }
}
