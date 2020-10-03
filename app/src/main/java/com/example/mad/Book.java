package com.example.mad;

public class Book {
    String image;
    String title;
    String author;
    String pages;
    String price;

    public Book(String image, String title, String author, String pages, String price) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;

    }

    public Book() {

    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPages() {
        return pages;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

