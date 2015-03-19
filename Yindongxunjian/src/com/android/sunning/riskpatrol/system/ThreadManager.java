package com.android.sunning.riskpatrol.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: ThreadManager
 * @Description: 全程线程管理类，实现线程池的初始化、线程池的销毁，该类的作用是在离开或者退出动作发生时直接销毁线程，节省资源和流量。
 */
public class ThreadManager {
	
	 private HashMap<String, ExecutorService> threadContainer;
	 
	 private static ThreadManager threadManager;
	 
	 private ThreadManager(){}
	 
     private static synchronized void syncInit() {
   		if (threadManager == null) {
   			threadManager = new ThreadManager();
   			threadManager.threadContainer = new HashMap<String, ExecutorService>();
   		}
   	}
     
  /** 
   * @Description: 获取ThreadManager单例
   * @return ThreadManager   
   */
   public static ThreadManager getThreadManager() {
   		if (threadManager == null) {
   			syncInit();
   		}
   		return threadManager;
   }
   
   /**
	* @Description: 初始化类的线程池以及线程池同时运行的线程个数 
	* @param @param clas
	* @param @param threadNum
	* @return void   
	*/
   public void initES(String clas,int threadNum){
	  ExecutorService executorService = threadContainer.get(clas);
 	  if(executorService == null){
 		  executorService = Executors.newFixedThreadPool(threadNum);
 		  threadContainer.put(clas, executorService);
 	  }
   }
   
   private ExecutorService getESByClass(String clas){
	   return threadContainer.get(clas);
   }
   
   /** 
	* @Description submit a runnable to ThreadPool
	* @param  clas class name
	* @param runnable 
	* @return void   
	*/
   public void submitRunnable(String clas,Runnable runnable){
	   ExecutorService executorService = getESByClass(clas);
	   if(executorService==null){
		   initES(clas,2);
		   executorService = getESByClass(clas);
	   }
	   executorService.execute(runnable);
   }
   
   /**
	* @Description: 从map中移除ExecutorService，并关闭正在运行的所有线程 
	* @param @param clas
	* @return void   
	*/
    public void removeES(String clas){
	   ExecutorService executorService = threadContainer.get(clas);
	   if(executorService!=null){
		   if(!executorService.isShutdown()){
			   executorService.shutdown();
			   executorService = null;
		   }
		   threadContainer.remove(clas);
	   }
	  
   }
    
   /**
	* @Description: 销毁线程管理类
	* @param 
	* @return void   
	*/
    public void destroyThreadManager(){
	   Iterator<String> it = threadContainer.keySet().iterator();
 	   while(it.hasNext()){
 		 String clas = it.next();
 		 removeES(clas);
 	   }
 	   threadContainer.clear();
 	   threadContainer = null;
 	   threadManager = null;
   }
}
