package com.example.websocketserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notification {
    Long id;
    String mess;
    Account sale;
    Long createDate;
    Long updateDate;
}
