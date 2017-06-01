package winter.zxb.smilesb101.coderhome.Bean;

import java.io.Serializable;

/**
 * 项目名称：ViewThisWorld
 * 类描述：文字干货的Bean类
 * 创建人：SmileSB101
 * 创建时间：2017/4/25 0025 08:27
 * 修改人：Administrator
 * 修改时间：2017/4/25 0025 08:27
 * 修改备注：
 */

public class TextGanioBean implements Serializable{
	private String desc;
	private String ganhuo_id;
	private String publishedAt;
	private String readability;
	private String type;
	private String url;
	private String who;

	public String getDesc(){
		return desc;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getGanhuo_id(){
		return ganhuo_id;
	}

	public void setGanhuo_id(String ganhuo_id){
		this.ganhuo_id = ganhuo_id;
	}

	public String getPublishedAt(){
		//格式化数据,去掉T之后的
		return publishedAt.substring(0,publishedAt.indexOf('T'));
	}

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getReadability(){
		return readability;
	}

	public void setReadability(String readability){
		this.readability = readability;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getWho(){
		return who;
	}

	public void setWho(String who){
		this.who = who;
	}
}
