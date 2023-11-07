import java.util.Scanner;

public abstract class Enrollee {
    protected String name;
    protected String password;
    protected String email;
    protected String id;
    protected String registerTime;
    protected String phoneNumber;

    public Enrollee(){};
    public Enrollee(String name, String password, String email, String id, String registerTime, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.registerTime = registerTime;
        this.phoneNumber = phoneNumber;
    }


    public void login()
    {
        int judgement = 0;
        boolean isValue = false;
        Scanner sc = new Scanner(System.in);
        while (!isValue)
        {
            System.out.print("请输入账号：");
            String admin = sc.next();
            System.out.print("请输入密码：");
            String password = sc.next();
            if(admin.equals(this.name) && password.equals(this.password))
            {
                System.out.println("登录成功");
                isValue = true;
            }
            else
            {
                System.out.println("账号或密码错误，登录失败");
                System.out.print("是否退出登录（是/否）：");
                String judge = sc.next();
                if(judge.equals("是"))
                {
                    System.out.println("退出登录");
                    isValue = true;
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
