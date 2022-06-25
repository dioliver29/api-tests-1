package guru.qa.helpers;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigProps.Sources({
        "classpath:config/config.properties"
})
public interface ConfigProps extends Config{

    String webUrl();
    String apiUrl();

//    URL selenideUrl();
}
