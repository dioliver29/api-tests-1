package guru.qa.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config.properties"
})
public interface ConfigWebAndApiUrls extends Config{

    @Key("webUrl")
    String webUrl();

    @Key("selenideUrl")
    String selenideUrl();
}
