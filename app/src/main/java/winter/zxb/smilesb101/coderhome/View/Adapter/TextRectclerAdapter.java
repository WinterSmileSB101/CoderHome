package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Activitys.GanioDetailsActivity;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.FragmentBase;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.TextGanioFragment;
import winter.zxb.smilesb101.coderhome.databinding.TextItemLayoutBinding;


/**
 * 项目名称：ViewThisWorld
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/4/26 0026 12:33
 * 修改人：Administrator
 * 修改时间：2017/4/26 0026 12:33
 * 修改备注：
 */

public class TextRectclerAdapter extends RecyclerView.Adapter{
	View rootView;
	Context context;

	ArrayList<TextGanioBean> textGanioBeanArrayList;

	static final String TAG = "TextRecyclerAdapter";
	Activity activity;
	RecyclerView recyclerView;
	TextGanioFragment textFragment;
	boolean isLoading;

	float DownY,Y;

	public TextRectclerAdapter(ArrayList<TextGanioBean> list,TextGanioFragment fragment,RecyclerView recyclerView)
	{
		//Log.i(TAG,"TextRectclerAdapter: 创建RecAdapter");
		this.activity = fragment.getActivity();
		this.textGanioBeanArrayList = list;
		this.recyclerView = recyclerView;
		textFragment = fragment;
		isLoading = false;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		//Log.i(TAG,"onCreateViewHolder: 创建ViewHolder："+textGanioBeanArrayList.size());
		context = parent.getContext();
		ViewDataBinding dataBinding = null;
		switch(viewType)
		{
			case -1://头部
				return null;
			case 0://中间正常部分
				dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.text_item_layout,parent,false);//Databinding的绑定实例化
				rootView = dataBinding.getRoot();
				TextRectclerAdapter.NormalViewHolder viewHolder = new NormalViewHolder(rootView);
				viewHolder.setBinding(dataBinding);//传递到holder中进行绑定
				return viewHolder;
			case 1://底部
				dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.recycler_view_footer,parent,false);//Databinding的绑定实例化
				rootView = dataBinding.getRoot();
				TextRectclerAdapter.FootViewHolder footViewHolder = new FootViewHolder(rootView);
				footViewHolder.setDataBinding(dataBinding);//传递到holder中进行绑定
				return footViewHolder;
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){

		int type = getItemViewType(position);
		//Log.i(TAG,"onBindViewHolder: "+position);
		switch(type)
		{
			case -1:
				break;
			case 0:
				TextRectclerAdapter.NormalViewHolder holder1 = (NormalViewHolder)holder;
				//  Log.i(TAG,"onBindViewHolder: "+ textGanioBeanArrayList.get(position).getDesc());
				final TextGanioBean bean = textGanioBeanArrayList.get(position);
				holder1.getDataBinding().setVariable(BR.GanioItem,bean);//绑定数据

				holder1.getDataBinding().setVariable(BR.clickListener,new View.OnClickListener(){
					@Override
					public void onClick(View v){
						Intent intent = new Intent(activity,GanioDetailsActivity.class);
						intent.putExtra(GanioDetailsActivity.TextGANIO_KEY,bean);//传递对象
						context.startActivity(intent);
						//context.startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(activity,v,"newsImage").toBundle());//打开新活动
					}
				});

				holder1.getDataBinding().executePendingBindings();//立即执行改变
				break;
			case 1:
				final TextRectclerAdapter.FootViewHolder footViewHolder = (FootViewHolder)holder;
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
										if(Y - DownY < 0 && ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()==getItemCount()-1) {
											setLoading(true);
											footViewHolder.progressBar.setVisibility(View.VISIBLE);
											footViewHolder.progressText.setText("正在加载...");
											recyclerView.setOnTouchListener(null);//去掉触摸监听
											textFragment.LoadMore(new TextGanioFragment.onLoaderMoreCallBack(){
												@Override
												public void onSuccess(ArrayList<TextGanioBean> beanArrayList){
													//成功，
													setLoading(false);
													footViewHolder.progressBar.setVisibility(View.INVISIBLE);
													footViewHolder.progressText.setText("加载完成...");
													if(beanArrayList.size()==0)
													{
														Toast.makeText(context,"已经到底了噢！",Toast.LENGTH_SHORT).show();
														TextRectclerAdapter.this.notifyDataSetChanged();
														return;
													}
													TextRectclerAdapter.this.textGanioBeanArrayList.addAll(beanArrayList);//添加新的一页

													TextRectclerAdapter.this.notifyDataSetChanged();
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
				//footViewHolder.getDataBinding().executePendingBindings();//立即执行改变
				break;
		}

	}

	View.OnTouchListener touchListener = new View.OnTouchListener(){
		@Override
		public boolean onTouch(View v,MotionEvent event){
			if(!isLoading()) {
				switch(event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						DownY = event.getY();
						return true;
					case MotionEvent.ACTION_UP:
						Y = event.getY();
						if(Y - DownY < 0) {
							setLoading(true);
							recyclerView.setOnTouchListener(null);//去掉触摸监听
							Toast.makeText(context,"上拉刷新",Toast.LENGTH_SHORT).show();
							textFragment.LoadMore(new TextGanioFragment.onLoaderMoreCallBack(){
								@Override
								public void onSuccess(ArrayList<TextGanioBean> beanArrayList){
									//成功，
									setLoading(false);
									if(beanArrayList.size()==0)
									{
										Toast.makeText(context,"已经到底了噢！",Toast.LENGTH_SHORT).show();
										return;
									}
									TextRectclerAdapter.this.textGanioBeanArrayList.addAll(beanArrayList);//添加新的一页

									TextRectclerAdapter.this.notifyDataSetChanged();
								}

								@Override
								public void onError(String error){
									Toast.makeText(context,"刷新失败：" + error,Toast.LENGTH_SHORT).show();
									setLoading(false);
								}
							});
						}
						return true;
				}
			}
			return false;
		}
	};

	@Override
	public int getItemViewType(int position){
		if(position == getItemCount()-1)
		{
			return 1;
		}
		return 0;
	}

	public boolean isLoading(){
		return isLoading;
	}

	public void setLoading(boolean loading){
		isLoading = loading;
	}

	@Override
	public int getItemCount(){
		return textGanioBeanArrayList.size()+1;
	}

	class NormalViewHolder extends RecyclerView.ViewHolder{
		private ViewDataBinding dataBinding;
		TextView desc;
		public NormalViewHolder(View itemView){
			super(itemView);
			desc = (TextView)rootView.findViewById(R.id.desc);
		}
		public void setBinding(ViewDataBinding dataBinding)
		{
			this.dataBinding = dataBinding;
		}
		public ViewDataBinding getDataBinding()
		{
			return this.dataBinding;
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
