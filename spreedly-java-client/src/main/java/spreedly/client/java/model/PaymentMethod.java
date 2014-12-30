package spreedly.client.java.model;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "payment_method") // Allows empty or missing elements
public class PaymentMethod extends Base
{
    // XXX: generate getters

    protected static final String EMAIL = "email";
    protected static final String DATA = "data";
    protected static final String STORAGE_STATE = "storage_state";
    protected static final String TEST = "test";
    protected static final String LAST_FOUR_DIGITS = "last_four_digits";
    protected static final String FIRST_SIX_DIGITS = "first_six_digits";
    protected static final String CARD_TYPE = "card_type";
    protected static final String FIRST_NAME = "first_name";
    protected static final String LAST_NAME = "last_name";
    protected static final String MONTH = "month";
    protected static final String YEAR = "year";
    protected static final String ADDRESS_1 = "address1";
    protected static final String ADDRESS_2 = "address2";
    protected static final String CITY = "city";
    protected static final String STATE = "state";
    protected static final String ZIP = "zip";
    protected static final String COUNTRY = "country";
    protected static final String PHONE_NUMBER = "phone_number";
    protected static final String SHIPPING_ADDRESS_1 = "shipping_address1";
    protected static final String SHIPPING_ADDRESS_2 = "shipping_address2";
    protected static final String SHIPPING_CITY = "shipping_city";
    protected static final String SHIPPING_STATE = "shipping_state";
    protected static final String SHIPPING_ZIP = "shipping_zip";
    protected static final String SHIPPING_COUNTRY = "shipping_country";
    protected static final String SHIPPING_PHONE_NUMBER = "shipping_phone_number";
    protected static final String FULL_NAME = "full_name";
    protected static final String ELIGIBLE_FOR_CARD_UPDATER = "eligible_for_card_updater";
    protected static final String PAYMENT_METHOD_TYPE = "payment_method_type";
    protected static final String ERRORS = "errors";
    protected static final String VERIFICATION_VALUE = "verification_value";
    protected static final String NUMBER = "number";

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
