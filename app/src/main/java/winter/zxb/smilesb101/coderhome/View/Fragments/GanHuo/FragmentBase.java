package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winter.zxb.smilesb101.coderhome.View.Utils.StaticUtils;

/**
 * 项目名称：ViewThisWorld
 * 类描述：碎片基类
 * 创建人：SmileSB101
 * 创建时间：2017/5/3 0003 08:10
 * 修改人：Administrator
 * 修改时间：2017/5/3 0003 08:10
 * 修改备注：
 */

public class FragmentBase extends Fragment{
	protected View rootView;
	protected Context rootContext;
	protected String TAG;
	protected String title;
	protected int titleImage;
	protected Context ContextWrapper;

	protected static final String TITLE_KEY = "title";
	protected static final String TITLEIMAGE_KEY = "title";

	public FragmentBase()
	{
		TAG = getClass().getName();
	}

	public static FragmentBase newInstance(){
		
		Bundle args = new Bundle();
		
		FragmentBase fragment = new FragmentBase();
		fragment.setArguments(args);
		return fragment;
	}

	public String getTitle(){
		return title;
	}

	public int getTitleImage(){
		return titleImage;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		this.getThemeWrapper();
		return super.onCreateView(inflater,container,savedInstanceState);
	}

	protected void getThemeWrapper()
	{
		this.ContextWrapper = StaticUtils.STATIC_UTILS.getTheme(this);
	}
}
