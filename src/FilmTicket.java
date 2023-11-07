import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class FilmTicket {
    private String id;
    private int row;
    private int col;
    private double cost;
    private Date buyTime;
    FilmHall filmHall;


    public FilmTicket(int row, int col, double cost, FilmHall filmHall, Date buyTime) {
        this.id = "XAD-123-423DGGUID";
        this.row = row;
        this.col = col;
        this.cost = cost;
        this.filmHall = filmHall;
        this.buyTime = buyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }



    /*---start:打印电影票功能---*/
    public static void printFilmTicket(FilmTicket filmTicket)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        System.out.println("以下为电影票信息：");
        System.out.println("电影片名为：" + filmTicket.filmHall.getFilm().getName());
        System.out.println("电影片长为：" + filmTicket.filmHall.getFilm().getDuration());
        System.out.println("电影放映厅为：" + filmTicket.filmHall.getId());
        System.out.println("电影开始时间为：" + sdf.format(filmTicket.filmHall.getStartTime()));
        System.out.println("电影结束时间为：" + sdf.format(filmTicket.filmHall.getEndTime()));
        System.out.println("该场次座位信息为：" + filmTicket.getRow() + "排" + filmTicket.getCol() + "列");
        System.out.println();
    }
    /*---end:打印电影票功能---*/

    /*---start:售票功能---*/
    public static void sellFilmTicket(LinkedList<Consumer> consumerList, LinkedList<FilmHall> filmHallList)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        int judgement = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入电影片名：");
        String ticketFilmName = sc.next();
        System.out.print("请输入场次：");
        String changCi = sc.next();
        System.out.print("请输入用户名：");
        String name = sc.next();
        System.out.print("请输入手机号：");
        String phoneNumber = sc.next();
        for(Consumer consumer: consumerList)
        {
            if(name.equals(consumer.name) || phoneNumber.equals(consumer.phoneNumber))
            {
                System.out.println("已找到该消费者");
                judgement = 1;
                int judge = 0;
                for (FilmHall filmHall: filmHallList)
                {
                    if(changCi.equals(filmHall.getChangCi()))
                    {
                        judge = 1;
                        System.out.println("找到该场次");
                        if(ticketFilmName.equals(filmHall.getFilm().getName()))
                        {
                            System.out.println("该场次的电影为：" + ticketFilmName);
                            System.out.print("请选择你的排数：");
                            int row = ExceptionHanding.NormalIntExceptionHanding();
                            System.out.print("请选择你的列数：");
                            int col = ExceptionHanding.NormalIntExceptionHanding();
                            System.out.println("需要支付的金额为：" + consumer.getDiscount() * filmHall.getPrice());
                            double cost = consumer.getDiscount() * filmHall.getPrice();
                            System.out.println("请输入支付金额：");
                            double costTest = sc.nextDouble();
                            consumer.setTotalConsumeCount(consumer.getTotalConsumeCount() + 1);
                            consumer.setTotalConsumeMoney(consumer.getTotalConsumeMoney() + costTest);
                            if(Math.abs(costTest - cost) < 1e-6)
                            {
                                System.out.println("支付成功");
                            }
                            else
                            {
                                System.out.println("金额不够，支付失败！");
                            }
                            boolean choose = false;
                            while(!choose)
                            {
                                if (filmHall.getSingleSeatState(row - 1, col - 1) == 0)
                                {
                                    filmHall.chooseSeat(row - 1, col - 1);
                                    FilmHall.getAllSeatState(filmHall);
                                    Date nowTime = new Date();
                                    FilmTicket newfilmTicket = new FilmTicket(row - 1, col - 1, cost, filmHall, nowTime);
                                    //System.out.print("请输入电影票ID：");
                                    //String id = sc.next();
                                    //newfilmTicket.setId(id);
                                    consumer.filmTicketList.add(newfilmTicket);
                                    System.out.println("已成功购买该电影票，下面为该电影票信息：");
                                    System.out.println("电影票ID为：" + newfilmTicket.id);
                                    System.out.println("放映厅ID为：" + newfilmTicket.filmHall.getId());
                                    System.out.println("放映开始时间：" + sdf.format(newfilmTicket.filmHall.getStartTime()));
                                    System.out.println("放映结束时间：" + sdf.format(newfilmTicket.filmHall.getEndTime()));
                                    System.out.println("片名：" + newfilmTicket.filmHall.getFilm().getName());
                                    System.out.println("片长：" + newfilmTicket.filmHall.getFilm().getDuration());
                                    System.out.println("座位为：" + newfilmTicket.row + "排" + newfilmTicket.col + "列");
                                    System.out.println("购票时间为：" + sdf.format(newfilmTicket.buyTime));
                                    System.out.println();
                                    choose = true;
                                }
                                else
                                {
                                    System.out.println("该座位已有人，购票失败！");
                                }
                            }
                        }
                        else
                        {
                            System.out.println("该场次的电影不是：" + ticketFilmName + ",购票失败！");
                        }
                    }
                }
                if(judge != 1)
                {
                    System.out.println("未找到该场次，购票失败！");
                }
            }
        }
        if(judgement != 1)
        {
            System.out.println("未找到该消费者，购票失败！");
        }
    }
    /*---end:售票功能---*/

}
