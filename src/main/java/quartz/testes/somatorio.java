package quartz.testes;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class somatorio implements Job{
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException{
		int valor = 30;
		valor += 30;
		System.out.println("Somatorio rodando:");
		System.out.println("O total agora: "+valor);
	}
}
