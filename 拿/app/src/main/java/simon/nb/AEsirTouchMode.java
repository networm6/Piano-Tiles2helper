package simon.nb;

import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;
import AE.sir.cytus.xfhelp;
import android.view.View;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import cn.leo.messenger.MagicMessenger;
import cn.leo.messenger.MessageCallback;
import android.os.Bundle;
import android.widget.Button;

public class AEsirTouchMode extends AccessibilityService
{
	
    @Override
    public void onAccessibilityEvent(AccessibilityEvent p1)
    {
        Toast.makeText(this,"ok",10).show();
    }
    public void dotap(){
        iss=false;
    }
   boolean iss=true;
	xfhelp xf;
Button mff;
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        
		xf=   new xfhelp().incon(this).in(R.layout.fll)
			.show();
      mff=  xf.getlayout().findViewById(R.id.fmm);
	  mff .setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                    dotap();
                    Toast.makeText(AEsirTouchMode.this,"change",10).show();
                }
            });
		MagicMessenger.subscribe("ee", new MessageCallback(){

				@Override
				public void onMsgCallBack(Bundle pp1)
				{
					//mff.setText("e"+System.currentTimeMillis());
					int ty=	pp1.getInt("e");
					switch (ty) {
						case 1:
							Tap(199,1285);
							break;
						case 2:
							Tap(407,1285);
				    		break;
						case 3:
							Tap(677,1285);
					        break;
						case 4:
							Tap(901,1285);
							break;
						case 55:
							iss=false;
							break;
					}
					mff.setText("jies"+ty+"");

				}
			});


    }

    @Override
    public void onInterrupt()
    {
        Toast.makeText(this,"终结者断开",10).show();
    }

    /**
     * 模拟滑动事件
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param startTime 0即可执行
     * @param duration  滑动时长
     * @return 执行是否成功
     */
    public void Swipe(int x1, int y1,int x2 , int y2 ,final int startTime , final int duration) {
		//    模拟滑动事件
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path p = new Path();
        p.moveTo(x1 , y1);
        p.lineTo(x2 , y2);
        builder.addStroke(new GestureDescription.StrokeDescription(p, 0L, 500L));
        GestureDescription gesture = builder.build();
        dispatchGesture(gesture, new GestureResultCallback() {
                @Override
                public void onCompleted(GestureDescription gestureDescription) {
                    super.onCompleted(gestureDescription);
					//完成
                }

                @Override
                public void onCancelled(GestureDescription gestureDescription) {
                    super.onCancelled(gestureDescription);
                    //取消
				}
            }, null);

    }
    /**
     * 模拟点击事件
     *
     * @param x
     * @param y
     */
    public void Tap(int x, int y) {
		if(iss){
		//  模拟点击事件

        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path p = new Path();
        p.moveTo(x , y);
        builder.addStroke(new GestureDescription.StrokeDescription(p, 0L, 500L));
        GestureDescription gesture = builder.build();
        dispatchGesture(gesture, new GestureResultCallback() {
                @Override
                public void onCompleted(GestureDescription gestureDescription) {
                    super.onCompleted(gestureDescription);

					//完成
				}

                @Override
                public void onCancelled(GestureDescription gestureDescription) {
                    super.onCancelled(gestureDescription);

					//取消
				}
            }, null);
			}
                    
    }

}

