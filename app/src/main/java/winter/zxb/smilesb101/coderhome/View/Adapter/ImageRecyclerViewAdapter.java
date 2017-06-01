package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.R;

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

	public ImageRecyclerViewAdapter(ArrayList<NotTextGanioBean> list)
	{
		this.ganioBeanArrayList = list;
		if(list == null)
		{
			this.ganioBeanArrayList = new ArrayList<>();
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		//Log.i(TAG,"onCreateViewHolder: 创建绑定");
		ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.image_recyclerview_item_layout,parent,false);
		MyViewHoler holer = new MyViewHoler(binding.getRoot());
		holer.setBinding(binding);
		context = parent.getContext();
		return holer;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		//Log.i(TAG,"onBindViewHolder: 图片URL："+ganioBeanArrayList.get(position).getUrl());
		final MyViewHoler holer1 = (MyViewHoler)holder;
		Glide.with(context)
				.load(ganioBeanArrayList.get(position).getUrl())
				.into(holer1.imageView);
		/*holer1.getBinding().setVariable(BR.item,ganioBeanArrayList.get(position));
		holer1.getBinding().setVariable(BR.clickListener,new View.OnClickListener(){
			@Override
			public void onClick(View v){
				//打开图片查看器，这里先粗略做出个放大到手机全屏
			}
		});*/
		holer1.getBinding().executePendingBindings();
	}

	@Override
	public int getItemCount(){
		Log.i(TAG,"getItemCount: 数量："+ganioBeanArrayList.size());
		return ganioBeanArrayList.size();
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
}
