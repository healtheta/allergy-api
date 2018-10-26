package org.healtheta.model.allergy;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import org.healtheta.model.common.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "__AllergyIntolerenceReaction")
public class AllergyIntoleranceReaction implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_substance")
    private CodeableConcept substance;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_manifestation")
    private List<CodeableConcept> manifestation;

    @Column(name = "_description")
    private String description;

    @Column(name = "_onset")
    private Date onset;

    @Column(name = "_severity")
    private Date severity;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_exposureRoute")
    private CodeableConcept exposureRoute;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_annotation")
    private List<Annotation> annotation;

    public Long getId() {
        return id;
    }

    public CodeableConcept getSubstance() {
        return substance;
    }

    public List<CodeableConcept> getManifestation() {
        return manifestation;
    }

    public String getDescription() {
        return description;
    }

    public Date getOnset() {
        return onset;
    }

    public Date getSeverity() {
        return severity;
    }

    public CodeableConcept getExposureRoute() {
        return exposureRoute;
    }

    public List<Annotation> getAnnotation() {
        return annotation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubstance(CodeableConcept substance) {
        this.substance = substance;
    }

    public void setManifestation(List<CodeableConcept> manifestation) {
        this.manifestation = manifestation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOnset(Date onset) {
        this.onset = onset;
    }

    public void setSeverity(Date severity) {
        this.severity = severity;
    }

    public void setExposureRoute(CodeableConcept exposureRoute) {
        this.exposureRoute = exposureRoute;
    }

    public void setAnnotation(List<Annotation> annotation) {
        this.annotation = annotation;
    }
}
