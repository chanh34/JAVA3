package Lab2;

public class User {
	private String fullname;
    private boolean gender;
    private String country;

    
    // Getter và Setter cho fullname
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    // Getter và Setter cho gender
    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    // Getter và Setter cho country
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
