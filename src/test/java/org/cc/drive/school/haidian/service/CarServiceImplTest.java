package org.cc.drive.school.haidian.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by chichen.cc on 2016/2/22.
 */
public class CarServiceImplTest extends BaseService {
    @Autowired
    private CarService carService;
    private static String ck="Cookie: CNZZDATA1257130924=65651327-1453712476-%7C1456221215; CNZZDATA5234107=cnzz_eid%3D126876391-1453711289-http%253A%252F%252Fhaijia.bjxueche.net%252F%26ntime%3D1456222786; ASP.NET_SessionId=roggtd24gorxjgyz2thz052e; ImgCode=Auu6/TH6IwY=; LoginOn=z5VjA2RRhbK200GBfLnsGJZiuZf8ntp+y3aJzqws/GOgPi/b4qilbIV5TZXVtFojvNrBahNuFD3sH9gzk7DaFct2ic6sLPxj9jzl6G57APZz2ehuq3NFOhwvsButyqGRK7p5c5eImrC+siiMDIDKaZ/zJby+jow31B9LDp9z8sI1+1iTNJ6uqA7sOuqSCTclzPdJHACGH+M7mzlF1OwtAkYMjRoshEBZU20HutUa4xhklxhC63xGt+M1CgLI+Vi62Omtu9N8N2SqUG9J5J2WJvHLE1XHTD2E56qlnNYi/5lxicVug4hfEnD2WEfLAKjP3qDyJyqbN6PY5FNnLSLmkVCYGodLzRZf7OH+KONLG+RQL6kmhgBBpT8Km3KNaJrZPz9xmkOSopDVVthTWtvx1QLCv25cQI3ispTztq1l2o8O+fXBOgp2QlXsMl22snlLIbnMUx9nKXKWL5e3rzS+qL3IBuyMBBLpZ0xYbvVbzroy55xQ/S243mhVw3D0IKb66mdX/XbcQfgTwlxE3tYQgICLcnbzC8bir5RROii50gyXkwi6DW0vkq0xjJ7CYkyOF1FDsxtKJlLmB3GHu1z16GtfXWKMsD0LHjJQg/7UQRk2RO9CBupOdI037sJnda8v";
    @Test
    public void testgetCookie() throws IOException {
        String queryJson = carService.queryCars(ck);
        ll.info("json:" + queryJson);
    }

}
