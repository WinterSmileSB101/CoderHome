package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.R;


/**
 * 项目名称：ViewThisWorld
 * 类描述：不是文字的RecyclerView的Adapter
 * 创建人：SmileSB101
 * 创建时间：2017/4/28 0028 07:26
 * 修改人：Administrator
 * 修改时间：2017/4/28 0028 07:26
 * 修改备注：
 */

public class NotTextRecyclerAdapter extends RecyclerView.Adapter{

	ArrayList<NotTextGanioBean> beanArrayList;
	View rootView;
	Context context;
	static final String TAG = "NotTextRecyclerAdapter";

	public NotTextRecyclerAdapter(ArrayList<NotTextGanioBean> beanArrayList){
		this.beanArrayList = beanArrayList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		context = parent.getContext();
		ViewDataBinding dataBinding = DataBindingUtil.inflate(
			LayoutInflater.from(context),R.layout.nottext_item_layout,parent,false);//Databinding的绑定实例化
		rootView = parent.getRootView();
		myViewHolder holder = new myViewHolder(rootView);
		holder.setDataBinding(dataBinding);//把绑定设置进holder中，以便获取
		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		myViewHolder holder1 = (myViewHolder)holder;
		//holder1.getDataBinding().setVariable(BR.item,beanArrayList.get(position));//把布局中的变量绑定数据
		holder1.getDataBinding().executePendingBindings();//立即执行改变
	}

	@Override
	public int getItemCount(){
		return beanArrayList.size();
	}

	class myViewHolder extends RecyclerView.ViewHolder{
		ViewDataBinding dataBinding;
		public myViewHolder(View itemView){
			super(itemView);
		}

		public ViewDataBinding getDataBinding(){
			return dataBinding;
		}

		public void setDataBinding(ViewDataBinding dataBinding){
			this.dataBinding = dataBinding;
		}
	}
}
