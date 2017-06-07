package winter.zxb.smilesb101.coderhome.Bean;

/**
 * 项目名称：CoderHome
 * 类描述：开源项目Bean
 * 创建人：SmileSB101
 * 创建时间：2017/6/7 0007 15:22
 * 修改人：Administrator
 * 修改时间：2017/6/7 0007 15:22
 * 修改备注：
 */

public class OpenSourceBean{
	String name;
	String url;
	int img_url;

	public OpenSourceBean(String name,String url,int img_url){
		this.name = name;
		this.url = url;
		this.img_url = img_url;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public int getImg_url(){
		return img_url;
	}

	public void setImg_url(int img_url){
		this.img_url = img_url;
	}
}
