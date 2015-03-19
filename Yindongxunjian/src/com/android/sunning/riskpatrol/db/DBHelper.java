package com.android.sunning.riskpatrol.db;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;

import com.android.sunning.riskpatrol.entity.E_Login;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.entity.generate.HomeEntity;
import com.android.sunning.riskpatrol.entity.generate.RiskElement;
import com.android.sunning.riskpatrol.entity.generate.RiskElements;
import com.android.sunning.riskpatrol.entity.generate.login.Login;
import com.android.sunning.riskpatrol.entity.generate.login.Site;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * Created by sunning on 14-8-14.
 */
public class DBHelper {

	public DbUtils dbUtils;

	private static DBHelper dbHelper;

	private DBHelper(Context context) {
		dbUtils = DbUtils.create(context);
		dbUtils.getDaoConfig().setDbName("riskPatrol.db");
	}

	public static synchronized DBHelper getDbHelperInstance(Context context) {
		if (dbHelper == null) {
			dbHelper = new DBHelper(context);
		}
		return dbHelper;
	}

	public void save(Object object) {
		if (object != null) {
			try {
				dbUtils.save(object);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveRiskElement(RiskElements riskElements) {
		if (riskElements != null) {
			if (!TextUtils.isEmpty(riskElements.getUpdateTime())) {
				save(riskElements);
			}
			if (riskElements.getRiskElements().size() > 0) {
				for (RiskElement element : riskElements.getRiskElements())
					save(element);
			}
		}
	}

	/**
	 * 存储登录信息
	 * 
	 * @param login
	 */
	public void saveLogin(Login login) {
		if (login != null) {
			Login eLogin = queryCurrentLogin();
			if (eLogin == null) {
				save(login);
			} else {
				removeLogin();
				save(login);
			}
		}
	}

	public void removeLogin() {
		try {
			dbUtils.deleteAll(E_Login.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public Login queryCurrentLogin() {
		Login login = null;
		try {
			login = dbUtils.findFirst(Login.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return login;
	}
	public String queryCurrentList() {
		String list = null;
		try {
			list = dbUtils.findFirst(String.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void saveSites(List<Site> sites) {
		if (sites != null) {
			if (sites.size() > 0) {
				List<Site> dbSite = querySites();
				if (dbSite != null) {
					delSites(dbSite);
				}
				for (Site site : sites)
					save(site);
			}
		}
	}

	public void delSites(List<Site> sites) {
		try {
			dbUtils.deleteAll(sites);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	public void saveHomeEntity(HomeEntity homeEntity) {
		if (homeEntity != null) {
			HomeEntity e_homeEntity = queryCurrentHomeEntity();
			if (e_homeEntity != null) {
				try {
					dbUtils.deleteAll(HomeEntity.class);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		save(homeEntity);

	}

	public HomeEntity queryCurrentHomeEntity() {
		// TODO Auto-generated method stub
		HomeEntity homeEntity = null;
		try {
			homeEntity = dbUtils.findFirst(HomeEntity.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return homeEntity;
	}

	public void saveDatum(List<Datum> datums) {
		if (datums != null) {
			if (datums.size() > 0) {
				List<Datum> dbDatum = queryDatum();
				if (dbDatum != null) {
					delDatum(dbDatum);
				}
				for (Datum datum : datums)
					save(datum);
			}
		}
	}

	public void delDatum(List<Datum> Datums) {
		try {
			dbUtils.deleteAll(Datums);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public List<Site> querySites() {
		List<Site> siteList = null;
		try {
			siteList = dbUtils.findAll(Site.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return siteList;
	}

	public List<Datum> queryDatum() {
		List<Datum> datumList = null;
		try {
			datumList = dbUtils.findAll(Datum.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return datumList;
	}

	// /**
	// * 保存Video对象
	// */
	// public void saveVideo(BUpload bUpload) {
	// try {
	// List<BUpload> entity =
	// queryVideoByVideoId(bUpload.userID,bUpload.videoId) ;
	// if (entity == null || entity.size() == 0) {
	// dbUtils.save(bUpload);
	// }
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// }
	//
	//
	// /**
	// * 根据视频id查询视频。
	// * @param videoId
	// * @return
	// */
	// public List<BUpload> queryVideoByVideoId(String userId,String videoId) {
	// List<BUpload> entity = null;
	// try {
	// WhereBuilder whereBuilder =WhereBuilder.b("userId", "=", userId);
	// whereBuilder.and("videoId", "=", videoId);
	// entity =
	// dbUtils.findAll(Selector.from(BUpload.class).where(whereBuilder));
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// return entity;
	// }
	//
	// /**
	// * 根据ID查询Video对象
	// *
	// * @param id
	// * @return
	// */
	// public BUpload queryVideoById(String id) {
	// BUpload entity = null;
	// try {
	// entity = dbUtils.findById(BUpload.class, id);
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// return entity;
	// }
	//
	// public List<BUpload> getAllVideoByUserId(String userId) {
	// List<BUpload> list = null;
	// try {
	// list = dbUtils.findAll(Selector.from(BUpload.class).where("userId", "=",
	// userId));
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// return list;
	// }
	//
	// public void delAllVideoByUserId(String userId) {
	// try {
	// List<BUpload> dbList = getAllVideoByUserId(userId);
	// for (BUpload b : dbList) {
	// dbUtils.deleteById(BUpload.class, b.id);
	// }
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public List<BUpload> getAllVideo() {
	// List<BUpload> list = null;
	// try {
	// list = dbUtils.findAll(BUpload.class);
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// return list;
	// }
	//
	//
	// public void delAllVideo() {
	// try {
	// dbUtils.deleteAll(BUpload.class);
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public boolean delAllVideoByVideoId(String videoId) {
	// boolean isSuccess = false;
	// try {
	// dbUtils.delete(Selector.from(BUpload.class).where("videoId", "=",
	// videoId));
	// isSuccess = true;
	// } catch (DbException e) {
	// e.printStackTrace();
	// isSuccess = false;
	// }
	// return isSuccess;
	// }
	//
	// public boolean delCurrentTask(BUpload bUpload) {
	// boolean isSuccess = false;
	// try {
	// dbUtils.deleteById(BUpload.class, bUpload);
	// isSuccess = true;
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// return isSuccess;
	// }
	//
	// public void updataVideo(BUpload bUpload) {
	// try {
	// dbUtils.replace(bUpload);
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void dropTable() {
	// try {
	// dbUtils.dropTable(BUpload.class);
	// } catch (DbException e) {
	// e.printStackTrace();
	// }
	// }

}
