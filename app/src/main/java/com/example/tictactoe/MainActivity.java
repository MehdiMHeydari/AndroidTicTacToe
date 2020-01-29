package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    private TicTacToeLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declares logic as a variable we can call on
        logic = new TicTacToeLogic();

        //puts all buttons on the board into an array
        int[] ids = {
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8

        };

        //sets a tag for all of the buttons and attaches a listener to them
        for (int i = 0; i < ids.length; i++) {
            Button but = findViewById(ids[i]);
            but.setTag("" + i);
            but.setOnClickListener(new ButtonHandler());



        }

        //declares the reset button and attaches a listener
        int res = R.id.ResetButton;
        Button reset = findViewById(res);
        reset.setTag(reset);
        reset.setOnClickListener(new ResetButton());


    }

    public void updateGraphics() {
        //puts all buttons on the board into an array
        int[] ids = {
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8

        };

        //for loop that goes through each button
        for (int i = 0; i < ids.length; i++) {
            //gets the button by id
            Button but = findViewById(ids[i]);
            // turns button into object
            Object grabber = but.getTag();
            //turns object into string
            String tagger = grabber.toString();
            //turns string into integer
            int tag = Integer.parseInt(tagger);
            //finds who turn it is
            String who = logic.getPlayer(tag);
            but.setText(who);
        }



        String turn = logic.whoseTurn();

        String winner = logic.checkWinner();

        //access the textview that displays winners and whos turn it is
        TextView whoplaying = (TextView) findViewById(R.id.statusText);



        //displays who won
        if(winner.equals("X")){
            whoplaying.setText("Winner: X");
        }

        if(winner.equals("O")){
            whoplaying.setText("Winner: O");
        }

        if(winner.equals("TIE")){
            whoplaying.setText("It's a TIE");
        }

        //displays the turn
        if (!logic.isGameOver()) {
            whoplaying.setText(turn + "'s Turn");
        }



    }





    public class ButtonHandler implements View.OnClickListener {

        //waits for click
        @Override
        public void onClick(View v) {


            //gets the tag of the button that was click so we can make move
            Button but = (Button) v;
            Object grabber = but.getTag();
            String tagger = grabber.toString();
            int tag = Integer.parseInt(tagger);
            logic.makeMove(tag);
            updateGraphics();
        }



    }

    private class ResetButton implements View.OnClickListener {

        //waits for click
        public void onClick(View v) {

            //runs reset function to reset board
            logic.reset();

            updateGraphics();
        }




    }



}
