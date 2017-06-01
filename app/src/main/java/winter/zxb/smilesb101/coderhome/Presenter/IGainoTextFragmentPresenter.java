package winter.zxb.smilesb101.coderhome.Presenter;

import android.util.Log;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.Model.ITextGanioModel;
import winter.zxb.smilesb101.coderhome.View.Interface.ITextGanioFragmentView;


/**
 * 项目名称：ViewThisWorld
 * 类描述：干货的桥梁
 * 创建人：SmileSB101
 * 创建时间：2017/4/26 0026 08:26
 * 修改人：Administrator
 * 修改时间：2017/4/26 0026 08:26
 * 修改备注：
 */

public class IGainoTextFragmentPresenter{

	ITextGanioFragmentView iTextGanioFragmentView;

	static final String TAG = "IGanioTextFragmentPre";

	public IGainoTextFragmentPresenter(ITextGanioFragmentView iTextGanioFragmentView){
		this.iTextGanioFragmentView = iTextGanioFragmentView;
	}

	public void getAllContent()
	{
		iTextGanioFragmentView.showLoading();//显示加载中
		ITextGanioModel.I_GANIO_MODEL.getAllContent(iTextGanioFragmentView.getGanioType(),25,1,new ITextGanioModel.IGanioListener(){
			@Override
			public void onSuccess(ArrayList<TextGanioBean> ganioList){
				Log.i(TAG,"onSuccess: 获取干货成功类型："+ iTextGanioFragmentView.getGanioType());
				iTextGanioFragmentView.closeLoding();//加载结束
				iTextGanioFragmentView.showGanio(ganioList);//调用显示干货
			}

			@Override
			public void onError(String error){
				Log.i(TAG,"onSuccess: 获取干货失败类型："+ iTextGanioFragmentView.getGanioType());
				iTextGanioFragmentView.closeLoding();//加载结束
				iTextGanioFragmentView.onError(error);//显示错误信息
			}
		});
	}

	/**
	 * 获取干货的搜索内容
	 */
	public void getGanioSearch()
	{
		iTextGanioFragmentView.showLoading();//显示加载中
		ITextGanioModel.I_GANIO_MODEL.getSearchContent(iTextGanioFragmentView.getGanioType(),new ITextGanioModel.IGanioListener(){
			@Override
			public void onSuccess(ArrayList<TextGanioBean> ganioList){
				Log.i(TAG,"onSuccess: 获取干货成功类型："+ iTextGanioFragmentView.getGanioType());
				iTextGanioFragmentView.closeLoding();//加载结束
				iTextGanioFragmentView.showGanio(ganioList);//调用显示干货
			}

			@Override
			public void onError(String error){
				Log.i(TAG,"onSuccess: 获取干货失败类型："+ iTextGanioFragmentView.getGanioType());
				iTextGanioFragmentView.closeLoding();//加载结束
				iTextGanioFragmentView.onError(error);//显示错误信息
			}
		});
	}
}
