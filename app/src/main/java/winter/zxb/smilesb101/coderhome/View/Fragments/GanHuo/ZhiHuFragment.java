package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.ZhiHuStoriesBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuTopStoriesBean;
import winter.zxb.smilesb101.coderhome.Presenter.IZhiHuStoiesFragmentPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Activitys.ZhiHuDetailsActivity;
import winter.zxb.smilesb101.coderhome.View.Adapter.ZhiHuRecyclerViewAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.IZhiHuStoriesFragmentView;
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

public class ZhiHuFragment extends FragmentBase implements IZhiHuStoriesFragmentView{

	ZhihuFragmentLayoutBinding binding;
	IZhiHuStoiesFragmentPresenter iZhiHuStoiesFragmentPresenter;
	RollPagerView banner;

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
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		binding = DataBindingUtil.inflate(inflater,R.layout.zhihu_fragment_layout,container,false);
		rootView = binding.getRoot();
		rootContext = container.getContext();
		RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.recyclerView);
		rv.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false));
		banner = (RollPagerView)rootView.findViewById(R.id.banner);

		iZhiHuStoiesFragmentPresenter = new IZhiHuStoiesFragmentPresenter(this);
		iZhiHuStoiesFragmentPresenter.getLatestStoies();
		iZhiHuStoiesFragmentPresenter.getLatestTopStoies();

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

	}

	@Override
	public void showStories(ArrayList<ZhiHuStoriesBean> zhiHuStoriesBeanArrayList){

		binding.setAdapter(new ZhiHuRecyclerViewAdapter(zhiHuStoriesBeanArrayList,this.getActivity()));
	}

	@Override
	public void showTopStories(ArrayList<ZhiHuTopStoriesBean> topStoriesBeanArrayList){
//		Intent intent = new Intent(rootContext,)
		banner.setAdapter(new TestLoopAdapter(banner,topStoriesBeanArrayList,this.getActivity()));
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

}
