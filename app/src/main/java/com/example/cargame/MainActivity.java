package com.example.cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    static final int NUM_ROWS = 5;
    static final int NUM_COLS = 3;

    /*
    Definitions
    */

    //hearts
    protected ImageView left_heart;
    protected ImageView center_heart;
    protected ImageView right_heart;

    //score
    protected TextView score;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //game setup
        init();
    }
    private void init() {
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
        score = (TextView) findViewById(R.id.score);
    }

    private void initBlocks() {
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
}