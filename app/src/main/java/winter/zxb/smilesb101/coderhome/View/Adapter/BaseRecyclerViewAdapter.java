package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * 项目名称：CoderHome
 * 类描述：基类的RecyclerView Adapter
 * 创建人：SmileSB101
 * 创建时间：2017/6/5 0005 15:46
 * 修改人：Administrator
 * 修改时间：2017/6/5 0005 15:46
 * 修改备注：
 */

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter{
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){

	}

	@Override
	public int getItemCount(){
		return 0;
	}

	public int getViewType(int pos,int listSize)
	{
		if(pos==listSize+1)
		{
			return 1;
		}
		return 0;
	}

}
