package winter.zxb.smilesb101.coderhome.View.Interface;


import winter.zxb.smilesb101.coderhome.Bean.ZhiHuContentInsideBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuContentOutSideBean;

/**
 * 项目名称：ViewThisWorld
 * 类描述：知乎详情的接口
 * 创建人：SmileSB101
 * 创建时间：2017/5/4 0004 22:49
 * 修改人：Administrator
 * 修改时间：2017/5/4 0004 22:49
 * 修改备注：
 */

public interface IZhiHuDetailsView extends IZhiHuFragmentViewBase{
	void showStoriesDetails(ZhiHuContentInsideBean insideBean);
	void showStoriesDetails(ZhiHuContentOutSideBean outSideBean);
	String getId();
}
