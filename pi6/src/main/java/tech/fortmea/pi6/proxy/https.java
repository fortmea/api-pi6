package tech.fortmea.pi6.proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/velho")
public class https {
 
	@GetMapping("/**")
   private String testIt(HttpServletRequest request){
	System.out.println(request.getQueryString());
    String requestURL = request.getRequestURL().substring(24)+"?"+request.getQueryString();
	

      URL url;
      try {
        try{
	     url = new URL(requestURL);
        }catch(Exception e){
            url = new URL("https://"+requestURL);
        }
	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			
	     //dumpl all cert info
	     //print_https_cert(con);
			
	     //dump all the content
	     return print_content(con);
			
      } catch (MalformedURLException e) {
	     e.printStackTrace();
      } catch (IOException e) {
	     e.printStackTrace();
      }
      return "";
   }
	
   private void print_https_cert(HttpsURLConnection con){
     
    if(con!=null){
			
      try {
				
	System.out.println("Response Code : " + con.getResponseCode());
	System.out.println("Cipher Suite : " + con.getCipherSuite());
	System.out.println("\n");
				
	Certificate[] certs = con.getServerCertificates();
	for(Certificate cert : certs){
	   System.out.println("Cert Type : " + cert.getType());
	   System.out.println("Cert Hash Code : " + cert.hashCode());
	   System.out.println("Cert Public Key Algorithm : " 
                                    + cert.getPublicKey().getAlgorithm());
	   System.out.println("Cert Public Key Format : " 
                                    + cert.getPublicKey().getFormat());
	   System.out.println("\n");
	}
				
	} catch (SSLPeerUnverifiedException e) {
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	}

     }
	
   }
	
   private String print_content(HttpsURLConnection con){
    String contents = "";
	if(con!=null){
			
	try {
		
	   BufferedReader br = 
		new BufferedReader(
			new InputStreamReader(con.getInputStream()));
				
	   String input;
        
	   while ((input = br.readLine()) != null){
	     contents += input;
	   }

	   br.close();
				
	} catch (IOException e) {
	   e.printStackTrace();
       contents = "403";
	}
			
       }
		return contents;
   }
}
