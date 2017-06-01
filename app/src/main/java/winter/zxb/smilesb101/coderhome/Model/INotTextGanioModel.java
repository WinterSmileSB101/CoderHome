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
import winter.zxb.smilesb101.coderhome.Bean.NotTextGanioBean;
import winter.zxb.smilesb101.coderhome.InterfaceAPI.IGanioAPI;

/**
 * 项目名称：ViewThisWorld
 * 类描述：除了文字之外的干货Model
 * 创建人：SmileSB101
 * 创建时间：2017/4/28 0028 08:26
 * 修改人：Administrator
 * 修改时间：2017/4/28 0028 08:26
 * 修改备注：
 */

public class INotTextGanioModel{
	private final String TAG = "INotTextGanioModel";
	public static final INotTextGanioModel I_NOT_TEXT_GANIO_MODEL = new INotTextGanioModel();

	/**
	 * 定义外部回调接口
	 */
	public interface NotTextGanioListener{
		void onSuccess(ArrayList<NotTextGanioBean> ganioBeanArrayList);
		void onError(String error);
	}

	/**
	 * 获取所有信息
	 * @param classify 分类
	 * @param count 数量
	 * @param page 页数
	 * @param iGanioListener 监听
	 */
	public void getAllContent(String classify,int count,int page,final NotTextGanioListener iGanioListener)
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

					ArrayList<NotTextGanioBean> ganioBeenLsit = gson.fromJson(js.getString("results"),new TypeToken<ArrayList<NotTextGanioBean>>(){}.getType());//获取到Gson


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

	public void getSearchContent(String classify,final NotTextGanioListener notTextGanioListener)
	{
		getSearchContent(classify,"25","1",notTextGanioListener);
	}

	public void getSearchContent(String classify,String count,String page,final NotTextGanioListener notTextGanioListener)
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

					ArrayList<NotTextGanioBean> ganioBeenLsit = gson.fromJson(js.getString("results"),new TypeToken<ArrayList<NotTextGanioBean>>(){}.getType());//获取到Gson,转换成对象

					//提交给Success处理
					notTextGanioListener.onSuccess(ganioBeenLsit);

				} catch(IOException e) {
					e.printStackTrace();
				} catch(JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call,Throwable t){
				//处理失败的数据
				notTextGanioListener.onError(t.getMessage());
			}
		});
	}
}
