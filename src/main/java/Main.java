import com.solvd.controllers.atm.AtmController;
import com.solvd.db.model.Transaction;
import com.solvd.services.TransactionService;
import com.solvd.util.ReceiptGenerator;

public class Main {
    public static void main(String[] args) {
/*        AtmController atmController = new AtmController();
        atmController.run();*/


        Transaction transaction = new TransactionService().getAll().get(0);

        ReceiptGenerator receiptGenerator = new ReceiptGenerator();

        receiptGenerator.createReceipt(transaction);




    }
}