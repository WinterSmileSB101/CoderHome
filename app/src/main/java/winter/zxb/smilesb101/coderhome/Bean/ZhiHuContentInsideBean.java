package winter.zxb.smilesb101.coderhome.Bean;

import java.io.Serializable;

/**
 * 项目名称：ViewThisWorld
 * 类描述：知乎站内消息内容Bean（type 为 0 的时候）
 * 创建人：SmileSB101
 * 创建时间：2017/5/1 0001 22:10
 * 修改人：Administrator
 * 修改时间：2017/5/1 0001 22:10
 * 修改备注：
 */

public class ZhiHuContentInsideBean implements Serializable{
	String body;
	String image_source;
	String title;
	String image;
	String share_url;
	String[] js;
	String recommenders;
	String ga_prefix;
	String section;
	String type;
	String id;
	String[] css;

	public String getBody(){
		return body;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getImage_source(){
		return image_source;
	}

	public void setImage_source(String image_source){
		this.image_source = image_source;
	}

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

	public String getShare_url(){
		return share_url;
	}

	public void setShare_url(String share_url){
		this.share_url = share_url;
	}

	public String[] getJs(){
		return js;
	}

	public void setJs(String[] js){
		this.js = js;
	}

	public String getRecommenders(){
		return recommenders;
	}

	public void setRecommenders(String recommenders){
		this.recommenders = recommenders;
	}

	public String getGa_prefix(){
		return ga_prefix;
	}

	public void setGa_prefix(String ga_prefix){
		this.ga_prefix = ga_prefix;
	}

	public String getSection(){
		return section;
	}

	public void setSection(String section){
		this.section = section;
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

	public String[] getCss(){
		return css;
	}

	public void setCss(String[] css){
		this.css = css;
	}
}
