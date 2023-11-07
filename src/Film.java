import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Film {
    private String name;
    private String director;
    private String mainActor;
    private String plot;
    private String duration;

    public Film(String name, String director, String mainActor, String plot, String duration) {
        this.name = name;
        this.director = director;
        this.mainActor = mainActor;
        this.plot = plot;
        this.duration = duration;
    }

    public Film(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    //列出所有正在上映影片的信息
    public static void printAllFilm(LinkedList<Film> filmList)
    {
        if(filmList.isEmpty())
        {
            System.out.println("目前无影片上映！");
            return;
        }
        System.out.println();
        for(Film film: filmList)
        {
            printSingleFilm(film);
        }
        System.out.println("----------------------------");
        System.out.println("已列出所有正在上映影片的信息");
    }
    //列出单个影片的信息
    public static void printSingleFilm(Film film)
    {
        System.out.println("片名：" + film.getName());
        System.out.println("导演：" + film.getDirector());
        System.out.println("主演：" + film.getMainActor());
        System.out.println("剧情简介：" + film.getPlot());
        System.out.println("时长：" + film.getDuration());
        System.out.println();
    }

}
