package guru.qa.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@ConfigWebAndApiUrls.Sources({
        "system:properties",
        "classpath:credential.properties"
})

public interface ConfigForAuth extends Config {

    @Key("login")
    String login();

    @Key("password")
    String password();
}
