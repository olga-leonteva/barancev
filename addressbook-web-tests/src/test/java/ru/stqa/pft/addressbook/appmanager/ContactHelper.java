package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillContactCreationForm(ContactData contactData){
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("address"),contactData.getAddress());
    type(By.name("email"),contactData.getEmail());
    type(By.name("home"),contactData.getHome());
  }

  public void clickEnter(){
    click(By.name("submit"));
  }

  public void selectContact(){
    click(By.name("selected[]"));
  }
  public void deleteSelectedContact(){
    click(By.id("content"));
  }


}
