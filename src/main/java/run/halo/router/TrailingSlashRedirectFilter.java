package run.halo.router;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import run.halo.app.security.AdditionalWebFilter;

@Component
public class TrailingSlashRedirectFilter implements AdditionalWebFilter {

    @Override
    @NonNull
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        var request = exchange.getRequest();
        String path = request.getURI().getPath();

        if (path.length() > 1 && path.endsWith("/")) {
            String newPath = path.substring(0, path.length() - 1);
            var newRequest = request.mutate().path(newPath).build();
            var newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
