package org.healtheta.model.allergy.repo;

import org.healtheta.model.allergy.AllergyIntolerance;
import org.healtheta.model.common.Identifier;
import org.healtheta.model.common.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AllergyIntoleranceRepo extends JpaRepository<AllergyIntolerance, Long> {
    public AllergyIntolerance findAllergyIntoleranceById(Long id);
    public AllergyIntolerance findAllergyIntoleranceByIdentifier(Identifier identifier);
    public List<AllergyIntolerance> findAllergyIntoleranceByPatient(Reference subject);
    public List<AllergyIntolerance> findAllergyIntoleranceByRecorder(Reference recorder);
}
