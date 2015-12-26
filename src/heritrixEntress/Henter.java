package heritrixEntress;

import java.io.IOException;

public class Henter {
	public static void main(String[] args){
//		Thread t1 = new Thread();
//		t1.start();
		Process p = null;
		String cmd="F:\\eclipsework2\\heritrix\\bin\\start.bat";

		System.out.println(cmd);
		//p = Runtime.getRuntime().exec(cmd);
		try {
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c",cmd);
			//p = Runtime.getRuntime().exec("cmd /c start F:\\eclipsework2\\heritrix\\bin\\start.bat");
			//p = Runtime.getRuntime().exec("cmd /c cd\");
			p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
