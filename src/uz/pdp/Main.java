package uz.pdp;

import uz.pdp.enums.CategoryEnum;
import uz.pdp.enums.RoleEnum;
import uz.pdp.model.*;
import uz.pdp.service.impl.*;
import uz.pdp.service.interfaces.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static UserService userService = new UserServiceImpl();
    static CardService cardService = new CardServiceImpl();
    static CategoryService categoryService = new CategoryServiceImpl();
    static ServiceService serviceService = new ServiceServiceImpl();
    static CashBackService cashBackService = new CashBackServiceImpl();
    static CommissionService commissionService = new CommissionServiceImpl();
    static CardHistoryService cardHistoryService = new CardHistoryServiceImpl();
    static RegionService regionService = new RegionServiceImpl();
    static DistrictService districtService = new DistrictServiceImpl();

    static int option = -1;
    static User crtUser;
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static String wrongOption = "\n########### WRONG OPTION ###########\n";
    static String success = "\n########### SUCCESS ###########\n";
    static String error = "\n########### ERROR ###########\n";
    static String hashStart = "\n########### ";
    static String hashEnd = " ###########\n";
    static String arrow = "================== > ";

    public static void main(String[] args) {
        System.out.println("\t\t####### P2P #######\n");
        while (option != 0) {
            option = menu();
            switch (option) {
                case 1:
                    if (signIn())
                        getCabinet();
                    option = -1;
                    break;
                case 2:
                    signUp();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(wrongOption);
            }

        }
    }

    private static void getCabinet() {
        while (option != 0) {
            if (crtUser.getRole().equals(RoleEnum.ADMIN))
                System.out.println("\n1.My Cards\t|\t2.Add Card\t|\t3.Send money\t|\t4.History\t|\t5.Payment\t|\t6.Settings\t|\t0.Exit");
            else
                System.out.println("\n1.My Cards\t|\t2.Add Card\t|\t3.Send money\t|\t4.History\t|\t5.Payment\t|\t0.Exit");
            System.out.print(arrow);
            option = scannerInt.nextInt();
            switch (option) {
                case 1:
                    getMyCards();
                    break;
                case 2:
                    addCard();
                    break;
                case 3:
                    sendMoney();
                    break;
                case 4:
                    getHistory();
                    option = -1;
                    break;
                case 5:
                    getPayment();
                    option = -1;
                    break;
                case 6:
                    if (!crtUser.getRole().equals(RoleEnum.ADMIN)) {
                        System.out.println(wrongOption);
                        break;
                    }
                    getSettings();
                    option = -1;
                    break;
                case 0:
                    break;
                default:
                    System.out.println(wrongOption);
            }
        }

    }

    private static void getPayment() {
        ArrayList<Category> categories = categoryService.getAll();
        while (option != 0) {
            int ind = 1;
            System.out.println("CHOOSE ONE");
            for (Category category : categories) {
                System.out.println((ind++) + "." + category.getName());
            }
            System.out.println("0.Exit");
            System.out.print(arrow);
            option = scannerInt.nextInt();
            if (option == 0) break;
            if (option >= ind || option < 0) {
                System.out.println(wrongOption);
                return;
            }
            Category category = categories.get(option - 1);

            if (category.getName().equals(CategoryEnum.TRANSFER.name())) {
                sendMoney();
            } else {
                ArrayList<Service> services = serviceService.getByCategoryId(category.getId());
                ind = 1;
                for (Service service : services) {
                    System.out.println((ind++) + "." + service.getName());
                }
                System.out.println("0.Exit");
                System.out.print(arrow);
                option = scannerInt.nextInt();
                if (option == 0) {
                    option = -1;
                    continue;
                }
                if (option >= ind || option < 0) {
                    System.out.println(wrongOption);
                    return;
                }
                Service service = services.get(option - 1);
                useService(service);

            }

        }
    }

    private static void useService(Service service) {
        if (service.getCategoryId().equals(CategoryServiceImpl.getCategoryByName(CategoryEnum.MOBILE_OPERATORS.name()).getId())) {
            System.out.println("PHONE NUMBER");
            System.out.print(arrow);
            String phone = scannerStr.next();

        } else if (service.getCategoryId().equals(CategoryServiceImpl.getCategoryByName(CategoryEnum.COMMUNAL.name()).getId())) {
            int ind = 1;
            ArrayList<Region> regions = regionService.getAll();
            for (Region region : regions) {
                System.out.println((ind++) + "." + region.getName());
            }
            System.out.println("0.Exit");
            System.out.print(arrow);
            option = scannerInt.nextInt();
            if (option == 0) return;

            if (option < 0 || option >= ind) {
                System.out.println(wrongOption);
                return;
            }
            Region region = regions.get(option - 1);
            ArrayList<District> districts = districtService.getByRegionId(region.getId());
            ind = 1;
            for (District district : districts) {
                System.out.println((ind++) + "." + district.getName());
            }
            System.out.println("0.Exit");
            System.out.print(arrow);
            option = scannerInt.nextInt();
            if (option == 0) return;

            if (option < 0 || option >= ind) {
                System.out.println(wrongOption);
                return;
            }
            District district = districts.get(option - 1);


        } else if (service.getCategoryId().equals(CategoryServiceImpl.getCategoryByName(CategoryEnum.WALLETS.name()).getId())) {
            System.out.println("WALLET ID");
            System.out.print(arrow);
            String id = scannerStr.next();
        } else {
            HashMap<String, String> objects = service.getObjects();
            for (String key : objects.keySet()) {
                System.out.println(hashStart + key + hashEnd);
                System.out.print(arrow);
                String next = scannerStr.next();
                objects.put(key, next);
            }

            service.setObjects(objects);
        }


        User admin = userService.getUser("1");
        Card adminCard = new Card();
        int ind = 1;
        for (Card card : cardService.getAll()) {
            if (card.getOwnerId().equals(admin.getId())) {
                adminCard = card;
                break;
            }
        }

        ArrayList<Card> myCards = cardService.getCardsOfOwner(crtUser.getId());
        if (myCards.isEmpty()) {
            System.out.println(hashStart + "YOU HAVE NO CARDS" + hashEnd);
            return;
        }

        System.out.println("\nCHOOSE CARD");
        System.out.println(hashStart);
        for (Card card : myCards) {
            System.out.println((ind++) + ".\t" + card.getName());
            System.out.println(hashStart);

        }
        System.out.print(arrow);
        option = scannerInt.nextInt();
        if (option >= ind) {
            System.out.println(wrongOption);
            return;
        }
        Card myCard = myCards.get(option - 1);

        double commission = CommissionServiceImpl
                .getStaticByServiceId(service.getId()).getAmount().doubleValue();

        double cashBack = CashBackServiceImpl
                .getByServiceId(service.getId()).getAmount().doubleValue();

        System.out.println("Commission: " + (commission * 100) + " %");
        System.out.println("CashBack: " + (cashBack * 100) + " %");
        System.out.println("\nHOW MUCH MONEY YOU WANT TO SEND($)");
        double money = scannerInt.nextDouble();
        if (myCard.getAmount().subtract(BigDecimal.valueOf(money * 1.01)).signum() == -1) {
            System.out.println("\nYOU HAVE NO SUCH MONEY\n");
            return;
        }

        service.setBalance(service.getBalance().add(BigDecimal.valueOf(money)));
        myCard.setAmount(myCard.getAmount().subtract(BigDecimal.valueOf(money * (commission + 1))));
        myCard.setCashBack(myCard.getCashBack().add(BigDecimal.valueOf(money * cashBack)));
        adminCard.setAmount(adminCard.getAmount().add(BigDecimal.valueOf(money * (commission - cashBack))));
        serviceService.editService(service);
        cardService.editCard(myCard);
        cardService.editCard(adminCard);
        cardHistoryService.add(new CardHistory(myCard.getId(), service.getId(), BigDecimal.valueOf(money)));
        System.out.println(success);
    }

    private static void getSettings() {
        while (option != 0) {
            System.out.println("1.Change Commissions\t|\t2.Change CashBacks\t|\t3.Add Category\t|\t4.Add service\t|\t5.Add Region\t|\t6.Add District\t|\t0.Exit");
            System.out.print(arrow);
            option = scannerInt.nextInt();
            switch (option) {
                case 1:
                    changeCommission();
                    break;
                case 2:
                    changeCashBack();
                    break;
                case 3:
                    addCategory();
                    break;
                case 4:
                    addService();
                    break;
                case 5:
                    addRegion();
                    break;
                case 6:
                    addDistrict();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(wrongOption);
            }
        }
    }

    private static void addDistrict() {
        ArrayList<Region> regions = regionService.getAll();
        int ind = 1;
        System.out.println(hashStart + "CHOOSE ONE" + hashEnd);
        for (Region region: regions) {
            System.out.println((ind++) + "." + region.getName());
        }
        System.out.println("0.Exit");
        System.out.print(arrow);
        option = scannerInt.nextInt();
        if (option == 0) return;
        if (option < 0 || option >= ind) {
            System.out.println(wrongOption);
            return;
        }
        Region region = regions.get(option - 1);
        System.out.println(hashStart + "ENTER the NAME" + hashEnd);
        System.out.print(arrow);
        String name = scannerStr.next().toUpperCase();
        if (districtService.checkByNameAndRegionId(name, region.getId())) {
            System.out.println(hashStart + "ALREADY EXIST" + hashEnd);
            return;
        }

        districtService.add(new District(name,region.getId()));

        System.out.println(success);
    }

    private static void addRegion() {
        System.out.println(hashStart + "ENTER the NAME" + hashEnd);
        System.out.print(arrow);
        String name = scannerStr.next().toUpperCase();
        if (regionService.checkByName(name)) {
            System.out.println(hashStart + "ALREADY EXIST" + hashEnd);
            return;
        }
        regionService.add(new Region(name));
        System.out.println(success);
    }

    private static void addService() {
        ArrayList<Category> categories = categoryService.getAll();
        int ind = 1;
        System.out.println(hashStart + "CHOOSE ONE" + hashEnd);
        for (Category category : categories) {
            System.out.println((ind++) + "." + category.getName());
        }
        System.out.println("0.Exit");
        System.out.print(arrow);
        option = scannerInt.nextInt();
        if (option == 0) return;
        if (option < 0 || option >= ind) {
            System.out.println(wrongOption);
            return;
        }
        Category category = categories.get(option - 1);
        System.out.println(hashStart + "ENTER the NAME" + hashEnd);
        System.out.print(arrow);
        String name = scannerStr.next().toUpperCase();
        if (serviceService.checkByNameAndCategoryId(name, category.getId())) {
            System.out.println(hashStart + "ALREADY EXIST" + hashEnd);
            return;
        }

        Service service = new Service(name, BigDecimal.valueOf(0), category.getId());
        if (!category.getId().equals(CategoryServiceImpl.getCategoryByName(CategoryEnum.WALLETS.name()).getId()) &&
                !category.getId().equals(CategoryServiceImpl.getCategoryByName(CategoryEnum.TRANSFER.name()).getId()) &&
                !category.getId().equals(CategoryServiceImpl.getCategoryByName(CategoryEnum.MOBILE_OPERATORS.name()).getId()) &&
                !category.getId().equals(CategoryServiceImpl.getCategoryByName(CategoryEnum.COMMUNAL.name()).getId()))
            while (true) {
                System.out.println(hashStart + "FIELD NAME" + hashEnd);
                System.out.println("\"-\" FOR EXIT");
                System.out.print(arrow);
                String key = scannerStr.next().toUpperCase();
                if (key.equals("-")) break;
                if (service.checkObjectByKey(key)) {
                    System.out.println(hashStart + "ALREADY EXIST" + hashEnd);
                    continue;
                }
                service.addObject(key, "");

            }

        serviceService.add(service);
        cashBackService.add(new CashBack(service.getId(), BigDecimal.valueOf(0)));
        commissionService.add(new Commission(service.getId(), BigDecimal.valueOf(0)));
        System.out.println(success);
    }

    private static void addCategory() {
        System.out.println(hashStart + "ENTER the NAME" + hashEnd);
        System.out.print(arrow);
        String name = scannerStr.next().toUpperCase();
        if (categoryService.checkByName(name)) {
            System.out.println(hashStart + "ALREADY EXIST" + hashEnd);
            return;
        }
        categoryService.add(new Category(name));
        System.out.println(success);
    }

    private static void changeCashBack() {
        ArrayList<Category> categories = categoryService.getAll();
        int ind = 1;
        System.out.println("CHOOSE ONE");
        for (Category category : categories) {
            System.out.println((ind++) + "." + category.getName());
        }
        System.out.print(arrow);
        option = scannerInt.nextInt();
        if (option >= ind) {
            System.out.println(wrongOption);
            return;
        }
        Category category = categories.get(option - 1);

        CashBack cashBack = null;
        if (category.getName().equals(CategoryEnum.TRANSFER.name())) {
            cashBack = CashBackServiceImpl
                    .getByServiceId(ServiceServiceImpl.getByName(CategoryEnum.TRANSFER.name()).getId());

        } else {
            ArrayList<Service> services = serviceService.getByCategoryId(category.getId());
            ind = 1;
            for (Service service : services) {
                System.out.println((ind++) + "." + service.getName());
            }
            System.out.println("0.Exit");
            System.out.print(arrow);
            option = scannerInt.nextInt();
            if (option == 0) {
                option = -1;
                return;
            }
            if (option >= ind || option < 0) {
                System.out.println(wrongOption);
                return;
            }
            Service service = services.get(option - 1);
            cashBack = CashBackServiceImpl.getByServiceId(service.getId());
        }

        System.out.println(hashStart + "OLD AMOUNT => " + (cashBack.getAmount().doubleValue() * 100) + " %");
        System.out.println("NEW AMOUNT(%)");
        System.out.print(arrow);
        double newAmount = scannerStr.nextDouble();
        if (newAmount < 0 || newAmount > 100) {
            System.out.println(wrongOption);
            return;
        }
        cashBack.setAmount(BigDecimal.valueOf(newAmount / 100));
        if (cashBackService.editCashBack(cashBack))
            System.out.println(success);
        else
            System.out.println(error);


    }

    private static void changeCommission() {
        ArrayList<Category> categories = categoryService.getAll();
        int ind = 1;
        System.out.println("CHOOSE ONE");
        for (Category category : categories) {
            System.out.println((ind++) + "." + category.getName());
        }
        System.out.print(arrow);
        option = scannerInt.nextInt();
        if (option >= ind) {
            System.out.println(wrongOption);
            return;
        }
        Category category = categories.get(option - 1);

        Commission commission = null;
        if (category.getName().equals(CategoryEnum.TRANSFER.name())) {
            commission = CommissionServiceImpl
                    .getStaticByServiceId(ServiceServiceImpl.getByName(CategoryEnum.TRANSFER.name()).getId());
        } else {
            ArrayList<Service> services = serviceService.getByCategoryId(category.getId());
            ind = 1;
            for (Service service : services) {
                System.out.println((ind++) + "." + service.getName());
            }
            System.out.println("0.Exit");
            System.out.print(arrow);
            option = scannerInt.nextInt();
            if (option == 0) {
                option = -1;
                return;
            }
            if (option >= ind || option < 0) {
                System.out.println(wrongOption);
                return;
            }
            Service service = services.get(option - 1);
            commission = CommissionServiceImpl.getStaticByServiceId(service.getId());
        }
        System.out.println(hashStart + "OLD AMOUNT => " + (commission.getAmount().doubleValue() * 100) + " %");
        System.out.println("NEW AMOUNT(%)");
        System.out.print(arrow);
        double newAmount = scannerStr.nextDouble();
        if (newAmount < 0 || newAmount > 100) {
            System.out.println(wrongOption);
            return;
        }

        commission.setAmount(BigDecimal.valueOf(newAmount / 100));
        if (commissionService.editCommission(commission))
            System.out.println(success);
        else
            System.out.println(error);

    }

    private static void getHistory() {
        while (option != 0) {
            System.out.println("1.Input\t|\t2.Output\t|\t3.Payment History\t|\t0.Exit");
            System.out.print(arrow);
            option = scannerInt.nextInt();
            switch (option) {
                case 1:
                    getInput();
                    break;
                case 2:
                    getOutput();
                    break;
                case 3:
                    getPaymentHistory();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(wrongOption);
            }
        }

    }

    private static void getPaymentHistory() {
        System.out.println("TO\t|\tPRICE\t|\tTIME");
        for (Card card : cardService.getCardsOfOwner(crtUser.getId())) {
            System.out.println(hashStart + card.getName() + hashEnd);
            for (CardHistory cardHistory : cardHistoryService.getHistoryFromCard(card.getId())) {
                Service service = serviceService.getById(cardHistory.getTo());
                if (service != null)
                    System.out.println(service.getName() + "\t|\t" + cardHistory.getPrice() + " $\t|\t" + cardHistory.getTime());
            }
        }
    }

    private static void getOutput() {
        System.out.println("TO\t|\tPRICE\t|\tTIME");
        for (Card card : cardService.getCardsOfOwner(crtUser.getId())) {
            System.out.println(hashStart + card.getName() + hashEnd);
            for (CardHistory cardHistory : cardHistoryService.getHistoryFromCard(card.getId())) {
                Card cardTo = cardService.getById(cardHistory.getTo());
                System.out.println(userService.getById(cardTo.getOwnerId()).getPhone() + "(" + cardTo.getName() + ")" + "\t|\t" + cardHistory.getPrice() + " $\t|\t" + cardHistory.getTime());
            }
        }

    }

    private static void getInput() {
        System.out.println("FROM\t|\tPRICE\t|\tTIME");
        for (Card card : cardService.getCardsOfOwner(crtUser.getId())) {
            System.out.println(hashStart + card.getName() + hashEnd);
            for (CardHistory cardHistory : cardHistoryService.getHistoryToCard(card.getId())) {
                Card cardFrom = cardService.getById(cardHistory.getFrom());
                System.out.println(userService.getById(cardFrom.getOwnerId()).getPhone() + "(" + cardFrom.getName() + ")" + "\t|\t" + cardHistory.getPrice() + " $\t|\t" + cardHistory.getTime());
            }
        }

    }

    private static void sendMoney() {
        String phone;
        int ind = 1;

        User admin = userService.getUser("1");
        Card adminCard = new Card();
        for (Card card : cardService.getAll()) {
            if (card.getOwnerId().equals(admin.getId())) {
                adminCard = card;
                break;
            }
        }

        ArrayList<Card> myCards = cardService.getCardsOfOwner(crtUser.getId());
        if (myCards.isEmpty()) {
            System.out.println(hashStart + "YOU HAVE NO CARDS" + hashEnd);
            return;
        }

        System.out.println("\nCHOOSE CARD");
        System.out.println(hashStart);
        for (Card card : myCards) {
            System.out.println((ind++) + ".\t" + card.getName());
            System.out.println(hashStart);

        }
        System.out.print(arrow);
        option = scannerInt.nextInt();
        if (option >= ind) {
            System.out.println(wrongOption);
            return;
        }
        Card myCard = myCards.get(option - 1);

        System.out.println("\nTO WHOM YOU WANT TO SEND MONEY(PHONE)");
        System.out.print(arrow);
        phone = scannerStr.next();
        if (!userService.isExist(phone)) {
            System.out.println(hashStart + "USER NOT FOUND" + hashEnd);
            return;
        }
        User user = userService.getUser(phone);
        ArrayList<Card> cardsOfOwner = cardService.getCardsOfOwner(user.getId());
        System.out.println("\nTO WHICH CARD YOU WANT TO SEND MONEY");
        ind = 1;
        System.out.println(hashStart);
        for (Card card : cardsOfOwner) {
            if (!card.getOwnerId().equals(crtUser.getId()) && !card.getId().equals(adminCard.getId())) {
                System.out.println((ind++) + ".\t" + card.getName());
                System.out.println(hashStart);
            }
        }
        if (ind == 1) {
            System.out.println(hashStart + "Other cards not found" + hashEnd);
            return;
        }
        System.out.print(arrow);
        option = scannerInt.nextInt();
        if (option >= ind) {
            System.out.println(wrongOption);
            return;
        }


        double commission = CommissionServiceImpl
                .getStaticByServiceId(ServiceServiceImpl
                        .getByName(CategoryEnum.TRANSFER.name()).getId()).getAmount().doubleValue();

        double cashBack = CashBackServiceImpl
                .getByServiceId(ServiceServiceImpl.getByName(CategoryEnum.TRANSFER.name()).getId()).getAmount().doubleValue();

        Card otherCard = cardsOfOwner.get(option - 1);
        System.out.println("Commission: " + (commission * 100) + " %");
        System.out.println("CashBack: " + (cashBack * 100) + " %");
        System.out.println("\nHOW MUCH MONEY YOU WANT TO SEND($)");
        double money = scannerInt.nextDouble();
        if (myCard.getAmount().subtract(BigDecimal.valueOf(money * 1.01)).signum() == -1) {
            System.out.println("\nYOU HAVE NO SUCH MONEY\n");
            return;
        }

        myCard.setAmount(myCard.getAmount()
                .subtract(BigDecimal.valueOf(money * (1 + commission))));

        myCard.setCashBack(myCard.getCashBack().add(BigDecimal.valueOf(money * cashBack)));

        otherCard.setAmount(otherCard.getAmount().add(BigDecimal.valueOf(money)));


        adminCard.setAmount(adminCard.getAmount().add(BigDecimal.valueOf(money * (commission - cashBack))));
        cardHistoryService.add(new CardHistory(myCard.getId(), otherCard.getId(), BigDecimal.valueOf(money)));
        cardService.editCard(myCard);
        cardService.editCard(otherCard);
        cardService.editCard(adminCard);
        System.out.println(success);
    }

    private static void addCard() {
        String name, cardNum;
        System.out.println("NAME");
        System.out.print(arrow);
        name = scannerStr.next();
        System.out.println("CARD NUMBER(16)");
        System.out.print(arrow);
        cardNum = scannerStr.next();
        if (cardNum.length() != 16) {
            System.out.println(wrongOption);
            return;
        }
        cardService.add(new Card(name, crtUser.getId(), BigDecimal.valueOf(100), BigDecimal.valueOf(0), cardNum));
        System.out.println(success);
    }

    private static void getMyCards() {
        ArrayList<Card> cardsOfOwner = cardService.getCardsOfOwner(crtUser.getId());
        if (cardsOfOwner.isEmpty()) {
            System.out.println(hashStart + "YOU HAVE NO CARDS" + hashEnd);
            return;
        }
        int ind = 1;
        System.out.println(hashStart);
        for (Card card : cardsOfOwner) {
            System.out.println((ind++) + ".\t" + card.getName());
            System.out.println("Amount: " + card.getAmount() + " $");
            System.out.println("Cashback: " + card.getCashBack() + " $");
            System.out.println("Card number: " + card.getCardNum().substring(0, 6) + "**..*" + card.getCardNum().substring(card.getCardNum().length() - 4));
            System.out.println("Expire Date: " + card.getExpireDate());
            System.out.print(hashStart);
        }
    }

    private static void signUp() {
        String phone, password, prePassword, name;
        System.out.println("\nPHONE ");
        System.out.print(arrow);
        phone = scannerStr.next();
        if (userService.isExist(phone)) {
            System.out.println(hashStart + "ALREADY EXIST" + hashEnd);
            return;
        }
        System.out.println("\nPASSWORD: ");
        System.out.print(arrow);
        password = scannerStr.next();
        System.out.println("\nPRE PASSWORD ");
        System.out.print(arrow);
        prePassword = scannerStr.next();
        if (!password.equals(prePassword)) {
            System.out.println(hashStart + "PASSWORDS DON'T MATCH" + hashEnd);
            return;
        }
        System.out.println("\nNAME");
        System.out.print(arrow);
        name = scannerStr.next();
        User user = new User(name, phone, password, RoleEnum.USER);
        userService.add(user);
        crtUser = user;
        System.out.println(success);
    }

    private static boolean signIn() {
        String phone, password;
        System.out.println("\nPHONE ");
        System.out.print(arrow);
        phone = scannerStr.next();
        if (!userService.isExist(phone)) {
            System.out.println(hashStart + "USER NOT FOUND" + hashEnd);
            return false;
        }
        System.out.println("\nPASSWORD ");
        System.out.print(arrow);
        password = scannerStr.next();
        User user = userService.login(phone, password);
        if (user == null) {
            System.out.println(hashStart + "PASSWORD IS WRONG" + hashEnd);
            return false;
        }
        crtUser = user;
        return true;
    }

    public static int menu() {
        System.out.println("1.Sign in\t|\t2.Sign up\t|\t0.Exit");
        System.out.print(arrow);
        return scannerInt.nextInt();
    }
}
