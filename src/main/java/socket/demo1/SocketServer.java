package socket.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName:SocketServer.java
 * @Description:TODO （）
 * @Author:qzh
 * @Date: 2019/4/13 13:24
 * @Version 1.0
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        //监听指定的端口
        int port = 8888;
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("准备接受信息");
        Socket socket = serverSocket.accept();

        //建立好连接后，从socket中获取输入流
        InputStream inputStream = socket.getInputStream();

        //接收读取的数据的容器
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            //指定编码格式
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        System.out.println("从客户端接收到消息：" + sb);
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
