package quartz.testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.jdbcjobstore.MSSQLDelegate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SchedulerException
    {
        System.out.println("Iniciando aplicação...");
        
        Properties properties = new Properties();
        
        
        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        properties.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        properties.put("org.quartz.jobStore.dataSource","quartzDataSource");
        
        
        properties.put("org.quartz.dataSource.quartzDataSource.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put("org.quartz.dataSource.quartzDataSource.URL", "jdbc:sqlserver://localhost:1433;databaseName=QUARTZBD");
        properties.put("org.quartz.dataSource.quartzDataSource.user", "sa");
        properties.put("org.quartz.dataSource.quartzDataSource.password", "Vmw4r3s4l3m4@");
        properties.put("org.quartz.threadPool.threadCount", "5");
        
        
        // Monta o job com base na classe que possui o execute de start
        JobDetail job1 = JobBuilder.newJob(MeuObjeto.class).build();
        
        JobDetail job2 = JobBuilder.newJob(somatorio.class).build();
        
        //Cria agendamento do job que será executado para sempre de 3 em 3 segundos
        SimpleScheduleBuilder agendamento = SimpleScheduleBuilder.simpleSchedule();
        agendamento.withIntervalInSeconds(03);
        agendamento.repeatForever();
        
        // Cria o disparo com base no agendamento
        Trigger disparo = TriggerBuilder.newTrigger().withIdentity("Trigger1").withSchedule(agendamento).build();
        Trigger disparo2 = TriggerBuilder.newTrigger().withIdentity("Trigger2").withSchedule(agendamento).build();
        
        
        //Cria o scheduler oficial que recebe o job e o agendamento
        Scheduler scheduleoficial = new StdSchedulerFactory(properties).getScheduler();
        
        scheduleoficial.start();
        scheduleoficial.scheduleJob(job1, disparo);
        scheduleoficial.scheduleJob(job2, disparo2);
        
    }
}
