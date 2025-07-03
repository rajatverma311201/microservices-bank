package in.rajat_verma.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.function.Function;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


    private Buildable<Route> configureRoute(PredicateSpec spec, String serviceName) {

        String matchPathPattern = "/" + serviceName + "/**";
        String oldPath = "/" + serviceName + "/(?<segment>.*)";
        String newPath = "/${segment}";
        String redirectUri = "lb://" + serviceName.toUpperCase();

        return spec.path(matchPathPattern)
                .filters(f -> f
                        .rewritePath(oldPath, newPath)
                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                .uri(redirectUri);
    }


    @Bean
    public RouteLocator bankRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> configureRoute(p, "accounts"))
                .route(p -> configureRoute(p, "loans"))
                .route(p -> configureRoute(p, "cards"))
                .build();
    }


//                .route(p -> p
//                        .path("/accounts/**")
//                        .filters(f -> f
//                                .rewritePath("/accounts/(?<segment>.*)", "/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//                        .uri("lb://ACCOUNTS"))
//                .route(p -> p
//                        .path("/loans/**")
//                        .filters(f -> f
//                                .rewritePath("/loans/(?<segment>.*)", "/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//                        .uri("lb://LOANS"))
//                .route(p -> p
//                        .path("/cards/**")
//                        .filters(f -> f
//                                .rewritePath("/cards/(?<segment>.*)", "/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//                        .uri("lb://CARDS"))


}
