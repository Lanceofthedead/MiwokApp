package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
//  array
//        String[] words = new String[10];
//        words[0] = "One";
//        words[1] = "Two";
//        words[2] = "Three";
//        words[3] = "Four";
//        words[4] = "Five";
//        words[5] = "Six";
//        words[6] = "Seven";
//        words[7] = "Eight";
//        words[8] = "Nine";
//        words[9] = "Ten";
//
//        Log.v("NumbersActivity", "Word at index 0: "+words[0]);
//        Log.v("NumbersActivity", "Word at index 1: "+words[1]);

//        //arrayList
//        ArrayList<String> words = new ArrayList<>();
//
//        words.add("one");
//        words.add("two");
//        words.add("three");
//        words.add("four");
//        words.add("five");
//        words.add("six");
//        words.add("seven");
//        words.add("eight");
//        words.add("nine");
//        words.add("ten");

        //arrayList using Word.java class
        final ArrayList<Word> words = new ArrayList<Word>();

//        words.add("one");
        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","mawinka",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na'aacha,",R.drawable.number_ten,R.raw.number_ten));






//        //grid view
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(itemsAdapter);

        //R.layout.simple_list_item_1  --
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);

        //using list_item layout
//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this,R.layout.list_item , words);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView =  (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        // set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(NumbersActivity.this,"List Item Clicked",Toast.LENGTH_SHORT).show();

                // get the (@link Word) object at the given position the user clicked on
                Word word = words.get(position);

                // create and setup the (@link MediaPlayer) for the audio resource associated with the current word
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getAudioResourceId());

                // start the audio file
                mMediaPlayer.start(); //no need to call prepare
            }
        });
//
//        //displays text via TextView without loop
//        LinearLayout rootView = findViewById(R.id.rootView);
//        TextView wordView = new TextView(this);
//        wordView.setText(words.get(0));
//        rootView.addView(wordView);
//
//        TextView wordView2 = new TextView(this);
//        wordView2.setText(words.get(1));
//        rootView.addView(wordView2);
//
//        TextView wordView3 = new TextView(this);
//        wordView3.setText(words.get(2));
//        rootView.addView(wordView3);




//        Log.v("NumbersActivity", "Word at index 0: "+ words.get(0));
//        Log.v("NumbersActivity", "Word at index 1: "+ words.get(1));


//
//        //initialize index for loop
//        int index = 0;
//
//        // while loop
//        while(index<words.size()){
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(index));
//            rootView.addView(wordView);
//
//            //update counter variable
//            index++;
//        }
//
        // find the root view to add child views in it
//        LinearLayout rootView = findViewById(R.id.rootView);
//
//        //for loop
//        for(int index = 0;index<words.size();index++){
//            //create a text view
//            TextView wordView = new TextView((this));
//            //set the text
//            wordView.setText(words.get(index));
//            //add this as a text view as child view inside the root view
//            rootView.addView(wordView);
//        }
    }
}
