package guru.qa.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigProps.Sources({
        "classpath:remote.properties"
})
public interface ConfigProps extends Config{

    String url();
    String selenideUrl();
}
