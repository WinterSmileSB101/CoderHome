package winter.zxb.smilesb101.coderhome.Presenter;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.Model.INotTextGanioModel;
import winter.zxb.smilesb101.coderhome.View.Interface.INotTextGanioFragmentView;


/**
 * 项目名称：ViewThisWorld
 * 类描述：除了文字的干货桥梁
 * 创建人：SmileSB101
 * 创建时间：2017/4/28 0028 08:23
 * 修改人：Administrator
 * 修改时间：2017/4/28 0028 08:23
 * 修改备注：
 */

public class IGanioNotTextFragmentPresenter{
	INotTextGanioFragmentView iNotTextGanioFragmentView;
	public IGanioNotTextFragmentPresenter(INotTextGanioFragmentView iNotTextGanioFragmentView)
	{
		this.iNotTextGanioFragmentView = iNotTextGanioFragmentView;
	}

	public void getAllContent()
	{
		iNotTextGanioFragmentView.showLoading();//显示加载中
		INotTextGanioModel.I_NOT_TEXT_GANIO_MODEL.getAllContent(iNotTextGanioFragmentView.getGanioType(),25,1,new INotTextGanioModel.NotTextGanioListener(){
			@Override
			public void onSuccess(ArrayList<NotTextGanioBean> ganioBeanArrayList){
				iNotTextGanioFragmentView.closeLoding();
				iNotTextGanioFragmentView.showGanio(ganioBeanArrayList);
			}

			@Override
			public void onError(String error){
				iNotTextGanioFragmentView.closeLoding();
				iNotTextGanioFragmentView.onError(error);
			}
		});
	}

	public void getSearchGanio()
	{
		iNotTextGanioFragmentView.showLoading();
		INotTextGanioModel.I_NOT_TEXT_GANIO_MODEL.getSearchContent(iNotTextGanioFragmentView.getGanioType(),new INotTextGanioModel.NotTextGanioListener(){
			@Override
			public void onSuccess(ArrayList<NotTextGanioBean> ganioBeanArrayList){
				iNotTextGanioFragmentView.closeLoding();
				iNotTextGanioFragmentView.showGanio(ganioBeanArrayList);
			}

			@Override
			public void onError(String error){
				iNotTextGanioFragmentView.closeLoding();
				iNotTextGanioFragmentView.onError(error);
			}
		});
	}
}
