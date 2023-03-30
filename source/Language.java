package source;

public enum Language {
    JAVA,
    PYTHON,
    JAVASCRIPT,
    C;
    
    public static Language fromString(String s){
        if (s.toUpperCase().equals("JAVA")){
            return JAVA;
        }else if (s.toUpperCase().equals("PYTHON")){
            return PYTHON;
        }
        else if (s.toUpperCase().equals("JAVASCRIPT")){
            return JAVASCRIPT;
        }
        return C;
    }

    @Override
    public String toString(){
        if (this == JAVA){
            return "Java";
        }else if (this == PYTHON){
            return "Python";
        }
        else if (this == JAVASCRIPT){
            return "JavaScript";
        }
        return "C";
    }
}
