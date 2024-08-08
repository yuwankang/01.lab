package controller;

import com.example.demo.service.ActionService;
import com.example.demo.service.ActionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ActionFactory actionFactory;

    @GetMapping
    public ModelAndView handleRequest(@RequestParam(name = "command", defaultValue = "list") String command,
                                      @RequestParam(name = "num", required = false) Integer num,
                                      @RequestParam(name = "password", required = false) String password) {
        Action action = actionFactory.getAction(command);
        return action.execute(num, password);
    }
}
