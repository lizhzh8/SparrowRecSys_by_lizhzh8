package com.sparrowrecsys.online;
import com.sparrowrecsys.online.datamanager.DataManager;
import com.sparrowrecsys.online.service.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URI;
// Recsys Server,end point of online recommendation service

public class RecSysServer_demo {
    public static void main(String[] args) throws Exception{
        new RecSysServer().run();
    }
    // recsys server port number
    private static final int DEFAULT_PORT = 6010;
    public void run() throws Exception{
        int port = DEFAULT_PORT;
        try{
            port = Integer.parseInt(System.getenv("PORT"));
                    } catch (NumberFormatException ignored){}
        // set ip and port number
        InetSocketAddress inetAddress = new InetSocketAddress("0.0.0.0",port);
        Server server = new Server(inetAddress);
        // get index.html path
        URL webRootLocation = this.getClass().getResource("/webroot/index.html");


    }




}
