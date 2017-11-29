package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    /** handles playback of all audio files*/
    private MediaPlayer mMediaPlayer;

    /** handles audio focus when playing a sound file*/
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        // AUDIO_FOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a short
                        // amount of time. AUDIO_FOCUS_LOSS_TRANSIENT_CAN_DUCK case means that our app is
                        // allowed to continue playing sound but at a lower volume. we'll treat both cases
                        // the same way because our app is playing short sound files

                        // pause playback and reset player to the start of the file. that way we can
                        // play the word from the beginning when we resume playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        // AUDIOFOCUS_GAIN case means we have regained focus and can resume playback
                        mMediaPlayer.start();
                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        // AUDIOFOCUS_LOSS case means we've lost audio focus and stop playback and clean resources
                        mMediaPlayer.release();
                    }
                }
            };

    /**
          * This listener gets triggered when the {@link MediaPlayer} has completed
          * playing the audio file.
          */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // now that the sound file has finished playing, release the media player resources
            releaseMediaPlayer();
        }
    };

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            // Abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // create and setup the (@link AudioManager) to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

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

                // Release media player if it currently exists because we are about to play a different audio file
                releaseMediaPlayer();

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // We have audio focus now
                    // create and setup the (@link MediaPlayer) for the audio resource associated with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getAudioResourceId());

                    // start the audio file
                    mMediaPlayer.start(); //no need to call prepare

                    // setup a listener on the media player , so that we can stop and release the media player
                    // once the sound has finished playing
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
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

    @Override
    protected void onStop() {
        super.onStop();
        // when the activity is stopped, release the media player resources because we won't be playing any  more sounds
        releaseMediaPlayer();
    }
}

