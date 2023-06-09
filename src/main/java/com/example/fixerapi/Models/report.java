package com.example.fixerapi.Models;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JsonIgnore
    @ManyToOne
    client client;
    @JsonIgnore
    @ManyToOne
    salle7 salle7;

    String Reason;
    String Description;
    String ImgPath;
}
