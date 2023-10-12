package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@Slf4j
public class RequestBodyJsonController {
    ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("message = {}", message);

        HelloData helloData = objectMapper.readValue(message, HelloData.class);
        log.info("helloData = {}", helloData);
        res.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody HelloData helloData) throws IOException {
        log.info("helloData = {}", helloData);
        return "ok";
    }

    @ResponseBody
    @PostMapping("request-body-json-v3")
    public HelloData requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("helloData = {}", helloData);
        return helloData;
    }
}
