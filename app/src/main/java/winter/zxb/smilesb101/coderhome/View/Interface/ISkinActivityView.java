package winter.zxb.smilesb101.coderhome.View.Interface;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.SkinBean;

/**
 * 项目名称：CoderHome
 * 类描述：皮肤View接口
 * 创建人：SmileSB101
 * 创建时间：2017/6/3 0003 22:54
 * 修改人：Administrator
 * 修改时间：2017/6/3 0003 22:54
 * 修改备注：
 */

public interface ISkinActivityView{
	void onOfficalSkinBcak(ArrayList<SkinBean> officalSkin);
	void onError(String error);
}
