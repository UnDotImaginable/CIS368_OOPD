package to_use;

import java.util.ArrayList;

public class PhoneList implements java.io.Serializable {
    private ArrayList<Phone> phoneArray;

    public PhoneList() {
        phoneArray = new ArrayList<>();
    }

    public void addPhoneNumber(Phone newNumber) {
        phoneArray.add(newNumber);
    }

    public void addPhoneNumber(String areaCode, String exchange, String extension) {
        Phone curPhone = new Phone(areaCode, exchange, extension);

        phoneArray.add(curPhone);
    }

    public void remPhoneNumber(int idx) {
        phoneArray.remove(idx);
    }

    public void showPhoneNumbers() {
        
        for (Phone p : phoneArray) {
            String areaCode = p.getAreaCode();
            String exchange = p.getExchange();
            String extension = p.getExtension();
            
            System.out.println(" --- " + areaCode + "-" + exchange + "-" + extension);
        }
    }

    public String getPhoneNumbers() {
        String out = "";
        for (Phone p : phoneArray) {
            String areaCode = p.getAreaCode();
            String exchange = p.getExchange();
            String extension = p.getExtension();
            
            String entry = " --- " + areaCode + "-" + exchange + "-" + extension;
            out = out + entry;
        }

        return out;
    }

    public void clearPhoneArray() {
        phoneArray.clear();
    }



}
