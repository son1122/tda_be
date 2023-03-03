package com.example.tda.Controller;

import com.example.tda.entity.Agent;
import com.example.tda.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RegisterController {
    private final AgentRepository agentRepository;
    @Autowired
    private JavaMailSender mailSender;

    public RegisterController(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }


    @PostMapping("/agent/register")
    public ResponseEntity<String> register(@RequestBody Agent agent) {
        agentRepository.save(agent);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @PostMapping("/agent/login")
    public ResponseEntity<Agent> login(@RequestParam String email, @RequestParam String password) {
        Optional<Agent> agent = Optional.ofNullable(agentRepository.findByEmail(email));
        if (agent.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(agent.get(), HttpStatus.OK);
        }

    }

    @GetMapping("/agent/profile")
    public ResponseEntity<Agent> profile(@RequestParam Integer id){

        Agent agent = agentRepository.findById(id).get();

        return new ResponseEntity<>(agent,HttpStatus.OK);
    }

    @GetMapping("/email")
    public void email() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setTo("son_1122@hotmail.com");
        helper.setSubject("Dhiphaya Insurance User");

        String html =
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <title>Welcome to Dhipaya Insurance!</title>\n" +
                        "  <style>\n" +
                        "    body {\n" +
                        "      font-family: Arial, sans-serif;\n" +
                        "      font-size: 16px;\n" +
                        "      line-height: 1.4;\n" +
                        "      color: #444444;\n" +
                        "    }\n" +
                        "\n" +
                        "    h1 {\n" +
                        "      font-size: 24px;\n" +
                        "      margin-bottom: 20px;\n" +
                        "    }\n" +
                        "\n" +
                        "    p {\n" +
                        "      margin-bottom: 10px;\n" +
                        "    }\n" +
                        "\n" +
                        "    strong {\n" +
                        "      font-weight: bold;\n" +
                        "    }\n" +
                        "\n" +
                        "    .container {\n" +
                        "      max-width: 600px;\n" +
                        "      margin: 0 auto;\n" +
                        "      padding: 20px;\n" +
                        "      background-color: #f5f5f5;\n" +
                        "      border: 1px solid #dddddd;\n" +
                        "      border-radius: 10px;\n" +
                        "      box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);\n" +
                        "    }\n" +
                        "\n" +
                        "    .logo {\n" +
                        "      display: block;\n" +
                        "      margin: 0 auto;\n" +
                        "      margin-bottom: 20px;\n" +
                        "      width: 100px;\n" +
                        "    }\n" +
                        "\n" +
                        "    .button {\n" +
                        "      display: inline-block;\n" +
                        "      padding: 8px 12px;\n" +
                        "      border-radius: 5px;\n" +
                        "      background-color: rgba(15, 14, 159, 1);\n" +
                        "      color: #ffffff;\n" +
                        "      font-size: 16px;\n" +
                        "      text-decoration: none;\n" +
                        "      margin-top: 20px;\n" +
                        "    }\n" +
                        "\n" +
                        "    .button:hover {\n" +
                        "      background-color: rgba(15, 14, 159, 0.8);\n" +
                        "    }\n" +
                        "\n" +
                        "    .footer {\n" +
                        "      margin-top: 20px;\n" +
                        "      text-align: center;\n" +
                        "      font-size: 12px;\n" +
                        "      color: #888888;\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <div class=\"container\">\n" +
                        "    <img src=\"https://www.dhipaya.co.th/uploads/ir_news/125/desktop/thai/8214582536685361a3f95b29f4e61762.jpg\" alt=\"Dhipaya Insurance Logo\" class=\"logo\">\n" +
                        "    <h1>Welcome to Dhipaya Insurance!</h1>\n" +
                        "    <p>Dear valued customer,</p>\n" +
                        "    <p>We are thrilled to have you on board and excited for you to experience our top-notch insurance services.</p>\n" +
                        "    <p>To get started, please use the following credentials to log in to the app:</p>\n" +
                        "    <p>Username: <strong>"+"agentLogin.getAgentUsername()"+"</strong></p>\n" +
                        "    <p>Password: <strong>"+"password"+"</strong><br/></p>\n" +
                        "    <p>After Login Please Change Password and Username Immediately</p>"+
                        "    <p>If you have any questions or concerns, don't hesitate to reach out to our customer support team. We're here to assist you 24/7.</p>\n" +
                        "    <a href=\"#\" class=\"button\">Get Start</a>\n" +
                        "    <p>Thank you for choosing Dhipaya Insurance. We look forward\n" +
                        "We look forward to serving you.\n" +
                        "\n" +
                        "Best regards,\n" +
                        "The Dhipaya Insurance Team\n" +
                        "          <div class=\"footer\">\n" +
                        "      This email was sent by the Dhipaya . &copy; 2023 Company Inc. All rights reserved.\n" +
                        "    </div>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>\n";

        helper.setText(html, true);
        mailSender.send(message);
    }



}