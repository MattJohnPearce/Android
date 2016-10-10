package com.tafe.matthewjohnpearce.speech2txt;
/*
/Program allows the user to talk and rediplay their speech as text
as a bonus feature it can search for installed apps and the user
can open any app by saying its name
 */
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {

    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    private ArrayList<Intent> launchApp;
    private ArrayList<String> appName;
    private ArrayList<String> appPackage;

    //On creates searches for installed apps and sets a listner for user voice input
    //using android speech recognizer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findName();

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }

    //Searches for all installed apps on phone and puts them into an array
    private void  findName(){

        PackageManager pm = getPackageManager();
        Intent main = new Intent(Intent.ACTION_MAIN, null);
        main.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> packages = pm.queryIntentActivities(main, 0);

        appName = new ArrayList<String>();
        appPackage = new ArrayList<String>();
        launchApp = new ArrayList<Intent>();

        for(ResolveInfo resolve_info : packages) {
            try {
                String package_name = resolve_info.activityInfo.packageName;
                String app_name = (String)pm.getApplicationLabel(
                        pm.getApplicationInfo(package_name , PackageManager.GET_META_DATA));
                boolean same = false;
                for(int i = 0; i < appName.size() ; i++) {
                if(package_name.equals(appPackage.get(i)))
                    same = true;
                }
                if(!same) {
                appName.add(app_name);
                appPackage.add(package_name);
                launchApp.add(pm.getLaunchIntentForPackage(package_name));
                }
            } catch(Exception e) { }
    }
}
    //Compares users voice to an array of apps installed on the phone
    private void searchApps(String search){
        int listCtr = 0;

        for(String apNm : appName){
            if(apNm.toLowerCase().contains(search.toLowerCase())){
                runNewApp(launchApp.get(listCtr));
                break;
            }
                listCtr++;
        }
    }

    public void runNewApp(Intent intent){
    if (intent != null) {
        startActivity(intent);//null pointer check in case package name was not found
    }
}

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                /*getString(R.string.speech_prompt)*/"What do you want!");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                searchApps(txtSpeechInput.getText().toString().toLowerCase());
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}
