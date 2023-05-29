package fr.umontpellier.iut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Hanoi implements JeuPuzzle {
    private ArrayList<Integer> tourGauche;
    private ArrayList<Integer> tourMillieu;
    private ArrayList<Integer> tourDroite;

    private int nbDisque;
    public Hanoi(int nbDisque) {
        /* crée un hanoi avec la configuration suivante :
                * sur la tour 1 les disques [taille,taille-1, .., 1]
                * rien sur les tour 2 et 3 (elles sont vides)
         */
        tourGauche = new ArrayList<>();
        for (int i = 0; i < nbDisque; i++) {
            tourGauche.add(nbDisque-i);
        }
        tourMillieu = new ArrayList<>();
        tourDroite = new ArrayList<>();
        this.nbDisque = nbDisque;
    }


    public Hanoi(ArrayList<Integer> tourGauche, ArrayList<Integer> tourMillieu, ArrayList<Integer> tourDroite,
                 int nbDisque) {
        /*
        Crée un hanoi où la tour 1 (resp. tour 2 et tour 3) contient les entiers de tour1 (resp. tour2 et tour3). Par exemple,
        si tour1 est une ArrayList contenant [3,2,1], et tour2 et tour3 sont des ArrayList vides, alors
        Hanoi(tour1, tour2, tour3) doit créer la même configuration que Hanoi(3).
         */

        this.tourGauche = new ArrayList<>();
        this.tourMillieu = new ArrayList<>();
        this.tourDroite = new ArrayList<>();
        this.tourGauche.addAll(tourGauche);
        this.tourMillieu.addAll(tourMillieu);
        this.tourDroite.addAll(tourDroite);
        this.nbDisque = nbDisque;
    }

    public Hanoi(Hanoi h) {
        tourGauche = new ArrayList<>();
        tourGauche.addAll(h.tourGauche);
        tourMillieu = new ArrayList<>();
        tourMillieu.addAll(h.tourMillieu);
        tourDroite = new ArrayList<>();
        tourDroite.addAll(h.tourDroite);
        nbDisque = h.nbDisque;
    }

    // Prérequis, l'objet courant hanoi est valide
    public boolean estGagnant() {
        return tourGauche.isEmpty() && tourMillieu.isEmpty();
    }

    public ArrayList<Hanoi> genererFils() {
        ArrayList<Hanoi> filsHanoi = new ArrayList<>();
        Hanoi clone = new Hanoi(this);

        // TOUR GAUCHE

        if (!tourGauche.isEmpty()) {

            // Cas de la tour du millieu

            if (deplacementValide(tourGauche, tourMillieu)) {
                clone.tourMillieu.add(clone.tourGauche.remove(tourGauche.size() - 1));
                filsHanoi.add(clone);
                clone = new Hanoi(this); // Reset
            }

            // Cas de la tour de droite

            if (deplacementValide(tourGauche, tourDroite)) {
                clone.tourDroite.add(clone.tourGauche.remove(tourGauche.size() - 1));
                filsHanoi.add(clone);
                clone = new Hanoi(this); // Reset
            }
        }

        // TOUR MILLIEU

        if (!tourMillieu.isEmpty()) {

            // Cas de la tour de gauche

            if (deplacementValide(tourMillieu, tourGauche)) {
                clone.tourGauche.add(clone.tourMillieu.remove(tourMillieu.size() - 1));
                filsHanoi.add(clone);
                clone = new Hanoi(this); // Reset
            }

            // Cas de la tour de droite

            if (deplacementValide(clone.tourMillieu, clone.tourDroite)) {
                clone.tourDroite.add(clone.tourMillieu.remove(tourMillieu.size() - 1));
                filsHanoi.add(clone);
                clone = new Hanoi(this); // Reset
            }
        }

        // TOUR DROITE

        if (!tourDroite.isEmpty()) {

            // Cas de la tour de gauche

            if (deplacementValide(tourDroite, tourGauche)) {
                clone.tourGauche.add(clone.tourDroite.remove(tourDroite.size() - 1));
                filsHanoi.add(clone);
                clone = new Hanoi(this); // Reset
            }

            // Cas de la tour du millieu

            if (deplacementValide(tourDroite, tourMillieu)) {
                clone.tourMillieu.add(clone.tourDroite.remove(tourDroite.size() - 1));
                filsHanoi.add(clone);
            }
        }

        return filsHanoi;
    }

    /**
     * Vérifie si le déplacement du disque de source est valide
     * @param source
     * @param deplacement
     * @return True si le deplacement est valide, sinon false
     */
    public boolean deplacementValide(ArrayList<Integer> source, ArrayList<Integer> deplacement) {
        if (source.isEmpty()) return false;
        if (deplacement.isEmpty()) return true;
        return source.get(source.size() - 1) < deplacement.get(deplacement.size() - 1);
    }

    /* Méthode utilitaire */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hanoi hanoi = (Hanoi) o;

        if (tourGauche.containsAll(hanoi.tourGauche) && tourMillieu.containsAll(hanoi.tourMillieu) &&
                tourDroite.containsAll(hanoi.tourDroite)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourGauche, tourMillieu, tourDroite, nbDisque);
    }

    @Override
    public String toString() {
        String s = "\nTour de gauche : ";
        for (int i = 0; i < tourGauche.size(); i++) {
            s += tourGauche.get(i) + " ";
        }
        s += "\nTour du millieu : ";
        for (int i = 0; i < tourMillieu.size(); i++) {
            s += tourMillieu.get(i) + " ";
        }
        s += "\nTour de droite : ";
        for (int i = 0; i < tourDroite.size(); i++) {
            s += tourDroite.get(i) + " ";
        }
        return s;
    }

    // GETTER


    public ArrayList<Integer> getTourGauche() {
        return tourGauche;
    }

    public ArrayList<Integer> getTourDroite() {
        return tourDroite;
    }

    public ArrayList<Integer> getTourMillieu() {
        return tourMillieu;
    }
}