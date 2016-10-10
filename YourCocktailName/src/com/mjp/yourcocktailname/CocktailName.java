/*
 * Student Name: Matthew Pearce
 * Student ID: 131600732
 * Date: 23/08/2016
 * Assessment: AT3 Your Cocktail Name
 * 
 * An app that allows users to select a letter from the alphabet and a month of the year.
 * For each selection a separate TextView will change and display a different different 
 * name. Once the name is displayed the text colour and background colour will change.
 * For each month a different image will display in the ImageView at the bottom of the 
 * screen. If the user taps/clicks the ImageView a new screen will open to display the 
 * drink details
 */
package com.mjp.yourcocktailname;

import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class CocktailName extends Activity {
String[] alphabet, month, letterNme, monthNme;
String dName1 = "Randy";
String dName2 = "Frolic";
ImageView drinkBtn ;
int pickMon, pickLet;

	//onCreate method opens up Listener events for both NumberPickers and Button events
	//when image is clicked a new intent is displayed and variables from the NumberPicker
	//values are passed on
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_name);
        setLetterName();
        setMonthName(); 
        
        drinkBtn = (ImageView) findViewById(R.id.cocktailImageBtn);
        drinkBtn.setOnClickListener((new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						CocktailName.this,
						RecipePage.class
						);
				intent.putExtra("pickLet", pickLet);
				intent.putExtra("pickMon", pickMon);
				startActivity(intent);			
			}
		}));     
    }
    //Sets the drink name in the text field
    public void SetName(){
    	TextView letName = (TextView) findViewById(R.id.letter);
        letName.setText( dName1 + " " + dName2);
    }
    //The spinner output show Strings pulled out of an array that correspond with their values.
    //The values are used for the image array and month array. Strings pulled from the array
    //are sent to the global variable for the drink name
    public void setMonthName(){   	
    	NumberPicker monthName = (NumberPicker) findViewById(R.id.picker_values_name_month);
        
        month = getResources().getStringArray(R.array.months);
        
        monthName.setDisplayedValues(month);
        monthName.setMinValue(0);
        monthName.setMaxValue(11);
        
        monthName.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker arg0, int arg1, int arg2) {	
						
				monthNme = getResources().getStringArray(R.array.name_month);
				dName2 = monthNme[arg0.getValue()];	        
		        
		        TypedArray picArray = getResources().obtainTypedArray(R.array.cocktail_imgs);
		        drinkBtn = (ImageView) findViewById(R.id.cocktailImageBtn);
		        drinkBtn.setImageResource(picArray.getResourceId(arg0.getValue(), 0));
		        pickMon = arg0.getValue();
		        picArray.recycle();
		        
		        setTextColour();
		        SetName();
			}
		});
    }
    //The spinner output show Strings pulled out of an array that correspond with their values.
    //The values are used for letter array and are sent to the global variable for the drink name
    public void setLetterName(){
    	NumberPicker letterName = (NumberPicker) findViewById(R.id.picker_values_name_letter);
    	
    	alphabet = getResources().getStringArray(R.array.alphabet);
    	
    	letterName.setDisplayedValues(alphabet);
        letterName.setMinValue(0);
        letterName.setMaxValue(25);
        
        letterName.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
				
		        letterNme = getResources().getStringArray(R.array.name_letter);
		        	        
		        dName1 = letterNme[arg0.getValue()];
		        pickLet = arg0.getValue();
		        setTextColour();
		        SetName();
			}
		});
    }
    //Every time a value changes in one of the spinners a random number is generated and
    //changes the text view background and text colour
    public void setTextColour(){
    	Random rand = new Random(); 
    	int value = rand.nextInt(12);
    	TextView monName = (TextView) findViewById(R.id.letter);
    	switch(value){
    	case 0:		
    		monName.setTextColor(getResources().getColor(R.color.text_color_1));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_1));
    		break;
    	case 1:
    		monName.setTextColor(getResources().getColor(R.color.text_color_2));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_2));
    		break;
    	case 2:
    		monName.setTextColor(getResources().getColor(R.color.text_color_3));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_3));
    		break;
    	case 3:
    		monName.setTextColor(getResources().getColor(R.color.text_color_4));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_4));
    		break;
    	case 4:
    		monName.setTextColor(getResources().getColor(R.color.text_color_5));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_5));
    		break;
    	case 5:
    		monName.setTextColor(getResources().getColor(R.color.text_color_6));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_6));
    		break;
    	case 6:
    		monName.setTextColor(getResources().getColor(R.color.text_color_7));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_7));
    		break;
    	case 7:
    		monName.setTextColor(getResources().getColor(R.color.text_color_8));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_8));
    		break;
    	case 8:
    		monName.setTextColor(getResources().getColor(R.color.text_color_9));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_9));
    		break;
    	case 9:
    		monName.setTextColor(getResources().getColor(R.color.text_color_10));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_10));
    		break;
    	case 10:
    		monName.setTextColor(getResources().getColor(R.color.text_color_11));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_11));
    		break;
    	case 11:
    		monName.setTextColor(getResources().getColor(R.color.text_color_12));
    		monName.setBackgroundColor(getResources().getColor(R.color.background_12));
    		break;
    	default:
    		monName.setTextColor(getResources().getColor(R.color.text_color));
    	} 	
    }
}
