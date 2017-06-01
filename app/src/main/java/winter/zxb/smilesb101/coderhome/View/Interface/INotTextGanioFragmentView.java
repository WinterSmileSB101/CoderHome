package winter.zxb.smilesb101.coderhome.View.Interface;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;

/**
 * 项目名称：ViewThisWorld
 * 类描述：非文字的干货信息显示类
 * 创建人：SmileSB101
 * 创建时间：2017/4/28 0028 08:10
 * 修改人：Administrator
 * 修改时间：2017/4/28 0028 08:10
 * 修改备注：
 */

public interface INotTextGanioFragmentView extends IGanioFragmentView{
	void showGanio(ArrayList<NotTextGanioBean> ganioBeanArrayList);
}
