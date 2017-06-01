package winter.zxb.smilesb101.coderhome.View.Interface;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.ZhiHuStoriesBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuTopStoriesBean;


/**
 * 项目名称：ViewThisWorld
 * 类描述：
 * 创建人：SmileSB101
 * 创建时间：2017/5/4 0004 15:37
 * 修改人：Administrator
 * 修改时间：2017/5/4 0004 15:37
 * 修改备注：
 */

public interface IZhiHuStoriesFragmentView extends IZhiHuFragmentViewBase{
	void showStories(ArrayList<ZhiHuStoriesBean> zhiHuStoriesBeanArrayList);
	void showTopStories(ArrayList<ZhiHuTopStoriesBean> topStoriesBeanArrayList);
}
