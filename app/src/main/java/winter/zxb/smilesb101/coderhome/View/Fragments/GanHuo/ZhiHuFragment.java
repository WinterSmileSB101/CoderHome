package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import winter.zxb.smilesb101.coderhome.Bean.ZhiHuStoriesBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuTopStoriesBean;
import winter.zxb.smilesb101.coderhome.Presenter.IZhiHuStoiesFragmentPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Activitys.ZhiHuDetailsActivity;
import winter.zxb.smilesb101.coderhome.View.Adapter.ZhiHuRecyclerViewAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.IZhiHuStoriesFragmentView;
import winter.zxb.smilesb101.coderhome.View.Utils.AnimationUtils;
import winter.zxb.smilesb101.coderhome.View.Utils.StaticUtils;
import winter.zxb.smilesb101.coderhome.View.customView.FullLinearLayoutManager;
import winter.zxb.smilesb101.coderhome.databinding.ZhihuFragmentLayoutBinding;


/**
 * 项目名称：ViewThisWorld
 * 类描述：知乎的Fragment
 * 创建人：SmileSB101
 * 创建时间：2017/5/4 0004 15:03
 * 修改人：Administrator
 * 修改时间：2017/5/4 0004 15:03
 * 修改备注：
 */

public class ZhiHuFragment extends FragmentBase implements IZhiHuStoriesFragmentView,SwipeRefreshLayout.OnRefreshListener{

	ZhihuFragmentLayoutBinding binding;
	IZhiHuStoiesFragmentPresenter iZhiHuStoiesFragmentPresenter;
	RollPagerView banner;

	RecyclerView recyclerView;
	boolean isLoadData = false;
	SwipeRefreshLayout refreshLayout;

	onLoadMoreCallBack onLoadMoreCallBack;
	int year,month,day;

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

	View.OnClickListener fab_cilckListener = new View.OnClickListener(){
		@Override
		public void onClick(View v){
			if(recyclerView!=null)
				recyclerView.smoothScrollToPosition(0);
		}
	};

	public static ZhiHuFragment newInstance(){

		Bundle args = new Bundle();

		ZhiHuFragment fragment = new ZhiHuFragment();
		fragment.setArguments(args);
		return fragment;
	}
	public ZhiHuFragment()
	{
		title = "知乎日报";
		titleImage = R.drawable.tab_zhihu;
		SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
		Log.i(TAG,"ZhiHuFragment: "+sdfY.format(new Date()));
		year = Integer.parseInt(sdfY.format(new Date()));
		SimpleDateFormat sdfM = new SimpleDateFormat("yyyyMM");
		month = Integer.parseInt(sdfM.format(new Date()).replace(sdfY.format(new Date()),""));
		SimpleDateFormat sdfD = new SimpleDateFormat("yyyyMMdd");
		day = Integer.parseInt(sdfD.format(new Date()).replace(sdfM.format(new Date()),""));
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		super.getThemeWrapper();
		LayoutInflater layoutInflater = inflater.cloneInContext(ContextWrapper);
		binding = DataBindingUtil.inflate(layoutInflater,R.layout.zhihu_fragment_layout,container,false);
		rootView = binding.getRoot();
		rootContext = container.getContext();
		final FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
		fab.setOnClickListener(fab_cilckListener);
		fab.setScaleX(0.0f);
		fab.setScaleY(0.0f);
		fab.setAlpha(0.0f);
		fab.setVisibility(View.GONE);
		recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
		LinearLayoutManager layout = new LinearLayoutManager(this.rootContext,LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(layout);
		recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener(){
			@Override
			public void onScrolled(RecyclerView recyclerView,int dx,int dy){
				super.onScrolled(recyclerView,dx,dy);
				if(((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition()==0)
				{
					if(fab.getVisibility()!=View.GONE && !isAnimatingOut) {
						AnimationUtils.scaleHide(fab,viewPropertyAnimatorListener);
					}
				}
				else
				{
					if(fab.getVisibility()!=View.VISIBLE)
						AnimationUtils.scaleShow(fab,null);
				}
			}
		});
//		recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
//		recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false));

		banner = (RollPagerView)rootView.findViewById(R.id.banner);
		iZhiHuStoiesFragmentPresenter = new IZhiHuStoiesFragmentPresenter(this);
		onRefresh();
		return rootView;
	}

	@Override
	public void showLoading(){

	}

	@Override
	public void closeLoading(){

	}

	@Override
	public String getType(){
		return title;
	}

	@Override
	public void onFailure(String error){
		if(!isLoadData)
		{}
		else
		{
			isLoadData = false;
			onLoadMoreCallBack.onError(error);
		}
	}

	@Override
	public void showStories(ArrayList<ZhiHuStoriesBean> zhiHuStoriesBeanArrayList){
		//refreshLayout.setRefreshing(false);
		if(!isLoadData) {
			binding.setAdapter(new ZhiHuRecyclerViewAdapter(zhiHuStoriesBeanArrayList,this,recyclerView));
		}
		else
		{
			isLoadData = false;
			onLoadMoreCallBack.onSuccess(zhiHuStoriesBeanArrayList);
			reduceDay();
		}
	}

	@Override
	public void showTopStories(ArrayList<ZhiHuTopStoriesBean> topStoriesBeanArrayList){
//		Intent intent = new Intent(rootContext,)
		//refreshLayout.setRefreshing(false);
		if(topStoriesBeanArrayList!=null && topStoriesBeanArrayList.size()>0) {
			banner.setAdapter(new TestLoopAdapter(banner,topStoriesBeanArrayList,this.getActivity()));
		}
	}

	@Override
	public String getDate(){
		String m = "";
		String d = "";
		if(month<10)
		{
			m = "0"+month;
		}
		else
		{
			m = month+"";
		}
		if(day<10)
		{
			d = "0"+day;
		}
		else {
			d = day+"";
		}
		//Log.i(TAG,"getDate: "+year+m+d);
		return year+m+d;
	}

	@Override
	public void onRefresh(){
		iZhiHuStoiesFragmentPresenter.getLatestStoies();
		iZhiHuStoiesFragmentPresenter.getLatestTopStoies();
	}

	private class TestLoopAdapter extends LoopPagerAdapter{
		private ArrayList<ZhiHuTopStoriesBean> beanArrayList;
		Intent intent;
		Activity activity;

		public TestLoopAdapter(RollPagerView viewPager,ArrayList<ZhiHuTopStoriesBean> list,Activity activity) {
			super(viewPager);
			this.beanArrayList = list;
			this.activity = activity;
			if(list==null)
			{
				this.beanArrayList = new ArrayList<>();
			}
		}

		@Override
		public View getView(ViewGroup container, int position) {
			final ZhiHuTopStoriesBean bean = beanArrayList.get(position);
			ImageView view = new ImageView(container.getContext());
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			view.setTransitionName("newsImage");//设置变换名称方便共享动画
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			Glide.with(container.getContext())
					.load(bean.getImage())
					.into(view);
			view.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					//进入,打开新界面显示详情新闻界面
					Intent intent = new Intent(rootContext,ZhiHuDetailsActivity.class);//新建Intent
					intent.putExtra(ZhiHuDetailsActivity.Item_TOP_KEY,bean);//传递对象

					rootContext.startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(activity,v,"newsImage").toBundle());//打开新活动
				}
			});
			return view;
		}

		@Override
		public int getRealCount() {
			return beanArrayList.size();
		}
	}

	public void LoadMore(onLoadMoreCallBack callBack)
	{
		onLoadMoreCallBack = callBack;
		isLoadData = true;
		iZhiHuStoiesFragmentPresenter.getPastNews();
	}

	void reduceDay()
	{
		if(day > 1)
		{
			day--;
		}
		else
		{
			month--;
			switch(month)
			{
				case 0:
					year--;
					month = 12;
					day = 31;
					break;
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					day = 31;
					break;
				case 2:
					if((year%4==0&&year%100!=0)||(year%400==0))
					{
						day = 29;
					}
					else
					{
						day = 28;
					}
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					day = 30;
					break;
			}
		}
	}

	public interface onLoadMoreCallBack{
		void onSuccess(ArrayList<ZhiHuStoriesBean> storiesBeanArrayList);
		void onTopSuccess(ArrayList<ZhiHuTopStoriesBean> topStoriesBeanArrayList);
		void onError(String error);
	}

}
