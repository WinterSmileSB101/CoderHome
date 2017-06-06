package winter.zxb.smilesb101.coderhome.Presenter;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.SkinBean;
import winter.zxb.smilesb101.coderhome.Model.ISkinModel;
import winter.zxb.smilesb101.coderhome.View.Interface.ISkinActivityView;

/**
 * 项目名称：CoderHome
 * 类描述：皮肤 活动转换器
 * 创建人：SmileSB101
 * 创建时间：2017/6/3 0003 23:18
 * 修改人：Administrator
 * 修改时间：2017/6/3 0003 23:18
 * 修改备注：
 */

public class ISkinActivityPresenter{

	ISkinActivityView iSkinActivityView;

	public ISkinActivityPresenter(ISkinActivityView iSkinActivityView){
		this.iSkinActivityView = iSkinActivityView;
	}

	public void getSkinList()
	{
		ISkinModel.I_SKIN_MODEL.getSkinList(new ISkinModel.getSkinCallBack(){
			@Override
			public void onSucess(ArrayList<SkinBean> skinBeanArrayList){
				iSkinActivityView.onOfficalSkinBcak(skinBeanArrayList);
			}

			@Override
			public void onError(String error){
				iSkinActivityView.onError(error);
			}
		});
	}
}
