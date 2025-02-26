package entities;

import java.util.List;

public class Author {

    private int author_id;
    private String name;

    private List<Book> books;

    //Getting data
    public Author(int author_id,  String name, List<Book> books){
        this.author_id = author_id;
        this.name = name;
        this.books = books;
    }

    //Not having id since DB auto generate it. When we create a new Author
    public Author(String name, List<Book> books){
        this.name = name;
        this.books = books;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
