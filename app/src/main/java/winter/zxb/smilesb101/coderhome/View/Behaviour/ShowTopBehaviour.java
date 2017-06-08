package winter.zxb.smilesb101.coderhome.View.Behaviour;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import winter.zxb.smilesb101.coderhome.View.Utils.AnimationUtils;

/**
 * 项目名称：CoderHome
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/6/8 0008 09:52
 * 修改人：Administrator
 * 修改时间：2017/6/8 0008 09:52
 * 修改备注：
 */

public class ShowTopBehaviour extends CoordinatorLayout.Behavior<FloatingActionButton>{
	public ShowTopBehaviour(Context context,AttributeSet attrs){
		super(context,attrs);
	}

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent,FloatingActionButton child,View dependency){
		return dependency instanceof RecyclerView;
	}

	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent,FloatingActionButton child,View dependency){
		if(dependency instanceof RecyclerView) {
			RecyclerView recyclerView = (RecyclerView)dependency;
			if(((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition()!=0)
			{
				//显示view
				AnimationUtils.scaleShow(child,null);
			}
			else
			{
				AnimationUtils.scaleHide(child,null);
			}
		}
		return true;
	}
}
