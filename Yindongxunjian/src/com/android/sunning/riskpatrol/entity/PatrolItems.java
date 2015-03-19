package com.android.sunning.riskpatrol.entity;

/**
 * Created by sunning on 15/2/17.
 * {
 "ItemID": "00000000-0000-0000-0000-000000000000",
 "FengBu": "",
 "ItemName": "happy",
 "CanDelete": false,
 "PatrolStatus": 0,
 "Attachements": null,
 "ProblemItems": null
 }
 */
public class PatrolItems {
    public String getItemID() {
		return ItemID;
	}
	public void setItemID(String itemID) {
		ItemID = itemID;
	}
	public String getFengBu() {
		return FengBu;
	}
	public void setFengBu(String fengBu) {
		FengBu = fengBu;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public boolean isCanDelete() {
		return CanDelete;
	}
	public void setCanDelete(boolean canDelete) {
		CanDelete = canDelete;
	}
	public int getPatrolStatus() {
		return PatrolStatus;
	}
	public void setPatrolStatus(int patrolStatus) {
		PatrolStatus = patrolStatus;
	}
	public String ItemID ;
    public String FengBu ;
    public String ItemName ;
    public boolean CanDelete ;
    public int PatrolStatus ;
    //TODO 还有两个值没定义
    /**
     * Attachements": null,
     "ProblemItems": null
     */
}
