
package com.company;

import java.util.List;
import java.util.LinkedList;
import java.util.Stack;


public class Support2D {
    int width, height;
    double probability;
    Ball[] mesh;

    public Support2D(int size) {
        width = size;
        height = size;
        probability = 0.5d;
        createBallMesh();
    }
    public Support2D(int size, double pProbability) {
        width = size;
        height = size;
        probability = pProbability;
        createBallMesh();
    }

    public Support2D(int pWidth, int pHeight) {
        width = pWidth;
        height = pHeight;
        probability = 0.5d;
        createBallMesh(); }
    public Support2D(int pWidth, int pHeight, double pProbability) {
        width = pWidth;
        height = pHeight;
        probability = pProbability;
        createBallMesh();
    }

    public Ball[] getMesh() { return mesh; }

    private int ballType(int i, int j) {
        int w_max = width - 1, h_max = height - 1;
        int type;

        if ((i == 0) && (j == 0))
            type = 0;  // up left corner
        else if (((i != 0) && (i < w_max)) && (j == 0))
            type = 1;  // up center
        else if ((i == w_max) && (j == 0))
            type = 2;  // up right corner
        else if (((j % 2 == 0) && (j != h_max)) && (i == 0))
            type = 3;  // left even
        else if (((j % 2 == 0) && (j != h_max)) && (i == w_max))
            type = 5;  // right even
        else if (((j % 2 == 1) && (j != h_max) && (j != 0)) && (i == 0))
            type = 6;  // left odd
        else if (((j % 2 == 1) && (j != h_max) && (j != 0)) && (i == w_max))
            type = 7;  // right odd
        else if ((i == 0) && (j == h_max)) {
            // down left corner
            if (height % 2 == 0)
                type = 8;
            else
                type = 11;
        }
        else if (((i != 0) && (i < w_max)) && (j == h_max))
            type = 9;  // down center
        else if ((i == w_max) && (j == h_max)) {
            // down right corner
            if (height % 2 == 0)
                type = 10;
            else
                type = 12;
        }
        else
            type = 4;  // majority of the grid, when you're in the middle

        return type;
    }

    private static void connect2Ball(Ball b1, Ball b2) {
        b1.addNeighbor(b2);
        b2.addNeighbor(b1);
    }

    private void createBallMesh() {

        // Creating the Ball Array:
        int size = width * height;
        int currentType = -1;
        int nbr = 0;
        int index = 0;
        int[] indices = {-1, -1, -1};
        mesh = new Ball[size];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                currentType = ballType(i, j);

                switch (currentType) {
                    case 1 -> {
                        indices[0] = index - 1;
                    }
                    case 2 -> indices[0] = index - 1;

                    case 3, 8 -> {
                        indices[0] = index - width;
                        indices[1] = index - width + 1;
                    }
                    case 4 -> {
                        indices[0] = index - 1;
                        indices[1] = index - 1;
                    }
                    case 5, 10 -> {
                        indices[0] = index - width;
                        indices[1] = index - 1;
                    }
                    case 6, 11 -> {
                        indices[0] = index - width;
                    }
                    case 7, 12 -> {
                        indices[0] = index - width - 1;
                        indices[1] = index - width;
                        indices[2] = index - 1;
                    }
                    case 9 -> {
                        indices[0] = index - width;
                        indices[1] = index - width + 1;
                        indices[2] = index - 1;
                    }
                }

                mesh[index] = new Ball(probability);
                mesh[index].setCoordinates(i, j, 0);

                for (int k: indices)
                    if (k != -1)
                        connect2Ball(mesh[index], mesh[k]);

                index++;
            }
        }
    }

    public List<List<Ball>> findPaths() {
        List<List<Ball>> result = new LinkedList<List<Ball>>();

        for (int i = 0; i < width; i++) {
            // just implementing a depth-first traversal to find the paths

            Ball starting_ball = mesh[i];

            List<Ball> visited = new LinkedList<Ball>();

            if (starting_ball.isHaveProperty()) {
                List<Ball> marked = new LinkedList<Ball>();

                Stack<Ball> waiting = new Stack<Ball>();
                waiting.add(starting_ball);

                while (!waiting.isEmpty()) {
                    Ball temp = waiting.pop();

                    if (!visited.contains(temp)) {
                        visited.add(temp);
                        marked.add(temp);

                        for (Ball ball : temp.getNeighbors())
                            if ((ball != null) && ball.isHaveProperty() && !marked.contains(ball))
                                waiting.add(ball);
                    }
                }
            }

            if (visited.size() > height) {
                boolean is_a_path = false;
                int j = 0, j_max = visited.size();
                int h_max = height - 1;

                while (!is_a_path && (j < j_max)) {
                    is_a_path = visited.get(j).coordinates[1] == h_max;
                    j++;
                }

                if (is_a_path)
                    result.add(visited);
            }
        }

        return result;
    }
}
