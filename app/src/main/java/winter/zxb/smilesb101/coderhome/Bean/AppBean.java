package winter.zxb.smilesb101.coderhome.Bean;

import java.util.ArrayList;

/**
 * 项目名称：ViewThisWorld
 * 类描述：应用数据类
 * 创建人：SmileSB101
 * 创建时间：2017/4/26 0026 09:22
 * 修改人：Administrator
 * 修改时间：2017/4/26 0026 09:22
 * 修改备注：
 */

public class AppBean{
	ArrayList<String> channels;

	public ArrayList<String> getChannels(){
		return channels;
	}

	public void setChannels(ArrayList<String> channels){
		this.channels = channels;
	}
	public void setChannels(String channel){
		if(channels==null)
		{
			channels = new ArrayList<String>();
		}
		channels.add(channel);
	}
}
