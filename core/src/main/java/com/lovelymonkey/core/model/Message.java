package com.lovelymonkey.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * Hibernate pojo mapping to table model_message.
 * @author guanxwei
 *
 */
@Data
@Entity
@Table(name = "model_message")
public class Message {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "messagebody", length = 255)
    private String messageBody;

    @Column(name = "messagetitle", length = 255)
    private String messageTitle;

    /**
     * Status to indicate the message status.
     * 0: new sent, but not read yet.
     * 1: read by receiver.
     * 2: deleted by receiver, but still visible to the sender.
     * 3: deleted by sender, but still visible to the receiver.
     * 4: deleted both by the sender and the receiver, not visible any more. Back-end message garbage cleaner may clean up the message permanently.
     */
    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "senderid")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverid")
    private User receiver;
}
