package com.florinani.ing.controllers;

import com.florinani.ing.domain.SavingAccount;
import com.florinani.ing.domain.User;
import com.florinani.ing.repository.SavingAccountReporsitory;
import com.florinani.ing.repository.UserRepository;
import com.florinani.ing.service.UserService;
import com.florinani.ing.util.AccountNumberGenerator;
import com.florinani.ing.validator.SavingAccountValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Controller
public class SavingAccountController {


    private final SavingAccountReporsitory savingAccountReporsitory;

    private final UserRepository userRepository;

    private final AccountNumberGenerator accountNumberGenerator;

    private final UserService userService;

    private SavingAccountValidator savingAccountValidator;


    public SavingAccountController(SavingAccountReporsitory savingAccountReporsitory, AccountNumberGenerator accountNumberGenerator
    , UserService userService, UserRepository userRepository) {

        this.savingAccountReporsitory = savingAccountReporsitory;
        this.accountNumberGenerator = accountNumberGenerator;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/addAccount")
    public String createSavingAccount(Model model) {

        model.addAttribute("isNoAccountRegistred", true);
        model.addAttribute("inWorkingHours", true);
        model.addAttribute("inWorkingDays", true);

        return "savingaccount/addAccount";
    }

    @RequestMapping(value = "/submitAccount", method = RequestMethod.POST)
    public String submitSavingAccount(@RequestParam("accountName") String accountName, @RequestParam("currency") String currency,
                                      Model model) {
        User user = userRepository.findByUserName(userService.getCurrentlyLoggedinUser());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        savingAccountValidator = new SavingAccountValidator(user, savingAccountReporsitory, model, timestamp);

        if (savingAccountValidator.validate()) {
            SavingAccount savingAccount = new SavingAccount();
            savingAccount.setName(accountName);
            savingAccount.setUser(user);
            savingAccount.setCurrency(currency);
            savingAccount.setAccountNumber(accountNumberGenerator.generate(user.getId()));
            savingAccount.setBalance(BigDecimal.valueOf(0));
            savingAccount.setActive(true);

            savingAccountReporsitory.save(savingAccount);

            model.addAttribute("user", user);
            model.addAttribute("savingAccountList", savingAccountReporsitory.findByUser(user));

        } else {
            return "savingaccount/addAccount";
        }

        return "users/successfulLogin";

    }
}
