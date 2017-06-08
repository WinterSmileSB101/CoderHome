package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.NotTextRecyclerAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.INotTextGanioFragmentView;
import winter.zxb.smilesb101.coderhome.View.Utils.AnimationUtils;
import winter.zxb.smilesb101.coderhome.View.Utils.StaticUtils;


/**
 * 项目名称：ViewThisWorld
 * 类描述：不是文字的RecyclerViewFragment
 * 创建人：SmileSB101
 * 创建时间：2017/4/28 0028 07:24
 * 修改人：Administrator
 * 修改时间：2017/4/28 0028 07:24
 * 修改备注：
 */

public class NotTextGanioFragment extends Fragment implements INotTextGanioFragmentView{
	RollPagerView rollPagerView;
	NotTextRecyclerAdapter adapter;
	View rootView;
	Context context;
	RecyclerView recyclerView;
	SwipeRefreshLayout refreshLayout;
	String type;

	static final String TYPE_KEY = "type";

	static final String TAG = "NotTextGanioFragment";

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

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		Context ContextWrapper = StaticUtils.STATIC_UTILS.getTheme(this);
		LayoutInflater layoutInflater = inflater.cloneInContext(ContextWrapper);
		context = container.getContext();
		rootView = layoutInflater.inflate(R.layout.nottext_fragment_layout,container,false);
		return rootView;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
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
		rollPagerView = (RollPagerView)rootView.findViewById(R.id.banner);
//		recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
//		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//		recyclerView.setLayoutManager(layoutManager);
		Bundle bundle = getArguments();
		if(bundle!=null)
		{
			type = bundle.getString(TYPE_KEY);
		}
	}

	@Override
	public void showLoading(){
		refreshLayout.setRefreshing(true);
	}

	@Override
	public void closeLoding(){
		refreshLayout.setRefreshing(false);
	}

	@Override
	public void onError(String error){

	}

	@Override
	public String getGanioType(){
		return type;
	}

	@Override
	public void showGanio(ArrayList<NotTextGanioBean> ganioBeanArrayList){
		adapter = new NotTextRecyclerAdapter(ganioBeanArrayList);
		recyclerView.setAdapter(adapter);
	}
}
