
package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.LinkedList;


public class Ball {
    double probability;
    boolean haveProperty;
    List<Ball> neighbors;

    int[] coordinates = {0, 0, 0};

    public Ball() {
        probability = 0.5d;

        haveProperty = Math.random() <= probability;

        neighbors = new LinkedList<Ball>();
    }
    public Ball(double pProbability) {
        probability = pProbability;

        haveProperty = Math.random() <= probability;

        neighbors = new LinkedList<Ball>();
    }
    public Ball(LinkedList<Ball> pNeighbors) {
        probability = 0.5d;

        haveProperty = Math.random() <= probability;

        neighbors = pNeighbors;
    }
    public Ball(double pProbability, LinkedList<Ball> pNeighbors) {
        probability = pProbability;

        haveProperty = Math.random() <= probability;

        neighbors = pNeighbors;
    }

    // getters
    public boolean isHaveProperty() { return haveProperty; }
    public List<Ball> getNeighbors() { return neighbors; }

    public void setCoordinates(int newX, int newY, int newZ) {
        coordinates[0] = newX;
        coordinates[1] = newY;
        coordinates[2] = newZ;
    }
    public void setCoordinates(int @NotNull [] newCoordinates) {
        coordinates[0] = newCoordinates[0];
        coordinates[1] = newCoordinates[1];
        coordinates[2] = newCoordinates[2];
    }

    public void setNeighbor(Ball pNeighbor, int index) { neighbors.set(index, pNeighbor); }
    public void addNeighbor(Ball pNeighbor) { neighbors.add(pNeighbor); }
}
