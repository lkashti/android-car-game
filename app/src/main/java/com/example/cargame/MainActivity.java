package com.example.cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static final int NUM_ROWS = 7;
    static final int NUM_COLS = 5;
    static final int BLOCK_MOVEMENT_RATE_MILLIS = 400;
    static final int LEFT_LANE = 35;
    static final int LEFT_MID_LANE = 36;
    static final int CENTER_LANE = 37;
    static final int RIGHT_MID_LANE = 38;
    static final int RIGHT_LANE = 39;

    //hearts
    protected ImageView left_heart;
    protected ImageView center_heart;
    protected ImageView right_heart;
    //score
    protected TextView scoreView;
    //blocks
    protected FrameLayout first_left;
    protected FrameLayout first_mid_left;
    protected FrameLayout first_center;
    protected FrameLayout first_mid_right;
    protected FrameLayout first_right;
    protected FrameLayout second_left;
    protected FrameLayout second_mid_left;
    protected FrameLayout second_center;
    protected FrameLayout second_mid_right;
    protected FrameLayout second_right;
    protected FrameLayout third_left;
    protected FrameLayout third_mid_left;
    protected FrameLayout third_center;
    protected FrameLayout third_mid_right;
    protected FrameLayout third_right;
    protected FrameLayout fourth_left;
    protected FrameLayout fourth_mid_left;
    protected FrameLayout fourth_center;
    protected FrameLayout fourth_mid_right;
    protected FrameLayout fourth_right;
    protected FrameLayout fifth_left;
    protected FrameLayout fifth_mid_left;
    protected FrameLayout fifth_center;
    protected FrameLayout fifth_mid_right;
    protected FrameLayout fifth_right;
    protected FrameLayout sixth_left;
    protected FrameLayout sixth_mid_left;
    protected FrameLayout sixth_center;
    protected FrameLayout sixth_mid_right;
    protected FrameLayout sixth_right;
    protected FrameLayout seventh_left;
    protected FrameLayout seventh_mid_left;
    protected FrameLayout seventh_center;
    protected FrameLayout seventh_mid_right;
    protected FrameLayout seventh_right;
    //cars
    protected FrameLayout left_car;
    protected FrameLayout left_mid_car;
    protected FrameLayout center_car;
    protected FrameLayout right_mid_car;
    protected FrameLayout right_car;
    //controls
    protected ImageButton left_button;
    protected ImageButton right_button;
    protected ImageButton start_button;

    //general
    protected int score, livesLeft;
    protected int currentCarPos = CENTER_LANE;
    protected ArrayList<FrameLayout> blocks;
    protected ArrayList<ImageView> hearts;
    protected Timer b1Timer, b2Timer, b3Timer, b4Timer;
    protected int firstBlockPos, secondBlockPos;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //game setup
        initGameViews();
        prepareGameLogic();

        start_button.setOnClickListener(v -> startGame());
        left_button.setOnClickListener(v -> moveLeft());
        right_button.setOnClickListener(v -> moveRight());
    }

    private void startGame() {
        if (b1Timer != null && b2Timer != null) {
            b1Timer.cancel();
            b2Timer.cancel();
        }
        start_button.setVisibility(View.INVISIBLE);

        livesLeft = 3;
        left_heart.setVisibility(View.VISIBLE);
        center_heart.setVisibility(View.VISIBLE);
        right_heart.setVisibility(View.VISIBLE);

        score = 0;
        scoreView.setText(String.valueOf(score));
        scoreView.setVisibility(View.VISIBLE);

        moveBlocks();
    }

    private void moveBlocks() {
        moveFirstBlock();
        moveSecondBlock();
    }

    private void moveFirstBlock() {
        b1Timer = new Timer();
        b1Timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateFirstBlockPosition();
                    }
                });
            }
        }, 0, BLOCK_MOVEMENT_RATE_MILLIS);
    }

    private void moveSecondBlock() {
        b2Timer = new Timer();
        b2Timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateSecondBlockPosition();
                    }
                });
            }
        }, 0, BLOCK_MOVEMENT_RATE_MILLIS);
    }

    private void updateFirstBlockPosition() {
        int nextBlockPosition = firstBlockPos + NUM_COLS;
        int boardLimit = NUM_COLS * NUM_ROWS - 1;
        if (nextBlockPosition == currentCarPos) { //collision
            Toast.makeText(this, "Crash!",
                    Toast.LENGTH_SHORT).show();
            vibrate();
            livesLeft--;
            hearts.get(livesLeft).setVisibility(View.INVISIBLE);
            if (livesLeft == 0) {
                startGame();
            }
            Log.i("INFO", "livesLeft = " + livesLeft);
        }
        blocks.get(firstBlockPos).setVisibility(View.INVISIBLE);
        if (nextBlockPosition > boardLimit) {
            scoreView.setText(String.valueOf(++score));
            firstBlockPos = new Random().nextInt(NUM_COLS * 2);
        } else {
            firstBlockPos += NUM_COLS;
        }
        blocks.get(firstBlockPos).setVisibility(View.VISIBLE);
    }

    private void updateSecondBlockPosition() {
        int nextBlockPosition = secondBlockPos + NUM_COLS;
        int boardLimit = NUM_COLS * NUM_ROWS - 1;
        if (nextBlockPosition == currentCarPos) { //collision
            Toast.makeText(this, "Crash!",
                    Toast.LENGTH_SHORT).show();
            vibrate();
            livesLeft--;
            hearts.get(livesLeft).setVisibility(View.INVISIBLE);
            if (livesLeft == 0) {
                startGame();
            }
            Log.i("INFO", "livesLeft = " + livesLeft);
        }
        blocks.get(secondBlockPos).setVisibility(View.INVISIBLE);
        if (nextBlockPosition > boardLimit) {
            scoreView.setText(String.valueOf(++score));
            secondBlockPos = new Random().nextInt(NUM_COLS * 2);
        } else {
            secondBlockPos += NUM_COLS;
        }
        blocks.get(secondBlockPos).setVisibility(View.VISIBLE);
    }

    private void moveRight() {
        if (currentCarPos == LEFT_LANE) {
            left_car.setVisibility(View.INVISIBLE);
            center_car.setVisibility(View.VISIBLE);
            currentCarPos = CENTER_LANE;
        } else if (currentCarPos == CENTER_LANE) {
            center_car.setVisibility(View.INVISIBLE);
            right_car.setVisibility(View.VISIBLE);
            currentCarPos = RIGHT_LANE;
        }
    }

    private void moveLeft() {
        if (currentCarPos == CENTER_LANE) {
            center_car.setVisibility(View.INVISIBLE);
            left_car.setVisibility(View.VISIBLE);
            currentCarPos = LEFT_LANE;
        } else if (currentCarPos == RIGHT_LANE) {
            right_car.setVisibility(View.INVISIBLE);
            center_car.setVisibility(View.VISIBLE);
            currentCarPos = CENTER_LANE;
        }
    }


    private void prepareGameLogic() {
        populateHeartsArray();
        populateBlocksArray();
        for (FrameLayout block : blocks) {
            block.setVisibility(View.INVISIBLE);
        }

        Random r = new Random();
        firstBlockPos = r.nextInt(NUM_COLS);
        while (firstBlockPos == secondBlockPos) secondBlockPos = r.nextInt(NUM_COLS * 2);
        blocks.get(firstBlockPos).setVisibility(View.VISIBLE);
        blocks.get(secondBlockPos).setVisibility(View.VISIBLE);

        left_heart.setVisibility(View.INVISIBLE);
        center_heart.setVisibility(View.INVISIBLE);
        right_heart.setVisibility(View.INVISIBLE);

        scoreView.setVisibility(View.INVISIBLE);

        center_car.setVisibility(View.VISIBLE);
        left_mid_car.setVisibility(View.INVISIBLE);
        left_car.setVisibility(View.INVISIBLE);
        right_car.setVisibility(View.INVISIBLE);
        right_mid_car.setVisibility(View.INVISIBLE);
    }

    public void vibrate() {
        v = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(200);
        }
    }


    private void initGameViews() {
        initHearts();
        initScore();
        initBlocks();
        initCars();
        initControls();
    }

    private void initHearts() {
        left_heart = (ImageView) findViewById(R.id.left_heart);
        center_heart = (ImageView) findViewById(R.id.center_heart);
        right_heart = (ImageView) findViewById(R.id.right_heart);
    }

    private void initScore() {
        scoreView = (TextView) findViewById(R.id.score);
    }

    private void initBlocks() {
        //init views
        first_left = (FrameLayout) findViewById(R.id.row_1_col_1_block);
        first_mid_left=(FrameLayout) findViewById(R.id.row_1_col_2_block);
        first_center = (FrameLayout) findViewById(R.id.row_1_col_3_block);
        first_mid_right = (FrameLayout) findViewById(R.id.row_1_col_4_block);
        first_right = (FrameLayout) findViewById(R.id.row_1_col_5_block);

        second_left = (FrameLayout) findViewById(R.id.row_2_col_1_block);
        second_mid_left=(FrameLayout) findViewById(R.id.row_2_col_2_block);
        second_center = (FrameLayout) findViewById(R.id.row_2_col_3_block);
        second_mid_right = (FrameLayout) findViewById(R.id.row_2_col_4_block);
        second_right = (FrameLayout) findViewById(R.id.row_2_col_5_block);

        third_left = (FrameLayout) findViewById(R.id.row_3_col_1_block);
        third_mid_left=(FrameLayout) findViewById(R.id.row_3_col_2_block);
        third_center = (FrameLayout) findViewById(R.id.row_3_col_3_block);
        third_mid_right = (FrameLayout) findViewById(R.id.row_3_col_4_block);
        third_right = (FrameLayout) findViewById(R.id.row_3_col_5_block);

        fourth_left = (FrameLayout) findViewById(R.id.row_4_col_1_block);
        fourth_mid_left=(FrameLayout) findViewById(R.id.row_4_col_2_block);
        fourth_center = (FrameLayout) findViewById(R.id.row_4_col_3_block);
        fourth_mid_right = (FrameLayout) findViewById(R.id.row_4_col_4_block);
        fourth_right = (FrameLayout) findViewById(R.id.row_4_col_5_block);

        fifth_left = (FrameLayout) findViewById(R.id.row_5_col_1_block);
        fifth_mid_left=(FrameLayout) findViewById(R.id.row_5_col_2_block);
        fifth_center = (FrameLayout) findViewById(R.id.row_5_col_3_block);
        fifth_mid_right = (FrameLayout) findViewById(R.id.row_5_col_4_block);
        fifth_right = (FrameLayout) findViewById(R.id.row_5_col_5_block);

        sixth_left = (FrameLayout) findViewById(R.id.row_6_col_1_block);
        sixth_mid_left=(FrameLayout) findViewById(R.id.row_6_col_2_block);
        sixth_center = (FrameLayout) findViewById(R.id.row_6_col_3_block);
        sixth_mid_right = (FrameLayout) findViewById(R.id.row_6_col_4_block);
        sixth_right = (FrameLayout) findViewById(R.id.row_6_col_5_block);

        seventh_left = (FrameLayout) findViewById(R.id.row_7_col_1_block);
        seventh_mid_left=(FrameLayout) findViewById(R.id.row_7_col_2_block);
        seventh_center = (FrameLayout) findViewById(R.id.row_7_col_3_block);
        seventh_mid_right = (FrameLayout) findViewById(R.id.row_7_col_4_block);
        seventh_right = (FrameLayout) findViewById(R.id.row_7_col_5_block);
    }

    private void initCars() {
        left_car = (FrameLayout) findViewById(R.id.left_car);
        left_mid_car = (FrameLayout) findViewById(R.id.left_mid_car);
        center_car = (FrameLayout) findViewById(R.id.center_car);
        right_mid_car = (FrameLayout) findViewById(R.id.right_mid_car);
        right_car = (FrameLayout) findViewById(R.id.right_car);
    }

    private void initControls() {
        left_button = (ImageButton) findViewById(R.id.left_button);
        start_button = (ImageButton) findViewById(R.id.start_button);
        right_button = (ImageButton) findViewById(R.id.right_button);
    }

    private void populateBlocksArray() {
        //populate arraylist
        blocks = new ArrayList<>();
        blocks.add(first_left);
        blocks.add(first_mid_left);
        blocks.add(first_center);
        blocks.add(first_mid_right);
        blocks.add(first_right);

        blocks.add(second_left);
        blocks.add(second_mid_left);
        blocks.add(second_center);
        blocks.add(second_mid_right);
        blocks.add(second_right);

        blocks.add(third_left);
        blocks.add(third_mid_left);
        blocks.add(third_center);
        blocks.add(third_mid_right);
        blocks.add(third_right);

        blocks.add(fourth_left);
        blocks.add(fourth_mid_left);
        blocks.add(fourth_center);
        blocks.add(fourth_mid_right);
        blocks.add(fourth_right);

        blocks.add(fifth_left);
        blocks.add(fifth_mid_left);
        blocks.add(fifth_center);
        blocks.add(fifth_mid_right);
        blocks.add(fifth_right);

        blocks.add(sixth_left);
        blocks.add(sixth_mid_left);
        blocks.add(sixth_center);
        blocks.add(sixth_mid_right);
        blocks.add(sixth_right);

        blocks.add(seventh_left);
        blocks.add(seventh_mid_left);
        blocks.add(seventh_center);
        blocks.add(seventh_mid_right);
        blocks.add(seventh_right);
    }

    private void populateHeartsArray() {
        hearts = new ArrayList<>();
        //order of addition reversed to string in order to pop last heart on screen;
        hearts.add(right_heart);
        hearts.add(center_heart);
        hearts.add(left_heart);
    }
}