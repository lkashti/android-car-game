package com.example.cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static final int NUM_ROWS = 5;
    static final int NUM_COLS = 3;
    static final int BLOCK_MOVEMENT_RATE_MILLIS = 400;
    static final int LEFT_LANE = 15;
    static final int CENTER_LANE = 16;
    static final int RIGHT_LANE = 17;

    //hearts
    protected ImageView left_heart;
    protected ImageView center_heart;
    protected ImageView right_heart;
    //score
    protected TextView scoreView;
    //blocks
    protected FrameLayout first_left;
    protected FrameLayout first_center;
    protected FrameLayout first_right;
    protected FrameLayout second_left;
    protected FrameLayout second_center;
    protected FrameLayout second_right;
    protected FrameLayout third_left;
    protected FrameLayout third_center;
    protected FrameLayout third_right;
    protected FrameLayout fourth_left;
    protected FrameLayout fourth_center;
    protected FrameLayout fourth_right;
    protected FrameLayout fifth_left;
    protected FrameLayout fifth_center;
    protected FrameLayout fifth_right;
    //cars
    protected FrameLayout left_car;
    protected FrameLayout center_car;
    protected FrameLayout right_car;
    //controls
    protected ImageButton left_button;
    protected ImageButton right_button;
    protected ImageButton start_button;

    //general
    protected int score, livesLeft;
    protected int currentCarPos = CENTER_LANE;
    protected ArrayList<FrameLayout> blocks;
    protected Timer timer;
    protected int firstBlockPos, secondBlockPos;

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
        start_button.setVisibility(View.INVISIBLE);

        livesLeft = 0;
        left_heart.setVisibility(View.VISIBLE);
        center_heart.setVisibility(View.VISIBLE);
        right_heart.setVisibility(View.VISIBLE);

        score = 0;
        scoreView.setText(String.valueOf(score));
        scoreView.setVisibility(View.VISIBLE);

        timer = new Timer();

        moveBlocks();
    }

    private void moveBlocks() {
        moveBlock(firstBlockPos);
        moveBlock(secondBlockPos);
    }

    private void moveBlock(int blockPos) {
        TimerTask moveBlockTimer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("INFO","TASK");
                        updateBlockUI(blockPos);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(moveBlockTimer,0,BLOCK_MOVEMENT_RATE_MILLIS);
    }

    private void updateBlockUI(int blockPos) {
        blocks.get(blockPos).setVisibility(View.INVISIBLE);
        blockPos += NUM_COLS;
        blocks.get(blockPos).setVisibility(View.VISIBLE);
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


    private void initGameViews() {
        initHearts();
        initScore();
        initBlocks();
        initCars();
        initControls();
    }

    private void prepareGameLogic() {
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
        left_car.setVisibility(View.INVISIBLE);
        right_car.setVisibility(View.INVISIBLE);
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
        first_center = (FrameLayout) findViewById(R.id.row_1_col_2_block);
        first_right = (FrameLayout) findViewById(R.id.row_1_col_3_block);

        second_left = (FrameLayout) findViewById(R.id.row_2_col_1_block);
        second_center = (FrameLayout) findViewById(R.id.row_2_col_2_block);
        second_right = (FrameLayout) findViewById(R.id.row_2_col_3_block);

        third_left = (FrameLayout) findViewById(R.id.row_3_col_1_block);
        third_center = (FrameLayout) findViewById(R.id.row_3_col_2_block);
        third_right = (FrameLayout) findViewById(R.id.row_3_col_3_block);

        fourth_left = (FrameLayout) findViewById(R.id.row_4_col_1_block);
        fourth_center = (FrameLayout) findViewById(R.id.row_4_col_2_block);
        fourth_right = (FrameLayout) findViewById(R.id.row_4_col_3_block);

        fifth_left = (FrameLayout) findViewById(R.id.row_5_col_1_block);
        fifth_center = (FrameLayout) findViewById(R.id.row_5_col_2_block);
        fifth_right = (FrameLayout) findViewById(R.id.row_5_col_3_block);
    }

    private void initCars() {
        left_car = (FrameLayout) findViewById(R.id.left_car);
        center_car = (FrameLayout) findViewById(R.id.center_car);
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
        blocks.add(first_center);
        blocks.add(first_right);
        blocks.add(second_left);
        blocks.add(second_center);
        blocks.add(second_right);
        blocks.add(third_left);
        blocks.add(third_center);
        blocks.add(third_right);
        blocks.add(fourth_left);
        blocks.add(fourth_center);
        blocks.add(fourth_right);
        blocks.add(fifth_left);
        blocks.add(fifth_center);
        blocks.add(fifth_right);
    }
}