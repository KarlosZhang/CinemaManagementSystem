import java.util.LinkedList;
import java.util.Scanner;

public class SellSnacks {
    public static void sellSnacks(LinkedList<Consumer> consumerList)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String id = sc.next();
        int judgement = 0;
        for(Consumer consumer: consumerList)
        {
            if(id.equals(consumer.name))
            {
                while (true)
                {
                    System.out.println("影城销售的零食有：");
                    System.out.println("1、爆米花       15元");
                    System.out.println("2、饮料         8元");
                    System.out.println("3、各类套餐     30元");
                    System.out.println("4、退出影城零食销售系统");
                    System.out.println();
                    int number = ExceptionHanding.IntExceptionHanding();
                    switch (number) {
                        case 1:
                            System.out.println("请输入支付金额：");
                            int pay0 = ExceptionHanding.IntExceptionHanding();
                            System.out.println("谢谢惠顾，您已购买爆米花！");
                            int temp = consumer.getTotalConsumeCount();
                            consumer.setTotalConsumeCount(temp + 1);
                            consumer.setTotalConsumeMoney(consumer.getTotalConsumeMoney() + pay0);
                            break;

                        case 2:
                            System.out.println("请输入支付金额：");
                            int pay1 = ExceptionHanding.IntExceptionHanding();
                            System.out.println("谢谢惠顾，您已购买饮料！");
                            int temp1 = consumer.getTotalConsumeCount();
                            consumer.setTotalConsumeCount(temp1 + 1);
                            consumer.setTotalConsumeMoney(consumer.getTotalConsumeMoney() + pay1);
                            break;

                        case 3:
                            System.out.println("请输入支付金额：");
                            int pay2 = ExceptionHanding.IntExceptionHanding();
                            System.out.println("谢谢惠顾，您已购买套餐！");
                            int temp2 = consumer.getTotalConsumeCount();
                            consumer.setTotalConsumeCount(temp2 + 1);
                            consumer.setTotalConsumeMoney(consumer.getTotalConsumeMoney() + pay2);
                            break;

                        case 4:
                            judgement = 1;
                            System.out.println("退出影城零食销售系统");
                            break;

                        default:
                            break;
                    }
                    if (judgement == 1) {
                        break;
                    }
                }
            }
        }
        if(judgement != 1)
        {
            System.out.println("未找到该用户，购买失败！");
        }
    }
}
