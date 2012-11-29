package org.fangxm.benchmark;

import static java.lang.System.out;  
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Iterator;  
import java.util.LinkedList;  
import java.util.List;  
import java.util.Stack;  
import java.util.Vector;  
import java.util.concurrent.CopyOnWriteArrayList;  
import org.apache.commons.collections.FastArrayList;  
import org.apache.commons.collections.list.TreeList;  
import org.apache.commons.lang.StringUtils;  
import org.apache.commons.lang.time.StopWatch;  

/**
 * code from: http://blog.csdn.net/inkfish/article/details/5185320  Java中List效率的比较
 主要测试对象： 
　　java.util.ArrayList;
　　java.util.LinkedList;
　　java.util.Stack;
　　java.util.Vector;
　　java.util.concurrent.CopyOnWriteArrayList;
　　org.apache.commons.collections.FastArrayList;
　　org.apache.commons.collections.list.TreeList; 
测试用例： 
　　1.测试List 
　　　1.1顺序添加
 　　　1.2随机插入
 　　　1.3随机删除
 　　　1.4随机访问
 　　　1.5随机更新
 　　　1.5顺序迭代
 　　2.测试List 在三种情况下的排序效率
 　　　2.1初始时List 中元素已从小到大有序排列（最优情况）
 　　　2.2初始时List 中元素已从大到小有序排列（最差情况）
 　　　2.3初始时List 中元素随机排列，无序
 　　3.测试List 互相转换的效率
 　　　3.1转化为TreeList 
　　　3.2转化为ArrayList 
　　　3.3转化为LinkedList 
　　　3.4转化为CopyOnWriteArrayList 
　　　3.5转化为Vector

结论： 
　　1.随机插入、随机删除操作中，用TreeList 效率最高；
 　　2.在只需要追加、迭代的环境下，LinkedList 效率最高；
 　　3.平均效率来讲，ArrayList 相对平衡，但如果海量随机操作，还是会造成性能瓶颈；
 　　4.CopyOnWriteArrayList 因为线程安全的原因，致使性能降低很多，所以慎用；
 　　5.Vector 没有传说中那么低的效率；
 　　6.让Stack 来做List 的事可以，不过语义上Stack 不应该做过多的List 的事情；
 　　7.在排序中，ArrayList 具有最好的性能，TreeList 平均性能也不错，LinkedList 的排序效率受元素初始状态的影响很大。
 　　8.各种List 间转换几乎没有时间损耗。
 * @author Administrator
 *
 */

@SuppressWarnings({"unchecked", "rawtypes"}) 
public class ListPerformance {  
    public static void main(String[] args) {  
        ListPerformance test = new ListPerformance(10 * 10000);  
        out.print(StringUtils.center("Test List Performance: loop=" + test.loop, 80, '-'));  
        out.printf("/n%20s%10s%10s%10s%10s%10s%10s", "", "add", "insert", "remove", "get", "set", "iterator");
        test.benchmark(new FastArrayList());  
        test.benchmark(new TreeList());  
        test.benchmark(new ArrayList());  
        test.benchmark(new LinkedList());  
        test.benchmark(new CopyOnWriteArrayList());  
        test.benchmark(new Vector());  
        test.benchmark(new Stack());  
        //2.测试排序  
        out.print("/n/n");  
        out.print(StringUtils.center("Test List sort Performance: loop=" + test.loop, 80, '-'));  
        out.printf("/n%20s%10s%10s%10s", "", "optimize", "worst", "random");  
        test.benchmarkSort(new FastArrayList());  
        test.benchmarkSort(new TreeList());  
        test.benchmarkSort(new ArrayList());  
        test.benchmarkSort(new LinkedList());  
        //test.benchmarkSort(new CopyOnWriteArrayList());//UnsupportedOperationException  
        test.benchmarkSort(new Vector());  
        test.benchmarkSort(new Stack());  
        //3.测试各种数据结构间转化  
        out.print("/n/n");  
        out.print(StringUtils.center("Test List convert Performance: loop=" + test.loop, 80, '-'));  
        out.printf("/n%20s%10s%10s%10s%10s%10s", "", "Tree", "Array", "Linked", "CopyOnWrite",  
                "Vector");  
        test.benchmarkConvert(new FastArrayList());  
        test.benchmarkConvert(new TreeList());  
        test.benchmarkConvert(new ArrayList());  
        test.benchmarkConvert(new LinkedList());  
        test.benchmarkConvert(new CopyOnWriteArrayList());  
    }  
    /**测试循环次数*/  
    private int loop = 10000;  
    public ListPerformance(int loop) {  
        this.loop = loop;  
    }  
    public void benchmark(List list) {  
        out.printf("/n%20s", list.getClass().getSimpleName());  
        int j;  
        StopWatch watch = null;  
        //1.测试顺序性能(Add)  
        (watch = new StopWatch()).start();  
        for (int i = 0; i < loop; i++) {  
            list.add(new Integer(i));  
        }  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //2.测试随机插入性能(Random insert)  
        (watch = new StopWatch()).start();  
        for (int i = 0; i < loop; i++) {  
            j = (int) (Math.random() * loop);  
            list.add(j, new Integer(-j));  
        }  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //3.测试随机索引删除(Random remove)  
        (watch = new StopWatch()).start();  
        for (int i = 0; i < loop; i++) {  
            j = (int) (Math.random() * loop);  
            list.remove(j);  
        }  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //4.测试随机取数性能(Random get)  
        (watch = new StopWatch()).start();  
        for (int i = 0; i < loop; i++) {  
            j = (int) (Math.random() * loop);  
            list.get(j);  
        }  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //5.测试随机更新性能(Random set)  
        (watch = new StopWatch()).start();  
        for (int i = 0; i < loop; i++) {  
            j = (int) (Math.random() * loop);  
            list.set(j, j);  
        }  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //6.测试迭代性能(Iterator)  
        (watch = new StopWatch()).start();  
        Iterator<Object> iter = list.iterator();  
        while (iter.hasNext()) {  
            iter.next();  
        }  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
    }  
    public void benchmarkConvert(List list) {  
        out.printf("/n%20s", list.getClass().getSimpleName());  
        StopWatch watch = null;  
        //1.转TreeList  
        (watch = new StopWatch()).start();  
        new TreeList(list);  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //2.转ArrayList  
        (watch = new StopWatch()).start();  
        new ArrayList(list);  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //3.转LinkedList  
        (watch = new StopWatch()).start();  
        new LinkedList(list);  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //4.转CopyOnWriteArrayList  
        (watch = new StopWatch()).start();  
        new CopyOnWriteArrayList(list);  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //5.转Vector  
        (watch = new StopWatch()).start();  
        new Vector(list);  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
    }  
    public void benchmarkSort(List list) {  
        out.printf("/n%20s", list.getClass().getSimpleName());  
        StopWatch watch = null;  
        //1.顺序List  
        for (int i = 0; i < loop; i++) {  
            list.add(new Integer(i));  
        }  
        (watch = new StopWatch()).start();  
        Collections.sort(list);  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //2.逆序List  
        for (int i = loop - 1; i > 0; i--) {  
            list.add(new Integer(i));  
        }  
        (watch = new StopWatch()).start();  
        Collections.sort(list);  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
        //3.随机顺序List  
        for (int i = 0, j = 0; i < loop; i++) {  
            j = (int) (Math.random() * loop);  
            list.add(new Integer(j));  
        }  
        (watch = new StopWatch()).start();  
        Collections.sort(list);  
        watch.stop();  
        out.printf("%10d", watch.getTime());  
    }  
} 
