/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagement;

/**
 *
 * @author zaaki
 */
public class Magazine extends LibraryItem {
    private int issueNumber;
    private String publicationDate;
    private String publisher;
    
    // Constructor using super() to call parent constructor
    public Magazine(String itemId, String title, String author, int issueNumber, 
                   String publicationDate, String publisher) {
        super(itemId, title, author);
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
    }
    
    // Getter methods
    public int getIssueNumber() {
        return issueNumber;
    }
    
    public String getPublicationDate() {
        return publicationDate;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    // Setter methods with validation
    public void setIssueNumber(int issueNumber) {
        if (issueNumber > 0) {
            this.issueNumber = issueNumber;
        }
    }
    
    public void setPublicationDate(String publicationDate) {
        if (publicationDate != null && !publicationDate.trim().isEmpty()) {
            this.publicationDate = publicationDate;
        }
    }
    
    // Implementing abstract methods from parent class
    @Override
    public String getItemType() {
        return "Magazine";
    }
    
    @Override
    public double calculateLateFee(int daysLate) {
        // Magazines: $0.25 per day late
        return daysLate * 0.25;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Issue: %d | Date: %s | Publisher: %s", 
                issueNumber, publicationDate, publisher);
    }
}
