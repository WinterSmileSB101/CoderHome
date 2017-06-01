package winter.zxb.smilesb101.coderhome.Bean;

import java.io.Serializable;

/**
 * 项目名称：ViewThisWorld
 * 类描述：知乎日报最新消息故事的Bean类
 * 创建人：SmileSB101
 * 创建时间：2017/5/1 0001 20:46
 * 修改人：Administrator
 * 修改时间：2017/5/1 0001 20:46
 * 修改备注：
 */

public class ZhiHuStoriesBean implements Serializable{
	String title;
	String ga_prefix;
	String[] images;//有可能无
	String type;
	String id;

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getGa_prefix(){
		return ga_prefix;
	}

	public void setGa_prefix(String ga_prefix){
		this.ga_prefix = ga_prefix;
	}

	public String[] getImages(){
		return images;
	}

	public void setImages(String[] images){
		this.images = images;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}
}
