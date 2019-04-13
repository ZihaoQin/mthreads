package UDP;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @ClassName:MyClient.java
 * @Description:TODO （）
 * @Author:qzh
 * @Date: 2019/4/10 17:56
 * @Version 1.0
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        //1、创建客户端+端口
        DatagramSocket client = new DatagramSocket(6666);
        //2、准备数据
        double num = 89.34;
        byte[] bytes = convert(num);
        //3、打包
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, new InetSocketAddress("localhost", 8888));
        //4、发送
        client.send(packet);
        //5、释放
        client.close();
    }

    private static byte[] convert(double num) throws IOException {
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeDouble(num);
        dos.flush();

        data = bos.toByteArray();
        dos.close();
        return data;

    }
}
