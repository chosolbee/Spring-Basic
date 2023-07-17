package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString(); //고객이 어떤 url로 요청했는지

        System.out.println("myLogger = " + myLogger.getClass());

        myLogger.setRequestURL(requestURL); //url 정보 넣어두기 -> 로그 남길 때 띄우기 위함

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
