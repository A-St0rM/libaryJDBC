package entities;

import java.util.List;
import java.util.Objects;

public class Borrower {

    private int borrower_id;
    private String name;
    private String address;
    private int zip;
    private List<Loan> borrowedBooks;

    public Borrower(int borrower_id, String name, String address, List<Loan> books) {
        this.borrower_id = borrower_id;
        this.name = name;
        this.address = address;
        this.borrowedBooks = books;
    }

    public Borrower(int borrower_id, String name, String address, int zip) {
        this.borrower_id = borrower_id;
        this.name = name;
        this.address = address;
        this.zip = zip;
    }

    public Borrower(String name, String address, int zip) {
        this.name = name;
        this.address = address;
        this.zip = zip;
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

    public int getZip(){
        return zip;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return borrower_id == borrower.borrower_id && zip == borrower.zip && Objects.equals(name, borrower.name) && Objects.equals(address, borrower.address) && Objects.equals(borrowedBooks, borrower.borrowedBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrower_id, name, address, zip, borrowedBooks);
    }
}
