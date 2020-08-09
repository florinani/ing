package com.florinani.ing.validator;

import com.florinani.ing.domain.SavingAccount;
import com.florinani.ing.domain.User;
import com.florinani.ing.repository.SavingAccountReporsitory;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.util.List;

public class SavingAccountValidator {

    private User user;

    private SavingAccountReporsitory savingAccountReporsitory;

    private Model model;

    private Timestamp timestamp;

    public SavingAccountValidator(User user, SavingAccountReporsitory savingAccountReporsitory, Model model,
                                  Timestamp timestamp) {
        this.user = user;
        this.savingAccountReporsitory = savingAccountReporsitory;
        this.model = model;
        this.timestamp = timestamp;
    }

    public boolean validate() {

        boolean isInWorkingHours = validateWorkingHours(timestamp);
        boolean isInWorkingDays = validateWorkingDays(timestamp);
        boolean hasNoAccountRegistred = validateNoRegistredAccount(user);

        return isInWorkingHours && isInWorkingDays && hasNoAccountRegistred;
    }

    private boolean validateWorkingHours(Timestamp timestamp) {
        int hour = timestamp.getHours();
        if (hour >= 9 && hour <= 17) {
            model.addAttribute("inWorkingHours", true);
            return true;
        }
        model.addAttribute("inWorkingHours", false);
        return false;
    }

    private boolean validateWorkingDays(Timestamp timestamp) {
        int day = timestamp.getDay();

        if (day == 6 || day == 7) {
            model.addAttribute("inWorkingDays", false);
            return false;

        }
        model.addAttribute("inWorkingDays", true);
        return true;
    }

    private boolean validateNoRegistredAccount(User user) {
        List<SavingAccount> savingAccountList = savingAccountReporsitory.findByUser(user);
        if (savingAccountList.size() != 0) {
            model.addAttribute("isNoAccountRegistred", false);
            return false;
        }
        model.addAttribute("isNoAccountRegistred", true);
        return true;
    }

}
