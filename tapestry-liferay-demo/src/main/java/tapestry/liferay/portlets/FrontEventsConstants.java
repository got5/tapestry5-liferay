package tapestry.liferay.portlets;

public class FrontEventsConstants {

	private FrontEventsConstants() {
	}

	/**
	 * Component can trigger this event when they change the current selected shop.
	 */
	public static final String SELECT_SHOP = "selectShop";

	/**
	 * Component can trigger this event when they change the current selected container.
	 */
	public static final String SELECT_CONTAINER = "selectContainer";
	/**
	 * Component can trigger this event when they change the current selected product.
	 */
	public static final String SELECT_PRODUCT = "selectProduct";

	/**
	 * Component can trigger this event when they change the current selected page.
	 */
	public static final String SELECT_PAGE = "selectPage";

	/**
	 * Component can trigger this event when they change the current sorting parameter.
	 */
	public static final String SELECT_SORTING = "selectSorting";

	/**
	 * Component can trigger this event when the userwants to validate the basket. The tunnel purchase should be called
	 */
	public final static String VALIDATION_BASKET_PAGE_REQUESTED = "VALIDATION_BASKET_PAGE_REQUESTED";

	/**
	 * Component can trigger this event when the user doesn't want to validate the basket.
	 */
	public final static String CANCEL_BASKET_PAGE_REQUESTED = "CANCEL_PAGE_REQUESTED";

	/**
	 * Component can trigger this event when the user wants to go to a specific product page
	 */
	public final static String PRODUCT_PAGE_REQUESTED = "PRODUCT_PAGE_REQUESTED";

	/**
	 * Component can trigger this event when the user wants to remove an item from the basket
	 */
	public final static String REMOVE_BASKET_ITEM_REQUESTED = "REMOVE_BASKET_ITEM_REQUESTED";

	/**
	 * Component can trigger this event when the user wants to see the full basket page.
	 */
	public final static String BASKET_PAGE_REQUESTED = "BASKET_PAGE_REQUESTED";

	/**
	 * Component can trigger this event when the user enter a discount code and wants to validate it
	 */
	public final static String DISCOUNT_CODE_SUBMITED = "DISCOUNT_CODE_SUBMITED";

	/**
	 * Component can trigger this event an item quantity have to be changed
	 */
	public final static String BASKETITEM_CHANGE_QUANTITY_REQUESTED = "BASKETITEM_QUANTITY_REQUESTED";

	/**
	 * Component can trigger this event when the user wants to add an item to the basket
	 */
	public final static String ADD_TO_BASKET_REQUESTED = "ADD_TO_BASKET_REQUESTED";

	/**
	 * Component can trigger this event when the user wants to add an item to the basket from the product component.
	 */
	public final static String ADD_TO_BASKET_REQUESTED_FROM_PRODUCT = "ADD_TO_BASKET_REQUESTED_FROM_PRODUCT";

	/**
	 * Component can trigger this event when the user wants to add MORE than one item to the basket
	 */
	public final static String ADD_ITEMS_RELATED_TO_BASKET_REQUESTED = "ADD_ITEMS_RELATED_TO_BASKET_REQUESTED";

	public final static String ADD_ITEMS_CROSSSELLING_TO_BASKET_REQUESTED = "ADD_ITEMS_CROSSSELLING_TO_BASKET_REQUESTED";

	public final static String CHARACTERISTIC_VALUE_SELECTED = "CHARACTERISTIC_VALUE_SELECTED";

	public final static String VARIATION_FILTERED = "VARIATION_FILTERED";
	/**
	 * Component can trigger this event when the user enter a search value and wants to process it
	 */
	public final static String SEARCH_SUBMITED = "SEARCH_SUBMITED";

	public final static String ACTION_LINK_FACET = "ACTION_LINK_FACET";

	public final static String ACTION_LINK_MONO_FACET = "ACTION_LINK_MONO_FACET";

	public static final String AUTOCOMPLETE = "ACTION_AUTOCOMPLETE";

	public final static String ACTION_LINK_UNSELECT_FACETVALUE = "ACTION_LINK_UNSELECT_FACETVALUE";

	public static final String CORE_SUBMITED = "CORE_SUBMITED";

	public static final String GET_FACET_ZONE = "facet.form.zone";

	/**
	 * Component can trigger it when the user click on a step.
	 */
	public static final String CLICK_STEP = "CLICK_STEP";

	public static final String SUGGESTER_ARTICLE_CLICKED = "serveArticleSuggesterArticleClicked";

	public static final String TREE_CONTAINER_NODE_SELECTED = "serveTreeContainerNodeSelected";

	public static final String TREE_CONTAINER_NODE_UNSELECTED = "serveTreeContainerNodeUnselected";

	/**
	 * Component AddressBook events constants
	 */

	public static final String EDIT_ADDRESS_TO_UPDATE = "editAddressToUpdate";

	public static final String NEW_ADDRESS_TO_CREATE = "newAddressToCreate";

	public static final String UPDATE_ADDRESS_SUCCESS = "updateAddressSuccess";

	public static final String CANCEL_UPDATE_ADDRESS = "cancelUpdateAddress";

	public static final String CANCEL_CREATE_ADDRESS = "cancelCreateAddress";

	public static final String CREATE_ADDRESS_SUCCESS = "createAddressSuccess";

	public static final String DELETE_ADDRESS = "deleteAddress";

	public static final String BILLING_ADDRESS_SELECTED = "billingAddressSelected";

	public static final String DELIVERY_ADDRESS_SELECTED = "deliveryAddressSelected";

	public static final String ADDRESS_BOOK_CANCEL = "addressBookCancel";

	public static final String ADDRESS_BOOK_SUCCESS = "addressBookSuccess";

	public static final String ADDRESS_BOOK_FAILURE = "addressBookFailure";

	public static final String ADDRESS_BOOK_NEXT_PAGE = "addressBookNextPage";

	public static final String ADDRESS_BOOK_CLOSE = "addressBookClose";

	/**
	 * Component Contact Form
	 */

	public static final String CONTACT_FORM_VALIDATE = "contactFormValidate";

	public static final String CONTACT_FORM_SUCCESS = "contactFormSuccess";

	public static final String CONTACT_FORM_FAILURE = "contactFormFailure";

	public static final String CONTACT_FORM_SUBMIT = "contactFormSubmit";

	/**
	 * Component Creation account
	 */

	public static final String ACCOUNT_CREATION_VALIDATE = "accountCreationValidate";

	public static final String ACCOUNT_CREATION_SUBMITED = "accountCreationSubmit";

	public static final String ACCOUNT_CREATION_FAILURE = "accountCreationFailure";

	public static final String ACCOUNT_CREATION_SUCCESS = "accountCreationSuccess";

	public static final String DISCOUNT_CODE_DELETED = "DISCOUNT_CODE_DELETED";

	public static final String LOGIN_FORM_VALIDATE = "loginformvalidate";

	public static final String LOGIN_FORM_FAILURE = "LOGIN_FORM_FAILURE";

	public static final String LOGIN_FORM_SUCCESS = "LOGIN_FORM_SUCCESS";

	public static final String LOGIN_FORM_SUBMIT = "LOGIN_FORM_SUBMIT";

	public static final String LOGIN_FORM_FORGETPASSWORD = "LOGIN_FORM_FORGETPASSWORD";

	public static final String LOGIN_FORM_REGISTER = "LOGIN_FORM_REGISTER";

	public static final String DDSLICK_ON_SELECTED_ELEMENT = "ddslick_on_selected_element";

	/**
	 * Component can trigger this event when the user need to change his delivery address
	 */
	public final static String CHANGE_ADDRESS_PAGE_REQUESTED = "CHANGE_ADDRESS_PAGE_REQUESTED";

}
