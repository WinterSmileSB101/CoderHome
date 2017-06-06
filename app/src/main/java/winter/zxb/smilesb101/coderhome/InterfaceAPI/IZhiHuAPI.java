package winter.zxb.smilesb101.coderhome.InterfaceAPI;

import android.support.v7.widget.RecyclerView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 项目名称：ViewThisWorld
 * 类描述：知乎数据获取的API（retro）
 * 创建人：SmileSB101
 * 创建时间：2017/5/4 0004 13:26
 * 修改人：Administrator
 * 修改时间：2017/5/4 0004 13:26
 * 修改备注：
 */

public interface IZhiHuAPI{
	/**
	 * 内容API
	 * http://news-at.zhihu.com/api/4/news/latest
	 * 最新参数是 latest
	 * 获取谋篇文章为文章id
	 */
	@GET("api/4/news/{param}")
	Call<ResponseBody> getNewsCall(@Path("param") String param);

	@GET("api/4/news/before/{date}")
	Call<ResponseBody> getPastNews(@Path("date") String date);
}
