package com.example.eroom.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Payment {

    @Id @GeneratedValue
    @Column(name = "payment_id")
    private Long id;

    private int amountDue; //지불해야할 금액

    private int amountPaid; //지불된 금액

    private LocalDate paymentDate; //지불 날짜

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType; //지불 유형(TRANSFER, CARD)

    private boolean isPaid; //지불 여부

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private Attendance attendance;
}
