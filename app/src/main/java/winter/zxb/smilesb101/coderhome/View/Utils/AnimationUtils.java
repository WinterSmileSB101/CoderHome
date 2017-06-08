package winter.zxb.smilesb101.coderhome.View.Utils;

import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;

/**
 * 项目名称：CoderHome
 * 类描述：静态动画类
 * 创建人：SmileSB101
 * 创建时间：2017/6/8 0008 09:10
 * 修改人：Administrator
 * 修改时间：2017/6/8 0008 09:10
 * 修改备注：
 */

public class AnimationUtils{
	public static void scaleShow(View view,ViewPropertyAnimatorListener viewPropertyAnimatorListener)
	{
		view.setVisibility(View.VISIBLE);
		ViewCompat.animate(view)
				.scaleX(1.0f)
				.scaleY(1.0f)
				.alpha(1.0f)
				.setDuration(800)
				.setListener(viewPropertyAnimatorListener)
				.setInterpolator(new LinearOutSlowInInterpolator())
				.withLayer()
				.start();
	}

	public static void scaleHide(View view,ViewPropertyAnimatorListener viewPropertyAnimatorListener)
	{
		if(viewPropertyAnimatorListener==null)
		{
			viewPropertyAnimatorListener = new ViewPropertyAnimatorListener(){
				@Override
				public void onAnimationStart(View view){

				}

				@Override
				public void onAnimationEnd(View view){
					view.setVisibility(View.GONE);
				}

				@Override
				public void onAnimationCancel(View view){

				}
			};
		}
		ValueAnimator animator = new ValueAnimator();

		ViewCompat.animate(view)
				.scaleX(0.0f)
				.scaleY(0.0f)
				.alpha(0.0f)
				.setDuration(800)
				.setListener(viewPropertyAnimatorListener)
				.setInterpolator(new LinearOutSlowInInterpolator())
				.withLayer()
				.start();
	}
}
