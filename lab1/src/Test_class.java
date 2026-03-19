import to_use.*;

public class Test_class {
    public static void main(String[] args) throws Exception {
		Name stu1 = new Name("Veer", "S", "Gaudani");
		Address stu_Address1 = new Address(12345, "Merry Drive", "", "Garyville", "OH", "44881");
		PhoneList stu_phonelist1 = new PhoneList();

		PersonInfo stu_p1 = new PersonInfo(stu1, stu_Address1, stu_phonelist1);
		stu_p1.addNewNumber("216", "334", "3813");

		Name stu2 = new Name("Carver", "K", "Ossoff");
		Address stu_Address2 = new Address(22222, "Jolly Drive", "", "Garyville", "OH", "44881");
		PhoneList stu_phonelist2 = new PhoneList();

		PersonInfo stu_p2 = new PersonInfo(stu2, stu_Address2, stu_phonelist2);
		stu_p2.addNewNumber("216", "334", "3814");
		stu_p2.addNewNumber("440", "334", "3814");
		stu_p2.addNewNumber("440", "334", "3214");
		stu_p2.addNewNumber("440", "334", "3824");


		Name stu3 = new Name("Abhi", "S", "Siri");
		Address stu_Address3 = new Address(54321, "Whimsy Drive", "", "Garyville", "OH", "44881");
		PhoneList stu_phonelist3 = new PhoneList();

		PersonInfo stu_p3 = new PersonInfo(stu3, stu_Address3, stu_phonelist3);
		stu_p3.addNewNumber("216", "334", "3815");

		StudentRecord stu_r1 = new Graduate("8999999",stu_p1);
		StudentRecord stu_r2 = new Undergraduate("7999999",stu_p2);
		StudentRecord stu_r3 = new Graduate("6999999",stu_p3);

		StudentArray stuArr = new StudentArray(stu_r1, stu_r2, stu_r3);

		

		System.out.println("Lab 2 Start__________________");
		//Course algebra = new Course("linear  ...+    algebra", "MATH", "two88", "8seven");
		Course algebra = new Course("linear algebra", "mth", "288", "87.4");
		//System.out.println(algebra);
		//StudentRecord student = new StudentRecord("123", stu_p1);
		StudentRecord student = new StudentRecord("9999999", stu_p1);
		student.addCourse(algebra);
		//System.out.println(student.toString());
		System.out.println(student);

		System.out.println("END OLD -----------------------------------------");

		//Comment out the line below to disable loading from test program
		GUI.updateListFromArrayList(stuArr.getStudentList());
		GUI.main(args);
		
    }
}
