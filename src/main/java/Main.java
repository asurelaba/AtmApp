import static com.solvd.util.AppConfig.DB;
import static com.solvd.util.AppConfig.ENVIRONMENT;

import com.solvd.controllers.atm.AtmController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    public static void main(String[] args) {
        if (ENVIRONMENT.equals("DEV")) {
            Logger LOG = LogManager.getLogger(Main.class);
            LOG.warn("ENVIRONMENT: DEV");
            LOG.warn("DB: " + DB);
            LOG.warn("Configure DB (SQLITE or MYSQL) in resources/appconfig.properties");
        }

        AtmController atmController = new AtmController();
        atmController.run();
    }

}