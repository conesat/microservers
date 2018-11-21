package com.hg.reservationservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;

@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner sampleData(ReservationRepository reservationRepository){
		return  args -> {
			Stream.of("ww","ws")
					.forEach(name -> reservationRepository.save(new Reservation(null,name)));
			reservationRepository.findAll().forEach(System.out::println);
		};
	}
}

@RepositoryRestResource
interface  ReservationRepository extends JpaRepository<Reservation,Long>{

}
@Entity
class Reservation {
	@Id
	@GeneratedValue
	private Long id;

	private String reservationName;

	public Reservation() {
	}

	public Long getId() {
		return id;
	}

	public String getReservation() {
		return reservationName;
	}

	public Reservation(Long id, String reservationName) {
		this.id = id;
		this.reservationName = reservationName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setReservation(String reservationName) {
		this.reservationName = reservationName;
	}
}
