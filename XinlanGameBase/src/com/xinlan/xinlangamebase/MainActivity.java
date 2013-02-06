package com.xinlan.xinlangamebase;

import com.xinlan.xinlangamebase.base.Background;
import com.xinlan.xinlangamebase.base.MainRender;
import com.xinlan.xinlangamebase.base.Waves;
import com.xinlan.xinlangamebase.utils.XinlanUtils;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PowerManager;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	public PowerManager.WakeLock wakeLock;
	public boolean isRunning = false;
	public static int screenW, screenH;
	
	private Context mContext;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
				"PowerLight");
		wakeLock.acquire();// 请求屏幕常亮

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		initScreenHW();
		isRunning = true;
		setContentView(new MainView(this));
	}
	
	private void initScreenHW(){
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		Rect rectgle = new Rect();
		Window window = getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
		screenW = display.getWidth();
		screenH = Math.abs(rectgle.top - rectgle.bottom);
	}

	private class MainView extends GLSurfaceView implements Runnable {
		private MainRender mRender;
		private Thread mThread;
		
		private Background mBackgroud;
		private Waves mWave;
		
		public MainView(Context context) {
			super(context);
			mRender = new MainRender();
			this.setRenderer(mRender);
			mThread = new Thread(this);
			mThread.start();
		}
		
		private void initGame(){
			Bitmap bgBitmap=XinlanUtils.loadBitmapFromAssets("game_background_layer_3.png", mContext);
			mBackgroud = new Background(bgBitmap,screenW,screenH);
			mRender.addMesh(mBackgroud.getMesh());
			
			mWave = new Waves(XinlanUtils.loadBitmapFromAssets("waves.png", mContext),
					screenW,screenH);
			mRender.addMesh(mWave.getMesh());
		}
		
		private void gameMain(){
			mBackgroud.update();
			mWave.update();
		}

		@Override
		public void run() {// 逻辑线程
			initGame();
			while (isRunning) {
				//System.out.println(screenW+","+screenH);
				long starttime = System.currentTimeMillis();
				gameMain();
				long timeForOneCycle = System.currentTimeMillis() - starttime;
				if (timeForOneCycle < 10) {
					try {
						Thread.sleep(10 - timeForOneCycle);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}//end if
			}
		}
	}// end inner class

	@Override
	protected void onDestroy() {
		isRunning = false;
		wakeLock.release();
		System.gc();
		super.onDestroy();
	}

	@Override
	public void onResume() {
		wakeLock.acquire();
		super.onResume();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onRestart() {
		super.onRestart();
	}

	@Override
	public void onPause() {
		wakeLock.release();
		super.onPause();
	}
}// end class
