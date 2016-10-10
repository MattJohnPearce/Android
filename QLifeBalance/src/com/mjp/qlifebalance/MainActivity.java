/* Student Name: Matthew Pearce
 * Student ID: 131600732
 * Date: 21/09/2016
 * 
 * Mobile Apps 2: Android App AT 1
 * 
 * App that has a seekbar, image and text view. As the seekbar is moved along 
 * the slider a different image scale and text will appear in the appropriate 
 * areas of the screen
 */

package com.mjp.qlifebalance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String[] allQuotes;
	private TextView quote;
	private SeekBar sk;
	private ImageView lifeImages, scaleImages;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarSelect();   
    }
    
    //When the SeekBar is changed by the user the progress interval is sent
    //to a switch statement
    public void seekBarSelect(){ 	  	
    	sk = (SeekBar) findViewById(R.id.seekBar1);
    	sk.setProgress(3);
    	sk.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
    		
        	@Override
        	public void onProgressChanged(SeekBar seekBar, int prog, boolean fromUser){ 
        		displaySelection(seekBar.getProgress());
            }

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {							
			}
        });
    }
    
    //Updates TextView and images using SeekBar progress 
    public void displaySelection(int sBar){	
    	allQuotes = getResources().getStringArray(R.array.quotes);
    	quote  = (TextView) findViewById(R.id.txtQuotes);
    	quote.setText(allQuotes[sBar]);
    	
    	lifeImages = (ImageView) findViewById(R.id.lifeImage);
    	scaleImages = (ImageView) findViewById(R.id.scaleImage);
    	
    	switch(sBar){
    	case 0:  		
    		lifeImages.setImageResource(R.drawable.zero);
    		scaleImages.setImageResource(R.drawable.scales0);
    		break;
    	case 1:   		
    		lifeImages.setImageResource(R.drawable.one);
    		scaleImages.setImageResource(R.drawable.scales1);
    		break;
    	case 2:  		
    		lifeImages.setImageResource(R.drawable.two);
    		scaleImages.setImageResource(R.drawable.scales2);
    		break;
    	case 3:
    		lifeImages.setImageResource(R.drawable.three);
    		scaleImages.setImageResource(R.drawable.scales3);
    		break;
    	case 4:
    		lifeImages.setImageResource(R.drawable.four);
    		scaleImages.setImageResource(R.drawable.scales4);
    		break;
    	case 5:
    		lifeImages.setImageResource(R.drawable.five);
    		scaleImages.setImageResource(R.drawable.scales5);
    		break;
    	case 6:
    		lifeImages.setImageResource(R.drawable.six);
    		scaleImages.setImageResource(R.drawable.scales6);
    		break;
    	default:
    		break;
    	}	
    }   
}