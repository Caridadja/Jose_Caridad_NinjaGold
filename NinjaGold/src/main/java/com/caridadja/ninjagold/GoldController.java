package com.caridadja.ninjagold;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GoldController {
	@RequestMapping("/")
	public String index(HttpSession session) {
		if(session.getAttribute("gold") == null) {
			session.setAttribute("gold", 0);
			session.setAttribute("logs", new ArrayList<String>());
		}
		return "index.jsp";
	}
	@RequestMapping(value="/play", method=RequestMethod.POST)
	public String ninjaGold(HttpSession session, Model model, @RequestParam(value="task") String task) {
		String time = new SimpleDateFormat("MMMMM dd yyyy HH:mm a").format(new Date());
		
		if(task.equals("farm")) {
			Integer getGold = getRandomGold(10, 20);
			Integer currentGold = (Integer) session.getAttribute("gold");
			session.setAttribute("gold", currentGold+getGold);
			((ArrayList<String>) session.getAttribute("logs")).add("You entered a farm and earned "+getGold+". ("+time+")");
		}
		if(task.equals("casino")) {
			Integer getGold = getRandomGold(50, -50);
			Integer currentGold = (Integer) session.getAttribute("gold");
			session.setAttribute("gold", currentGold+getGold);
			if(getGold < 0) {
				((ArrayList<String>) session.getAttribute("logs")).add("You entered a casino and lost "+getGold+". ("+time+")");
			}
			else {
			((ArrayList<String>) session.getAttribute("logs")).add("You entered a casino and earned "+getGold+". ("+time+")");
			}
		}
		ArrayList<String> finalLogs = (ArrayList<String>) session.getAttribute("logs");
		Collections.reverse(finalLogs);
		model.addAttribute("gold", session.getAttribute("gold"));
		model.addAttribute("logs", finalLogs);
		return "index.jsp";
	}

public static int getRandomGold(int min, int max){
    int x = (int)(Math.random()*((max-min)+1))+min;
    return x;
}
}
