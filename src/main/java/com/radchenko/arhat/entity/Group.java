package com.radchenko.arhat.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Group {
    @Id
    private Long id;
}
