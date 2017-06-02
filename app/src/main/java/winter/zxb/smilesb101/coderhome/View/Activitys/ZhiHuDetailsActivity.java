package winter.zxb.smilesb101.coderhome.View.Activitys;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import winter.zxb.smilesb101.coderhome.Bean.ZhiHuContentInsideBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuContentOutSideBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuStoriesBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuTopStoriesBean;
import winter.zxb.smilesb101.coderhome.Presenter.IZhiHuStoiesFragmentPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Interface.IZhiHuDetailsView;
import winter.zxb.smilesb101.coderhome.databinding.ActivityZhiHuDetailsBinding;

public class ZhiHuDetailsActivity extends AppCompatActivity implements IZhiHuDetailsView{

	private String TAG = "ZhiHuDetailsActivity";
    ActivityZhiHuDetailsBinding binding;
	public static final String Item_TOP_KEY = "item_top";
	public static final String Item_KEY = "item";
	String id;
	ZhiHuTopStoriesBean Topbean;
	ZhiHuStoriesBean bean;

	IZhiHuStoiesFragmentPresenter IPresenter;

	ImageView imageView;
	WebView html;
	CollapsingToolbarLayout collpasingToolBar;
	ProgressBar progressBar;

	View rootView;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		binding = DataBindingUtil.setContentView(this,R.layout.activity_zhi_hu_details);

		rootView = binding.getRoot();
		imageView = (ImageView)rootView.findViewById(R.id.imageView);
		html = (WebView)rootView.findViewById(R.id.html);
		progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar1);

		collpasingToolBar = (CollapsingToolbarLayout)rootView.findViewById(R.id.collpasingToolBar);

		Intent intent = getIntent();
		if(intent!=null)
		{
			if(intent.getSerializableExtra(Item_TOP_KEY)!=null) {
				Topbean = (ZhiHuTopStoriesBean)intent.getSerializableExtra(Item_TOP_KEY);
				id = Topbean.getId();
				/**
				 * 设置默认值，开启共享动画
				 */
				Glide.with(this)
						.load(Topbean.getImage())
						.into(imageView);
				collpasingToolBar.setTitle(Topbean.getTitle());
			}
			else if(intent.getSerializableExtra(Item_KEY)!=null)
			{
				bean = (ZhiHuStoriesBean)intent.getSerializableExtra(Item_KEY);
				id = bean.getId();
				/**
				 * 设置默认值，开启共享动画
				 */
				Glide.with(this)
						.load(bean.getImages()[0])
						.into(imageView);
				collpasingToolBar.setTitle(bean.getTitle());
			}

			if(!id.equals(""))
			{
				//Log.i(TAG,"onCreate: 获取到ID:"+bean.getId());
				IPresenter = new IZhiHuStoiesFragmentPresenter(this);
				IPresenter.getStoriesContentById();
			}
		}

		Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void onBackPressed(){
		super.onBackPressed();
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
	public void showLoading(){

	}

	@Override
	public void closeLoading(){

	}

	@Override
	public String getType(){
		return null;
	}

	@Override
	public void onFailure(String error){

	}

	@Override
	public void showStoriesDetails(ZhiHuContentInsideBean insideBean){
		try {
			//内部的展示
			//html.loadData(insideBean.getBody(), "text/html; charset=UTF-8", null);//这样写才能解决乱码问题，官方提供的写法解决不了中文乱码

			String s = "<link rel=\"stylesheet\" href=\""+insideBean.getCss()[0]+"\" type=\"text/css\" />"+insideBean.getBody();//引入Css
			s = s.replace("img-place-holder","");//去掉头部的白框
			html.setWebViewClient(new WebViewClient(){
				@Override
				public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest request){
					progressBar.setProgress(0);
					view.loadUrl(request.getUrl().toString());
					return true;
				}
			});
//			html.getSettings().setUseWideViewPort(true);//设置webview推荐使用的窗口，设置为true
//			html.getSettings().setLoadWithOverviewMode(true);//设置webview加载的页面的模式，也设置为true
			html.getSettings().setJavaScriptEnabled(true);
			html.setWebChromeClient(new WebChromeClient(){
				@Override
				public void onProgressChanged(WebView view,int newProgress){
					//更新进度条
					if(progressBar.getProgress()==100)
					{
						progressBar.setVisibility(View.INVISIBLE);
					}
					else {
						progressBar.setVisibility(View.VISIBLE);
						progressBar.setProgress(newProgress);
					}
				}
			});
			html.loadData(s, "text/html; charset=UTF-8", null);//载入网页
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showStoriesDetails(ZhiHuContentOutSideBean outSideBean){

	}

	@Override
	public String getId(){
		return id;
	}
}
