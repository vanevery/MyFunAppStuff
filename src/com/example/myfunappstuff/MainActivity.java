package com.example.myfunappstuff;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

	WebView webview;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webview = (WebView) this.findViewById(R.id.webView1);
        //webview.loadUrl("http://itp.nyu.edu/itp/");
        webview.loadUrl("file:///android_asset/index.html");
        
        // Enable JavaScript overall
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);   
        
        webview.addJavascriptInterface(new JSInterface(), "jsobjectname");        
    }
    
    public class JSInterface {

		//window.jsinterface.specialClick()
        /**
         * This is not called on the UI thread. Post a runnable to invoke
         * loadUrl on the UI thread.
         */
    	@JavascriptInterface
        public void specialClick() {
            handler.post(new Runnable() {
                public void run() {
                    //webview.loadUrl("javascript:alert('Special Click Was Called');");
                    
                    // Could do other things here, like load a different activity
                    //Intent nintent = new Intent(WebviewExample.this,WebviewOtherActivity.class);
                    //startActivityForResult(nintent,0); // if we did startActivityForResult we could get the data back??
                	Intent cameraintent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                	startActivity(cameraintent);
                }
            });
        }		
	}    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
