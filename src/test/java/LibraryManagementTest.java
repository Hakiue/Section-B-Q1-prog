/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.librarymanagement.Book;
import com.mycompany.librarymanagement.LibraryItem;
import com.mycompany.librarymanagement.LibraryManager;
import com.mycompany.librarymanagement.Magazine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LibraryManagementTest {
    private LibraryManager libraryManager;
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Starting Library Management System Tests...");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("All Library Management System Tests completed.");
    }
    
    @BeforeEach
    public void setUp() {
        // Create a fresh LibraryManager for each test
        this.libraryManager = new LibraryManager();
    }
    
    @AfterEach
    public void tearDown() {
        // Clean up after each test
        this.libraryManager = null;
    }
    
    // Test LibraryItem creation and basic functionality
    @Test
    public void testLibraryItemCreation() {
        Book book = new Book("TEST001", "Test Book", "Test Author", 200, "Fiction", "123-456-789");
        
        assertNotNull(book, "Book should be created successfully");
        assertEquals("TEST001", book.getItemId(), "Book ID should match");
        assertEquals("Test Book", book.getTitle(), "Book title should match");
        assertEquals("Test Author", book.getAuthor(), "Book author should match");
        assertTrue(book.isAvailable(), "Book should be available by default");
        assertEquals("", book.getBorrowerName(), "Borrower name should be empty initially");
    }
    
    // Test Book class specific functionality
    @Test
    public void testBookCreation() {
        Book book = new Book("B001", "Java Programming", "John Doe", 300, "Programming", "978-0123456789");
        
        assertEquals(300, book.getPages(), "Book pages should match");
        assertEquals("Programming", book.getGenre(), "Book genre should match");
        assertEquals("978-0123456789", book.getIsbn(), "Book ISBN should match");
        assertEquals("Book", book.getItemType(), "Book type should be 'Book'");
        
        // Test late fee calculation (Books: $0.50 per day)
        double lateFee = book.calculateLateFee(5);
        assertEquals(2.5, lateFee, 0.01, "Book late fee calculation should be correct");
    }
    
    // Test Magazine class specific functionality
    @Test
    public void testMagazineCreation() {
        Magazine magazine = new Magazine("M001", "Tech Today", "Editor Smith", 15, "March 2024", "Tech Publications");
        
        assertEquals(15, magazine.getIssueNumber(), "Magazine issue number should match");
        assertEquals("March 2024", magazine.getPublicationDate(), "Magazine publication date should match");
        assertEquals("Tech Publications", magazine.getPublisher(), "Magazine publisher should match");
        assertEquals("Magazine", magazine.getItemType(), "Magazine type should be 'Magazine'");
        
        // Test late fee calculation (Magazines: $0.25 per day)
        double lateFee = magazine.calculateLateFee(4);
        assertEquals(1.0, lateFee, 0.01, "Magazine late fee calculation should be correct");
    }
    
    // Test inheritance and polymorphism
    @Test
    public void testInheritancePolymorphism() {
        LibraryItem book = new Book("POLY001", "Polymorphism Test", "Test Author", 150, "Test", "000-000-000");
        LibraryItem magazine = new Magazine("POLY002", "Magazine Test", "Test Editor", 1, "Jan 2024", "Test Publisher");
        
        // Test polymorphic behavior
        assertEquals("Book", book.getItemType(), "Book type should be identified correctly through polymorphism");
        assertEquals("Magazine", magazine.getItemType(), "Magazine type should be identified correctly through polymorphism");
        
        // Test different late fee calculations
        assertTrue(book.calculateLateFee(10) > magazine.calculateLateFee(10), 
                   "Book late fee should be higher than magazine late fee");
    }
    
    // Test adding items to library
    @Test
    public void testAddItem() {
        Book newBook = new Book("NEW001", "New Book", "New Author", 100, "Test", "111-111-111");
        
        int initialCount = libraryManager.getItemCount();
        boolean added = libraryManager.addItem(newBook);
        
        assertTrue(added, "Item should be added successfully");
        assertEquals(initialCount + 1, libraryManager.getItemCount(), "Item count should increase by 1");
    }
    
    // Test adding duplicate items
    @Test
    public void testAddDuplicateItem() {
        Book book1 = new Book("DUP001", "Book One", "Author One", 100, "Test", "111-111-111");
        Book book2 = new Book("DUP001", "Book Two", "Author Two", 200, "Test", "222-222-222");
        
        boolean firstAdd = libraryManager.addItem(book1);
        boolean secondAdd = libraryManager.addItem(book2);
        
        assertTrue(firstAdd, "First item should be added successfully");
        assertFalse(secondAdd, "Duplicate item should be rejected");
    }
    
    // Test searching item by ID
    @Test
    public void testSearchItemById() {
        LibraryItem foundItem = libraryManager.searchItemById("B001"); // Default item
        
        assertNotNull(foundItem, "Item should be found by ID");
        assertEquals("B001", foundItem.getItemId(), "Correct item should be found");
    }
    
    // Test searching for non-existent item
    @Test
    public void testSearchItemByIdNotFound() {
        LibraryItem notFound = libraryManager.searchItemById("NONEXISTENT");
        
        assertNull(notFound, "Non-existent item should return null");
    }
    
    // Test searching items by title
    @Test
    public void testSearchItemsByTitle() {
        LibraryItem[] results = libraryManager.searchItemsByTitle("Great");
        
        assertTrue(results.length > 0, "Search should return results");
        assertTrue(results[0].getTitle().toLowerCase().contains("great"), 
                   "Found item should contain search term");
    }
    
    // Test borrowing an item
    @Test
    public void testBorrowItem() {
        boolean borrowed = libraryManager.borrowItem("B002", "John Smith");
        LibraryItem item = libraryManager.searchItemById("B002");
        
        assertTrue(borrowed, "Item should be borrowed successfully");
        assertFalse(item.isAvailable(), "Item should no longer be available");
        assertEquals("John Smith", item.getBorrowerName(), "Correct borrower name should be set");
    }
    
    // Test borrowing non-existent item
    @Test
    public void testBorrowItemNotFound() {
        boolean borrowed = libraryManager.borrowItem("NONEXISTENT", "John Smith");
        
        assertFalse(borrowed, "Cannot borrow non-existent item");
    }
    
    // Test borrowing already borrowed item
    @Test
    public void testBorrowItemAlreadyBorrowed() {
        libraryManager.borrowItem("B003", "First Borrower");
        boolean secondBorrow = libraryManager.borrowItem("B003", "Second Borrower");
        
        assertFalse(secondBorrow, "Cannot borrow already borrowed item");
    }
    
    // Test returning an item
    @Test
    public void testReturnItem() {
        libraryManager.borrowItem("M001", "Jane Doe");
        boolean returned = libraryManager.returnItem("M001");
        LibraryItem item = libraryManager.searchItemById("M001");
        
        assertTrue(returned, "Item should be returned successfully");
        assertTrue(item.isAvailable(), "Item should be available again");
        assertEquals("", item.getBorrowerName(), "Borrower name should be cleared");
    }
    
    // Test returning non-existent item
    @Test
    public void testReturnItemNotFound() {
        boolean returned = libraryManager.returnItem("NONEXISTENT");
        
        assertFalse(returned, "Cannot return non-existent item");
    }
    
    // Test returning item that wasn't borrowed
    @Test
    public void testReturnItemNotBorrowed() {
        boolean returned = libraryManager.returnItem("M002"); // Available item
        
        assertFalse(returned, "Cannot return item that wasn't borrowed");
    }
    
    // Test getting available items
    @Test
    public void testGetAvailableItems() {
        libraryManager.borrowItem("B001", "Test User");
        
        LibraryItem[] available = libraryManager.getAvailableItems();
        LibraryItem[] all = libraryManager.getAllItems();
        
        assertTrue(available.length < all.length, "Available items should be less than total items");
        
        // Check all returned items are actually available
        for (LibraryItem item : available) {
            assertTrue(item.isAvailable(), "All returned items should be available");
        }
    }
    
    // Test getting borrowed items
    @Test
    public void testGetBorrowedItems() {
        libraryManager.borrowItem("B002", "Test User");
        libraryManager.borrowItem("M001", "Another User");
        
        LibraryItem[] borrowed = libraryManager.getBorrowedItems();
        
        assertTrue(borrowed.length > 0, "Some items should be borrowed");
        
        // Check all returned items are actually borrowed
        for (LibraryItem item : borrowed) {
            assertFalse(item.isAvailable(), "All returned items should be borrowed");
        }
    }
    
    // Test array functionality
    @Test
    public void testArrayFunctionality() {
        LibraryItem[] allItems = libraryManager.getAllItems();
        
        assertTrue(allItems.length > 0, "Array should contain items");
        assertTrue(allItems.length <= 100, "Array should be within expected bounds");
        
        // Test array element access
        for (int i = 0; i < allItems.length; i++) {
            assertNotNull(allItems[i], "Array element should not be null");
        }
    }
    
    // Test loop functionality
    @Test
    public void testLoopFunctionality() {
        // Test search loops work correctly
        LibraryItem[] searchResults = libraryManager.searchItemsByTitle("a");
        int manualCount = 0;
        LibraryItem[] allItems = libraryManager.getAllItems();
        
        // Manual count using loop
        for (LibraryItem item : allItems) {
            if (item.getTitle().toLowerCase().contains("a")) {
                manualCount++;
            }
        }
        
        assertEquals(manualCount, searchResults.length, "Loop-based search should match manual count");
    }
    
    // Test constructor chaining
    @Test
    public void testConstructorChaining() {
        Book book = new Book("CONST001", "Constructor Test", "Test Author", 100, "Test", "000-000-000");
        
        // Test that parent constructor was called
        assertEquals("CONST001", book.getItemId(), "Parent constructor should set ID");
        assertEquals("Constructor Test", book.getTitle(), "Parent constructor should set title");
        assertEquals("Test Author", book.getAuthor(), "Parent constructor should set author");
        assertTrue(book.isAvailable(), "Parent constructor should set availability");
        
        // Test that child constructor set specific fields
        assertEquals(100, book.getPages(), "Child constructor should set pages");
        assertEquals("Test", book.getGenre(), "Child constructor should set genre");
        assertEquals("000-000-000", book.getIsbn(), "Child constructor should set ISBN");
    }
    
    // Test information hiding (encapsulation)
    @Test
    public void testInformationHiding() {
        Book book = new Book("HIDE001", "Hiding Test", "Test Author", 100, "Test", "000-000-000");
        
        // Test that we can only access through public methods
        assertNotNull(book.getItemId(), "Should be able to access through public getter");
        assertNotNull(book.getTitle(), "Should be able to access through public getter");
        
        // Test that setters work with validation
        book.setTitle("New Title");
        assertEquals("New Title", book.getTitle(), "Setter should change title");
        
        // Test borrowing changes internal state
        boolean borrowed = book.borrowItem("Test Borrower");
        assertTrue(borrowed, "Borrowing should succeed");
        assertFalse(book.isAvailable(), "Internal state should change");
        assertEquals("Test Borrower", book.getBorrowerName(), "Borrower name should be set");
    }
    
    // Test null input handling
    @Test
    public void testNullInputHandling() {
        assertNull(libraryManager.searchItemById(null), "Should handle null ID gracefully");
        
        LibraryItem[] emptyResults = libraryManager.searchItemsByTitle(null);
        assertEquals(0, emptyResults.length, "Should return empty array for null title");
        
        assertFalse(libraryManager.borrowItem(null, "Test User"), "Should handle null ID in borrow");
        assertFalse(libraryManager.borrowItem("B001", null), "Should handle null borrower name");
    }
    
    // Test edge cases
    @Test
    public void testEdgeCases() {
        // Test empty string handling
        LibraryItem[] emptyResults = libraryManager.searchItemsByTitle("");
        assertEquals(0, emptyResults.length, "Should return empty array for empty title");
        
        // Test case insensitive search
        LibraryItem[] results1 = libraryManager.searchItemsByTitle("GREAT");
        LibraryItem[] results2 = libraryManager.searchItemsByTitle("great");
        assertEquals(results1.length, results2.length, "Search should be case insensitive");
    }
    
    // Test late fee calculations
    @Test
    public void testLateFeeCalculations() {
        Book book = new Book("FEE001", "Fee Test", "Test Author", 100, "Test", "000-000-000");
        Magazine magazine = new Magazine("FEE002", "Fee Test", "Test Editor", 1, "Jan 2024", "Test Publisher");
        
        // Test zero days late
        assertEquals(0.0, book.calculateLateFee(0), 0.01, "No late fee for 0 days");
        assertEquals(0.0, magazine.calculateLateFee(0), 0.01, "No late fee for 0 days");
        
        // Test specific calculations
        assertEquals(5.0, book.calculateLateFee(10), 0.01, "Book: $0.50 per day");
        assertEquals(2.5, magazine.calculateLateFee(10), 0.01, "Magazine: $0.25 per day");
    }
}