package com.avishkar.e_bookstore.model;

public class BooksModel {

    int id;
    String bookName, authorName, authorContact,price;

    public BooksModel(int id, String bookName, String authorName, String authorContact, String price) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.authorContact = authorContact;
        this.price = price;
    }

    public BooksModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorContact() {
        return authorContact;
    }

    public void setAuthorContact(String authorContact) {
        this.authorContact = authorContact;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
