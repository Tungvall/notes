package se.twowall.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import view.MenuView;

@SpringBootApplication
public class NotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);

        new MenuView();
    }

}
