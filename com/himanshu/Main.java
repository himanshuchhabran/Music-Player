package com.himanshu;


public class Main {

    public static void main(String[] args) {
        
        PlayerController.printMenu();
        
        Song[] playList = new Song[50];

        DatabaseManager.addToPlayList(playList);
        
        PlayerController.play(playList);
        
    }
}
