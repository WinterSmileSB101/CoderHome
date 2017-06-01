package winter.zxb.smilesb101.coderhome.View.Interface;

/**
 * 项目名称：ViewThisWorld
 * 类描述：知乎日报的基类View接口
 * 创建人：SmileSB101
 * 创建时间：2017/5/4 0004 15:05
 * 修改人：Administrator
 * 修改时间：2017/5/4 0004 15:05
 * 修改备注：
 */

public interface IZhiHuFragmentViewBase{
	void showLoading();
	void closeLoading();
	String getType();
	void onFailure(String error);
}
