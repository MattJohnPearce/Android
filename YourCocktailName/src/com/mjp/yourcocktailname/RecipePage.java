/*
 * This class displays a recipe for the selected drink on the previous page using values from
 * the NumberPickers which are passed through the intent.
 */
package com.mjp.yourcocktailname;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RecipePage extends Activity {
	
	RelativeLayout layout; 
	int getPickLet;
    int getPickMon;
    
	//onCreate method that pulls and stores values from the NumberPicker widgets 
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.recipe_page);
	        
	        getPickLet = getIntent().getIntExtra("pickLet", 0);
	        getPickMon = getIntent().getIntExtra("pickMon", 0);
	        showBackground();
	        showRecipe();
	        closeIntent();     
	 }
	 //Loads up the drinks picture using the values from the NumberPicker
	 public void showBackground(){  
	        TypedArray icArray = getResources().obtainTypedArray(R.array.cocktail_imgs);        
	        layout =(RelativeLayout)findViewById(R.id.back);
	        layout.setBackgroundResource(icArray.getResourceId(getPickMon, -1));
	        icArray.recycle();
	 }
	 //Shows the drink name and its recipe using values from the string arrays.
	 public void showRecipe(){
		 String[] ingredOne = getResources().getStringArray(R.array.letter_ingred);
	        String[] ingredTwo = getResources().getStringArray(R.array.months_ingred);
	        String[] monthNme = getResources().getStringArray(R.array.name_month);
	        String[] letterNme = getResources().getStringArray(R.array.name_letter);
	        		
	        String newRecipe = "Add some "+ ingredOne[getPickLet]+" with two parts of "+ ingredTwo[getPickMon]+" " +
	        		"and voilà you have a " +letterNme[getPickLet]+" "+monthNme[getPickMon] ;
	        
	        TextView recipe = (TextView) findViewById(R.id.recipe);
	        recipe.setText(newRecipe);
	 }
	 //Lets the user return to drink selection page
	 public void closeIntent(){
		 Button hmeBtn = (Button) findViewById(R.id.home);
	        hmeBtn.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					finish();				
				}
			});
	 }
}
