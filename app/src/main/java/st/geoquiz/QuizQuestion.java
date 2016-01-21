package st.geoquiz;

/**
 * Created by tengsun on 1/20/16.
 */
public class QuizQuestion {

    private int question;
    private boolean isTrue;

    public QuizQuestion(int question, boolean isTrue) {
        this.question = question;
        this.isTrue = isTrue;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }

}
