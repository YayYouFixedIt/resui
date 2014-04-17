package util.resui;

import android.app.*;
import java.lang.Process;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.FileOutputStream;
import android.util.DisplayMetrics;
import android.content.Context;
import android.view.WindowManager.LayoutParams;
import android.graphics.PixelFormat;

public class MainActivity extends Activity
{
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{

		
		Button mRes1;
		Button mRes2;
		Button mRes3;
		Button mReboot;
		DisplayMetrics mMetrics;
		int w;
		int h;
		int density;
		TextView mResInfo;
		String[] cmds;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		LayoutParams layOutParams = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
			WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
			PixelFormat.TRANSLUCENT);
			getWindow().setAttributes(layOutParams);
		mMetrics = new DisplayMetrics();
		mResInfo = (TextView)findViewById(R.id.text_resolution);
		w = getWindowManager().getDefaultDisplay().getWidth();
		h = getWindowManager().getDefaultDisplay().getHeight();
		getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		density = mMetrics.densityDpi;
		String info = "Current Resolution: " +w + " " + h + "\n" + "Current Density: "+density;
		mResInfo.setText(info);
		mRes1 = (Button)findViewById(R.id.res1);
		mRes1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				toSdCard(320);
				String[] cmds = {"sh "+ getCacheDir().getAbsolutePath() + "/density_320.sh"};
				String uri = "android.resource://" + getPackageName() + "/"+R.raw.density_320;
				RunAsRoot(cmds);
			}
		});
		mRes2 = (Button)findViewById(R.id.res2);
		mRes2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				toSdCard(240);
				String[] cmds = {"sh "+ getCacheDir().getAbsolutePath() + "/density_240.sh"};
				String uri = "android.resource://" + getPackageName() + "/"+R.raw.density_240;
				RunAsRoot(cmds);
				
			}
		});
		mRes3 = (Button)findViewById(R.id.res3);
		mRes3.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					toSdCard(160);
					String[] cmds = {"sh "+ getCacheDir().getAbsolutePath() + "/density_160.sh"};
					String uri = "android.resource://" + getPackageName() + "/"+R.raw.density_160;
					RunAsRoot(cmds);

				}
			});
		mReboot = (Button)findViewById(R.id.reboot);
		mReboot.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				String [] cmds = {"reboot"};
				RunAsRoot(cmds);
				}
		});
    }
	//copy raw resource to cache dir
	public void toSdCard(int density){
		switch(density){
			case 320:
				InputStream in = getResources().openRawResource(R.raw.density_320);
				String file = getCacheDir().getAbsolutePath() + "/density_320.sh";
				try{
					FileOutputStream out = new FileOutputStream(file);

					byte[] buff = new byte[1024];
					int read = 0;

					try {
						while ((read = in.read(buff)) > 0) {
							out.write(buff, 0, read);
						}
					} finally {
						in.close();

						out.close();
					}
				}catch(IOException E){

				}
				break;
			case 240:
				in = getResources().openRawResource(R.raw.density_240);
				file = getCacheDir().getAbsolutePath() + "/density_240.sh";
				try{
					FileOutputStream out = new FileOutputStream(file);

					byte[] buff = new byte[1024];
					int read = 0;

					try {
						while ((read = in.read(buff)) > 0) {
							out.write(buff, 0, read);
						}
					} finally {
						in.close();

						out.close();
					}
				}catch(IOException E){

				}
				break;
			case 160:
				in = getResources().openRawResource(R.raw.density_160);
				file = getCacheDir().getAbsolutePath() + "/density_160.sh";
				try{
					FileOutputStream out = new FileOutputStream(file);

					byte[] buff = new byte[1024];
					int read = 0;

					try {
						while ((read = in.read(buff)) > 0) {
							out.write(buff, 0, read);
						}
					} finally {
						in.close();

						out.close();
					}
				}catch(IOException E){

				}
				break;
		}
		
	}
	//execute script resources as root
	public void RunAsRoot(String[] cmds)
	{
		try
		{
			Process p = Runtime.getRuntime().exec("su");
			DataOutputStream os = new DataOutputStream(p.getOutputStream());            
			for (String tmpCmd : cmds)
			{
				os.writeBytes(tmpCmd + "\n");
			}           
			os.writeBytes("exit\n");  
			os.flush();
		}
		catch (IOException e)
		{
			Toast.makeText(this,"Resolution change failed. IOException",Toast.LENGTH_LONG);
			e.printStackTrace();
		}
	}
}
