package de.mirko_werner.playwright_cucumber.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import de.mirko_werner.playwright_cucumber.models.PageObject;
import de.mirko_werner.testdata.persistence.models.Address;
import de.mirko_werner.testdata.persistence.models.Customer;

public class CheckoutPage extends PageObject {

    private final String firstNameSelector = "id=billing_first_name";
    private final String lastNameSelector = "id=billing_last_name";
    private final String countryDropdownIconSelector = "id=select2-billing_country-container";
    private final String searchFieldCountrySelector = "css=input[class='select2-search__field']";
    private final String streetAndNumberSelector = "id=billing_address_1";
    private final String citySelector = "id=billing_city";
    private final String stateDropdownIconSelector = "id=select2-billing_state-container";
    private final String searchFieldStateSelector = "css=input[class='select2-search__field']";
    private final String postcodeSelector = "id=billing_postcode";
    private final String emailSelector = "id=billing_email";
    private final String placeOrderBtnSelector = "id=place_order";
    private final String blockOverlaysSelector = "css=.blockUI.blockOverlay";
    private final String thankYouOrderTextSelector = "css=p[class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']";

    public void addBillingDetails(Customer customer) {
        page.waitForSelector(emailSelector);
        Address mainAddress = customer.getAddressList().stream()
                .filter(address -> address.addressType().contentEquals("primary")).findFirst()
                .orElse(null);
        assert mainAddress != null;
        page.fill(firstNameSelector, customer.getFirstName());
        page.fill(lastNameSelector, customer.getLastName());
        page.fill(postcodeSelector, mainAddress.postalCode());
        page.fill(streetAndNumberSelector, mainAddress.street() + " " + mainAddress.number());
        page.fill(emailSelector, customer.getEmailAddress());
        page.fill(citySelector, mainAddress.city());
        page.click(countryDropdownIconSelector);
        page.fill(searchFieldCountrySelector, mainAddress.country().getName());
        page.keyboard().press("Enter");
        page.click(stateDropdownIconSelector);
        page.fill(searchFieldStateSelector, mainAddress.state().getName());
        page.keyboard().press("Enter");
    }
    public void placeOrder() {
        page.waitForSelector(blockOverlaysSelector,
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
        page.click(placeOrderBtnSelector);
    }

    public String checkThankYouOrderText() {
        page.waitForSelector(blockOverlaysSelector,
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));

        return page.waitForSelector(thankYouOrderTextSelector).textContent();
    }
}
