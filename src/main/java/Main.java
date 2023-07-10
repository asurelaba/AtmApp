import com.solvd.EnumEventNames;
import com.solvd.controllers.atm.AtmController;
import com.solvd.db.model.Transaction;
import com.solvd.services.EventService;
import com.solvd.services.TransactionService;
import com.solvd.util.ReceiptGenerator;

public class Main {
    public static void main(String[] args) {
/*        AtmController atmController = new AtmController();
        atmController.run();*/

        // Print receipt Test
        Transaction withdrawal = new TransactionService().getTransactionByEventId(new EventService()
                .getEventsByType(EnumEventNames.WITHDRAWAL.getEventName())
                .get(0).
                getEventId());
        Transaction deposit = new TransactionService().getTransactionByEventId(new EventService()
                .getEventsByType(EnumEventNames.DEPOSIT.getEventName())
                .get(0).
                getEventId());
        deposit.setAmount(123.44F);
        Transaction transfer = new TransactionService().getTransactionByEventId(new EventService()
                .getEventsByType(EnumEventNames.TRANSFER.getEventName())
                .get(0).
                getEventId());
        Transaction balanceInquiry = new TransactionService().getTransactionByEventId(new EventService()
                .getEventsByType(EnumEventNames.BALANCE_INQUIRY.getEventName())
                .get(0).
                getEventId());

        ReceiptGenerator.createReceipt(withdrawal);
        ReceiptGenerator.createReceipt(deposit);
        ReceiptGenerator.createReceipt(transfer);
        ReceiptGenerator.createReceipt(balanceInquiry);
    }
}