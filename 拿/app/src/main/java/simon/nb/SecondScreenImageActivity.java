package simon.nb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.hardware.SensorListener;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.widget.TextView;
import android.os.Vibrator;
import android.view.WindowManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.hardware.Camera.Parameters;


import java.nio.ByteBuffer;
import AE.sir.cytus.xfhelp;
import android.view.View.OnLongClickListener;
import cn.leo.messenger.MagicMessenger;
import android.graphics.Color;
import cn.leo.messenger.MessageCallback;
import android.widget.TextView;
import android.hardware.SensorListener;
import android.widget.Toast;

public class SecondScreenImageActivity extends Activity implements SensorListener
{
	@Override
	public void onSensorChanged(int sensor, float[] values) {
// TODO Auto-generated method stub
		if(sensor == SensorManager.SENSOR_ACCELEROMETER){
			long curTime = System.currentTimeMillis();
//每100毫秒检测一次
			if((curTime-lastUpdate)>100){
				long diffTime = (curTime-lastUpdate);
				lastUpdate = curTime;

				x = values[SensorManager.DATA_X]; 

				y = values[SensorManager.DATA_Y]; 

				z = values[SensorManager.DATA_Z]; 
				float speed = Math.abs(x+y+z-last_x-last_y-last_z)/diffTime*10000;
				if(speed>SHAKE_THRESHOLD){
//这里写上自己的功能代码
					//aa.setText("x="+(int)x+","+"y="+(int)y+","+"z="+(int)z); 
					Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
					vibrator.vibrate(10);
				isjl=false;
				Toast.makeText(SecondScreenImageActivity.this,"guanb",10).show();
					Bundle a=new Bundle();
				a.putInt("e",55);
					MagicMessenger.post("ee",a);
				}
				last_x = x;
				last_y = y;
				last_z = z;
			}
		} 
	}


	@Override
	public void onAccuracyChanged(int p1, int p2)
	{
		// TODO: Implement this method
	}

//	private SensorManager sensorMgr; 

// Sensor sensor = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); 

	private float x, y, z,last_x,last_y,last_z; 
	private long lastUpdate;
	CameraManager manager;
	Camera camera;
	Parameters parameters;
    private static final int SHAKE_THRESHOLD = 2500;
//这个控制精度，越小表示反应越灵敏
	
    private ImageView imageView;
    private MediaProjection mediaProjection;
    private VirtualDisplay virtualDisplay;
    private MediaProjectionManager projectionManager;
    final static int REQUESTRESULT = 0x100;
    private int mWidth;
    private int mHeight;
    private int mScreenDensity;
    private ImageReader mImageReader;
    xfhelp xff;
	boolean isLightColor(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        if (darkness < 0.5) {
            return true; // It's a light color
        } else {
            return false; // It's a dark color
        }
    }
	Button tvv;
	@Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		manager = (CameraManager)this.getSystemService("camera");
		
		SensorManager sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE); 
		sensorMgr.registerListener(this, SensorManager.SENSOR_ACCELEROMETER,
								   SensorManager.SENSOR_DELAY_GAME);
		
        initView();
		initxf();
        initData();
		//EventBus.getDefault().register(this);
		MagicMessenger.init(getApplication());
		
		MagicMessenger.subscribe("o", new MessageCallback(){

				@Override
				public void onMsgCallBack(Bundle p1)
				{
					tvv.setText(p1.getString("oo"));
				}
			});
		
    }
	boolean isjl=false;
    public void initxf(){
		xff=new xfhelp();
		xff.incon(this)
			.in(R.layout.fl)
			.show()
			.getlayout ( ).findViewById ( R.id.flButton1 ).setOnClickListener ( new View.OnClickListener ( ){

				@Override
				public void onClick ( View p1 )
				{
					if(!isjl){//开始
					doo(null);
						isfinish=true;
						isjl=true;

					}else{//结束
						isfinish=false;
						isjl=false;
						

					}
				}
			} );
			tvv=xff.getlayout().findViewById(R.id.flButton1);
		xff.getlayout().findViewById(R.id.flButton1).setOnLongClickListener(new View.OnLongClickListener(){

				@Override
				public boolean onLongClick(View p1)
				{
					xff.hide();
					return true;
				}
			});
	}
	
    private void initView() {
     
        
        imageView=findViewById(R.id.mainImageView1);
    }
	boolean isfinish=true;
   public void doo(View v){
	   new Thread(new Runnable(){

			   @Override
			   public void run()
			   {
				   while(isfinish){
					  /* try
					   {
						   Thread.sleep(15);
					   }
					   catch (InterruptedException e)
					   {}*/
					   doimage();
					   }
			   }
		   }).start();
	  //
   }
    public void initData(){
        projectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth();
        mHeight = display.getHeight();
        DisplayMetrics outMetric = new DisplayMetrics();
        display.getMetrics(outMetric);
        mScreenDensity = (int) outMetric.density;
        Intent intent = new Intent(projectionManager.createScreenCaptureIntent());
        startActivityForResult(intent,REQUESTRESULT);
       
		
						
    }
	public void doimage(){
		Image image = mImageReader.acquireLatestImage();
		if(image!=null){
		int width = image.getWidth();
		int height = image.getHeight();
		final Image.Plane[] planes = image.getPlanes();
		final ByteBuffer buffer = planes[0].getBuffer();
		int pixelStride = planes[0].getPixelStride();
		int rowStride = planes[0].getRowStride();
		int rowPadding = rowStride - pixelStride * width;
		Bitmap bitmap = Bitmap.createBitmap(width+rowPadding/pixelStride, height,
											Bitmap.Config.ARGB_8888);
		bitmap.copyPixelsFromBuffer(buffer);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0,width, height);
		image.close();
		if(bitmap!=null)
		{
			//Intent intent = new Intent("ok");
			//sendBroadcast(intent);
			Bundle a=new Bundle();
			boolean aa=isLightColor( bitmap.getPixel(199,1285));//最左
			boolean bb=isLightColor( bitmap.getPixel(407,1285));//左第二
			boolean cc=isLightColor( bitmap.getPixel(677,1285));//再向右
			boolean dd=isLightColor( bitmap.getPixel(901,1285));//最右
			int aad=5;
			if(!aa)
				aad=1;
				if(!bb)
					aad=2;
					if(!cc)
						aad=3;
						if(!dd)
							aad=4;
			a.putInt("e",aad);
			MagicMessenger.post("ee",a);
			//runon(bitmap);
            	}
		//runon(bitmap);
		
		}
	}
	private void runon(final Bitmap bitmap){
		runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
					imageView.setImageBitmap(bitmap);
					
				}
			});
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mImageReader = ImageReader.newInstance(mWidth,mHeight, PixelFormat.RGBA_8888, 2);
            mediaProjection = projectionManager.getMediaProjection(resultCode,data);
            virtualDisplay = mediaProjection.createVirtualDisplay("mediaprojection",mWidth,mHeight,
																  mScreenDensity, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,mImageReader.getSurface(),null,null);

        }
    }
}


