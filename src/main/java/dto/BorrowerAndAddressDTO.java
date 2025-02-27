package dto;

public class BorrowerAndAddressDTO {

    private String name;
    private int zip;
    private String city;

    public BorrowerAndAddressDTO(String name, int zip, String city) {
        this.name = name;
        this.zip = zip;
        this.city = city;
    }

    public BorrowerAndAddressDTO(String name, String city, int zip) {
        this.name = name;
        this.zip = zip;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Borrower: " +
                "name='" + name + '\'' +
                ", zip=" + zip +
                ", city='" + city + '\'';
    }
}
