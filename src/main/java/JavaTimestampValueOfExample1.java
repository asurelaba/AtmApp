import com.solvd.services.TransactionService;

import java.sql.Timestamp;
import java.util.Scanner;

public class JavaTimestampValueOfExample1 {
    public static void main(String[] args) {
        String one ="2023-07-01 00:00:00";
        String two ="2023-07-12 00:00:00";

        // returns a Timestamp value corresponding to the given string
        Timestamp from = Timestamp.valueOf(one);
        Timestamp to = Timestamp.valueOf(two);


/*        Scanner scanner = new Scanner(System.in);
        System.out.println("now");
        String str2 = scanner.nextLine();
        System.out.println("one " + str);
        System.out.println("two " + str2);
        Timestamp timestamp2= Timestamp.valueOf(str2);
        System.out.println("TWO: "+timestamp2);*/

        System.out.println(new TransactionService().getTransactionsByDateRange(from,to));

    }
}