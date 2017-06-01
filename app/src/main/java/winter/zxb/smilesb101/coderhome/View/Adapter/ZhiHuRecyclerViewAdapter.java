package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.ZhiHuStoriesBean;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Activitys.ZhiHuDetailsActivity;
import winter.zxb.smilesb101.coderhome.databinding.ZhihuRecyclerItemLayoutBinding;


/**
 * 项目名称：ViewThisWorld
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/5/4 0004 16:34
 * 修改人：Administrator
 * 修改时间：2017/5/4 0004 16:34
 * 修改备注：
 */

public class ZhiHuRecyclerViewAdapter extends RecyclerView.Adapter{
	ArrayList<ZhiHuStoriesBean> beanArrayList;
	Context context;
	Activity activity;

	public ZhiHuRecyclerViewAdapter(ArrayList<ZhiHuStoriesBean> list,Activity activity)
	{
		this.activity = activity;
		this.beanArrayList = list;
		if(list==null)
		{
			this.beanArrayList = new ArrayList<>();
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		ZhihuRecyclerItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.zhihu_recycler_item_layout,parent,false);
		MyViewHolder holder = new MyViewHolder(binding.getRoot());
		holder.setBinding(binding);
		context = parent.getContext();
		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		MyViewHolder holder1 = (MyViewHolder)holder;
		final ZhiHuStoriesBean bean = beanArrayList.get(position);
		holder1.getBinding().setZhiHuItem(bean);
		holder1.getBinding().setClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(activity,ZhiHuDetailsActivity.class);
				intent.putExtra(ZhiHuDetailsActivity.Item_KEY,bean);//传递对象

				context.startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(activity,v,"newsImage").toBundle());//打开新活动
			}
		});
		holder1.getBinding().executePendingBindings();
		Glide.with(context)
				.load(beanArrayList.get(position).getImages()[0])
				.into(holder1.imageView);
	}

	@Override
	public int getItemCount(){
		return beanArrayList.size();
	}
	class MyViewHolder extends RecyclerView.ViewHolder{
		ImageView imageView;
		ZhihuRecyclerItemLayoutBinding binding;
		public MyViewHolder(View itemView){
			super(itemView);
			imageView = (ImageView)itemView.findViewById(R.id.imageView);
		}

		public ZhihuRecyclerItemLayoutBinding getBinding(){
			return binding;
		}

		public void setBinding(ZhihuRecyclerItemLayoutBinding binding){
			this.binding = binding;
		}
	}
}
