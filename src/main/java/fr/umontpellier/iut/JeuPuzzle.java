package fr.umontpellier.iut;

import java.util.ArrayList;
import java.util.Objects;

public interface JeuPuzzle <T extends JeuPuzzle <T>> {
    public boolean estGagnant();
    public ArrayList<T> genererFils();
}
