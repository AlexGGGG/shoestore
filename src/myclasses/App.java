/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Customer;
import entity.Purchase;
import entity.Shoe;
import entity.Income;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.SaverToBase;
import tools.SaverToFiles;


/**
 *
 * @author 
 */
public class App {
    public static boolean toFile = false;

    
    private final Scanner scanner = new Scanner(System.in);
    private List<Shoe> shoes = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Purchase> purchases = new ArrayList<>();
    Income income = new Income();
    private final Keeping keeper;
    
    public App(){
        if(toFile){
            keeper = new SaverToFiles();
        }else{
            keeper = new SaverToBase();
        }
        shoes = keeper.loadShoes();
        customers = keeper.loadCustomers();
        purchases = keeper.loadPurchases();
    }
    
    public void run(){
        String repeat = "yes";
        do{
            System.out.println("Выберите номер задачи: ");
            System.out.println("0: Закрыть программу");
            System.out.println("1: Добавить покупателя");
            System.out.println("2: Добавить обувь");
            System.out.println("3: Список обуви");
            System.out.println("4: Список покупателей");
            System.out.println("5: Купить обувь");
            System.out.println("6: Доход магазина");
            System.out.println("7: Добавить деньги покупателю");
            System.out.println("7: Изменить товар");
            System.out.println("8: Изменить пользователя");
            int task = getNumber();

            switch (task) {
                case 0:
                    repeat = "no";
                    break;
                    
                case 1:
                    addCustomer();
                    break;
                
                case 2:
                    addShoe();
                    break;
                    
                case 3:
                    listShoe();
                    break;
                    
                case 4:
                    listCustomer();
                    break;
                    
                case 5:
                    purchaseShoe();
                    break;
                   
                case 6:
                    Income();
                    break;
                    
                case 7:
                    addMoney();
                    break;
                    
                case 8:
                    changeCustomer();
                    break;
                    
                case 9:
                    changeShoe();
                    break;
                   
       }
    }while("yes".equals(repeat));
       System.out.println("До свидания!");
    }

    private int getNumber() {
        int number;
        do{
           String strNumber = scanner.nextLine();
           try {
               number = Integer.parseInt(strNumber);
               return number;
           } catch (NumberFormatException e) {
               System.out.println("Введено \""+strNumber+"\". Выбирайте номер ");
           }
       }while(true);
    }

    private void addCustomer() {
        System.out.println("Добавить покупателя: ");
        Customer customer = new Customer();
        System.out.print("Введите имя покупателя: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Введите телефон покупателя: ");
        customer.setPhone(scanner.nextLine());
        System.out.println("Введите количество денег у покупателя");
        customer.setMoney(scanner.nextDouble());scanner.nextLine();
        System.out.println("Покупатель инициирован: "+customers.toString());
        customers.add(customer);
        keeper.saveCustomers(customers);
    }

    private void addShoe() {
        System.out.println("Добавить обувь: ");
        Shoe shoe = new Shoe();
        System.out.println("Введите фирму обуви: ");
        shoe.setShoeFirm(scanner.nextLine());
        System.out.println("Введите размер обуви: ");
        shoe.setShoeSize(scanner.nextDouble());scanner.nextLine();
        System.out.println("Введите цену обуви: ");
        shoe.setShoePrice(scanner.nextDouble());scanner.nextLine();
        System.out.print("Введите количество пар обуви: ");
        shoe.setQuantity(scanner.nextInt());scanner.nextLine();
        shoe.setCount(shoe.getQuantity());
        System.out.println("Обувь инициирована: "+shoes.toString());
        shoes.add(shoe);
        keeper.saveShoes(shoes);
    }

    private void listShoe() {
        System.out.println("Список кроссовок: ");
        for (int i = 0; i < shoes.size(); i++) {
            if(shoes.get(i) != null && shoes.get(i).getCount() > 0){
                System.out.printf("%d. Фирма: %s. Размер: %s. Цена: %s%n.",
                            i+1,
                            shoes.get(i).getShoeFirm(),
                            shoes.get(i).getShoeSize(),
                            shoes.get(i).getShoePrice()
                            );
            }

        }
    }

    private void listCustomer() {
        System.out.println("Список покупателей: ");
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.printf("%d. %s %s. тел.: %s%n. кошелек: %s%n."
                       ,i+1
                       ,customers.get(i).getFirstname()
                       ,customers.get(i).getLastname()
                       ,customers.get(i).getPhone()
                       ,customers.get(i).getMoney()
                       );
            }
        }
    }

    private void purchaseShoe() {
        System.out.println("Список кроссовок: ");
        listShoe();
        
        System.out.println("Выберите номер обуви: ");
        int numberShoe = scanner.nextInt(); scanner.nextLine();
        
        System.out.println("Список покупателей: ");
        listCustomer();
        
        System.out.println("Выберите номер покупателя: ");
        int numberCustomer = scanner.nextInt(); scanner.nextLine();
        
        
        
        Purchase purchase = new Purchase();
        purchase.setShoe(shoes.get(numberShoe - 1));
        purchase.setCustomer(customers.get(numberCustomer - 1));
        Calendar c = new GregorianCalendar();
        purchase.setPurchaseShoe(c.getTime());
        if(purchase.getCustomer().getMoney()>=purchase.getShoe().getShoePrice()){
            System.out.printf("Обувь %s %s купил %s %s за %.2f евро %s%n",
                    purchase.getShoe().getShoeFirm(),
                    purchase.getShoe().getShoeSize(),
                    purchase.getCustomer().getFirstname(),
                    purchase.getCustomer().getLastname(),
                    purchase.getShoe().getShoePrice(),
                    purchase.getPurchaseShoe()
            );
            purchase.getCustomer().setMoney(purchase.getCustomer().getMoney()-purchase.getShoe().getShoePrice());
            income.setGeneralMoney(income.getGeneralMoney()+purchase.getShoe().getShoePrice());
            purchases.add(purchase);
            purchase.getShoe().setCount(purchase.getShoe().getCount() - 1);
            keeper.saveShoes(shoes);
        }
        else{
        System.out.println("Этот пользователь не может совершить покупку, так как у него не хватает денег!");
        keeper.savePurchases(purchases);
    }
    
    }   

    private void Income() {
        System.out.println("Доход магазина: ");
        System.out.printf("Выручка магазина составляет: %.2f%n",income.getGeneralMoney());
        keeper.saveIncome(income);
    }

    private void addMoney() {
        System.out.println("Добавить в кошелек деньги");
        listCustomer();
        System.out.println("Выберите покупателя: ");
        int choice= scanner.nextInt(); scanner.nextLine();
        System.out.println("Введите сколько денег вы хотите добавить этому покупателю: ");
        int add= scanner.nextInt(); scanner.nextLine();
        customers.get(choice-1).setMoney(customers.get(choice-1).getMoney()+add);
    }

    private void changeCustomer() {
        
    }

    private void changeShoe() {
        Shoe shoe = new Shoe();
        System.out.println("Выберите обувь которую хотите изменить: ");
        listShoe();
        int choice= scanner.nextInt(); scanner.nextLine();
        String repeat2 = "yes";
        do {
            System.out.println("0: Выход");
            System.out.println("1: Изменить модель");
            System.out.println("2: Изменить цену");
            System.out.println("3: Добавить кол-во");
            int task2 = scanner.nextInt(); scanner.nextLine();
            switch (task2) {
                case 0:
                    System.out.println("Выход");
                    repeat2 = "no";
                    break;
                case 1:
                    System.out.print("Другая фирма: ");
                    shoes.get(choice-1).setShoeFirm(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Новая цена: ");
                    shoes.get(choice-1).setShoePrice(scanner.nextDouble());
                    break;
                case 3:
                    System.out.println("Обновить количество пар: ");
                    shoes.get(choice-1).setCount(shoes.get(choice-1).getCount()+scanner.nextInt());
                    break;
                default:
                    System.out.println("Выберите номер из списка!");
            }
        } while ("yes".equals(repeat2));
        System.out.println("Обувь инициирована как: "+shoes.get(choice-1).toString());
        shoes.add(shoe);
        keeper.saveShoes(shoes);    
    
    }

}