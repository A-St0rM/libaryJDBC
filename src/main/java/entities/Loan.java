package entities;

import java.time.LocalDate;

public class Loan {

    private Book book;
    private LocalDate date;
    private int loan_id;

    public Loan(Book book, LocalDate date, int loan_id) {
        this.book = book;
        this.date = date;
        this.loan_id = loan_id;
    }

    public Loan(LocalDate date, Book book) {
        this.date = date;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }
}
