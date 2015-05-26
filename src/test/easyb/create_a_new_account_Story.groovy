import wadp.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


description """A new user account can be created 
              if a proper unused username 
              and a proper password are given"""

scenario "creation successfully with correct username and password", {
    given 'command new user is selected', {

    }
 
    when 'a valid username and password are entered', {

    }

    then 'new user is registered to system', {

    }
}

scenario "can login with successfully generated account", {
    given 'command new user is selected', {
    }
 
    when 'a valid username and password are entered', {
    }

    then  'new credentials allow logging in to system', {
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected'
    when 'a valid username and too short password are entered'
    then 'new user is not be registered to system'
}

scenario "creation fails with correct username and password consisting of letters", {
    given 'command new user is selected'
    when 'a valid username and password consisting of letters are entered'
    then 'new user is not be registered to system'
}

scenario "creation fails with too short username and valid password", {
    given 'command new user is selected'
    when 'a too sort username and valid password are entered'
    then 'new user is not be registered to system'
}

scenario "creation fails with already taken username and valid password", {
    given 'command new user is selected'
    when 'a already taken username and valid password are entered'
    then 'new user is not be registered to system'
}

scenario "can not login with account that is not successfully created", {
    given 'command new user is selected'
    when 'a invalid username/password are entered'
    then  'new credentials do not allow logging in to system'
}