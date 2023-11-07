import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class FilmHall {
    private String id;
    private int row = 7;
    private int col = 12;
    private Film film;
    private int seat[][];
    private double price;
    private Date startTime;
    private Date endTime;
    private String changCi;

    public FilmHall(String id, int row, int col, Film film, double price, String startTime, String endTime, String changCi) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        this.id = id;
        this.row = row;
        this.col = col;
        this.film = film;
        int[][] test = new int[row][col];
        this.seat = test;
        this.price = price;
        this.startTime = sdf.parse(startTime);
        this.endTime = sdf.parse(endTime);
        this.changCi = changCi;
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getSingleSeatState(int row, int col) {
        return seat[row][col];
    }
    public void chooseSeat(int row, int col) {
        seat[row][col] = 1;
    }

    public void cancelSeat(int row, int col) {
        seat[row][col] = 0;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //查看该放映厅座位信息
    public static void getAllSeatState(FilmHall filmHall) {
        for (int i = 0; i < filmHall.getRow() + 1; i++) {
            for (int j = 0; j < filmHall.getCol() + 1; j++) {
                if(i == 0)
                {
                    System.out.print(j + " ");
                    continue;
                }
                if(i != 0 && j == 0)
                {
                    System.out.print(i + " ");
                    continue;
                }
                if(i != 0 && j != 0)
                {
                    System.out.print(filmHall.getSingleSeatState(i - 1, j - 1) + " ");
                }
                if(j >= 10)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setStartTime(String startTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        this.startTime = sdf.parse(startTime);
    }
    public void setEndTime(String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        this.endTime = sdf.parse(endTime);
    }

    public String getChangCi() {
        return changCi;
    }

    public void setChangCi(String changCi) {
        this.changCi = changCi;
    }

    //计算空闲座位数
    public static int countAvailableSeat(FilmHall filmHall){
        int count = 0;
        for (int i = 0; i < filmHall.getRow(); i++)
        {
            for (int j = 0; j < filmHall.getCol(); j++)
            {
                if(filmHall.getSingleSeatState(i,j) == 1)
                {
                    count++;
                }
            }
        }
        return (filmHall.getRow() * filmHall.getCol()) - count;
    }

    //列出所有场次信息
    public static void printAllSession(LinkedList<FilmHall> filmHallList)
    {
        System.out.println();
        Date nowTime = new Date();
        for(FilmHall filmHall: filmHallList)
        {
            if(((filmHall.getStartTime().getTime() - nowTime.getTime()) / (1000 * 60 * 60 * 24)) <= 7)
            {
                printSingleSeesionMessage(filmHall);
            }
        }
        System.out.println("----------------------------");
        System.out.println("已列出所有最近一周场次的信息");
    }

    //打印单个场次信息
    public static void printSingleSeesionMessage(FilmHall filmHall)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        System.out.println("该场次放映厅ID为：" + filmHall.getId());
        System.out.println("该场次放映的电影为：" + filmHall.getFilm().getName());
        System.out.println("该场次的总座位数为：" + filmHall.getRow() * filmHall.getCol());
        System.out.println("空闲座位数：" + FilmHall.countAvailableSeat(filmHall));
        System.out.println("该场次的价格为：" + filmHall.getPrice());
        System.out.println("该场次的开始时间为：" + sdf.format(filmHall.getStartTime()));
        System.out.println("该场次的结束时间为：" + sdf.format(filmHall.getEndTime()));
        System.out.println("该场次的场次为：" + filmHall.getChangCi());
        System.out.println();
    }

    //列出指定电影和场次的信息
    public static void printSpecialSession(LinkedList<FilmHall> filmHallList)
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
            }
        }
        if(judgement != 1)
        {
            System.out.println("未找到该电影场次！");
        }

    }
}
