package winter.zxb.smilesb101.coderhome.Bean;

/**
 * 项目名称：CoderHome
 * 类描述：团队码农Bean
 * 创建人：SmileSB101
 * 创建时间：2017/6/7 0007 15:24
 * 修改人：Administrator
 * 修改时间：2017/6/7 0007 15:24
 * 修改备注：
 */

public class CoderBean{
	String name;
	String github;
	int img;
	String blog;

	public CoderBean(String name,String github,int img,String blog){
		this.name = name;
		this.github = github;
		this.img = img;
		this.blog = blog;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getGithub(){
		return github;
	}

	public void setGithub(String github){
		this.github = github;
	}

	public int getImg(){
		return img;
	}

	public void setImg(int img){
		this.img = img;
	}

	public String getBlog(){
		return blog;
	}

	public void setBlog(String blog){
		this.blog = blog;
	}
}
