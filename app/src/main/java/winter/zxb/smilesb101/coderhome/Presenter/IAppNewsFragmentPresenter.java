package winter.zxb.smilesb101.coderhome.Presenter;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Model.IAppModel;
import winter.zxb.smilesb101.coderhome.View.Interface.IAppFragmentView;


/**
 * 项目名称：ViewThisWorld
 * 类描述：App的桥梁
 * 创建人：SmileSB101
 * 创建时间：2017/4/27 0027 16:16
 * 修改人：Administrator
 * 修改时间：2017/4/27 0027 16:16
 * 修改备注：
 */

public class IAppNewsFragmentPresenter{
	IAppFragmentView iAppFragmentView;
	public IAppNewsFragmentPresenter(IAppFragmentView iAppFragmentView)
	{
		this.iAppFragmentView = iAppFragmentView;
	}

	/**
	 * 获取频道列表
	 * @return
	 */
	public ArrayList<String> getChannels()
	{
		return IAppModel.I_APP_MODEL.getChannel();
	}

}
