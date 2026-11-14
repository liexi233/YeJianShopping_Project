package context;
//ThreadLocal
public class BaseContext {

    public static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    //设置盒子;
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    //其他的也是;
    public static Long getCurrentId(){
        return threadLocal.get();
    }
    public static void removeCurrentId(){
        threadLocal.remove();
        //移除这个线程用户的id,线程复用用的是线程池技术;
    }




}
