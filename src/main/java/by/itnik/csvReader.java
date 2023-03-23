package by.itnik;

import by.itnik.model.beans.logins;
import by.itnik.model.beans.postings;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class csvReader {
    public List<logins> readLoginsCsv(String FILE_LOGINS) {
        //Коллекция
        List<logins> logines = new ArrayList<>();

        try {
            //Создание файла с которого будет считываться данные в объекты
            CSVReader readerLogins = new CSVReader(new FileReader(FILE_LOGINS));
            List<String[]> records;
            records = readerLogins.readAll();
            Iterator<String[]> iterator = records.iterator();
            String[] record = iterator.next();

            //Запись данных из logins.csv в коллекцию
            while (iterator.hasNext()) {
                record = iterator.next();
                logins login = new logins();
                login.setApplication(record[0]);
                String setAppName = record[1].trim();
                login.setAppAccountName(setAppName.substring(0, setAppName.length() - 1));//Костыль, принимает значение с запятой в конце
                ///###
                String isActive = record[2].toLowerCase();
                boolean bool2 = Boolean.parseBoolean(isActive.substring(0, isActive.length() - 1));//repeat
                //###
                login.setIsActive(bool2);
                login.setJobTitle(record[3]);
                login.setDepartment(record[4]);
                logines.add(login);
            }
            return logines;
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public List<postings> readPostingsCsv(String FILE_POSTINGS) {
        //Коллекция
        List<postings> postinges = new ArrayList<>();


        try {
            //Создания билдера для парсинга по определенным меткам, в моем случае по знаку ;
            CSVParser csvParser = new CSVParserBuilder()
                    .withSeparator(';')
                    .withIgnoreQuotations(true)
                    .build();

            CSVReader readerPostings = new CSVReaderBuilder(new FileReader(FILE_POSTINGS))
                    .withSkipLines(1)
                    .withCSVParser(csvParser)
                    .build();

            List<String[]> records = readerPostings.readAll();
            Iterator<String[]> iterator = records.iterator();
            String[] record = iterator.next();

            //Запись данных из postings.csv в коллекцию
            while (iterator.hasNext()) {
                record = iterator.next();
                postings posting = new postings();
                String setmat = record[0].trim();
                posting.setMat_Doc(Double.parseDouble(setmat));
                String setitem = record[1].trim();
                posting.setItem(Integer.parseInt(setitem.replaceAll(",", ".")));

                //data util -> sql data doc_date
                String docDateUtilS = record[2].trim();
                java.util.Date docDateUtil = new SimpleDateFormat("dd.MM.yyyy").parse(docDateUtilS);
                java.sql.Date docDateSql = new java.sql.Date(docDateUtil.getTime());

                //data util -> sql data pstngDate
                String pstngDateUtilS = record[3].trim();
                java.util.Date setPstngDate = new SimpleDateFormat("dd.MM.yyyy").parse(pstngDateUtilS);
                java.sql.Date pstngSql = new java.sql.Date(setPstngDate.getTime());

                posting.setDoc_Date(docDateSql);
                posting.setPstng_Date(pstngSql);
                posting.setMaterial_Description(record[4]);
                String setQuant = record[5].trim();
                posting.setQuantity(Integer.parseInt(setQuant));
                posting.setbUn(record[6]);
                String setAmountLc = record[7].trim();
                //Парсинг double и изменение знака , на .
                posting.setAmount_LC(Double.parseDouble(setAmountLc.replaceAll(",", ".")));
                posting.setCrcy(record[8]);
                String setUserName = record[9].trim();
                posting.setUser_Name(setUserName);
                //Так как не известно является ли поставщик auth, всем ставим false
                posting.setIsAuthDelivery(false);
                postinges.add(posting);
            }
            return postinges;
        } catch (IOException | CsvException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
