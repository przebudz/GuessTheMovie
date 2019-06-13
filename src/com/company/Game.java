package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<String> moviesTitles;
    private String movieTitle;
    private String movieTitleMasked;
    private Integer points;
    private ArrayList<Character> wrongChars;
    private boolean endOfGame;

    public Game(String filePath) {
        moviesTitles = this.uploadMoviesTitles(filePath);
        movieTitle = "";
        movieTitleMasked = "";
        points = 10;
        wrongChars = new ArrayList<>();
        endOfGame = false;
    }

    public ArrayList<String> getMoviesTitles() {
        return moviesTitles;
    }

    public void setMoviesTitles(ArrayList<String> moviesTitles) {
        this.moviesTitles = moviesTitles;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieTitleMasked() {
        return movieTitleMasked;
    }

    public void setMovieTitleMasked(String movieTitleMasked) {
        this.movieTitleMasked = movieTitleMasked;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public ArrayList<Character> getWrongChars() {
        return wrongChars;
    }

    public void setWrongChars(ArrayList<Character> wrongChars) {
        this.wrongChars = wrongChars;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }

    public void setEndOfGame(boolean endOfGame) {
        this.endOfGame = endOfGame;
    }

    private ArrayList<String> uploadMoviesTitles(String filePath) {

        ArrayList<String> movies = new ArrayList<>();

        File file = new File (filePath);

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while(fileScanner.hasNextLine()){

            movies.add(fileScanner.nextLine());

        }

        return movies;
    }

    public void rollTheMovieTitle(){
        int numberOfMovies = this.moviesTitles.size();

        int movieNumber = (int) (Math.random()*numberOfMovies);

        this.movieTitle = this.moviesTitles.get(movieNumber);

        this.movieTitleMasked = this.maskTheMovieTitle(this.movieTitle);

        this.setPoints(10);
        this.setEndOfGame(false);
        this.wrongChars.clear();

    }

    private String maskTheMovieTitle(String movieTitle) {

        String maskedTitle = "";

        maskedTitle = movieTitle.replaceAll("\\w", "_");

        return maskedTitle;
    }

    public String printWrongLetters() {
        String wrongLetters = "";

        for (int i =0; i < this.getWrongChars().size(); i++){
           wrongLetters += this.getWrongChars().get(i) + " ";
        }

        return wrongLetters;
    }

    public void checkLetter(String nextLine) {

        if (nextLine.length() == 0) {
            System.out.println("You typed nothing.");
        } else if (nextLine.length() > 1) {
            System.out.println("You typed more than one character");
        } else if (!(nextLine.matches("\\w"))) {
            System.out.println("This is not a letter");
        } else {
            findLetterInTitle(nextLine);
        }
    }

    private void findLetterInTitle(String letter) {

        StringBuilder titleMasked = new StringBuilder(this.movieTitleMasked);
        String title = this.movieTitle;
        if(title.contains(letter)){
            int index = title.indexOf(letter);
            while(index >=0){
                titleMasked.setCharAt(index, letter.charAt(0));
                index = title.indexOf(letter, index +1);
            }
            this.movieTitleMasked = titleMasked.toString();
            if(this.movieTitleMasked.equals(this.movieTitle)){
                this.setEndOfGame(true);
            }
        } else {
            this.getWrongChars().add(letter.charAt(0));
            this.setPoints(this.getPoints()-1);
            if(this.getPoints() == 0) {
                this.setEndOfGame(true);
            }

        }
    }
}
