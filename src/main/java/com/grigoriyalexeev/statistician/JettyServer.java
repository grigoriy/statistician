package com.grigoriyalexeev.statistician;

//import org.eclipse.jetty.server.Connector;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.ServerConnector;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//import org.eclipse.jetty.webapp.WebAppContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.context.support.XmlWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import java.io.IOException;
//import java.net.URL;
//import java.security.ProtectionDomain;

public class JettyServer {
//    private  static Logger log = LoggerFactory.getLogger(JettyServer.class);
//    public static void main(String[] args) throws Exception {
//        Server server = new Server();
//
//        ServerConnector connector = new ServerConnector(server);
//        connector.setPort(8080);
//
//        server.setConnectors(new Connector[] { connector });
//
//        WebAppContext context = new WebAppContext();
//        context.setServer(server);
//        context.setContextPath("/");
//
//        ProtectionDomain protectionDomain = JettyServer.class.getProtectionDomain();
//        URL location = protectionDomain.getCodeSource().getLocation();
//        context.setWar(location.toExternalForm());
//
//        server.setHandler(context);
//        while (true) {
//            try {
//                server.start();
//                break;
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//            }
//        }
//        try {
//            System.in.read();
//            server.stop();
//            server.join();
//        } catch (Exception e) {
//            log.error("Exception while stopping Jetty server", e);
//            System.exit(100);
//        }
//    }
//
//    private static final int DEFAULT_PORT = 8080;
//    private static final String CONTEXT_PATH = "/";
//    private static final String CONFIG_LOCATION = "classpath*:dispatcher-servlet.xml";
//    private static final String MAPPING_URL = "/*";
//
//    public static void main(String[] args) throws Exception {
//        new JettyServer().startJetty(getPortFromArgs(args));
//    }
//
//    private static int getPortFromArgs(String[] args) {
//        if (args.length > 0) {
//            try {
//                return Integer.valueOf(args[0]);
//            } catch (NumberFormatException ignore) {
//            }
//        }
//        return DEFAULT_PORT;
//    }
//
//    private void startJetty(int port) throws Exception {
//        Server server = new Server(port);
//        server.setHandler(getServletContextHandler(getContext()));
//        server.start();
//        server.join();
//    }
//
//    private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
//        ServletContextHandler contextHandler = new ServletContextHandler();
//        contextHandler.setErrorHandler(null);
//        contextHandler.setContextPath(CONTEXT_PATH);
//        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
//        contextHandler.addEventListener(new ContextLoaderListener(context));
////        contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
//        return contextHandler;
//    }
//
//    private static WebApplicationContext getContext() {
//        XmlWebApplicationContext context = new XmlWebApplicationContext();
//        context.setConfigLocations("classpath*:applicationContext.xml", CONFIG_LOCATION);
//        return context;
//    }

}
