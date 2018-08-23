package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation(){
      app.getNavigationHelper().goToAddNewContactPage();
      app.getContactHelper().fillContactCreationForm(new ContactData("firstName", "middleName", "lastName",
              "nick", "title", "address", "email", "home"));
      app.getContactHelper().clickEnter();
      app.getNavigationHelper().goToHomePage();

  }


}
