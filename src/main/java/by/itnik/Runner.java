package by.itnik;

import by.itnik.model.beans.logins;
import by.itnik.model.beans.postings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Runner {
    private static final String FILE_LOGINS = "src/main/resources/logins.csv";
    private static final String FILE_POSTINGS = "src/main/resources/postings.csv";

    public static void main(String[] args) throws ParseException {
        //Переменные
        int menu1, menu2;
        boolean fExit = true, fExit1;

        Scanner sc = new Scanner(System.in);
        //Объекты классов
        csvReader csvRead = new csvReader();
        csvComparator csvComparator = new csvComparator();
        connectorSql cnsql = new connectorSql();
        //Прочтение и создание коллекции из logins и postings
        List<logins> logines = csvRead.readLoginsCsv(FILE_LOGINS);
        List<postings> postinges = csvRead.readPostingsCsv(FILE_POSTINGS);

        //Сравнение есть ли имя postinges в списке logines и является ли он isActive
        csvComparator.compare(logines, postinges);

        //Отправляю исправленные данные в базу данных
        cnsql.addData(logines, postinges);

        do {
            fExit1 = true;
            System.out.println("Выберите пункт меню:");
            System.out.println("1. Запрос данных из бд(всех) по дате");
            System.out.println("2. Запрос данных из бд(только авторизированных) по дате");
            System.out.println("3. Выход");
            do {///Проверка на выбор меню
                menu1 = sc.nextInt();
                if (menu1 <= 0 || menu1 > 3) {
                    System.out.println("Input correct number between 1 to 3");
                } else {
                    fExit1 = false;
                }
            } while (fExit1);
            sc.nextLine();
            if (menu1 != 3) {
                System.out.println("Введите дату, с которой требуется вывести данные, пример: 20-05-2020");
            }
            switch (menu1) {
                case 1://Запрос данных из бд(всех) по дате от, remake date in method
                    String stringDate = sc.nextLine();
                    Date dateDate = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);
                    java.sql.Date sqlDate = new java.sql.Date(dateDate.getTime());
                    boolean active = false;
                    cnsql.selectDateAuth(sqlDate, active);
                    break;
                case 2://Запрос данных из бд(только авторизированных) по дате от
                    String stringDate2 = sc.nextLine();
                    Date dateDate2 = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate2);
                    java.sql.Date sqlDate2 = new java.sql.Date(dateDate2.getTime());
                    boolean active2 = true;
                    cnsql.selectDateAuth(sqlDate2, active2);
                    break;
                case 3://Выход
                    sc.close();
                    fExit = false;
                    break;
            }
        } while (fExit);
    }


}
