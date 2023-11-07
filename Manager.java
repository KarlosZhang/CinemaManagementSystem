import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Manager extends Enrollee {
    private String type;
    public static String changePasswordName;

    public Manager(String name, String password, String email, String id, String registerTime, String phoneNumber) {
        super(name, password, email, id, registerTime, phoneNumber);
        this.type = "经理";
    }

    /*---start:经理方法---*/
    public static void managerMethods(LinkedList<Manager> managerList, LinkedList<Consumer> consumerList, LinkedList<FilmHall> filmHallList, LinkedList<Film> filmList) throws ParseException {
        int judgement = 0;
        System.out.println("请登录：");
        if(Manager.login(managerList)) {
            while (true) {
                Manager.menus();
                int number = ExceptionHanding.IntExceptionHanding();
                switch (number) {
                    case 1:
                        Manager.managePassword(consumerList, managerList);
                        break;

                    case 2:
                        Manager.manageFilm(filmList);
                        break;

                    case 3:
                        Manager.manageFilmSchedule(filmHallList, filmList);
                        break;

                    case 4:
                        Manager.manageConsumer(consumerList);
                        break;

                    case 5:
                        SellSnacks.sellSnacks(consumerList);
                        break;

                    case 6:
                        Manager.logout();
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
    /*---end:经理方法---*/

    /*---start:菜单功能---*/
    public static void menus()
    {
        System.out.println("以下为经理系统可执行的操作");
        System.out.println("1、密码管理");
        System.out.println("2、影片管理");
        System.out.println("3、排片管理");
        System.out.println("4、消费者管理");
        System.out.println("5、销售零食功能");
        System.out.println("6、退出经理系统");
        System.out.println("----------------------------");
    }
    /*---end:菜单功能---*/

    /*---start:登录功能---*/
    public static boolean login(LinkedList<Manager> managerList)
    {
        boolean isValue = false;
        int judgement = 0;
        Scanner sc = new Scanner(System.in);
        while (!isValue)
        {
            System.out.print("请输入经理账号：");
            String admin = sc.next();
            System.out.print("请输入密码：");
            String password = sc.next();
            for (Manager manager : managerList)
            {
                if (admin.equals(manager.name) && password.equals(manager.password))
                {
                    System.out.println("登录成功，欢迎经理" + manager.name);
                    changePasswordName = manager.name;
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

    /*---start:密码管理功能---*/
    public static void managePassword(LinkedList<Consumer> consumerList, LinkedList<Manager> managerList) {
        int judgement = 0; //退出循环标志
        while (true) {
            System.out.println("1、修改自身密码");
            System.out.println("2、重置指定消费者的密码");
            System.out.println("3、退出密码管理功能");
            System.out.println("----------------------------");
            int number = ExceptionHanding.IntExceptionHanding();
            Scanner sc = new Scanner(System.in);
            switch (number) {
                case 1:
                    Manager.setPassword(managerList,changePasswordName);
                    break;

                case 2:
                    Manager.changeConsumerPassword(consumerList);
                    break;

                case 3:
                    judgement = 1;
                    break;

                default:
                    break;
            }
            //退出循环
            if (judgement == 1) {
                System.out.println("退出密码管理功能");
                break;
            }
        }
    }

    //3.1修改自身密码
    public static void setPassword(LinkedList<Manager> managerList, String changePasswordName) {
        Scanner sc = new Scanner(System.in);
        int judgement = 0;
        for(Manager manager: managerList)
        {
            if(changePasswordName.equals(manager.name)) {
                System.out.println("找到该经理");
                System.out.print("请输入新密码:");
                String password = sc.next();
                manager.password = password;
                System.out.println("已修改好该经理的密码");
                judgement = 1;
                break;
            }
        }
        if(judgement != 1)
        {
            System.out.println("未找到该经理，修改密码失败！");
        }
    }

    //3.2重置指定消费者的密码
    public static void changeConsumerPassword(LinkedList<Consumer> consumerList)
    {
        Scanner sc = new Scanner(System.in);
        int judge = 0;
        System.out.print("请输入该消费者的用户名：");
        String name = sc.next();
        //遍历消费者链表，找到对应经理id才修改密码
        for (Consumer consumer : consumerList) {
            if (name.equals(consumer.name)) {
                System.out.print("请输入该消费者的新密码：");
                String password = sc.next();
                consumer.setPassword(password);
                System.out.println("已修改该消费者的密码");
                judge = 1;
                break;
            }
        }
        if (judge != 1) {
            System.out.println("未找到该消费者，修改密码失败");
        }
    }

    /*---end:密码管理功能---*/

    /*---start:影片管理功能---*/
    public static void manageFilm(LinkedList<Film> filmList)
    {
        int judgement = 0;//退出循环标志位
        while (true)
        {
            System.out.println("1、列出所有正在上映影片的信息");
            System.out.println("2、添加影片的信息");
            System.out.println("3、修改电影的信息");
            System.out.println("4、删除电影信息");
            System.out.println("5、查询影片的信息");
            System.out.println("6、退出影片管理功能");
            System.out.println("----------------------------");
            int number = ExceptionHanding.IntExceptionHanding();
            switch (number)
            {
                case 1:
                    Film.printAllFilm(filmList);
                    break;

                case 2:
                    Manager.addFilm(filmList);
                    break;

                case 3:
                    Manager.changeFilm(filmList);
                    break;

                case 4:
                    Manager.deleteFilm(filmList);
                    break;

                case 5:
                    Manager.findFilmMessage(filmList);
                    break;

                case 6:
                    judgement = 1;
                    break;
            }
            if(judgement == 1){
                System.out.println("已退出影片管理功能");
                break;
            }
        }
    }


    //4.2添加影片的信息
    public static void addFilm(LinkedList<Film> filmList)
    {
        System.out.println("添加信息：");
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入片名：");
        String name = sc.next();
        if(Manager.uniqueCheck(name, filmList))
        {
            System.out.print("请输入导演：");
            String director = sc.next();
            System.out.print("请输入主演：");
            String mainActor = sc.next();
            System.out.print("请输入剧情简介：");
            String plot = sc.next();
            System.out.print("请输入时长：");
            String duration = sc.next();
            Film film = new Film(name, director, mainActor, plot, duration);
            filmList.add(film);
            System.out.println("已添加电影信息");
        }
    }

    //唯一性检查
    public static boolean uniqueCheck(String name, LinkedList<Film> filmList)
    {
        for(Film film: filmList)
        {
            if(name.equals(film.getName()))
            {
                System.out.println("片名重合，添加失败！");
                return false;
            }
        }
        return true;
    }

    //4.3修改电影信息
    public static void changeFilm(LinkedList<Film> filmList)
    {
        int judge = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入片名：");
        String name = sc.next();
        for(Film film: filmList)
        {
            if(name.equals(film.getName()))
            {
                System.out.println("找到该影片，开始修改：");
                System.out.print("请输入片名：");
                film.setName(sc.next());
                System.out.print("请输入导演：");
                film.setDirector(sc.next());
                System.out.print("请输入主演：");
                film.setMainActor(sc.next());
                System.out.print("请输入剧情简介：");
                film.setPlot(sc.next());
                System.out.print("请输入时长：");
                film.setDuration(sc.next());
                System.out.println("已修改完电影的信息！");
                judge = 1;
                break;
            }
        }
        if(judge != 1)
        {
            System.out.println("未找到该电影");
        }
    }

    //4.4删除影片的信息
    public static void deleteFilm(LinkedList<Film> filmList)
    {
        int judge = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入片名：");
        String name = sc.next();
        for(Film film: filmList)
        {
            if(name.equals(film.getName()))
            {
                System.out.print("已找到该电影，删除后无法恢复，确认要删除吗？（输入是或否）：");
                String deleteJudge = sc.next();
                if(deleteJudge.equals("是"))
                {
                    filmList.remove(film);
                    System.out.println("已删除该影片");
                    judge = 1;
                    break;
                }
                else
                {
                    System.out.println("取消删除");
                    break;
                }

            }
        }
        if(judge != 1)
        {
            System.out.println("未找到该电影，删除失败！");
        }
    }

    //4.5查询影片的信息
    public static void findFilmMessage(LinkedList<Film> filmList)
    {
        System.out.println("请选择查询方式：");
        System.out.println("1、片名");
        System.out.println("2、导演");
        System.out.println("3、主演");
        System.out.println("4、片名+导演");
        System.out.println("5、片名+主演");
        System.out.println("6、导演+主演");
        System.out.println("7、片名+导演+主演");
        System.out.println("8、退出查询");
        Scanner sc = new Scanner(System.in);
        int number = ExceptionHanding.IntExceptionHanding();
        System.out.println();
        switch (number)
        {
            case 1:
                int judge1 = 0;
                System.out.print("请输入片名：");
                String name = sc.next();
                for(Film film: filmList)
                {
                    if(name.equals(film.getName()))
                    {
                        Film.printSingleFilm(film);
                        judge1 = 1;
                    }
                }
                if (judge1 != 1)
                {
                    System.out.println("未找到该影片");
                }
                break;

            case 2:
                int judge2 = 0;
                System.out.print("请输入导演名字：");
                String director = sc.next();
                for(Film film: filmList)
                {
                    if(director.equals(film.getDirector()))
                    {
                        Film.printSingleFilm(film);
                        judge2 = 1;
                    }
                }
                if (judge2 != 1)
                {
                    System.out.println("未找到该影片");
                }
                break;

            case 3:
                int judge3 = 0;
                System.out.print("请输入主演名字：");
                String mainActor = sc.next();
                for(Film film: filmList)
                {
                    if(mainActor.equals(film.getMainActor()))
                    {
                        Film.printSingleFilm(film);
                        judge3 = 1;
                    }
                }
                if (judge3 != 1)
                {
                    System.out.println("未找到该影片");
                }
                break;

            case 4:
                int judge4 = 0;
                System.out.print("请输入片名：");
                String name1 = sc.next();
                System.out.print("请输入导演名字：");
                String director1 = sc.next();
                for(Film film: filmList)
                {
                    if(name1.equals(film.getName()) && director1.equals(film.getDirector()))
                    {
                        Film.printSingleFilm(film);
                        judge4 = 1;
                    }
                }
                if (judge4 != 1)
                {
                    System.out.println("未找到该影片");
                }
                break;

            case 5:
                int judge5 = 0;
                System.out.print("请输入片名：");
                String name2 = sc.next();
                System.out.print("请输入主演名字：");
                String mainActor1 = sc.next();
                for(Film film: filmList)
                {
                    if(name2.equals(film.getName()) && mainActor1.equals(film.getMainActor()))
                    {
                        Film.printSingleFilm(film);
                        judge5 = 1;
                    }
                }
                if (judge5 != 1)
                {
                    System.out.println("未找到该影片");
                }
                break;

            case 6:
                int judge6 = 0;
                System.out.print("请输入导演名字：");
                String director2 = sc.next();
                System.out.print("请输入主演名字：");
                String mainActor2 = sc.next();
                for(Film film: filmList)
                {
                    if(director2.equals(film.getDirector()) && mainActor2.equals(film.getMainActor()))
                    {
                        Film.printSingleFilm(film);
                        judge6 = 1;
                    }
                }
                if (judge6 != 1)
                {
                    System.out.println("未找到该影片");
                }
                break;

            case 7:
                int judge7 = 0;
                System.out.print("请输入片名：");
                String name3 = sc.next();
                System.out.print("请输入导演名字：");
                String director3 = sc.next();
                System.out.print("请输入主演名字：");
                String mainActor3 = sc.next();
                for(Film film: filmList)
                {
                    if(name3.equals(film.getName()) && director3.equals(film.getDirector()) && mainActor3.equals(film.getMainActor()))
                    {
                        Film.printSingleFilm(film);
                        judge7 = 1;
                    }
                }
                if (judge7 != 1)
                {
                    System.out.println("未找到该影片");
                }
                break;

            case 8:
                System.out.println("已退出查询！");
                break;

            default:
                break;
        }
    }
    /*---end:影片管理功能---*/

    /*---start:排片管理功能---*/
    public static void manageFilmSchedule(LinkedList<FilmHall> filmHallList, LinkedList<Film> filmList) throws ParseException {
        int judgement = 0;
        while (true) {
            System.out.println("1、增加场次");
            System.out.println("2、修改场次");
            System.out.println("3、删除场次");
            System.out.println("4、列出所有场次信息");
            System.out.println("5、退出排片管理功能");
            System.out.println("----------------------------");
            int number = ExceptionHanding.IntExceptionHanding();
            switch (number) {
                case 1:
                    addSession(filmHallList, filmList);
                    break;

                case 2:
                    changeSession(filmHallList, filmList);
                    break;

                case 3:
                    deleteSession(filmHallList);
                    break;

                case 4:
                    FilmHall.printAllSession(filmHallList);;
                    break;

                case 5:
                    judgement = 1;
                    break;

                default:
                    break;
            }
            if (judgement == 1) {
                System.out.println("已退出排片管理系统！");
                break;
            }
        }
    }

    //5.1增加场次
    public static void addSession(LinkedList<FilmHall> filmHallList, LinkedList<Film> filmList) throws ParseException {
        Film afilm = new Film();
        int judge = 0;
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        System.out.print("请输入要安排的放映厅ID：");
        String id = sc.next();
        System.out.print("请输入要安排的电影片名：");
        String filmName = sc.next();
        for (Film film : filmList) {
            if (filmName.equals(film.getName())) {
                judge = 1;
                afilm = film;
            }
        }
        if (judge != 1) {
            System.out.println("目前上映的电影中没有该电影，增加场次失败！");
            return;
        }
        System.out.println("请输入指定的时间段（允许提前安排一周）");
        System.out.print("请输入开始时间(格式：yyyy年MM月dd日HH:mm:ss)：");
        String startTime = sc.next();
        Date nowTime = new Date();
        if (((sdf.parse(startTime).getTime() - nowTime.getTime()) / (1000 * 60 * 60 * 24)) > 7) {
            System.out.println("时间超过一周，增加场次失败！");
            return;
        }
        for (FilmHall filmHall : filmHallList) {
            if (id.equals(filmHall.getId()) && startTime.equals(sdf.format(filmHall.getStartTime()))) {
                System.out.println("场次冲突，添加失败！");
                return;
            }
        }
        System.out.print("请输入结束时间（格式：yyyy年MM月dd日HH:mm:ss）：");
        String endTime = sc.next();
        System.out.print("请输入价格：");
        double price = sc.nextDouble();
        System.out.print("请输入场次：");
        String changCi = sc.next();
        FilmHall filmHall = new FilmHall(id, 7, 12, afilm, price, startTime, endTime, changCi);
        filmHallList.add(filmHall);
        System.out.println("已增加该场次电影");
    }

    //5.2修改场次
    public static void changeSession(LinkedList<FilmHall> filmHallList, LinkedList<Film> filmList)
    {
        int judgement = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入需要修改场次的放映厅id：");
        String id = sc.next();
        System.out.print("请输入需要修改场次的场次：");
        String changCi = sc.next();
        for(FilmHall filmHall: filmHallList)
        {
            if(id.equals(filmHall.getId()) && changCi.equals(filmHall.getChangCi()))
            {
                System.out.print("找到该场次电影，需要空场吗（是/否）：");
                String judge = sc.next();
                judgement = 1;
                if(judge.equals("是"))
                {
                    filmHallList.remove(filmHall);
                    System.out.println("已将该场次空场");
                }
                else if(judge.equals("否"))
                {
                    System.out.print("请输入新影片片名：");
                    String name = sc.next();
                    for(Film film: filmList) {
                        if(name.equals(film.getName())){
                            System.out.println("该影片正在上映！");
                            System.out.print("请输入价格：");
                            double newPrice = sc.nextDouble();
                            filmHall.setPrice(newPrice);
                        }
                    }
                }
                else
                {
                    System.out.println("输入有误，退出修改场次功能！");
                }
                break;
            }
        }
        if(judgement != 1)
        {
            System.out.println("未找到该场次电影！");
        }
    }

    //5.3删除场次
    public static void deleteSession(LinkedList<FilmHall> filmHallList)
    {
        int judgement = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入你要删除场次的影片片名：");
        String name = sc.next();
        System.out.print("请输入该影片的场次：");
        String changCi = sc.next();
        for(FilmHall filmHall: filmHallList)
        {
            if(name.equals(filmHall.getFilm().getName()) && changCi.equals(filmHall.getChangCi()))
            {
                judgement = 1;
                System.out.print("找到该场次影片，确定删除吗（是/否）：");
                String judge = sc.next();
                if(judge.equals("是"))
                {
                    filmHallList.remove(filmHall);
                    System.out.println("已删除该场次影片！");
                }
                else
                {
                    System.out.println("取消删除");
                }
                break;
            }
        }
        if(judgement != 1)
        {
            System.out.println("未找到该场次影片，取消删除！");
        }
    }

    /*---end:排片管理功能---*/

    /*---start:用户管理功能---*/
    public static void manageConsumer(LinkedList<Consumer> consumerList)
    {
        int judgement = 0;
        while (true) {
            System.out.println("1、列出所有消费者信息");
            System.out.println("2、查询消费者信息");
            System.out.println("3、退出用户管理功能");
            System.out.println("----------------------------");
            int number = ExceptionHanding.IntExceptionHanding();
            switch (number) {
                case 1:
                    Consumer.printAllConsumerMessage(consumerList);
                    break;

                case 2:
                    Manager.findConsumerMessage(consumerList);
                    break;

                case 3:
                    judgement = 1;
                    break;

                default:
                    break;
            }
            if (judgement == 1) {
                System.out.println("已退出用户管理系统！");
                break;
            }
        }
    }


    //6.2查询用户信息
    public static void findConsumerMessage(LinkedList<Consumer> consumerList)
    {
        System.out.println("请选择查询方式：");
        System.out.println("1、消费者ID");
        System.out.println("2、消费者用户名");
        System.out.println("3、查询所有消费者信息");
        System.out.println("4、退出查询用户功能");
        Scanner sc = new Scanner(System.in);
        int number = ExceptionHanding.IntExceptionHanding();
        System.out.println();
        switch (number)
        {
            case 1:
                int judge1 = 0;
                System.out.print("请输入消费者ID：");
                String id = sc.next();
                for (Consumer consumer: consumerList)
                {
                    if(id.equals(consumer.id))
                    {
                        System.out.println("找到该消费者，该消费者信息如下：");
                        Consumer.printSingleConsumerMessage(consumer);
                        judge1 = 1;
                    }
                }
                if(judge1 != 1)
                {
                    System.out.println("未找到该用户");
                }
                break;

            case 2:
                int judge2 = 0;
                System.out.print("请输入消费者用户名：");
                String name = sc.next();
                for (Consumer consumer: consumerList)
                {
                    if(name.equals(consumer.name))
                    {
                        System.out.println("找到该消费者，该消费者信息如下：");
                        Consumer.printSingleConsumerMessage(consumer);
                        judge2 = 1;
                    }
                }
                if(judge2 != 1)
                {
                    System.out.println("未找到该用户");
                }
                break;

            case 3:
                Consumer.printAllConsumerMessage(consumerList);
                break;

            case 4:
                break;

            default:
                break;
        }
    }
    /*---end:用户管理功能---*/

    /*---start:退出功能---*/
    public static void logout(){
        System.out.println("退出经理系统");
    }
    /*---end:退出功能---*/

    public String getType() {
        return type;
    }


}
