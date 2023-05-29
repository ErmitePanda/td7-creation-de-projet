package fr.umontpellier.iut;

import java.util.ArrayList;
import java.util.List;

public class AppJeuxPuzzle {
    public static void main(String[] args) {
        /*int [][] tab = {
                {1,2,3},
                {4,0,5},
                {7,8,6};
        };
        JeuPuzzle taquin = new Taquin(tab);
        Contexte c = new Contexte(taquin);
        c.resoudre();
        System.out.println(c);*/ // ==> ça marche

        /*JeuPuzzle jp = new Hanoi(5);
        Contexte c = new Contexte(jp);
        c.resoudre();
        System.out.println(c);
        System.out.println(c.getSolution().size());*/

        /*int [][] grille = new int[][] {
                {4, 1, 2, 3},
                {0, 0, 1, 4},
                {0, 3, 4, 1},
                {1, 4, 3, 2}
        };
        JeuPuzzle sudoku = new Sudoku(grille);
        Contexte c = new Contexte(sudoku);
        c.resoudre();
        System.out.println(c.getSolution());*/

        /*int [][] tab = new int[][] {
                {1,0,3,2,5}
        };
        JeuPuzzle jeuPuzzle = new Taquin(tab);
        Contexte c = new Contexte(jeuPuzzle);
        c.resoudre();
        System.out.println(c);*/

        int [][] grille = new int[][] {
                {4,3,2,1},
                {1,2,4,3},
                {0,0,0,0},
                {0,0,0,0}
        };

        JeuPuzzle sudokuQuasiVide = new Sudoku(grille);
        Contexte c = new Contexte(sudokuQuasiVide);
        c.resoudre();
        System.out.println(c);
    }
}
