package winter.zxb.smilesb101.coderhome.View.Behaviour;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import winter.zxb.smilesb101.coderhome.View.Utils.AnimationUtils;

/**
 * 项目名称：CoderHome
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/6/8 0008 09:03
 * 修改人：Administrator
 * 修改时间：2017/6/8 0008 09:03
 * 修改备注：
 */

public class BackTopButtonBehaviour extends FloatingActionButton.Behavior{

	String TAG = "BackTopBehaviour";
	private boolean isAnimatingOut = false;

	ViewPropertyAnimatorListener viewPropertyAnimatorListener = new ViewPropertyAnimatorListener() {

		@Override
		public void onAnimationStart(View view) {
			isAnimatingOut = true;
		}

		@Override
		public void onAnimationEnd(View view) {
			isAnimatingOut = false;
			view.setVisibility(View.GONE);
		}

		@Override
		public void onAnimationCancel(View arg0) {
			isAnimatingOut = false;
		}
	};

	public BackTopButtonBehaviour(Context context,AttributeSet attrs){
		super(context,attrs);
		Log.i(TAG,"BackTopButtonBehaviour: 创建Behaviour");
	}

	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,FloatingActionButton child,View directTargetChild,View target,int nestedScrollAxes){
		Log.i(TAG,"onStartNestedScroll: 开始滑动");
		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;//返回竖直方向
	}

	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout,FloatingActionButton child,View target,int dxConsumed,int dyConsumed,int dxUnconsumed,int dyUnconsumed){
		/*if (dyConsumed > 0 && dyUnconsumed == 0) {
			System.out.println("上滑中。。。");
		}
		if (dyConsumed == 0 && dyUnconsumed > 0) {
			System.out.println("到边界了还在上滑。。。");
		}
		if (dyConsumed < 0 && dyUnconsumed == 0) {
			System.out.println("下滑中。。。");
		}
		if (dyConsumed == 0 && dyUnconsumed < 0) {
			System.out.println("到边界了，还在下滑。。。");
		}*/
		if (((dyConsumed > 0 && dyUnconsumed == 0)
				||(dyConsumed == 0 && dyUnconsumed > 0))
				&& child.getVisibility() != View.VISIBLE) {
			//显示FAB
			Log.i(TAG,"onNestedScroll: 上滑显示");
			AnimationUtils.scaleShow(child,null);
		}else if(((dyConsumed < 0 && dyUnconsumed == 0)
				||(dyConsumed == 0 && dyUnconsumed < 0))
				&& child.getVisibility()!=View.GONE && !isAnimatingOut)
		{
			Log.i(TAG,"onNestedScroll: 下滑隐藏");
			AnimationUtils.scaleHide(child,viewPropertyAnimatorListener);
		}
	}
}
