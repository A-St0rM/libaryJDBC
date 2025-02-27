package dk.storm;

import entities.Book;
import entities.Borrower;
import exceptions.DatabaseException;
import persistence.*;
import java.net.Authenticator;

public class Main
{
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/libary?currentSchema=public";
    private static DatabaseConnection databaseConnection;

    static
    {
        try
        {
            databaseConnection = new DatabaseConnection(USERNAME, PASSWORD, URL);
        }
        catch (DatabaseException e)
        {
            System.out.println(e.getMessage());

        }
    }

    public static void main(String[] args)
    {

        BorrowerRepo borrowerRepo = new BorrowerRepo(databaseConnection);
        BookRepo bookRepo = new BookRepo(databaseConnection);
        AuthorRepo authorRepo = new AuthorRepo(databaseConnection);
        LoanRepo loanRepo = new LoanRepo(databaseConnection);

        try
        {
            // Get a user by name
//            Borrower borrower = borrowerRepo.findBorrowerById(1);
//            System.out.println(borrower);

//            Book book = bookRepo.findBookByName("Den lange rejse");
//            System.out.println(book);

//            Book book = authorRepo.findBookByAuthor("Karen Blixen");
//            System.out.print(book);

//            borrowerRepo.getAllBorrowerWithAddress().forEach(System.out::println);
//
//            bookRepo.getAllBooksAndAuthors().forEach(System.out::println);
//
//            loanRepo.GetAllLoanersWithBooktitelAndAuthors().forEach(System.out::println);
//            Borrower borrower2 = new Borrower("Jens", "Voltvej 5", 7500);
//            Borrower borrower = borrowerRepo.InsertNewBorrower(borrower2);

//            loanRepo.deleteLoaner(2,3);

            bookRepo.updateBookTitle("Den korte rejse", "Den lange rejse");


        }
        catch (DatabaseException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
        }
    }
}

