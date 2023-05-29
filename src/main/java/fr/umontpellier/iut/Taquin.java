package fr.umontpellier.iut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Taquin implements JeuPuzzle {
    private final int[][] tableau;

    public Taquin(int[][] tableau) {
        this.tableau = tableau;
    }

    /**
     * @return true si le Taquin courant est dans une configuration gagnante
     */
    public boolean estGagnant() {
        if (tableau[tableau.length-1][tableau[0].length-1] != 0) {
            return false;
        }

        int valueRes = 1;

        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau[i].length; j++) {
                if (valueRes == tableau.length * tableau[i].length) 
                    valueRes = 0;
                if (valueRes != tableau[i][j])
                    return false;;
                valueRes++;
            }
        }

        return true;
    }

    /**
     * @return la liste des configurations obtenues avec un seul mouvement
     * à partir du Taquin courant
     */
    public ArrayList<Taquin> genererFils() {
        ArrayList<Taquin> fils = new ArrayList<>();
        int [] coordTrou = trouverTrou();

        // mouvement en haut ??
        if (coordTrou[0] != 0) {
            int [][] tableauCourant = cloneTaquinCourant();
            Taquin t = new Taquin(tableauCourant);
            t.tableau[coordTrou[0]][coordTrou[1]] = t.tableau[coordTrou[0] - 1][coordTrou[1]];
            t.tableau[coordTrou[0] - 1][coordTrou[1]] = 0;
            fils.add(t);
        }

        // mouvement en bas ??
        if (coordTrou[0] != tableau.length - 1) {
            int [][] tableauCourant = cloneTaquinCourant();
            Taquin t = new Taquin(tableauCourant);
            t.tableau[coordTrou[0]][coordTrou[1]] = t.tableau[coordTrou[0]+1][coordTrou[1]];
            t.tableau[coordTrou[0]+1][coordTrou[1]] = 0;
            fils.add(t);
        }

        // mouvement à gauche ??
        if (coordTrou[1] != 0) {
            int [][] tableauCourant = cloneTaquinCourant();
            Taquin t = new Taquin(tableauCourant);
            t.tableau[coordTrou[0]][coordTrou[1]] = t.tableau[coordTrou[0]][coordTrou[1] - 1];
            t.tableau[coordTrou[0]][coordTrou[1] - 1] = 0;
            fils.add(t);
        }

        // mouvement à droite ??
        if (coordTrou[1] != tableau[0].length - 1) {
            int [][] tableauCourant = cloneTaquinCourant();
            Taquin t = new Taquin(tableauCourant);
            t.tableau[coordTrou[0]][coordTrou[1]] = t.tableau[coordTrou[0]][coordTrou[1] + 1];
            t.tableau[coordTrou[0]][coordTrou[1] + 1] = 0;
            fils.add(t);
        }

        return fils;
    }

    /**
     * @return un tableau [i,j] si tableau[i][j]==0
     */
    public int[] trouverTrou() {
        int [] coordTrou = new int[2];
        boolean trouve = false;
        int i = 0,j = 0;

        while (!trouve && i < tableau.length) {
            while (!trouve && j < tableau[0].length) {
                if (tableau[i][j] == 0) {
                    trouve = true;
                    coordTrou[0] = i; coordTrou[1] = j;
                }
                j++;
            }
            j = 0;
            i++;
        }
        return coordTrou;
    }

    @Override
    public String toString() {
        String s = "\n+";

        for (int i = 0; i < tableau.length; i++) {
            s += "--";
        }

        s += "+";

        for (int i = 0; i < tableau.length; i++) {
            s += "\n| ";
            for (int j = 0; j < tableau[i].length; j++) {
                s += tableau[i][j] + " ";
            }
            s+="|";
        }

        s += "\n+";

        for (int i = 0; i < tableau.length; i++) {
            s += "--";
        }

        s += "+";

        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // On vérifie d'abord que nos 2 taquins ont la même longeur
        if (tableau.length != ((Taquin) o).tableau.length || tableau[0].length != ((Taquin) o).tableau[0].length)
            return false;

        /* Enfin on vérifie que les valeurs contenue par nos taquin de même dimenssion
        * sont toutes égales */
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau.length; j++) {
                if (tableau[i][j] != ((Taquin) o).tableau[i][j])
                    return false;
            }
        }

        return true;
    }
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tableau);
    }

    public int [][] cloneTaquinCourant() {
        int [][] tabClone = new int[tableau.length][tableau[0].length];
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau[0].length; j++) {
                tabClone[i][j] = tableau[i][j];
            }
        }
        return tabClone;
    }
}
