package com.devsuperior.dslearnbds.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devsuperior.dslearnbds.entities.pk.EnrollmentPK;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId // é chave composta (tem classe auxiliar)
	private EnrollmentPK id = new EnrollmentPK(); // tipo da chave composta

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") // data no formato UTC
	private Instant enrollMoment;;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant refundMoment;
	private boolean avaliable;
	private boolean onlyUpdate;

	public Enrollment() {
	}

	public Enrollment(EnrollmentPK id, Instant enrollMoment, Instant refundMoment, boolean avaliable,
			boolean onlyUpdate, User user, Offer offer) {
		super();
		this.id = id;
		this.enrollMoment = enrollMoment;
		this.refundMoment = refundMoment;
		this.avaliable = avaliable;
		this.onlyUpdate = onlyUpdate;
		id.setUser(user);
		id.setOffer(offer);
	}

	public User getStudent() {
		return id.getUser();
	}

	public void setStudent(User user) {
		id.setUser(user);
	}

	public Offer getOffert() {
		return id.getOffer();
	}

	public void setOffer(Offer offer) {
		id.setOffer(offer);
	}

	public Instant getEnrollMoment() {
		return enrollMoment;
	}

	public void setEnrollMoment(Instant enrollMoment) {
		this.enrollMoment = enrollMoment;
	}

	public Instant getRefundMoment() {
		return refundMoment;
	}

	public void setRefundMoment(Instant refundMoment) {
		this.refundMoment = refundMoment;
	}

	public boolean isAvaliable() {
		return avaliable;
	}

	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}

	public boolean isOnlyUpdate() {
		return onlyUpdate;
	}

	public void setOnlyUpdate(boolean onlyUpdate) {
		this.onlyUpdate = onlyUpdate;
	}

}
