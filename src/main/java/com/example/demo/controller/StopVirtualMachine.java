package com.example.demo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class StopVirtualMachine {
	public void suspendVM(String name) throws IOException {
		System.out.println("---------Creare suspendVM.bat si scrierea in el---------");
        BufferedWriter bwr2 = new BufferedWriter(new FileWriter(new File("C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/suspendVM.bat")));
        bwr2.write("echo.\r\n" + 
        		"echo ------------------------------------ \r\n" + 
        		"echo * Acesta este mesajul din suspendVM.bat cum sa nu *\r\n" + 
        		"echo ------------------------------------\r\n" + 
        		"echo.\r\n" + 
        		"C:\r\n" + 
        		"cd C:\\Users\\tcorneanu\\Desktop\\Licenta\\MasinileVirtuale\\"+name+"\r\n" + 
        		"vagrant suspend");
		//flush the stream
		bwr2.flush();
		//close the stream
		bwr2.close();
		
		System.out.println("---------Executa suspendVM.bat---------");
		//Run the process
        Process p = Runtime.getRuntime().exec("cmd /c C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/suspendVM.bat");
        //Get the input stream
        InputStream is = p.getInputStream();
 
        //Read script execution results
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while ( (i = is.read()) !=-1 )
            sb.append((char)i);
        
        System.out.println("---------In urma rularii programului suspendVM.bat---------");
        System.out.println(sb.toString());
        
        BufferedWriter bwr3 = new BufferedWriter(new FileWriter(new File("C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/RaspunsDinCmdLaVagrantSuspend.txt")));
		
		//write contents of StringBuffer to a file
		bwr3.write(sb.toString());
		//flush the stream
		bwr3.flush();
		
		//close the stream
		bwr3.close();
	}
}
