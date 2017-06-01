package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.Presenter.IGainoTextFragmentPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.TextRectclerAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.ITextGanioFragmentView;

/**
 * 项目名称：ViewThisWorld
 * 类描述：文字类型的Fragment
 * 创建人：SmileSB101
 * 创建时间：2017/4/26 0026 08:47
 * 修改人：Administrator
 * 修改时间：2017/4/26 0026 08:47
 * 修改备注：
 */

public class TextGanioFragment extends Fragment implements ITextGanioFragmentView{
	View rootView;
	Context context;
	RecyclerView recyclerView;
	TextRectclerAdapter adapter;
	SwipeRefreshLayout refreshLayout;
	String type;

	IGainoTextFragmentPresenter iGainoPresenter;

	static final String TYPE_KEY = "type";

	static final String TAG = "TextGanioFragment";

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
		rootView = inflater.inflate(R.layout.text_fragment_layout,container,false);
		if(rootView!=null) {
			//refreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.refreshlayout);//获取刷新控件
			recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
			LinearLayoutManager layout = new LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL, false);
			recyclerView.setLayoutManager(layout);
		}
		Bundle bundle = getArguments();
		if(bundle != null) {

			type = bundle.getString(TYPE_KEY);//获取到类型
			iGainoPresenter = new IGainoTextFragmentPresenter(this);
			iGainoPresenter.getAllContent();//获取数据

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
		//处理错误
	}

	@Override
	public void showGanio(ArrayList<TextGanioBean> textGanioBeanArrayList){
		//展示干货信息
		adapter = new TextRectclerAdapter(textGanioBeanArrayList,this.getActivity());
		recyclerView.setAdapter(adapter);
	}

	@Override
	public String getGanioType(){
		//Log.i(TAG,"getGanioType: 获取频道："+type);
		return type;
	}
}
