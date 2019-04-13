package UDP;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @ClassName:MyServer.java
 * @Description:TODO （）
 * @Author:qzh
 * @Date: 2019/4/10 17:56
 * @Version 1.0
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        //1、创建服务端+端口
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        //2、准备接受容器
        byte[] container = new byte[1024];
        //3、封装成包
        DatagramPacket datagramPacket = new DatagramPacket(container, container.length);
        //4、接受数据
        datagramSocket.receive(datagramPacket);
        //5、分析数据
        byte[] data = datagramPacket.getData();
        double convertdouble = convert(data);
        System.out.println(convertdouble);
        //6、释放
        datagramSocket.close();
    }

    public static double convert(byte[] data) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        double readDouble = dis.readDouble();
        dis.close();
        return readDouble;
    }
}
