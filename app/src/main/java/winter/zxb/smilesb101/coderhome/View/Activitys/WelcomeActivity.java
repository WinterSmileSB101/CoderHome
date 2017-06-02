package winter.zxb.smilesb101.coderhome.View.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import winter.zxb.smilesb101.coderhome.Bean.BingToday;
import winter.zxb.smilesb101.coderhome.Presenter.IBingTodayPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Interface.IWelcomeView;

public class WelcomeActivity extends AppCompatActivity implements IWelcomeView{

	ImageView bingToday;
	TextView title;
	TextView subtitle;
	TextView desc;
	TextView waitText;

	IBingTodayPresenter iBingTodayPresenter;

	String TAG = "WelcomeActivity";

	Handler handler;
	int watiTime;
	static MainActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initValue();
	}

	void initValue()
	{
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		bingToday = (ImageView)findViewById(R.id.imageToday);

		Glide.with(this)
				.load(R.mipmap.welcome)
		.into(bingToday);

		title = (TextView)findViewById(R.id.title);
		subtitle = (TextView)findViewById(R.id.subTitle);
		desc = (TextView)findViewById(R.id.desc);
		waitText = (TextView)findViewById(R.id.waitText);

		iBingTodayPresenter = new IBingTodayPresenter(this);
		iBingTodayPresenter.getBingToday();

		/**
		 * 执行耗时操作
		 */
		new Runnable(){
			@Override
			public void run(){
				activity = new MainActivity();
				activity.initData();
			}
		}.run();

		handler = new Handler();
		watiTime = 5;
		waitText.setText(watiTime+"");
		handler.postDelayed(runanble,1000);
	}

	Runnable runanble = new Runnable(){
		@Override
		public void run(){
			watiTime--;
			waitText.setText(watiTime + "");
			if(watiTime<=0)
			{
				//打开主界面
				Log.i(TAG,"run: 打开新界面");
				startActivity(new Intent(WelcomeActivity.this,activity.getClass()));
				WelcomeActivity.this.finish();
				return;
			}
			handler.postDelayed(this,1000);
		}
	};

	@Override
	public void ShowTodayBing(BingToday bingToday){
		//展示信息

		Log.i(TAG,"ShowTodayBing: ");
		Glide.with(this)
				.load(bingToday.getImg_1366())
				.into(this.bingToday);

		title.setText(bingToday.getTitle());
		subtitle.setText(bingToday.getSubtitle());
		desc.setText(bingToday.getDescription());



	}

	@Override
	public void onError(String error){
		Log.i(TAG,"ShowTodayBing: ");
		Toast.makeText(this,error,Toast.LENGTH_LONG).show();
	}
}
