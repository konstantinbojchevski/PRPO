package si.fri.prpo.lokacijskiopomniki.api.v1;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;

@OpenAPIDefinition(info = @Info(title = "REST Service", version = "v1.0.0"),
        servers = @Server(url = "http://localhost:8080"))
@ApplicationPath("v1")
public class RestServices extends javax.ws.rs.core.Application{
}
