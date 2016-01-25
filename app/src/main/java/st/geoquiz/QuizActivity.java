package st.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button trueButton;
    private Button falseButton;
    private Button cheatButton;
    private Button nextButton;
    private TextView questionText;
    private int currentIndex = 0;
    private boolean isCheater;

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
        Log.d(TAG, "onCreate(Bundle) called");

        // restore current index
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void setChildViews() {
        questionText = (TextView) findViewById(R.id.text_question);
        updateQuestion();

        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_false);
        cheatButton = (Button) findViewById(R.id.button_cheat);
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
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = questions[currentIndex].isTrue();
                intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(intent, 0);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questions.length;
                isCheater = false;
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
        if (isCheater) {
            messageId = R.string.quiz_cheat_alert;
        } else {
            if (answer == isTrue) {
                messageId = R.string.quiz_correct;
            } else {
                messageId = R.string.quiz_incorrect;
            }
        }

        Toast.makeText(QuizActivity.this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState(Bundle) called");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        isCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }
}
