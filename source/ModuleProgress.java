package source;

public class ModuleProgress {
    private float quizGrade;
    private boolean hasPassed;

    public ModuleProgress(float quizGrade, boolean hasPassed){
        this.quizGrade = quizGrade;
        this.hasPassed = hasPassed;
    }

    public float getQuizGrade(){
        return quizGrade;
    }

    public boolean getHasPassed(){
        return hasPassed;
    }

    public void setQuizGrade(float quizGrade){
        this.quizGrade = quizGrade;
    }

    public void setHasPassed(boolean hasPassed){
        this.hasPassed = hasPassed;
    }
}
