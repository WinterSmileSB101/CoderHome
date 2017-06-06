package winter.zxb.smilesb101.coderhome.View.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import winter.zxb.smilesb101.coderhome.View.Utils.StaticUtils;

/**
 * 项目名称：CoderHome
 * 类描述：可以改变主题的基类活动
 * 创建人：SmileSB101
 * 创建时间：2017/6/4 0004 09:08
 * 修改人：Administrator
 * 修改时间：2017/6/4 0004 09:08
 * 修改备注：
 */

public class ThemeBaseActivity extends AppCompatActivity{
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState){
		StaticUtils.STATIC_UTILS.setTheme(this);
		super.onCreate(savedInstanceState);

	}
}
