package to_use;

public class Address {
    private double streetNumber;
    private String streetName;
    private String theRest;
    private String city;
    private String state;
    private String zip;


    static boolean checkStreetName(String strname) {

        for (int i = 0; i < strname.length(); i++) {
            char c = strname.charAt(i);

            if (!Character.isLetter(c) && c != ' ') 
                return false;
        }

        return true;
    }

    static boolean checkCity(String cityName) {

        for (int i = 0; i < cityName.length(); i++) {
            char c = cityName.charAt(i);

            if (!Character.isLetter(c) && c != ' ' && c != '-') {
                return false;
            }
        }

        return true;
    }

    static boolean checkState(String stateAbbr) {

        for (int i = 0; i < stateAbbr.length(); i++) {
            char c = stateAbbr.charAt(i);

            if (!Character.isLetter(c)) {
                return false;
            }

            else if (!Character.isUpperCase(c)) {
                return false;
            }
        }

        return true;
    }

    static boolean checkZip(String zipCode) {

        if (zipCode.length() != 5) {
            return false;
        }

        for (int i = 0; i < zipCode.length(); i++) {
            char c = zipCode.charAt(i);

            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
    



    public Address() {

    }

    public Address(double streetNumber, String streetName, String theRest, String city, String state, String zip) {
        if (streetNumber < 0) 
            throw new IllegalArgumentException("Invalid street number!");

        if (!checkStreetName(streetName))
            throw new IllegalArgumentException("Invalid street name! If street name contains numbers (52nd, for example), spell it out (Fifty Second).");

        if (!checkCity(city)) 
            throw new IllegalArgumentException("Invalid city name!");
        
        if (!checkState(state)) 
            throw new IllegalArgumentException("Invalid state abbreviation!");

        if (!checkZip(zip))
            throw new IllegalArgumentException("Invalid zip code!");

        this.streetNumber = streetNumber;
        this.streetName = Name.capitalize(streetName.trim());
        this.theRest = theRest;
        this.city = Name.capitalize(city.trim());
        this.state = state;
        this.zip = zip;

    }
    

    public double getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(double newStreetNumber) {
        if (streetNumber < 0) 
            throw new IllegalArgumentException("Invalid street number!");


        this.streetNumber = newStreetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String newStreetName) {
        if (!checkStreetName(newStreetName))
            throw new IllegalArgumentException("Invalid street name! If street name contains numbers (52nd, for example), spell it out (Fifty Second).");

        this.streetName = Name.capitalize(newStreetName.trim());
    }

    public String getTheRest() {
        return theRest;
    }

    public void setTheRest(String newTheRest) {
        this.theRest = newTheRest;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String newCity) {
        if (!checkCity(newCity))
            throw new IllegalArgumentException("Invalid city name!");

        this.city = Name.capitalize(newCity.trim());
    }

    public String getState() {
        return state;
    }

    public void setState(String newState) {
        if (!checkState(newState))
            throw new IllegalArgumentException("Invalid state name!");

        this.state = newState;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String newZip) {
        if (!checkZip(newZip))
            throw new IllegalArgumentException("Invalid zip code!");

        this.zip = newZip;
    }
}
