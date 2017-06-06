package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuStoriesBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuTopStoriesBean;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Activitys.ZhiHuDetailsActivity;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.TextGanioFragment;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.ZhiHuFragment;
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

	ZhiHuFragment zhiHuFragment;
	RecyclerView recyclerView;
	boolean isLoading;

	float DownY,Y;

	public ZhiHuRecyclerViewAdapter(ArrayList<ZhiHuStoriesBean> list,ZhiHuFragment fragment,RecyclerView recyclerView)
	{
		this.activity = fragment.getActivity();
		zhiHuFragment = fragment;
		this.recyclerView = recyclerView;
		this.beanArrayList = list;
		if(list==null)
		{
			this.beanArrayList = new ArrayList<>();
		}
		isLoading = false;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		ViewDataBinding binding = null;
		context = parent.getContext();
		switch(viewType)
		{
			case -1:
				break;
			case 0:
				binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.zhihu_recycler_item_layout,parent,false);
				MyViewHolder holder = new MyViewHolder(binding.getRoot());
				holder.setBinding(binding);
				return holder;
			case 1:
				binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.recycler_view_footer,parent,false);
				FootViewHolder footViewHolder = new FootViewHolder(binding.getRoot());
				context = parent.getContext();
				return footViewHolder;
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		int viewType = getItemViewType(position);
		switch(viewType)
		{
			case -1:
				break;
			case 0:
				MyViewHolder holder1 = (MyViewHolder)holder;
				final ZhiHuStoriesBean bean = beanArrayList.get(position);
				holder1.getBinding().setVariable(BR.ZhiHuItem,bean);
				holder1.getBinding().setVariable(BR.clickListener,new View.OnClickListener(){
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
				break;
			case 1:
				final FootViewHolder footViewHolder = (FootViewHolder)holder;
				footViewHolder.progressText.setText("上拉加载");
				//  Log.i(TAG,"onBindViewHolder: "+ textGanioBeanArrayList.get(position).getDesc());
				if(!isLoading()) {
					recyclerView.setOnTouchListener(new View.OnTouchListener(){
						@Override
						public boolean onTouch(View v,MotionEvent event){
							if(!isLoading()) {
								switch(event.getAction()) {
									case MotionEvent.ACTION_DOWN:
										DownY = event.getY();
										return false;//这里是为了不让recycler的滑动受到阻挠，滑动更顺滑
									case MotionEvent.ACTION_UP:
										Y = event.getY();
										//如果向上滑动而且是最后一个条目可见,则开始加载数据
										if(Y - DownY < 0 && ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition()==getItemCount()-1) {
											setLoading(true);
											footViewHolder.progressBar.setVisibility(View.VISIBLE);
											footViewHolder.progressText.setText("正在加载...");
											recyclerView.setOnTouchListener(null);//去掉触摸监听
											zhiHuFragment.LoadMore(new ZhiHuFragment.onLoadMoreCallBack(){
												@Override
												public void onSuccess(ArrayList<ZhiHuStoriesBean> beanArrayList){
													//成功，
													setLoading(false);
													footViewHolder.progressBar.setVisibility(View.INVISIBLE);
													footViewHolder.progressText.setText("加载完成...");
													if(beanArrayList.size()==0)
													{
														Toast.makeText(context,"已经到底了噢！",Toast.LENGTH_SHORT).show();
														ZhiHuRecyclerViewAdapter.this.notifyDataSetChanged();
														return;
													}
													ZhiHuRecyclerViewAdapter.this.beanArrayList.addAll(beanArrayList);//添加新的一页

													ZhiHuRecyclerViewAdapter.this.notifyDataSetChanged();
												}

												@Override
												public void onTopSuccess(ArrayList<ZhiHuTopStoriesBean> topStoriesBeanArrayList){
													setLoading(false);
												}

												@Override
												public void onError(String error){
													footViewHolder.progressBar.setVisibility(View.INVISIBLE);
													footViewHolder.progressText.setText("加载失败...");
													Toast.makeText(context,"刷新失败：" + error,Toast.LENGTH_SHORT).show();
													setLoading(false);
												}
											});
										}
										return false;//这里是为了不让recycler的滑动受到阻挠，滑动更顺滑
								}
							}
							return false;//这里是为了不让recycler的滑动受到阻挠，滑动更顺滑
						}
					});
				}
				else
				{
					recyclerView.setOnTouchListener(null);
				}
				break;
		}
	}

	@Override
	public int getItemCount(){
		return beanArrayList.size()+1;
	}

	public boolean isLoading(){
		return isLoading;
	}

	public void setLoading(boolean loading){
		isLoading = loading;
	}

	@Override
	public int getItemViewType(int position){
		if(position == getItemCount()-1)
		{
			return 1;
		}
		return 0;
	}
	class MyViewHolder extends RecyclerView.ViewHolder{
		ImageView imageView;
		ViewDataBinding binding;
		public MyViewHolder(View itemView){
			super(itemView);
			imageView = (ImageView)itemView.findViewById(R.id.imageView);
		}

		public ViewDataBinding getBinding(){
			return binding;
		}

		public void setBinding(ViewDataBinding binding){
			this.binding = binding;
		}
	}

	class FootViewHolder extends RecyclerView.ViewHolder{

		ViewDataBinding dataBinding;
		ProgressBar progressBar;
		TextView progressText;
		View rootView;

		public FootViewHolder(View itemView){
			super(itemView);
			rootView = itemView;
			progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
			progressText = (TextView)itemView.findViewById(R.id.progressText);
		}

		public ViewDataBinding getDataBinding(){
			return dataBinding;
		}

		public void setDataBinding(ViewDataBinding dataBinding){
			this.dataBinding = dataBinding;
		}
	}
}
