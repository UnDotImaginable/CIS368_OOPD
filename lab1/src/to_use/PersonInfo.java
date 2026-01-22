package to_use;

public class PersonInfo {
    private Name name;
    private Address address;
    private PhoneList phoneList;

    public PersonInfo() {

    }

    // Most likely won't need additional checking HERE 
    // since each object will likely have been created with 
    // their respective class's constructor (which DOES have checking)
    public PersonInfo(Name name, Address address, PhoneList phoneList) {
        this.name = name;
        this.address = address;
        this.phoneList = phoneList;
    }


    public String getName() {
        return name.getFirst() + " " + name.getMiddleInitial() + " " + name.getLast();
    }

    public void setName(Name newName) {
        this.name.setFirst(newName.getFirst());
        this.name.setMiddleInitial(newName.getMiddleInitial());
        this.name.setLast(newName.getLast());
    }

    public void setName(String first, String middleInitial, String last) {
        if (first == null || first.isBlank())
            throw new IllegalArgumentException("First name is required!");
        
        if (last == null || last.isBlank())
            throw new IllegalArgumentException("Last name is required!");

        if (middleInitial != null && middleInitial.length() != 1)
            throw new IllegalArgumentException("Middle initial must be only 1 character!");
        
        
        if (!Name.contentChecker(first, "first"))
            throw new IllegalArgumentException("Invalid first name!");
        
        if (!Name.contentChecker(last, "last"))
            throw new IllegalArgumentException("Invalid last name!");
    
        if (!Name.contentChecker(middleInitial, "middle"))
            throw new IllegalArgumentException("Invalid middle initial!");


        this.name.setFirst(Name.capitalize(first.trim()));
        this.name.setLast(Name.capitalize(last.trim()));
        if (middleInitial == null) {
            this.name.setMiddleInitial(null);
        }
        else {
            this.name.setMiddleInitial(middleInitial.toUpperCase());
        }
    }



    public String getAddress() {
        return (int)address.getStreetNumber() 
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
        this.address.setStreetNumber(newAddress.getStreetNumber());
        this.address.setStreetName(newAddress.getStreetName());
        this.address.setTheRest(newAddress.getTheRest());
        this.address.setCity(newAddress.getCity());
        this.address.setState(newAddress.getState());
        this.address.setZip(newAddress.getZip());

    }

    public void setAddress(double streetNumber, String streetName, String theRest, String city, String state, String zip) {
        if (streetNumber < 0) 
            throw new IllegalArgumentException("Invalid street number!");

        if (!Address.checkStreetName(streetName))
            throw new IllegalArgumentException("Invalid street name! If street name contains numbers (52nd, for example), spell it out (Fifty Second).");

        if (!Address.checkCity(city)) 
            throw new IllegalArgumentException("Invalid city name!");
        
        if (!Address.checkState(state)) 
            throw new IllegalArgumentException("Invalid state abbreviation!");

        if (!Address.checkZip(zip))
            throw new IllegalArgumentException("Invalid zip code!");

        this.address.setStreetNumber(streetNumber);
        this.address.setStreetName(Name.capitalize(streetName.trim()));
        this.address.setTheRest(theRest);
        this.address.setCity(Name.capitalize(city.trim()));
        this.address.setState(state);
        this.address.setZip(zip);
    }


    public void showPhoneList() {
        phoneList.showPhoneNumbers();
    }

    public void addNewNumber(Phone newNumber) {
        phoneList.addPhoneNumber(newNumber);
    }

    public void addNewNumber(String areaCode, String exchange, String extension) {
        Phone curPhone = new Phone(areaCode, exchange, extension);

        phoneList.addPhoneNumber(curPhone);
    }

    public void remANumber(int idx) {
        phoneList.remPhoneNumber(idx);
    }

    public void emptyPhoneList() {
        phoneList.clearPhoneArray();
    }

}
