package winter.zxb.smilesb101.coderhome.Model;

import java.util.ArrayList;
import java.util.Date;

import winter.zxb.smilesb101.coderhome.Bean.CoderBean;
import winter.zxb.smilesb101.coderhome.Bean.OpenSourceBean;
import winter.zxb.smilesb101.coderhome.Bean.ProgrammAboutBean;
import winter.zxb.smilesb101.coderhome.R;

/**
 * 项目名称：CoderHome
 * 类描述：程序关于的数据获取类
 * 创建人：SmileSB101
 * 创建时间：2017/6/6 0006 20:02
 * 修改人：Administrator
 * 修改时间：2017/6/6 0006 20:02
 * 修改备注：
 */

public class IProgrammAboutModel{
	public static final IProgrammAboutModel I_PROGRAMM_ABOUT_MODEL = new IProgrammAboutModel();
	public interface onAboutInfoListener
	{
		void onSuccess(ProgrammAboutBean programmAboutBean);
		void onError(String error);
	}

	public void getAboutInfo(onAboutInfoListener aboutInfoListener)
	{
		ProgrammAboutBean bean = new ProgrammAboutBean();
		bean.setProgramm_name("码农之家");
		bean.setProgramm_version("0.01");
		bean.setProgramm_desc("很高兴你能看到这里，这个使用干货api(gan.io)以及知乎日报为数据源的IT方面的APP，其中使用了MVP模式，DataBinding，以及Material Designer和一些开源项目完成，虽然功能现在看来还是相对简单，但是其中也有不少的坑，以及一些技术点，希望看到这里的童鞋能够从中获取到一些有用的东西。我也会慢慢的完善一下这个APP");
		bean.setLastWords("如果感觉有用，那么我就恬不知耻的求Star 求 fork，\n git地址:\n https://github.com/WinterSmileSB101/CoderHome");
		ArrayList funcs = new ArrayList();
		funcs.add("IT新闻");
		funcs.add("福利图片");
		funcs.add("知乎日报");
		funcs.add("主题切换（暂时没有使用换肤，也并没有插件化）");
		funcs.add("启动欢迎");
		bean.setProgramm_function(funcs);

		funcs = new ArrayList();
		funcs.add("MVP");
		funcs.add("DataBinding");
		funcs.add("Material Designer");
		bean.setProgramm_tecs(funcs);

		ArrayList<CoderBean> coderBeanArrayList = new ArrayList<>();
		coderBeanArrayList.add(new CoderBean("SmileSB101","https://github.com/WinterSmileSB101",R.mipmap.myheadimg,"http://blog.csdn.net/qq_21265915"));
		bean.setCoderBeanArrayList(coderBeanArrayList);

		ArrayList<OpenSourceBean> openSourceBeen = new ArrayList();
		openSourceBeen.add(new OpenSourceBean("Retrofit2.0","http://square.github.io/retrofit/",R.mipmap.retrofit2));
		openSourceBeen.add(new OpenSourceBean("Glide","https://github.com/bumptech/glide",R.mipmap.glide));
		openSourceBeen.add(new OpenSourceBean("RollViewPager","https://github.com/bumptech/glide",R.mipmap.rollpagerview));
		openSourceBeen.add(new OpenSourceBean("Gson","https://github.com/google/gson",R.mipmap.gson));
		bean.setOpenSourceBeanArrayList(openSourceBeen);

		bean.setProgramm_img(R.mipmap.app);

		bean.setProgramm_pubTime(new Date());

		aboutInfoListener.onSuccess(bean);
	}

}
