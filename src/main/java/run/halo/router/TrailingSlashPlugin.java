package run.halo.router;

import org.springframework.stereotype.Component;
import run.halo.app.plugin.BasePlugin;
import run.halo.app.plugin.PluginContext;

@Component
public class TrailingSlashPlugin extends BasePlugin {

    public TrailingSlashPlugin(PluginContext pluginContext) {
        super(pluginContext);
    }
}
