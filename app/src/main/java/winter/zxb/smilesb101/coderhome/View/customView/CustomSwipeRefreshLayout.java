package winter.zxb.smilesb101.coderhome.View.customView;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;

import winter.zxb.smilesb101.coderhome.R;

/**
 * 项目名称：CoderHome
 * 类描述：自定义刷新（可以下拉刷新，上拉刷新）
 * 创建人：SmileSB101
 * 创建时间：2017/6/5 0005 14:07
 * 修改人：Administrator
 * 修改时间：2017/6/5 0005 14:07
 * 修改备注：
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout{

	public interface onUpLoadListener{

	}

	int mTouchSlop;
	onUpLoadListener onUpLoadListener;
	View mfootloadView;
	boolean isLoading = false;
	int mLastDown,mLast;
	RecyclerView recyclerView;


	public CustomSwipeRefreshLayout(Context context){
		super(context);
	}

	public CustomSwipeRefreshLayout(Context context,AttributeSet attrs){
		super(context,attrs);
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mfootloadView = LayoutInflater.from(context)
				.inflate(R.layout.recycler_view_footer,null,false);
	}

	@Override
	protected void onLayout(boolean changed,int left,int top,int right,int bottom){
		super.onLayout(changed,left,top,right,bottom);
		if(recyclerView==null)
		{
			getRecyclerView();
		}
	}


	/**
	 * 如果是底部，而且向上拉，而且没有加载标志则可以加载
	 * @return
	 */
	boolean canLoad()
	{
		return true;
	}

	void getRecyclerView()
	{
		if(getChildCount() > 0)
		{
			if(getChildAt(0) instanceof RecyclerView)
			{
				recyclerView = (RecyclerView)getChildAt(0);
				recyclerView.setOnScrollListener(new OnScrollListener(){
					@Override
					public void onScrolled(RecyclerView recyclerView,int dx,int dy){
						super.onScrolled(recyclerView,dx,dy);
						if(canLoad())
						{
						}
					}
				});
			}
		}
	}
}
