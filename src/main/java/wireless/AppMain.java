package wireless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import wireless.udpserver.UdpServer;
import wireless.utils.Utils;

@SpringBootApplication
@ComponentScan({"wireless"})
public class AppMain {
    public static void main( String[] args ) {
    	
    	SpringApplication.run(AppMain.class, args);
    	
    	try {
    		if(args==null || args.length==0) new UdpServer(Utils.DEFAULT_PORT).run();
    		else new UdpServer(Integer.valueOf(args[0])).run();    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }    
}
