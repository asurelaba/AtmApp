import com.solvd.Logo;
import com.solvd.controllers.atm.AtmController;

public class Main {
    public static void main(String[] args) {
        Logo.logo();
        AtmController atmController = new AtmController();
        atmController.run();
    }
}