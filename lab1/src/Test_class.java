import to_use.*;

public class Test_class {
    public static void main(String[] args) throws Exception {
        Name veerg = new Name("Veer", "S", "Gaudani");
        Address veerg_Address = new Address(12345, "Merry Drive", "", "Garyville", "OH", "44881");
        PhoneList veerg_phonelist = new PhoneList();

        PersonInfo vg_p1 = new PersonInfo(veerg, veerg_Address, veerg_phonelist);
        vg_p1.addNewNumber("216", "334", "3813");

        System.out.println(vg_p1.getName());
        System.out.println(vg_p1.getAddress());
        vg_p1.showPhoneList();
        
    }
}
