package guru.qa.helpers;

import org.aeonbits.owner.ConfigFactory;

public class ConfigForTests {
    public static ConfigWebAndApiUrls config = ConfigFactory.create(ConfigWebAndApiUrls.class, System.getProperties());

}
