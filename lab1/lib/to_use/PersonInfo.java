import to_use.Name;
import to_use.Address;
import to_use.PhoneList;


public class PersonInfo {
    private Name name;
    private Address address;
    private PhoneList phoneList;

    PersonInfo() {

    }

    // Most likely won't need additional checking HERE 
    // since each object will likely have been created with 
    // their respective class's constructor (which DOES have checking)
    PersonInfo(Name name, Address address, PhoneList phoneList) {
        this.name = name;
        this.address = address;
        this.phoneList = phoneList;
    }


    public String getName() {
        return name.getFirst() + " " + name.getMiddleInitial() + " " + name.getLast();
    }

    public void setName(Name newName) {
        this.name.first = newName.getFirst();
        this.name.middleInitial = newName.getMiddleInitial();
        this.name.last = newName.getLast();
    }

    public void setName(String first, String middleInitial, String last) {
        if (first == null || first.isBlank())
            throw new IllegalArgumentException("First name is required!");
        
        if (last == null || last.isBlank())
            throw new IllegalArgumentException("Last name is required!");

        if (middleInitial != null && middleInitial.length() != 1)
            throw new IllegalArgumentException("Middle initial must be only 1 character!");
        
        
        if (!contentChecker(first, "first"))
            throw new IllegalArgumentException("Invalid first name!");
        
        if (!contentChecker(last, "last"))
            throw new IllegalArgumentException("Invalid last name!");
    
        if (!contentChecker(middleInitial, "middle"))
            throw new IllegalArgumentException("Invalid middle initial!");


        this.first = capitalize(first.trim());
        this.last = capitalize(last.trim());
        if (middleInitial == null) {
            this.middleInitial = null;
        }
        else {
            this.middleInitial = middleInitial.toUpperCase();
        }
    }



    public String getAddress() {
        return address.getStreetNumber() 
            + " " 
            + address.getStreetName() 
            + " "
            + address.getTheRest()
            + ", "
            + address.getCity()
            + ", "
            + address.getState()
            + " "
            + address.getZip();

    }

    public void setAddress(Address newAddress) {
        this.address.streetNumber = newAddress.getStreetNumber();
        this.address.streetName = newAddress.getStreetName();
        this.address.theRest = newAddress.getTheRest();
        this.address.city = newAddress.getCity();
        this.address.state = newAddress.getState();
        this.address.zip = newAddress.getZip();
    }

    public void setAddress(double streetNumber, String streetName, String theRest, String city, String state, String zip) {
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
        this.streetName = capitalized(streetName.trim());
        this.theRest = theRest;
        this.city = capitalized(city.trim());
        this.state = state;
        this.zip = zip;
    }


    public void showPhoneList() {
        phoneList.showPhoneNumbers();
    }

    public void addNewNumber(Phone newNumber) {
        phoneList.add(newNumber);
    }

    public void addNewNumber(String areaCode, String exchange, String extension) {
        Phone curPhone = new Phone(areaCode, exchange, extension);

        phoneList.add(curPhone);
    }

    public void remANumber(int idx) {
        phoneList.remPhoneNumber(idx);
    }

    public void emptyPhoneList() {
        phoneList.clearPhoneArray();
    }

}
