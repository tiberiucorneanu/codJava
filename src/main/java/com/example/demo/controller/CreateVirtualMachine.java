package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.VirtualMachine;
import com.example.demo.repo.VirtualMachineRepo;

public class CreateVirtualMachine {
	@Autowired
	private VirtualMachineRepo repo;
	
	public void createVM(VirtualMachine vm) throws IOException {
		String name = vm.name;
		String vm_box = "ubuntu/trusty64";
		String memori = vm.memori;
		String cpu = vm.cpu;
		String vram = "123"; //memoria video
		String description = vm.description;
		String stingApp = "mysql,python,java,apache,maven";
		String[] aplicatii = stingApp.split(",");
		
		System.out.println("-----------Lista aplicatiilor-----------");
		for(String a: aplicatii)
			System.out.println(a);
		//# Display the VirtualBox GUI when booting the machine
		//vb.gui = true
		//# Enable 3D acceleration:
		//vb.customize ["modifyvm", :id, "--accelerate3d", "on"]
		
		System.out.println("-----------Creare un nou folder "+name+"--------------");
		// create an abstract pathname (File object) 
        File f = new File("C:\\Users\\tcorneanu\\Desktop\\Licenta\\MasinileVirtuale\\"+name); 
        // check if the directory can be created 
        // using the abstract path name 
        if (f.mkdir()) { 
            // display that the directory is created 
            // as the function returned true 
            System.out.println("Directory is created"); 
        } 
        else { 
            // display that the directory cannot be created 
            // as the function returned false 
            System.out.println("Directory cannot be created"); 
        } 
        System.out.println("---------Creare un fisier Vagrantfile---------");
		//Crearea si completarea fisierului Vagrantfile
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/Vagrantfile")));
		bwr.write("Vagrant.configure(\"2\") do |config|\r\n" + 
				"  config.vm.box = \""+vm_box+"\"\r\n" + 
				"  config.vm.post_up_message =\"Felicitari ai reusit sa configurezi masina virtuala\"\r\n" +
				"  config.vm.provider \"virtualbox\" do |vb|\r\n" + 
				"    config.vm.provider :virtualbox do |vb|\r\n" + 
				"      vb.name = \""+name+"\"\r\n" + 
				"    end\r\n" + 
				"    vb.gui = true\r\n" + 
				"    vb.memory = \""+memori+"\"\r\n" + 
				"    vb.cpus = "+cpu+"\r\n" + 
				"    vb.customize [\"modifyvm\", :id, \"--vram\", \""+vram+"\"]\r\n"+
				"    vb.customize [\"modifyvm\", :id, \"--description\", \""+description+"\"]\r\n"+
				"    vb.customize [\"modifyvm\", :id, \"--accelerate3d\", \"on\"]\r\n"+
				"    vb.customize [\"modifyvm\", :id, \"--usb\", \"on\"]\r\n"+
				"  end\r\n" +
				"end");
		bwr.flush();
		bwr.close();
		
		System.out.println("---------Creare createVM.bat si scrierea in el---------");
        BufferedWriter bwr2 = new BufferedWriter(new FileWriter(new File("C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/createVM.bat")));
        bwr2.write("echo.\r\n" + 
        		"echo ------------------------------------ \r\n" + 
        		"echo * Acesta este mesajul din createVM.bat cum sa nu *\r\n" + 
        		"echo ------------------------------------\r\n" + 
        		"echo.\r\n" + 
        		"echo \"%CD%\"\r\n" + 
        		"cd C:\\Users\\tcorneanu\\Desktop\\Licenta\\JavaBiblioteci\r\n" + 
        		"echo \"%CD%\" am schimbat fisierul\r\n" + 
        		"start \"\" \"C:\\Users\\tcorneanu\\Desktop\\Licenta\\MasinileVirtuale\\"+name+"\"\r\n" + 
        		"D:\r\n" + 
        		"cd D:\\Licenta\\vgdemo\r\n" + 
        		"C:\r\n" + 
        		"cd C:\\Users\\tcorneanu\\Desktop\\Licenta\\MasinileVirtuale\\"+name+"\r\n" + 
        		"vagrant up");
		//flush the stream
		bwr2.flush();
		//close the stream
		bwr2.close();
		
		System.out.println("---------Executa createVM.bat---------");
		//Run the process
        Process p = Runtime.getRuntime().exec("cmd /c C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/createVM.bat");
        //Get the input stream
        InputStream is = p.getInputStream();
 
        //Read script execution results
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while ( (i = is.read()) !=-1 )
            sb.append((char)i);
        
        System.out.println("---------In urma rularii programului createVM.bat---------");
        System.out.println(sb.toString());
        
        BufferedWriter bwr3 = new BufferedWriter(new FileWriter(new File("C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/RaspunsDinCmdLaVagrantUp.txt")));
		
		//write contents of StringBuffer to a file
		bwr3.write(sb.toString());
		//flush the stream
		bwr3.flush();
		
		//close the stream
		bwr3.close();
        
        System.out.println("---------Se citeste ultima linie din cadrul fisierului creat mai sus RaspunsDinCmdLaVagrantUp.txt----------");

		String file = "C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/RaspunsDinCmdLaVagrantUp.txt";
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        String sCurrentLine; 
        String lastLine = "";

        while ((sCurrentLine = reader.readLine()) != null) {
            lastLine = sCurrentLine;
        }
        
        System.out.println("Ultima linie este: "+lastLine);
        
        
	}
}
