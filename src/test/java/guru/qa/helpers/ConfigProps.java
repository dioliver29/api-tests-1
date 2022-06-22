package guru.qa.helpers;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigProps.Sources({
        "system:properties",
        "classpath:remote.properties"
})
public interface ConfigProps extends Config{

    @Key("baseUrl")
    @DefaultValue("http://demowebshop.tricentis.com")
    String baseUrl();

    @Key("remoteUrl")
    URL selenideUrl();
}
