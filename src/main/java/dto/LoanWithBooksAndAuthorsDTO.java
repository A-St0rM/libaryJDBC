package dto;

public class LoanWithBooksAndAuthorsDTO {

    private String authorName;
    private String loanName;
    private int releaseYear;
    private String titel;

    public LoanWithBooksAndAuthorsDTO(String titel, int releaseYear, String loanName, String authorName) {
        this.titel = titel;
        this.releaseYear = releaseYear;
        this.loanName = loanName;
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Loaners" +
                " authorName='" + authorName + '\'' +
                ", loanName='" + loanName + '\'' +
                ", releaseYear=" + releaseYear +
                ", titel='" + titel + '\'';
    }
}
