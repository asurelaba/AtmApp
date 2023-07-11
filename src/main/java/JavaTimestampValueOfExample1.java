import java.sql.Timestamp;
import java.util.Scanner;

public class JavaTimestampValueOfExample1 {
    public static void main(String[] args) {
        String str ="2018-09-01 09:01:15";
        System.out.println("String : "+str);
        // returns a Timestamp value corresponding to the given string
        Timestamp timestamp= Timestamp.valueOf(str);
        System.out.println("value of Timestamp : "+timestamp);

        Scanner scanner = new Scanner(System.in);
        System.out.println("now");
        String str2 = scanner.nextLine();
        System.out.println("one " + str);
        System.out.println("two " + str2);
        Timestamp timestamp2= Timestamp.valueOf(str2);
        System.out.println("TWO: "+timestamp2);

    }
}