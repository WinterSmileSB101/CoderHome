package winter.zxb.smilesb101.coderhome.View.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.SkinBean;
import winter.zxb.smilesb101.coderhome.Presenter.ISkinActivityPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.SkinRecyclerViewAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.ISkinActivityView;

public class SkinActivity extends ThemeBaseActivity implements ISkinActivityView{


	ISkinActivityPresenter iSkinActivityPresenter;
	RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skin);
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		/*FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				Snackbar.make(view,"Replace with your own action",Snackbar.LENGTH_LONG)
						.setAction("Action",null).show();
			}
		});*/
		initValue();
	}

	void initValue()
	{
		recyclerView = (RecyclerView)findViewById(R.id.official_recyclerView);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
		iSkinActivityPresenter = new ISkinActivityPresenter(this);
		iSkinActivityPresenter.getSkinList();//获取
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
	public void onOfficalSkinBcak(ArrayList<SkinBean> officalSkin){
		recyclerView.setAdapter(new SkinRecyclerViewAdapter(officalSkin,this));
	}

	@Override
	public void onError(String error){
		Toast.makeText(this,"错误："+error,Toast.LENGTH_LONG);
	}

	@Override
	public void onBackPressed(){
		super.onBackPressed();
		Intent intent = new Intent(this,MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//这里设置这个tag可以让活动重新创建
		startActivity(intent);
		finish();
	}
}
