package com.example.test_task.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity(name = "ACCOUNT")
@NoArgsConstructor
@Accessors(chain = true)
public class Account {
    @Id
    @Column(name = "account_id")
    Integer accountId;
    @Column(name = "user_id")
    Integer userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "sum")
    BigDecimal sum;
}
