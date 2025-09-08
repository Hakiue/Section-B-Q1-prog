/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package librarymanagement;

import com.mycompany.librarymanagement.Book;
import com.mycompany.librarymanagement.LibraryItem;
import com.mycompany.librarymanagement.LibraryManager;
import com.mycompany.librarymanagement.Magazine;
import java.util.Scanner;

/**
 * Main application class for Library Management System
 * Demonstrates console interface and user interaction
 */
public class LibraryManagementApp {
    private LibraryManager libraryManager;
    private Scanner scanner;
    
    // Constructor
    public LibraryManagementApp() {
        this.libraryManager = new LibraryManager();
        this.scanner = new Scanner(System.in);
    }
    
    // Main method to start the application
    public static void main(String[] args) {
        LibraryManagementApp app = new LibraryManagementApp();
        app.run();
    }
    
    // Main application loop
    public void run() {
        System.out.println("Welcome to the Library Management System!");
        System.out.println("=========================================");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    addNewItem();
                    break;
                case 2:
                    searchItemById();
                    break;
                case 3:
                    searchItemsByTitle();
                    break;
                case 4:
                    borrowItem();
                    break;
                case 5:
                    returnItem();
                    break;
                case 6:
                    viewAvailableItems();
                    break;
                case 7:
                    viewBorrowedItems();
                    break;
                case 8:
                    generateReport();
                    break;
                case 9:
                    System.out.println("Thank you for using the Library Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    // Display main menu
    private void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            LIBRARY MANAGEMENT MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Add New Item (Book/Magazine)");
        System.out.println("2. Search Item by ID");
        System.out.println("3. Search Items by Title");
        System.out.println("4. Borrow Item");
        System.out.println("5. Return Item");
        System.out.println("6. View Available Items");
        System.out.println("7. View Borrowed Items");
        System.out.println("8. Generate Library Report");
        System.out.println("9. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Please select an option (1-9): ");
    }
    
    // Get menu choice with validation
    private int getMenuChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            return -1; // Invalid choice
        }
    }
    
    // Add new item to library
    private void addNewItem() {
        System.out.println("\n=== ADD NEW ITEM ===");
        System.out.println("1. Add Book");
        System.out.println("2. Add Magazine");
        System.out.print("Select item type (1-2): ");
        
        try {
            int type = Integer.parseInt(scanner.nextLine().trim());
            
            if (type == 1) {
                addBook();
            } else if (type == 2) {
                addMagazine();
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
    
    // Add a new book
    private void addBook() {
        System.out.println("\n--- Adding New Book ---");
        
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        
        System.out.print("Enter Number of Pages: ");
        int pages;
        try {
            pages = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of pages. Book not added.");
            return;
        }
        
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine().trim();
        
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        
        if (id.isEmpty() || title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
            System.out.println("All fields are required. Book not added.");
            return;
        }
        
        Book book = new Book(id, title, author, pages, genre, isbn);
        if (libraryManager.addItem(book)) {
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Failed to add book. ID might already exist or library is full.");
        }
    }
    
    // Add a new magazine
    private void addMagazine() {
        System.out.println("\n--- Adding New Magazine ---");
        
        System.out.print("Enter Magazine ID: ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter Author/Editor: ");
        String author = scanner.nextLine().trim();
        
        System.out.print("Enter Issue Number: ");
        int issueNumber;
        try {
            issueNumber = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid issue number. Magazine not added.");
            return;
        }
        
        System.out.print("Enter Publication Date: ");
        String pubDate = scanner.nextLine().trim();
        
        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine().trim();
        
        if (id.isEmpty() || title.isEmpty() || author.isEmpty() || pubDate.isEmpty() || publisher.isEmpty()) {
            System.out.println("All fields are required. Magazine not added.");
            return;
        }
        
        Magazine magazine = new Magazine(id, title, author, issueNumber, pubDate, publisher);
        if (libraryManager.addItem(magazine)) {
            System.out.println("Magazine added successfully!");
        } else {
            System.out.println("Failed to add magazine. ID might already exist or library is full.");
        }
    }
    
    // Search for item by ID
    private void searchItemById() {
        System.out.println("\n=== SEARCH ITEM BY ID ===");
        System.out.print("Enter Item ID: ");
        String id = scanner.nextLine().trim();
        
        LibraryItem item = libraryManager.searchItemById(id);
        if (item != null) {
            System.out.println("\nItem Found:");
            System.out.println(item.toString());
        } else {
            System.out.println("No item found with ID: " + id);
        }
    }
    
    // Search for items by title
    private void searchItemsByTitle() {
        System.out.println("\n=== SEARCH ITEMS BY TITLE ===");
        System.out.print("Enter Title (partial match allowed): ");
        String title = scanner.nextLine().trim();
        
        LibraryItem[] results = libraryManager.searchItemsByTitle(title);
        if (results.length > 0) {
            System.out.println("\nItems Found (" + results.length + " results):");
            for (int i = 0; i < results.length; i++) {
                System.out.println((i + 1) + ". " + results[i].toString());
            }
        } else {
            System.out.println("No items found with title containing: " + title);
        }
    }
    
    // Borrow an item
    private void borrowItem() {
        System.out.println("\n=== BORROW ITEM ===");
        System.out.print("Enter Item ID to borrow: ");
        String id = scanner.nextLine().trim();
        
        LibraryItem item = libraryManager.searchItemById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        
        if (!item.isAvailable()) {
            System.out.println("Item is already borrowed by: " + item.getBorrowerName());
            return;
        }
        
        System.out.print("Enter Borrower Name: ");
        String borrowerName = scanner.nextLine().trim();
        
        if (borrowerName.isEmpty()) {
            System.out.println("Borrower name is required.");
            return;
        }
        
        if (libraryManager.borrowItem(id, borrowerName)) {
            System.out.println("Item borrowed successfully!");
            System.out.println("Borrowed: " + item.getTitle() + " by " + borrowerName);
        } else {
            System.out.println("Failed to borrow item.");
        }
    }
    
    // Return an item
    private void returnItem() {
        System.out.println("\n=== RETURN ITEM ===");
        System.out.print("Enter Item ID to return: ");
        String id = scanner.nextLine().trim();
        
        LibraryItem item = libraryManager.searchItemById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        
        if (item.isAvailable()) {
            System.out.println("Item is not currently borrowed.");
            return;
        }
        
        String borrower = item.getBorrowerName();
        if (libraryManager.returnItem(id)) {
            System.out.println("Item returned successfully!");
            System.out.println("Returned: " + item.getTitle() + " (was borrowed by " + borrower + ")");
        } else {
            System.out.println("Failed to return item.");
        }
    }
    
    // View available items
    private void viewAvailableItems() {
        System.out.println("\n=== AVAILABLE ITEMS ===");
        LibraryItem[] available = libraryManager.getAvailableItems();
        
        if (available.length == 0) {
            System.out.println("No items currently available.");
        } else {
            System.out.println("Available Items (" + available.length + " items):");
            for (int i = 0; i < available.length; i++) {
                System.out.println((i + 1) + ". " + available[i].toString());
            }
        }
    }
    
    // View borrowed items
    private void viewBorrowedItems() {
        System.out.println("\n=== BORROWED ITEMS ===");
        LibraryItem[] borrowed = libraryManager.getBorrowedItems();
        
        if (borrowed.length == 0) {
            System.out.println("No items currently borrowed.");
        } else {
            System.out.println("Borrowed Items (" + borrowed.length + " items):");
            for (int i = 0; i < borrowed.length; i++) {
                System.out.println((i + 1) + ". " + borrowed[i].toString());
            }
        }
    }
    
    // Generate and display full report
    private void generateReport() {
        libraryManager.generateDetailedReport();
    }
}
