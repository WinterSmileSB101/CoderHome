package winter.zxb.smilesb101.coderhome.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.SkinBean;
import winter.zxb.smilesb101.coderhome.Bean.StaticBean.StaticValue;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Utils.StaticUtils;

/**
 * 项目名称：CoderHome
 * 类描述：皮肤rv adapter
 * 创建人：SmileSB101
 * 创建时间：2017/6/2 0002 22:20
 * 修改人：Administrator
 * 修改时间：2017/6/2 0002 22:20
 * 修改备注：
 */

public class SkinRecyclerViewAdapter extends RecyclerView.Adapter{
	ArrayList<SkinBean> skinBeanArrayList;
	Context context;
	Activity activity;

	public SkinRecyclerViewAdapter(ArrayList<SkinBean> skinBeanArrayList,Activity activity){
		this.skinBeanArrayList = skinBeanArrayList;
		this.activity = activity;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		context = parent.getContext();
		View view = LayoutInflater.from(context)
				.inflate(R.layout.skin_rv_item,parent,false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
		final SkinBean skinBean = skinBeanArrayList.get(position);
		MyViewHolder myViewHolder = (MyViewHolder)holder;
		if(skinBean.is_chose())
		{
			myViewHolder.chose_text.setText("试用中");
			myViewHolder.chose_img.setVisibility(View.VISIBLE);
		}
		else
		{
			myViewHolder.chose_text.setText("未使用");
		}

		myViewHolder.skin_bg.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				//更新ui

				//按下就换肤，保存信息到sharerefeerence中

				SharedPreferences.Editor editor = context.getSharedPreferences(StaticValue.SKIN_CONFIG_NAME,Context.MODE_PRIVATE).edit();
				editor.remove(StaticValue.SKIN_NAME);
				editor.remove(StaticValue.SKIN_CHANGE);
				editor.putBoolean(StaticValue.SKIN_CHANGE,true);
				editor.putString(StaticValue.SKIN_NAME,skinBean.getSkin_name());
				editor.apply();
				editor.commit();

				StaticUtils.STATIC_UTILS.setTheme(activity);
				activity.recreate();
			}
		});

		myViewHolder.skin_name.setText(skinBean.getSkin_name());
		if(!skinBean.getSkin_img().equals("")) {
			Glide.with(context)
					.load(skinBean.getSkin_img())
					.into(myViewHolder.skin_bg);
		}
		else
		{
			myViewHolder.skin_bg.setBackgroundColor(skinBean.getSkin_color());
		}
	}

	@Override
	public int getItemCount(){
		return skinBeanArrayList.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder{

		ImageView skin_bg;
		ImageView chose_img;
		TextView skin_name;
		TextView chose_text;

		public MyViewHolder(View itemView){
			super(itemView);
			skin_bg = (ImageView)itemView.findViewById(R.id.skin_bg);
			chose_img = (ImageView)itemView.findViewById(R.id.chose_img);
			skin_name = (TextView)itemView.findViewById(R.id.skin_name);
			chose_text = (TextView)itemView.findViewById(R.id.chose_text);
		}
	}
}
