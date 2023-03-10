package br.com.cefec.client;

import br.com.cefec.dtos.EmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msEmail", url = "http://localhost:8080")
public interface EmailClient {

    String URL = "/sending-email";

    @PostMapping(URL)
    void sendingEmail(@RequestBody EmailDto dto);

}
