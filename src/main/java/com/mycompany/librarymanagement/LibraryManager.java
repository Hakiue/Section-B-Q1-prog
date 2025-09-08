/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagement;

/**
 *
 * @author zaaki
 */
import java.util.Scanner;

public class LibraryManager {
    // Arrays to store library items (demonstrating advanced arrays)
    private LibraryItem[] libraryItems;
    private int itemCount;
    private final int MAX_ITEMS = 100;
    
    // Constructor
    public LibraryManager() {
        this.libraryItems = new LibraryItem[MAX_ITEMS];
        this.itemCount = 0;
        initializeDefaultItems(); // Add some default items for demonstration
    }
    
    // Initialize with some default items for demonstration
    private void initializeDefaultItems() {
        addItem(new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald", 180, "Fiction", "978-0-7432-7356-5"));
        addItem(new Book("B002", "To Kill a Mockingbird", "Harper Lee", 281, "Fiction", "978-0-06-112008-4"));
        addItem(new Book("B003", "1984", "George Orwell", 328, "Dystopian", "978-0-452-28423-4"));
        addItem(new Magazine("M001", "National Geographic", "Various Authors", 201, "January 2024", "National Geographic Society"));
        addItem(new Magazine("M002", "Time Magazine", "Various Authors", 5, "February 2024", "Time Inc."));
    }
    
    // Method to add a new item to the library
    public boolean addItem(LibraryItem item) {
        if (itemCount < MAX_ITEMS && item != null) {
            // Check for duplicate ID using loops
            for (int i = 0; i < itemCount; i++) {
                if (libraryItems[i].getItemId().equals(item.getItemId())) {
                    return false; // Duplicate ID found
                }
            }
            libraryItems[itemCount] = item;
            itemCount++;
            return true;
        }
        return false;
    }
    
    // Method to search for an item by ID
    public LibraryItem searchItemById(String itemId) {
        if (itemId == null) return null;
        
        // Using loop to search through array
        for (int i = 0; i < itemCount; i++) {
            if (libraryItems[i].getItemId().equalsIgnoreCase(itemId)) {
                return libraryItems[i];
            }
        }
        return null;
    }
    
    // Method to search for items by title (partial match)
    public LibraryItem[] searchItemsByTitle(String title) {
        if (title == null || title.trim().isEmpty()) return new LibraryItem[0];
        
        LibraryItem[] results = new LibraryItem[MAX_ITEMS];
        int resultCount = 0;
        
        // Loop through all items to find matches
        for (int i = 0; i < itemCount; i++) {
            if (libraryItems[i].getTitle().toLowerCase().contains(title.toLowerCase())) {
                results[resultCount] = libraryItems[i];
                resultCount++;
            }
        }
        
        // Return array with exact size
        LibraryItem[] finalResults = new LibraryItem[resultCount];
        for (int i = 0; i < resultCount; i++) {
            finalResults[i] = results[i];
        }
        return finalResults;
    }
    
    // Method to borrow an item
    public boolean borrowItem(String itemId, String borrowerName) {
        LibraryItem item = searchItemById(itemId);
        if (item != null && item.isAvailable()) {
            return item.borrowItem(borrowerName);
        }
        return false;
    }
    
    // Method to return an item
    public boolean returnItem(String itemId) {
        LibraryItem item = searchItemById(itemId);
        if (item != null && !item.isAvailable()) {
            return item.returnItem();
        }
        return false;
    }
    
    // Method to get all available items
    public LibraryItem[] getAvailableItems() {
        LibraryItem[] available = new LibraryItem[itemCount];
        int availableCount = 0;
        
        for (int i = 0; i < itemCount; i++) {
            if (libraryItems[i].isAvailable()) {
                available[availableCount] = libraryItems[i];
                availableCount++;
            }
        }
        
        // Return exact size array
        LibraryItem[] result = new LibraryItem[availableCount];
        for (int i = 0; i < availableCount; i++) {
            result[i] = available[i];
        }
        return result;
    }
    
    // Method to get all borrowed items
    public LibraryItem[] getBorrowedItems() {
        LibraryItem[] borrowed = new LibraryItem[itemCount];
        int borrowedCount = 0;
        
        for (int i = 0; i < itemCount; i++) {
            if (!libraryItems[i].isAvailable()) {
                borrowed[borrowedCount] = libraryItems[i];
                borrowedCount++;
            }
        }
        
        // Return exact size array
        LibraryItem[] result = new LibraryItem[borrowedCount];
        for (int i = 0; i < borrowedCount; i++) {
            result[i] = borrowed[i];
        }
        return result;
    }
    
    // Method to get library statistics
    public String getLibraryStatistics() {
        int totalBooks = 0, totalMagazines = 0;
        int availableBooks = 0, availableMagazines = 0;
        
        // Loop to count items by type and availability
        for (int i = 0; i < itemCount; i++) {
            if (libraryItems[i] instanceof Book) {
                totalBooks++;
                if (libraryItems[i].isAvailable()) {
                    availableBooks++;
                }
            } else if (libraryItems[i] instanceof Magazine) {
                totalMagazines++;
                if (libraryItems[i].isAvailable()) {
                    availableMagazines++;
                }
            }
        }
        
        StringBuilder stats = new StringBuilder();
        stats.append("=== LIBRARY STATISTICS ===\n");
        stats.append("Total Items: ").append(itemCount).append("\n");
        stats.append("Total Books: ").append(totalBooks).append(" (Available: ").append(availableBooks).append(")\n");
        stats.append("Total Magazines: ").append(totalMagazines).append(" (Available: ").append(availableMagazines).append(")\n");
        stats.append("Total Available: ").append(getAvailableItems().length).append("\n");
        stats.append("Total Borrowed: ").append(getBorrowedItems().length).append("\n");
        
        return stats.toString();
    }
    
    // Method to generate detailed report
    public void generateDetailedReport() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                           LIBRARY MANAGEMENT REPORT");
        System.out.println("=".repeat(80));
        
        // Display statistics
        System.out.println(getLibraryStatistics());
        
        // Display all items
        System.out.println("=== ALL LIBRARY ITEMS ===");
        if (itemCount == 0) {
            System.out.println("No items in the library.");
        } else {
            for (int i = 0; i < itemCount; i++) {
                System.out.println((i + 1) + ". " + libraryItems[i].toString());
            }
        }
        
        // Display available items
        System.out.println("\n=== AVAILABLE ITEMS ===");
        LibraryItem[] available = getAvailableItems();
        if (available.length == 0) {
            System.out.println("No items currently available.");
        } else {
            for (int i = 0; i < available.length; i++) {
                System.out.println((i + 1) + ". " + available[i].toString());
            }
        }
        
        // Display borrowed items
        System.out.println("\n=== BORROWED ITEMS ===");
        LibraryItem[] borrowed = getBorrowedItems();
        if (borrowed.length == 0) {
            System.out.println("No items currently borrowed.");
        } else {
            for (int i = 0; i < borrowed.length; i++) {
                System.out.println((i + 1) + ". " + borrowed[i].toString());
            }
        }
        
        System.out.println("=".repeat(80));
    }
    
    // Getter for itemCount (for testing purposes)
    public int getItemCount() {
        return itemCount;
    }
    
    // Method to get all items (for testing purposes)
    public LibraryItem[] getAllItems() {
        LibraryItem[] result = new LibraryItem[itemCount];
        for (int i = 0; i < itemCount; i++) {
            result[i] = libraryItems[i];
        }
        return result;
    }
}