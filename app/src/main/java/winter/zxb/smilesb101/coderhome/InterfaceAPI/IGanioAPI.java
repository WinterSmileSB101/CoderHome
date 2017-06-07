package winter.zxb.smilesb101.coderhome.InterfaceAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 项目名称：ViewThisWorld
 * 类描述：干货集中营API
 * 创建人：SmileSB101
 * 创建时间：2017/4/25 0025 20:21
 * 修改人：Administrator
 * 修改时间：2017/4/25 0025 20:21
 * 修改备注：
 */

public interface IGanioAPI{
	/**
	 * 获取所有干货内容
	 * @param classify 分类
	 * @param count 数量
	 * @param page 页数
	 * @return
	 */
	@GET("api/data/{class}/{count}/{page}")
	Call<ResponseBody> allContentCall(@Path("class") String classify,@Path("count") int count,@Path("page") int page);

/**
 * 搜索API
 * http://gank.io/
 */
@GET("api/search/query/listview/category/{class}/count/{count}/page/{page}")
Call<ResponseBody> searchCall(@Path("class") String classify,@Path("count") String count,@Path("page") String page);
//https://gank.io/api/add2gank?url="baidu.com"&desc="asdasd"&who="891532752"&type="iOS"&debug=true
	@GET("api/add2gank")
	Call<ResponseBody> addCall(@Query("url") String url,@Query("desc") String desc,@Query("who") String who,@Query("type") String type,@Query("debug") boolean debug);
}
