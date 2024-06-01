package com.example.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
	private int id;
	private int customerId;
	private String type;
	private float amount;
	private float balance;
	private LocalDateTime date;
}
