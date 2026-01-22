package to_use;

public class Phone {
    private String areaCode;
    private String exchange;
    private String extension;

    boolean checkAreaCode(String code) {

        if (code.length() != 3)
            return false;
        
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            if (!Character.isDigit(c)) {
                return false;
            }

        }

        return true;
    }

    boolean checkExchange(String exc) {

        if (exc.length() != 3)
            return false;
        
        for (int i = 0; i < exc.length(); i++) {
            char c = exc.charAt(i);

            if (!Character.isDigit(c)) {
                return false;
            }

        }

        return true;
    }

    boolean checkExtension(String ext) {

        if (ext.length() != 4)
            return false;
        
        for (int i = 0; i < ext.length(); i++) {
            char c = ext.charAt(i);

            if (!Character.isDigit(c)) {
                return false;
            }

        }

        return true;
    }



    public Phone() {
        
    }

    public Phone(String areaCode, String exchange, String extension) {
        if (!checkAreaCode(areaCode))
            throw new IllegalArgumentException("Invalid area code!");
        
        if (!checkExchange(exchange))
            throw new IllegalArgumentException("Invalid exchange!");
        
        if (!checkExtension(extension))
            throw new IllegalArgumentException("Invalid extension!");

        this.areaCode = areaCode;
        this.exchange = exchange;
        this.extension = extension;
    }


    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String newAreaCode) {
        if (!checkAreaCode(newAreaCode))
            throw new IllegalArgumentException("Invalid area code!");
    
        this.areaCode = newAreaCode;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String newExchange) {
        if (!checkExchange(newExchange))
            throw new IllegalArgumentException("Invalid exchange!");

        this.areaCode = newExchange;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String newExtension) {
        if (!checkExtension(newExtension))
            throw new IllegalArgumentException("Invalid extension!");        

        this.extension = newExtension;
    }
}
