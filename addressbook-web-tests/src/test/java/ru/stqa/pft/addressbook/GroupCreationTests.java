package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {

    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test", "test1", "test2"));
    submitGroupCreation();
    reternToGroupPage();
  }


}
