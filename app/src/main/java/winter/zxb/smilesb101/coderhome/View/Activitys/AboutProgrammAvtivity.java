package winter.zxb.smilesb101.coderhome.View.Activitys;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import winter.zxb.smilesb101.coderhome.Bean.ProgrammAboutBean;
import winter.zxb.smilesb101.coderhome.Presenter.IProgrammAboutPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.AboutProgrammRecyclerAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.IAboutProgrammView;
import winter.zxb.smilesb101.coderhome.databinding.ActivityAboutProgrammAvtivityBinding;

public class AboutProgrammAvtivity extends ThemeBaseActivity implements IAboutProgrammView{

	String TAG = "AboutActivity";

	IProgrammAboutPresenter iProgrammAboutPresenter;
	ActivityAboutProgrammAvtivityBinding avtivityBinding;

	ImageView app_icon;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		avtivityBinding  = DataBindingUtil.setContentView(this,R.layout.activity_about_programm_avtivity);
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		initValue();

	}

	void initValue()
	{
		app_icon = (ImageView)findViewById(R.id.appIcon);
		RecyclerView rv = (RecyclerView)findViewById(R.id.develop_rv);
		rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
		rv = (RecyclerView)findViewById(R.id.open_source_rv);
		rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
		iProgrammAboutPresenter = new IProgrammAboutPresenter(this);
		iProgrammAboutPresenter.getAboutInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId())
		{
			case android.R.id.home:
				onBackPressed();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSuccess(ProgrammAboutBean programmAboutBean){
		//展示info
		app_icon.setImageResource(programmAboutBean.getProgramm_img());
		avtivityBinding.setAboutbean(programmAboutBean);
		avtivityBinding.setDevelopTeamAdapter(new AboutProgrammRecyclerAdapter(programmAboutBean.getCoderBeanArrayList(),null,this,0));
		avtivityBinding.setOpensourceAdapter(new AboutProgrammRecyclerAdapter(null,programmAboutBean.getOpenSourceBeanArrayList(),this,1));
	}

	@Override
	public void onError(String error){
		Log.i(TAG,"onError: "+error);
	}
}
