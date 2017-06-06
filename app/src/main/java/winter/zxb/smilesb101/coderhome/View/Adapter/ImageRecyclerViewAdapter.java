package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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

import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.ImageGanioFragment;
import winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo.TextGanioFragment;

/**
 * 项目名称：ViewThisWorld
 * 类描述：图片的recyclerView的Adapter
 * 创建人：SmileSB101
 * 创建时间：2017/5/3 0003 21:27
 * 修改人：Administrator
 * 修改时间：2017/5/3 0003 21:27
 * 修改备注：
 */

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter{
	private final String TAG = " ImageRecycler";
	ArrayList<NotTextGanioBean> ganioBeanArrayList;
	Context context;

	Activity activity;
	ImageGanioFragment imageGanioFragment;
	RecyclerView recyclerView;
	boolean isLoading;
	View rootView;

	float DownY,Y;

	public ImageRecyclerViewAdapter(ArrayList<NotTextGanioBean> list,ImageGanioFragment fragment,RecyclerView recyclerView)
	{
		this.ganioBeanArrayList = list;
		if(list == null)
		{
			this.ganioBeanArrayList = new ArrayList<>();
		}
		activity = fragment.getActivity();
		imageGanioFragment = fragment;
		this.recyclerView = recyclerView;
		isLoading = false;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		//Log.i(TAG,"onCreateViewHolder: 创建绑定");
		context = parent.getContext();
		ViewDataBinding dataBinding = null;
		switch(viewType)
		{
			case -1://头部
				return null;
			case 0://中间正常部分
				dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.image_recyclerview_item_layout,parent,false);//Databinding的绑定实例化
				rootView = dataBinding.getRoot();
				MyViewHoler viewHolder = new MyViewHoler(rootView);
				viewHolder.setBinding(dataBinding);//传递到holder中进行绑定
				return viewHolder;
			case 1://底部
				dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.recycler_view_footer,parent,false);//Databinding的绑定实例化
				rootView = dataBinding.getRoot();
				FootViewHolder footViewHolder = new FootViewHolder(rootView);
				footViewHolder.setDataBinding(dataBinding);//传递到holder中进行绑定
				return footViewHolder;
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		//Log.i(TAG,"onBindViewHolder: 图片URL："+ganioBeanArrayList.get(position).getUrl());
		int type = getItemViewType(position);
		switch(type)
		{
			case -1:
				break;
			case 0:
				final MyViewHoler holer1 = (MyViewHoler)holder;
				Glide.with(context)
						.load(ganioBeanArrayList.get(position).getUrl())
						.into(holer1.imageView);
				holer1.getBinding().setVariable(BR.item,ganioBeanArrayList.get(position));
				holer1.getBinding().setVariable(BR.clickListener,new View.OnClickListener(){
					@Override
					public void onClick(View v){
						//打开图片查看器，这里先粗略做出个放大到手机全屏
						Toast.makeText(context,"显示大图，敬请期待",Toast.LENGTH_SHORT).show();
					}
				});
				holer1.getBinding().executePendingBindings();
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
										if(Y - DownY < 0 && ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()==getItemCount()-1) {
											setLoading(true);
											footViewHolder.progressBar.setVisibility(View.VISIBLE);
											footViewHolder.progressText.setText("正在加载...");
											recyclerView.setOnTouchListener(null);//去掉触摸监听
											imageGanioFragment.LoadMore(new ImageGanioFragment.onLoadMoreCallBack(){
												@Override
												public void onSuccess(ArrayList<NotTextGanioBean> beanArrayList){
													//成功，
													setLoading(false);
													footViewHolder.progressBar.setVisibility(View.INVISIBLE);
													footViewHolder.progressText.setText("加载完成...");
													if(beanArrayList.size()==0)
													{
														Toast.makeText(context,"已经到底了噢！",Toast.LENGTH_SHORT).show();
														ImageRecyclerViewAdapter.this.notifyDataSetChanged();
														return;
													}
													ImageRecyclerViewAdapter.this.ganioBeanArrayList.addAll(beanArrayList);//添加新的一页

													ImageRecyclerViewAdapter.this.notifyDataSetChanged();
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
		Log.i(TAG,"getItemCount: 数量："+ganioBeanArrayList.size());
		return ganioBeanArrayList.size()+1;
	}

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

	class MyViewHoler extends RecyclerView.ViewHolder{
		ViewDataBinding binding;
		ImageView imageView;
		View rootView;
		public MyViewHoler(View itemView){
			super(itemView);
			rootView = itemView;
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
