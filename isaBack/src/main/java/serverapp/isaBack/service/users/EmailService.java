package serverapp.isaBack.service.users;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import serverapp.isaBack.model.Client;
import serverapp.isaBack.model.ComplaintUnit;
import serverapp.isaBack.model.ComplaintUser;
import serverapp.isaBack.model.Reservation;
import serverapp.isaBack.model.User;


@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	private final String LOCAL_URL = "http://localhost:3000";
	
	
	
	@Autowired
	private Environment env;
	
	
	
	@Async
	public void sendSignUpNotificaitionAsync(Client client)
			throws MailException, InterruptedException, MessagingException {
		
		
		System.out.println("usao 1");
		
		String url = LOCAL_URL + "/activeAccount/" + client.getId();
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<p>Hello " + client.getName() + ",</p>" +
					"<p>Registrovali ste se na sajt doživi avanturu, pre korišćenja morate aktivirati svoj nalog ovde:</p>"
					+ "<a href=\"" + url + "\">potvrdi nalog</a>.</p>" + "<p>Admin sajta</p>"; 
		helper.setText(htmlMsg, true);
		helper.setTo("acamijatovic.98@gmail.com");
		helper.setSubject("Aktivacija naloga");
		helper.setFrom(env.getProperty("spring.mail.username"));
		System.out.println("usao 2");
		javaMailSender.send(mimeMessage);
		
	}
	
	@Async
	public void sendReservationNotification(Reservation reservation) throws MessagingException {
		
		DateFormat formatterForTime = new SimpleDateFormat("HH:mm");
		DateFormat mainFormatter = new SimpleDateFormat("dd-MM-yyyy");
		
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg="";
		if(reservation.getActionPrice()>0) {
		 htmlMsg = "<p>Pozdrav " + reservation.getClient().toString() + ",</p>" + 
		             "<p> Uspešno ste rezervisali : " + reservation.getUnit().getName() + "na akciji kod vlasnika "
						+ reservation.getOwner().toString() + 
						", za period od: " + mainFormatter.format(reservation.getStartDateTime()) + " " + formatterForTime.format(reservation.getStartDateTime()) + " do: "
						+mainFormatter.format(reservation.getEndDateTime()) + " " + formatterForTime.format(reservation.getEndDateTime()) +
						"</p> <p> Ukupna cena je: "+ reservation.getPrice()+
						"</p> <p> Cena na akciji je: "+ reservation.getActionPrice()+
						"</p> <p>Admin sistema</p>";
		}else {
		 htmlMsg = "<p>Pozdrav " + reservation.getClient().toString() + ",</p>" + 
	             "<p> Uspešno ste rezervisali : " + reservation.getUnit().getName() + " kod vlasnika "
					+ reservation.getOwner().toString() + 
					", za period od: " + mainFormatter.format(reservation.getStartDateTime()) + " " + formatterForTime.format(reservation.getStartDateTime()) + " do: "
					+mainFormatter.format(reservation.getEndDateTime()) + " " + formatterForTime.format(reservation.getEndDateTime()) +
					"</p> <p> Ukupna cena je: "+ reservation.getPrice()+
					"</p> <p>Admin sistema</p>";
		}
		helper.setText(htmlMsg, true);
		//helper.setTo(reservation.getClient().getEmail());
		helper.setTo("acamijatovic.98@gmail.com");
		helper.setSubject("Rezervacija");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		
	}
	
	
	@Async
	public void sendEmailforReplyedComplaint(ComplaintUnit complaintUnit)
			throws MailException, InterruptedException, MessagingException {
		
		
		System.out.println("usao u odgovor");
		
		
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<p>Pozdrav " + complaintUnit.getClient().getName() + ",</p>" +
					"<p>Ovde je naš odgovor na vašu žalbu: " + complaintUnit.getText()+ "</p>"
					+ "<p>" + complaintUnit.getReply()+ "</p>" + "<p>Admin sistema</p>"; 
		helper.setText(htmlMsg, true);
		//helper.setTo(complaintUnit.getClient().getEmail());
		helper.setTo("acamijatovic.98@gmail.com");
		System.out.println("Ejaasjdasjdas");
		helper.setSubject("Odgovor na žalbu");
		helper.setFrom(env.getProperty("spring.mail.username"));
		System.out.println("usao 2");
		javaMailSender.send(mimeMessage);
		
	}
	
	@Async
	public void sendEmailforReplyedComplaintToOwner(ComplaintUnit complaintUnit)
			throws MailException, InterruptedException, MessagingException {
		
		
		System.out.println("usao u odgovor");
		
		
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<p>Pozdrav " + ",</p>" +
					"<p>Imate žalbu za : " +complaintUnit.getUnitName() +" " + complaintUnit.getText()+ "</p>"
					+ "<p>Naš odgovor je: " + complaintUnit.getReplyToUser()+ "</p>" + "<p>Admin sistema</p>"; 
		helper.setText(htmlMsg, true);
		//helper.setTo(complaintUnit.getUserEmail());
		helper.setTo("acamijatovic.98@gmail.com");
		System.out.println("Ejaasjdasjdas");
		helper.setSubject("Odgovor na žalbu");
		helper.setFrom(env.getProperty("spring.mail.username"));
		System.out.println("usao 2");
		javaMailSender.send(mimeMessage);
		
	}
	
	
	@Async
	public void sendEmailforReplyedComplaint(ComplaintUser complaintUnit)
			throws MailException, InterruptedException, MessagingException {
		
		
		System.out.println("usao u odgovor");
		
		
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<p>Pozdrav " + complaintUnit.getClient().getName() + ",</p>" +
					"<p>Ovde je naš odgovor na vašu žalbu: " + complaintUnit.getText()+ "</p>"
					+ "<p>" + complaintUnit.getReply()+ "</p>" + "<p>Admin sistema</p>"; 
		helper.setText(htmlMsg, true);
		//helper.setTo(complaintUnit.getClient().getEmail());
		helper.setTo("acamijatovic.98@gmail.com");
		System.out.println("Ejaasjdasjdas");
		helper.setSubject("Odgovor na žalbu");
		helper.setFrom(env.getProperty("spring.mail.username"));
		System.out.println("usao 2");
		javaMailSender.send(mimeMessage);
		
	}
	
	@Async
	public void sendEmailforReplyedComplaintToOwner(ComplaintUser complaintUnit)
			throws MailException, InterruptedException, MessagingException {
		
		
		System.out.println("usao u odgovor");
		
		
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<p>Pozdrav " +complaintUnit.getUserName()+ ",</p>" +
					"<p>Imate žalbu : "  +" " + complaintUnit.getText()+ "</p>"
					+ "<p>Naš odgovor je: " + complaintUnit.getReplyToUser()+ "</p>" + "<p>Admin sistema</p>"; 
		helper.setText(htmlMsg, true);
		//helper.setTo(complaintUnit.getUserEmail());
		helper.setTo("acamijatovic.98@gmail.com");
		System.out.println("Ejaasjdasjdas");
		helper.setSubject("Odgovor na žalbu");
		helper.setFrom(env.getProperty("spring.mail.username"));
		System.out.println("usao 2");
		javaMailSender.send(mimeMessage);
		
	}
	
}
