package com.himanshu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Scanner;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/musicplayer1?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "********";

    public static void addToPlayList(Song[] playList) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT * FROM songs";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                int playlistIndex = 0;
                while (resultSet.next() && playlistIndex < playList.length) {
                    String title = resultSet.getString("title");
                    double duration = resultSet.getDouble("duration");
                    String artistName = resultSet.getString("artist_name");
                    String genre = resultSet.getString("genre");

                    if (title != null && artistName != null && genre != null) {
                        playList[playlistIndex++] = new Song(title, duration, artistName, genre);
                    } else {
                        System.out.println("Skipping song with null values: " + title);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database. Please check your connection settings.");
        }
    }

    public static void addNewSong(Song[] playList) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the details for the new song:");

        System.out.print("Title: ");
        String title = sc.nextLine();
        
            double duration = 0.0; 
        try {
            System.out.print("Duration: ");
            duration = sc.nextDouble();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid duration. Setting duration to 0.0");
            sc.nextLine();
        }

        System.out.print("Artist Name: ");
        String artistName = sc.nextLine();

        System.out.print("Genre: ");
        String genre = sc.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO songs (title, duration, artist_name, genre) " +
                    "VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, title);
                preparedStatement.setDouble(2, duration);
                preparedStatement.setString(3, artistName);
                preparedStatement.setString(4, genre);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Song added to the database successfully!");
                    addToPlayList(playList);
                } else {
                    System.out.println("Failed to add the song to the database.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database. Please check your connection settings.");
        }
        }
        
    }

