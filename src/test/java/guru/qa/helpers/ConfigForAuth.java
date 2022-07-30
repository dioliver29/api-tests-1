package guru.qa.helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:credentials.properties"
})

public interface ConfigForAuth extends Config {

    @Key("login")
    String login();

    @Key("password")
    String password();
}
