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
import android.widget.TextView;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Activitys.GanioDetailsActivity;
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

	public TextRectclerAdapter(ArrayList<TextGanioBean> list,Activity activity)
	{
		//Log.i(TAG,"TextRectclerAdapter: 创建RecAdapter");
		this.activity = activity;
		this.textGanioBeanArrayList = list;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		//Log.i(TAG,"onCreateViewHolder: 创建ViewHolder："+textGanioBeanArrayList.size());
		context = parent.getContext();
		ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.text_item_layout,parent,false);//Databinding的绑定实例化
		rootView = dataBinding.getRoot();
		TextRectclerAdapter.viewHolder viewHolder = new viewHolder(rootView);
		viewHolder.setBinding(dataBinding);//传递到holder中进行绑定
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		TextRectclerAdapter.viewHolder holder1 = (viewHolder)holder;
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
	}

	@Override
	public int getItemCount(){
		return textGanioBeanArrayList.size();
	}

	class viewHolder extends RecyclerView.ViewHolder{
		private ViewDataBinding dataBinding;
		TextView desc;
		public viewHolder(View itemView){
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
}
