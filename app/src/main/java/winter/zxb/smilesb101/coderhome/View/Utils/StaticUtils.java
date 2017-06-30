package winter.zxb.smilesb101.coderhome.View.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;

import winter.zxb.smilesb101.coderhome.Bean.StaticBean.StaticValue;
import winter.zxb.smilesb101.coderhome.R;

/**
 * 项目名称：CoderHome
 * 类描述：静态view 工具类
 * 创建人：SmileSB101
 * 创建时间：2017/6/4 0004 08:41
 * 修改人：Administrator
 * 修改时间：2017/6/4 0004 08:41
 * 修改备注：
 */

public class StaticUtils{

	public static final StaticUtils STATIC_UTILS = new StaticUtils();

	String TAG;

	public StaticUtils()
	{
		TAG = "StaticUtils";
	}

	public void setTheme(Activity activity)
	{
		SharedPreferences sharedPreferences = activity.getSharedPreferences(StaticValue.SKIN_CONFIG_NAME,Context.MODE_PRIVATE);
		boolean isChange = sharedPreferences.getBoolean(StaticValue.SKIN_CHANGE,false);
		if(isChange)
		{
			String name = sharedPreferences.getString(StaticValue.SKIN_NAME,"");
			switch(name) {
				case "":
				case "活力红":
					//默认主题
					activity.setTheme(R.style.DayTheme);
					break;
				case "地域黑":
					//夜间主题
					activity.setTheme(R.style.NightTheme);
					break;
				case "原谅绿":
					//夜间主题
					activity.setTheme(R.style.GreenTheme);
					break;
			}
		}
		else{
			//默认主题
			activity.setTheme(R.style.DayTheme);
		}
	}

	public Context getTheme(Fragment fragment)
	{
		Activity activity = fragment.getActivity();
		SharedPreferences sharedPreferences = activity.getSharedPreferences(StaticValue.SKIN_CONFIG_NAME,Context.MODE_PRIVATE);
		boolean isChange = sharedPreferences.getBoolean(StaticValue.SKIN_CHANGE,false);
		if(isChange)
		{
			String name = sharedPreferences.getString(StaticValue.SKIN_NAME,"");
			switch(name) {
				case "":
				case "活力红":
					//默认主题
					return new ContextThemeWrapper(activity,R.style.DayTheme);
				case "地域黑":
					//夜间主题
					return new ContextThemeWrapper(activity,R.style.NightTheme);
				case "原谅绿":
					//夜间主题
					return new ContextThemeWrapper(activity,R.style.GreenTheme);
			}
		}
		return new ContextThemeWrapper(activity,R.style.DayTheme);//此处也返回默认主题（解决首次安装出现的BUG，由于没有配置文件的存在，所以直接崩溃的问题）
	}

}
