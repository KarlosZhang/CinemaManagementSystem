import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Consumer extends Enrollee{
    private String level;
    private double discount = 1.0d;
    private double totalConsumeMoney;
    private int totalConsumeCount;
    private int permitLogin;
    LinkedList<FilmTicket> filmTicketList;
    LinkedList<FilmTicket> pastFilmTicketList;
    private Date buyTicketTime;



    public Consumer(String name, String password, String email, String id, String registerTime, String phoneNumber, String level, double totalConsumeMoney, int totalConsumeCount) {
        super(name, password, email, id, registerTime, phoneNumber);
        this.level = level;
        if(level.equals("银牌"))
        {
            this.discount = 0.95d;
        }
        if(level.equals("金牌"))
        {
            this.discount = 0.88d;
        }
        this.totalConsumeMoney = totalConsumeMoney;
        this.totalConsumeCount = totalConsumeCount;
        this.permitLogin = 0;
        this.filmTicketList = new LinkedList<>();
        this.pastFilmTicketList = new LinkedList<>();
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getTotalConsumeMoney() {
        return totalConsumeMoney;
    }

    public void setTotalConsumeMoney(double totalConsumeMoney) {
        this.totalConsumeMoney = totalConsumeMoney;
    }

    public int getTotalConsumeCount() {
        return totalConsumeCount;
    }

    public void setTotalConsumeCount(int totalConsumeCount) {
        this.totalConsumeCount = totalConsumeCount;
    }

    /*---start:用户功能---*/
    public static void consumerMethod(LinkedList<Consumer> consumerList, LinkedList<Film> filmList, LinkedList<FilmHall> filmHallList)
    {
        boolean isvalid = false;
        while (!isvalid)
        {
            Consumer.menus();
            int number = ExceptionHanding.IntExceptionHanding();
            switch (number)
            {
                case 1:
                    if(!Consumer.register(consumerList))
                    {
                        isvalid = true;
                    }
                    break;

                case 2:
                    Consumer.login(consumerList, filmList, filmHallList);
                    break;

                case 3:
                    Consumer.forgetPassword(consumerList);
                    break;

                default:
                    isvalid = true;
                    Consumer.logout();
                    break;
            }
        }
    }
    /*---end:用户功能---*/

    /*---start:菜单功能---*/
    public static void menus(){
        System.out.println("1、注册");
        System.out.println("2、登录");
        System.out.println("3、忘记密码");
        System.out.println("4、退出用户系统");
        System.out.println("----------------------------");
    }

    public static void consumerMenu()
    {
        System.out.println("以下为用户系统可执行的操作");
        System.out.println("1、修改密码");
        System.out.println("2、购票功能");
        System.out.println("3、退出登录");
        System.out.println("----------------------------");
    }
    /*---end:菜单功能---*/

    /*---start:1、注册功能---*/
    public static boolean register(LinkedList<Consumer> consumerList)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Scanner sc= new Scanner(System.in);
        boolean isvalue = false;
        String name = "";
        String password = "";
        String judge;
        while (!isvalue) {
            System.out.print("请输入用户名：");
            name = sc.next();
            int judgement = 0;

            if (!RegisterValidator.isValidUsername(name)) {
                System.out.println("用户名不合法，长度应不少于5个字符。");
                System.out.print("是否重新注册（是/否）：");
                judge = sc.next();
                if(judge.equals("否"))
                {
                    return false;
                }
                else if(judge.equals("是")){
                    continue;
                }
                else
                {
                    System.out.println("输入有误，请重新注册！");
                    return false;
                }
            }

            for(Consumer consumer: consumerList)
            {
                if(name.equals(consumer.name))
                {
                    judgement = 1;
                    System.out.println("已存在该用户名，请重新注册！");
                    System.out.print("是否重新注册（是/否）：");
                    judge = sc.next();
                    if(judge.equals("否"))
                    {
                        return false;
                    }
                    else if(judge.equals("是")){
                        continue;
                    }
                    else
                    {
                        System.out.println("输入有误，请重新注册！");
                        return false;
                    }
                }
            }
            if(judgement == 1)
            {
                continue;
            }

            boolean isValue = false;
            while(!isValue)
            {
                System.out.print("请输入密码：");
                password = sc.next();
                if (!RegisterValidator.isValidPassword(password)) {
                    System.out.println("密码不合法，长度应大于8个字符，必须是大小写字母、数字和标点符号的组合。");
                    System.out.println("请重新注册！");
                    System.out.print("是否重新输入密码（是/否）：");
                    judge = sc.next();
                    if (judge.equals("否"))
                    {
                        return false;
                    }
                }
                else
                {
                    isValue = true;
                }
            }
            isvalue = true;
        }
        System.out.print("请输入你的邮箱：");
        String email = sc.next();
        System.out.print("请输入你的ID：");
        String id = sc.next();
        Date nowTime = new Date();
        System.out.print("请输入你的手机号：");
        String phoneNumber = sc.next();
        Consumer consumer = new Consumer(name, password, email, id, sdf.format(nowTime), phoneNumber, "铜牌", 0, 0);
        consumerList.add(consumer);
        System.out.println("注册成功！默认为铜牌用户。");
        return true;
    }
    /*---end:1、注册功能---*/

    /*---start:2、登录功能---*/
    public static void login(LinkedList<Consumer> consumerList, LinkedList<Film> filmList, LinkedList<FilmHall> filmHallList){
        boolean isValue = false;
        Scanner sc = new Scanner(System.in);
        while (!isValue)
        {
            System.out.print("请输入消费者账号：");
            String admin = sc.next();
            for (Consumer consumer : consumerList)
            {
                if(admin.equals(consumer.name) && consumer.permitLogin <= 5)
                {
                    System.out.print("请输入密码：");
                    String password = sc.next();
                    if(password.equals(consumer.password))
                    {
                        System.out.println("登录成功，欢迎消费者" + consumer.name);
                        consumer.permitLogin = 0;
                        Consumer.mainMethhods(consumer, filmList, filmHallList);
                        return;
                    }
                    else
                    {
                        System.out.println("密码错误，登录失败");
                        consumer.permitLogin++;
                        System.out.print("是否退出登录（是/否）：");
                        String judge = sc.next();
                        if (judge.equals("是") || consumer.permitLogin > 5) {
                            isValue = true;
                        }
                    }
                }
                if(consumer.permitLogin > 5)
                {
                    System.out.println("登录次数超过5次，账号已锁定，请联系经理解锁！");
                    return ;
                }
                if (isValue)
                {
                    System.out.println("退出登录");
                    return ;
                }
            }
        }
        System.out.println("未找到该消费者！");
    }

    //登录后用户总方法
    public static void mainMethhods(Consumer consumer, LinkedList<Film> filmList, LinkedList<FilmHall> filmHallList)
    {
        int judgement = 0;
        while (true)
        {
            Consumer.consumerMenu();
            int number = ExceptionHanding.IntExceptionHanding();
            switch (number)
            {
                case 1:
                    Consumer.setPassword(consumer);
                    break;

                case 2:
                    Consumer.buyTicket(consumer, filmList, filmHallList);
                    break;

                default:
                    judgement = 1;
                    break;
            }
            if (judgement == 1)
            {
                break;
            }
        }
    }

    /*---end:2、登录功能---*/

    /*---start:3、密码管理功能---*/

    //3.1修改自身密码
    public static void setPassword(Consumer consumer) {
        Scanner sc = new Scanner(System.in);
        boolean isvalid = false;

        while (!isvalid) {
            System.out.print("请输入新密码:");
            String password = sc.next();
            if (!RegisterValidator.isValidPassword(password))
            {
                System.out.println("密码不合法，长度应大于8个字符，必须是大小写字母、数字和标点符号的组合。");
                System.out.println("请重新输入！");
                System.out.print("是否重新输入密码（是/否）：");
                String judge = sc.next();
                if (judge.equals("否")) {
                    isvalid = true;
                }
            }
            else {
                consumer.password = password;
                System.out.println("已修改好该消费者的密码");
                isvalid = true;
            }
        }

    }


    //3.2忘记密码
    public static void forgetPassword(LinkedList<Consumer> consumerList)
    {
        Scanner sc = new Scanner(System.in);
        boolean isvalid = false;
        int judgement = 0;
        System.out.print("请输入消费者账号：");
        String admin = sc.next();
        for(Consumer consumer: consumerList)
        {
            if(admin.equals(consumer.name))
            {
                System.out.println("找到该消费者");
                System.out.print("请输入邮箱:");
                String email = sc.next();
                System.out.println("已发送登录密码到该邮箱" + email + " ，请及时查看并修改新的密码！");
            }
        }
        if(judgement != 1)
        {
            System.out.println("未找到该消费者！");
        }
    }

    /*---end:3、密码管理功能---*/


    /*---start:4、购票功能---*/
    public static void buyTicket(Consumer consumer, LinkedList<Film> filmList, LinkedList<FilmHall> filmHallList)
    {
        boolean isvalid = false;
        while (!isvalid)
        {
            System.out.println("1、查看所有电影放映信息");
            System.out.println("2、查看指定电影放映信息");
            System.out.println("3、购票与支付");
            System.out.println("4、取票");
            System.out.println("5、查看购票历史");
            System.out.println("6、退出购票功能");
            System.out.println("----------------------------");
            int number = ExceptionHanding.IntExceptionHanding();
            switch (number)
            {
                case 1:
                    Film.printAllFilm(filmList);
                    break;

                case 2:
                    FilmHall.printSpecialSession(filmHallList);
                    break;

                case 3:
                    Consumer.chooseTicket(consumer, filmHallList);
                    break;

                case 4:
                    Consumer.takeTicket(consumer);
                    break;

                case 5:
                    Consumer.checkBuyTicketHistory(consumer);
                    break;

                default:
                    isvalid = true;
                    System.out.println("退出购票功能！");
                    break;
            }
        }
    }

    //4.3购票
    public static void chooseTicket(Consumer consumer, LinkedList<FilmHall> filmHallList)
    {
        int judgement = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入电影片名：");
        String name = sc.next();
        System.out.print("请输入场次：");
        String changCi = sc.next();
        for(FilmHall filmHall: filmHallList)
        {
            if(name.equals(filmHall.getFilm().getName()) && changCi.equals(filmHall.getChangCi()))
            {
                judgement = 1;
                System.out.println("找到该电影场次");
                System.out.println("总座位数：" + filmHall.getRow() * filmHall.getCol());
                System.out.println("空闲座位数：" + FilmHall.countAvailableSeat(filmHall));
                System.out.println("座位信息如下（0为空位，1为已被选位置）：");
                FilmHall.getAllSeatState(filmHall);
                System.out.println("已列出该场次座位信息！");
                Consumer.pay(consumer, filmHall);
            }
        }
        if(judgement != 1)
        {
            System.out.println("未找到该电影场次！");
        }
    }

    //4.3.1购票支付
    public static void pay(Consumer consumer, FilmHall filmHall)
    {
        Date payTime;
        System.out.println("请输入你要购买的票数：");
        int number = ExceptionHanding.IntExceptionHanding();
        int[] row = new int[number];
        int[] col = new int[number];
        for (int i = 0; i < number; i++) {
            System.out.print("请输入第" + (i+1) + "个电影票座位的座位行数：");
            row[i] = ExceptionHanding.IntExceptionHanding();
            System.out.print("请输入第" + (i+1) + "个电影票座位的座位列数：");
            col[i] = ExceptionHanding.IntExceptionHanding();
            if(filmHall.getSingleSeatState(row[i] - 1, col[i] - 1) == 1)
            {
                System.out.println("该座位已有人，请重新购票！");
                i -= 1;
                continue;
            }
            filmHall.chooseSeat(row[i] - 1, col[i] - 1);
            System.out.println("已锁定好第" + row[i] + "排, 第" + col[i] + "列的座位");
        }
        System.out.println("请在2分钟内完成支付，否则购票失败，座位将被释放！");
        consumer.buyTicketTime = new Date();
        double singlePay = filmHall.getPrice() * consumer.getDiscount();
        double pay = filmHall.getPrice() * number * consumer.getDiscount();
        System.out.println("需要支付的金额为：" + pay);
        System.out.println("请选择你的支付方式");
        System.out.println("1、支付宝");
        System.out.println("2、微信");
        System.out.println("3、银行卡");
        int choose = ExceptionHanding.IntExceptionHanding();
        switch (choose)
        {
            case 1:
                System.out.println("支付宝支付" + pay + "元成功");
                break;

            case 2:
                System.out.println("微信支付" + pay + "元成功");
                break;

            case 3:
                System.out.println("银行卡支付" + pay + "元成功");
                break;

            default:
                System.out.println("支付失败！");
                break;
        }
        payTime = new Date();
        long timeDifferenceInMillis = (payTime.getTime() - consumer.buyTicketTime.getTime()) / (60 * 1000); //分钟
        if(timeDifferenceInMillis <= 2)
        {
            System.out.println("支付成功！");
            consumer.totalConsumeCount += 1;
            consumer.totalConsumeMoney += pay;
            for (int i = 0; i < number; i++) {
                FilmTicket newfilmTicket = new FilmTicket(row[i], col[i], singlePay, filmHall, payTime);
                consumer.filmTicketList.add(newfilmTicket);
            }
            System.out.println("您已购得" + number + "张电影票, 可凭借电子ID编号取票！");
        }
        else
        {
            System.out.println("超过2分钟未支付，购票失败，座位已释放！");
            for (int i = 0; i < number; i++) {
                filmHall.cancelSeat(row[i], col[i]);
            }
        }
    }

    //4.4取票功能
    public static void takeTicket(Consumer consumer)
    {
        int judgement = 0;
        if(consumer.filmTicketList.isEmpty())
        {
            System.out.println("您还未购买电影票，取票失败！");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入电影票电子ID编号：");
        String id = sc.next();
        for(FilmTicket filmTicket: consumer.filmTicketList)
        {
            if(id.equals(filmTicket.getId()))
            {
                System.out.println();
                FilmTicket.printFilmTicket(filmTicket);
                consumer.filmTicketList.remove(filmTicket);
                consumer.pastFilmTicketList.add(filmTicket);
                System.out.println("取票成功！");
                judgement = 1;
            }
        }
        if(judgement != 1) {
            System.out.println("电影票编号有误，取票失败！");
        }
    }

    //4.5查看购票历史
    public static void checkBuyTicketHistory(Consumer consumer)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        System.out.println("以下为您的购票历史：");
        for (FilmTicket filmTicket: consumer.pastFilmTicketList)
        {
            System.out.println("购买时间：" + sdf.format(filmTicket.getBuyTime()));
            FilmTicket.printFilmTicket(filmTicket);
        }
        System.out.println("已查看完购票历史！");
    }

    /*---end:4、购票功能---*/

    /*---start:5、退出功能---*/
    public static void logout(){
        System.out.println("退出用户系统");
    }
    /*---end:5、退出功能---*/

    //列出所有消费者信息
    public static void printAllConsumerMessage(LinkedList<Consumer> consumerList)
    {
        System.out.println();
        for(Consumer consumer: consumerList)
        {
            printSingleConsumerMessage(consumer);
        }
        System.out.println("----------------------------");
        System.out.println("已列出所有消费者的信息");
    }

    //打印单个消费者的信息
    public static void printSingleConsumerMessage(Consumer consumer)
    {
        System.out.println("消费者ID为：" + consumer.id);
        System.out.println("消费者用户名为：" + consumer.name);
        System.out.println("消费者级别：" + consumer.getLevel());
        System.out.println("消费者注册时间：" + consumer.registerTime);
        System.out.println("消费者累计消费总金额：" + consumer.getTotalConsumeMoney());
        System.out.println("消费者累计消费次数：" + consumer.getTotalConsumeCount());
        System.out.println("消费者手机号：" + consumer.phoneNumber);
        System.out.println("消费者邮箱：" + consumer.email);
        System.out.println();
    }

}
