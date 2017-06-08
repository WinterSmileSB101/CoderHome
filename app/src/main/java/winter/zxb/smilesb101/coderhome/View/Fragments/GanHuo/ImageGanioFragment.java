package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.Presenter.IGainoTextFragmentPresenter;
import winter.zxb.smilesb101.coderhome.Presenter.IGanioNotTextFragmentPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.ImageRecyclerViewAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.INotTextGanioFragmentView;
import winter.zxb.smilesb101.coderhome.View.Utils.AnimationUtils;
import winter.zxb.smilesb101.coderhome.databinding.ImageganioRecyclerLayoutBinding;

/**
 * 项目名称：ViewThisWorld
 * 类描述：图片干货碎片
 * 创建人：SmileSB101
 * 创建时间：2017/5/3 0003 22:20
 * 修改人：Administrator
 * 修改时间：2017/5/3 0003 22:20
 * 修改备注：
 */

public class ImageGanioFragment extends FragmentBase implements INotTextGanioFragmentView,SwipeRefreshLayout.OnRefreshListener{
	ImageganioRecyclerLayoutBinding binding;
	IGanioNotTextFragmentPresenter igNotextParesenter;
	SwipeRefreshLayout refreshLayout;
	RecyclerView recyclerView;

	ArrayList<NotTextGanioBean> lastList;

	int NowItemCount = 0;//新闻的条目数（每页的偏移）
	int NowPage = 1;//新闻的页数
	boolean isLoadData = false;

	onLoadMoreCallBack onLoadMoreCallBack;

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

	public static ImageGanioFragment newInstance(){

		Bundle args = new Bundle();

		ImageGanioFragment fragment = new ImageGanioFragment();
		fragment.setArguments(args);
		return fragment;
	}
	public ImageGanioFragment()
	{
		title = "福利";
		titleImage = R.drawable.tab_pic;
		lastList = new ArrayList<>();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		super.getThemeWrapper();
		LayoutInflater layoutInflater = inflater.cloneInContext(ContextWrapper);
		binding = DataBindingUtil.inflate(layoutInflater,R.layout.imageganio_recycler_layout,container,false);
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
			public void onScrollStateChanged(RecyclerView recyclerView,int newState){
				super.onScrollStateChanged(recyclerView,newState);

			}

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

		refreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.refreshlayout);
		refreshLayout.setColorSchemeColors(Color.RED,Color.MAGENTA,Color.YELLOW,Color.BLUE);
		refreshLayout.setOnRefreshListener(this);
		refreshLayout.setRefreshing(true);
		onRefresh();

		return rootView;
	}

	int getNewItemNum(ArrayList<NotTextGanioBean> list)
	{
		if(lastList.size()==0)
		{
			return list.size();
		}
		int count = 0;
		for(int i = 0;i<lastList.size();i++)
		{
			if(list.get(i).getUrl().equals(lastList.get(i).getUrl()))
				return count;
			count++;
		}
		return count;
	}

	@Override
	public void showLoading(){

	}

	@Override
	public void closeLoding(){

	}

	@Override
	public void onError(String error){
		if(!isLoadData)
		{

		}
		else
		{
			isLoadData = false;
			onLoadMoreCallBack.onError(error);
		}
	}

	@Override
	public String getGanioType(){
		return title;
	}

	@Override
	public void showGanio(ArrayList<NotTextGanioBean> ganioBeanArrayList){
		Log.i(TAG,"showGanio: 图片展示："+ganioBeanArrayList.size());
		if(!isLoadData) {
			refreshLayout.setRefreshing(false);
			Toast.makeText(this.getActivity(),"图片刷新完成，新增数据 "+getNewItemNum(ganioBeanArrayList)+" 条.",Toast.LENGTH_SHORT).show();
			lastList = ganioBeanArrayList;
			binding.setAdapter(new ImageRecyclerViewAdapter(ganioBeanArrayList,this,recyclerView));//显示
		}
		else
		{
			isLoadData = false;
			onLoadMoreCallBack.onSuccess(ganioBeanArrayList);
		}
	}

	public void LoadMore(onLoadMoreCallBack callBack)
	{
		isLoadData = true;
		onLoadMoreCallBack = callBack;
		NowPage++;
		igNotextParesenter.getAllContent(NowPage);
	}

	public void addItemCount()
	{
		NowItemCount+=25;
		if(NowItemCount > 50)
		{
			//重置
			NowItemCount -= 50;
			NowPage++;
		}
	}

	@Override
	public void onRefresh(){
		//刷新监听
		//Log.i(TAG,"onRefresh: 开始刷新");
		refreshData();
		//Toast.makeText(this.getActivity(),"操作太快，我跟不上了..",Toast.LENGTH_SHORT);
	}

	void refreshData()
	{
		//refreshLayout.setRefreshing(true);//开始刷新
		//Log.i(TAG,"refreshData: 刷新数据");
		igNotextParesenter = new IGanioNotTextFragmentPresenter(this);
		igNotextParesenter.getAllContent();//获取数据
	}


	public interface onLoadMoreCallBack{
		void onSuccess(ArrayList<NotTextGanioBean> beanArrayList);
		void onError(String error);
	}

}
