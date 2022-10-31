package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

class MoneyTransferTest {
  @Test
  void shouldTransferValidMoneyFromFirstToSecondCard() {

    var loginPage = open("http://localhost:9999", LoginPage.class);
    var autoInfo = DataHelper.getAuthInfo();
    var varificationPage = loginPage.validLogin(autoInfo);
    var varificationCode = DataHelper.getVerificationCode();
    var dashbordPage = varificationPage.validVerify(varificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalanc = dashbordPage.getCardBalance(firstCardInfo);
    var secondCardBalance = dashbordPage.getCardBalance(secondCardInfo);
    var amount = generateValidAmount(firstCardBalanc);
    var expectedBalanceFirstCard = firstCardBalanc - amount;
    var expectedBalanceSecondCard = secondCardBalance + amount;
    var transferPage = dashbordPage.selectCardToTransfer(secondCardInfo);
    dashbordPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
    var actualBalanceFirstCard = dashbordPage.getCardBalance(firstCardInfo);
    var actualBalanceSecondCard = dashbordPage.getCardBalance(secondCardInfo);
    assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
    assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

  }
  @Test
  void shouldTransferValidMoneyFromSecondToFirstCard() {

    var loginPage = open("http://localhost:9999", LoginPage.class);
    var autoInfo = DataHelper.getAuthInfo();
    var varificationPage = loginPage.validLogin(autoInfo);
    var varificationCode = DataHelper.getVerificationCode();
    var dashbordPage = varificationPage.validVerify(varificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalanc = dashbordPage.getCardBalance(firstCardInfo);
    var secondCardBalance = dashbordPage.getCardBalance(secondCardInfo);
    var amount = generateValidAmount(secondCardBalance);
    var expectedBalanceFirstCard = firstCardBalanc + amount;
    var expectedBalanceSecondCard = secondCardBalance - amount;
    var transferPage = dashbordPage.selectCardToTransfer(firstCardInfo);
    dashbordPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCardInfo);
    var actualBalanceFirstCard = dashbordPage.getCardBalance(firstCardInfo);
    var actualBalanceSecondCard = dashbordPage.getCardBalance(secondCardInfo);
    assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
    assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

  }
  @Test
  void shouldTransferZeroMoneyFromFirstToSecondCard() {

    var loginPage = open("http://localhost:9999", LoginPage.class);
    var autoInfo = DataHelper.getAuthInfo();
    var varificationPage = loginPage.validLogin(autoInfo);
    var varificationCode = DataHelper.getVerificationCode();
    var dashbordPage = varificationPage.validVerify(varificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalanc = dashbordPage.getCardBalance(firstCardInfo);
    var secondCardBalance = dashbordPage.getCardBalance(secondCardInfo);
    var amount = 0;
    var expectedBalanceFirstCard = firstCardBalanc - amount;
    var expectedBalanceSecondCard = secondCardBalance + amount;
    var transferPage = dashbordPage.selectCardToTransfer(secondCardInfo);
    dashbordPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
    var actualBalanceFirstCard = dashbordPage.getCardBalance(firstCardInfo);
    var actualBalanceSecondCard = dashbordPage.getCardBalance(secondCardInfo);
    assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
    assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
  }
  @Test
  void shouldTransferZeroMoneyFromSecondToFirstCard() {

    var loginPage = open("http://localhost:9999", LoginPage.class);
    var autoInfo = DataHelper.getAuthInfo();
    var varificationPage = loginPage.validLogin(autoInfo);
    var varificationCode = DataHelper.getVerificationCode();
    var dashbordPage = varificationPage.validVerify(varificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalanc = dashbordPage.getCardBalance(firstCardInfo);
    var secondCardBalance = dashbordPage.getCardBalance(secondCardInfo);
    var amount = 0;
    var expectedBalanceFirstCard = firstCardBalanc + amount;
    var expectedBalanceSecondCard = secondCardBalance - amount;
    var transferPage = dashbordPage.selectCardToTransfer(firstCardInfo);
    dashbordPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCardInfo);
    var actualBalanceFirstCard = dashbordPage.getCardBalance(firstCardInfo);
    var actualBalanceSecondCard = dashbordPage.getCardBalance(secondCardInfo);
    assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
    assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

  }

  @Test
  void shouldTransferInvalidMoneyFromFirstToSecondCard() {

    var loginPage = open("http://localhost:9999", LoginPage.class);
    var autoInfo = DataHelper.getAuthInfo();
    var varificationPage = loginPage.validLogin(autoInfo);
    var varificationCode = DataHelper.getVerificationCode();
    var dashbordPage = varificationPage.validVerify(varificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalanc = dashbordPage.getCardBalance(firstCardInfo);
    var secondCardBalance = dashbordPage.getCardBalance(secondCardInfo);
    var amount = generateInvalidAmount(firstCardBalanc);
    var transferPage = dashbordPage.selectCardToTransfer(secondCardInfo);
    transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
    transferPage.findErrorMessage("Выполнена попытка перевода суммы, превыщающий баланс карты!");
    var actualBalanceFirstCard = dashbordPage.getCardBalance(firstCardInfo);
    var actualBalanceSecondCard = dashbordPage.getCardBalance(secondCardInfo);
    assertEquals(firstCardBalanc, actualBalanceFirstCard);
    assertEquals(secondCardBalance, actualBalanceSecondCard);

  }
  @Test
  void shouldTransferInvalidMoneyFromSecondToFirstCard() {

    var loginPage = open("http://localhost:9999", LoginPage.class);
    var autoInfo = DataHelper.getAuthInfo();
    var varificationPage = loginPage.validLogin(autoInfo);
    var varificationCode = DataHelper.getVerificationCode();
    var dashbordPage = varificationPage.validVerify(varificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalanc = dashbordPage.getCardBalance(firstCardInfo);
    var secondCardBalance = dashbordPage.getCardBalance(secondCardInfo);
    var amount = generateInvalidAmount(secondCardBalance);
    var transferPage = dashbordPage.selectCardToTransfer(firstCardInfo);
    transferPage.makeValidTransfer(String.valueOf(amount), secondCardInfo);
    transferPage.findErrorMessage("Выполнена попытка перевода суммы, превыщающий баланс карты!");
    var actualBalanceFirstCard = dashbordPage.getCardBalance(firstCardInfo);
    var actualBalanceSecondCard = dashbordPage.getCardBalance(secondCardInfo);
    assertEquals(firstCardBalanc, actualBalanceFirstCard);
    assertEquals(secondCardBalance, actualBalanceSecondCard);

  }
  @Test
  void shouldTransferMoneyFromThirdToSecondCardErrorMessage() {

    var loginPage = open("http://localhost:9999", LoginPage.class);
    var autoInfo = DataHelper.getAuthInfo();
    var varificationPage = loginPage.validLogin(autoInfo);
    var varificationCode = DataHelper.getVerificationCode();
    var dashbordPage = varificationPage.validVerify(varificationCode);
    var secondCardInfo = getSecondCardInfo();
    var thirdCardInfo = getThirdCardInfo();
    var amount = 1;
    var transferPage = dashbordPage.selectCardToTransfer(secondCardInfo);
    transferPage.makeValidTransfer(String.valueOf(amount), thirdCardInfo);
    transferPage.findErrorMessage("Ошибка! Произошла ошибка");

  }
}

