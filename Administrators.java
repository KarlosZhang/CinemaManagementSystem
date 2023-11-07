import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Administrators extends Enrollee{
    LinkedList<Manager> managerList;
    LinkedList<Reception> receptionList;
    public Administrators(String name, String password){
        this.name = name;
        this.password = password;
        managerList = new LinkedList<>();
        receptionList = new LinkedList<>();
    }

    /*---start:管理员方法---*/
    public void administratorMethods()
    {
        int judgement = 0;
        login();
        while (true)
        {
            this.menus();
            int number = ExceptionHanding.IntExceptionHanding();
            switch (number)
            {
                case 1:
                    managePassword();
                    break;

                case 2:
                    manageUser();
                    break;

                case 3:
                    logout();
                    judgement = 1;
                    break;

                default:
                    break;
            }
            if (judgement == 1)
            {
                break;
            }
        }
    }
    /*---end:管理员方法---*/

    /*---start:菜单功能---*/
    public void menus()
    {
        System.out.println("以下为管理员系统可执行的操作");
        System.out.println("1、密码管理");
        System.out.println("2、用户管理");
        System.out.println("3、退出管理员系统");
        System.out.println("----------------------------");
    }
    /*---end:菜单功能---*/

    /*---start:密码管理功能---*/
    public void managePassword()
    {
        int judgement = 0; //退出循环标志
        while (true)
        {
            System.out.println("1、修改密码");
            System.out.println("2、重置指定影城用户的密码");
            System.out.println("3、退出密码管理功能");
            System.out.println("----------------------------");
            int number = ExceptionHanding.IntExceptionHanding();
            Scanner sc = new Scanner(System.in);
            switch (number)
            {
                case 1:
                    System.out.print("请输入新密码:");
                    setPassword(sc.next());
                    break;

                case 2:
                    changeUserPassword();
                    break;

                case 3:
                    judgement = 1;
                    break;

                default:
                    break;
            }
            //退出循环
            if(judgement == 1)
            {
                System.out.println("退出密码管理功能");
                break;
            }
        }
    }

    //2.2重置指定影城方用户（经理、前台）的密码
    public void changeUserPassword()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入影城用户类型：");
        String type = sc.next();
        int judge = 0;
        if(type.equals("经理"))
        {
            System.out.print("请输入该经理用户的用户名：");
            String name = sc.next();
            Manager.changePasswordName = name;
            Manager.setPassword(managerList, Manager.changePasswordName);
        }
        if(type.equals("前台"))
        {
            System.out.print("请输入该前台用户的用户名：");
            String name = sc.next();
            Reception.changePasswordName = name;
            Reception.setPassword(receptionList, Reception.changePasswordName);
        }
    }
    /*---end:密码管理功能---*/

    /*---start:用户管理功能---*/
    public void manageUser()
    {
        int judgement = 0;//退出循环标志位
        while (true)
        {
            System.out.println("----------------------------");
            System.out.println("1、列出所有影城方用户信息");
            System.out.println("2、删除影城方用户信息");
            System.out.println("3、查询影城方用户信息");
            System.out.println("4、增加影城方用户信息");
            System.out.println("5、修改影城方用户信息");
            System.out.println("6、退出用户管理功能");

            int number = ExceptionHanding.IntExceptionHanding();

            switch (number)
            {
                case 1:
                    printAllUserMessage();
                    break;

                case 2:
                    deleteUserMessage();
                    break;

                case 3:
                    inquireUserMessage();
                    break;

                case 4:
                    addUser();
                    break;

                case 5:
                    changeUserMessage();
                    break;

                case 6:
                    judgement = 1;

                default:
                    break;

            }
            if(judgement == 1)
            {
                System.out.println("退出用户管理功能");
                break;
            }
        }
    }

    //3.1打印所有影城方用户信息
    public void printAllUserMessage()
    {
        System.out.println("----------------------------");
        System.out.println("下列为经理的信息：");
        for (Manager manager: managerList)
        {
            printSingleManagerMessage(manager);
        }
        System.out.println("----------------------------");
        System.out.println("下列为前台的信息：");
        for (Reception reception: receptionList)
        {
            printSingleReceptionMessage(reception);
        }
        System.out.println("----------------------------");
        System.out.println("已列出所有影城方用户信息");
    }

    //打印单个经理信息
    public void printSingleManagerMessage(Manager manager)
    {
        System.out.println("用户ID：" + manager.id);
        System.out.println("用户名：" + manager.name);
        System.out.println("用户注册时间：" + manager.registerTime);
        System.out.println("用户类型：" + manager.getType());
        System.out.println("用户手机号：" + manager.phoneNumber);
        System.out.println("用户邮箱：" + manager.email);
        System.out.println();
    }

    //打印单个前台信息
    public void printSingleReceptionMessage(Reception reception)
    {
        System.out.println("用户ID：" + reception.id);
        System.out.println("用户名：" + reception.name);
        System.out.println("用户注册时间：" + reception.registerTime);
        System.out.println("用户类型：" + reception.getType());
        System.out.println("用户手机号：" + reception.phoneNumber);
        System.out.println("用户邮箱：" + reception.email);
        System.out.println();
    }

    //3.2删除影城方用户信息
    public void deleteUserMessage()
    {
        Scanner sc = new Scanner(System.in);
        int deleteJudge = 0;
        System.out.println("请输入你要删除用户的id：");
        String id = sc.next();
        for (Manager manager: managerList)
        {
            if(id.equals(manager.id))
            {
                System.out.print("已找到该用户，确认要删除吗？（输入是或否）：");
                String judge = sc.next();
                if(judge.equals("是"))
                {
                    managerList.remove(manager);
                    deleteJudge = 1;
                    System.out.println("已删除该用户");
                    break;
                }
                else
                {
                    System.out.println("取消删除");
                    break;
                }
            }
        }
        for (Reception reception: receptionList)
        {
            if(id.equals(reception.id))
            {
                System.out.print("已找到该用户，确认要删除吗？（输入是或否）：");
                String judge = sc.next();
                if(judge.equals("是"))
                {
                    receptionList.remove(reception);
                    deleteJudge = 1;
                    System.out.println("已删除该用户");
                    break;
                }
                else
                {
                    System.out.println("取消删除");
                    break;
                }
            }
        }
        if(deleteJudge != 1)
        {
            System.out.println("未找到该用户");
        }
    }

    //3.3查询影城方用户信息
    public void inquireUserMessage()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------");
        System.out.println("1、ID");
        System.out.println("2、用户名");
        System.out.println("3、一次查询影城方用户信息");
        System.out.println("4、退出查询功能");
        int number = ExceptionHanding.IntExceptionHanding();
        int judge = 0;
        switch (number)
        {
            case 1:
                System.out.print("请输入ID：");
                String findID = sc.next();
                for(Manager manager: managerList)
                {
                    if(findID.equals(manager.id))
                    {
                        System.out.println();
                        System.out.println("查询到该用户，用户信息如下：");
                        printSingleManagerMessage(manager);
                        judge = 1;
                    }
                }
                for (Reception reception:receptionList)
                {
                    if(findID.equals(reception.id))
                    {
                        System.out.println();
                        System.out.println("查询到该用户，用户信息如下：");
                        printSingleReceptionMessage(reception);
                        judge = 1;
                    }
                }
                if(judge != 1)
                {
                    System.out.println("未找到该用户");
                }
                break;

            case 2:
                System.out.print("请输入用户名：");
                String findName = sc.next();
                for(Manager manager: managerList)
                {
                    if(findName.equals(manager.name))
                    {
                        System.out.println();
                        printSingleManagerMessage(manager);
                        judge = 1;
                    }
                }
                for (Reception reception:receptionList)
                {
                    if(findName.equals(reception.name))
                    {
                        System.out.println();
                        printSingleReceptionMessage(reception);
                        judge = 1;
                    }
                }
                if(judge != 1)
                {
                    System.out.println("未找到该用户");
                }
                break;

            case 3:
                printAllUserMessage();
                break;

            case 4:
                break;

            default:
                break;

        }
    }

    //唯一性检查
    public boolean unquenessCheck(String id, String name, String phoneNumber, String email){
        for(Manager manager: managerList)
        {
            if(id.equals(manager.id))
            {
                System.out.println(id + "与其它经理ID重复，添加信息失败");
                return false;
            }
            if(name.equals(manager.name))
            {
                System.out.println(name + "与其它经理用户名重复，添加信息失败");
                return false;
            }
            if(phoneNumber.equals(manager.phoneNumber))
            {
                System.out.println(phoneNumber + "与其它经理手机号重复，添加信息失败");
                return false;
            }
            if(email.equals(manager.email))
            {
                System.out.println(email + "与其它经理邮箱重复，添加信息失败");
                return false;
            }
        }
        for (Reception reception: receptionList)
        {
            if(id.equals(reception.id))
            {
                System.out.println(id + "ID与其它前台ID重复，添加信息失败");
                return false;
            }
            if(name.equals(reception.name))
            {
                System.out.println(name + "与其它前台用户名重复，添加信息失败");
                return false;
            }
            if(phoneNumber.equals(reception.phoneNumber))
            {
                System.out.println(phoneNumber + "与其它前台手机号重复，添加信息失败");
                return false;
            }
            if(email.equals(reception.email))
            {
                System.out.println(email + "与其它前台邮箱重复，添加信息失败");
                return false;
            }
        }
        return true;
    }


    //3.4增加用户信息
    public void addUser(){
        System.out.println("添加信息：");
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户ID:");
        String id = sc.next();
        System.out.print("请输入用户名:");
        String name = sc.next();
        System.out.print("请输入用户注册时间:");
        String registerTime = sc.next();
        System.out.print("请输入用户类型（经理/前台）:");
        String type = sc.next();
        System.out.print("请输入用户手机号:");
        String phoneNumber = sc.next();
        System.out.print("请输入用户邮箱:");
        String email = sc.next();
        if(unquenessCheck(id, name, phoneNumber, email))
        {
            if(type.equals("经理"))
            {
                Manager manager = new Manager(name, name, email, id, registerTime, phoneNumber);
                managerList.add(manager);
                System.out.println("已添加经理信息，初始密码与用户名相同");
            }
            if(type.equals("前台"))
            {
                Reception reception = new Reception(name, name, email, id, registerTime, phoneNumber);
                receptionList.add(reception);
                System.out.println("已添加前台信息，初始密码与用户名相同");
            }
        }
    }

    //3.5修改用户信息
    public void changeUserMessage()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入你要修改的用户的ID：");
        String id = sc.next();
        int judge = 0;
        for (Manager manager: managerList)
        {
            if(id.equals(manager.id))
            {
                System.out.println("找到该经理用户");
                System.out.print("输入新的用户类型：");
                String type = sc.next();
                System.out.print("输入新的用户手机号：");
                manager.phoneNumber = sc.next();
                System.out.print("输入新的用户邮箱：");
                manager.email = sc.next();
                if(type.equals("前台"))
                {
                    Reception reception = new Reception(manager.name, manager.password, manager.email, manager.id, manager.registerTime, manager.phoneNumber);
                    managerList.remove(manager);
                    receptionList.add(reception);
                }
                System.out.println("修改完成");
                judge = 1;
                break;
            }
        }
        for (Reception reception: receptionList) {
            if (id.equals(reception.id)) {
                System.out.println("找到该前台用户");
                System.out.print("输入新的用户类型：");
                String type = sc.next();
                System.out.print("输入新的用户手机号：");
                reception.phoneNumber = sc.next();
                System.out.print("输入新的用户邮箱：");
                reception.email = sc.next();
                if (type.equals("经理")) {
                    Manager managerTemp = new Manager(reception.name, reception.password, reception.email, reception.id, reception.registerTime, reception.phoneNumber);
                    receptionList.remove(reception);
                    managerList.add(managerTemp);
                }
                System.out.println("修改完成");
                judge = 1;
                break;
            }
        }
        if(judge != 1)
        {
            System.out.println("未找到该用户，修改失败");
        }
    }
    /*---end:用户管理功能---*/


    /*---start:退出功能---*/
    public void logout(){
        System.out.println("退出管理员系统");
    }
    /*---end:退出功能---*/

}
