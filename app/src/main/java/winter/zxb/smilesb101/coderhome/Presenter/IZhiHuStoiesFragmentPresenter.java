package winter.zxb.smilesb101.coderhome.Presenter;

import android.util.Log;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.ZhiHuContentInsideBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuContentOutSideBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuStoriesBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuTopStoriesBean;
import winter.zxb.smilesb101.coderhome.Model.IZhiHuModel;
import winter.zxb.smilesb101.coderhome.View.Interface.IZhiHuDetailsView;
import winter.zxb.smilesb101.coderhome.View.Interface.IZhiHuStoriesFragmentView;


/**
 * 项目名称：ViewThisWorld
 * 类描述：知乎和知乎Stories的桥梁
 * 创建人：SmileSB101
 * 创建时间：2017/5/4 0004 15:50
 * 修改人：Administrator
 * 修改时间：2017/5/4 0004 15:50
 * 修改备注：
 */

public class IZhiHuStoiesFragmentPresenter{
	private String TAG = "IZhiHuPresenter";

	IZhiHuStoriesFragmentView iZhiHuStoriesFragmentView;
	/**
	 * 详情界面的接口
	 */
	IZhiHuDetailsView iZhiHuDetailsView;

	public IZhiHuStoiesFragmentPresenter(IZhiHuStoriesFragmentView iZhiHuStoriesFragmentView){
		this.iZhiHuStoriesFragmentView = iZhiHuStoriesFragmentView;
	}

	/**
	 * 获取消息详情的界面
	 * @param iZhiHuDetailsView 详情的界面接口
	 */
	public IZhiHuStoiesFragmentPresenter(IZhiHuDetailsView iZhiHuDetailsView){
		this.iZhiHuDetailsView = iZhiHuDetailsView;
	}

	/**
	 * 获取最新消息
	 */
	public void getLatestStoies()
	{
		IZhiHuModel.I_ZHI_HU_MODEL.getLatestStoriesNews("latest",new IZhiHuModel.StoreisListener(){
			@Override
			public void onSuccess(ArrayList<ZhiHuStoriesBean> zhiHuStoriesList){
				iZhiHuStoriesFragmentView.showStories(zhiHuStoriesList);
			}

			@Override
			public void onError(String error){
				iZhiHuStoriesFragmentView.onFailure(error);
			}
		});
	}

	/**
	 * 获取最新消息
	 */
	public void getLatestTopStoies()
	{
		IZhiHuModel.I_ZHI_HU_MODEL.getLatestTopStoriesNews("latest",new IZhiHuModel.TopStoreisListener(){

			@Override
			public void onSuccess(ArrayList<ZhiHuTopStoriesBean> zhiHuStoriesList){
				iZhiHuStoriesFragmentView.showTopStories(zhiHuStoriesList);
			}

			@Override
			public void onError(String error){
				iZhiHuStoriesFragmentView.onFailure(error);
			}
		});
	}

	public void getPastNews()
	{
		IZhiHuModel.I_ZHI_HU_MODEL.getPastStories(iZhiHuStoriesFragmentView.getDate(),new IZhiHuModel.StoreisListener(){
			@Override
			public void onSuccess(ArrayList<ZhiHuStoriesBean> zhiHuStoriesList){
				iZhiHuStoriesFragmentView.showStories(zhiHuStoriesList);
			}

			@Override
			public void onError(String error){
				iZhiHuStoriesFragmentView.onFailure(error);
			}
		});
	}

	/**
	 * 获取消息内容
	 */
	public void getStoriesContentById()
	{

		IZhiHuModel.I_ZHI_HU_MODEL.getStoriesContentById(iZhiHuDetailsView.getId(),new IZhiHuModel.StoriesContentListener(){
			@Override
			public void onSuccess(ZhiHuContentInsideBean insideBean){
				Log.i(TAG,"getStoriesContentById: 获取到返回");
				if(insideBean!=null)
				{
					iZhiHuDetailsView.showStoriesDetails(insideBean);
				}
			}

			@Override
			public void onSuccess(ZhiHuContentOutSideBean outSideBean){
				if(outSideBean!=null)
				{
					iZhiHuDetailsView.showStoriesDetails(outSideBean);
				}
			}

			@Override
			public void onError(String error){
				iZhiHuDetailsView.onFailure(error);
			}
		});
	}

}
