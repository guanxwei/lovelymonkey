package com.lovelymonkey.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * Hibernate pojo for password reset requests records.
 * @author guanxwei
 *
 */
@Data
@Entity
@Table(name = "model_passwordresetrecords")
public class PasswordResetRecord {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "recordDate")
    private String recordDate;

    @Column(name = "signature")
    private String signature;
}
