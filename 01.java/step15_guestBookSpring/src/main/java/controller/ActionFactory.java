package controller;

import controller.action.Action;
import controller.action.AllViewBoardAction;
import controller.action.DeleteBoardAction;
import controller.action.UpdateBoardAction;
import controller.action.UpdateFormBoardAction;
import controller.action.ViewBoardAction;
import controller.action.WriteBoardAction;

public class ActionFactory {
	
	private static ActionFactory instance = new ActionFactory();
	private ActionFactory(){}
	
	public static ActionFactory getInstance(){
		return instance;
	}
	package com.example.demo.service;

	import org.springframework.stereotype.Component;

	@Component
	public class ActionFactory {

	    private final AllViewBoardAction allViewBoardAction;
	    private final ViewBoardAction viewBoardAction;
	    private final WriteBoardAction writeBoardAction;
	    private final UpdateFormBoardAction updateFormBoardAction;
	    private final UpdateBoardAction updateBoardAction;
	    private final DeleteBoardAction deleteBoardAction;

	    public ActionFactory(AllViewBoardAction allViewBoardAction,
	                         ViewBoardAction viewBoardAction,
	                         WriteBoardAction writeBoardAction,
	                         UpdateFormBoardAction updateFormBoardAction,
	                         UpdateBoardAction updateBoardAction,
	                         DeleteBoardAction deleteBoardAction) {
	        this.allViewBoardAction = allViewBoardAction;
	        this.viewBoardAction = viewBoardAction;
	        this.writeBoardAction = writeBoardAction;
	        this.updateFormBoardAction = updateFormBoardAction;
	        this.updateBoardAction = updateBoardAction;
	        this.deleteBoardAction = deleteBoardAction;
	    }

	    public Action getAction(String command){
	        switch (command) {
	            case "list":
	                return allViewBoardAction;
	            case "view":
	                return viewBoardAction;
	            case "write":
	                return writeBoardAction;
	            case "updateForm":
	                return updateFormBoardAction;
	            case "update":
	                return updateBoardAction;
	            case "delete":
	                return deleteBoardAction;
	            default:
	                throw new IllegalArgumentException("Invalid command: " + command);
	        }
	    }
	}

	public Action getAction(String command){ //list
		Action action = null;
		
		if(command.equals("list")){	  //command=list				
			action = new AllViewBoardAction();
		}else if(command.equals("view")){ //board?command=view&num=" + num
			action = new ViewBoardAction();
		}else if(command.equals("write")){  
			action = new WriteBoardAction();
		}else if(command.equals("updateForm")){ // updateForm
			action = new UpdateFormBoardAction();
		}else if(command.equals("update")){
			action = new UpdateBoardAction();
		}else if(command.equals("delete")){ //board?command=delete&password=값
			action = new DeleteBoardAction();
		}
		
		return action;
	}
}

