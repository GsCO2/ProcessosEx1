package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	public RedesController() {
		super();
	}
	private String os() {
		return System.getProperty("os.name"); 
	}
	public void ip() {
		String os = os(); 
		String proc = null;
		if(os.contains("Windows")) { 
			proc = "IPCONFIG"; 
		} else if(os.contains("Linux")) {
			proc = "ip addr";
		} else {
			System.out.println("SISTEMA OPERACIONAL NÃO IDENTIFICADO");
			return;
		}
		String[] procarr = proc.split(" "); 
		try {
			Process p = Runtime.getRuntime().exec(procarr);
			InputStream flow = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(flow);
			BufferedReader buffer = new BufferedReader(reader);
			String linha;
			String adaptador = null;
			String ipv4;
			while((linha = buffer.readLine()) != null) {
				if(os.contains("Windows")) {
					if(linha.contains("Adaptador")) {
						adaptador = linha.split(":")[0];
					}
					if(linha.contains("IPv4")) {
						ipv4 = linha.split(":")[1];
						if(adaptador != null) {
							System.out.println(adaptador + " - IPv4: " + ipv4);
						}
					}
				}  else if (os.contains("Linux")) {
				    if(linha.contains("MULTICAST")) {
				    	adaptador = linha.split(": ")[1];
				    }
				    if(linha.contains("dynamic")) {
				    	ipv4 = linha.split(" ")[5];
				    	if(adaptador != null) {
				    		System.out.println(adaptador + " - Ipv4: " + ipv4);
				    	}
				    }
				}
			}
			buffer.close();
			reader.close();
		}	catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void ping() {
		String os = os();
		String proc = null;
		if(os.contains("Windows")) { 
			proc = "ping -4 -n 10 www.google.com.br"; 
		} else if(os.contains("Linux")) {
			proc = "ping -4 -c 10 www.google.com.br";
		} else {
			System.out.println("SISTEMA OPERACIONAL NÃO IDENTIFICADO");
			return;
		}
		String[] procarr = proc.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(procarr);
			InputStream flow = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(flow, "CP850");
			BufferedReader buffer = new BufferedReader(reader);
			String linha;
			while((linha = buffer.readLine()) != null){
				if(os.contains("Windows")) {
					if(linha.contains("Média")){
						String[] partes = linha.split(", ");
						String media = partes[2];
						System.out.println(media);
					}
				} else if(os.contains("Linux")) {
					if(linha.contains("avg")) {
						String[] partes = linha.split("/");
						String media = partes[4];
						System.out.println("Média = " + media + "ms");
					}
				}
			}
			reader.close();
			buffer.close();
			
		} 	catch (Exception e) {
			System.err.println(e);
		}
	}
}