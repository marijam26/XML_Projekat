package project.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.xml.loading.Loading;

import javax.xml.bind.JAXBException;

@SpringBootApplication
public class XmlApplication {

	public static void main(String[] args) throws JAXBException {

		SpringApplication.run(XmlApplication.class, args);
		Loading l = new Loading();
		System.out.println("\n\n");

		l.loadA();
		System.out.println("\n\n");

		l.loadP();
		System.out.println("\n\n");

		l.loadZ();
	}

}
