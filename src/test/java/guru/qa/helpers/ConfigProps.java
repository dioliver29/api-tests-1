package guru.qa.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigProps.Sources({
        "classpath:${Config}.properties"
})
public interface ConfigProps extends Config{

    String url();
    String selenideUrl();
}
