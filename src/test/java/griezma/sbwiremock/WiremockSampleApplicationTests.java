package griezma.sbwiremock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class WiremockSampleApplicationTests {
    @Autowired
    SampleService service;

    @Value("${wiremock.server.port}")
    String wireMockPort = "8083";

    @Test
    void wireMockPort() {
        log.info("wireMockPort: " + wireMockPort);
        assertFalse(wireMockPort.isEmpty());
    }

    @Test
    void contextLoadsWireMock() {
        service.setBase("http://localhost:" + wireMockPort);

        stubFor(get(urlMatching("/resource/.+")).willReturn(aResponse()
            .withHeader("Content-Type", "text/plain")
            .withBody("Hello World")));

        assertEquals("Hello World", service.go());
    }
}
