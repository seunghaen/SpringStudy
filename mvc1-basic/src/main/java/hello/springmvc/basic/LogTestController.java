package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.cloud.CloudFoundryVcapEnvironmentPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestController {
//    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest(){
        String name = "spring";
        log.trace("trace = {}", name);
        log.debug("debug = {}", name);
        log.info("info = {}", name);
        log.warn("warn = {}", name);
        log.error("error = {}", name);

        return "ok";
    }


}
