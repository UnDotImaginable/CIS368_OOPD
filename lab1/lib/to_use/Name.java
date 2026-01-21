public class Name {
    private String first;
    private String middleInitial;
    private String last;

    static boolean contentChecker(String s, String label) {

            if (s == null) {
                if ("middle".equals(label)) {
                    return true;
                }
            }

        if ("first".equals(label) || "last".equals(label)) {
            
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isLetter(c) && c != '-' && c != '\'') {
                    return false;
                }
            }

            return true;
        }

        if ("middle".equals(label)) {
            return Character.isLetter(s.charAt(0));
        }

        return false;
    }

    static capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    Name() {

    }

    Name(String first, String middleInitial, String last) {
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

    public String getFirst() {
        return first;
    }

    public void setFirst(String newFirst) {
        if (!contentChecker(newFirst, "first"))
            throw new IllegalArgumentException("Invalid first name!");

        this.first = capitalize(newFirst.trim());
    }

    public String getLast() {
        return last;
    } 

    public void setLast(String newLast) {
        if (!contentChecker(newLast, "last"))
            throw new IllegalArgumentException("Invalid last name!");

        this.last = capitalize(newLast.trim());
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String newMiddleInitial) {
        if (!contentChecker(newMiddleInitial, "middle")) 
            throw new IllegalArgumentException("Invalid middle initial!");

        if (newMiddleInitial == null) {
            this.middleInitial = null;
        }
        else {
            this.middleInitial = newMiddleInitial.toUpperCase();
        }
    }

    

    
}

