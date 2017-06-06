package winter.zxb.smilesb101.coderhome.Model;

import android.graphics.Color;

import java.util.ArrayList;

import winter.zxb.smilesb101.coderhome.Bean.SkinBean;

/**
 * 项目名称：CoderHome
 * 类描述：皮肤Model
 * 创建人：SmileSB101
 * 创建时间：2017/6/3 0003 22:34
 * 修改人：Administrator
 * 修改时间：2017/6/3 0003 22:34
 * 修改备注：
 */

public class ISkinModel{

	public static final ISkinModel I_SKIN_MODEL = new ISkinModel();

	public interface getSkinCallBack{
		void onSucess(ArrayList<SkinBean> skinBeanArrayList);
		void onError(String error);
	}

	public void getSkinList(getSkinCallBack callBack)
	{
		ArrayList<SkinBean> arrayList = new ArrayList<>();
		arrayList.add(new SkinBean("活力红","",Color.RED,"",true));
		arrayList.add(new SkinBean("地域黑","",Color.BLACK,"",false));
		arrayList.add(new SkinBean("原谅绿","",Color.GREEN,"",false));
		callBack.onSucess(arrayList);
	}

}
