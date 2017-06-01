package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Presenter.IAppNewsFragmentPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.TextFragmentPageAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.IAppFragmentView;
import winter.zxb.smilesb101.coderhome.databinding.ReadFragmentLayoutBinding;

/**
 * 项目名称：ViewThisWorld
 * 类描述：阅读的碎片
 * 创建人：SmileSB101
 * 创建时间：2017/4/25 0025 09:32
 * 修改人：Administrator
 * 修改时间：2017/4/25 0025 09:32
 * 修改备注：
 */

public class GainoFragment extends FragmentBase implements IAppFragmentView{

	private final static String ChannelKey = "channel";

	TabLayout tabLayout;
	ViewPager viewPager;

	private IAppNewsFragmentPresenter appNewsFragmentPresenter;

	TextFragmentPageAdapter pageAdapter;

	ArrayList<String> channelList;

	public static GainoFragment newInstance(){
		
		Bundle args = new Bundle();

		GainoFragment fragment = new GainoFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	/**
	 * 构造方法
	 */
	public GainoFragment()
	{
		super();
		title = "干 货";
		titleImage = R.drawable.tab_ganio;
		this.appNewsFragmentPresenter = new IAppNewsFragmentPresenter(this);
		this.channelList = this.appNewsFragmentPresenter.getChannels();//从桥梁获取频道数据
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		rootContext = container.getContext();
		ReadFragmentLayoutBinding binding = DataBindingUtil.inflate(inflater,R.layout.read_fragment_layout,container,false);
		rootView = binding.getRoot();
		binding.setAdapter(new TextFragmentPageAdapter(this.getChildFragmentManager(),this.channelList));
		tabLayout = (TabLayout)rootView.findViewById(R.id.tablayout);
		viewPager = (ViewPager)rootView.findViewById(R.id.viewPager);
		tabLayout.setupWithViewPager(viewPager);
		return rootView;
	}

	@Override
	public ArrayList<String> getChannelList(){
		return this.channelList;
	}

}
