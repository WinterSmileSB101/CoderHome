package winter.zxb.smilesb101.coderhome.View.Fragments.GanHuo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.Presenter.IGanioNotTextFragmentPresenter;
import winter.zxb.smilesb101.coderhome.R;
import winter.zxb.smilesb101.coderhome.View.Adapter.ImageRecyclerViewAdapter;
import winter.zxb.smilesb101.coderhome.View.Interface.INotTextGanioFragmentView;
import winter.zxb.smilesb101.coderhome.databinding.ImageganioRecyclerLayoutBinding;

/**
 * 项目名称：ViewThisWorld
 * 类描述：图片干货碎片
 * 创建人：SmileSB101
 * 创建时间：2017/5/3 0003 22:20
 * 修改人：Administrator
 * 修改时间：2017/5/3 0003 22:20
 * 修改备注：
 */

public class ImageGanioFragment extends FragmentBase implements INotTextGanioFragmentView{
	ImageganioRecyclerLayoutBinding binding;
	IGanioNotTextFragmentPresenter igNotextParesenter;
	public static ImageGanioFragment newInstance(){

		Bundle args = new Bundle();

		ImageGanioFragment fragment = new ImageGanioFragment();
		fragment.setArguments(args);
		return fragment;
	}
	public ImageGanioFragment()
	{
		title = "福利";
		titleImage = R.drawable.tab_pic;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
		binding = DataBindingUtil.inflate(inflater,R.layout.imageganio_recycler_layout,container,false);
		rootView = binding.getRoot();
		rootContext = container.getContext();
		RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.recyclerView);
		rv.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false));

		igNotextParesenter = new IGanioNotTextFragmentPresenter(this);
		igNotextParesenter.getAllContent();//获取数据

		return rootView;
	}

	@Override
	public void showLoading(){

	}

	@Override
	public void closeLoding(){

	}

	@Override
	public void onError(String error){

	}

	@Override
	public String getGanioType(){
		return title;
	}

	@Override
	public void showGanio(ArrayList<NotTextGanioBean> ganioBeanArrayList){
		Log.i(TAG,"showGanio: 图片展示："+ganioBeanArrayList.size());
		binding.setAdapter(new ImageRecyclerViewAdapter(ganioBeanArrayList));//显示
	}
}
