package guru.qa.helpers;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigWebAndApiUrls.Sources({
        "system:properties",
        "classpath:config.properties"
})
public interface ConfigWebAndApiUrls extends Config{

    String webUrl();
    String apiUrl();

//    URL selenideUrl();
}
