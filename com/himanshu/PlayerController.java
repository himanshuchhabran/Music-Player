package com.himanshu;

import java.util.Scanner;

public class PlayerController {
    public static void play(Song[] playList) {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        int currentIndex = 0;

        while (!quit) {

            try {
                int action = sc.nextInt();
                sc.nextLine();

                if (action < 0 || action > 10) {
                    System.out.println("Invalid input. Available options are from 0 to 10 only");
                    continue;
                }
                switch (action) {

                    case 0:
                        if (playList[0] == null) {
                            System.out.println("This playlist has no song");
                            see();
                        } else {
                            System.out.println("Now playing " + playList[0].toString());
                            playwave();
                            see();
                        }
                        break;

                    case 1:
                        System.out.println("Paused!!" + playList[currentIndex].toString());
                        see();
                        break;

                    case 2:
                        if (currentIndex + 1 < playList.length && playList[currentIndex + 1] != null) {
                            System.out.println("Now playing " + playList[++currentIndex].toString());
                            playwave();
                            see();
                        } else {
                            System.out.println("No song available, reached to the end of the list");
                            forward = false;
                            see();
                        }

                        break;

                    case 3:

                        if (currentIndex - 1 >= 0 && playList[currentIndex - 1] != null) {
                            currentIndex--;
                            System.out.println("Now playing " + playList[currentIndex].toString());
                            playwave();
                            see();
                        } else {
                            System.out.println("We are at the first song");
                            forward = false;
                            see();
                        }

                        break;

                    case 4:
                        if (playList[currentIndex] != null) {
                            System.out.println("Now replaying " + playList[currentIndex].toString());
                            playwave();
                            see();
                        } else {
                            System.out.println("No song available to replay");
                            see();
                        }
                        break;

                    case 5:
                        printList(playList);
                        see();
                        break;

                    case 6:
                        addNewSong(playList);
                        see();
                        break;

                    case 7:
                        System.out.print("Enter the title of the song to find: ");
                        String titleToFind = sc.nextLine();
                        Song foundSongByTitle = findSongByTitle(titleToFind, playList);
                        if (foundSongByTitle != null) {
                            System.out.println("Found song by title: " + foundSongByTitle.toString());
                            System.out.println("Do you want to play this song? (1 for Yes / 0 for No): ");
                            int playOption = sc.nextInt();
                            sc.nextLine();
                            if (playOption == 1) {
                                playList[0] = foundSongByTitle;
                                currentIndex = 0;
                                System.out.println("Now playing " + playList[0].toString());
                                playwave();
                                see();
                            } else {
                                System.out.println("Song is not there.");
                                see();
                            }
                        } else {
                            System.out.println("Song not found with title: " + titleToFind);
                            see();
                        }
                        break;

                    case 8:

                        System.out.print("Enter the artist name to find songs: ");
                        String artistToFind = sc.nextLine();
                        Song[] songsByArtist = findSongsByArtist(artistToFind, playList);
                        if (songsByArtist.length > 0) {
                            System.out.println("Found songs by artist " + artistToFind + ":");
                            printList(songsByArtist);
                            System.out.println("Enter the track number to play: ");
                            int trackNumber = sc.nextInt();
                            sc.nextLine();
                            if (trackNumber >= 1 && trackNumber <= songsByArtist.length) {
                                playList[0] = songsByArtist[trackNumber - 1];
                                currentIndex = 0;
                                System.out.println("Now playing " + playList[0].toString());
                                playwave();
                                see();
                            } else {
                                System.out.println("Invalid track number.");
                                see();
                            }
                        } else {
                            System.out.println("No songs found by artist: " + artistToFind);
                            see();
                        }

                        break;

                    case 9:
                        printMenu();
                        break;

                    case 10:
                        System.out.println("Have a nice day!");
                        quit = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input.Please Enter only valid Available options");
                sc.nextLine();
                see();
            }
        }
    }

    public static void addNewSong(Song[] playList) {
        DatabaseManager.addNewSong(playList);
    }
    //for (int i = 0; i < playList.length; i++) {
    //   Song song = playList[i];
    public static Song findSongByTitle(String title, Song[] playList) {
        for (Song song : playList) {
            if (song != null && song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    public static Song[] findSongsByArtist(String artist, Song[] playList) {
        int count = 0;
        for (Song song : playList) {
            if (song != null && song.getArtistName().equalsIgnoreCase(artist)) {
                count++;
            }
        }

        Song[] songsByArtist = new Song[count];
        int index = 0;
        for (Song song : playList) {
            if (song != null && song.getArtistName().equalsIgnoreCase(artist)) {
                songsByArtist[index++] = song;
            }
        }
        return songsByArtist;
    }

    public static void see() {
        System.out.println("Press 9 to see all the available options");
    }

    public static void playwave() {
        System.out.println("+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+");
    }

    public static void printList(Song[] playList) {
        System.out.println("-----------------");
        for (Song song : playList) {
            if (song != null) {
                System.out.println(song.toString());
            }
        }
        System.out.println("-----------------");
    }

    public static void printMenu() {
        System.out.println("Available options\nPress:");
        System.out.println("0 - to play\n" +
                "1 - to pause the song\n" +
                "2 - to play next song\n" +
                "3 - to play previous song\n" +
                "4 - to replay the current song\n" +
                "5 - list of all songs \n" +
                "6 - add a new song\n" +
                "7 - Find and play a song using song name\n" +
                "8 - Find and play a song using artist name\n" +
                "9 - Print all available options\n" +
                "10 - Quit");
    }
}