package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.FragmentBase;


/**
 * 项目名称：ViewThisWorld
 * 类描述：viewPagerAdapter的基类
 * 创建人：SmileSB101
 * 创建时间：2017/5/3 0003 09:01
 * 修改人：Administrator
 * 修改时间：2017/5/3 0003 09:01
 * 修改备注：
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter{
	ArrayList<FragmentBase> fragmentBases;
	ArrayList<String> titles;
	public BaseFragmentPagerAdapter(FragmentManager fm,ArrayList<FragmentBase> fragmentBaseArrayList){
		super(fm);
		fragmentBases = fragmentBaseArrayList;
		if(fragmentBaseArrayList==null)
		{
			fragmentBases = new ArrayList<>();
		}
		titles = new ArrayList<>();

	}
	public BaseFragmentPagerAdapter(FragmentManager fm)
	{
		super(fm);
		fragmentBases = new ArrayList<>();
	}

	@Override
	public Fragment getItem(int position){
		return fragmentBases.get(position);
	}

	@Override
	public int getCount(){
		return fragmentBases.size();
	}

	@Override
	public CharSequence getPageTitle(int position){
		return fragmentBases.get(position).getTitle();
	}

	public void setFragmentBases(ArrayList<FragmentBase> fragmentBases){
		this.fragmentBases = fragmentBases;
		notifyDataSetChanged();
	}

	public void addFragmentBases(FragmentBase fragmentBase)
	{
		this.fragmentBases.add(fragmentBase);
		notifyDataSetChanged();
	}
}
