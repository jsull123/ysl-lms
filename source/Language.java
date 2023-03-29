package source;

public enum Language {
    JAVA,
    PYTHON,
    C;
    
    public static Language fromString(String s){
        if (s.toUpperCase().equals("JAVA")){
            return JAVA;
        }else if (s.equals("PYTHON")){
            return PYTHON;
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
        return "C";
    }
}
