package winter.zxb.smilesb101.coderhome.View.Interface;

/**
 * 项目名称：ViewThisWorld
 * 类描述：干货碎片的Bean接口类
 * 创建人：SmileSB101
 * 创建时间：2017/4/28 0028 08:08
 * 修改人：Administrator
 * 修改时间：2017/4/28 0028 08:08
 * 修改备注：
 */

public interface IGanioFragmentView{
	void showLoading();
	void closeLoding();
	void onError(String error);
	String getGanioType();
}
