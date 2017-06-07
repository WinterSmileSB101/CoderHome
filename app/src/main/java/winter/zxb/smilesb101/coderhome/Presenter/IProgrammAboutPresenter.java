package winter.zxb.smilesb101.coderhome.Presenter;

import winter.zxb.smilesb101.coderhome.Bean.ProgrammAboutBean;
import winter.zxb.smilesb101.coderhome.Model.IAppModel;
import winter.zxb.smilesb101.coderhome.Model.IProgrammAboutModel;
import winter.zxb.smilesb101.coderhome.View.Interface.IAboutProgrammView;

/**
 * 项目名称：CoderHome
 * 类描述：程序关于的桥梁
 * 创建人：SmileSB101
 * 创建时间：2017/6/6 0006 20:28
 * 修改人：Administrator
 * 修改时间：2017/6/6 0006 20:28
 * 修改备注：
 */

public class IProgrammAboutPresenter{
	IAboutProgrammView iAboutProgrammView;

	public IProgrammAboutPresenter(IAboutProgrammView iAboutProgrammView){
		this.iAboutProgrammView = iAboutProgrammView;
	}

	public void getAboutInfo()
	{
		IProgrammAboutModel.I_PROGRAMM_ABOUT_MODEL.getAboutInfo(new IProgrammAboutModel.onAboutInfoListener(){
			@Override
			public void onSuccess(ProgrammAboutBean programmAboutBean){
				iAboutProgrammView.onSuccess(programmAboutBean);
			}

			@Override
			public void onError(String error){
				iAboutProgrammView.onError(error);
			}
		});
	}
}
