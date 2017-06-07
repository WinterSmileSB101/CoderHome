package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.BR;
import winter.zxb.smilesb101.coderhome.Bean.CoderBean;
import winter.zxb.smilesb101.coderhome.Bean.OpenSourceBean;
import winter.zxb.smilesb101.coderhome.R;

/**
 * 项目名称：CoderHome
 * 类描述：关于界面的RV adapter
 * 创建人：SmileSB101
 * 创建时间：2017/6/6 0006 21:17
 * 修改人：Administrator
 * 修改时间：2017/6/6 0006 21:17
 * 修改备注：
 */

public class AboutProgrammRecyclerAdapter extends RecyclerView.Adapter{

	ArrayList<CoderBean> teamList;
	ArrayList<OpenSourceBean> openSourceList;
	int ViewHolderPosition;

	Activity activity;

	public AboutProgrammRecyclerAdapter(ArrayList<CoderBean> teamList,ArrayList<OpenSourceBean> openSourceList,Activity activity,int viewHolderPosition){
		this.teamList = teamList;
		this.openSourceList = openSourceList;
		this.activity = activity;
		this.ViewHolderPosition = viewHolderPosition;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		ViewDataBinding binding = null;
		View view = null;
		switch(ViewHolderPosition)
		{
			case 0:
				binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.develop_team_item_layout,parent,false);
				TeamViewHolder teamViewHolder = new TeamViewHolder(binding.getRoot());
				teamViewHolder.setDataBinding(binding);
				return teamViewHolder;
			case 1:
				binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.open_source_item_layout,parent,false);
				OpenSourceViewHolder openSourceViewHolder = new OpenSourceViewHolder(binding.getRoot());
				openSourceViewHolder.setDataBinding(binding);
				return openSourceViewHolder;
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		switch(ViewHolderPosition)
		{
			case 0:
				TeamViewHolder teamViewHolder = (TeamViewHolder)holder;
				CoderBean team_item = teamList.get(position);
				teamViewHolder.getDataBinding().setVariable(BR.team_item,team_item);
				teamViewHolder.getDataBinding().executePendingBindings();
				teamViewHolder.imageView.setImageResource(team_item.getImg());
				break;
			case 1:
				OpenSourceViewHolder openSourceViewHolder = (OpenSourceViewHolder)holder;
				OpenSourceBean open_item = openSourceList.get(position);
				openSourceViewHolder.getDataBinding().setVariable(BR.source_item,open_item);
				openSourceViewHolder.getDataBinding().executePendingBindings();
				openSourceViewHolder.imageView.setImageResource(open_item.getImg_url());
				break;
		}
	}

	@Override
	public int getItemCount(){
		switch(ViewHolderPosition)
		{
			case 0:
				return teamList.size();
			case 1:
				return openSourceList.size();
		}
		return 0;
	}

	/**
	 * 团队人员的ViewHolder
	 */
	class TeamViewHolder extends RecyclerView.ViewHolder{
		ViewDataBinding dataBinding;
		ImageView imageView;
		public TeamViewHolder(View itemView){
			super(itemView);
			imageView = (ImageView)itemView.findViewById(R.id.item_pic);
		}

		public ViewDataBinding getDataBinding(){
			return dataBinding;
		}

		public void setDataBinding(ViewDataBinding dataBinding){
			this.dataBinding = dataBinding;
		}
	}

	/**
	 * 开源项目的ViewHolder
	 */
	class OpenSourceViewHolder extends RecyclerView.ViewHolder{
		ViewDataBinding dataBinding;
		ImageView imageView;
		public OpenSourceViewHolder(View itemView){
			super(itemView);
			imageView = (ImageView)itemView.findViewById(R.id.item_pic);
		}

		public ViewDataBinding getDataBinding(){
			return dataBinding;
		}

		public void setDataBinding(ViewDataBinding dataBinding){
			this.dataBinding = dataBinding;
		}
	}
}
