package com.android.sunning.riskpatrol.entity.generate;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Datum {
	private int id;
	@Expose
	private String NormalID;
	@Expose
	private JianChaFuZeRenC JianChaFuZeRenC;
	@Expose
	private Integer InspectType;
	@Expose
	private Integer InspectWay;
	@Expose
	private Object InspectTimeArranged;
	@Expose
	private String InspectWayTxt;
	@Expose
	private com.android.sunning.riskpatrol.entity.generate.Site Site;
	@Expose
	private JianChaXiangMu JianChaXiangMu;
	@Expose
	private String InspectNum;
	@Expose
	private String InspectNumFormat;
	@Expose
	private Company Company;
	@Expose
	private String InspectTime;
	@Expose
	private String JianChaFuZeRen;
	@Expose
	private ShouJianFuZeRen ShouJianFuZeRen;
	@Expose
	private String CanJianRenYuan;
	@Expose
	private String CanJiaRenYuanInput;
	@Expose
	private String BeiZhu;
	@Expose
	private Object LastReViewStatus;
	@Expose
	private Integer AttachmentCount;
	@Expose
	private String CreateTime;
	@Expose
	private String CreatorID;
	@Expose
	private String CreatorName;
	@Expose
	private Integer Status;
	@Expose
	private Integer CheckListStatus;
	@Expose
	private Integer SubCount;
	@Expose
	private String ReformStatusTxt;
	@Expose
	private String CheckListStatusTxt;
	@Expose
	private Object InspectDiscription;
	@Expose
	private Object CheckResultRemark;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return The NormalID
	 */
	public String getNormalID() {
		return NormalID;
	}

	/**
	 * 
	 * @param NormalID
	 *            The NormalID
	 */
	public void setNormalID(String NormalID) {
		this.NormalID = NormalID;
	}

	/**
	 * 
	 * @return The JianChaFuZeRenC
	 */
	public com.android.sunning.riskpatrol.entity.generate.JianChaFuZeRenC getJianChaFuZeRenC() {
		return JianChaFuZeRenC;
	}

	/**
	 * 
	 * @param JianChaFuZeRenC
	 *            The JianChaFuZeRenC
	 */
	public void setJianChaFuZeRenC(
			com.android.sunning.riskpatrol.entity.generate.JianChaFuZeRenC JianChaFuZeRenC) {
		this.JianChaFuZeRenC = JianChaFuZeRenC;
	}

	/**
	 * 
	 * @return The InspectType
	 */
	public Integer getInspectType() {
		return InspectType;
	}

	/**
	 * 
	 * @param InspectType
	 *            The InspectType
	 */
	public void setInspectType(Integer InspectType) {
		this.InspectType = InspectType;
	}

	/**
	 * 
	 * @return The InspectWay
	 */
	public Integer getInspectWay() {
		return InspectWay;
	}

	/**
	 * 
	 * @param InspectWay
	 *            The InspectWay
	 */
	public void setInspectWay(Integer InspectWay) {
		this.InspectWay = InspectWay;
	}

	/**
	 * 
	 * @return The InspectTimeArranged
	 */
	public Object getInspectTimeArranged() {
		return InspectTimeArranged;
	}

	/**
	 * 
	 * @param InspectTimeArranged
	 *            The InspectTimeArranged
	 */
	public void setInspectTimeArranged(Object InspectTimeArranged) {
		this.InspectTimeArranged = InspectTimeArranged;
	}

	/**
	 * 
	 * @return The InspectWayTxt
	 */
	public String getInspectWayTxt() {
		return InspectWayTxt;
	}

	/**
	 * 
	 * @param InspectWayTxt
	 *            The InspectWayTxt
	 */
	public void setInspectWayTxt(String InspectWayTxt) {
		this.InspectWayTxt = InspectWayTxt;
	}

	/**
	 * 
	 * @return The Site
	 */
	public Site getSite() {
		return Site;
	}

	/**
	 * 
	 * @param Site
	 *            The Site
	 */
	public void setSite(Site Site) {
		this.Site = Site;
	}

	/**
	 * 
	 * @return The JianChaXiangMu
	 */
	public JianChaXiangMu getJianChaXiangMu() {
		return JianChaXiangMu;
	}

	/**
	 * 
	 * @param JianChaXiangMu
	 *            The JianChaXiangMu
	 */
	public void setJianChaXiangMu(JianChaXiangMu JianChaXiangMu) {
		this.JianChaXiangMu = JianChaXiangMu;
	}

	/**
	 * 
	 * @return The InspectNum
	 */
	public String getInspectNum() {
		return InspectNum;
	}

	/**
	 * 
	 * @param InspectNum
	 *            The InspectNum
	 */
	public void setInspectNum(String InspectNum) {
		this.InspectNum = InspectNum;
	}

	/**
	 * 
	 * @return The InspectNumFormat
	 */
	public String getInspectNumFormat() {
		return InspectNumFormat;
	}

	/**
	 * 
	 * @param InspectNumFormat
	 *            The InspectNumFormat
	 */
	public void setInspectNumFormat(String InspectNumFormat) {
		this.InspectNumFormat = InspectNumFormat;
	}

	/**
	 * 
	 * @return The Company
	 */
	public com.android.sunning.riskpatrol.entity.generate.Company getCompany() {
		return Company;
	}

	/**
	 * 
	 * @param Company
	 *            The Company
	 */
	public void setCompany(
			com.android.sunning.riskpatrol.entity.generate.Company Company) {
		this.Company = Company;
	}

	/**
	 * 
	 * @return The InspectTime
	 */
	public String getInspectTime() {
		return InspectTime;
	}

	/**
	 * 
	 * @param InspectTime
	 *            The InspectTime
	 */
	public void setInspectTime(String InspectTime) {
		this.InspectTime = InspectTime;
	}

	/**
	 * 
	 * @return The JianChaFuZeRen
	 */
	public String getJianChaFuZeRen() {
		return JianChaFuZeRen;
	}

	/**
	 * 
	 * @param JianChaFuZeRen
	 *            The JianChaFuZeRen
	 */
	public void setJianChaFuZeRen(String JianChaFuZeRen) {
		this.JianChaFuZeRen = JianChaFuZeRen;
	}

	/**
	 * 
	 * @return The ShouJianFuZeRen
	 */
	public com.android.sunning.riskpatrol.entity.generate.ShouJianFuZeRen getShouJianFuZeRen() {
		return ShouJianFuZeRen;
	}

	/**
	 * 
	 * @param ShouJianFuZeRen
	 *            The ShouJianFuZeRen
	 */
	public void setShouJianFuZeRen(
			com.android.sunning.riskpatrol.entity.generate.ShouJianFuZeRen ShouJianFuZeRen) {
		this.ShouJianFuZeRen = ShouJianFuZeRen;
	}

	/**
	 * 
	 * @return The CanJianRenYuan
	 */
	public String getCanJianRenYuan() {
		return CanJianRenYuan;
	}

	/**
	 * 
	 * @param CanJianRenYuan
	 *            The CanJianRenYuan
	 */
	public void setCanJianRenYuan(String CanJianRenYuan) {
		this.CanJianRenYuan = CanJianRenYuan;
	}

	/**
	 * 
	 * @return The CanJiaRenYuanInput
	 */
	public String getCanJiaRenYuanInput() {
		return CanJiaRenYuanInput;
	}

	/**
	 * 
	 * @param CanJiaRenYuanInput
	 *            The CanJiaRenYuanInput
	 */
	public void setCanJiaRenYuanInput(String CanJiaRenYuanInput) {
		this.CanJiaRenYuanInput = CanJiaRenYuanInput;
	}

	/**
	 * 
	 * @return The BeiZhu
	 */
	public String getBeiZhu() {
		return BeiZhu;
	}

	/**
	 * 
	 * @param BeiZhu
	 *            The BeiZhu
	 */
	public void setBeiZhu(String BeiZhu) {
		this.BeiZhu = BeiZhu;
	}

	/**
	 * 
	 * @return The LastReViewStatus
	 */
	public Object getLastReViewStatus() {
		return LastReViewStatus;
	}

	/**
	 * 
	 * @param LastReViewStatus
	 *            The LastReViewStatus
	 */
	public void setLastReViewStatus(Object LastReViewStatus) {
		this.LastReViewStatus = LastReViewStatus;
	}

	/**
	 * 
	 * @return The AttachmentCount
	 */
	public Integer getAttachmentCount() {
		return AttachmentCount;
	}

	/**
	 * 
	 * @param AttachmentCount
	 *            The AttachmentCount
	 */
	public void setAttachmentCount(Integer AttachmentCount) {
		this.AttachmentCount = AttachmentCount;
	}

	/**
	 * 
	 * @return The CreateTime
	 */
	public String getCreateTime() {
		return CreateTime;
	}

	/**
	 * 
	 * @param CreateTime
	 *            The CreateTime
	 */
	public void setCreateTime(String CreateTime) {
		this.CreateTime = CreateTime;
	}

	/**
	 * 
	 * @return The CreatorID
	 */
	public String getCreatorID() {
		return CreatorID;
	}

	/**
	 * 
	 * @param CreatorID
	 *            The CreatorID
	 */
	public void setCreatorID(String CreatorID) {
		this.CreatorID = CreatorID;
	}

	/**
	 * 
	 * @return The CreatorName
	 */
	public String getCreatorName() {
		return CreatorName;
	}

	/**
	 * 
	 * @param CreatorName
	 *            The CreatorName
	 */
	public void setCreatorName(String CreatorName) {
		this.CreatorName = CreatorName;
	}

	/**
	 * 
	 * @return The Status
	 */
	public Integer getStatus() {
		return Status;
	}

	/**
	 * 
	 * @param Status
	 *            The Status
	 */
	public void setStatus(Integer Status) {
		this.Status = Status;
	}

	/**
	 * 
	 * @return The CheckListStatus
	 */
	public Integer getCheckListStatus() {
		return CheckListStatus;
	}

	/**
	 * 
	 * @param CheckListStatus
	 *            The CheckListStatus
	 */
	public void setCheckListStatus(Integer CheckListStatus) {
		this.CheckListStatus = CheckListStatus;
	}

	/**
	 * 
	 * @return The SubCount
	 */
	public Integer getSubCount() {
		return SubCount;
	}

	/**
	 * 
	 * @param SubCount
	 *            The SubCount
	 */
	public void setSubCount(Integer SubCount) {
		this.SubCount = SubCount;
	}

	/**
	 * 
	 * @return The ReformStatusTxt
	 */
	public String getReformStatusTxt() {
		return ReformStatusTxt;
	}

	/**
	 * 
	 * @param ReformStatusTxt
	 *            The ReformStatusTxt
	 */
	public void setReformStatusTxt(String ReformStatusTxt) {
		this.ReformStatusTxt = ReformStatusTxt;
	}

	/**
	 * 
	 * @return The CheckListStatusTxt
	 */
	public String getCheckListStatusTxt() {
		return CheckListStatusTxt;
	}

	/**
	 * 
	 * @param CheckListStatusTxt
	 *            The CheckListStatusTxt
	 */
	public void setCheckListStatusTxt(String CheckListStatusTxt) {
		this.CheckListStatusTxt = CheckListStatusTxt;
	}

	/**
	 * 
	 * @return The InspectDiscription
	 */
	public Object getInspectDiscription() {
		return InspectDiscription;
	}

	/**
	 * 
	 * @param InspectDiscription
	 *            The InspectDiscription
	 */
	public void setInspectDiscription(Object InspectDiscription) {
		this.InspectDiscription = InspectDiscription;
	}

	/**
	 * 
	 * @return The CheckResultRemark
	 */
	public Object getCheckResultRemark() {
		return CheckResultRemark;
	}

	/**
	 * 
	 * @param CheckResultRemark
	 *            The CheckResultRemark
	 */
	public void setCheckResultRemark(Object CheckResultRemark) {
		this.CheckResultRemark = CheckResultRemark;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(NormalID).append(JianChaFuZeRenC)
				.append(InspectType).append(InspectWay)
				.append(InspectTimeArranged).append(InspectWayTxt).append(Site)
				.append(JianChaXiangMu).append(InspectNum)
				.append(InspectNumFormat).append(Company).append(InspectTime)
				.append(JianChaFuZeRen).append(ShouJianFuZeRen)
				.append(CanJianRenYuan).append(CanJiaRenYuanInput)
				.append(BeiZhu).append(LastReViewStatus)
				.append(AttachmentCount).append(CreateTime).append(CreatorID)
				.append(CreatorName).append(Status).append(CheckListStatus)
				.append(SubCount).append(ReformStatusTxt)
				.append(CheckListStatusTxt).append(InspectDiscription)
				.append(CheckResultRemark).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Datum) == false) {
			return false;
		}
		Datum rhs = ((Datum) other);
		return new EqualsBuilder().append(NormalID, rhs.NormalID)
				.append(JianChaFuZeRenC, rhs.JianChaFuZeRenC)
				.append(InspectType, rhs.InspectType)
				.append(InspectWay, rhs.InspectWay)
				.append(InspectTimeArranged, rhs.InspectTimeArranged)
				.append(InspectWayTxt, rhs.InspectWayTxt)
				.append(Site, rhs.Site)
				.append(JianChaXiangMu, rhs.JianChaXiangMu)
				.append(InspectNum, rhs.InspectNum)
				.append(InspectNumFormat, rhs.InspectNumFormat)
				.append(Company, rhs.Company)
				.append(InspectTime, rhs.InspectTime)
				.append(JianChaFuZeRen, rhs.JianChaFuZeRen)
				.append(ShouJianFuZeRen, rhs.ShouJianFuZeRen)
				.append(CanJianRenYuan, rhs.CanJianRenYuan)
				.append(CanJiaRenYuanInput, rhs.CanJiaRenYuanInput)
				.append(BeiZhu, rhs.BeiZhu)
				.append(LastReViewStatus, rhs.LastReViewStatus)
				.append(AttachmentCount, rhs.AttachmentCount)
				.append(CreateTime, rhs.CreateTime)
				.append(CreatorID, rhs.CreatorID)
				.append(CreatorName, rhs.CreatorName)
				.append(Status, rhs.Status)
				.append(CheckListStatus, rhs.CheckListStatus)
				.append(SubCount, rhs.SubCount)
				.append(ReformStatusTxt, rhs.ReformStatusTxt)
				.append(CheckListStatusTxt, rhs.CheckListStatusTxt)
				.append(InspectDiscription, rhs.InspectDiscription)
				.append(CheckResultRemark, rhs.CheckResultRemark).isEquals();
	}

}
