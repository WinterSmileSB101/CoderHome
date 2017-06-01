package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.BR;
import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.databinding.VideoRecyclerviewItemLayoutBinding;


/**
 * 项目名称：ViewThisWorld
 * 类描述：视频的Recycler列表Adapter
 * 创建人：SmileSB101
 * 创建时间：2017/5/3 0003 17:58
 * 修改人：Administrator
 * 修改时间：2017/5/3 0003 17:58
 * 修改备注：
 */

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter{

	Context context;
	ArrayList<NotTextGanioBean> videoGanioBeenList;
	public VideoRecyclerViewAdapter(ArrayList<NotTextGanioBean> list)
	{
		this.videoGanioBeenList = list;
		if(list == null)
		{
			videoGanioBeenList = new ArrayList<>();
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		VideoRecyclerviewItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.video_recyclerview_item_layout,parent,false);
		MyViewHolder holder = new MyViewHolder(binding.getRoot());
		holder.setDataBinding(binding);
		context = parent.getContext();
		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		MyViewHolder holder1 = (MyViewHolder)holder;
		holder1.getDataBinding().setVariable(BR.video_item,videoGanioBeenList.get(position));
		holder1.getDataBinding().executePendingBindings();
		//显示视频的截图,获取不了。问题未解决
		WebView webView = new WebView(context);
		webView.loadUrl(videoGanioBeenList.get(position).getUrl());
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest request){
				view.loadUrl(request.getUrl().toString());
				return true;
			}
		});
		webView.setDrawingCacheEnabled(true);
		Bitmap bmp = webView.getDrawingCache();
		Glide.with(context)
		.load(bmp)
		.into(holder1.webView);

	}

	@Override
	public int getItemCount(){
		return videoGanioBeenList.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder{
		ImageView webView;
		VideoRecyclerviewItemLayoutBinding dataBinding;
		public MyViewHolder(View itemView){
			super(itemView);
			webView = (ImageView)itemView.findViewById(R.id.surfaceView);
		}

		public VideoRecyclerviewItemLayoutBinding getDataBinding(){
			return dataBinding;
		}

		public void setDataBinding(VideoRecyclerviewItemLayoutBinding dataBinding){
			this.dataBinding = dataBinding;
		}
	}
}
