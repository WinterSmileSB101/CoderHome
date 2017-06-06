package winter.zxb.smilesb101.coderhome.View.Activitys;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.databinding.library.baseAdapters.BR;

import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.databinding.ActivityGanioDetailsBinding;

public class GanioDetailsActivity extends ThemeBaseActivity{
    WebView webView;
	View rootView;
	TextView errorView;
	public static final String TextGANIO_KEY = "textGanio";

	TextGanioBean textGanioBean;
	ActionBar actionBar;
	ProgressBar progressBar;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		final ActivityGanioDetailsBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_ganio_details);
		rootView = binding.getRoot();
		Intent intent = getIntent();
		if(intent.getSerializableExtra(TextGANIO_KEY)!=null)
		{
			textGanioBean = (TextGanioBean)intent.getSerializableExtra(TextGANIO_KEY);
			binding.setVariable(BR.text_item,textGanioBean);
		}

		errorView = (TextView)rootView.findViewById(R.id.errorView);

		Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		this.actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(textGanioBean.getDesc());

		progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar1);


		webView = (WebView)rootView.findViewById(R.id.html);
		webView.getSettings().setUseWideViewPort(true);//设置webview推荐使用的窗口，设置为true
		webView.getSettings().setLoadWithOverviewMode(true);//设置webview加载的页面的模式，也设置为true
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest request){
				progressBar.setProgress(0);
				errorView.setVisibility(View.GONE);
				view.loadUrl(request.getUrl().toString());
				return true;
			}

			@Override
			public void onReceivedError(WebView view,WebResourceRequest request,WebResourceError error){
				super.onReceivedError(view,request,error);
				binding.setError(error.toString());
				errorView.setVisibility(View.VISIBLE);
			}
		});
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onReceivedTitle(WebView view,String title){
				super.onReceivedTitle(view,title);
				actionBar.setTitle(title);
			}

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
		//载入网页
		webView.loadUrl(textGanioBean.getUrl());

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

}
