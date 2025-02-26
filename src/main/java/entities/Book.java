package entities;

import java.util.List;

public class Book {

    private int book_id;
    private String titel;
    private List<Author> authors;
    private int releaseYear;

    public Book(int book_id, String titel, List<Author> authors, int releaseYear) {
        this.book_id = book_id;
        this.titel = titel;
        this.authors = authors;
        this.releaseYear = releaseYear;
    }

    public Book(String titel, List<Author> authors, int releaseYear) {
        this.titel = titel;
        this.authors = authors;
        this.releaseYear = releaseYear;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", titel='" + titel + '\'' +
                ", authors=" + authors +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
