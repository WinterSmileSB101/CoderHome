package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.Presenter.IGanioNotTextFragmentPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.VideoRecyclerViewAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.INotTextGanioFragmentView;
import winter.zxb.smilesb101.coderhome.View.Utils.AnimationUtils;
import winter.zxb.smilesb101.coderhome.View.Utils.StaticUtils;
import winter.zxb.smilesb101.coderhome.databinding.VideoGanioFragmentLayoutBinding;

/**
 * 项目名称：ViewThisWorld
 * 类描述：视频的干货碎片
 * 创建人：SmileSB101
 * 创建时间：2017/5/3 0003 14:29
 * 修改人：Administrator
 * 修改时间：2017/5/3 0003 14:29
 * 修改备注：
 */

public class VideoGanioFragment extends FragmentBase implements INotTextGanioFragmentView{

	VideoGanioFragmentLayoutBinding binding;
	IGanioNotTextFragmentPresenter presenter;

	RecyclerView recyclerView;

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

	public static VideoGanioFragment newInstance(){
		
		Bundle args = new Bundle();
		
		VideoGanioFragment fragment = new VideoGanioFragment();
		fragment.setArguments(args);
		return fragment;
	}

	public VideoGanioFragment()
	{
		title = "休息视频";
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		super.getThemeWrapper();
		LayoutInflater layoutInflater = inflater.cloneInContext(ContextWrapper);
		binding = DataBindingUtil.inflate(layoutInflater,R.layout.video_ganio_fragment_layout,container,false);
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
//		RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.recyclerView);
//		rv.setLayoutManager(new LinearLayoutManager(rootContext,LinearLayoutManager.VERTICAL,false));

		presenter = new IGanioNotTextFragmentPresenter(this);
		presenter.getSearchGanio();

		return rootView;
	}

	@Override
	public void showLoading(){

	}

	@Override
	public void closeLoding(){

	}

	@Override
	public void onError(String error){

	}

	@Override
	public String getGanioType(){
		return title;
	}

	@Override
	public void showGanio(ArrayList<NotTextGanioBean> ganioBeanArrayList){
		binding.setAdapter(new VideoRecyclerViewAdapter(ganioBeanArrayList));
	}
}
