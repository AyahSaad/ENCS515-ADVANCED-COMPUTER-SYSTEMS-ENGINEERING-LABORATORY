package edu.birzeit.a1191334_ayah_saad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int randNum;
    private int prevRandNum;
    private int attempts = 7;
    private EditText inputField;
    private Button Guess_button;
    private TextView Feedback_text;
    private TextView Attempts_text;
    private Button Reset_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);
        Guess_button = findViewById(R.id.Guess_button);
        Attempts_text = findViewById(R.id.Attempts_text);
        Feedback_text = findViewById(R.id.Feedback_text);
        Reset_button = findViewById(R.id.Reset_button);

        Reset_button.setBackgroundColor(getResources().getColor(R.color.my_color));
        Attempts_text.setText(+attempts + " attempts left");

        // Generate a random number at the start of the game.
        generateRandomNumber();

        Guess_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

        Reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    //generateRandomNumber methods that generates a random number between 1 and 100 and returns it.
    private void generateRandomNumber() {
        // Store the current random number as the previous random number.
        prevRandNum = randNum;
        Random random = new Random();
        randNum = random.nextInt(100) + 1;
    }

    //check methods to Guess the Number Game.
    private void check() {
        String inputText = inputField.getText().toString(); // retrieves the text from the Input field, parses it into an integer
        if (inputText.isEmpty()) {
            Feedback_text.setText("Please enter a number");
            Attempts_text.setText(+attempts + " attempts left");
            return;
        }
        int input = Integer.parseInt(inputText);
        //validate the input to ensure it's a number in the range 1-100:
        if (input < 1 || input > 100) {
            Feedback_text.setText("Number should be in range 1 and 100");
            Attempts_text.setText(+attempts + " attempts left");
            inputField.setText("");
            return;
        }
        attempts--;
        // If the input equals the random number, win feedback will appear and number of attempts resets to 7.
        if (input == randNum) {
            reset();
            Feedback_text.setText("Good Job! You've guessed the number");
            return;
        } else if (attempts == 0) {
            reset();
            Feedback_text.setText("You lose! the number was " + prevRandNum);
            Attempts_text.setText(+attempts + " attempts left");
            //  return;
        } else {
            String msg = (input < randNum) ? "Your Guess is low" : "Your Guess is high";
            Feedback_text.setText(msg);
            Attempts_text.setText(+attempts + " attempts left");
        }
        inputField.setText("");
    }

/* reset method to start a new game by resetting the number of attempts
    to 7, choosing a new random number, and clearing the input field.*/
    private void reset() {
        attempts = 7;
        generateRandomNumber();
        Attempts_text.setText(+attempts + " attempts left");
        inputField.setText("");
        Feedback_text.setText("");
    }
}