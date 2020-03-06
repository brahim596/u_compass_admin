package com.ucompass.ucompassadmin.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import com.ucompass.ucompassadmin.domain.enumeration.Affluence;

import com.ucompass.ucompassadmin.domain.enumeration.Type;

/**
 * A CampusService.
 */
@Entity
@Table(name = "campus_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CampusService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "idd")
    private String idd;

    @Column(name = "name")
    private String name;

    @Column(name = "open")
    private Boolean open;

    @Column(name = "description")
    private String description;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "estimated_waiting_time")
    private Duration estimatedWaitingTime;

    @Column(name = "attente")
    private Integer attente;

    @Column(name = "opening_time")
    private Instant openingTime;

    @Column(name = "closure_time")
    private Instant closureTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "affluence")
    private Affluence affluence;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @OneToMany(mappedBy = "campusService")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Evenement> evenements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdd() {
        return idd;
    }

    public CampusService idd(String idd) {
        this.idd = idd;
        return this;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getName() {
        return name;
    }

    public CampusService name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isOpen() {
        return open;
    }

    public CampusService open(Boolean open) {
        this.open = open;
        return this;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getDescription() {
        return description;
    }

    public CampusService description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public CampusService longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public CampusService latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Duration getEstimatedWaitingTime() {
        return estimatedWaitingTime;
    }

    public CampusService estimatedWaitingTime(Duration estimatedWaitingTime) {
        this.estimatedWaitingTime = estimatedWaitingTime;
        return this;
    }

    public void setEstimatedWaitingTime(Duration estimatedWaitingTime) {
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    public Integer getAttente() {
        return attente;
    }

    public CampusService attente(Integer attente) {
        this.attente = attente;
        return this;
    }

    public void setAttente(Integer attente) {
        this.attente = attente;
    }

    public Instant getOpeningTime() {
        return openingTime;
    }

    public CampusService openingTime(Instant openingTime) {
        this.openingTime = openingTime;
        return this;
    }

    public void setOpeningTime(Instant openingTime) {
        this.openingTime = openingTime;
    }

    public Instant getClosureTime() {
        return closureTime;
    }

    public CampusService closureTime(Instant closureTime) {
        this.closureTime = closureTime;
        return this;
    }

    public void setClosureTime(Instant closureTime) {
        this.closureTime = closureTime;
    }

    public Affluence getAffluence() {
        return affluence;
    }

    public CampusService affluence(Affluence affluence) {
        this.affluence = affluence;
        return this;
    }

    public void setAffluence(Affluence affluence) {
        this.affluence = affluence;
    }

    public Type getType() {
        return type;
    }

    public CampusService type(Type type) {
        this.type = type;
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Evenement> getEvenements() {
        return evenements;
    }

    public CampusService evenements(Set<Evenement> evenements) {
        this.evenements = evenements;
        return this;
    }

    public CampusService addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
        evenement.setCampusService(this);
        return this;
    }

    public CampusService removeEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
        evenement.setCampusService(null);
        return this;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CampusService)) {
            return false;
        }
        return id != null && id.equals(((CampusService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CampusService{" +
            "id=" + getId() +
            ", idd='" + getIdd() + "'" +
            ", name='" + getName() + "'" +
            ", open='" + isOpen() + "'" +
            ", description='" + getDescription() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", estimatedWaitingTime='" + getEstimatedWaitingTime() + "'" +
            ", attente=" + getAttente() +
            ", openingTime='" + getOpeningTime() + "'" +
            ", closureTime='" + getClosureTime() + "'" +
            ", affluence='" + getAffluence() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
