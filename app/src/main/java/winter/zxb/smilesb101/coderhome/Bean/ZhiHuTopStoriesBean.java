package winter.zxb.smilesb101.coderhome.Bean;

import java.io.Serializable;

/**
 * 项目名称：ViewThisWorld
 * 类描述：知乎最新消息轮播消息
 * 创建人：SmileSB101
 * 创建时间：2017/5/1 0001 21:29
 * 修改人：Administrator
 * 修改时间：2017/5/1 0001 21:29
 * 修改备注：
 */

public class ZhiHuTopStoriesBean implements Serializable{
	String title;
	String image;
	String ga_prefix;
	String type;
	String id;

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getImage(){
		return image;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getGa_prefix(){
		return ga_prefix;
	}

	public void setGa_prefix(String ga_prefix){
		this.ga_prefix = ga_prefix;
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
