package com.eazybytes.EazySchoolApplication.service;

//commit
import com.eazybytes.EazySchoolApplication.config.EazySchoolProps;
import com.eazybytes.EazySchoolApplication.constants.EazySchoolConstants;
import com.eazybytes.EazySchoolApplication.model.Contact;
import com.eazybytes.EazySchoolApplication.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Data
@Slf4j
@Service
@ApplicationScope //one bean per single servletcontext
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EazySchoolProps eazySchoolProps;
   // int counter =0;
    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */
    // Spring Boot JDBC START-----------------------------------
//    public boolean saveMessageDetails(Contact contact){
//        //TODO - Need to persist the data into the DB table
//        log.info(contact.toString());
//        boolean isSaved = false;
//        contact.setStatus(EazySchoolConstants.OPEN);
//        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
//        contact.setCreatedAt(LocalDateTime.now());
//        int result = contactRepository.saveContactMsg(contact);
//        if(result>0) {
//            isSaved = true;
//        }
//        return isSaved;
//    }
//
//    public List<Contact> findMsgsWithOpenStatus(){
//        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(EazySchoolConstants.OPEN);
//        return contactMsgs;
//    }
//
//    public boolean updateMsgStatus(int contactId, String updatedBy){
//        boolean isUpdated = false;
//        int result = contactRepository.updateMsgStatus(contactId,EazySchoolConstants.CLOSE, updatedBy);
//        if(result>0) {
//            isUpdated = true;
//        }
//        return isUpdated;
//    }
// Spring Boot JDBC END-----------------------------------


    // Spring Boot JPA START-----------------------------------
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir){
        int pageSize = eazySchoolProps.getPageSize();
        if(null!=eazySchoolProps.getContact() && null!=eazySchoolProps.getContact().get("pageSize")){
            pageSize = Integer.parseInt(eazySchoolProps.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatusWithQuery(
                EazySchoolConstants.OPEN,pageable);

        //Sorting not work
        //NamedQuery
//                Page<Contact> msgPage = contactRepository.findOpenMsgs(
//                EazySchoolConstants.OPEN,pageable);
        //NamedNativeQuery
//                Page<Contact> msgPage = contactRepository.findOpenMsgsNative(
//                EazySchoolConstants.OPEN,pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
//        Optional<Contact> contact = contactRepository.findById(contactId);
//        contact.ifPresent(contact1 -> {
//            contact1.setStatus(EazySchoolConstants.CLOSE);
//        });
    //    Contact updatedContact = contactRepository.save(contact.get());
//        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
//            isUpdated = true;
//        }

        //Using Custom Query
      //  int rows = contactRepository.updateStatusById(EazySchoolConstants.CLOSE,contactId);
      //  Using @NamedQuery
       //  int rows = contactRepository.updateMsgStatus(EazySchoolConstants.CLOSE,contactId);
        //  Using @NamedNativeQuery
        int rows = contactRepository.updateMsgStatusNative(EazySchoolConstants.CLOSE,contactId);
        if(rows>0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}