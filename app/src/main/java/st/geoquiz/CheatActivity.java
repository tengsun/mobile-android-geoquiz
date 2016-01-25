package st.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "st.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "st.geoquiz.answer_shown";
    private boolean answerIsTrue;

    private TextView answerTextView;
    private Button showAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // get answer from quiz activity
        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        // set answer not shown at init
        setAnswerShownResult(false);

        answerTextView = (TextView) findViewById(R.id.text_answer);
        showAnswerButton = (Button) findViewById(R.id.button_show_answer);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerIsTrue) {
                    answerTextView.setText(R.string.quiz_true);
                } else {
                    answerTextView.setText(R.string.quiz_false);
                }
                setAnswerShownResult(true);
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

}
