package org.healtheta.model.allergy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.healtheta.model.common.Identifier;
import org.healtheta.model.common.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "AllergyIntolerance")
public class AllergyIntolerance implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(unique=true, name = "_identifier")
    private Identifier identifier;

    @Column(name = "_clinicalStatus")
    private String clinicalStatus;

    @Column(name = "_verificationStatus")
    private String verificationStatus;

    @Column(name = "_type")
    private String type;

    @ElementCollection
    @Column(name = "_category")
    private List<String> category;

    @Column(name = "_criticality")
    private String criticality;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_code")
    private CodeableConcept code;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_patient")
    private Reference patient;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_onset")
    private Onset onset;

    @Column(name = "_assertedDate")
    private Date assertedDate;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_recorder")
    private Reference recorder;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_asserter")
    private Reference asserter;

    @Column(name = "_lastOccurence")
    private Date lastOccurence;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_annotation")
    private List<Annotation> annotation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_reaction")
    private List<AllergyIntoleranceReaction> reaction;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_reference")
    private Reference reference;

    public Long getId() {
        return id;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getClinicalStatus() {
        return clinicalStatus;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public String getType() {
        return type;
    }

    public List<String> getCategory() {
        return category;
    }

    public String getCriticality() {
        return criticality;
    }

    public CodeableConcept getCode() {
        return code;
    }

    public Reference getPatient() {
        return patient;
    }

    public Onset getOnset() {
        return onset;
    }

    public Date getAssertedDate() {
        return assertedDate;
    }

    public Reference getRecorder() {
        return recorder;
    }

    public Reference getAsserter() {
        return asserter;
    }

    public Date getLastOccurence() {
        return lastOccurence;
    }

    public List<Annotation> getAnnotation() {
        return annotation;
    }

    public List<AllergyIntoleranceReaction> getReaction() {
        return reaction;
    }

    public Reference getReference() {
        return reference;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void setClinicalStatus(String clinicalStatus) {
        this.clinicalStatus = clinicalStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public void setCode(CodeableConcept code) {
        this.code = code;
    }

    public void setPatient(Reference patient) {
        this.patient = patient;
    }

    public void setOnset(Onset onset) {
        this.onset = onset;
    }

    public void setAssertedDate(Date assertedDate) {
        this.assertedDate = assertedDate;
    }

    public void setRecorder(Reference recorder) {
        this.recorder = recorder;
    }

    public void setAsserter(Reference asserter) {
        this.asserter = asserter;
    }

    public void setLastOccurence(Date lastOccurence) {
        this.lastOccurence = lastOccurence;
    }

    public void setAnnotation(List<Annotation> annotation) {
        this.annotation = annotation;
    }

    public void setReaction(List<AllergyIntoleranceReaction> reaction) {
        this.reaction = reaction;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
