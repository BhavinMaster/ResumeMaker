package rb.resumecareer.resume;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

	final static int SPLASH_TIME_OUT=1300;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		ActionBar bar = getActionBar();
		bar.hide();
		Thread t = new Thread(){
			public void run()
			{
				try
				{
					sleep(2000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent intent = new Intent(SplashScreen.this,MainActivityListView.class);
					startActivity(intent);
					finish();
				}
			}
		};
		t.start();
	}
}
