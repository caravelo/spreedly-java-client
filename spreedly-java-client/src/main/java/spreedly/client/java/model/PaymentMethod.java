package spreedly.client.java.model;

import static spreedly.client.java.model.Fields.ADDRESS_1;
import static spreedly.client.java.model.Fields.ADDRESS_2;
import static spreedly.client.java.model.Fields.CARD_TYPE;
import static spreedly.client.java.model.Fields.CITY;
import static spreedly.client.java.model.Fields.COUNTRY;
import static spreedly.client.java.model.Fields.CREATED_AT;
import static spreedly.client.java.model.Fields.DATA;
import static spreedly.client.java.model.Fields.ELIGIBLE_FOR_CARD_UPDATER;
import static spreedly.client.java.model.Fields.EMAIL;
import static spreedly.client.java.model.Fields.FIRST_NAME;
import static spreedly.client.java.model.Fields.FIRST_SIX_DIGITS;
import static spreedly.client.java.model.Fields.FULL_NAME;
import static spreedly.client.java.model.Fields.LAST_FOUR_DIGITS;
import static spreedly.client.java.model.Fields.LAST_NAME;
import static spreedly.client.java.model.Fields.MONTH;
import static spreedly.client.java.model.Fields.NUMBER;
import static spreedly.client.java.model.Fields.PAYMENT_METHOD_TYPE;
import static spreedly.client.java.model.Fields.PHONE_NUMBER;
import static spreedly.client.java.model.Fields.SHIPPING_ADDRESS_1;
import static spreedly.client.java.model.Fields.SHIPPING_ADDRESS_2;
import static spreedly.client.java.model.Fields.SHIPPING_CITY;
import static spreedly.client.java.model.Fields.SHIPPING_COUNTRY;
import static spreedly.client.java.model.Fields.SHIPPING_PHONE_NUMBER;
import static spreedly.client.java.model.Fields.SHIPPING_STATE;
import static spreedly.client.java.model.Fields.SHIPPING_ZIP;
import static spreedly.client.java.model.Fields.STATE;
import static spreedly.client.java.model.Fields.STORAGE_STATE;
import static spreedly.client.java.model.Fields.TEST;
import static spreedly.client.java.model.Fields.TOKEN;
import static spreedly.client.java.model.Fields.UPDATED_AT;
import static spreedly.client.java.model.Fields.VERIFICATION_VALUE;
import static spreedly.client.java.model.Fields.YEAR;
import static spreedly.client.java.model.Fields.ZIP;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "payment_method")
public class PaymentMethod extends Base
{
    // XXX: generate getters

    @Element(name = EMAIL, required = false)
    protected final String email;

    @Element(name = DATA, required = false)
    protected final String data;

    // TODO: could be Enum type instead of String
    @Element(name = STORAGE_STATE)
    protected final String storageState;

    @Element(name = TEST)
    protected final boolean test;

    @Element(name = LAST_FOUR_DIGITS)
    protected final String lastFourDigits;

    @Element(name = FIRST_SIX_DIGITS)
    protected final String firstSixDigits;

    // TODO: could be Enum type instead of String
    @Element(name = CARD_TYPE)
    protected final String cardType;

    @Element(name = FIRST_NAME)
    protected final String firstName;

    @Element(name = LAST_NAME)
    protected final String lastName;

    @Element(name = MONTH)
    protected final int month;

    @Element(name = YEAR)
    protected final int year;

    @Element(name = ADDRESS_1, required = false)
    protected final String address1;

    @Element(name = ADDRESS_2, required = false)
    protected final String address2;

    @Element(name = CITY, required = false)
    protected final String city;

    @Element(name = STATE, required = false)
    protected final String state;

    @Element(name = ZIP, required = false)
    protected final String zip;

    @Element(name = COUNTRY, required = false)
    protected final String country;

    @Element(name = PHONE_NUMBER, required = false)
    protected final String phoneNumber;

    @Element(name = SHIPPING_ADDRESS_1, required = false)
    protected final String shippingAddress1;

    @Element(name = SHIPPING_ADDRESS_2, required = false)
    protected final String shippingAddress2;

    @Element(name = SHIPPING_CITY, required = false)
    protected final String shippingCity;

    @Element(name = SHIPPING_STATE, required = false)
    protected final String shippingState;

    @Element(name = SHIPPING_ZIP, required = false)
    protected final String shippingZip;

    @Element(name = SHIPPING_COUNTRY, required = false)
    protected final String shippingCountry;

    @Element(name = SHIPPING_PHONE_NUMBER, required = false)
    protected final String shippingPhoneNumber;

    @Element(name = FULL_NAME)
    protected final String fullName;

    @Element(name = ELIGIBLE_FOR_CARD_UPDATER)
    protected final boolean eligibleForCardUpdater;

    // TODO: could be Enum type instead of String
    @Element(name = PAYMENT_METHOD_TYPE)
    protected final String paymentMethodType;

    // TODO: when Error model object is available
//    @Element(name = ERRORS)
//    protected final List<Error> errors;

    @Element(name = VERIFICATION_VALUE, required = false)
    protected final String verification_value;

    @Element(name = NUMBER, required = false)
    protected final String number;

    public PaymentMethod(
            @Element(name = TOKEN) String token,
            @Element(name = CREATED_AT) Date createdAt,
            @Element(name = UPDATED_AT) String updatedAt,
            @Element(name = EMAIL) String email,
            @Element(name = DATA) String data,
            @Element(name = STORAGE_STATE) String storageState,
            @Element(name = TEST) boolean test,
            @Element(name = LAST_FOUR_DIGITS) String lastFourDigits,
            @Element(name = FIRST_SIX_DIGITS) String firstSixDigits,
            @Element(name = CARD_TYPE) String cardType,
            @Element(name = FIRST_NAME) String firstName,
            @Element(name = LAST_NAME) String lastName,
            @Element(name = MONTH) int month,
            @Element(name = YEAR) int year,
            @Element(name = ADDRESS_1) String address1,
            @Element(name = ADDRESS_2) String address2,
            @Element(name = CITY) String city,
            @Element(name = STATE) String state,
            @Element(name = ZIP) String zip,
            @Element(name = COUNTRY) String country,
            @Element(name = PHONE_NUMBER) String phoneNumber,
            @Element(name = SHIPPING_ADDRESS_1) String shippingAddress1,
            @Element(name = SHIPPING_ADDRESS_2) String shippingAddress2,
            @Element(name = SHIPPING_CITY) String shippingCity,
            @Element(name = SHIPPING_STATE) String shippingState,
            @Element(name = SHIPPING_ZIP) String shippingZip,
            @Element(name = SHIPPING_COUNTRY) String shippingCountry,
            @Element(name = SHIPPING_PHONE_NUMBER) String shippingPhoneNumber,
            @Element(name = FULL_NAME) String fullName,
            @Element(name = ELIGIBLE_FOR_CARD_UPDATER) boolean eligibleForCardUpdater,
            @Element(name = PAYMENT_METHOD_TYPE) String paymentMethodType,
//            @Element(name = ERRORS) List<Error> errors,
            @Element(name = VERIFICATION_VALUE) String verificationValue,
            @Element(name = NUMBER) String number
            )
    {
        super(token, createdAt, updatedAt);

        this.email = email;
        this.data = data;
        this.storageState = storageState;
        this.test = test;
        this.lastFourDigits = lastFourDigits;
        this.firstSixDigits = firstSixDigits;
        this.cardType = cardType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.month = month;
        this.year = year;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.shippingAddress1 = shippingAddress1;
        this.shippingAddress2 = shippingAddress2;
        this.shippingCity = shippingCity;
        this.shippingState = shippingState;
        this.shippingZip = shippingZip;
        this.shippingCountry = shippingCountry;
        this.shippingPhoneNumber = shippingPhoneNumber;
        this.fullName = fullName;
        this.eligibleForCardUpdater = eligibleForCardUpdater;
        this.paymentMethodType = paymentMethodType;
//        this.errors = errors;
        this.verification_value = verificationValue;
        this.number = number;
    }

}
