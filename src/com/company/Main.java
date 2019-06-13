package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int numberOfMovies;
        String filePath = "./src/resources/movies.txt";

        System.out.println("Guess The Movie Game");

        Game game = new Game(filePath);

        numberOfMovies = game.getMoviesTitles().size();
        System.out.println("Number of movies to guess: " + numberOfMovies);

        playTheGame(game);


    }

    public static void playTheGame(Game game){

        System.out.println("New game.");

        game.rollTheMovieTitle();

        Scanner scanner = new Scanner(System.in);

        while(!game.isEndOfGame()){
            System.out.println("You have " + game.getPoints() + " points.");
            System.out.println("You have guessed (" + game.getWrongChars().size() + ") wrong letters: " + game.printWrongLetters()) ;
            System.out.println("You are guessing: " + game.getMovieTitleMasked() );
            System.out.println("Type a letter: ");
            game.checkLetter(scanner.nextLine().toLowerCase());
        }

        if (game.isEndOfGame()){
            if (game.getPoints() > 0 ){
                System.out.println("You win !!!");
                System.out.println("The title of the movie is: " + game.getMovieTitle());
            } else {
                System.out.println("You lost the game");
                System.out.println("The title of the movie was: " + game.getMovieTitle());
            }

            System.out.println("Want to play another round ? [Y/N]");
            String answer = scanner.nextLine();
            if (answer.toLowerCase().equals("y")) playTheGame(game);
        }
    }
}
