package dto;

public class BooksAndAuthorsDTO {

    private String authorName;
    private String titel;
    private int releaseYear;

    public BooksAndAuthorsDTO(String authorName, String titel, int releaseYear) {
        this.authorName = authorName;
        this.titel = titel;
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Books And Authors " +
                "authorName='" + authorName + '\'' +
                ", titel='" + titel + '\'' +
                ", releaseYear=" + releaseYear;
    }
}
