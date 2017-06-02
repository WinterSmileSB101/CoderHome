package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.TextGanioFragment;


/**
 * 项目名称：ViewThisWorld
 * 类描述：文字类型的PageAdapter
 * 创建人：SmileSB101
 * 创建时间：2017/4/26 0026 08:44
 * 修改人：Administrator
 * 修改时间：2017/4/26 0026 08:44
 * 修改备注：
 */

public class TextFragmentPageAdapter extends BaseFragmentPagerAdapter{
	ArrayList<String> typesList;
	ArrayList<TextGanioFragment> fragmentlist;
	private static final String TAG = "TextFragmentPageAdapter";
	public TextFragmentPageAdapter(FragmentManager fm,ArrayList<String> types){
		super(fm);
		this.typesList = types;
		if(this.typesList==null)
		{
			this.typesList = new ArrayList<>();
		}
		fragmentlist = new ArrayList<>();
	}

	@Override
	public Fragment getItem(int position){
		//Log.i(TAG,"getItem: 位置："+position);
		switch(position)
		{
			default://文字
				return TextGanioFragment.newInstance(typesList.get(position));
		}
	}

	@Override
	public int getCount(){
		//Log.i(TAG,"getPageTitle: 获取数量 "+typesList.size());
		return typesList.size();
	}

	@Override
	public CharSequence getPageTitle(int position){
		//Log.i(TAG,"getPageTitle: 获取标题 "+typesList.get(position));
		return typesList.get(position);
	}
}
