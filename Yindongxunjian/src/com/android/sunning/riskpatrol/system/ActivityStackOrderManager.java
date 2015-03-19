
/*
 * Copyright (C) 2007 The Android  Source Project
 *
 * Licensed under the RichenInfo License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.richeninfo.com/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.android.sunning.riskpatrol.system;

import java.util.Stack;

import android.util.SparseArray;

/**
 * @Description: Activity顺序管理类，负责添加activity路径的顺序到map中。
 */
public class ActivityStackOrderManager {
	private static ActivityStackOrderManager activityStackOrderManager;
	private SparseArray<Stack<String>> activityStackOrderMap;
	
	private static synchronized void syncInit() {
		if (activityStackOrderManager == null) {
			activityStackOrderManager = new ActivityStackOrderManager();
			activityStackOrderManager.activityStackOrderMap = new SparseArray<Stack<String>>();
		}
	}

	/**
	 * 获取ActivityStackOrderManager实例
	 * 
	 */
	public static ActivityStackOrderManager getASOMInstance() {
		if (activityStackOrderManager == null) {
			syncInit();
		}
		return activityStackOrderManager;
	}
	
	private ActivityStackOrderManager(){
		
	}
	
	
	public Stack<String> getActivityStack(int menuItemId){
		Stack<String> stack  = activityStackOrderMap.get(menuItemId);
		if(stack==null){
			stack = new Stack<String>(); 
			activityStackOrderMap.put(menuItemId, stack);
		}
		return stack;
	}
	
	/**
	 * <p>添加classpath到栈中</p> 
	 * @param @param menuItemId
	 * @param @param clas
	 * @return void   
	 */
	public void addActivityPath(int menuItemId,String clas){
		Stack<String> stack  = getActivityStack(menuItemId);
		stack.push(clas);
	}
	
	/**
	 * 根据菜单id获取最后一个activity
	 * 
	 * @param menuItemId
	 *        菜单itemId
	 * 
	 */
	public String getLastActivityPath(int menuItemId){
		Stack<String> stack  = getActivityStack(menuItemId);
		if(stack!=null&&stack.size()>1){
			String lastActivityName = stack.lastElement() ;
			stack.pop();
			while(lastActivityName.equals(stack.lastElement())){
				stack.pop() ;
			}
		}
		String lastKey = stack.isEmpty()?"":stack.lastElement();
		return lastKey;
	}
	
	/**
	 * 清除堆栈
	 * 
	 */
	public void clearAllStack(){
		int size = activityStackOrderMap.size();
		for(int i=0;i<size;i++){
			int key = activityStackOrderMap.keyAt(i);
			
			Stack<String> stack = activityStackOrderMap.get(key);
			stack.clear();
			stack = null;
		}
		activityStackOrderMap.clear();
	}
	/**
	 * 销毁ActivityStackOrderManager
	 * 
	 * 
	 */
	public void destroy(){
		clearAllStack();
		activityStackOrderManager = null;
	}
	
	public void printStack(int menuItemId){
		String stackInfo ="";
		Stack<String> stack = getActivityStack(menuItemId);
//		currentStack = stack ;
		for(int i=0;i<stack.size();i++){
//			if(stack.elementAt(i).equals(currentStack)){
//				i++ ;
//				continue ;
//			}
			stackInfo += stackInfo.equals("")?stack.elementAt(i):"->"+stack.elementAt(i);
		}
	}
	
}
