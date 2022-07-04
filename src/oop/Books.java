package oop;

import java.io.Serializable;

public class Books implements Serializable {
    private int book_id;
    private String title;
    private String authors;
    private double avgRating;
    private String isbn;
    private double isbn13;
    private String language_code;
    private int page;
    private int ratingCount;
    private int reviewCount;
    private int quantity;

    public Books(int book_id, String title, String authors, double avgRating,
                 String isbn, double isbn13, String language_code, int page,
                 int ratingCount, int reviewCount, int quantity) {
        this.book_id = book_id;
        this.title = title;
        this.authors = authors;
        this.avgRating = avgRating;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.language_code = language_code;
        this.page = page;
        this.ratingCount = ratingCount;
        this.reviewCount = reviewCount;
        this.quantity = quantity;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getIsbn13() {
        return isbn13;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public int getPage() {
        return page;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Books{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", avgRating=" + avgRating +
                ", isbn=" + isbn +
                ", isbn13=" + isbn13 +
                ", language_code='" + language_code + '\'' +
                ", page=" + page +
                ", ratingCount=" + ratingCount +
                ", reviewCount=" + reviewCount +
                ", quantity=" + quantity +
                '}';
    }

    public String getString() {
        return ""+book_id+",-"+title+",-"+authors+",-"+avgRating+",-"+isbn+",-"+page+",-"+ratingCount+",-"+quantity;
    }
}
