package org.healtheta.web;

import org.healtheta.model.allergy.AllergyIntolerance;
import org.healtheta.model.allergy.repo.AllergyIntoleranceRepo;
import org.healtheta.model.common.Identifier;
import org.healtheta.model.common.OperationOutcome;
import org.healtheta.model.common.Reference;
import org.healtheta.model.common.repos.IdentifierRepo;
import org.healtheta.model.common.repos.ReferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping(value = "/allergy")
public class AllergyIntoleranceController {
    @Autowired
    AllergyIntoleranceRepo allergyIntoleranceRepo;
    @Autowired
    private IdentifierRepo identifierRepo;
    @Autowired
    private ReferenceRepo referenceRepo;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody AllergyIntolerance allergyIntolerance){
        Identifier tmpId = allergyIntolerance.getIdentifier();
        if(tmpId.getValue() == null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InvalidParameter(),
                    HttpStatus.OK);
        }

        if(identifierRepo.findIdentifierByValue(tmpId.getValue()) != null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordExists(),
                    HttpStatus.OK);
        }

        try{
            AllergyIntolerance tmp = new AllergyIntolerance();
            tmp = allergyIntoleranceRepo.save(tmp);
            allergyIntolerance.setId(tmp.getId());

            Reference ref = new Reference();
            ref.setIdentifier(allergyIntolerance.getIdentifier());
            ref.setDisplay("allergy-reference");
            allergyIntolerance.setReference(ref);
            allergyIntolerance = allergyIntoleranceRepo.save(allergyIntolerance);
            return new ResponseEntity<AllergyIntolerance>(allergyIntolerance, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(),
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> read(@PathVariable String id){
        try{
            Long lId = Long.parseLong(id);
            AllergyIntolerance allergyIntolerance = allergyIntoleranceRepo.findAllergyIntoleranceById(lId);
            if(allergyIntolerance != null){
                return new ResponseEntity<AllergyIntolerance>(allergyIntolerance, HttpStatus.OK);
            }else{
                return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody AllergyIntolerance allergyIntolerance){
        Long id = allergyIntolerance.getId();
        AllergyIntolerance tmp = allergyIntoleranceRepo.findAllergyIntoleranceById(id);
        if ( tmp != null){
            //record exists let;s update.
            //reference and identifiers are not to be updated.
            allergyIntolerance.setIdentifier(tmp.getIdentifier());
            allergyIntolerance.setReference(tmp.getReference());
            allergyIntolerance = allergyIntoleranceRepo.save(allergyIntolerance);
            return new ResponseEntity<AllergyIntolerance>(allergyIntolerance, HttpStatus.OK);
        }else{
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> delete(String id) {
        return null;
    }

    @RequestMapping(value = "/search",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam(value = "patient", required=false) Long patient,
                                    @RequestParam(value = "recorder", required=false) Long recorder){
        if(patient != null){
                Reference ref = referenceRepo.findReferenceById(patient);
                List<AllergyIntolerance> allergyIntoleranceList = allergyIntoleranceRepo.findAllergyIntoleranceByPatient(ref);
                return new ResponseEntity<List>(allergyIntoleranceList, HttpStatus.OK);
        }else if ( recorder != null){
            Reference ref = referenceRepo.findReferenceById(recorder);
            List<AllergyIntolerance> allergyIntoleranceList = allergyIntoleranceRepo.findAllergyIntoleranceByRecorder(ref);
            return new ResponseEntity<List>(allergyIntoleranceList, HttpStatus.OK);
        }else{
            return new ResponseEntity<OperationOutcome>(OperationOutcome.OperationNotSupported(),
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/format",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> format(){
        AllergyIntolerance allergyIntolerance = new AllergyIntolerance();
        Identifier identifier = new Identifier();
        identifier.setValue("ALLERGY:KDJMDMKD-92929229-DKDKDKDDK-92929292");
        allergyIntolerance.setIdentifier(identifier);
        return new ResponseEntity<AllergyIntolerance>(allergyIntolerance, HttpStatus.OK);
    }
}
