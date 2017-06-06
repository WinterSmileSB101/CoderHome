package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.BaseFragmentPagerAdapter;
import winter.zxb.smilesb101.coderhome.databinding.NewsFragmentLayoutBinding;

/**
 * 项目名称：ViewThisWorld
 * 类描述：新闻的碎片
 * 创建人：SmileSB101
 * 创建时间：2017/5/3 0003 08:54
 * 修改人：Administrator
 * 修改时间：2017/5/3 0003 08:54
 * 修改备注：
 */

public class GanioNewsFragment extends FragmentBase{

	public static GanioNewsFragment newInstance(){
		
		Bundle args = new Bundle();
		
		GanioNewsFragment fragment = new GanioNewsFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	public GanioNewsFragment()
	{
		title = "资 讯";
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		title = getResources().getString(R.string.NewsName);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		super.getThemeWrapper();
		LayoutInflater layoutInflater = inflater.cloneInContext(ContextWrapper);
		NewsFragmentLayoutBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.news_fragment_layout,container,false);
		rootView = binding.getRoot();
		rootContext  = container.getContext();
		return rootView;
	}
	void initView(NewsFragmentLayoutBinding binding)
	{
		binding.setAdapter(new BaseFragmentPagerAdapter(this.getChildFragmentManager()));
	}
}
