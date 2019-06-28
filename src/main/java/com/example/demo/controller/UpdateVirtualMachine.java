package com.example.demo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.example.demo.entities.VirtualMachine;


public class UpdateVirtualMachine {
	public void updateVM(VirtualMachine vm, VirtualMachine oldVM) throws IOException {
		
		
		String name = vm.name;
		String vm_box = "ubuntu/trusty64";
		String memori = vm.memori;
		String cpu = vm.cpu;
		String vram = "123"; //memoria video
		String description = vm.description;
		String stingApp = "mysql,python,java,apache,maven";
		String[] aplicatii = stingApp.split(",");
		vm.setStatus("Actualizare...");
		
		System.out.println("---------Sterge vechiul fisier Vagrantfile---------");
		 File file = new File("C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/Vagrantfile");
	     if(file.delete()){
	    	 System.out.println("Fisierul din C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/Vagrantfile sters cu success");
	     }else System.out.println("Fisierul C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/\"+vm.getName()+\"/Vagrantfile nu exista");
		
	    System.out.println("---------Creare un nou fisier Vagrantfile---------"); 
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
		
		System.out.println("---------Creare reloadVM.bat si scrierea in el---------");
        BufferedWriter bwr2 = new BufferedWriter(new FileWriter(new File("C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/reloadVM.bat")));
        bwr2.write("echo.\r\n" + 
        		"echo ------------------------------------ \r\n" + 
        		"echo * Acesta este mesajul din reloadVM.bat cum sa nu *\r\n" + 
        		"echo ------------------------------------\r\n" + 
        		"echo.\r\n" + 
        		"C:\r\n" + 
        		"cd C:\\Users\\tcorneanu\\Desktop\\Licenta\\MasinileVirtuale\\"+name+"\r\n" + 
        		"vagrant reload");
		//flush the stream
		bwr2.flush();
		//close the stream
		bwr2.close();
		
		System.out.println("---------Executa reloadVM.bat---------");
		//Run the process
        Process p = Runtime.getRuntime().exec("cmd /c C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/reloadVM.bat");
        //Get the input stream
        InputStream is = p.getInputStream();
 
        //Read script execution results
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while ( (i = is.read()) !=-1 )
            sb.append((char)i);
        
        System.out.println("---------In urma rularii programului reloadVM.bat s- a creat RaspunsDinCmdLaVagrantReload.txt---------");
        System.out.println(sb.toString());
        
        BufferedWriter bwr3 = new BufferedWriter(new FileWriter(new File("C:/Users/tcorneanu/Desktop/Licenta/MasinileVirtuale/"+name+"/RaspunsDinCmdLaVagrantReload.txt")));
		
		//write contents of StringBuffer to a file
		bwr3.write(sb.toString());
		//flush the stream
		bwr3.flush();
		
		//close the stream
		bwr3.close();
	}
}
