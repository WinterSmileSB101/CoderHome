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
import winter.zxb.smilesb101.coderhome.Bean.TextGanioBean;
import winter.zxb.smilesb101.coderhome.InterfaceAPI.IGanioAPI;

/**
 * 项目名称：ViewThisWorld
 * 类描述：干货的Model
 * 创建人：SmileSB101
 * 创建时间：2017/4/25 0025 21:00
 * 修改人：Administrator
 * 修改时间：2017/4/25 0025 21:00
 * 修改备注：
 */

public class ITextGanioModel{

	private final String TAG = "ITextGanioModel";
	public final static ITextGanioModel I_GANIO_MODEL  = new ITextGanioModel();

	/**
	 * 定义外部监听接口
	 *
	 */
	public interface IGanioListener{
		void onSuccess(ArrayList<TextGanioBean> ganioList);
		void onError(String error);
	}

	/**
	 * 获取所有信息
	 * @param classify 分类
	 * @param count 数量
	 * @param page 页数
	 * @param iGanioListener 监听
	 */
	public void getAllContent(String classify,int count,int page,final IGanioListener iGanioListener)
	{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://gank.io/")
				.build();//实例化retrofit
		IGanioAPI ganioAPI = retrofit.create(IGanioAPI.class);//实例化API
		Call<ResponseBody> call = ganioAPI.allContentCall(classify,count,page);//获取到Call
		//异步提交请求
		call.enqueue(new Callback<ResponseBody>(){
			@Override
			public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
				//处理成功的数据

				try {
					Gson gson = new Gson();
					JSONObject js = new JSONObject(response.body().string());

					ArrayList<TextGanioBean> ganioBeenLsit = gson.fromJson(js.getString("results"),new TypeToken<ArrayList<TextGanioBean>>(){}.getType());//获取到Gson


					//提交给Success处理
					iGanioListener.onSuccess(ganioBeenLsit);

				} catch(IOException e) {
					e.printStackTrace();
				} catch(JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call,Throwable t){
				//处理失败的数据
				iGanioListener.onError(t.getMessage());
			}
		});
	}

	public void getSearchContent(String classify,final IGanioListener iGanioListener)
	{
		getSearchContent(classify,"25","1",iGanioListener);
	}

	public void getSearchContent(String classify,String count,String page,final IGanioListener iGanioListener)
	{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://gank.io/")
				.build();//实例化retrofit
		IGanioAPI ganioAPI = retrofit.create(IGanioAPI.class);//实例化API
		Call<ResponseBody> call = ganioAPI.searchCall(classify,count,page);//获取到Call
		//异步提交请求
		call.enqueue(new Callback<ResponseBody>(){
			@Override
			public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
				//处理成功的数据

				try {
					Gson gson = new Gson();
					JSONObject js = new JSONObject(response.body().string());

					ArrayList<TextGanioBean> ganioBeenLsit = gson.fromJson(js.getString("results"),new TypeToken<ArrayList<TextGanioBean>>(){}.getType());//获取到Gson


					//提交给Success处理
					iGanioListener.onSuccess(ganioBeenLsit);

				} catch(IOException e) {
					e.printStackTrace();
				} catch(JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call,Throwable t){
				//处理失败的数据
				iGanioListener.onError(t.getMessage());
			}
		});
	}

}
