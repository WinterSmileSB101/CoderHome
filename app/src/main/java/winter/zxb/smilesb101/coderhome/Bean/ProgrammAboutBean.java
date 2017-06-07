package winter.zxb.smilesb101.coderhome.Bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * 项目名称：CoderHome
 * 类描述：程序的关于Bean类
 * 创建人：SmileSB101
 * 创建时间：2017/6/6 0006 19:54
 * 修改人：Administrator
 * 修改时间：2017/6/6 0006 19:54
 * 修改备注：
 */

public class ProgrammAboutBean{
	String programm_name;
	int programm_img;
	String programm_version;
	String programm_desc;
	Date programm_pubTime;
	ArrayList<OpenSourceBean> openSourceBeanArrayList;
	ArrayList<CoderBean> coderBeanArrayList;
	ArrayList<String> programm_function;
	ArrayList<String> programm_tecs;

	public ProgrammAboutBean(){
	}

	public ProgrammAboutBean(String programm_name,String programm_version,String programm_desc,Date programm_pubTime){
		this.programm_name = programm_name;
		this.programm_version = programm_version;
		this.programm_desc = programm_desc;
		this.programm_pubTime = programm_pubTime;
	}

	public String getProgramm_name(){
		return programm_name;
	}

	public void setProgramm_name(String programm_name){
		this.programm_name = programm_name;
	}

	public String getProgramm_version(){
		return programm_version;
	}

	public void setProgramm_version(String programm_version){
		this.programm_version = programm_version;
	}

	public String getProgramm_desc(){
		return programm_desc;
	}

	public void setProgramm_desc(String programm_desc){
		this.programm_desc = programm_desc;
	}

	public Date getProgramm_pubTime(){
		return programm_pubTime;
	}

	public void setProgramm_pubTime(Date programm_pubTime){
		this.programm_pubTime = programm_pubTime;
	}

	public ArrayList<OpenSourceBean> getOpenSourceBeanArrayList(){
		return openSourceBeanArrayList;
	}

	public void setOpenSourceBeanArrayList(ArrayList<OpenSourceBean> openSourceBeanArrayList){
		this.openSourceBeanArrayList = openSourceBeanArrayList;
	}

	public ArrayList<CoderBean> getCoderBeanArrayList(){
		return coderBeanArrayList;
	}

	public void setCoderBeanArrayList(ArrayList<CoderBean> coderBeanArrayList){
		this.coderBeanArrayList = coderBeanArrayList;
	}

	public ArrayList<String> getProgramm_function(){
		return programm_function;
	}

	public void setProgramm_function(ArrayList<String> programm_function){
		this.programm_function = programm_function;
	}

	public ArrayList<String> getProgramm_tecs(){
		return programm_tecs;
	}

	public void setProgramm_tecs(ArrayList<String> programm_tecs){
		this.programm_tecs = programm_tecs;
	}

	public int getProgramm_img(){
		return programm_img;
	}

	public void setProgramm_img(int programm_img){
		this.programm_img = programm_img;
	}
}
