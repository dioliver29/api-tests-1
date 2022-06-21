package guru.qa.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigProps.Sources({
        "classpath:config/remote.properties"
})
public interface ConfigProps extends Config{

    String url();
    String selenideUrl();
}
