package ga.repin.easybot.pcstore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
//import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "PC STORE FOR EASYBOT API",
                version = "0.0",
                description = "PC store cross-origin endpoints",
                license = @License(
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html",
                        name = "Apache 2.0"
                ),
                contact = @Contact(
                        name = "Alexey Repin",
                        email = "a@repin.ga ",
                        url = "repin.ga"
                )
        ),
        servers = {
                @Server(
                        description = "(local)",
                        url = "/"),
        }
)
public class RestfulApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    public static void main(String[] args) {
        SpringApplication.run(RestfulApp.class, args);
    }
    
}
