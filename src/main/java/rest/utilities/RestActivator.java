package rest.utilities;

import io.swagger.jaxrs.config.BeanConfig;
import rest.ressources.GestionEmploye;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class RestActivator extends Application {
    public RestActivator(){
        super();
    }

    BeanConfig beanConfig = new BeanConfig();
    {
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("GestionEmploye_war_exploded/api");
        beanConfig.setResourcePackage("rest.ressources");
        beanConfig.setScan(true);
    }


    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new HashSet();
        resources.add(GestionEmploye.class);
        // Available at localhost:port/swagger.json
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;

    }
}
