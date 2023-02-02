
package com.company;

import java.util.Scanner;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("+-+-------------------+-+");
        System.out.println("| |    Percolation    | |");
        System.out.println("+-+-------------------+-+");
        System.out.println();

        Scanner dimensionInput = new Scanner(System.in);
        boolean running_state = true;

        while (running_state) {
            System.out.println("How many dimensions do you want to work with ?");
            System.out.println("\toptions : 2 or quit");
            System.out.println();

            String dimension = dimensionInput.nextLine();

            if (dimension.trim().equals("2")
                    || dimension.trim().equals("2d")
                    || dimension.trim().equals("2D"))
                do_2D_percolation();
            else if (dimension.trim().equalsIgnoreCase("quit")
                    || dimension.trim().equalsIgnoreCase("exit"))
                running_state = false;
            else
                System.out.println("Sorry. This is an invalid command, please retry");

            System.out.println();
        }
        System.out.println();
        System.out.println("Thank you for you're usage of percolation");
    }

    static void do_2D_percolation() {
        // logo
        System.out.println();
        System.out.println("\t ________ \t________");
        System.out.println("\t//      \\\\\t||     \\\\");
        System.out.println("\t||      ||\t||      \\\\");
        System.out.println("\t\\\\     // \t||       ||");
        System.out.println("\t      //  \t||       ||");
        System.out.println("\t    //    \t||       ||");
        System.out.println("\t  //      \t||      //");
        System.out.println("\t//________\t||_____//");
        System.out.println();

        boolean running2D = true;
        boolean correctAnswer;
        Scanner input_data = new Scanner(System.in);

        while (running2D) {

            double probability = 0.5;
            correctAnswer = false;
            while (!correctAnswer) {
                System.out.println("Enter the probability of a ball having the property :");
                System.out.println("\t(must be between 0 and 1)");
                System.out.println();
                probability = input_data.nextDouble();
                System.out.println();

                if ((probability >= 0) && (probability <= 1))
                    correctAnswer = true;
            }

            int width = 1;
            correctAnswer = false;
            while (!correctAnswer) {
                System.out.println("Enter the horizontal size of the support :");
                System.out.println("\t(must be an integer > 0)");
                System.out.println();
                width = input_data.nextInt();
                System.out.println();

                if (width > 0)
                    correctAnswer = true;
            }

            int height = 1;
            correctAnswer = false;
            while (!correctAnswer) {
                System.out.println("Enter the vertical size of the support :");
                System.out.println("\t(must be an integer > 0)");
                System.out.println();
                height = input_data.nextInt();
                System.out.println();

                if (height > 0)
                    correctAnswer = true;
            }

            Support2D support = new Support2D(width, height, probability);
            List<List<Ball>> result = support.findPaths();

            if (result.size() > 0)
                System.out.println("There is percolation !");
            else
                System.out.println("There isn't percolation ...");
            System.out.println("There is " + result.size() + " path(s)");
            System.out.println();

            input_data.nextLine();
            correctAnswer = false;

            while (!correctAnswer) {
                System.out.println("Do you want to stay in 2D or get back to menu ?");
                System.out.println();

                String answer = input_data.nextLine();

                if (answer.trim().equalsIgnoreCase("quit")
                        || answer.trim().equalsIgnoreCase("exit")
                        || answer.trim().equalsIgnoreCase("back")
                        || answer.trim().equalsIgnoreCase("return")) {
                    running2D = false;
                    correctAnswer = true;
                } else if (answer.trim().equalsIgnoreCase("continue")
                        || answer.trim().equalsIgnoreCase("stay")
                        || answer.trim().equalsIgnoreCase("2")
                        || answer.trim().equalsIgnoreCase("2d")
                        || answer.trim().equalsIgnoreCase("2D"))
                    correctAnswer = true;
            }
            System.out.println();
        }
    }
}
