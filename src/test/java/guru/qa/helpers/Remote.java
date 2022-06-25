package guru.qa.helpers;

import org.aeonbits.owner.ConfigFactory;

public class Remote {
    public static ConfigRemote config = ConfigFactory.create(ConfigRemote.class, System.getProperties());

}
