package socket.demo1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName:SocketClient.java
 * @Description:TODO （）
 * @Author:qzh
 * @Date: 2019/4/13 13:34
 * @Version 1.0
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        //ip地址
        String host = "127.0.0.1";
        int port = 8888;
        //与服务端建立连接
        Socket socket = new Socket(host, port);
        //获得输出流
        OutputStream outputStream = socket.getOutputStream();
        String message = "你好";
        outputStream.write(message.getBytes());
        outputStream.close();
        socket.close();
    }
}
