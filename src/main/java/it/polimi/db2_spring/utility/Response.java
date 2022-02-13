package it.polimi.db2_spring.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class Response {
   private LocalDateTime timeStamp;
   private int statusCode;
   private HttpStatus status;
   private String reason;
   private String message;
   private Map<?,?> data;
}
