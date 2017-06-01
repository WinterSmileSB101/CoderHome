package winter.zxb.smilesb101.coderhome.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuContentInsideBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuContentOutSideBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuStoriesBean;
import winter.zxb.smilesb101.coderhome.Bean.ZhiHuTopStoriesBean;
import winter.zxb.smilesb101.coderhome.InterfaceAPI.IZhiHuAPI;

/**
 * 项目名称：ViewThisWorld
 * 类描述：知乎的数据Model
 * 创建人：SmileSB101
 * 创建时间：2017/5/4 0004 12:55
 * 修改人：Administrator
 * 修改时间：2017/5/4 0004 12:55
 * 修改备注：
 */

public class IZhiHuModel{
	public static final IZhiHuModel I_ZHI_HU_MODEL = new IZhiHuModel();

	/**
	 * Stories监听
	 */
	public interface StoreisListener{
		void onSuccess(ArrayList<ZhiHuStoriesBean> zhiHuStoriesList);
		void onError(String error);
	}

	/**
	 * TopStories监听
	 */
	public interface TopStoreisListener{
		void onSuccess(ArrayList<ZhiHuTopStoriesBean> zhiHuStoriesList);
		void onError(String error);
	}

	/**
	 * 消息内容获取监听
	 */
	public interface StoriesContentListener{
		void onSuccess(ZhiHuContentInsideBean insideBean);
		void onSuccess(ZhiHuContentOutSideBean outSideBean);
		void onError(String error);
	}

	/**
	 * 获取Storise消息
	 */
	public void getLatestStoriesNews(String param,final StoreisListener linstener)
	{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://news-at.zhihu.com/")
				.build();//实例化Retrofit
		IZhiHuAPI iZhiHuAPI = retrofit.create(IZhiHuAPI.class);//实例化API
		Call<ResponseBody> call = iZhiHuAPI.getNewsCall(param);
		//异步提交请求
		call.enqueue(new Callback<ResponseBody>(){
			@Override
			public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
				//成功
				try {
					Gson gson = new Gson();
					JSONObject object = new JSONObject(response.body().string());
					ArrayList<ZhiHuStoriesBean> beanArrayList = gson.fromJson(object.getString("stories"),new TypeToken<ArrayList<ZhiHuStoriesBean>>(){}.getType());//获取对象

					linstener.onSuccess(beanArrayList);//交给监听处理

				} catch(JSONException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call,Throwable t){
				linstener.onError(t.getMessage());
			}
		});
	}

	/**
	 * 获取TopStorise消息
	 */
	public void getLatestTopStoriesNews(String param,final TopStoreisListener linstener)
	{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://news-at.zhihu.com/")
				.build();//实例化Retrofit
		IZhiHuAPI iZhiHuAPI = retrofit.create(IZhiHuAPI.class);//实例化API
		Call<ResponseBody> call = iZhiHuAPI.getNewsCall(param);
		//异步提交请求
		call.enqueue(new Callback<ResponseBody>(){
			@Override
			public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
				//成功
				try {
					Gson gson = new Gson();
					JSONObject object = new JSONObject(response.body().string());
					ArrayList<ZhiHuTopStoriesBean> beanArrayList = gson.fromJson(object.getString("top_stories"),new TypeToken<ArrayList<ZhiHuTopStoriesBean>>(){}.getType());//获取对象

					linstener.onSuccess(beanArrayList);//交给监听处理

				} catch(JSONException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call,Throwable t){
				linstener.onError(t.getMessage());
			}
		});
	}

	/**
	 * 获取消息内容
	 */
	public void getStoriesContentById(String id,final StoriesContentListener linstener)
	{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://news-at.zhihu.com/")
				.build();//实例化Retrofit
		IZhiHuAPI iZhiHuAPI = retrofit.create(IZhiHuAPI.class);//实例化API
		Call<ResponseBody> call = iZhiHuAPI.getNewsCall(id);
		//异步提交请求
		call.enqueue(new Callback<ResponseBody>(){
			@Override
			public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
				//成功
				try {
					Gson gson = new Gson();
					JSONObject object = new JSONObject(response.body().string());
					if(object.getString("type").equals("0"))
					{
						//内部消息
						ZhiHuContentInsideBean insideBean = gson.fromJson(object.toString(),new TypeToken<ZhiHuContentInsideBean>(){}.getType());//获取对象
						linstener.onSuccess(insideBean);//交给监听处理
					}
					else if(object.getString("type").equals("1"))
					{
						//外站消息
						ZhiHuContentOutSideBean outSideBean = gson.fromJson(object.toString(),new TypeToken<ZhiHuContentOutSideBean>(){}.getType());//获取对象
						linstener.onSuccess(outSideBean);//交给监听处理
					}


				} catch(JSONException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call,Throwable t){
				linstener.onError(t.getMessage());
			}
		});
	}
}
