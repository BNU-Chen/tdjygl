package heritrixEntress;

import java.io.IOException;

public class MyThread extends Thread {
    public void run() {
        
    	System.out.println("MyThread.run()");
        Process p = null;
		String cmd="F:\\eclipsework2\\heritrix\\bin\\start.bat";

		System.out.println(cmd);
		try {
			p = Runtime.getRuntime().exec("cmd /c start F:\\eclipsework2\\heritrix\\bin\\start.bat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    public static void main(String[] args){
//		Thread t1 = new Thread();
//		t1.start();
    	new MyThread().run();
	}
}
