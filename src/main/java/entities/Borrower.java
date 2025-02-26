package entities;

import java.util.List;

public class Borrower {

    private int borrower_id;
    private String name;
    private String address;

    private List<Loan> borrowedBooks;

    public Borrower(int borrower_id, String name, String address, List<Loan> books) {
        this.borrower_id = borrower_id;
        this.name = name;
        this.address = address;
        this.borrowedBooks = books;
    }

    public Borrower(String name, String address, List<Loan> books) {
        this.name = name;
        this.address = address;
        this.borrowedBooks = books;
    }

    public int getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(int borrower_id) {
        this.borrower_id = borrower_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Loan> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Loan> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void addLoan(Loan loan){
        borrowedBooks.add(loan);
    }

    public String toString(){
        return borrower_id + " " + name + " " + " " + address;
    }
}
