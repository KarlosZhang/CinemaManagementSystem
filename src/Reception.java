import java.text.ParseException;
import java.util.LinkedList;
import java.util.Scanner;

public class Reception extends Enrollee{
    private String type = "前台";
    public static String changePasswordName;

    public Reception() {
        this.type = "前台";
    }

    public Reception(String name, String password, String email, String id, String registerTime, String phoneNumber) {
        super(name, password, email, id, registerTime, phoneNumber);
        this.type = "前台";
    }

    /*---start:前台功能---*/
    public static void receptionMethods(LinkedList<Reception> receptionList, LinkedList<FilmHall> filmHallList, LinkedList<Film> filmList, LinkedList<Consumer> consumerList) throws ParseException {
        int judgement = 0;
        System.out.println("请登录：");
        if(Reception.login(receptionList)) {
            while (true) {
                Reception.menus();
                int number = ExceptionHanding.IntExceptionHanding();
                switch (number) {
                    case 1:
                        Film.printAllFilm(filmList);
                        break;

                    case 2:
                        FilmHall.printAllSession(filmHallList);
                        break;

                    case 3:
                        FilmHall.printSpecialSession(filmHallList);
                        break;

                    case 4:
                        FilmTicket.sellFilmTicket(consumerList, filmHallList);
                        break;

                    case 5:
                        SellSnacks.sellSnacks(consumerList);
                        break;

                    case 6:
                        Reception.logout();
                        judgement = 1;
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
    /*---end:前台功能---*/

    /*---start:菜单功能---*/
    public static void menus(){
        System.out.println("以下为前台系统可执行的操作");
        System.out.println("1、列出所有正在上映影片的信息");
        System.out.println("2、列出所有场次的信息");
        System.out.println("3、列出指定电影和场次的信息");
        System.out.println("4、售票功能");
        System.out.println("5、销售零食功能");
        System.out.println("6、退出前台系统");
        System.out.println("----------------------------");
    }
    /*---end:菜单功能---*/

    /*---start:登录功能---*/
    public static boolean login(LinkedList<Reception> receptionList)
    {
        boolean isValue = false;
        Scanner sc = new Scanner(System.in);
        while (!isValue)
        {
            System.out.print("请输入前台账号：");
            String admin = sc.next();
            Reception.changePasswordName = admin;
            System.out.print("请输入密码：");
            String password = sc.next();
            for (Reception reception : receptionList)
            {
                if (admin.equals(reception.name) && password.equals(reception.password))
                {
                    System.out.println("登录成功，欢迎前台" + reception.name);
                    return true;
                }

            }
            System.out.println("账号或密码错误，登录失败");
            System.out.print("是否退出登录（是/否）：");
            String judge = sc.next();
            if (judge.equals("是"))
            {
                System.out.println("退出登录");
                return false;
            }
        }
        return false;
    }
    /*---end:登录功能---*/

    //修改自身密码
    public static void setPassword(LinkedList<Reception> receptionList, String changePasswordName) {
        Scanner sc = new Scanner(System.in);
        int judgement = 0;
        for(Reception reception: receptionList)
        {
            if(changePasswordName.equals(reception.name)) {
                System.out.println("找到该前台");
                System.out.print("请输入新密码:");
                String password = sc.next();
                reception.password = password;
                System.out.println("已修改好该前台的密码");
                judgement = 1;
                break;
            }
        }
        if(judgement != 1)
        {
            System.out.println("未找到该前台，修改密码失败！");
        }
    }

    /*---start:退出功能---*/
    public static void logout(){
        System.out.println("退出前台系统");
    }
    /*---end:退出功能---*/
    public String getType() {
        return type;
    }
}
