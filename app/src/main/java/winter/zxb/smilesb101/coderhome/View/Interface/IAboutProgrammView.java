package winter.zxb.smilesb101.coderhome.View.Interface;

import winter.zxb.smilesb101.coderhome.Bean.ProgrammAboutBean;

/**
 * 项目名称：CoderHome
 * 类描述：程序关于的View接口
 * 创建人：SmileSB101
 * 创建时间：2017/6/6 0006 20:30
 * 修改人：Administrator
 * 修改时间：2017/6/6 0006 20:30
 * 修改备注：
 */

public interface IAboutProgrammView{
	void onSuccess(ProgrammAboutBean programmAboutBean);
	void onError(String error);
}
