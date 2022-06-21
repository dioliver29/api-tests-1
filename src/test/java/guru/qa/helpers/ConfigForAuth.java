package guru.qa.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigProps.Sources({
        "system:properties",
        "classpath:config/credential.properties"
})

public interface ConfigForAuth extends Config {

    String login();
    String password();
}
