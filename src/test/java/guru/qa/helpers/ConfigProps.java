package guru.qa.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigProps.Sources({
        "system:properties",
        "classpath:remote.properties"
})
public interface ConfigProps extends Config{

    String url();
    String selenideUrl();
}
