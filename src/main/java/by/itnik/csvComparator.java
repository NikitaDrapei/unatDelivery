package by.itnik;

import by.itnik.model.beans.logins;
import by.itnik.model.beans.postings;

import java.util.List;

public class csvComparator {

    public void compare(List<logins> logins, List<postings> postings) {
        //Пробегаюсь по всем логинам, т.е 1 логин из logins смотрится по всем логинам из postings, если условие верно в postings меняется значение isAuth
        //Если нет, то берем следующее имя, и так по порядку
        for (logins logs : logins) {
            for (postings post : postings) {
                if (post.getUser_Name().equals(logs.getAppAccountName()) && logs.getIsActive()) {
                    post.setIsAuthDelivery(true);
                }
            }
        }
    }
}
