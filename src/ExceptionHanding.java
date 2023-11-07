import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHanding {
    /*---start:异常处理功能---*/
    public static int IntExceptionHanding()
    {
        int number = 0;
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;

        //异常处理，如果输入的是非整数就重新输入
        while (!validInput)
        {
            try
            {
                System.out.print("输入数字确定功能：");
                number = sc.nextInt();
                validInput = true; // 输入合法，跳出循环
            }
            catch (InputMismatchException e)
            {
                System.out.println("你输入的是非整数，请重新输入！");
                sc.next(); // 清除输入缓冲区中的非整数值
            }
        }
        return number;
    }

    public static int NormalIntExceptionHanding()
    {
        int number = 0;
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;

        //异常处理，如果输入的是非整数就重新输入
        while (!validInput)
        {
            try
            {
                number = sc.nextInt();
                validInput = true; // 输入合法，跳出循环
            }
            catch (InputMismatchException e)
            {
                System.out.println("你输入的是非整数，请重新输入！");
                sc.next(); // 清除输入缓冲区中的非整数值
            }
        }
        return number;
    }
    /*---start:异常处理功能---*/
}
