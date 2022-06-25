package guru.qa.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigProps.Sources({
        "system:properties",
        "classpath:remote.properties"
})

public interface ConfigRemote extends Config {

    String selenideUrl();
}
