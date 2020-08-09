package com.florinani.ing.validator;


import com.florinani.ing.domain.SavingAccount;
import com.florinani.ing.domain.User;
import com.florinani.ing.repository.SavingAccountReporsitory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SavingAccountValidatorTest {

    @Mock
    private Model model;
    @Mock
    private SavingAccountReporsitory savingAccountReporsitory;
    @Mock
    private User user;
    @Mock
    private Timestamp timestamp;

    private SavingAccountValidator savingAccountValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        savingAccountValidator = new SavingAccountValidator(user, savingAccountReporsitory, model, timestamp);
    }

    @Test
    public void validate_hasExistingAccount() {

        //SETUP
        setUpTest(10,5,1);

        //RUN TEST
        boolean testResult = savingAccountValidator.validate();

        //VERIFY
        Assert.assertFalse(testResult);
    }

    @Test
    public void validate_hasNoExistingAccount() {

        //SETUP
        setUpTest(10,5,0);

        //RUN TEST
        boolean testResult = savingAccountValidator.validate();

        //VERIFY
        Assert.assertTrue(testResult);
    }

    @Test
    public void validate_inWorkingHours() {

        //SETUP
        setUpTest(10,5,0);

        //RUN TEST
        boolean testResult = savingAccountValidator.validate();

        //VERIFY
        Assert.assertTrue(testResult);
    }

    @Test
    public void validate_outOffWorkingHours() {

        //SETUP
        setUpTest(19,5,0);

        //RUN TEST
        boolean testResult = savingAccountValidator.validate();

        //VERIFY
        Assert.assertFalse(testResult);
    }

    @Test
    public void validate_InWorkingDays() {

        //SETUP
        setUpTest(9,5,0);

        //RUN TEST
        boolean testResult = savingAccountValidator.validate();

        //VERIFY
        Assert.assertTrue(testResult);

    }

    @Test
    public void validate_OutOffWorkingDays() {

        //SETUP
        setUpTest(9,6,0);

        //RUN TEST
        boolean testResult = savingAccountValidator.validate();

        //VERIFY
        Assert.assertFalse(testResult);

    }


    private void setUpTest(int hour, int day, int existingAccount){

        List<SavingAccount> savingAccountList = new ArrayList<>();
        when(timestamp.getHours()).thenReturn(hour);
        when(timestamp.getDay()).thenReturn(day);

        if (existingAccount == 0) {
            when(savingAccountReporsitory.findByUser(any())).thenReturn(savingAccountList);
        } else {
            savingAccountList.add(new SavingAccount());
            when(savingAccountReporsitory.findByUser(any())).thenReturn(savingAccountList);
        }
    }

}
