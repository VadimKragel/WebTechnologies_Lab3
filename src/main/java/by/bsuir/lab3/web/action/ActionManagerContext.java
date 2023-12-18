package by.bsuir.lab3.web.action;

import org.springframework.web.context.WebApplicationContext;

public class ActionManagerContext {

	public ActionManagerContext() {
	}

	public static BaseAction getAction(String action, WebApplicationContext webApplicationContext) {
		return (BaseAction) webApplicationContext.getBean(action);
	}

}
