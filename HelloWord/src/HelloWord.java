import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

/**
 * @Author yuanxiya
 * @Description
 * @Date 2020/10/26 22:47
 */
public class HelloWord {

    public static void main(String[] args) {
        Random random = new Random();
        int a = random.nextInt(100)+1;
        System.out.println(a);

        boolean c = true;

       while (c){
           Scanner scanner = new Scanner(System.in);
           int b = scanner.nextInt();

           if(a==b){
               System.out.println(true);
               c=false;
           }else if (a>b){
               System.out.println("猜小了");
           }else if(a<b){
               System.out.println("猜大了");
           }
       }

    }
}
