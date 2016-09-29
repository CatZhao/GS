package cn.zhaoxiaoxue.nsfw.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.apache.struts2.json.annotations.JSON;

import cn.zhaoxiaoxue.core.action.BaseAction;
import cn.zhaoxiaoxue.nsfw.entity.Info;
import cn.zhaoxiaoxue.nsfw.service.InfoService;

public class InfoAction extends BaseAction {
	//容器注入
	private InfoService infoService;
	
	//存储所有(符合条件的）info
	private List<Info> infoList;
	private Info info;
	private Map<String,String> infoTypeMap = Info.INFO_TYPE_MAP;
	
	//分页展示所有数据，去listUI页面
	public String listUI(){
		Map<String,Object> condition = new HashMap<String,Object>();
		if(keyword != null){
			condition.put(" title like ? ", "%"+keyword+"%");
		}
		pr = infoService.getPageResult(pageNo, PAGESIZE,condition);
		return "listUI";
	}
	
	//跳转到添加页面
	public String addUI(){
		//ActionContext.getContext().getContextMap().put("infoTypeMap", info.INFO_TYPE_MAP);
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		return "addUI";
	}
	
	//删除选中的
	public String deleteSelected(){
		if(this.selectedRow != null){
			for(String infoId : selectedRow){
				infoService.delete(infoId);
			}
		}
		return "list";
	}
	
	//修改信息的状态
	public String publicInfo(){
		dataMap = new HashMap<String,Object>();
		try {
			if(info != null && info.getInfoId() != null && info.getState() != null){
				String id = info.getInfoId();
				String state = info.getState();
				info = infoService.findById(id);
				info.setState(state);
				infoService.update(info);
				this.dataMap.put("msg", "success");
			}
		} catch (Exception e) {
			this.dataMap.put("msg", "fail");
		}
		return "json";
	}
	
	//进入编辑页面
	public String editUI(){
		if(info != null && info.getInfoId() != null){
			info = infoService.findById(info.getInfoId());
		}
		return "editUI";
	}
	
	//删除一条
	public String delete(){
		if(info != null && info.getInfoId() != null){
			infoService.delete(info.getInfoId());
		}
		return "list";
	}
	
	//增加一条
	public String add(){
		if(info != null){
			infoService.save(info);
		}
		return "list";
	}
	
	//修改
	public String edit(){
		if(info != null && info.getInfoId() != null){
			infoService.update(info);
		}
		return "list";
	}
	
	/*----------get/set-------------方法*/
	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	@JSON(serialize=false) 
	public List<Info> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}

	@JSON(serialize=false) 
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@JSON(serialize=false) 
	public Map<String, String> getInfoTypeMap() {
		return infoTypeMap;
	}

	public void setInfoTypeMap(Map<String, String> infoTypeMap) {
		this.infoTypeMap = infoTypeMap;
	}
	
}
