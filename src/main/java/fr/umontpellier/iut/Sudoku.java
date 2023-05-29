package fr.umontpellier.iut;

import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku implements JeuPuzzle {
    private int[][] grille;

    // pré-requis : la grille est carrée
    public Sudoku(int[][] g) {
        this.grille = g;
    }

    /* Constructeur par recopie
     * pré-requis : la grille est carrée */
    public Sudoku(Sudoku s) {
        grille = new int[s.grille.length][s.grille.length];
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille.length; j++) {
                this.grille[i][j] = s.grille[i][j];
            }
        }
    }

    /* Constructeur par recopie
    * pré-requis : la grille est carrée */


    public boolean estGagnant() {
        if (!grilleRemplie()) return false;

        int sommeLigne = 0, sommeColone = 0;
        int sommeLigneColonne = (grille.length + 1) * grille.length/2;
        for (int i = 0; i < grille.length; i++) {
            sommeLigne = 0;
            sommeColone = 0;
            for (int j = 0; j < grille.length; j++) {
                sommeLigne += grille[i][j];
                sommeColone += grille[j][i];
            }
            if (sommeLigne != sommeLigneColonne || sommeColone != sommeLigneColonne) return false;
        }

        return true;
    }

    public ArrayList<Sudoku> genererFils() {
        boolean trouve = false;
        if (grilleRemplie()) return new ArrayList<>();

        ArrayList<Sudoku> filsSudoku = new ArrayList<>();

        ArrayList<Integer> valeurNonPresente;

        Sudoku copieThis = new Sudoku(this);

        for (int i = 0; i < grille.length && !trouve; i++) {
            for (int j = 0; j < grille.length && !trouve; j++) {

                // Si la case n'est pas remplie
                if (grille[i][j] == 0) {
                    trouve = !trouve;

                    valeurNonPresente = valNonPresente(i, j);

                    for (int k = 0; k < valeurNonPresente.size(); k++) {
                        copieThis.grille[i][j] = valeurNonPresente.get(k);
                        filsSudoku.add(copieThis);
                        copieThis = new Sudoku(this);
                    }
                }
            }
        }
        return filsSudoku;
    }

    @Override
    public String toString() {
        String s = "\n";
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille.length; j++) {
                s+=grille[i][j]+" | ";
            }
            s+="\n";
            for (int j = 0; j < grille.length; j++) {
                s+="----";
            }
            s+="\n";
        }
        return s+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sudoku sudoku = (Sudoku) o;
        return Arrays.deepEquals(grille, sudoku.grille);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grille);
    }

    // Mes fonctions

    /**
     * Vérifie si le sudoku courant à toute c'est grille remplie
     * @return
     */
    public boolean grilleRemplie() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille.length; j++) {
                if (grille[i][j] == 0) return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si la valeur de la grille i j est valide
     * pré-requis : la grille est remplie
     * @param line
     * @param column
     * @return
     */
    public boolean valeurValide(int line, int column) {
        int value = grille[line][column];

        // Vérification sur la ligne et la colonne
        for (int i = 0; i < grille.length; i++) {
            if (grille[i][column] == value && i != line) return false;
            if (grille[line][i] == value && i != column) return false;
        }

        return true;
    }

    /**
     * Renvoie une liste pouvant contenir des valeurs de 1 à grille.length qui
     * sont des valeurs pas présente sur la ligne et colonne de grille[i][j]
     * @return
     */
    public ArrayList<Integer> valNonPresente(int line, int column) {
        ArrayList<Integer> valPresente = new ArrayList<>();
        for (int i = 0; i < grille.length; i++) {
            if (!valPresente.contains(grille[i][column]) && i != line && grille[i][column] != 0)
                valPresente.add(grille[i][column]);

            if (!valPresente.contains(grille[line][i]) && i != column && grille[line][i] != 0)
                valPresente.add(grille[line][i]);
        }

        ArrayList<Integer> valNonPresente = new ArrayList<>();
        for (int i = 1; i <= grille.length; i++) {
            if (!valPresente.contains(i))
                valNonPresente.add(i);
        }

        return valNonPresente;
    }

    public Sudoku copieSudoku() {
        int [][] grilleCopie = new int[grille.length][grille.length];
        for (int i = 0; i < grilleCopie.length; i++) {
            for (int j = 0; j < grilleCopie.length; j++) {
                grilleCopie[i][j] = grille[i][j];
            }
        }
        Sudoku s = new Sudoku(grilleCopie);
        return s;
    }
}