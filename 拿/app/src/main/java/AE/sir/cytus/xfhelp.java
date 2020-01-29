package AE.sir.cytus;
import android.view.WindowManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

public class xfhelp
{
       // INTERFACE;
    private    Context incon;
    private    WindowManager mWindowManager;
    private    ViewGroup toucherLayout;
    private    WindowManager.LayoutParams mParams;
        public xfhelp incon(Context th){
            incon=th;
            return this;
        }
        public ViewGroup getlayout(){
            return toucherLayout;
        }
        public xfhelp show(){
            
                mWindowManager.addView(toucherLayout, mParams);     
               return this;
        }
        public xfhelp show(WindowManager.LayoutParams in){
                mWindowManager.removeView(toucherLayout);
                
                mWindowManager.addView(toucherLayout, in);     
                return this;
        }
        public WindowManager.LayoutParams getWindowParams(){
            return mParams;
        }
        public xfhelp hide(){
               
                mWindowManager.removeView(toucherLayout);
                return this;
        }
        public xfhelp in(int layoutid){
                mWindowManager = (WindowManager) incon.getSystemService(Context.WINDOW_SERVICE);
                mParams = new WindowManager.LayoutParams();
                mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
               mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                mParams.gravity = Gravity.LEFT | Gravity.TOP;
                
                // 实现悬浮窗可以移动的属性
                mParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL 
                |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mParams.format = PixelFormat.TRANSPARENT;
                LayoutInflater inflater = LayoutInflater.from(incon);
                //获取浮动窗口视图所在布局.
                  toucherLayout = (ViewGroup) inflater.inflate(layoutid, null);
                
                return this;
        }
        
}
