package cn.zhaoxiaoxue.nsfw.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Info entity. @author MyEclipse Persistence Tools
 */

public class Info implements java.io.Serializable {

	// Fields

	private String infoId;
	private String type;
	private String source;
	private String title;
	private String content;
	private String memo;
	private String creater;
	private Timestamp createTime;
	private String state;

	// Constant
	public static String INFO_STATE_PUBLIC = "1";
	public static String INFO_STATE_STOP = "0";
	public static String INFO_TYPE_TZGG ="tzgg";
	public static String INFO_TYPE_ZCSD = "zcsd";
	public static String INFO_TYPE_NSZD = "nszd";
	public static Map<String,String> INFO_TYPE_MAP;
	static{
		INFO_TYPE_MAP = new HashMap<String,String>();
		INFO_TYPE_MAP.put(INFO_TYPE_TZGG, "通知公告");
		INFO_TYPE_MAP.put(INFO_TYPE_ZCSD, "政策速递");
		INFO_TYPE_MAP.put(INFO_TYPE_NSZD, "纳税指导");
	}
	// Constructors

	/** default constructor */
	public Info() {
	}

	/** minimal constructor */
	public Info(String type, String title, String creater,
			Timestamp createTime, String state) {
		this.type = type;
		this.title = title;
		this.creater = creater;
		this.createTime = createTime;
		this.state = state;
	}

	/** full constructor */
	public Info(String type, String source, String title, String content,
			String memo, String creater, Timestamp createTime, String state) {
		this.type = type;
		this.source = source;
		this.title = title;
		this.content = content;
		this.memo = memo;
		this.creater = creater;
		this.createTime = createTime;
		this.state = state;
	}

	// Property accessors

	public String getInfoId() {
		return this.infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}