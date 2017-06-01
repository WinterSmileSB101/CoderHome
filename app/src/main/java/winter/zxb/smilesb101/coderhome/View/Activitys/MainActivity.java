package winter.zxb.smilesb101.coderhome.View.Activitys;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.FragmentBase;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.GainoFragment;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.ImageGanioFragment;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.ZhiHuFragment;
import winter.zxb.smilesb101.coderhome.View.customView.RoundImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{

	String TAG = "主活动";

	ViewPager viewPager;
	MainActivityPagerAdapter adapter;
	RoundImageView headImage;
	TabLayout tabLayout;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initValue();
	}

	void initValue()
	{
		headImage = (RoundImageView)findViewById(R.id.headImage);
		headImage.setOnClickListener(this);

		viewPager = (ViewPager)findViewById(R.id.viewPager);

		tabLayout = (TabLayout)findViewById(R.id.tablayout);

		adapter = new MainActivityPagerAdapter(this.getSupportFragmentManager(),this);

		setTabLayout();
		FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				Snackbar.make(view,"Replace with your own action",Snackbar.LENGTH_LONG)
						.setAction("Action",null).show();
			}
		});
		DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

	}

	/**
	 * 设置adapter
	 */
	void setTabLayout()
	{
		viewPager.setAdapter(adapter);
		tabLayout.setupWithViewPager(viewPager);
		ArrayList<Integer> titleImages = adapter.getTitleImages();

		for(int i = 0;i<tabLayout.getTabCount();i++)
		{
			TabLayout.Tab tab = tabLayout.getTabAt(i);
			if(tab!=null)
			{
				tab.setIcon(titleImages.get(i));
				tab.setText("");
			}
		}
	}

	@Override
	public void onBackPressed(){
		DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		if(drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if(id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item){
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if(id == R.id.nav_ganio) {
			// 打开干货界面
			//Help.cleanSupportFragmentManager(this);
			//adapter = new MainActivityPagerAdapter(this.getSupportFragmentManager(),this);
			//setTabLayout();
		}
		 else if(id == R.id.nav_share) {

		} else if(id == R.id.nav_send) {

		}

		DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void onClick(View v){
		switch(v.getId())
		{
			case R.id.headImage:
				Log.i(TAG,"onClick: 展开导航");
				DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
				drawer.openDrawer(GravityCompat.START);
				break;
			default:
				break;
		}
	}


	class MainActivityPagerAdapter extends FragmentPagerAdapter{

		ArrayList<FragmentBase> fragmentList;
		ArrayList<Integer> titleImages;
		ArrayList<String> titles;
		Activity activity;

		public MainActivityPagerAdapter(FragmentManager fm,Activity activity){
			super(fm);
			fragmentList = new ArrayList<>();
			fragmentList.add(GainoFragment.newInstance());//阅读
			fragmentList.add(ImageGanioFragment.newInstance());//福利
			fragmentList.add(ZhiHuFragment.newInstance());//知乎

			titles = new ArrayList<>();
			titleImages = new ArrayList<>();
			for(FragmentBase fragment : fragmentList) {
				titles.add(fragment.getTitle());
				titleImages.add(fragment.getTitleImage());
			}
		}

		@Override
		public int getCount(){
			return fragmentList.size();
		}

		@Override
		public Fragment getItem(int position){
			return fragmentList.get(position);
		}

		public ArrayList<Integer> getTitleImages(){
			return titleImages;
		}

		@Override
		public CharSequence getPageTitle(int position){
			return "";
		}

		public ArrayList<String> getTitles(){
			return titles;
		}
	}


}
