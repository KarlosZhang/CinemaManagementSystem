import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CinemaManagementSystemDemo {
    static LinkedList<Manager> managerList = new LinkedList<>();
    static LinkedList<Reception> receptionList = new LinkedList<>();
    static LinkedList<Consumer> consumerList = new LinkedList<>();
    static LinkedList<FilmHall> filmHallList = new LinkedList<>();
    static LinkedList<Film> filmList = new LinkedList<>();
    public static void main(String[] args) throws ParseException {
        //创建管理员对象
        Administrators administrator = new Administrators("admin", "ynuinfo#777");
        administrator.managerList = managerList;
        administrator.receptionList = receptionList;

        int judgement = 0; //退出循环标志位
        while (true)
        {
            mainMenus();
            int number = ExceptionHanding.IntExceptionHanding();
            switch (number)
            {
                case 1:
                    administrator.administratorMethods();
                    break;

                case 2:
                    Manager.managerMethods(managerList, consumerList, filmHallList, filmList);
                    break;

                case 3:
                    Reception.receptionMethods(receptionList, filmHallList, filmList, consumerList);
                    break;

                case 4:
                    Consumer.consumerMethod(consumerList, filmList, filmHallList);
                    break;

                case 5:
                    judgement = 1;
                    break;

                default:
                    break;
            }
            if(judgement == 1)
            {
                System.out.println("已退出影城管理系统，感谢使用！");
                break;
            }
        }

    }

    public static void mainMenus()
    {
        System.out.println("请选择你的身份：");
        System.out.println("1、管理员");
        System.out.println("2、经理");
        System.out.println("3、前台");
        System.out.println("4、消费者");
        System.out.println("5、退出影城管理系统");
    }
}
