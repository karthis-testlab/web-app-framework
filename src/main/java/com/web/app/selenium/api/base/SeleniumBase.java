package com.web.app.selenium.api.base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.NoSuchDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.ConnectionClosedException;

import com.web.app.framework.utlis.general.Logs;
import com.web.app.framework.utlis.properties.ConfigPropertiesHandler;
import com.web.app.selenium.api.design.Browser;
import com.web.app.selenium.api.design.Element;
import com.web.app.selenium.api.design.Locators;

public class SeleniumBase extends DriverInstance implements Browser, Element {
	
	private static final ConfigPropertiesHandler config = ConfigFactory.create(ConfigPropertiesHandler.class);

	@Override
	public void executeJavaScript(String js, WebElement ele) {		
		getDriver().executeScript(js, ele);
	}

	@Override
	public void click(WebElement ele) {
		try {
			ele.click();
		} catch (ElementClickInterceptedException e) {
			new Logs().console().info("The exception is usually thrown when an attempt to click on an element on a web page is intercepted or blocked by another element. "+e.toString());
	        new Logs().file().info("The exception is usually thrown when an attempt to click on an element on a web page is intercepted or blocked by another element. "+e.toString());		
			executeJavaScript("arguments[0].scrollIntoView();", ele);
			ele.click();
		} catch (Exception e) {
			new Logs().console().fail("Unable to click the given "+ele.toString()+" webelement. Due to --> "+e.toString());
			new Logs().file().fail("Unable to click the given "+ele.toString()+" webelement. Due to --> "+e.toString());
            throw new RuntimeException("Unable to click the given "+ele.toString()+" webelement. Due to --> "+e.toString());
		}
	}

	@Override
	public void append(WebElement ele, String data) {
		ele.sendKeys(data);		
	}

	@Override
	public void clear(WebElement ele) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearAndType(WebElement ele, String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getElementText(WebElement ele) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBackgroundColor(WebElement ele) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypedText(WebElement ele) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectDropDownUsingText(WebElement ele, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectDropDownUsingValue(WebElement ele, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyDisplayed(WebElement ele) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyDisappeared(WebElement ele) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyEnabled(WebElement ele) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifySelected(WebElement ele) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void startApp(String url) {
		try {
			setDriver("chrome", false);
			getDriver().get(url);
			new Logs().console().pass("Successfully opened "+url+" application under test in the chrome browser.");
			new Logs().file().pass("Successfully opened "+url+" application under test in the chrome browser.");
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWaitTime()));
			new Logs().console().pass("Implicit wait time set to "+config.getImplicitWaitTime()+" seconds.");
			new Logs().file().pass("Implicit wait time set to "+config.getImplicitWaitTime()+" seconds.");
		} catch (SessionNotCreatedException sne) {
			new Logs().console().fail("A new session could not be successfully created. Due to --> "+sne.toString());
			new Logs().file().fail("A new session could not be successfully created. Due to --> "+sne.toString());
        } catch (ConnectionClosedException cce) {
        	new Logs().console().fail("The Driver Connection got lost. Due to --> "+cce.toString());
			new Logs().file().fail("The Driver Connection got lost. Due to --> "+cce.toString());
            throw new RuntimeException("The Driver Connection got lost. Due to --> "+cce.toString());
        } catch (UnreachableBrowserException ube) {
        	new Logs().console().fail("The chrome browser is unable to be opened or has crashed because of "+ube.toString());
			new Logs().file().fail("The chrome browser is unable to be opened or has crashed because of "+ube.toString());
            throw new RuntimeException("The chrome browser is unable to be opened or has crashed because of "+ube.toString());
        } catch (NoSuchDriverException nde) {
        	new Logs().console().fail("Unable to find chrome driver in the local machine and, Now trying to set driver by webdriver manager class. "+nde.toString());
			new Logs().file().fail("Unable to find chrome driver in the local machine and, Now trying to set driver by webdriver manager class. "+nde.toString());
            System.out.println("Unable to find chrome driver in the local machine and, Now trying to set driver by webdriver manager class. "+nde.toString());
        } catch (Exception e) {
        	new Logs().console().fail("Unable to launch given chrome browser. Due to --> "+e.toString());
			new Logs().file().fail("Unable to launch given chrome browser. Due to --> "+e.toString());
            throw new RuntimeException("Unable to launch given chrome browser. Due to --> "+e.toString());
        }
		
	}

	@Override
	public void startApp(String url, boolean headless) {
		try {
			setDriver(config.getDefaultBrowserName(), config.isBrowserHeadless());
			getDriver().get(url);
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWaitTime()));
		} catch (SessionNotCreatedException sne) {
			new Logs().console().fail("A new session could not be successfully created. Due to --> "+sne.toString());
			new Logs().file().fail("A new session could not be successfully created. Due to --> "+sne.toString());
        } catch (ConnectionClosedException cce) {
        	new Logs().console().fail("The Driver Connection got lost. Due to --> "+cce.toString());
			new Logs().file().fail("The Driver Connection got lost. Due to --> "+cce.toString());
            throw new RuntimeException("The Driver Connection got lost. Due to --> "+cce.toString());
        } catch (UnreachableBrowserException ube) {
        	new Logs().console().fail("The "+config.getDefaultBrowserName()+" browser is unable to be opened or has crashed because of "+ube.toString());
			new Logs().file().fail("The "+config.getDefaultBrowserName()+" browser is unable to be opened or has crashed because of "+ube.toString());
            throw new RuntimeException("The "+config.getDefaultBrowserName()+" browser is unable to be opened or has crashed because of "+ube.toString());
        } catch (NoSuchDriverException nde) {
        	new Logs().console().fail("Unable to find "+config.getDefaultBrowserName()+" driver in the local machine and, Now trying to set driver by webdriver manager class. "+nde.toString());
			new Logs().file().fail("Unable to find "+config.getDefaultBrowserName()+" driver in the local machine and, Now trying to set driver by webdriver manager class. "+nde.toString());
            System.out.println("Unable to find "+config.getDefaultBrowserName()+" driver in the local machine and, Now trying to set driver by webdriver manager class. "+nde.toString());
        } catch (Exception e) {
        	new Logs().console().fail("Unable to launch given "+config.getDefaultBrowserName()+" browser. Due to --> "+e.toString());
			new Logs().file().fail("Unable to launch given "+config.getDefaultBrowserName()+" browser. Due to --> "+e.toString());
            throw new RuntimeException("Unable to launch given "+config.getDefaultBrowserName()+" browser. Due to --> "+e.toString());
        }
	}

	@Override
	public void startApp(String browser, boolean headless, String url) {
		try {
			setDriver(browser, config.isBrowserHeadless());
			getDriver().get(url);
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWaitTime()));
		} catch (SessionNotCreatedException sne) {
			new Logs().console().fail("A new session could not be successfully created. Due to --> "+sne.toString());
			new Logs().file().fail("A new session could not be successfully created. Due to --> "+sne.toString());
        } catch (ConnectionClosedException cce) {
        	new Logs().console().fail("The Driver Connection got lost. Due to --> "+cce.toString());
			new Logs().file().fail("The Driver Connection got lost. Due to --> "+cce.toString());
            throw new RuntimeException("The Driver Connection got lost. Due to --> "+cce.toString());
        } catch (UnreachableBrowserException ube) {
        	new Logs().console().fail("The "+browser+" browser is unable to be opened or has crashed because of "+ube.toString());
			new Logs().file().fail("The "+browser+" browser is unable to be opened or has crashed because of "+ube.toString());
            throw new RuntimeException("The "+browser+" browser is unable to be opened or has crashed because of "+ube.toString());
        } catch (NoSuchDriverException nde) {
        	new Logs().console().fail("Unable to find "+browser+" driver in the local machine and, Now trying to set driver by webdriver manager class. "+nde.toString());
			new Logs().file().fail("Unable to find "+browser+" driver in the local machine and, Now trying to set driver by webdriver manager class. "+nde.toString());
            System.out.println("Unable to find "+browser+" driver in the local machine and, Now trying to set driver by webdriver manager class. "+nde.toString());
        } catch (Exception e) {
        	new Logs().console().fail("Unable to launch given "+browser+" browser. Due to --> "+e.toString());
			new Logs().file().fail("Unable to launch given "+browser+" browser. Due to --> "+e.toString());
            throw new RuntimeException("Unable to launch given "+browser+" browser. Due to --> "+e.toString());
        }
	}

	@Override
	public WebElement locateElement(Locators locatorType, String value) {
		WebElement element = null;
		try {
			switch (locatorType) {
			case ID:
				element = getDriver().findElement(By.id(value));
				new Logs().console().pass("Found the given weblement "+value+" using id locator type.");
				break;
			case NAME:
				element = getDriver().findElement(By.name(value));
				break;
			case CLASS_NAME:
				element = getDriver().findElement(By.className(value));
				break;
			default:
				break;
			}
		} catch (NoSuchElementException e) {
			new Logs().console().fail("Unable to found given web element "+value+" using"+locatorType.toString()+" type. "+e.toString());
			throw new RuntimeException("Unable to found given web element "+value+" using"+locatorType.toString()+" type."+e.toString());
		} catch (StaleElementReferenceException e) {
			
		} catch (Exception e) {
			
		}
		return element;
	}

	@Override
	public WebElement locateElement(String value) {		
		WebElement element = null;
		try {
			element = getDriver().findElement(By.id(value));
		} catch(Exception e) {
			
		}
		return element;
	}

	@Override
	public List<WebElement> locateElements(Locators locatorType, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void switchToAlert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptAlert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dismissAlert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAlertText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void typeAlert(String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void switchToWindow(int index) {
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(index));
	}

	@Override
	public boolean switchToWindow(String title) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void switchToFrame(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void switchToFrame(WebElement ele) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void switchToFrame(String idOrName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defaultContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyUrl(String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyTitle(String title) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

}