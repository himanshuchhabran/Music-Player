package com.himanshu;
class Song {
    
    private String title;        
    private double duration;     
    private String artistName;   
    private String genre;       

    public Song(String title, double duration, String artistName, String genre) {
        this.title = title;            
        this.duration = duration;      
        this.artistName = artistName;  
        this.genre = genre;             
    }

    public String getTitle() {
        return title; 
    }

    
    public double getDuration() {
        return duration; 
    }

    
    public String getArtistName() {
        return artistName; 
    }


    public String getGenre() {
        return genre; 
    }

    

    
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                ", artistName='" + artistName + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}