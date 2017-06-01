package winter.zxb.smilesb101.coderhome.View.Interface;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;


/**
 * 项目名称：ViewThisWorld
 * 类描述：文字的View接口
 * 创建人：SmileSB101
 * 创建时间：2017/4/26 0026 11:09
 * 修改人：Administrator
 * 修改时间：2017/4/26 0026 11:09
 * 修改备注：
 */

public interface ITextGanioFragmentView extends IGanioFragmentView{

	void showGanio(ArrayList<TextGanioBean> textGanioBeanArrayList);
}
