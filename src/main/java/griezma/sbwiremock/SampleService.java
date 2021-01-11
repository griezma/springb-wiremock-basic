package griezma.sbwiremock;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {
    private final RestTemplate rest;

    @Setter
    private String base = "http://localhost:8080";

    public String go() {
        return rest.getForObject(base + "/resource/1234", String.class);
    }
}
