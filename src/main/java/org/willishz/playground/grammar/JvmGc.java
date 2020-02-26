package org.willishz.playground.grammar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * jvm的gc测试
 */
public class JvmGc {

    public static void main(String[] args) {
        JvmGc.metaspaceOOM();
    }

    /**
     * OutOfMemoryError: Java heap space
     * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+UseConcMarkSweepGC
     */
    private static void heapOOM() {
        List<JvmGc> list = new ArrayList();
        while (true) {
            list.add(new JvmGc());
        }
    }

    /**
     * StackOverflowError
     * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+UseConcMarkSweepGC
     */
    private static void stackLeak() {
        stackLeak();
    }

    /**
     * jdk8常量池已经在堆内存里了
     * OutOfMemoryError: Java heap space
     * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+UseConcMarkSweepGC
     * https://blog.csdn.net/misterfm/article/details/80680732
     */
    private static void runtimeConstantPoolOOM() {
        List<String> list = new ArrayList();
        String append = "very long string";
        while (true) {
            append += append;
            list.add(append.intern());
        }  //intern() :若常量池没有当前的这个对象，则将此对相关加入常量池。
    }

    /**
     * OutOfMemoryError: Metaspace
     * -verbose:gc -verbose:class -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=1m -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+UseConcMarkSweepGC
     */
    private static void metaspaceOOM() {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            int i = 1;
            while (true) {
                System.out.println(i++);
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("org.willishz.playground.grammar.JvmGc");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * class space不足导致频繁Full GC
     * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=20m -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+UseConcMarkSweepGC
     * https://www.jianshu.com/p/028ab6d96a4d
     */
    private static void classLoaderOOM() {
        while (true) {
            try {
                Class clazz0 = new MyClassLoader().loadClass("org.willishz.playground.grammar.JvmGc");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                String filePath = "D:/workspace/playground/target/classes/" + name.replace('.', File.separatorChar) + ".class";
                //指定读取磁盘上的某个文件夹下的.class文件：
                File file = new File(filePath);
                FileInputStream fis = new FileInputStream(file);
                byte[] bytes = new byte[fis.available()];
                fis.read(bytes);
                //调用defineClass方法，将字节数组转换成Class对象
                Class<?> clazz = this.defineClass(name, bytes, 0, bytes.length);
                fis.close();
                return clazz;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
            return super.findClass(name);
        }
    }
}
