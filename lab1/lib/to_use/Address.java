public class Address {
    private double streetNumber;
    private String streetName;
    private String theRest;

    private String city;
    private String state;
    private String zip;

    private boolean checkStreetName(String strname) {

        for (int i = 0; i < strname.length(); i++) {
            if (!Character.isLetter(strname.charAt(i))) 
                return false;
        }

        return true;
    }

    


    Address() {

    }

    Address(double streetNumber, String streetName, String theRest, String city, String state, String zip) {
        if (streetNumber < 0) 
            throw new IllegalArgumentException("Invalid street number!");

        if (!checkStreetName(streetName))
            throw new IllegalArgumentException("Invalid street name!");

    }
}
