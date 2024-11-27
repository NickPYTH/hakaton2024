package de401t;


import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import de401t.service.UserService;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAsync
public class JwtAuthServiceApp implements CommandLineRunner {

  final UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    return builder -> {
      builder.simpleDateFormat("dd-MM-yyyy HH:mm");
      builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
      builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    };
  }

  @Override
  public void run(String... params) throws Exception {
//    User admin = new User();
//    admin.setUsername("admin");
//    admin.setPassword("admin");
//    admin.setEmail("admin@email.com");
//    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
//
//    userService.signup(admin);
//
//    User client = new User();
//    client.setUsername("client");
//    client.setPassword("client");
//    client.setEmail("client@email.com");
//    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//
//    userService.signup(client);
  }

}
