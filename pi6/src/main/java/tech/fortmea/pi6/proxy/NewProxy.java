package tech.fortmea.pi6.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
public class NewProxy {
    @Autowired
    ProxyService service;

    @RequestMapping("/**")
    public String sendRequestToSPM(@RequestBody(required = false) String body,
            HttpMethod method, HttpServletRequest request, HttpServletResponse response)
            throws URISyntaxException {
        String adString = "";
        try {
            if (request.getQueryString().isEmpty() == false) {
                adString = "/?" + request.getQueryString();
            }
        } catch (Exception e) {
            
        }
        String requestURL = "https://" + request.getRequestURL().substring(24) + adString;
        System.out.println(request.getRequestURL().substring(24));
        service.domain = requestURL;
        return service.processProxyRequest(body, method, request, response, UUID.randomUUID().toString()).getBody();
    }
}
