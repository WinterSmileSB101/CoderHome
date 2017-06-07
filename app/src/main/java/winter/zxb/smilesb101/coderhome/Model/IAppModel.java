package winter.zxb.smilesb101.coderhome.Model;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.AppBean;

/**
 * 项目名称：ViewThisWorld
 * 类描述：App的Model
 * 创建人：SmileSB101
 * 创建时间：2017/4/26 0026 09:51
 * 修改人：Administrator
 * 修改时间：2017/4/26 0026 09:51
 * 修改备注：
 */

public class IAppModel{
	public final static IAppModel I_APP_MODEL = new IAppModel();
    AppBean appBean;

	public IAppModel(){
		appBean = new AppBean();
		ArrayList<String> list = new ArrayList<>();
		list.add("all");
		list.add("Android");
		list.add("iOS");
		//list.add("休息视频");
		//list.add("福利");
		list.add("拓展资源");
		list.add("前端");
		list.add("瞎推荐");
		list.add("App");
		//list.add("知乎日报");
		appBean.setChannels(list);
	}

	/**
	 * 获取频道
	 * @return
	 */
	public ArrayList<String> getChannel()
	{
		return appBean.getChannels();
	}
}
