/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// LibraryItem.java - Base class demonstrating inheritance and information hiding
package com.mycompany.librarymanagement;

/**
 * Abstract base class for all library items
 * Demonstrates inheritance, constructors, and information hiding
 */
public abstract class LibraryItem {
    // Private fields demonstrating information hiding
    private String itemId;
    private String title;
    private String author;
    private boolean isAvailable;
    private String borrowerName;
    
    // Constructor
    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.borrowerName = "";
    }
    
    // Getter methods (public interface)
    public String getItemId() {
        return itemId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public String getBorrowerName() {
        return borrowerName;
    }
    
    // Setter methods with validation
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
    }
    
    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author;
        }
    }
    
    // Methods for borrowing and returning
    public boolean borrowItem(String borrowerName) {
        if (isAvailable && borrowerName != null && !borrowerName.trim().isEmpty()) {
            this.isAvailable = false;
            this.borrowerName = borrowerName;
            return true;
        }
        return false;
    }
    
    public boolean returnItem() {
        if (!isAvailable) {
            this.isAvailable = true;
            this.borrowerName = "";
            return true;
        }
        return false;
    }
    
    // Abstract method to be implemented by subclasses
    public abstract String getItemType();
    
    // Abstract method for calculating late fees
    public abstract double calculateLateFee(int daysLate);
    
    @Override
    public String toString() {
        return String.format("ID: %s | Title: %s | Author: %s | Type: %s | Available: %s | Borrower: %s",
                itemId, title, author, getItemType(), 
                isAvailable ? "Yes" : "No", 
                isAvailable ? "N/A" : borrowerName);
    }
}