package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.Presenter.IGainoTextFragmentPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.TextRectclerAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.ITextGanioFragmentView;
import winter.zxb.smilesb101.coderhome.View.Utils.AnimationUtils;
import winter.zxb.smilesb101.coderhome.View.Utils.StaticUtils;

/**
 * 项目名称：ViewThisWorld
 * 类描述：文字类型的Fragment
 * 创建人：SmileSB101
 * 创建时间：2017/4/26 0026 08:47
 * 修改人：Administrator
 * 修改时间：2017/4/26 0026 08:47
 * 修改备注：
 */

public class TextGanioFragment extends Fragment implements ITextGanioFragmentView,SwipeRefreshLayout.OnRefreshListener{
	View rootView;
	Context context;
	RecyclerView recyclerView;
	TextRectclerAdapter adapter;
	SwipeRefreshLayout refreshLayout;
	String type;

	ArrayList<TextGanioBean> lastList;

	int NowItemCount = 0;//新闻的条目数（每页的偏移）
	int NowPage = 1;//新闻的页数
	boolean isLoadData = false;

	IGainoTextFragmentPresenter iGainoPresenter;

	onLoaderMoreCallBack onLoaderMoreCallBack;

	static final String TYPE_KEY = "type";

	static final String TAG = "TextGanioFragment";

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


	public static TextGanioFragment newInstance(String type){
		
		Bundle args = new Bundle();
		
		TextGanioFragment fragment = new TextGanioFragment();
		args.putString(TYPE_KEY,type);
		fragment.setArguments(args);
		return fragment;
	}
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		context = container.getContext();
		Context ContextWrapper = StaticUtils.STATIC_UTILS.getTheme(this);
		LayoutInflater layoutInflater = inflater.cloneInContext(ContextWrapper);
		rootView = layoutInflater.inflate(R.layout.text_fragment_layout,container,false);
		if(rootView!=null) {
			final FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
			fab.setOnClickListener(fab_cilckListener);
			fab.setScaleX(0.0f);
			fab.setScaleY(0.0f);
			fab.setAlpha(0.0f);
			fab.setVisibility(View.GONE);
			recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
			LinearLayoutManager layout = new LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL, false);
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
			refreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.refreshlayout);//获取刷新控件
			refreshLayout.setColorSchemeColors(Color.RED,Color.MAGENTA,Color.YELLOW,Color.BLUE);
			refreshLayout.setOnRefreshListener(this);
		}
		Bundle bundle = getArguments();
		if(bundle != null) {
			type = bundle.getString(TYPE_KEY);//获取到类型
			lastList = new ArrayList<>();
			refreshLayout.setRefreshing(true);
			onRefresh();
		}
		return rootView;
	}

	@Override
	public void showLoading(){
		//显示加载
//		if(refreshLayout!=null)
//		{
//			refreshLayout.setRefreshing(true);
//		}
	}

	@Override
	public void closeLoding(){
		//关闭加载
//		if(refreshLayout!=null)
//		{
//			refreshLayout.setRefreshing(false);
//		}
	}

	@Override
	public void onError(String error){
		refreshLayout.setRefreshing(false);
		//处理错误
		if(!isLoadData)
		{

		}
		else
		{
			isLoadData = false;
			onLoaderMoreCallBack.onError(error);
		}
	}

	@Override
	public void showGanio(ArrayList<TextGanioBean> textGanioBeanArrayList){
		//展示干货信息
		if(!isLoadData) {
			refreshLayout.setRefreshing(false);
			Toast.makeText(this.getActivity(),type+" 刷新完成，新增数据 "+getNewItemNum(textGanioBeanArrayList)+" 条.",Toast.LENGTH_SHORT).show();
			lastList = textGanioBeanArrayList;
			adapter = new TextRectclerAdapter(textGanioBeanArrayList,this,recyclerView);
			recyclerView.setAdapter(adapter);
		}
		else
		{
			isLoadData = false;
			onLoaderMoreCallBack.onSuccess(textGanioBeanArrayList);
		}
	}

	int getNewItemNum(ArrayList<TextGanioBean> list)
	{
		if(lastList.size()==0)
		{
			return list.size();
		}
		int count = 0;
		for(int i = 0;i<lastList.size();i++)
		{
			if(list.get(i).getDesc().equals(lastList.get(i).getDesc()))
				return count;
			count++;
		}
		return count;
	}

	@Override
	public String getGanioType(){
		//Log.i(TAG,"getGanioType: 获取频道："+type);
		return type;
	}

	public void LoadMore(onLoaderMoreCallBack callBack)
	{
		isLoadData = true;
		onLoaderMoreCallBack = callBack;
		NowPage++;
		iGainoPresenter.getAllContent(NowPage);
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
		iGainoPresenter = new IGainoTextFragmentPresenter(this);
		iGainoPresenter.getAllContent();//获取数据
	}

	public interface onLoaderMoreCallBack
	{
		void onSuccess(ArrayList<TextGanioBean> beanArrayList);
		void onError(String error);
	}


}
