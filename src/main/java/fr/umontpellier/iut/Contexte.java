package fr.umontpellier.iut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Contexte {

    private final JeuPuzzle jeuPuzzle;
    private ArrayList<JeuPuzzle> solution;

    public Contexte(JeuPuzzle jeuPuzzle) {
        this.jeuPuzzle = jeuPuzzle;
        solution = new ArrayList<>();
    }

    public void resoudre() {
        HashSet<JeuPuzzle> dejaVu = new HashSet<>();
        LinkedList<Couple> frontiere = new LinkedList<>();
        boolean gagnant = false;
        frontiere.add(new Couple(jeuPuzzle, null));

        while (!frontiere.isEmpty() && !gagnant) {

            if (frontiere.get(0).getJeuPuzzle().estGagnant()) {
                gagnant = true;
                solution = frontiere.get(0).getListeDeMouvements();
            }
            // On met à jour
            frontiere.get(0).mettreAJour(frontiere, dejaVu);
        }
    }

    public ArrayList<JeuPuzzle> getSolution() {
        return solution;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < solution.size(); i++) {
            s += solution.get(i).toString() + "\n_______________________________";
        }
        return s;
    }
}