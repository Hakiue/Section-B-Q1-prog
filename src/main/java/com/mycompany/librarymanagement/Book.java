/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagement;

/**
 *
 * @author zaaki
 */
public class Book extends LibraryItem {
    private int pages;
    private String genre;
    private String isbn;
    
    // Constructor using super() to call parent constructor
    public Book(String itemId, String title, String author, int pages, String genre, String isbn) {
        super(itemId, title, author);
        this.pages = pages;
        this.genre = genre;
        this.isbn = isbn;
    }
    
    // Getter methods
    public int getPages() {
        return pages;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    // Setter methods with validation
    public void setPages(int pages) {
        if (pages > 0) {
            this.pages = pages;
        }
    }
    
    public void setGenre(String genre) {
        if (genre != null && !genre.trim().isEmpty()) {
            this.genre = genre;
        }
    }
    
    // Implementing abstract methods from parent class
    @Override
    public String getItemType() {
        return "Book";
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // Books: $0.50 per day late
        return daysLate * 0.50;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Pages: %d | Genre: %s | ISBN: %s", 
                pages, genre, isbn);
    }
}
