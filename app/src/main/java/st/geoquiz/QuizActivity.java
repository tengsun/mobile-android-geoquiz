package st.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionText;
    private int currentIndex = 0;

    private QuizQuestion[] questions = new QuizQuestion[] {
            new QuizQuestion(R.string.quiz_bj, false),
            new QuizQuestion(R.string.quiz_sh, true),
            new QuizQuestion(R.string.quiz_nj, false),
            new QuizQuestion(R.string.quiz_sz, false),
            new QuizQuestion(R.string.quiz_tp, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set init content view with layout
        setContentView(R.layout.activity_quiz);

        // set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set child views
        setChildViews();

        // set control actions
        setControlActions();
    }

    private void setChildViews() {
        questionText = (TextView) findViewById(R.id.text_question);
        updateQuestion();

        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_false);
        nextButton = (Button) findViewById(R.id.button_next);
    }

    private void setControlActions() {
        // set event handlers
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questions.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        int question = questions[currentIndex].getQuestion();
        questionText.setText(question);
    }

    private void checkAnswer(boolean answer) {
        boolean isTrue = questions[currentIndex].isTrue();

        int messageId;
        if (answer == isTrue) {
            messageId = R.string.quiz_correct;
        } else {
            messageId = R.string.quiz_incorrect;
        }
        Toast.makeText(QuizActivity.this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
