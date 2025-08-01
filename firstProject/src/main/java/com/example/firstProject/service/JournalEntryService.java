package com.example.firstProject.service;

import com.example.firstProject.entity.JournalEntry;
import com.example.firstProject.entity.User;
import com.example.firstProject.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName)
    {
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            // delibeartely for getting error and exception
            // to test - transactional
            // user.setUserName(null);
            userService.saveEntry(user);
        }
        catch(Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry)
    {
        try{

            journalEntryRepository.save(journalEntry);
        }
        catch(Exception e)
        {
            log.error("Exception",e);
        }
    }


    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName)
    {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

}
